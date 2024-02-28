package bcgov.jh.etk.jhetkcommon.service.impl;

import java.net.URI;
import java.util.concurrent.Future;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.dataaccess.dao.EtkDaoService;
import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.EtkInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.EtkTicket;
import bcgov.jh.etk.jhetkcommon.model.FtpFileList;
import bcgov.jh.etk.jhetkcommon.model.PathConst;
import bcgov.jh.etk.jhetkcommon.model.SftpAPIOutput;
import bcgov.jh.etk.jhetkcommon.model.TransitionConfig;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTransitionMode;
import bcgov.jh.etk.jhetkcommon.service.EmailService;
import bcgov.jh.etk.jhetkcommon.service.IWorkerService;
import bcgov.jh.etk.jhetkcommon.service.RestService;
import bcgov.jh.etk.jhetkcommon.util.InterfaceStateUtil;
import bcgov.jh.etk.jhetkcommon.util.PauseResumeUtil;
import bcgov.jh.etk.jhetkcommon.util.StringUtil;

/**
 * The Class RepublishService.
 */
@Service
public class WorkerService implements IWorkerService {
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(WorkerService.class);

	/** The vph dao. */
	@Autowired(required = false)
	EtkDaoService etkDao;
	
	/** The rest service. */
    @Autowired
    RestService restService;
    
    @Autowired
    ErrorService errorService;
    
    @Autowired
    EmailService emailService;
    
    /** The interface state util. */
	@Autowired
	InterfaceStateUtil interfaceStateUtil;
	
	/**
	 * Re publish helper, call to prime adapter to reprocess the ticket.
	 *
	 * @param etkTicket the etk ticket
	 */
	@Override
	@Async
	public void rePublishHelper(EtkTicket etkTicket) {
		if (StringUtils.isBlank(ApplicationProperties.PRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI)) {
	    	logger.error("Ticket reprocess server URI isn't configrued: {}, cannot proceed with ticket reprocessing.", ApplicationProperties.PRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI);
	    } else {
			String url = ApplicationProperties.PRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI + PathConst.PATH_TICKET_REPROCESS;
			logger.info("Reprocess ticket[{}], send it to this url: {}.", etkTicket.getTicketNumber(), url);

			//send ticket to prime adapter to process
		    try {
		    	restService.restfulSave(url, etkTicket, HttpMethod.POST, MediaType.APPLICATION_JSON);
			} catch (Exception e) {
				Integer ticketID = etkTicket.getTicketID() == null ? null : etkTicket.getTicketID().intValue();
				String errorDescription = "Fail sending ticket [" + etkTicket.getTicketNumber() + "; " + ticketID + "] to primeAdapter for reprocessing, error message: " + e.toString() + "; " + e.getMessage();
				logger.error(errorDescription);
				//raise an I11 error
				errorService.saveError(ticketID, EnumErrorCode.I11, "WorkerService.rePublishHelper", errorDescription, Const.CONST_JH_ETK, null, null, etkTicket.getSource_xml_txt(), true);

				try {
					// Exception occurred, set ticket to be 'QUEUED'
					etkDao.UpdateTickeProcessStateByTicketID(ticketID, EnumInterface.ICBC_CC, EnumProcessState.QUEUED);
					//stop the hub
					stopHub(Const.CONST_JH_ETK, EnumInterfaceState.STOPPED, EnumTransitionMode.AUTO);
				} catch (EtkDataAccessException e1) {
					logger.error("Exception occurred while accessing db: ", e1.toString() + "" + e1.getMessage()); 
				}
	    	}
	    }
	}

	/**
	 * Stop hub.
	 *
	 * @param updUserID the upd user ID
	 * @param newState the new state
	 * @param mode the mode
	 * @throws EtkDataAccessException 
	 */
	private void stopHub(final String updUserID, EnumInterfaceState newState, final EnumTransitionMode mode) throws EtkDataAccessException {
		logger.error("Auto stopped the hub");
		EtkInterfaceState currentState = interfaceStateUtil.getInterfaceState(EnumInterface.ICBC_CC);
		
		//check isTransitionable
		TransitionConfig config = new TransitionConfig(updUserID, currentState.getInterfaceState(), newState, false, false, EnumTransitionMode.AUTO, "Ticket issuance process", "");
		PauseResumeUtil.isTransitionable(config, false);
		
		// if canTransition, update ICBC_CC interface
		if (config.isCanTransition()) {
			etkDao.UpdateInterface(EnumInterface.ICBC_CC, newState, updUserID);
		}
		if (config.isCanEmail()) {
			emailService.sendSimpleMessage(config.getEmailSubject(), config.getEmailBody());
		}
		etkDao.UpdateInterface(EnumInterface.ICBC_CC, EnumInterfaceState.STOPPED, Const.CONST_JH_ETK);
	}
	
