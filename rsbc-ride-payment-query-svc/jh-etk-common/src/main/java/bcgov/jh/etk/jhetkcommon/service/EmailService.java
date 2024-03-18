package bcgov.jh.etk.jhetkcommon.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

/**
 * The Interface EmailService.
 * @author HLiang
 */
@Service
public interface EmailService {
	
	/**
	 * Send simple message.
	 *
	 * @param subject the subject
	 * @param text the text
	 */
	void sendSimpleMessage(final String subject, final String text);
	
	/**
	 * Send mime message.
	 *
	 * @param subject the subject
	 * @param text the text
	 */
	void sendMimeMessage(final String subject, final String text);
	
	/**
	 * Send mime message with attachment.
	 *
	 * @param subject the subject
	 * @param emailBody the email body
	 * @param attachmentFilename the attachment filename
	 * @param inputStreamSource the input stream source
	 * @param contentType the content type
	 */
	void sendMimeMessageWithAttachment(final String subject, String emailBody, final String attachmentFilename, final ByteArrayResource inputStreamSource, final String contentType);
}
