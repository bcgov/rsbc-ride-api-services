/*
 * 
 */
package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * The Enum EnumTicketType.
 */
@Getter
public enum EnumTicketType {
	
	/** The e. */
	E ("E", "eVT"),

	/** The s. */
	S ("S", "ISC"),
	
	/** The p. */
	P ("P", "pVT");
	
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumTicketType> mapCode;
	
	/**
	 * Instantiates a new enum event type.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumTicketType(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}

	static {
		mapCode = new HashMap<String, EnumTicketType>();
		for (EnumTicketType v : EnumTicketType.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumTicketType getEnumfromCodeValue(String codeValue) {
		EnumTicketType ticketType = mapCode.get(codeValue);
		if (ticketType == null) {
			ticketType = EnumTicketType.P;
		}
		return ticketType;
	}
	
}
