package bcgov.jh.etk.jhetkcommon.model.task;

import java.util.HashMap;
import java.util.Map;

/**
 * The Enum EnumTaskExecState.
 * @author HLiang
 */
public enum EnumTaskExecState {

	/** The act. */
	ACT("0", "Active"),
	
	/** The run. */
	RUN("1", "Running"),
	
	/** The sus. */
	EXP("2", "expired");
	
	/** The code value. */
	private String codeValue;
	
	/** The code description. */
	private String codeDescription;
	
	/** The Constant mapCode. */
	private static final Map<String, EnumTaskExecState> mapCode;
	
	/**
	 * Instantiates a new enum error status.
	 *
	 * @param codeValue the code value
	 * @param codeDescription the code description
	 */
	private EnumTaskExecState(String codeValue, String codeDescription) {
		this.codeValue = codeValue;
		this.codeDescription = codeDescription;
	}
	
	static {
		mapCode = new HashMap<String, EnumTaskExecState>();
		for (EnumTaskExecState v : EnumTaskExecState.values()) {
			mapCode.put(v.codeValue, v);
		}
	}
	
	/**
	 * Gets the enumfrom code value.
	 *
	 * @param codeValue the code value
	 * @return the enumfrom code value
	 */
	public static EnumTaskExecState getEnumfromCodeValue(String codeValue) {
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
	 * Sets the code value.
	 *
	 * @param codeValue the new code value
	 */
	public void setCodeValue(String codeValue) {
		this.codeValue = codeValue;
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
	 * Sets the code description.
	 *
	 * @param codeDescription the new code description
	 */
	public void setCodeDescription(String codeDescription) {
		this.codeDescription = codeDescription;
	}

	
}
