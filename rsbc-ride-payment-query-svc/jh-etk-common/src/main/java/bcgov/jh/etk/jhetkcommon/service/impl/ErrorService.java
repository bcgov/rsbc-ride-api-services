package bcgov.jh.etk.jhetkcommon.service.impl;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.ErrorComment;
import bcgov.jh.etk.jhetkcommon.dataaccess.entity.ErrorEntity;
import bcgov.jh.etk.jhetkcommon.dataaccess.repository.ErrorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.EtkError;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCategory;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorSeverity;
import bcgov.jh.etk.jhetkcommon.service.EmailService;
import bcgov.jh.etk.jhetkcommon.service.IErrorService;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;

@Service
public class ErrorService implements IErrorService {

	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(ErrorService.class);

	/** The email service. 
	 *  Set required=false, to make EmailService bean optional
	 **/
	@Autowired(required = false)
	EmailService emailService;

	@Autowired
	ErrorRepository errorRepository;

	@Override
	public void saveError(final String contraventionNum, final String ticketNum, final EnumErrorCode errorCode, final String errorSource, final String errorDescription,
						  final String updateUserID, final String icbcText, final String justinText, final String primeText, final Boolean sendEmail) {
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

		saveError(error, errorDescription, sendEmail);
	}

	/**
	 * Save error and/or send email.
	 *
	 * @param error        the error
	 * @param errorDetails the error details
	 * @param sendEmail    the send email
	 */
	private void saveError(EtkError error, String errorDetails, Boolean sendEmail) {
		Integer errorID = null;
		try {
            assert error != null;
			ErrorEntity newError = new ErrorEntity(
					error.getErrorCategory().getCodeValue(),
					error.getErrorSeverity().getCodeValue(),
					error.getCorrelationTicketNumber(),
					error.getCorrelationContraventionNumber(),
					error.getErrorDescription(),
					error.getErrorSource(),
					error.getICBCText()
			);
			newError.getComments().add(new ErrorComment(Const.CONST_PAYMENT_SVC, errorDetails));
            errorRepository.save(newError);
			
			//send email if sendEmail is true
			if (sendEmail == null || Boolean.TRUE.equals(sendEmail)) {
				String subject = buildEmailSubject(error);
				String emailBody = buildEmailBody(error, errorDetails, String.valueOf(errorID));
				emailService.sendSimpleMessage(subject, emailBody);
			}
		} catch (Exception e) {
			logger.error("Error occurred while calling saveError: {}", e.getMessage());
		}
	}

	/**
	 * Builds the email body.
	 * 	Error summary: Failed to generate valid ICBC XML
	 * 		Error code: I.4.3
	 * 		Error occurred on:  2019-02-26T14:33:36
	 * 		Error details link: https://test.jag.gov.bc.ca/vphsc/viewVPHErrorDetails/4809
	 * 		Related ticket number: EZ03102285
	 * 		Error technical details:
	 * 		--
	 * 		 HTTP Status Message: Internal Server Error HTTP Service Error: Error Path : /tns:createContraventionRequest/Violation/Count[0]/OffenseDescription
	 * 		Error Code: VV-005
	 * 		Error Message : [ISC.0082.9034] Field is absent, field must exist
	 * @param error the error
	 * @param errorDetails the error details
	 * @param errorID the error ID
	 * @return the string
	 */
	private String buildEmailBody (final EtkError error, final String errorDetails, final String errorID) {
		StringBuilder stringBuilder = new StringBuilder();
		
		String ticketNumber = error.getCorrelationTicketNumber();

		stringBuilder.append("Error summary: ").append(error.getErrorDescription()).append("\n\n");
		stringBuilder.append("Error code: ").append(error.getErrorCode().getErrorCode()).append("\n\n");
		stringBuilder.append("Error occurred on: ").append(DateUtil.getLocalCurrentDatetimeString()).append("\n\n");
		stringBuilder.append("Error details link: ").append(ApplicationProperties.emailURLPrefix).append(errorID).append("\n\n");
		stringBuilder.append("Related ticket number: ").append(ticketNumber).append("\n\n");
		stringBuilder.append("Error technical details: ").append("\n\n").append(errorDetails);
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
