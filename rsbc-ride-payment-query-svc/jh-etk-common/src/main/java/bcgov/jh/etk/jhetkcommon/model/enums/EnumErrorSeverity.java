package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumErrorSeverity.
 * @author HLiang
 */
public enum EnumErrorSeverity {
	
	/** The ft. */
	FT("FATAL", "Fatal error"),
	
	/** The WN. */
	WN("WARNING", "Follow up error"),
	
	/** The info. */
	IF("INFO", "None error");
	
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumErrorSeverity> mapCode;
	
	/**
	 * Instantiates a new enum error severity.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumErrorSeverity(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumErrorSeverity>();
		for (EnumErrorSeverity v : EnumErrorSeverity.values()) {
			mapCode.put(v.codeValue, v);
		}
	}

	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumErrorSeverity getEnumfromCodeValue(String codeValue) {
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
