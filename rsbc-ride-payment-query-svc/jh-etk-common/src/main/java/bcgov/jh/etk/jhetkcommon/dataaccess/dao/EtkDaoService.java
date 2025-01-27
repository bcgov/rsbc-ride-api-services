package bcgov.jh.etk.jhetkcommon.dataaccess.dao;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.service.RestService;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import bcgov.jh.etk.jhetkcommon.util.PauseResumeUtil;

/**
 * The Class EtkDaoService.
 */
@Service
public class EtkDaoService {
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(EtkDaoService.class);
	
	/** The vph dao. */
	@Autowired(required = false)
	EtkDao etkDao;
	
	/** The email sender. */
	@Autowired(required = false)
    public JavaMailSender emailSender;
	
	/** The rest service. */
    @Autowired
    RestService restService;

	/**
	 * Log user comment.
	 *
	 * @param errorID the error ID
	 * @param authorUserID the author user ID
	 * @param comments the comments
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	public void logUserComment(final String errorID, final String authorUserID, final String comments) throws EtkDataAccessException {
		try {
			etkDao.logUserComment(errorID, authorUserID, comments);
		} catch (Exception e) {
			logger.error("Exception occurred while executing logUserComment: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}

	/**
	 * Creates the error stored proc.
	 *
	 * @param ticketID the ticket ID
	 * @param errorCategoryCD the error category CD
	 * @param errorSeverityLevel the error severity level
	 * @param ticketNO the ticket NO
	 * @param contraventionNO the contravention NO
	 * @param detailTxt the detail txt
	 * @param serviceNM the service NM
	 * @param primeTxt the prime txt
	 * @param ICBCTxt the ICBC txt
	 * @param justinTxt the justin txt
	 * @param errorCD the error CD
	 * @return the integer
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Integer createErrorStoredProc(final Integer ticketID, final String errorCategoryCD, final String errorSeverityLevel, final String ticketNO, 
		final String contraventionNO, final String detailTxt, final String serviceNM, final String primeTxt,
		final String ICBCTxt, final String justinTxt, final String errorCD) throws EtkDataAccessException {
	
		try {
			return etkDao.createErrorStoredProc(ticketID, errorCategoryCD, errorSeverityLevel, ticketNO, contraventionNO, detailTxt, 
					serviceNM, primeTxt, ICBCTxt, justinTxt, errorCD);
		} catch (Exception e) {
			logger.error("Exception occurred while executing createErrorStoredProc: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}

	/**
	 * Gets the icbc payment code.
	 *
	 * @param icbcPaymentMsgCode the icbc payment msg code
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, String> GetIcbcPaymentCode(final String icbcPaymentMsgCode) throws EtkDataAccessException {
		try {
			return etkDao.GetIcbcPaymentCode(icbcPaymentMsgCode);
		} catch (Exception e) {
			logger.error("Exception occurred while executing GetIcbcPaymentCode: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}

	/**
	 * Record query event.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void RecordQueryEvent(final String ticketNO) throws EtkDataAccessException {
		try {
			etkDao.RecordQueryEvent(ticketNO);
		} catch (Exception e) {
			logger.error("Exception occurred while executing RecordQueryEvent: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}

	/**
	 * Retrieve interface.
	 *
	 * @param interfaceNM the interface NM
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> RetrieveInterface(final String interfaceNM) throws EtkDataAccessException {
		try {
			return etkDao.RetrieveInterface(interfaceNM);
		} catch (Exception e) {
			logger.error("Exception occurred while executing RetrieveInterface: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Update interface.
	 *
	 * @param interfaceNM the interface NM
	 * @param interfaceStateCD the interface state CD
	 * @param updUserID the upd user ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void UpdateInterface(final EnumInterface interfaceNM, final EnumInterfaceState interfaceStateCD, final String updUserID) throws EtkDataAccessException {
		try {
			etkDao.UpdateInterface(interfaceNM, interfaceStateCD, updUserID);
		} catch (Exception e) {
			logger.error("Exception occurred while executing UpdateInterface: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}

	/**
	 * Db exception handling.
	 *
	 * @param e the e
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void dbExceptionHandling (Exception e) throws EtkDataAccessException {
		String summary = "";
		String resolutionSteps = "Perform the each of the following steps until issue is resolved: " + "\n\n" +
			" * Check for general database connectivity error" + "\n\n" + 
			" * Contact eTK application support for further investigation";
			
		if (e instanceof DataAccessException) {
			summary = "Database access exception";
			logger.error("Data access exception occurred: {}", e.toString());
		} else {
			summary = "Database exception occurred";
			logger.error("DB exception [{}] occurred, file pause the hub. Exception details: {}", e.toString(), e.getMessage());
			String subject = "Hub has entered the paused mode due to a database error ";
			String emailBody = e.toString() + "; \n\n" + e.getMessage() +
					"\n\n" + 
					"Note: In the case of a database connectivity error, there will not be a corresponding error record in the support console.";
			
			StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append("Error summary: " + summary + "\n\n");
			stringBuilder.append("Error occurred on: " + DateUtil.getLocalCurrentDatetimeString() + "\n\n");
			stringBuilder.append("Error technical details: " + "\n\n" + emailBody + "\n\n");
			stringBuilder.append("Error resolution steps: " + "\n\n" + resolutionSteps);
			
			PauseResumeUtil.sendSimpleMessage(emailSender, subject, stringBuilder.toString());
			PauseResumeUtil.WritePauseFile(restService);
		}
		throw new EtkDataAccessException(e);
	}

	/**
	 * Gets the DB version.
	 *
	 * @return the DB version
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String getDBVersion() throws EtkDataAccessException {
		try {
			return etkDao.getDBVersion();
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing getDBVersion: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return null;
	}

	/**
	 * Update ticke process state by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @param interfaceNM the interface NM
	 * @param ticketProcesState the ticket proces state
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void UpdateTickeProcessStateByTicketID(final Integer ticketID, final EnumInterface interfaceNM, final EnumProcessState ticketProcesState) throws EtkDataAccessException {
		try {
			etkDao.UpdateTickeProcessStateByTicketID(ticketID, interfaceNM, ticketProcesState);
		} catch (Exception e) {
			String returnStr = "Exception occurred while updating ticket processing state: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
	}
}
