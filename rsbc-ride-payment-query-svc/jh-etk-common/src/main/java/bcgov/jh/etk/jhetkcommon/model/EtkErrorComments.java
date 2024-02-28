package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;

import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class VPHErrorComments.
 * @author HLiang
 */
public class EtkErrorComments {

	/** The updated by. */
	private String updatedBy;
	
	/** The comments. */
	private String comments;
	
	/** The updated DT. */
	private LocalDateTime updatedDT;

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public String getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy the new updated by
	 */
	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
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

	/**
	 * Gets the updated DT.
	 *
	 * @return the updated DT
	 */
	public LocalDateTime getUpdatedDT() {
		return DateUtil.toPacificLDT(updatedDT);
	}

	/**
	 * Sets the updated DT.
	 *
	 * @param updatedDT the new updated DT
	 */
	public void setUpdatedDT(LocalDateTime updatedDT) {
		this.updatedDT = updatedDT;
	}
}
