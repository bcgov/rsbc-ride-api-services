package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import lombok.Getter;
import lombok.Setter;


/**
 * The Class FTPFileRelatedError.
 * @author HLiang
 */
@Setter
@Getter
public class FTPFileRelatedError implements Comparable<FTPFileRelatedError> {
	
	/** The id. */
	private Long id;
	
	/** The created date time. */
	private LocalDateTime createdDateTime; 
	
	/** The details. */
	private String details;
	
	/** The error code. */
	private EnumErrorCode errorCode;

	@Override
	public int compareTo(FTPFileRelatedError arg0) {
		return arg0.getId().compareTo(this.id);
	}
	
}
