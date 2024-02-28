package bcgov.jh.etk.jhetkcommon.model;

import java.util.List;

/**
 * The Class VPHInterfaceStateTransition.
 * @author HLiang
 */
public class EtkInterfaceStateTransition {
	
	/** The error msg. */
	private String errorMsg;
	
	/** The transition. */
	private List<EtkInterfaceState> interfaceInfo;
	
	/**
	 * Gets the error msg.
	 *
	 * @return the error msg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}
	
	/**
	 * Sets the error msg.
	 *
	 * @param errorMsg the new error msg
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * Gets the interface info.
	 *
	 * @return the interface info
	 */
	public List<EtkInterfaceState> getInterfaceInfo() {
		return interfaceInfo;
	}

	/**
	 * Sets the interface info.
	 *
	 * @param interfaceInfo the new interface info
	 */
	public void setInterfaceInfo(List<EtkInterfaceState> interfaceInfo) {
		this.interfaceInfo = interfaceInfo;
	}

}
