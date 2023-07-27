package ca.bc.gov.ride.geocodersvc.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;


import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

	@Value("${application.security.username}")
	private String apiUserName;

	@Value("${application.security.password}")
	private String apiUserPassword;

	@Value("${application.path.ping}")
	private String pathPing;

	@Value("${application.path.ready}")
	private String pathReady;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static final String[] AUTH_WHITELIST = {
			// -- Swagger UI v2
			"/v2/api-docs",
			"/swagger-resources/**",
			"/configuration/ui",
			"/configuration/security",
			"/swagger-ui.html",
			"/webjars/**",
			// -- Swagger UI v3
			"/v3/api-docs/**",
			"/swagger-ui/**",
			// CSA Controllers
			"/csa/api/token",
			// Actuators
			"/actuator",
			"/actuator/**",
			"/health/**"
	};

	@Bean
	SecurityWebFilterChain springWebFilterChain(ServerHttpSecurity http) {
				http
						.csrf(ServerHttpSecurity.CsrfSpec::disable)
						.authorizeExchange(exchanges -> exchanges
						.pathMatchers(AUTH_WHITELIST).permitAll()
						.pathMatchers(pathPing, pathReady).permitAll()
						.anyExchange().authenticated()
				)
				.httpBasic(withDefaults())
				.formLogin(withDefaults());
		return http.build();
	}

	@Bean
	MapReactiveUserDetailsService userDetailsService() {
		UserDetails admin = User
				.withUsername(apiUserName)
				.password(passwordEncoder.encode(apiUserPassword))
				.roles("ADMIN", "USER")
				.build();
		return new MapReactiveUserDetailsService(admin);
	}
}