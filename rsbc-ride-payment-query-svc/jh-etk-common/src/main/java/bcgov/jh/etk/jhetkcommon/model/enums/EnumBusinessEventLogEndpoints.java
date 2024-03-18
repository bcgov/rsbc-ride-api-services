package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumSankeyDSourceTarget.
 */
public enum EnumBusinessEventLogEndpoints {

	/** The Prime corp. */
	PRIMECORP("PRIMECorp", "PRIMECorp"),
	
	/** The icbc. */
	ICBC("ICBC", "ICBC"),
	
	/** The Pay BC. */
	PAYBC("PayBC", "PayBC"),
	
	/** The tismit. */
	COURTS("Courts", "Courts"),
	
	/** The pa. */
	PRIMEADAPTER("jh-etk-primeadapter", "primeadapter"),
	
	/** The is. */
	ISSUANCESVC("jh-etk-issuancesvc", "issuancesvc"),
	
	/** The ia. */
	ICBCADAPTER("jh-etk-icbcadapter", "icbcadapter"),
	
	/** The ra. */
	RCMPADAPTER("jh-etk-rcmpadapter", "rcmpadapter"),
	
	/** The ds. */
	DISPUTESVC("jh-etk-disputesvc", "disputesvc"),
	
	/** The es. */
	EVENTSVC("jh-etk-eventsvc", "eventsvc"),
	
	/** The ja. */
	JIADAPTER("jh-etk-jiadapter", "jiadapter"),
	
	/** The PAYMENTSVC. */
	PAYMENTSVC("jh-etk-paymentsvc", "paymentsvc"),
	
	/** The paybcadapter. */
	PAYBCADAPTER("jh-etk-paybcadapter", "paybcadapter");
	

	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumBusinessEventLogEndpoints> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumBusinessEventLogEndpoints(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumBusinessEventLogEndpoints>();
		for (EnumBusinessEventLogEndpoints v : EnumBusinessEventLogEndpoints.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumBusinessEventLogEndpoints getEnumfromCodeValue(String codeValue) {
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
