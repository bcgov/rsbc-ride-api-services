package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumDisputeActionCode.
 */
public enum EnumDisputeActionCode {
	
	/** Paid. */
	P("P", "PAID"),
	
	/** CANCELLED. */
	C("C", "CANCELLED"),
	
	/**  Disputed. */
	D("D", "DISPUTE");
	
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumDisputeActionCode> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumDisputeActionCode(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumDisputeActionCode>();
		for (EnumDisputeActionCode v : EnumDisputeActionCode.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumDisputeActionCode getEnumfromCodeValue(String codeValue) {
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
