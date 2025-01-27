package bcgov.jh.etk.jhetkcommon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.model.PathConst;
import bcgov.jh.etk.jhetkcommon.model.TransitionConfig;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTransitionMode;
import bcgov.jh.etk.jhetkcommon.service.RestService;

/**
 * The Class PauseResumeUtil.
 */
public class PauseResumeUtil {
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PauseResumeUtil.class);

	/**
	 * Send simple message.
	 *
	 * @param emailSender the email sender
	 * @param subject the subject
	 * @param text the text
	 */
	public static void sendSimpleMessage(final JavaMailSender emailSender, final String subject, final String text) {
		logger.debug("Send a simple email message, to: {}, subject: {}, text: {}", ApplicationProperties.emailTo, subject, text);
    	try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(StringUtils.isEmpty(ApplicationProperties.emailTo) ? null : ApplicationProperties.emailTo.split(","));
            message.setSubject(subject);
            message.setText(text);
            message.setFrom(ApplicationProperties.emailFrom);

            emailSender.send(message);
            logger.debug("Send a simple email message done.");
        } catch (MailException exception) {
        	logger.error("Send a simple email message failed: {}", exception.getMessage());
        }
	}
	
	/**
	 * Write pause file.
	 *
	 * @param restService the rest service
	 */
	public static void WritePauseFile(final RestService restService) {
		if (!StringUtil.isPropertyValueConfigured(ApplicationProperties.FTP_FILE_MANAGEMENT_URI)) {
			return;
		}
		
		String url = ApplicationProperties.FTP_FILE_MANAGEMENT_URI + PathConst.PATH_PAUSE_FILE_WRITE;
		logger.info("Write pause file, send the request to this URL: {}", url);
		try {
	    	ResponseEntity<String> response = restService.restfulSave(url, null, HttpMethod.POST, MediaType.APPLICATION_JSON);

			//success, process the response from ICBC
			if (HttpStatus.OK.equals(response.getStatusCode()) || HttpStatus.CREATED.equals(response.getStatusCode())) {
				logger.debug("Pause file successfully written");
			}
		} catch (Exception e) {
			logger.error("Fail writing pause file, error message: {}", e.toString() + "; " + e.getMessage());
    	}
	}
}
