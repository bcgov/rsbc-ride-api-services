package bcgov.jh.etk.jhetkcommon.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * The Class MailConfig.
 * @author HLiang
 */
@Configuration
public class EmailConfig {
	
	/**
	 * Gets the java mail sender.
	 * Configure the bean only if spring.mail.host property is present
	 *
	 * @return the java mail sender
	 */
	@Bean
	@ConditionalOnProperty(prefix = "spring", name = "mail.host")
	@ConfigurationProperties("spring.mail")
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    
	    return mailSender;
	}
}
