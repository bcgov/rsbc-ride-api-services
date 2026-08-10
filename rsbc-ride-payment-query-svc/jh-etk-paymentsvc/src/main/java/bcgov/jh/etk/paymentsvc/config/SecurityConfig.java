package bcgov.jh.etk.paymentsvc.config;

import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_PING_REQUEST;
import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_READY_REQUEST;
import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_ACTUATOR;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import java.util.List;
import org.springframework.security.config.Customizer;
import org.springframework.security.oauth2.server.resource.web.authentication.BearerTokenAuthenticationFilter;
@Configuration
public class SecurityConfig {


	@Value("${svc.security.user.name}")
	private String apiUserName;

	@Value("${svc.security.user.password}")
	private String apiUserPassword;
	
	

	@Value("${oauth.enabled:false}")
	private boolean oauthEnabled;

	@Value("${http.basic.enabled:false}")
	private boolean httpBasicEnabled;

	

	@Value("${jwt.audience.allowed:}")
	private List<String> allowedAudiences;


	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.anonymous().disable();		
		http
    	.csrf(csrf -> csrf
        .ignoringRequestMatchers(           
            PATH_ACTUATOR
        )
    )
    .sessionManagement(session ->
        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
    );

		if (oauthEnabled || httpBasicEnabled) {
			http.authorizeHttpRequests((auth) -> {
			//auth.requestMatchers(PATH_PING_REQUEST, PATH_READY_REQUEST, PATH_ACTUATOR).permitAll();
			auth.requestMatchers("/", PATH_PING_REQUEST, PATH_READY_REQUEST, PATH_ACTUATOR).permitAll();
			auth.anyRequest().authenticated();
		}).httpBasic(withDefaults());
		} else {
			// Both flags off: permit all — used during gradual partner rollout
			http.authorizeHttpRequests(auth -> auth
			.anyRequest().permitAll()
		);
		}

		if (oauthEnabled) {
			// OAuth takes priority; HTTP Basic is never configured even if http.basic.enabled=true
			//http.oauth2ResourceServer().jwt();
			http.oauth2ResourceServer(oauth2 ->
			oauth2.jwt(Customizer.withDefaults())
			);
			http.addFilterAfter(new AudienceValidationFilter(allowedAudiences), BearerTokenAuthenticationFilter.class);
		} else if (httpBasicEnabled) {
			http.httpBasic(Customizer.withDefaults());
		}
			return http.build();
	}

	@Bean
	@ConditionalOnProperty(name = "http.basic.enabled", havingValue = "true")
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@ConditionalOnProperty(name = "http.basic.enabled", havingValue = "true")
	public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername(apiUserName)
			.password(passwordEncoder.encode(apiUserPassword))
			.roles("USER")
			.build());
		return manager;
	}

	
}