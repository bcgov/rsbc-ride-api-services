package bcgov.jh.etk.jhetkcommon.service;

import java.util.Base64;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


/**
 * The Class RestService.
 * @author HLiang
 */
@Service
public class EtkRestService {
	
	/** The rest template. 
	 *  Set required=false, to make RestTemplate bean optional
	 * */
	@Autowired(required = false)
    RestTemplate restTemplate;

	@Autowired
	private CircuitBreakerFactory circuitBreakerFactory;

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
	 * Rest exchange helper new.
	 *
	 * @param endpointURL the endpoint URL
	 * @param payload the payload
	 * @param httpMethod the http method
	 * @param username the username
	 * @param password the password
	 * @param contentType the content type
	 * @return the response entity
	 */
	private ResponseEntity<String> restExchangeHelper(final String endpointURL, final Object payload, final HttpMethod httpMethod, final String username, final String password, final MediaType contentType) {
		final HttpEntity<Object> request = getRequest(payload, username, password, contentType);

		CircuitBreaker circuitBreaker = circuitBreakerFactory.create("circuitbreaker");
		return circuitBreaker.run(() -> restTemplate.exchange(endpointURL, httpMethod, request, String.class));
	}

	private HttpEntity<Object> getRequest(Object payload, String username, String password, MediaType contentType) {
		HttpEntity<Object> request;
		if (StringUtils.isNoneBlank(username) && StringUtils.isNotBlank(password)) {
			request = getRequestWithCredential(payload, username, password, contentType);
		} else {
			request = getRequest(payload, contentType);
		}
		return request;
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
