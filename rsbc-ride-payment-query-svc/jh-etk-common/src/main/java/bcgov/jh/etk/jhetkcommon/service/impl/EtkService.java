package bcgov.jh.etk.jhetkcommon.service.impl;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.dataaccess.dao.EtkDaoService;
import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.AuditRecord;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.EtkAgencyCodeText;
import bcgov.jh.etk.jhetkcommon.model.EtkAgencyStatistics;
import bcgov.jh.etk.jhetkcommon.model.EtkDisputeFinding;
import bcgov.jh.etk.jhetkcommon.model.EtkError;
import bcgov.jh.etk.jhetkcommon.model.EtkReport;
import bcgov.jh.etk.jhetkcommon.model.EtkReportKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketErrorDetail;
import bcgov.jh.etk.jhetkcommon.model.EtkTicketCntStatistics;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketErrorDetailWrapper;
import bcgov.jh.etk.jhetkcommon.model.EtkTicket;
import bcgov.jh.etk.jhetkcommon.model.EventNotificationTarget;
import bcgov.jh.etk.jhetkcommon.model.FTPFileInfo;
import bcgov.jh.etk.jhetkcommon.model.FTPFileRelatedError;
import bcgov.jh.etk.jhetkcommon.model.FileDeletable;
import bcgov.jh.etk.jhetkcommon.model.FtpFileInstance;
import bcgov.jh.etk.jhetkcommon.model.FtpFileList;
import bcgov.jh.etk.jhetkcommon.model.PathConst;
import bcgov.jh.etk.jhetkcommon.model.PrimeIssuanceReconRecord;
import bcgov.jh.etk.jhetkcommon.model.SftpAPIOutput;
import bcgov.jh.etk.jhetkcommon.model.BankingFeePerTicketType;
import bcgov.jh.etk.jhetkcommon.model.TransitionConfig;
import bcgov.jh.etk.jhetkcommon.model.EtkErrorComments;
import bcgov.jh.etk.jhetkcommon.model.EtkInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.EtkInterfaceStateTransition;
import bcgov.jh.etk.jhetkcommon.model.EtkQueuedDisputeRecord;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumAuditEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorStatus;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTransitionMode;
import bcgov.jh.etk.jhetkcommon.model.eventing.Event;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.KafkaEvent;
import bcgov.jh.etk.jhetkcommon.model.task.TaskDetails;
import bcgov.jh.etk.jhetkcommon.service.EmailService;
import bcgov.jh.etk.jhetkcommon.service.IEtkService;
import bcgov.jh.etk.jhetkcommon.service.RestService;
import bcgov.jh.etk.jhetkcommon.util.EvtEventingUtil;
import bcgov.jh.etk.jhetkcommon.util.KafkaEvtEventingUtil;
import bcgov.jh.etk.jhetkcommon.util.PauseResumeUtil;
import bcgov.jh.etk.jhetkcommon.util.icbc.ICBCUtil;

/**
 * The Class EtkService.
 * @author HLiang
 */
@Service
public class EtkService implements IEtkService {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(EtkService.class);
	
	/** The vph dao. */
	@Autowired(required = false)
	EtkDaoService etkDao;

	/** The email service. d
	 *  Set required=false, to make EmailService bean optional
	 **/
	@Autowired(required = false)
	EmailService emailService;

	/** The email sender. */
	@Autowired(required = false)
    public JavaMailSender emailSender;
	
	/** The rest service. */
    @Autowired
    RestService restService;

    /** The republish service. */
    @Autowired
    WorkerService workerService;
	
	/**
	 * Gets the etk errors.
	 *
	 * @param startDateTime the start date time
	 * @param endDateTime the end date time
	 * @param errorStatuses the error statuses
	 * @param errorID the error ID
	 * @return the etk errors
	 * @throws EtkDataAccessException the etk data access exception
	 */
	/* (non-Javadoc)
	 * @see ca.bc.gov.pssg.vph.sc.dataaccess.service.IVPHService#getVPHErrors(java.lang.String, java.lang.String, java.lang.String, java.math.BigInteger)
	 */
	@Override
	public List<EtkError> getEtkErrors(String startDateTime, String endDateTime, List<EnumErrorStatus> errorStatuses,
			String errorID) throws EtkDataAccessException {
		logger.debug("Get errors, startDateTime: {}, endDateTime: {}, errorID: {}", startDateTime, endDateTime, errorID);
		return etkDao.getVPHErrors(startDateTime, endDateTime, errorStatuses, errorID);
	}

