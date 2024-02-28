package bcgov.jh.etk.jhetkcommon.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SftpAPIOutput {

	/** The result. */
	boolean result = false;
	
	/** The error description. */
	String errorDescription;

}
