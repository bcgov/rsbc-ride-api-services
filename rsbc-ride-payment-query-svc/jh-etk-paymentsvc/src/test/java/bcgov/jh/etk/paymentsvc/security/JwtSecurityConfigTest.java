package bcgov.jh.etk.paymentsvc.security;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;
import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import org.springframework.data.jpa.mapping.JpaMetamodelMappingContext;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import org.springframework.web.client.RestTemplate;

import bcgov.jh.etk.jhetkcommon.service.EtkRestService;
import bcgov.jh.etk.jhetkcommon.service.ICBCRestService;
import bcgov.jh.etk.jhetkcommon.service.impl.ErrorService;
import bcgov.jh.etk.jhetkcommon.service.impl.EtkService;
import bcgov.jh.etk.paymentsvc.config.SecurityConfig;

import bcgov.jh.etk.paymentsvc.controller.PaymentRestController;

import org.springframework.http.HttpStatus;

/**
 * Verifies the JWT/OAuth2 resource server path of SecurityConfig (oauth.enabled=true)
 * including AudienceValidationFilter behaviour wired into the security chain.
 *
 * Strategy: GET /request/resumeProcessing with no query params returns 200 immediately
 * (all interfaceNames checks are skipped). A 200 confirms the request cleared both JWT
 * validation and aud-claim checks. A 401/403 means a security layer rejected it.
 *
 * NOTE: Disabled because @WebMvcTest cannot satisfy entityManagerFactory — the main
 * application class declares @EnableJpaRepositories which forces all JPA repositories
 * into the context, but @WebMvcTest does not provide a DataSource or EntityManagerFactory.
 * All filter logic is covered by AudienceValidationFilterTest (pure unit tests).
 * To run these tests, wire up a full Spring context with a real or in-memory database.
 */

@WebMvcTest(PaymentRestController.class)
@MockBean(JpaMetamodelMappingContext.class)
@Import({
        PaymentRestController.class,
        SecurityConfig.class,
        JwtSecurityConfigTest.RestTemplateTestConfig.class
})

@TestPropertySource(properties = {
        "oauth.enabled=true",
        "http.basic.enabled=false",
        "splunk.enabled=false",
        "logging.splunk.url=https://example.com",

        "logging.splunk.token=test",
        "jwt.audience.allowed=test-audience-5287,partner-b",
        "spring.security.oauth2.resourceserver.jwt.issuer-uri=https://dev.loginproxy.gov.bc.ca/auth/realms/standard"
})
public class JwtSecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean private JwtDecoder jwtDecoder;
    @MockBean private EtkService etkService;
    @MockBean private EtkRestService restService;
    @MockBean private ErrorService errorService;
    @MockBean private PasswordEncoder passwordEncoder;
    @MockBean private JpaMetamodelMappingContext jpaMetamodelMappingContext;
    @MockBean private ICBCRestService icbcRestService;
    @MockBean private PaymentRestController paymentRestController;
    

    private static final String ALLOWED_AUD =
            "test-audience-5287";

    private static final String ALLOWED_AUD2 =
            "partner-b";

    private static final String PROTECTED =
            "/ticket/EZ040000771";

    @BeforeEach
    void setUp() {
        when(etkService.GetPaymentMessage(anyString()))
                .thenReturn("");
        when(paymentRestController.individualTicketQueryToICBC(anyString()))
            .thenReturn(ResponseEntity
                    .status(HttpStatus.SERVICE_UNAVAILABLE)
                    .body("{\"mock\":\"response\"}"));
    }

    
    @Configuration
    static class RestTemplateTestConfig {

        @Bean
        RestTemplateBuilder restTemplateBuilder() {
            return new RestTemplateBuilder()
                    .setConnectTimeout(Duration.ofSeconds(1))
                    .setReadTimeout(Duration.ofSeconds(1));
        }

        @Bean
        RestTemplate restTemplate(RestTemplateBuilder builder) {
            return builder.build();
        }
    }

    @Test
    void ping_noAuth_isPublic() throws Exception {
        mockMvc.perform(get("/ping"))
                .andExpect(status().isOk());
    }

    @Test
    void protectedEndpoint_noAuth_returns401() throws Exception {
        mockMvc.perform(get(PROTECTED))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void protectedEndpoint_validJwt_allowedAud_passesSecurityLayer() throws Exception {
        mockMvc.perform(get(PROTECTED)
                        .with(jwt().jwt(j ->
                                j.claim("aud", Collections.singletonList(ALLOWED_AUD)))))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void protectedEndpoint_validJwt_secondAllowedAud_passesSecurityLayer() throws Exception {
        mockMvc.perform(get(PROTECTED)
                        .with(jwt().jwt(j ->
                                j.claim("aud", Collections.singletonList(ALLOWED_AUD2)))))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void protectedEndpoint_validJwt_multipleAudiences_oneAllowed_passes() throws Exception {
        mockMvc.perform(get(PROTECTED)
                        .with(jwt().jwt(j ->
                                j.claim("aud", Arrays.asList("other-service", ALLOWED_AUD)))))
                .andExpect(status().isServiceUnavailable());
    }

    @Test
    void protectedEndpoint_validJwt_unknownAud_returns403() throws Exception {
        mockMvc.perform(get(PROTECTED)
                        .with(jwt().jwt(j ->
                                j.claim("aud", Collections.singletonList("unregistered-client")))))
                .andExpect(status().isForbidden());
    }

    @Test
    void protectedEndpoint_validJwt_missingAudClaim_returns401() throws Exception {
        mockMvc.perform(get(PROTECTED).with(jwt()))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void protectedEndpoint_validJwt_emptyAudList_returns401() throws Exception {
        mockMvc.perform(get(PROTECTED)
                        .with(jwt().jwt(j ->
                                j.claim("aud", Collections.emptyList()))))
                .andExpect(status().isUnauthorized());
    }
}