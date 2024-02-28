package bcgov.jh.etk.jhetkcommon.util;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.dataaccess.dao.EtkDaoService;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.EtkInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.PathConst;
import bcgov.jh.etk.jhetkcommon.model.SftpAPIOutput;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.service.RestService;

@Service
public class InterfaceStateUtil {
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(InterfaceStateUtil.class);
	
	@Autowired(required = false)
	EtkDaoService etkDao;
	
	@Autowired
	RestService restService;
	
	public EtkInterfaceState getInterfaceState (EnumInterface interfaceCD) {
		Map<String, Object> results = null;
		EnumInterfaceState state = null;
		LocalDateTime lastUpdatedDTM = null;
		EtkInterfaceState returnVal = new EtkInterfaceState();
		returnVal.setInterfaceName(interfaceCD);
		try {
			results = etkDao.RetrieveInterface(interfaceCD.getCodeValue());
			
			if (results != null) {
				state = (EnumInterfaceState) results.get(Const.KEY_INTEFACE_STATE_CD);
				lastUpdatedDTM = (LocalDateTime) results.get(Const.KEY_INTEFACE_LAST_UPDATE_DTM);
				
				//if getting ICBC_CC interface info, apply the following logic:
				//db state is NOT STOPPED, check VPH_PAUSED file, if present, map FILEPAUSED state
				if (EnumInterface.ICBC_CC.equals(interfaceCD)) {
					if (!EnumInterfaceState.STOPPED.equals(state)) {
						SftpAPIOutput pauseFileExistent = checkFileExistenceHelper(Const.FTP_PROCESSING_FOLDER + "/" + Const.FILENAME_VPH_PAUSED);
						logger.debug("{} existence: {}", Const.FILENAME_VPH_PAUSED, pauseFileExistent != null && pauseFileExistent.isResult() ? true : false );
						if (pauseFileExistent != null && pauseFileExistent.isResult()) {
							state = EnumInterfaceState.FILEPAUSED;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error occurred while retrieving interfaceInfo for: {}, error: {}", interfaceCD.getCodeValue(), e.getMessage());
			if (EnumInterface.ICBC_CC.equals(interfaceCD)) {
				state = EnumInterfaceState.FILEPAUSED;
			}
		}
		
		logger.debug("[{}] state is: {}", interfaceCD.getCodeValue(), state == null ? "null" : state.getCodeValue());
		returnVal.setInterfaceState(state);
		returnVal.setLastUpdatedDateTime(lastUpdatedDTM);
		return returnVal;
	}
	
	public SftpAPIOutput checkFileExistenceHelper (final String fileName) {
		if (!StringUtil.isPropertyValueConfigured(ApplicationProperties.FTP_FILE_MANAGEMENT_URI)) {
			return null;
		}
		
		String url = ApplicationProperties.FTP_FILE_MANAGEMENT_URI + PathConst.PATH_FTP_FILE_CHECK;
		logger.debug("Check if file [{}] exists", fileName);
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);

		//build the param
        if (fileName != null) {
        	builder.queryParam("fileFullPath", fileName);
        }
        URI uri = builder.build().encode().toUri();
        
        SftpAPIOutput ftpFileExsitence = null;
		
		//check file existence
	    try {
	    	ResponseEntity<String> response = restService.restfulSave(uri.toString(), null, HttpMethod.GET, MediaType.APPLICATION_JSON);
	    	
			//success, process the response from ICBC
			if (HttpStatus.OK.equals(response.getStatusCode()) || HttpStatus.CREATED.equals(response.getStatusCode())) {
				if (StringUtils.isNotBlank(response.getBody())) {
	            	ftpFileExsitence = processResponse(response.getBody());
	            } else {
	            	logger.error("Failed checking file [{}] existence, nothing returned", fileName);
	            }
			}
		} catch (Exception e) {
			logger.error("Fail checking file [{}] existence, error message: {}", fileName, e.toString() + "; " + e.getMessage());
    	}
		return ftpFileExsitence;	
	}
	
	/**
	 * Process response.
	 *
	 * @param responseBody the response body
	 * @return the sftp API output
	 */
	public SftpAPIOutput processResponse(String responseBody) {
		SftpAPIOutput ftpFileExsitence = null;
		if (StringUtils.isNotBlank(responseBody)) {
        	
        	// convert JSON string to object
        	ObjectMapper objectMapper = new ObjectMapper();
        	try {
        		ftpFileExsitence = objectMapper.readValue(responseBody, SftpAPIOutput.class);
    		} catch (Exception e) {
    			logger.error("Failed converting string [{}] to object, error: {}", responseBody, e.toString() + "; " + e.getMessage());
    		} 
        }
		return ftpFileExsitence;
	}
}
