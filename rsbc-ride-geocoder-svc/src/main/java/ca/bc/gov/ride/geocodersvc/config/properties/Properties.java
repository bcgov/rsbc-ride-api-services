package ca.bc.gov.ride.geocodersvc.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ComponentScan
@ConfigurationProperties(prefix = "application")
public class Properties {

	private String environment;
	private int minimumConfidenceScore;

	private String geocodeUserName;
	private String geocodePassword;

	private String dataBcUrl;
	private String dataBcApiKey;

	private String googleUrl;
	private String googleApiKey;
	private boolean googlefailOver;

	private String webClientBaseUri;
	private int webClientTimeOut;

	private Securiry security;
	private Path path;

	@Getter
	@Setter
	public class Securiry {
		public String username;
		public String password;
	}

	@Getter
	@Setter
	public class Path {
		public String ping;
		public String ready;
		public String geocodersvc;
	}
}
