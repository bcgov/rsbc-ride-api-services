package bcgov.jh.etk.jhetkcommon.model.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumInterfaceState.
 * @author HLiang
 */
public enum EnumInterfaceState {

	/** The running. */
	RUNNING("RUNNING", "Interface is running", "Running"),
	
	/** The paused. */
	PAUSED("PAUSED", "Interface is temporarily, and automatically on-hold", "Paused"),
	
	/** The resuming. */
	RESUMING("RESUMING", "Interface is transitioning from paused to running state", "Resuming"),
	
	/** The stopped. */
	STOPPED("STOPPED", "Interface is stopped, or has run into an un-recoverable error", "Stopped"),
	
	/** The filepaused. */
	FILEPAUSED("FILEPAUSED", "Interface is temporarily, and automatically on-hold", "Paused (VPH_PAUSED file exists)"),
	
	/** The failed. */
	FAILED("FAILED", "Interface failed", "Failed");
	
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The ui description. */
	private String uiDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumInterfaceState> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumInterfaceState(String codeValue, String codeDescription, String uiDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
		this.uiDescription = uiDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumInterfaceState>();
		for (EnumInterfaceState v : EnumInterfaceState.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumInterfaceState getEnumfromCodeValue(String codeValue) {
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

	/**
	 * Gets the ui description.
	 *
	 * @return the ui description
	 */
	public String getUiDescription() {
		return uiDescription;
	}
	
	
}
