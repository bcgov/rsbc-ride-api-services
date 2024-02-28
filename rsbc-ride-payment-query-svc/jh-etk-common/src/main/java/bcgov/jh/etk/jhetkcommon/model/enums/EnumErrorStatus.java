package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumErrorStatus.
 * @author HLiang
 */
public enum EnumErrorStatus {
	
	/** The new. */
	NEW("NEW", "New error"),
	
	/** The viewed. */
	VIEWED("VIEWED", "Error viewed"),
	
	/** The solved. */
	RSVD("RSVD", "Error resolved"),
	
	/** The assigned. */
	ASND("ASND", "Error assigned"),
	
	/** The cancel. */
	CNCD("CNCD", "Error cancelled"),
	
	/** The closed. */
	CLSD("CLSD", "Error closed");
	
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumErrorStatus> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumErrorStatus(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumErrorStatus>();
		for (EnumErrorStatus v : EnumErrorStatus.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumErrorStatus getEnumfromCodeValue(String codeValue) {
		return mapCode.get(codeValue);
	}

	/**
	 * Gets the code description.
	 *
	 * @return the code description
	 */
	public String getCodeDescription() {
		return codeDescription;
	}

	/**
	 * Gets the code value.
	 *
	 * @return the code value
	 */
	public String getCodeValue() {
		return codeValue;
	}
}
