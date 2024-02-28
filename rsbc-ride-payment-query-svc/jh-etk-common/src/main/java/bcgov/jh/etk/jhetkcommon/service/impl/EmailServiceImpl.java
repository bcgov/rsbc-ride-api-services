package bcgov.jh.etk.jhetkcommon.service.impl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.service.EmailService;
import bcgov.jh.etk.jhetkcommon.util.PauseResumeUtil;

/**
 * The Class EmailServiceImpl.
 * @author HLiang
 */
@Component
public class EmailServiceImpl implements EmailService {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(EmailServiceImpl.class);
	
	/** The email sender. 
     *  Set required=false, to make JavaMailSender bean optional
     **/
	@Autowired(required = false)
    public JavaMailSender emailSender;
 
    /* (non-Javadoc)
     * @see bcgov.jh.etk.jhetkdisputesvc.service.EmailService#sendSimpleMessage(java.lang.String, java.lang.String, java.lang.String)
     */
	@Async
    public void sendSimpleMessage(final String subject, final String text) {
    	PauseResumeUtil.sendSimpleMessage(emailSender, subject, text);
    }
    
	@Async
	public void sendMimeMessage(final String subject, final String emailBody) {
		MimeMessage mimeMessage = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
		
		String htmlMsg = emailBody;
		try {
			helper.setText(htmlMsg, true);
			helper.setTo(StringUtils.isEmpty(ApplicationProperties.emailTo) ? null : ApplicationProperties.emailTo.split(","));
			helper.setSubject(subject);
			helper.setFrom(ApplicationProperties.emailFrom);
			emailSender.send(mimeMessage);
		} catch (MessagingException e) {
			logger.error("Send a mimeMessage email failed: {}", e.getMessage());
		} 
	}

	  @Override 
	  public void sendMimeMessageWithAttachment(String subject, String emailBody, String attachmentFilename, ByteArrayResource inputStreamSource, String contentType) { 
		  MimeMessagePreparator preparator = mimeMessage -> { 
			  MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, "UTF-8");
			  message.setTo(StringUtils.isEmpty(ApplicationProperties.emailTo) ? null : ApplicationProperties.emailTo.split(","));
			  message.setFrom(ApplicationProperties.emailFrom);
			  message.setSubject(subject); 
			  message.setText(emailBody);
			  
			  message.addAttachment(attachmentFilename, inputStreamSource, contentType); 
		  };
	  
		  try { 
			  emailSender.send(preparator); 
		  } catch (MailException ex) {
			  logger.error("Send a mimeMessage email with attachment failed: {}",
			  ex.getMessage()); 
		  }
	  }
}