	@Override
	@Async
	public Future<SftpAPIOutput> fileCheck(String fileName) {
		SftpAPIOutput fileExistence = interfaceStateUtil.checkFileExistenceHelper (fileName);
		return new AsyncResult<SftpAPIOutput>(fileExistence);
		
	}
	
	@Override
	public SftpAPIOutput fileCheck_sync(String fileName) {
		return interfaceStateUtil.checkFileExistenceHelper(fileName);
	}
	
	@Override
	public boolean ftpConnectivityCheck() {
		if (!StringUtil.isPropertyValueConfigured(ApplicationProperties.FTP_FILE_MANAGEMENT_URI)) {
			return false;
		}
		String url = ApplicationProperties.FTP_FILE_MANAGEMENT_URI + PathConst.PATH_FTP_CONNECT_CHECK;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

        URI uri = builder.build().encode().toUri();
        		
		//check connectivity
	    try {
	    	ResponseEntity<String> response = restService.restfulSave(uri.toString(), null, HttpMethod.GET, MediaType.APPLICATION_JSON);
	    	
			//success
			if (HttpStatus.OK.equals(response.getStatusCode())) {
				return true;
			}
		} catch (Exception e) {
    	}
	    
		return false;	
	}

	/**
	 * Check file existence helper.
	 *
	 * @param fileName the file name
	 * @return the FTP file existence
	 */
	
	@Override
	public boolean fileDelete(String fileName) {
		if (!StringUtil.isPropertyValueConfigured(ApplicationProperties.FTP_FILE_MANAGEMENT_URI)) {
			return false;
		}
		String url = ApplicationProperties.FTP_FILE_MANAGEMENT_URI + PathConst.PATH_FTP_FILE_DELETE;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		boolean successfullyDeleted = false;

		//build the param
        if (fileName != null) {
        	builder.queryParam("fileFullPath", fileName);
        }
        URI uri = builder.build().encode().toUri();
        
		logger.info("Delete file [{}]", fileName);
		SftpAPIOutput ftpFileDelete = null;
		try {
			ResponseEntity<String> response = restService.restfulSave(uri.toString(), null, HttpMethod.POST,  MediaType.APPLICATION_JSON);
			if (StringUtils.isNotBlank(response.getBody())) {
				ftpFileDelete = interfaceStateUtil.processResponse(response.getBody());
				if (ftpFileDelete != null && ftpFileDelete.isResult()) {
					successfullyDeleted = true;
					logger.debug("File [{}] successfully deleted", fileName);
				} else {
					logger.error("Fail deleting the file [{}], error: {}", fileName, ftpFileDelete == null ? "Invalid response received." : ftpFileDelete.getErrorDescription());
				}
			} else {
				logger.error("Fail deleting the file [{}], empty response received", fileName);
			}
		} catch (Exception e) {
			logger.error("Exception occurred when deleting file [{}], error message: {}", fileName, e.toString() + "; " + e.getMessage());
		}
		return successfullyDeleted;
	}

