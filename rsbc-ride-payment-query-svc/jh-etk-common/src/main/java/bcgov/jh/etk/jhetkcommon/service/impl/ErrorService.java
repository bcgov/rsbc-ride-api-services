package bcgov.jh.etk.jhetkcommon.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.dataaccess.dao.EtkDaoService;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.EtkError;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCategory;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorSeverity;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.service.EmailService;
import bcgov.jh.etk.jhetkcommon.service.IErrorService;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import bcgov.jh.etk.jhetkcommon.util.InterfaceStateUtil;

@Service
public class ErrorService implements IErrorService {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(ErrorService.class);
	
	/** The vph dao. */
	@Autowired(required = false)
	EtkDaoService etkDao;
	
	/** The email service. 
	 *  Set required=false, to make EmailService bean optional
	 **/
	@Autowired(required = false)
	EmailService emailService;
	
	/** The interface state util. */
	@Autowired
	InterfaceStateUtil interfaceStateUtil;

	@Override
	public Integer saveError(final String contraventionNum, final String ticketNum, final EnumErrorCode errorCode, final String errorSource, final String errorDescription, 
			final String updateUserID, final String icbcText, final String justinText, final String primeText, final Boolean sendEmail) {
		// Don't raise an error if 
		// 1. The interface is RESUMING and
		// 2. The errorCode.silentErrorWhileInterfaceResuming is true
		if (errorCode != null &&
				errorCode.getSilentErrorWhileInterfaceResuming() && 
				EnumInterfaceState.RESUMING.equals(interfaceStateUtil.getInterfaceState(errorCode.getInterface()).getInterfaceState())) {
			return null;
		}
		logger.debug("Save error and/or email");
		EtkError error = new EtkError();
		error.setErrorCode(errorCode);
		error.setAssignedRole(null);
		error.setCorrelationContraventionNumber(contraventionNum);
		error.setCorrelationTicketNumber(ticketNum);
		error.setErrorCategory(EnumErrorCategory.getEnumfromCodeValue(errorCode.getErrorCategory()));
		error.setErrorDescription(errorCode.getErrorSummary());
		error.setErrorSeverity(EnumErrorSeverity.getEnumfromCodeValue(errorCode.getErrorSeverity()));
		error.setErrorSource(errorSource);
		error.setICBCText(icbcText);
		error.setJUSTINText(justinText);
		error.setPRIMEText(primeText);
		error.setUpdateUserID(updateUserID);
		
		return saveError(error, errorDescription, sendEmail);
	}

	@Override
	public Integer saveError(final Integer ticketID, final EnumErrorCode errorCode, final String errorSource, final String errorDescription, 
			final String updateUserID, final String icbcText, final String justinText, final String primeText, final Boolean sendEmail) {
		// Don't raise an error if 
		// 1. Issuance interface is RESUMING and
		// 2. The errorCode.silentErrorWhileInterfaceResuming is true
		if (errorCode != null &&
				errorCode.getSilentErrorWhileInterfaceResuming() && 
				EnumInterfaceState.RESUMING.equals(interfaceStateUtil.getInterfaceState(errorCode.getInterface()).getInterfaceState())) {
			return null;
		}
		logger.debug("Save error and/or email");
		EtkError error = new EtkError();
		error.setErrorCode(errorCode);
		error.setAssignedRole(null);
		error.setCorrelationTicketID(ticketID);
		error.setErrorCategory(EnumErrorCategory.getEnumfromCodeValue(errorCode.getErrorCategory()));
		error.setErrorDescription(errorCode.getErrorSummary());
		error.setErrorSeverity(EnumErrorSeverity.getEnumfromCodeValue(errorCode.getErrorSeverity()));
		error.setErrorSource(errorSource);
		error.setICBCText(icbcText);
		error.setJUSTINText(justinText);
		error.setPRIMEText(primeText);
		error.setUpdateUserID(updateUserID);
		
		return saveError(error, errorDescription, sendEmail);
	}