	/**
	 * Update ticket data.
	 *
	 * @param ticketID the ticket id
	 * @param outboundXML the outbound XML
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void updateTicketData(Integer ticketID, String outboundXML) throws EtkDataAccessException {
		logger.debug("Update ticket outboundXML, ticketID: {}", ticketID);
		etkDao.updateTicketdata(ticketID, outboundXML);
	}

	/**
	 * Gets the VPH error comments by error ID.
	 *
	 * @param errorID the error ID
	 * @return the VPH error comments by error ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<EtkErrorComments> getVPHErrorCommentsByErrorID(String errorID) throws EtkDataAccessException {
		logger.debug("Get error comments by errorID[{}]", errorID);
		return etkDao.getCommentsByErrorID(errorID);
	}

	/**
	 * Update error.
	 *
	 * @param errorId the error id
	 * @param errorStatus the error status
	 * @param assignToUserID the assign to user ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void updateError(String errorId, EnumErrorStatus errorStatus, String assignToUserID)
			throws EtkDataAccessException {
		logger.debug("Update error, errorID: {}, errorStatus: {}, assignToUserID: {}", errorId, errorStatus == null ? null : errorStatus.getCodeValue(), assignToUserID);
		etkDao.updateError(errorId, errorStatus, assignToUserID);
		
	}

	/**
	 * Log user access.
	 *
	 * @param errorId the error id
	 * @param accessingUserID the accessing user ID
	 * @param auditEventType the audit event type
	 * @return the integer
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public Integer logUserAccess(String errorId, String accessingUserID, EnumAuditEventType auditEventType)
			throws EtkDataAccessException {
		logger.debug("Log user error access, errorId: {}, accessingUserID: {}, auditEventType: {}", errorId, accessingUserID, auditEventType == null ? null : auditEventType.getCodeValue());
		return etkDao.logUserAccess(errorId, accessingUserID, auditEventType);
		
	}

	/**
	 * Log user comment.
	 *
	 * @param errorId the error id
	 * @param authorUserID the author user ID
	 * @param comments the comments
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void logUserComment(String errorId, String authorUserID, String comments) throws EtkDataAccessException {
		logger.debug("Log user comment, errorId: {}, authorUserID: {}, comments: {}", errorId, authorUserID, comments);
		etkDao.logUserComment(errorId, authorUserID, comments);
		
	}

	/**
	 * Gets the etk ticket.
	 *
	 * @param ticketID the ticket ID
	 * @return the etk ticket
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public EtkTicket getEtkTicket(Integer ticketID) throws EtkDataAccessException {
		logger.debug("Get ticket details, ticketID: {}", ticketID);
		return etkDao.getTicketDetail(ticketID);
	}
	
	/**
	 * Gets the ticket detail by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @return the ticket detail by ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<EtkTicket> getTicketDetailByTicketNO(final String ticketNO) throws EtkDataAccessException {
		List<EtkTicket> ticketDetailList = etkDao.getTicketDetailByTicketNO(ticketNO);
		return ticketDetailList;
		
	}
	
	/**
	 * Gets the etk ticket statistics.
	 *
	 * @param endEnteredDT the end entered DT
	 * @param displayStart the display start
	 * @param displaySize the display size
	 * @param searchText the search text
	 * @param sortField the sort field
	 * @param sortDirection the sort direction
	 * @return the etk ticket statistics
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public EtkStatisticsTicketErrorDetailWrapper getEtkTicketStats(OffsetDateTime endEnteredDT, Integer displayStart, Integer displaySize, String searchText,
			String sortField, String sortDirection) throws EtkDataAccessException {
		logger.debug("Get ticket statistics");
		return etkDao.getTicketStatistics(endEnteredDT, displayStart, displaySize, searchText, sortField, sortDirection);
	}

	/**
	 * Gets the etk ticket cnt.
	 *
	 * @return the etk ticket cnt
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public EtkTicketCntStatistics getEtkTicketCnt() throws EtkDataAccessException {
		logger.debug("Get ticket count");
		return etkDao.getTicketCnt();
	}
	
	/**
	 * Gets the etk agency stats.
	 *
	 * @return the etk agency stats
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public EtkAgencyStatistics getEtkAgencyStats() throws EtkDataAccessException {
		logger.debug("Get agency statistics");
		return etkDao.getAgencyStats();
	}
	
	/**
	 * Gets the etk report data.
	 *
	 * @param ticketNO the ticket NO
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param dataTypeFilter the data type filter
	 * @param agencyFilter the agency filter
	 * @return the etk report data
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public EtkReport getEtkReportData(String ticketNO, String startDate, String endDate, String dataTypeFilter, String agencyFilter) throws EtkDataAccessException {
		logger.debug("Get report data, ticketNO: {}, startDate: {}, endDate: {}, dataTypeFilter: {}, agencyFilter: {}", ticketNO, startDate, endDate, dataTypeFilter, agencyFilter);
		return etkDao.getEtkReportData(ticketNO, startDate, endDate, dataTypeFilter, agencyFilter);
	}
	
	/**
	 * Creates the ticket.
	 *
	 * @param sourceXML the source XML
	 * @param fileName the file name
	 * @param interfaceName the interface name
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public Map<String, Object> createTicket(String sourceXML, String fileName, String interfaceName)
			throws EtkDataAccessException {
		logger.debug("Save ticket, fileName: {}", fileName);
		return etkDao.createTicketStoreProc(sourceXML, fileName, interfaceName);
	}
	
	
	/**
	 * Gets the errors by ticket I ds.
	 *
	 * @param ticketIDs the ticket I ds
	 * @return the hash map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public HashMap<Integer, List<EtkError>> GetErrorsByTicketIDs(String ticketIDs) throws EtkDataAccessException {
		logger.debug("Get errors by ticketIDs: {}", ticketIDs);
		return etkDao.GetErrorsByTicketIDs(ticketIDs);
	}
	
	/**
	 * Gets the icbc payment code.
	 *
	 * @param icbcPaymentMsgCode the icbc payment msg code
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public Map<String, String> GetIcbcPaymentCode(String icbcPaymentMsgCode) throws EtkDataAccessException {
		logger.debug("Get ICBC payment code details: {}", icbcPaymentMsgCode);
		return etkDao.GetIcbcPaymentCode(icbcPaymentMsgCode);
	}
	
	/**
	 * Gets the next queued ticket.
	 *
	 * @return the etk ticket
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public EtkTicket GetNextQueuedTicket() throws EtkDataAccessException {
		logger.debug("Get next queued ticket");
		return etkDao.GetNextQueuedTicket();
	}
	
	/**
	 * Gets the tickets by evtstates.
	 *
	 * @param evtStates the evt states
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<EtkTicket> GetTicketsByEvtstates(List<EnumProcessState> evtStates) throws EtkDataAccessException {
		logger.debug("Get tickets by processing states.");
		return etkDao.GetTicketsByEvtstates(evtStates);
	}

	/**
	 * Gets the ticket kpi details.
	 *
	 * @param ticketNO the ticket NO
	 * @return the etk report KPI details
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public EtkReportKPIDetails GetTicketKpiDetails(final String ticketNO) throws EtkDataAccessException {
		logger.debug("Get KPIDetails by ticketno: {}.", ticketNO);
		return etkDao.GetTicketKpiDetails(ticketNO);
	}
	
	
	/**
	 * Gets the tickets kpi details.
	 *
	 * @param ticketNOs a list of comma deliminated ticket numbers
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public Map<String, EtkReportKPIDetails> GetTicketsKpiDetails(final String ticketNOs) throws EtkDataAccessException {
		logger.debug("Get KPIDetails by a list of ticketno: {}.", ticketNOs);
		return etkDao.GetTicketsKpiDetails(ticketNOs);
	}

	
	/**
	 * Update ticke process state by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @param interfaceNM the interface NM
	 * @param ticketProcesState the ticket proces state
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void UpdateTickeProcessStateByTicketID(final Integer ticketID, final EnumInterface interfaceNM, final EnumProcessState ticketProcesState) throws EtkDataAccessException {
		etkDao.UpdateTickeProcessStateByTicketID(ticketID, interfaceNM, ticketProcesState);
	}

	/**
	 * Record issuance event.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void RecordIssuanceEvent(final String ticketNO) throws EtkDataAccessException{
		logger.debug("Record issuance event, {}", ticketNO);
		etkDao.RecordIssuanceEvent(ticketNO);
	}
	
	/**
	 * Record query event.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception.
	 */
	@Override
	public void RecordQueryEvent(final String ticketNO) throws EtkDataAccessException{
		logger.debug("Record query event, {}", ticketNO);
		etkDao.RecordQueryEvent(ticketNO);
	}
	
