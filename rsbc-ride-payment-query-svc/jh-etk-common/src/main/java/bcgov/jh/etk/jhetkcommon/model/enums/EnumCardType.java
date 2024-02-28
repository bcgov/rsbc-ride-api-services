/*
 * 
 */
package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * The Enum EnumCardType.
 */
@Getter
public enum EnumCardType {
	
	/** The MasterCard. */
	MasterCard("MasterCard",  "MasterCard"),
	
	/** The Visa card. */
	Visa("Visa", "Visa"),

	/** The Amex. */
	Amex("Amex", "American Express"),
	
	/** The Visa debit. */
	VisaDebit("VisaDebit", "Visa Debit"),
	
	/** The Master card debit. */
	MasterCardDebit("MasterCardDebit", "Debit MasterCard");	
		
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumCardType> mapCode;
	
	private static final Map<String, EnumCardType> mapCodeDescription;
	
	/**
	 * Instantiates a new enum event type.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumCardType(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}

	static {
		mapCode = new HashMap<String, EnumCardType>();
		for (EnumCardType v : EnumCardType.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	static {
		mapCodeDescription = new HashMap<String, EnumCardType>();
		for (EnumCardType v : EnumCardType.values()) {
			mapCodeDescription.put(v.codeDescription, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumCardType getEnumfromCodeValue(String codeValue) {
		EnumCardType ticketType = mapCode.get(codeValue);
		if (ticketType == null) {
			ticketType = EnumCardType.MasterCard;
		}
		return ticketType;
	}
	
	/**
	 * Gets the enumfrom code description.
	 *
	 * @param codeDescription the code description
	 * @return the enumfrom code description
	 */
	public static EnumCardType getEnumfromCodeDescription(String codeDescription) {
		EnumCardType ticketType = mapCodeDescription.get(codeDescription);
		if (ticketType == null) {
			ticketType = EnumCardType.MasterCard;
		}
		return ticketType;
	}
	
}
