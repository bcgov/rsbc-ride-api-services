package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumEventType.
 */
public enum EnumEventType {

	VT_QUERY ("VT_QUERY", "VT Query"),
	
	/** The query. */
	QUERY ("QUERY", "Ticket Query"),
	
	/** The payment. */
	PAYMENT ("PAYMENT", "Ticket Payment"),
	
	/** The dispute. */
	DISPUTE ("DISPUTE", "Ticket Dispute"),
	
	/** The dispute update. */
	DISPUTE_STATUS_UPDATE ("DISPUTE_STATUS_UPDATE", "Ticket Status Dispute"),
	
	/** The issuance. */
	ISSUANCE ("ISSUANCE", "Ticket Issuance"),
	
	/** The dispute finding. */
	DISPUTE_FINDING ("DISPUTE_FINDING", "Dispute finding"),
	
	/** The sent. */
	SENT ("SENT", "Ticket Sent"),
	
	/** The purged. */
	PURGED ("PURGED", "Ticket Purged"),
	
	/** The all. */
	ALL ("all", "All types");

	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumEventType> mapCode;
	
	/**
	 * Instantiates a new enum event type.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumEventType(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}

	static {
		mapCode = new HashMap<String, EnumEventType>();
		for (EnumEventType v : EnumEventType.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumEventType getEnumfromCodeValue(String codeValue) {
		return mapCode.get(codeValue);
	}
	
	/**
	 * Gets the code value.
	 *
	 * @return the code value
	 */
	public String getCodeValue() {
		return codeValue;
	}

	/**
	 * Gets the code description.
	 *
	 * @return the code description
	 */
	public String getCodeDescription() {
		return codeDescription;
	}	
	
}
