package bcgov.jh.etk.jhetkcommon.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ValidationResult {
	
	/** The etk error. */
	private EtkError etkError;
	
	/** The validation result. */
	private Object validationResult;
	
	/** The error details. */
	private String errorDetails;
}
