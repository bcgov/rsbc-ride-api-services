package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import bcgov.jh.etk.jhetkcommon.util.icbc.ICBCUtil;
import lombok.Getter;
import lombok.Setter;


/**
 * The Class FTPFileInfo.
 * @author HLiang
 */
@Setter
@Getter
public class FTPFileInfo {
	
	/** The ticket ID. */
	private Integer ticketID;
	
	/** The ftp file name. */
	private String ftpFileName;
	
	/** The ftp file name short name. */
	private String ftpFileNameShortName;
	
	/** The db file name. */
	private String dbFileName;
	
	/** The db file name short name. */
	private String dbFileNameShortName;
	
	/** The processing state. */
	private EnumProcessState processingState;
	
	/** The ticket number. */
	private String ticketNumber;
	
	/** The deletable. */
	private boolean deletable;
	
	/** The is deletable rule. */
	private String isDeletableRule;
	
	/** The can re queue. */
	private boolean canReQueue;
	
	/** The re queue rule. */
	private String reQueueRule;
	
	/** The errors. */
	private List<FTPFileRelatedError> errors;
	
	/** The last modified DT. */
	private String lastModifiedDT;
	
	/** The show ticket file flag. */
	private boolean showTicketFileFlag;
	
	/**
	 * Gets the ftp file name short name.
	 *
	 * @return the ftp file name short name
	 */
	public String getFtpFileNameShortName() {
		if (StringUtils.isBlank(this.ftpFileNameShortName)) {
			this.ftpFileNameShortName = getFileShortName(this.ftpFileName);
		}
		return ftpFileNameShortName;
	}


	/**
	 * Gets the db file name short name.
	 *
	 * @return the db file name short name
	 */
	public String getDbFileNameShortName() {
		if (StringUtils.isEmpty(this.dbFileNameShortName)) {
			this.dbFileNameShortName = getFileShortName(this.dbFileName);
		}
		return dbFileNameShortName;
	}


	/**
	 * Gets the file short name.
	 *
	 * @param fileFullName the file full name
	 * @return the file short name
	 */
	private String getFileShortName(final String fileFullName) {
		return ICBCUtil.getFileName(fileFullName);
	}
	
	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public List<FTPFileRelatedError> getErrors() {
		return errors;
	}

	/**
	 * Sets the errors.
	 *
	 * @param errors the new errors
	 */
	public void setErrors(List<FTPFileRelatedError> errors) {
		this.errors = errors;
	}

	/**
	 * Gets the ftp file name.
	 *
	 * @return the ftp file name
	 */
	public String getFtpFileName() {
		return ftpFileName;
	}
	
	/**
	 * Sets the ftp file name.
	 *
	 * @param ftpFileName the new ftp file name
	 */
	public void setFtpFileName(String ftpFileName) {
		this.ftpFileName = ftpFileName;
	}
	
	/**
	 * Gets the db file name.
	 *
	 * @return the db file name
	 */
	public String getDbFileName() {
		return dbFileName;
	}
	
	/**
	 * Sets the db file name.
	 *
	 * @param dbFileName the new db file name
	 */
	public void setDbFileName(String dbFileName) {
		this.dbFileName = dbFileName;
	}
	
	/**
	 * Gets the processing state.
	 *
	 * @return the processing state
	 */
	public EnumProcessState getProcessingState() {
		return processingState;
	}
	
	/**
	 * Sets the processing state.
	 *
	 * @param processingState the new processing state
	 */
	public void setProcessingState(EnumProcessState processingState) {
		this.processingState = processingState;
	}
	
	/**
	 * Gets the ticket number.
	 *
	 * @return the ticket number
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}
	
	/**
	 * Sets the ticket number.
	 *
	 * @param ticketNumber the new ticket number
	 */
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	/**
	 * Checks if is deletable.
	 *
	 * @return true, if is deletable
	 */
	public boolean isDeletable() {
		return deletable;
	}
	
	/**
	 * Sets the deletable.
	 *
	 * @param deletable the new deletable
	 */
	public void setDeletable(boolean deletable) {
		this.deletable = deletable;
	}

	/**
	 * Gets the checks if is deletable rule.
	 *
	 * @return the checks if is deletable rule
	 */
	public String getIsDeletableRule() {
		return isDeletableRule;
	}

	/**
	 * Sets the checks if is deletable rule.
	 *
	 * @param isDeletableRule the new checks if is deletable rule
	 */
	public void setIsDeletableRule(String isDeletableRule) {
		this.isDeletableRule = isDeletableRule;
	}


	/**
	 * Checks if is can re queue.
	 *
	 * @return true, if is can re queue
	 */
	public boolean isCanReQueue() {
		return canReQueue;
	}


	/**
	 * Sets the can re queue.
	 *
	 * @param canReQueue the new can re queue
	 */
	public void setCanReQueue(boolean canReQueue) {
		this.canReQueue = canReQueue;
	}


	/**
	 * Gets the re queue rule.
	 *
	 * @return the re queue rule
	 */
	public String getReQueueRule() {
		return reQueueRule;
	}


	/**
	 * Sets the re queue rule.
	 *
	 * @param reQueueRule the new re queue rule
	 */
	public void setReQueueRule(String reQueueRule) {
		this.reQueueRule = reQueueRule;
	}
	
	/**
	 * Getlast modified DT.
	 *
	 * @return the local date time
	 */
	public LocalDateTime getLastModifiedDT() {
		return DateUtil.StringToLocalDateTime(this.lastModifiedDT, "EEE MMM dd HH:mm:ss z yyyy");
	}
}
