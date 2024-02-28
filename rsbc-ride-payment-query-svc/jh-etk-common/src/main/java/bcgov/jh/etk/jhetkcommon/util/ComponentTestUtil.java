package bcgov.jh.etk.jhetkcommon.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.ComponentTestResult;
import bcgov.jh.etk.jhetkcommon.model.PathConst;
import bcgov.jh.etk.jhetkcommon.service.RestService;
import bcgov.jh.etk.jhetkcommon.service.impl.EtkService;

@Service
public class ComponentTestUtil {
	private static final Logger logger = LoggerFactory.getLogger(ComponentTestUtil.class);
	
	@Autowired
	EtkService etkService;
	
	@Autowired
	RestService restService;
	
	public ComponentTestResult dbVersionCheck() {
		StringBuilder message = new StringBuilder();
		boolean isSuccess = true;
		
		// check DBVersion
		String curDBVersion = null;
		try {
			curDBVersion = etkService.getDBVersion();
			if (!ApplicationProperties.dbVersion.equals(curDBVersion)) {
				message.append("DB version check failed, expected: " + ApplicationProperties.dbVersion + "; current DB version: " + curDBVersion + "\n");
				isSuccess = false;
			}
		} catch (EtkDataAccessException e) {
			message.append("Failed execute getDBVersion, error details: " + e.toString() + "; " + e.getMessage() + "\n");
			logger.error(message.toString());
			isSuccess = false;
		}

		return new ComponentTestResult(isSuccess, message.toString());
	}
	
	/**
	 * Ping check.
	 *
	 * @param urlPrefix the url prefix
	 * @param componentName the component name
	 * @return the component test result
	 */
	public ComponentTestResult pingCheck(String urlPrefix, String componentName) {
		StringBuilder message = new StringBuilder();
		boolean isSuccess = true;
		
		// access ping service
		ResponseEntity<String> response;
		String serverUrl;
		HttpStatus respCode;
		String resp;
		
		serverUrl = urlPrefix + PathConst.PATH_PING_REQUEST;
		logger.trace("Access {} ping service: {}", componentName, serverUrl);
		
		response = restService.restfulSave(serverUrl, null, HttpMethod.GET, MediaType.APPLICATION_JSON);
		respCode = response.getStatusCode();
		resp = response.getBody();
		
		if (respCode != HttpStatus.OK) {
			message.append("Failed accessing " + componentName + ". " + "Http status: " + respCode + "; body: " + resp + "\n");
			isSuccess = false;
		} 
		
		return new ComponentTestResult(isSuccess, message.toString());
	}
}