	/**
	 * Record payment event.
	 *
	 * @param ticketNO the ticket NO
	 * @param paymentAmount the payment amount
	 * @param ticketType the ticket type
	 * @param cardType the card type
	 * @param receiptNumber the receipt number
	 * @param txn_number the txn number
	 * @throws DataIntegrityViolationException the data integrity violation exception
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void RecordPaymentEvent(String ticketNO, Double paymentAmount, EnumTicketType ticketType, String cardType,
			String receiptNumber, String txn_number) throws DataIntegrityViolationException, EtkDataAccessException {
		logger.debug("Record payment event, {}", ticketNO);
		etkDao.RecordPaymentEvent(ticketNO, paymentAmount, ticketType, cardType, receiptNumber, txn_number);
	}
	
	
	/**
	 * Update interface.
	 *
	 * @param interfaceNM the interface NM
	 * @param interfaceStateCD the interface state CD
	 * @param updUserID the upd user ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void UpdateInterface(final EnumInterface interfaceNM, final EnumInterfaceState interfaceStateCD, final String updUserID) throws EtkDataAccessException {
		logger.debug("Update interface, {}", interfaceNM);
		etkDao.UpdateInterface(interfaceNM, interfaceStateCD, updUserID);
	}
	
	/**
	 * Delete old tickets.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void DeleteOldTickets() throws EtkDataAccessException {
		logger.debug("Delete old tickets");
		etkDao.DeleteOldTickets();
	}
	
	/**
	 * Delete errors.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void DeleteErrors() throws EtkDataAccessException {
		logger.debug("Delete errors");
		etkDao.DeleteErrors();
	}
	
	/**
	 * Delete smoketest ticket.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void DeleteSmoketestTicket() throws EtkDataAccessException {
		logger.debug("Delete smoke test ticket");
		etkDao.DeleteOldTickets();
	}
	
	/**
	 * Gets the interface states.
	 *
	 * @param getTicketIssuanceState the get ticket issuance state
	 * @return the interface states
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public EtkInterfaceStateTransition getInterfaceStates(final boolean getTicketIssuanceState) throws EtkDataAccessException {
		logger.debug("Get interface states, getTicketIssuanceState: {}", getTicketIssuanceState);
		EtkInterfaceStateTransition returnVal = new EtkInterfaceStateTransition();
		List<EtkInterfaceState> interfaceInfo = new ArrayList<EtkInterfaceState>();
		
		// If getTicketIssuanceState flag is true, return ICBC_CC interface state
		if (getTicketIssuanceState) {
			EtkInterfaceState icbcCCInfo = workerService.getInterfaceState(EnumInterface.ICBC_CC);
			interfaceInfo.add(icbcCCInfo);
			returnVal.setInterfaceInfo(interfaceInfo);
			return returnVal;
		}
		
		// get hub external facing interface state
		Iterator<Entry<EnumInterface, EnumInterface>> it = EnumInterface.getPartnerFacingInterfaces().entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<EnumInterface, EnumInterface> pair = (Map.Entry<EnumInterface, EnumInterface>)it.next();
            EtkInterfaceState info = workerService.getInterfaceState(pair.getKey());
			interfaceInfo.add(info);
        }
				
        //get ticket dispute finding interface state if it's temporary commented out from the partnerfacing interfaces.
        if (EnumInterface.getPartnerFacingInterfaces().get(EnumInterface.JSTN_DF) == null) {
			EtkInterfaceState dfInfo = workerService.getInterfaceState(EnumInterface.JSTN_DF);
			interfaceInfo.add(dfInfo);
        }
        
        // get hub internal interface (VPH_FD) state
 		EtkInterfaceState fileDelInfo = workerService.getInterfaceState(EnumInterface.VPH_FD);
 		interfaceInfo.add(fileDelInfo);
 		returnVal.setInterfaceInfo(interfaceInfo);
 		
		return returnVal;
	}
	
	/**
	 * Issuance interface state transition.
	 *
	 * @param updUserID the upd user ID
	 * @param newState the new state
	 * @param mode the mode
	 * @param callbackURL the callback URL
	 * @param emailBody the email body
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void issuanceInterfaceStateTransitionWithEmailBody (final String updUserID, EnumInterfaceState newState, EnumTransitionMode mode, String callbackURL, String emailBody, boolean isPosponed) throws EtkDataAccessException {
		logger.debug("{} transition, updUserID: {}, new state:{}", mode.getCodeDescription(), updUserID, newState.getCodeValue());
		issuanceInterfaceStateTransitionHelper(updUserID, newState, mode, callbackURL, emailBody, isPosponed);
	}
	
	/**
	 * Issuance interface state transition.
	 *
	 * @param updUserID the upd user ID
	 * @param newState the new state
	 * @param mode the mode
	 * @param callbackURL the callback URL
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void issuanceInterfaceStateTransition (final String updUserID, EnumInterfaceState newState, EnumTransitionMode mode, String callbackURL) throws EtkDataAccessException {
		logger.debug("{} transition, updUserID: {}, new state:{}", mode.getCodeDescription(), updUserID, newState.getCodeValue());
		issuanceInterfaceStateTransitionHelper(updUserID, newState, mode, callbackURL, null, false);
	}
	
	/**
	 * Check file existance.
	 *
	 * @param fileName the file name
	 * @return true, if successful
	 */
	@Override
	public boolean checkFileExistance(final String fileName) {
		if (StringUtils.isBlank(fileName)) {
			return false;
		} else {
			SftpAPIOutput fileExistence = workerService.fileCheck_sync(fileName);
			return fileExistence == null ? false : fileExistence.isResult();
		}
	}
	
