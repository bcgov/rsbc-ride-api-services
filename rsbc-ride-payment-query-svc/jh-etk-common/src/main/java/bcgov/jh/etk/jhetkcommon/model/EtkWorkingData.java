package bcgov.jh.etk.jhetkcommon.model;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class EtkWorkingData {
	
	/** The data ID. */
	private BigInteger dataID;
	
	/** The component name. */
	private String componentName;
	
	/** The data name. */
	private String dataName;
	
	/** The data value. */
	private String dataValue;
	
	/** The lock number. */
	private int lockNumber;
}
