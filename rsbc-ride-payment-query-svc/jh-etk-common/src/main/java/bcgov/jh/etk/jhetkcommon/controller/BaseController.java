package bcgov.jh.etk.jhetkcommon.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import bcgov.jh.etk.jhetkcommon.model.CustomErrorResponse;

/**
 * The Class BaseController.
 */
public class BaseController {
	
	/** The Constant PATH_TICKET_DISPUTE. */
    protected static final String PATH_TICKET_DISPUTE = "/dispute/v3/ticket-dispute";
    
    /** The Constant PATH_DISPUTE_STATUS_UPDATE. */
    protected static final String PATH_DISPUTE_STATUS_UPDATE = "/dispute/v3/dispute-status-update";
    
    /** The Constant CUSTOM_HTTP_STATUS_FROM_JIADAPTER. */
    protected static final HttpStatus CUSTOM_HTTP_STATUS_FROM_JIADAPTER = HttpStatus.ACCEPTED;
    
    /** The Constant CUSTOM_HTTP_STATUS_FROM_ICBCADAPTER. */
    protected static final HttpStatus CUSTOM_HTTP_STATUS_FROM_ICBCADAPTER = HttpStatus.ACCEPTED;
    
    /** The Constant CUSTOM_HTTP_HEADER_ERROR_CODE. */
    protected static final String CUSTOM_HTTP_HEADER_ERROR_CODE = "header_error_code";
    
    /** The Constant CUSTOM_HTTP_HEADER_ERROR_SOURCE. */
    protected static final String CUSTOM_HTTP_HEADER_ERROR_SOURCE = "header_error_source";
    
    /** The Constant CUSTOM_HTTP_HEADER_STATUS_CODE. */
    protected static final String CUSTOM_HTTP_HEADER_STATUS_CODE = "header_status_code";
    
    /** The Constant CUSTOM_HTTP_HEADER_RAISE_ERROR_FLAG. */
    protected static final String CUSTOM_HTTP_HEADER_RAISE_ERROR_FLAG = "header_raise_error_flag";
	
    /**
	 * Builds the custom response.
	 *
	 * @param httpStatus the http status
	 * @param message the message
	 * @param path the path
	 * @return the response entity
	 */
	protected ResponseEntity<String> buildCustomResponse(HttpStatus httpStatus, String message, String path) {
    	HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); 
    	ResponseEntity<String> response = new ResponseEntity<>(new CustomErrorResponse(httpStatus.value(), httpStatus.getReasonPhrase(), 
    			message, path).toJSONString(), headers, httpStatus);
    	return response;
    }
	
    /**
     * Gets the request.
     *
     * @param payload the payload
     * @return the request
     */
    protected HttpEntity<Object> getRequest(final Object payload) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        if (payload != null) {
            return new HttpEntity<>(payload, headers);
        } else {
            return new HttpEntity<>(headers);
        }

    }

}
