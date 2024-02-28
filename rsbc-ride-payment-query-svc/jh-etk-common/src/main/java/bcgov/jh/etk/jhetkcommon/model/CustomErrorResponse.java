package bcgov.jh.etk.jhetkcommon.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class CustomErrorResponse.
 * @author HLiang
 */
public class CustomErrorResponse {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(CustomErrorResponse.class);
	
	/** The timestamp. */
	private String timestamp;
	
	/** The status. */
	private int status;
	
	/** The error. */
	private String error;
	
	/** The message. */
	private String message;
	
	/** The path. */
	private String path;
	
	/**
	 * Instantiates a new custom error response.
	 *
	 * @param status the status
	 * @param error the error
	 * @param message the message
	 * @param path the path
	 */
	public CustomErrorResponse(int status, String error, String message, String path) {
		this.timestamp = DateUtil.getCurrentDatetimeString();
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}
	
		
	/**
	 * Gets the timestamp.
	 *
	 * @return the timestamp
	 */
	public String getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp.
	 *
	 * @param timestamp the new timestamp
	 */
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	
	/**
	 * Gets the error.
	 *
	 * @return the error
	 */
	public String getError() {
		return error;
	}
	
	/**
	 * Sets the error.
	 *
	 * @param error the new error
	 */
	public void setError(String error) {
		this.error = error;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * Gets the path.
	 *
	 * @return the path
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * Sets the path.
	 *
	 * @param path the new path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * To JSON string.
	 *
	 * @return the string
	 */
	public String toJSONString() {
		String str = null;
		try {
    		ObjectMapper mapper = new ObjectMapper();
			str = mapper.writeValueAsString(this);
    	} catch (Exception e) {
    		logger.error("Fail to convert customErrorResponse object to JSON string, error message: [{}]", e.getMessage());
    	}
		
		return str;
	}
	
}
