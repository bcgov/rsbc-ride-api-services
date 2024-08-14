package bcgov.jh.etk.paymentsvc.config;

import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_PING_REQUEST;
import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_READY_REQUEST;
import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	@Value("${svc.security.user.name}")
	private String apiUserName;

	@Value("${svc.security.user.password}")
	private String apiUserPassword;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((auth) -> {
			auth.requestMatchers(PATH_PING_REQUEST, PATH_READY_REQUEST).permitAll();
			auth.anyRequest().authenticated();
		}).httpBasic(withDefaults());
		return http.build();
	}

	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User
				.withUsername(apiUserName)
				.password(passwordEncoder.encode(apiUserPassword))
				.authorities("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}