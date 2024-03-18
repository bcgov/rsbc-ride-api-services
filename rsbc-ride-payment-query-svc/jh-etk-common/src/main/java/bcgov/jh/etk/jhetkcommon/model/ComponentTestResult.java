package bcgov.jh.etk.jhetkcommon.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class ComponentTestResult.
 */
@Setter
@Getter
public class ComponentTestResult {
	
	/** The is success. */
	private boolean isSuccess;
	
	/** The message. */
	private String message;
	
	public ComponentTestResult(boolean isSuccess, String message) {
		this.isSuccess = isSuccess;
		this.message = message;
	}
}
