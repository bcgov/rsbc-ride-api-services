package bcgov.jh.etk.jhetkcommon.model.task;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;

/**
 * The Class TaskInputs.
 * @author HLiang
 */
public class TaskInputs {
	public static final String SERVICE_TYPE_HUB_STOP = "To stop the Hub";
	
	public static final String SERVICE_TYPE_HUB_START = "To start the Hub";
	
	/** The upd user ID. */
	private String updUserID;
	
	/** The new state. */
	private String newState;
	
	/** The service type. */
	private String serviceType;
	
	/** The comments. */
	private String comments;
	
	private EnumInterface enumInterface;
	
	public TaskInputs(String updUserID, String newState, String comments, String serviceName, EnumInterface enumInterface) {
		this.updUserID = updUserID;
		this.newState = newState;
		this.serviceType = serviceName;
		this.comments = comments;
		this.enumInterface = enumInterface;
	}
	
	/**
	 * Gets the upd user ID.
	 *
	 * @return the upd user ID
	 */
	public String getUpdUserID() {
		return updUserID;
	}
	
	/**
	 * Sets the upd user ID.
	 *
	 * @param updUserID the new upd user ID
	 */
	public void setUpdUserID(String updUserID) {
		this.updUserID = updUserID;
	}
	
	/**
	 * Gets the new state.
	 *
	 * @return the new state
	 */
	public String getNewState() {
		return newState;
	}
	
	/**
	 * Sets the new state.
	 *
	 * @param newState the new new state
	 */
	public void setNewState(String newState) {
		this.newState = newState;
	}

	/**
	 * Gets the service type.
	 *
	 * @return the service type
	 */
	public String getServiceType() {
		if (serviceType == null || serviceType.equals("etkService.hubStateTransition")) {
			this.serviceType = "Error occurred";
			if ("STOPPED".equals(this.newState)) {
				this.serviceType = TaskInputs.SERVICE_TYPE_HUB_STOP;
			}else if ("RUNNING".equals(this.newState)) {
				this.serviceType = TaskInputs.SERVICE_TYPE_HUB_START;
			}
		}
		return serviceType;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public EnumInterface getEnumInterface() {
		return this.enumInterface;
	}
}
