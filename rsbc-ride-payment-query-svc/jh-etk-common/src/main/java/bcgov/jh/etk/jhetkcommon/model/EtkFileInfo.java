package bcgov.jh.etk.jhetkcommon.model;

/**
 * The Class VPHFileInfo.
 * @author HLiang
 */
public class EtkFileInfo {
	
	/** The error msg. */
	private String errorMsg;
	
	/** The file existence. */
	private boolean fileExistence;
	
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
	 * Gets the file existence.
	 *
	 * @return the file existence
	 */
	public boolean getFileExistence() {
		return fileExistence;
	}
	
	/**
	 * Sets the file existence.
	 *
	 * @param fileExistence the new file existence
	 */
	public void setFileExistence(boolean fileExistence) {
		this.fileExistence = fileExistence;
	}
	
	
}
