package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumErrorType.
 * @author HLiang
 */
public enum EnumErrorCategory {

	/** The connectivity error. */
	CONN("CONN", "Connectivity error"),
	
	/** The othr. */
	OTHER("OTHER", "Other error"),
	
	/** The data. */
	DATA("DATA", "Data error");
	

	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumErrorCategory> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumErrorCategory(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumErrorCategory>();
		for (EnumErrorCategory v : EnumErrorCategory.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumErrorCategory getEnumfromCodeValue(String codeValue) {
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
