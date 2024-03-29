package bcgov.jh.etk.jhetkcommon.service;

import bcgov.jh.etk.jhetkcommon.model.disputestatusupdate.DisputeStatusUpdate;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.model.ticketdispute.TicketDispute;

public interface IErrorService {
	/**
	 * Save error related to ticket dispute.
	 *
	 * @param ticketdispute the ticketdispute
	 * @param errorCode the error code
	 * @param errorSource the error source
	 * @param errorDescription the error description
	 * @param updateUserID the update user ID
	 * @param icbcText the icbc text
	 * @param justinText the justin text
	 * @param primeText the prime text
	 * @param sendEmail the send email
	 * @return the long
	 */
	public Integer saveError(final TicketDispute ticketdispute, final EnumErrorCode errorCode, final String errorSource, final String errorDescription, 
			final String updateUserID, final String icbcText, final String justinText, final String primeText, final Boolean sendEmail); 
	
	/**
	 * Save error related to dispute status update.
	 *
	 * @param disputeStatusUpdate the dispute status update
	 * @param errorCode the error code
	 * @param errorSource the error source
	 * @param errorDescription the error description
	 * @param updateUserID the update user ID
	 * @param icbcText the icbc text
	 * @param justinText the justin text
	 * @param primeText the prime text
	 * @param sendEmail the send email
	 * @return the long
	 */
	public Integer saveError(final DisputeStatusUpdate disputeStatusUpdate, final EnumErrorCode errorCode, final String errorSource, final String errorDescription, 
			final String updateUserID, final String icbcText, final String justinText, final String primeText, final Boolean sendEmail); 
	
	
	/**
	 * Save error.
	 *
	 * @param contraventionNum the contravention num
	 * @param ticketNum the ticket num
	 * @param errorCode the error code
	 * @param errorSource the error source
	 * @param errorDescription the error description
	 * @param updateUserID the update user ID
	 * @param icbcText the icbc text
	 * @param justinText the justin text
	 * @param primeText the prime text
	 * @param sendEmail the send email
	 * @return the integer
	 */
	public Integer saveError(final String contraventionNum, final String ticketNum, final EnumErrorCode errorCode, final String errorSource, final String errorDescription, 
			final String updateUserID, final String icbcText, final String justinText, final String primeText, final Boolean sendEmail); 

	
	/**
	 * Save error.
	 *
	 * @param ticketID the ticket ID
	 * @param errorCode the error code
	 * @param errorSource the error source
	 * @param errorDescription the error description
	 * @param updateUserID the update user ID
	 * @param icbcText the icbc text
	 * @param justinText the justin text
	 * @param primeText the prime text
	 * @param sendEmail the send email
	 * @return the integer
	 */
	public Integer saveError(final Integer ticketID, final EnumErrorCode errorCode, final String errorSource, final String errorDescription, 
			final String updateUserID, final String icbcText, final String justinText, final String primeText, final Boolean sendEmail); 

	
	/**
	 * Save error.
	 *
	 * @param ticketID the ticket ID
	 * @param contraventionNO the contravention NO
	 * @param errorCode the error code
	 * @param errorSource the error source
	 * @param errorDescription the error description
	 * @param updateUserID the update user ID
	 * @param icbcText the icbc text
	 * @param justinText the justin text
	 * @param primeText the prime text
	 * @param sendEmail the send email
	 * @return the integer
	 */
	public Integer saveError(final Integer ticketID, final EnumErrorCode errorCode, final String errorSource, final String errorDescription, 
			final String updateUserID, final String icbcText, final String justinText, final String primeText, final Boolean sendEmail, String contraventionNO); 

}
