package bcgov.jh.etk.paymentsvc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mail.MailSenderAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import bcgov.jh.etk.jhetkcommon.JhEtkCommonApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude = {MailSenderAutoConfiguration.class, DataSourceAutoConfiguration.class})
@Import(JhEtkCommonApplication.class)
@ComponentScan(basePackages = {"bcgov.jh.etk.jhetkcommon", "bcgov.jh.etk.paymentsvc"})
@EnableMongoRepositories(basePackages = {"bcgov.jh.etk.jhetkcommon.dataaccess.repository"})
@EnableAsync
public class PaymentsvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsvcApplication.class, args);
	}

}
