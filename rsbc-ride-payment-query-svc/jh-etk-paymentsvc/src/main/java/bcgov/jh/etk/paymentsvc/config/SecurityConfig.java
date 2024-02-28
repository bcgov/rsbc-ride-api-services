package bcgov.jh.etk.paymentsvc.config;

import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_PING_REQUEST;
import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_READY_REQUEST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Value("${svc.security.user.name}")
	private String apiUserName;

	@Value("${svc.security.user.password}")
	private String apiUserPassword;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable()
				.authorizeRequests()
				.anyRequest()
				.authenticated()
				.and().httpBasic();
	}

	/* 
     * Unsecure /ping service
     * (non-Javadoc)
     * @see org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter#configure(org.springframework.security.config.annotation.web.builders.WebSecurity)
     */
    @Override
    public void configure(final WebSecurity webSecurity) throws Exception {
        webSecurity.ignoring()
                // All of Spring Security will ignore the requests
                .antMatchers(PATH_PING_REQUEST)
                .antMatchers(PATH_READY_REQUEST);
    }
    
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder authentication)
			throws Exception {
		authentication.inMemoryAuthentication()
				.withUser(apiUserName)
				.password(passwordEncoder.encode(apiUserPassword))
				.authorities("USER");
	}
}