	/**
	 * File batch delete.
	 *
	 * @param fileNames the file names
	 * @return true, if successful
	 */
	@Override
	public SftpAPIOutput fileBatchDelete(String fileNames) {
		SftpAPIOutput ftpFileDelete = new SftpAPIOutput();
		if (!StringUtil.isPropertyValueConfigured(ApplicationProperties.FTP_FILE_MANAGEMENT_URI)) {
			ftpFileDelete.setErrorDescription("FTP_FILE_MANAGEMENT_URI property not configured");
			return ftpFileDelete;
		}
		String url = ApplicationProperties.FTP_FILE_MANAGEMENT_URI + PathConst.PATH_FTP_BATCH_FILE_DELETE;
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
		
		//build the param
        if (fileNames != null) {
        	builder.queryParam("fileNames", fileNames);
        }
        URI uri = builder.build().encode().toUri();
		logger.info("Batch file deletion [{}]", fileNames);
		
		String message = "";
		try {
			ResponseEntity<String> response = restService.restfulSave(uri.toString(), null, HttpMethod.POST,  MediaType.APPLICATION_JSON);
			if (StringUtils.isNotBlank(response.getBody())) {
				ftpFileDelete = interfaceStateUtil.processResponse(response.getBody());
				return ftpFileDelete;
			} else {
				message = "Fail batch deleting [" + fileNames + "], empty response received";
				logger.error(message);
			}
		} catch (Exception e) {
			message = "Exception occurred when batch deleting [" + fileNames + "], error message: " + e.toString() + "; " + e.getMessage();
			logger.error(message);
		}
		ftpFileDelete.setErrorDescription(message);
		return ftpFileDelete;
	}
	
	@Override
	public FtpFileList getListOfFTPFiles() {
		if (!StringUtil.isPropertyValueConfigured(ApplicationProperties.FTP_FILE_MANAGEMENT_URI)) {
			return null;
		}
		String url = ApplicationProperties.FTP_FILE_MANAGEMENT_URI + PathConst.PATH_FTP_FILE_GET;
		logger.info("Get list of ftp files from this URL: {}", url);
		FtpFileList ftpFiles = null;
		
		//get list of FTP files
	    try {
	    	ResponseEntity<String> response = restService.restfulSave(url, null, HttpMethod.GET, MediaType.APPLICATION_JSON);
	    	
			//success, process the response from ICBC
			if (HttpStatus.OK.equals(response.getStatusCode()) || HttpStatus.CREATED.equals(response.getStatusCode())) {
				if (StringUtils.isNotBlank(response.getBody())) {
	            	// convert JSON string to object
	            	ObjectMapper objectMapper = new ObjectMapper();
	            	try {
	        			ftpFiles = objectMapper.readValue(response.getBody(), FtpFileList.class);
	        			logger.debug("Get list of ftp files succeeded, found {} files.", ftpFiles == null ? 0 : ftpFiles.getNumFiles());
	        		} catch (Exception e) {
	        			logger.error("Failed to converting ftp files string to object, error: {}", e.toString() + "; " + e.getMessage());
	        		} 
	            } else {
	            	logger.error("Failed getting list of ftp files, nothing returned");
	            }
			}
		} catch (Exception e) {
			logger.error("Fail getting list of FTP files, error message: {}", e.toString() + "; " + e.getMessage());
    	}
		return ftpFiles;
	}

	@Override
	public void WritePauseFile() {
		PauseResumeUtil.WritePauseFile(restService);
	}

	/**
	 * Checks if the interface is stopped.
	 *
	 * @param interfaceCD the interface CD
	 * @return true, if the interface state is null or stopped
	 */
	@Override
	public boolean isInterfaceStopped (EnumInterface interfaceCD) {
		EtkInterfaceState interfaceState = interfaceStateUtil.getInterfaceState(interfaceCD);
		if (interfaceState == null || 
			interfaceState.getInterfaceState() == null || 
			EnumInterfaceState.STOPPED.equals(interfaceState.getInterfaceState())) {
			return true;
		}
		return false;
	}
	
	/**
	 * Checks if is interface running.
	 *
	 * @param interfaceCD the interface CD
	 * @return true, if is interface running
	 */
	@Override
	public boolean isInterfaceRunning (EnumInterface interfaceCD) {
		EtkInterfaceState interfaceState = interfaceStateUtil.getInterfaceState(interfaceCD);
		if (interfaceState != null && 
			interfaceState.getInterfaceState() != null &&
			EnumInterfaceState.RUNNING.equals(interfaceState.getInterfaceState())) {
			return true;
		}
		return false;
	}

	@Override
	public EtkInterfaceState getInterfaceState(EnumInterface interfaceCD) {
		return interfaceStateUtil.getInterfaceState(interfaceCD);
	}
}
