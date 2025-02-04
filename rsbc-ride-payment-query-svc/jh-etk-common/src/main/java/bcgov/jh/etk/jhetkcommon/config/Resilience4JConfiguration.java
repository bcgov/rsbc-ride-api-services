package bcgov.jh.etk.jhetkcommon.config;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class Resilience4JConfiguration {
    @Bean
    public Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration() {
        CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
                .failureRateThreshold(ApplicationProperties.circuitBreakerFailureRateThreshold)
                .waitDurationInOpenState(Duration.ofMillis(ApplicationProperties.circuitBreakerWaitDurationInOpenStateInMS))
                .permittedNumberOfCallsInHalfOpenState(ApplicationProperties.circuitBreakerPermittedNumberOfCallsInHalfOpenState)
                .slidingWindowSize(ApplicationProperties.circuitBreakerSlidingWindowSize)
                .build();
        TimeLimiterConfig timeLimiterConfig = TimeLimiterConfig.custom()
                .timeoutDuration(Duration.ofMillis(ApplicationProperties.serviceConnectionTimeoutInMS))
                .build();

        return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
                .timeLimiterConfig(timeLimiterConfig)
                .circuitBreakerConfig(circuitBreakerConfig)
                .build());
    }

    @Bean
    public CircuitBreakerFactory factory()
    {
        Resilience4JCircuitBreakerFactory factory = new Resilience4JCircuitBreakerFactory();
        Customizer<Resilience4JCircuitBreakerFactory> globalCustomConfiguration = globalCustomConfiguration();
        globalCustomConfiguration.customize(factory);
        return factory;
    }
}
