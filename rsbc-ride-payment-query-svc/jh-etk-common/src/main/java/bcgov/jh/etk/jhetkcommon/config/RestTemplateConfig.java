package bcgov.jh.etk.jhetkcommon.config;

import java.time.Duration;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * The Class RestTemplateConfig.
 * @author HLiang
 */
@Configuration
public class RestTemplateConfig {
	
	/**
	 * Rest template.
	 * Configure the bean only if service.connection.timeout.ms property is present.
	 *
	 * @param builder the builder
	 * @return the rest template
	 */
	@Bean
	@ConditionalOnProperty(value = "service.connection.timeout.ms", matchIfMissing = false)
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
                .setConnectTimeout(Duration.ofMillis(ApplicationProperties.serviceConnectionTimeoutInMS))
                .setReadTimeout(Duration.ofMillis(ApplicationProperties.serviceReadTimeoutInMS))
                .build();
    }
}
