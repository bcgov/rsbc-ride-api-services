package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumIssuanceReconTicketStatus.
 */
public enum EnumIssuanceReconTicketStatus {
		
	/** The a. */
	A("A", "cancelled"),
	
	/** The c. */
	C("C", "Complied"),
	
	/** The i. */
	I("I", "Issued"),
	
	/** The n. */
	N("N", "Notice"),
	
	/** The p. */
	P("P", "Paid"),
	
	/** The s. */
	S("S", "Summons"),
	
	/** The v. */
	V("V", "Void"),
	
	/** The w. */
	W("W", "Warning");
	
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumIssuanceReconTicketStatus> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumIssuanceReconTicketStatus(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumIssuanceReconTicketStatus>();
		for (EnumIssuanceReconTicketStatus v : EnumIssuanceReconTicketStatus.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumIssuanceReconTicketStatus getEnumfromCodeValue(String codeValue) {
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
