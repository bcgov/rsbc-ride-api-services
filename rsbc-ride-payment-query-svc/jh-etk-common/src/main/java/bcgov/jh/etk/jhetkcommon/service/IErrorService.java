package bcgov.jh.etk.jhetkcommon.service;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;

public interface IErrorService {

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


}