	/**
	 * Save error and/or send email.
	 *
	 * @param error the error
	 * @param errorDetails the error details
	 * @param sendEmail the send email
	 * @return the long
	 */
	private Integer saveError(EtkError error, String errorDetails, Boolean sendEmail) {
		// Don't raise an error if 
		// 1. The interface is RESUMING and
		// 2. The errorCode.silentErrorWhileInterfaceResuming is true
		if (error != null &&
				error.getErrorCode() != null && 
				error.getErrorCode().getSilentErrorWhileInterfaceResuming() && 
				EnumInterfaceState.RESUMING.equals(interfaceStateUtil.getInterfaceState(error.getErrorCode().getInterface()).getInterfaceState())) {
			return null;
		}
		Integer errorID = null;
		try {
			//save error to db
			errorID = etkDao.createErrorStoredProc(error.getCorrelationTicketID(), error.getErrorCategory() == null ? null : error.getErrorCategory().getCodeValue(), 
					error.getErrorSeverity() == null ? null : error.getErrorSeverity().getCodeValue(), error.getCorrelationTicketNumber(), error.getCorrelationContraventionNumber(), 
					error.getErrorDescription(), error.getErrorSource(), error.getPRIMEText(), error.getICBCText(), error.getJUSTINText(), 
					error.getErrorCode().getErrorCode());
			
			// save error comments to db
			etkDao.logUserComment(String.valueOf(errorID), Const.CONST_JH_ETK, errorDetails);
			
			//send email if sendEmail is true
			if (sendEmail == null || Boolean.TRUE.equals(sendEmail)) {
				String subject = buildEmailSubject(error);
				String emailBody = buildEmailBody(error, errorDetails, String.valueOf(errorID));
				emailService.sendSimpleMessage(subject, emailBody);
			}
		} catch (Exception e) {
			logger.error("Error occurred while calling saveError: {}", e.getMessage());
		}
		return errorID;
	}

	/**
	 * Builds the email body.
	 * 	Error summary: Failed to generate valid ICBC XML
	 * 
	 * 		Error code: I.4.3
	 * 
	 * 		Error occurred on:  2019-02-26T14:33:36
	 * 
	 * 		Error details link: https://test.jag.gov.bc.ca/vphsc/viewVPHErrorDetails/4809
	 * 
	 * 		Related ticket number: EZ03102285
	 * 
	 * 		Error technical details:
	 * 		--
	 * 		 HTTP Status Message: Internal Server Error HTTP Service Error: Error Path : /tns:createContraventionRequest/Violation/Count[0]/OffenseDescription
	 * 		Error Code: VV-005
	 * 		Error Message : [ISC.0082.9034] Field is absent, field must exist
	 *
	 * @param error the error
	 * @param errorDetails the error details
	 * @param errorID the error ID
	 * @return the string
	 */
	private String buildEmailBody (final EtkError error, final String errorDetails, final String errorID) {
		StringBuilder stringBuilder = new StringBuilder();
		
		String ticketNumber = error.getCorrelationTicketNumber();

		stringBuilder.append("Error summary: " + error.getErrorDescription() + "\n\n");
		stringBuilder.append("Error code: " + error.getErrorCode().getErrorCode() + "\n\n");
		stringBuilder.append("Error occurred on: " + DateUtil.getLocalCurrentDatetimeString() + "\n\n");
		stringBuilder.append("Error details link: " + ApplicationProperties.emailURLPrefix + errorID +  "\n\n");
		stringBuilder.append("Related ticket number: " + ticketNumber + "\n\n");
		stringBuilder.append("Error technical details: " + "\n\n" + errorDetails);
		return stringBuilder.toString();
	}
	
	/**
	 * Builds the email subject.
	 *
	 * @param error the error
	 * @return the string
	 */
	private String buildEmailSubject (final EtkError error) {
		String subject = "";
		if (error != null) {
			subject = " " + error.getErrorCode().getErrorCode() + " - " + error.getErrorDescription();
			subject = ApplicationProperties.emailSubjectPrefix.equals("null") ? subject : ApplicationProperties.emailSubjectPrefix + subject;
		}
		return subject;
	}
}
