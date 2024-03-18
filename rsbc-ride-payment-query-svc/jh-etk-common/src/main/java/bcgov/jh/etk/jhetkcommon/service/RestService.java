package bcgov.jh.etk.jhetkcommon.service;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.exception.RestCallException;


/**
 * The Class RestService.
 * @author HLiang
 */
@Service
public class RestService {
	
	/**
     * The logger.
     */
    private static Logger logger = LoggerFactory.getLogger(RestService.class);

    
	/** The rest template. 
	 *  Set required=false, to make RestTemplate bean optional
	 * */
	@Autowired(required = false)
    RestTemplate restTemplate;
	
	/**
	 * Restful save.
	 *
	 * @param endpointURL the endpoint URL
	 * @param payload the payload
	 * @param httpMethod the http method
	 * @return the response entity
	 */
	public ResponseEntity<String> restfulSave(final String endpointURL, final Object payload, final HttpMethod httpMethod, final MediaType contentType) {
		return restExchangeHelper(endpointURL, payload, httpMethod, null, null, contentType);
	}
	
	/**
	 * Restful save.
	 *
	 * @param endpointURL the endpoint URL
	 * @param payload the payload
	 * @param httpMethod the http method
	 * @return the response entity
	 * @throws InterruptedException 
	 * @throws RestClientException the rest client exception
	 */
	public ResponseEntity<String> restfulSave(final String endpointURL, final String payload, final HttpMethod httpMethod, final MediaType contentType) {
		return restExchangeHelper(endpointURL, payload, httpMethod, null, null, contentType);
	}
	
	/**
	 * Secure restful exchange.
	 *
	 * @param endpointURL the endpoint URL
	 * @param payload the payload
	 * @param httpMethod the http method
	 * @param username the username
	 * @param password the password
	 * @return the response entity
	 */
	public ResponseEntity<String> secureRestfulExchange(final String endpointURL, final Object payload, final HttpMethod httpMethod, final String username, final String password, final MediaType contentType) {
		return restExchangeHelper(endpointURL, payload, httpMethod, username, password, contentType);
	}
	
	/**
	 * Rest exchange helper.
	 *
	 * @param endpointURL the endpoint URL
	 * @param payload the payload
	 * @param httpMethod the http method
	 * @param username the username
	 * @param password the password
	 * @return the response entity
	 */
	private ResponseEntity<String> restExchangeHelper(final String endpointURL, final Object payload, final HttpMethod httpMethod, final String username, final String password, final MediaType contentType) {
		HttpEntity<Object> request = null;
		if (StringUtils.isNoneBlank(username) && StringUtils.isNotBlank(password)) {
			request = getRequestWithCredential(payload, username, password, contentType);
		} else {
			request = getRequest(payload, contentType);
		}
		
    	ResponseEntity<String> response = null;
    	Exception exception = null;
    	for (int i = 0; i < ApplicationProperties.retryCount; i++) {
    		try{
    			response = restTemplate.exchange(endpointURL, httpMethod, request, String.class);
    			if (response != null && (response.getStatusCode() == HttpStatus.CREATED || response.getStatusCode() == HttpStatus.OK)) {
    				return response;
    			}
    		} catch (Exception e) {
    			logger.error("Exception occurred while connecting to {}, error: {}", endpointURL, e.toString() + ";" + e.getMessage());
    			exception = e;
    		} 
    		try {
				Thread.sleep(ApplicationProperties.waitInMS);
			} catch (InterruptedException e) {
				logger.error("Exception occurred during the sleep call: {}", e.getMessage());
			}
    	}
        if (exception != null) {
        	throw new RestCallException("Exception occurred while connecting to " + endpointURL + "; error: " + exception.toString() + "; " + exception.getMessage(), exception);
        }
        
        return response;
	}

	/**
     * Gets the request.
     *
     * @param payload the payload
     * @return the request
     */
    protected HttpEntity<Object> getRequest(final Object payload, final MediaType contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);

        if (payload != null) {
            return new HttpEntity<>(payload, headers);
        } else {
            return new HttpEntity<>(headers);
        }

    }
    
    /**
     * Gets the request with credential.
     *
     * @param payload the payload
     * @param username the username
     * @param password the password
     * @return the request with credential
     */
    protected HttpEntity<Object> getRequestWithCredential(final Object payload, final String username, final String password, final MediaType contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);
        headers.add("Authorization", "Basic " + getBase64Credentials(username, password));
        
        if (payload != null) {
            return new HttpEntity<>(payload, headers);
        } else {
            return new HttpEntity<>(headers);
        }

    }
    
    /**
     * Gets the base64 credentials.
     *
     * @return the base64 credentials
     */
    private String getBase64Credentials(final String username, final String password) {
        String plainCreds = username + ":" + password;
        byte[] plainCredsBytes = plainCreds.getBytes();
        String base64Creds = Base64.getEncoder().encodeToString(plainCredsBytes);
        return base64Creds;
    }
}