	/**
	 * Check pause file existance.
	 *
	 * @return true, if successful
	 */
	@Override
	public boolean checkPauseFileExistance() {
		SftpAPIOutput fileExistence = workerService.fileCheck_sync(Const.FTP_PROCESSING_FOLDER + "/" + Const.FILENAME_VPH_PAUSED);
		return fileExistence == null ? false : fileExistence.isResult();
	}
	
	
	/**
	 * Check inbound files.
	 * @return the list of FTP files, main and processed folders
	 */
	@Override
	public List<FTPFileInfo> checkInboundFiles() {
		logger.debug("Check files in FTP folder");
		List<FTPFileInfo> ftpFiles = null;

		StopWatch sw = new StopWatch();
		sw.start();
		//get list of ftp files
		FtpFileList fileList = workerService.getListOfFTPFiles();
		sw.stop();
		logger.debug("Total time fetching list of ftp files: {} ms", sw.getLastTaskTimeMillis());

		sw = new StopWatch();
		sw.start();
		if (fileList != null && fileList.getFtpFileList() != null) {
			FTPFileInfo fTPFileInfo = null;
			ftpFiles = new ArrayList<FTPFileInfo>();
			List<String> fileNames_in_processingFolder = new ArrayList<String>(); // list of names of files that are in the processed folder
			HashMap<String, String> fileName_fileTimeStamp = new HashMap<String, String>();
			
			for (FtpFileInstance fi : fileList.getFtpFileList()) {
				// continue if it is not VPH_PAUSED file
				if (!Const.FILENAME_VPH_PAUSED.contentEquals(fi.getFileName())) {
					// file is in the main folder, set 'NEW' to processState
					if (!fi.getFileName().contains(Const.FTP_PROCESSING_FOLDER + "/")) {
						fTPFileInfo = new FTPFileInfo();
						fTPFileInfo.setFtpFileName(fi.getFileName());
						fTPFileInfo.setProcessingState(EnumProcessState.NEW);
						fTPFileInfo.setLastModifiedDT(fi.getLastModifiedDT());
						ftpFiles.add(fTPFileInfo);
					} else {
						// file is in the process folder, need further analysis. Add the fileName to fileNames_in_processedFolder list
						fileNames_in_processingFolder.add(ICBCUtil.getFileName(fi.getFileName()));
						fileName_fileTimeStamp.put(ICBCUtil.getFileName(fi.getFileName()), fi.getLastModifiedDT());
					}
				} 
			}
			
			// call db to get fileStats for all files in the processed folder
			if (fileNames_in_processingFolder != null && fileNames_in_processingFolder.size() > 0) {
				try {
					List<FTPFileInfo> fileStats = this.GetEvtFilesStats(StringUtils.join(fileNames_in_processingFolder, ","));
					
					if (fileStats != null) {
						// add lastModifiedDT to each file in fileStats list
						for (FTPFileInfo fi : fileStats) {
							if (fi.isShowTicketFileFlag() && fileName_fileTimeStamp.containsKey(fi.getDbFileName())) {
								fi.setLastModifiedDT(fileName_fileTimeStamp.get(fi.getDbFileName()));
							}
							if (!fi.isShowTicketFileFlag()) {
								fi.setFtpFileName("");
								fi.setFtpFileNameShortName("");
							}
						}
						ftpFiles.addAll(fileStats);
					}
				} catch (EtkDataAccessException e) {
					logger.error("Exception occurred while executing GetEvtFilesStats: {}", e.getMessage());
				}	
			}
		}
		sw.stop();
		logger.debug("Total time process the list of ftp files: {} ms", sw.getLastTaskTimeMillis());
		return ftpFiles;
	}
	
	//TODO inBypassRange
	//private boolean inBypassRange(String ticketNumber) {
	//	return false;
	//}
	
