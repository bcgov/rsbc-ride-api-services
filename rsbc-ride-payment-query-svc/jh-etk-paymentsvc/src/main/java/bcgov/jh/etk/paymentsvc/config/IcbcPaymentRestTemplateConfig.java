package bcgov.jh.etk.paymentsvc.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.security.oauth2.client.*;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.ClientAuthenticationMethod;


import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.web.client.RestTemplate;

@Configuration
public class IcbcPaymentRestTemplateConfig {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Value("${icbc_oauth2_token_endpoint_url}")
    private String tokenUri;

    @Value("${icbc_issuanceservice_username}")
    private String clientId;

    @Value("${icbc_issuanceservice_password}")
    private String clientSecret;


    /**
     *  OAuth2 client registration 
     */
    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {

        log.info("ICBC OAuth2 Access Token URL: {}", tokenUri);
        
     

        ClientRegistration registration =
            ClientRegistration.withRegistrationId("icbc")
                .tokenUri(tokenUri)
                .clientId(clientId)
                .clientSecret(clientSecret)
                .clientAuthenticationMethod(ClientAuthenticationMethod.CLIENT_SECRET_POST)
                .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                .scope("app")
                .build();



    

        return new InMemoryClientRegistrationRepository(registration);
    }

    @Bean
    public OAuth2AuthorizedClientService authorizedClientService(
            ClientRegistrationRepository clientRegistrationRepository) {

        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository);
    }

    @Bean
    public OAuth2AuthorizedClientManager authorizedClientManager(
            ClientRegistrationRepository clientRegistrationRepository,
            OAuth2AuthorizedClientService authorizedClientService) {

        OAuth2AuthorizedClientProvider provider =
                OAuth2AuthorizedClientProviderBuilder.builder()
                        .clientCredentials()
                        .build();

        AuthorizedClientServiceOAuth2AuthorizedClientManager manager =
                new AuthorizedClientServiceOAuth2AuthorizedClientManager(
                        clientRegistrationRepository,
                        authorizedClientService);

        manager.setAuthorizedClientProvider(provider);
        return manager;
    }
    

    /**
     * RestTemplate with automatic Bearer token injection
     */
    @Bean
    public RestTemplate icbcPaymentRestTemplate(OAuth2AuthorizedClientManager manager) {

        RestTemplate restTemplate = new RestTemplate();

        ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
             // Log request URL + headers
        log.info("ICBC token call URL: {}", request.getURI());
        log.info("ICBC token request headers: {}", request.getHeaders());

            OAuth2AuthorizeRequest authorizeRequest =
                    OAuth2AuthorizeRequest.withClientRegistrationId("icbc")
                            .principal("icbc-client")
                            .build();

            OAuth2AuthorizedClient authorizedClient =
                    manager.authorize(authorizeRequest);

            if (authorizedClient == null) {
                throw new IllegalStateException("Failed to authorize OAuth2 client");
            }
             log.info("ICBC OAuth2 token acquired. Token length: {}", authorizedClient.getAccessToken().getTokenValue().length());

            request.getHeaders().setBearerAuth(
                    authorizedClient.getAccessToken().getTokenValue());

            return execution.execute(request, body);
        };

        restTemplate.getInterceptors().add(interceptor);

        return restTemplate;
    }
}
