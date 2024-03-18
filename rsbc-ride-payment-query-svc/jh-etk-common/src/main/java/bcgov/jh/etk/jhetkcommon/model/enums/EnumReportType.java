package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumReportType.
 */
public enum EnumReportType {
	
	/** The all. */
	ALL("all", "All"),
	
	/** The Event. */
	Event("event", "Event"),
	
	/** The ikpi. */
	IKPI("issuancekpi", "Issuance KPI"),
	
	/** The ivkpi. */
	IVKPI("issuanceviolationkpi", "Issuance violation KPI"),
	
	/** The Disput KPI. */
	DisputKPI("disputekpi", "Dispute KPI"),
	
	/** The dfkpi. */
	DFKPI("disputefindingkpi", "Dispute Finding KPI"),
	
	/** The pkpi. */
	PKPI("paymentkpi", "Payment KPI"),
	
	/** The errkpi. */
	ERRKPI("errorkpi", "Error KPI");
	
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumReportType> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumReportType(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumReportType>();
		for (EnumReportType v : EnumReportType.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumReportType getEnumfromCodeValue(String codeValue) {
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