	/**
	 * Re queue.
	 *
	 * @param updUserID the upd user ID
	 * @param ticketID the ticket ID
	 * @param errorID the error ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void reQueue(final String updUserID, final Integer ticketID, final String errorID) throws EtkDataAccessException {
		EtkTicket etkTicket = getEtkTicket(ticketID);
		
		if (etkTicket != null && etkTicket.getProcess_state_type_cd().isRequeuable()) {
			String requeueComments = "The ticket's processing state has been manually reset to \"QUEUED\" by " + updUserID + " as an attempt to re-process the ticket.";

			//set ticket processing state to QUEUED
			this.UpdateTickeProcessStateByTicketID(ticketID, EnumInterface.ICBC_CC, EnumProcessState.QUEUED);
			
			//create error comments
			if (StringUtils.isNotBlank(errorID)) {
				logUserComment(errorID, updUserID, requeueComments);
			}
			
			//check hub current state
			Map<String,Object> results = etkDao.RetrieveInterface(EnumInterface.ICBC_CC.getCodeValue());
			EnumInterfaceState state = (EnumInterfaceState) results.get(Const.KEY_INTEFACE_STATE_CD);
			if (EnumInterfaceState.RUNNING.equals(state)) {
				workerService.rePublishHelper(etkTicket);
			}
		}
	}
	
	/**
	 * Issuance interface state transition helper.
	 *
	 * @param updUserID the upd user ID
	 * @param newState the new state
	 * @param mode the mode
	 * @param callbackURL the callback URL
	 * @param emailBody the email body
	 * @param isPostponed
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	private List<Object> issuanceInterfaceStateTransitionHelper(final String updUserID, EnumInterfaceState newState, final EnumTransitionMode mode, String callbackURL, String emailBody, boolean isPostponed)
			throws EtkDataAccessException {
		logger.info("User: {} attempt to {} change the hub state to {}.", updUserID, mode.getCodeDescription(), newState.getCodeValue());
		//init value:
		EnumInterfaceState state;
		String errorMsg = null;

		//pause/resume only applies to ICBC_CC interface.
		EnumInterface ICBC_CC = EnumInterface.ICBC_CC;
		EtkInterfaceState currentState = workerService.getInterfaceState(ICBC_CC);

		// state transition
		// if new state is either running or resuming, get QUEUED records
		List<EtkTicket> queuedTickets = null;
		if (EnumInterfaceState.RUNNING.equals(newState) || EnumInterfaceState.RESUMING.equals(newState)) {
			ArrayList<EnumProcessState> queued = new ArrayList<EnumProcessState>();
			queued.add(EnumProcessState.QUEUED);
			queuedTickets = etkDao.GetTicketsByEvtstates(queued);
			if (queuedTickets == null || (queuedTickets != null && queuedTickets.size() == 0)) {
				newState = EnumInterfaceState.RUNNING;
			}
		}

		// check isTransitionable
		TransitionConfig config = new TransitionConfig(updUserID, currentState.getInterfaceState(), newState, false, false, mode, "Interface " + "\"" + EnumInterface.ICBC_CC.getCodeDescription() + "\"", "");
		PauseResumeUtil.isTransitionable(config, isPostponed);

		// if canTransition, update ICBC_CC interface
		if (config.isCanTransition()) {
			etkDao.UpdateInterface(ICBC_CC, newState, updUserID);
			state = currentState.getInterfaceState();

			//check VPH_PAUSED file
			logger.debug("Invoke a sync method webService.fileCheck to check VPH_PAUSED file");
			SftpAPIOutput pauseFileExist = workerService.fileCheck_sync(Const.FTP_PROCESSING_FOLDER + "/" + Const.FILENAME_VPH_PAUSED);
			if (pauseFileExist != null && pauseFileExist.isResult()) {
				logger.debug("VPH_PAUSED file found, attempt to delete VPH_PAUSED file");
				workerService.fileDelete(Const.FTP_PROCESSING_FOLDER + "/" + Const.FILENAME_VPH_PAUSED);
				// TODO Exception not raised, what do we do?
			} else {
				logger.debug("VPH_PAUSED file not found");
			}

		} else {
			state = currentState.getInterfaceState();
			if (state.equals(newState)){
				config.setCanEmail(false);
			} else {
				errorMsg = "Cannot transition the state of the interface [" + ICBC_CC + "] from " + state + " to " + newState + " in " + mode + " mode.";
			
				// force the email notification to be sent out.
				config.setCanEmail(true);
				config.setEmailBody(errorMsg);
			}
		}
		// Only send email when callbackURL is blank and isCanEmail is true
		if (StringUtils.isAllBlank(callbackURL) && config.isCanEmail()) {
			String final_emailBody = config.getEmailBody();
			if (StringUtils.isNotEmpty(emailBody)) {
				final_emailBody = final_emailBody + "\n\n" + emailBody;					
			}
			emailService.sendSimpleMessage(config.getEmailSubject(), final_emailBody);
		}

		List<Object> retObj = new ArrayList<Object>();
		retObj.add(state);
		retObj.add(errorMsg);
		return retObj;
	}
	
	/**
	 * Log user access .
	 *
	 * @param ticketID the ticket ID
	 * @param accessingUserID the user ID
	 * @param auditEventType the event type
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	
	@Override
	public Integer logUserTicketAccess(Integer ticketID, String accessingUserID, EnumAuditEventType auditEventType) throws EtkDataAccessException {
		logger.debug("Log user ticket access, ticketNO: {}, accessingUserID: {}, auditEventType: {}", ticketID, accessingUserID, auditEventType == null ? null : auditEventType.getCodeValue());
		return etkDao.logUserTicketAccess(ticketID, accessingUserID, auditEventType);
	}

	/**
	 * Query FTP files.
	 *
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<FTPFileInfo> queryFTPFiles() throws EtkDataAccessException {
		List<FTPFileInfo> files = checkInboundFiles();
		StopWatch sw = new StopWatch();
		sw.start();
		List<FTPFileRelatedError> ftpFileErrors = null;
		FTPFileRelatedError fTPFileRelatedError = null;
		if (files != null && files.size() > 0) {
			// Concatenate the ftp file names (deliminated by comma)
			List<String> ticketIDs = new ArrayList<String>();
			for (FTPFileInfo fl : files) {
				// only concat the name of the files that's not new
				if (!EnumProcessState.NEW.equals(fl.getProcessingState()) && fl.getTicketID() != null) {
					ticketIDs.add(fl.getTicketID().toString());
				}
			}
			
			// call db to get list of errors associated with the ftp files
			HashMap<Integer, List<EtkError>> errorMap = null;
			if (ticketIDs != null && ticketIDs.size() > 0) {
				try {
					errorMap = GetErrorsByTicketIDs(StringUtils.join(ticketIDs, ","));
				} catch (EtkDataAccessException e) {
					logger.error("Exception occurred while getting errors by ticketIDs: {}", e.toString() + "; " + e.getMessage());
				}	
			}
			
			for (FTPFileInfo fl : files) {
				//reset it to false
				ftpFileErrors = null;
				if (fl.getTicketID() != null) {
					List<EtkError> errors = errorMap == null ? null : (List<EtkError>) errorMap.get(fl.getTicketID());
					if (errors != null && errors.size() > 0) {
						ftpFileErrors = new ArrayList<FTPFileRelatedError>();
						for (EtkError etkError : errors) {
							fTPFileRelatedError = new FTPFileRelatedError();
							fTPFileRelatedError.setCreatedDateTime(etkError.getCreateDT());
							fTPFileRelatedError.setDetails(etkError.getErrorDescription());
							fTPFileRelatedError.setId(etkError.getErrorID());
							fTPFileRelatedError.setErrorCode(etkError.getErrorCode());
							// add to ftpFileErrors
							ftpFileErrors.add(fTPFileRelatedError);
						}
						Collections.sort(ftpFileErrors);
					}
				}
				fl.setErrors(ftpFileErrors);
				FileDeletable fileDeletable = new FileDeletable();
				PauseResumeUtil.isDeletable(fileDeletable, fl);
				fl.setCanReQueue(fileDeletable.isCanReQueue());
				fl.setDeletable(fileDeletable.isDeletable());
				fl.setIsDeletableRule(fileDeletable.getIsDeletableRule());
				fl.setReQueueRule(fileDeletable.getReQueueRule());
			}
		}
		sw.stop();
		logger.debug("Total time of last step processing gathering file stats: {} ms", sw.getLastTaskTimeMillis());
		return files;
	}

	/**
	 * Gets the schedule jobs from DB.
	 *
	 * @param taskID the task ID
	 * @return the schedule jobs from DB
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<TaskDetails> getScheduleJobsFromDB(String taskID) throws EtkDataAccessException {
		logger.debug("Get scheduled jobs from DB");
		return etkDao.getScheduleJobsFromDB(taskID);
	}

	/**
	 * Save scheduled job.
	 *
	 * @param taskID the task ID
	 * @param nextRun the next run
	 * @param nextState the next state
	 * @param updUserID the upd user ID
	 * @param serviceName the service name
	 * @param comments the comments
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void saveScheduledJob(String taskID, String nextRun, String partnerFacingInterface, String nextState, String updUserID, String serviceName, String comments) throws EtkDataAccessException {
		logger.debug("Save scheduled job to DB");
		etkDao.saveScheduledJob(taskID, nextRun, partnerFacingInterface, nextState, updUserID, serviceName, comments);
	}

	/**
	 * Removes the scheduled job.
	 *
	 * @param taskID the task ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void removeScheduledJob(String taskID) throws EtkDataAccessException {
		logger.debug("Delete a scheduled task, taskID: {}", taskID);
		etkDao.removeScheduledJob(taskID);
	}

	/**
	 * Save issuance recon record.
	 *
	 * @param primeIssuanceRecon the prime issuance recon
	 * @return the string
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public String saveIssuanceReconRecord(List<PrimeIssuanceReconRecord> primeIssuanceRecon) throws EtkDataAccessException {
		logger.debug("Insert a prime issuance recon record");
		return etkDao.saveIssuanceReconRecord(primeIssuanceRecon);
	}

	/**
	 * Validate issuance recon record.
	 *
	 * @param reconFileName the recon file name
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<PrimeIssuanceReconRecord> validateIssuanceReconRecord(String reconFileName) throws EtkDataAccessException {
		logger.debug("Validate the issuance recon reconds in this recon file: {}", reconFileName);
		return etkDao.validateIssuanceReconRecord(reconFileName);
		
	}

	/**
	 * Gets the active event notification targets.
	 *
	 * @return the active event notification targets
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<EventNotificationTarget> getActiveEventNotificationTargets() throws EtkDataAccessException {
		return etkDao.getActiveEventNotificationTargets();
	}

	/**
	 * Update event notification target.
	 *
	 * @param eventNotificationTarget the event notification target
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void updateEventNotificationTarget(EventNotificationTarget eventNotificationTarget) throws EtkDataAccessException {
		etkDao.updateEventNotificationTarget(eventNotificationTarget);
	}

	/**
	 * Record dispute findings.
	 *
	 * @param etkDisputeFindings the etk dispute findings
	 * @return the string
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public String recordDisputeFindings(List<EtkDisputeFinding> etkDisputeFindings) throws EtkDataAccessException {
		return etkDao.recordDisputeFindings(etkDisputeFindings);
	}

	/**
	 * Retrieve events for event publish.
	 *
	 * @param startEventID the start event ID
	 * @param endEventID the end event ID
	 * @param startEventDate the start event date
	 * @param endEventDate the end event date
	 * @param pageSize the page size
	 * @param eventTypes the event types
	 * @param issuingAgencyCodeList the issuing agency code list
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<Event> retrieveEventsForEventPublish(Integer startEventID, Integer endEventID, String startEventDate,
			String endEventDate, Integer pageSize, String eventTypes, String issuingAgencyCodeList)
			throws EtkDataAccessException {
		return etkDao.retrieveEvents(startEventID, endEventID, startEventDate, endEventDate, EvtEventingUtil.getEventPublishPageSize(pageSize), eventTypes, issuingAgencyCodeList);
	}
	
	/**
	 * Retrieve events for kafka.
	 *
	 * @param startEventID the start event ID
	 * @param endEventID the end event ID
	 * @param startEventDate the start event date
	 * @param endEventDate the end event date
	 * @param pageSize the page size
	 * @param eventTypes the event types
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<KafkaEvent> retrieveEventsForKafka(Integer startEventID, Integer endEventID, String startEventDate,
			String endEventDate, Integer pageSize, String eventTypes)
			throws EtkDataAccessException {
		return etkDao.retrieveEvents_kafka(startEventID, endEventID, startEventDate, endEventDate, KafkaEvtEventingUtil.getEventPublishPageSize(pageSize), eventTypes);
	}

	/**
	 * Retrieve events for event lookup.
	 *
	 * @param startEventID the start event ID
	 * @param endEventID the end event ID
	 * @param startEventDate the start event date
	 * @param endEventDate the end event date
	 * @param pageSize the page size
	 * @param eventTypes the event types
	 * @param issuingAgencyCodeList the issuing agency code list
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<Event> retrieveEventsForEventLookup(Integer startEventID, Integer endEventID, String startEventDate,
			String endEventDate, Integer pageSize, String eventTypes, String issuingAgencyCodeList)
			throws EtkDataAccessException {
		return etkDao.retrieveEvents(startEventID, endEventID, startEventDate, endEventDate, EvtEventingUtil.getEventLookupPageSize(pageSize), eventTypes, issuingAgencyCodeList);
	}

	/**
	 * Gets the evt files stats.
	 *
	 * @param fileNames a list of comma deliminated file names
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<FTPFileInfo> GetEvtFilesStats(String fileNames) throws EtkDataAccessException {
		return etkDao.GetEvtFilesStats(fileNames);
	}

	/**
	 * Delete ticket by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void deleteTicketByTicketNO(String ticketNO) throws EtkDataAccessException {
		etkDao.deleteTicketByTicketNO(ticketNO);
	}
	
	/**
	 * Gets the audit records by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @return the audit records by ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<AuditRecord> getAuditRecordsByTicketNO(final String ticketNO) throws EtkDataAccessException {
		return etkDao.getAuditRecordsByTicketNO(ticketNO);
	}

	/**
	 * Update agency latest mre date.
	 *
	 * @param agencyCD the agency CD
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void updateAgencyLatestMreDate(String agencyCD) throws EtkDataAccessException {
		etkDao.updateAgencyLatestMreDate(agencyCD);
	}

	/**
	 * Gets the DB version.
	 *
	 * @return the DB version
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public String getDBVersion() throws EtkDataAccessException {
		return etkDao.getDBVersion();
	}
	
	/**
	 * Gets the associated errors.
	 *
	 * @param errorID the error ID
	 * @return the associated errors
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<EtkError> getAssociatedErrorsUnresolved(final String errorID) throws EtkDataAccessException {
		return etkDao.getAssociatedErrorsUnresolved(errorID);
	}

	/**
	 * Gets the tickets sent today.
	 *
	 * @return the tickets sent today
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<EtkTicket> getTicketsReceivedToday() throws EtkDataAccessException {
		return etkDao.getTicketsReceivedToday();
	}
	
	/**
	 * Gets the tickets not sent.
	 *
	 * @return the tickets not sent
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<EtkStatisticsTicketErrorDetail> getTicketsNotSent() throws EtkDataAccessException {
		return etkDao.getTicketsNotSent();
	}
	
	/**
	 * Update errors by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @param errorStatus the error status
	 * @param updUserID the upd user ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void updateErrorsByTicketID(Integer ticketID, String errorStatus, String updUserID) throws EtkDataAccessException {
		etkDao.updateErrorsByTicketID(ticketID, errorStatus, updUserID);
	}
	
	/**
	 * Gets the next queued dispute record.
	 *
	 * @param dataTypeCode the data type code, values: DISPUTE or DISPUTE_STATUS_UPDATE
	 * @return the etk queued dispute record
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public EtkQueuedDisputeRecord GetNextQueuedDisputeRecord(String dataTypeCode) throws EtkDataAccessException {
		return etkDao.GetNextQueuedDisputeRecord(dataTypeCode);
	}
	
	/**
	 * Update dispute record.
	 *
	 * @param processID the process ID
	 * @param newProcessState the new process state
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void updateDisputeRecord(int processID, EnumProcessState newProcessState) throws EtkDataAccessException {
		etkDao.updateDisputeRecord(processID, newProcessState);
	}

	/**
	 * Creates the ticket KPI details and event.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public Map<String, Object> createTicketKPIDetailsEvent(Integer ticketID) throws EtkDataAccessException {
		return etkDao.createTicketKPIDetailsEvent(ticketID);
	}
	
	/**
	 * Gets the active agencies.
	 *
	 * @return the active agencies
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<EtkAgencyCodeText> getActiveAgencies() throws EtkDataAccessException {
		return etkDao.getActiveAgencies();
	}
	
	/**
	 * Delete ticket by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void deleteTicketByTicketID(final Integer ticketID) throws EtkDataAccessException {
		etkDao.deleteTicketByTicketID(ticketID);
	}
	
	/**
	 * Delete dup tickets by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void deleteDupTicketsByTicketID(final Integer ticketID) throws EtkDataAccessException {
		etkDao.deleteDupTicketsByTicketID(ticketID);
	}
	
	/**
	 * Re publish EVT from DB.
	 */
	@Override
	public void signalTicketProcess() {
		// Signal PrimeAdapter to process all QUEUED and NEW eVTs
		String signalURL = ApplicationProperties.PRIME_ADAPTER_TICKET_REPROCESS_SERVICE_URI + PathConst.PATH_PROCESS_NEW_QUEUED_TICKETS;
		logger.debug("Signal Primeadapter to process QUEUED and NEW tickets.");

		try {
	    	restService.restfulSave(signalURL, null, HttpMethod.GET, MediaType.APPLICATION_JSON);
		} catch (Exception e) {
			String errorDescrip = "Failed signal Primeadapter to process QUEUED and NEW tickets, error message: " + e.toString() + "; " + e.getMessage();
			logger.error(errorDescrip);
    	}
	}

