package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Enum EnumProcessState.
 * @author HLiang
 */
public enum EnumProcessState {

	/** The processing. */
	@JsonProperty("PROCESSING")
	PROCESSING("PROCESSING", "Ticket in processing"),
	
	/** The sent. */
	@JsonProperty("SENT")
	SENT("SENT", "Ticket sent to ICBC"),
	
	/** The dataerror. */
	@JsonProperty("DATAERROR")
	DATAERROR("DATAERROR", "Ticket data error"),
	
	/** The bypassed. */
	@JsonProperty("BYPASSED")
	BYPASSED("BYPASSED", "Ticket bypassed"),
	
	/** The ready. */
	@JsonProperty("READY")
	READY("READY", "Ticket ready to process"),
	
	/** The queued. */
	@JsonProperty("QUEUED")
	QUEUED("QUEUED", "Ticket queued"),
	
	/** The purged. */
	@JsonProperty("PURGED")
	PURGED("PURGED", "Ticket purged"),
	
	/** The new. */
	@JsonProperty("NEW")
	NEW("NEW", "Ticket not processed"),
	
	/** The fileerror. */
	@JsonProperty("FILEERROR")
	FILEERROR("FILEERROR", "Bad eVT file"),
	
	@JsonProperty("DUPLICATEERROR")
	DUPLICATEERROR("DUPLICATEERROR", "Duplicate eVT ticket"),
	
	/** The voided. */
	@JsonProperty("VOIDED")
	VOIDED("VOIDED", "Voided");

	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumProcessState> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumProcessState(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumProcessState>();
		for (EnumProcessState v : EnumProcessState.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	public boolean isRequeuable() {
		boolean result = false;
		switch (this) {
			case DATAERROR: 
			case FILEERROR: 
					result = true;
					break;
			default:
				break;
		}
		return result;
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumProcessState getEnumfromCodeValue(String codeValue) {
		if (StringUtils.isNoneBlank(codeValue)) {
			return mapCode.get(codeValue);
		}
		return null;
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