	/**
	 * Gets the known users.
	 *
	 * @return the known users
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public List<String> getKnownUsers() throws EtkDataAccessException {
		return etkDao.getKnownUsers();
	}

	/**
	 * Validate ticket.
	 *
	 * @param sourceXML the source XML
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public Map<String, Object> validateTicket(final String sourceXML) throws EtkDataAccessException {
		return etkDao.validateTicket(sourceXML);
	}
	
	@Override
	public void SetTickeStatusToSent(final Integer ticketID, final EnumInterface interfaceNM) throws EtkDataAccessException {
		etkDao.SetTickeStatusToSent(ticketID, interfaceNM);
	}
	
	@Override
	public Map<String, Object> CheckForStuckTickets() throws EtkDataAccessException {
		return etkDao.CheckForStuckTickets();
	}
	
	@Override
	public void disputeInterfaceStateTransition (final EnumInterface theInterface, final String updUserID, EnumInterfaceState newState, EnumTransitionMode mode, boolean skipEmail) throws EtkDataAccessException {
		logger.debug("Interface - {}, {} transition, updUserID: {}, new state:{}", theInterface.getCodeValue(), mode.getCodeDescription(), updUserID, newState.getCodeValue());
		disputeInterfaceStateTransitionHelper(theInterface, updUserID, newState, mode, skipEmail);
	}
	
	/**
	 * Interface state transition helper.
	 *
	 * @param theInterface the the interface
	 * @param updUserID the upd user ID
	 * @param newState the new state
	 * @param mode the mode
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	private void disputeInterfaceStateTransitionHelper(final EnumInterface theInterface, final String updUserID, EnumInterfaceState newState, final EnumTransitionMode mode, final boolean skipEmail)
			throws EtkDataAccessException {
		logger.info("User: {} attempt to {} change the {} state to {}.", updUserID, mode.getCodeDescription(), theInterface.getCodeValue(), newState.getCodeValue());
		//init value:
		EnumInterfaceState state;
		String errorMsg = null;

		//get current interface state.
		EtkInterfaceState currentState = workerService.getInterfaceState(theInterface);

		// if new state is either running or resuming, get QUEUED records
		EnumEventType eventType = EnumEventType.DISPUTE_STATUS_UPDATE;
		if (EnumInterface.JSTN_TD.equals(theInterface)) {
			eventType = EnumEventType.DISPUTE;
		}
		if (EnumInterfaceState.RUNNING.equals(newState) || EnumInterfaceState.RESUMING.equals(newState)) {
			List<EtkQueuedDisputeRecord> record = this.GetDisputeRecords(eventType.getCodeValue(), EnumProcessState.QUEUED.getCodeValue());
			// there is no more QUEUED records, set the newState to be RUNNING.
			if (record == null || record.size() == 0) {
				newState = EnumInterfaceState.RUNNING;
			}
		}

		// check isTransitionable
		TransitionConfig config = new TransitionConfig(updUserID, currentState.getInterfaceState(), newState, false, false, mode, "Interface " + "\"" + theInterface.getCodeDescription() + "\"", "");
		PauseResumeUtil.isTransitionable(config, false);

		// if canTransition, update the interface
		if (config.isCanTransition()) {
			etkDao.UpdateInterface(theInterface, newState, updUserID);
		} else {
			state = currentState.getInterfaceState();
			if (state.equals(newState)){
				config.setCanEmail(false);
			} else {
				errorMsg = "Cannot transition the state of the interface [" + theInterface.getCodeValue() + "] from " + state + " to " + newState + " in " + mode + " mode.";
			
				// force the email notification to be sent out.
				config.setCanEmail(true);
				config.setEmailBody(errorMsg);
			}
		}
		// Only send email when isCanEmail is true and skipEmail is false
		if (config.isCanEmail() && !skipEmail) {
			String final_emailBody = config.getEmailBody();
			emailService.sendSimpleMessage(config.getEmailSubject(), final_emailBody);
		}
	}
	
	@Override
	public List<EtkQueuedDisputeRecord> GetDisputeRecords(String dataTypeCode, String processStateTypeCode) throws EtkDataAccessException {
		return etkDao.GetDisputeRecords(dataTypeCode, processStateTypeCode);
	}
	
	@Override
	public Map<EnumTicketType, BankingFeePerTicketType> bankingFeeCalculation(String startDate, String endDate) throws EtkDataAccessException {
		return etkDao.bankingFeeCalculation(startDate, endDate);
	}
}
