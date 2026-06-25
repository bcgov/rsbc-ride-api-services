package bcgov.jh.etk.paymentsvc.security;


import static jakarta.servlet.http.HttpServletResponse.SC_FORBIDDEN;
import static jakarta.servlet.http.HttpServletResponse.SC_OK;
import static jakarta.servlet.http.HttpServletResponse.SC_UNAUTHORIZED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

import java.time.Instant;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import jakarta.servlet.FilterChain;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import bcgov.jh.etk.paymentsvc.config.AudienceValidationFilter;

/**
 * Unit tests for AudienceValidationFilter.
 *
 * Keycloak CSS Portal sets aud to the client's resource name (single string).
 * NimbusJwtDecoder normalises single-string aud to List<String>, so tests
 * reflect the post-parse state the filter actually receives.
 */
public class AudienceValidationFilterTest {

    private static final String PARTNER_A = "test-audience-5287";
    private static final String PARTNER_B = "test-audience2-8592";
    private static final List<String> ALLOWED = Arrays.asList(PARTNER_A, PARTNER_B);

    private final AudienceValidationFilter filter = new AudienceValidationFilter(ALLOWED);

    @AfterEach
    public void clearContext() {
        SecurityContextHolder.clearContext();
    }

    // ── helpers ──────────────────────────────────────────────────────────────

    private Jwt buildJwt(List<String> audiences) {
        Jwt.Builder builder = Jwt.withTokenValue("test.token.value")
                .header("alg", "RS256")
                .subject("service-account-" + PARTNER_A)
                .issuer("https://dev.loginproxy.gov.bc.ca/auth/realms/standard")
                .issuedAt(Instant.now())
                .expiresAt(Instant.now().plusSeconds(3600));
        if (audiences != null) {
            // Matches the List<String> shape NimbusJwtDecoder produces after parsing
            builder.claim("aud", audiences);
        }
        return builder.build();
    }

    private void setAuthenticated(Jwt jwt) {
        SecurityContextHolder.getContext().setAuthentication(
                new JwtAuthenticationToken(jwt, Collections.emptyList()));
    }

    // ── tests ─────────────────────────────────────────────────────────────────

    @Test
    public void whitelistedAud_passesThrough() throws Exception {
        setAuthenticated(buildJwt(Collections.singletonList(PARTNER_A)));

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/lookup/events");
        MockHttpServletResponse res = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        filter.doFilter(req, res, chain);

        verify(chain).doFilter(req, res);
        assertEquals(SC_OK, res.getStatus());
    }

    @Test
    public void secondPartnerAud_passesThrough() throws Exception {
        setAuthenticated(buildJwt(Collections.singletonList(PARTNER_B)));

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/lookup/events");
        MockHttpServletResponse res = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        filter.doFilter(req, res, chain);

        verify(chain).doFilter(req, res);
        assertEquals(SC_OK, res.getStatus());
    }

    @Test
    public void unknownAud_returns403() throws Exception {
        setAuthenticated(buildJwt(Collections.singletonList("unknown-client-xyz")));

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/lookup/events");
        MockHttpServletResponse res = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        filter.doFilter(req, res, chain);

        verify(chain, never()).doFilter(req, res);
        assertEquals(SC_FORBIDDEN, res.getStatus());
    }

    @Test
    public void missingAudClaim_returns401() throws Exception {
        setAuthenticated(buildJwt(null));

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/lookup/events");
        MockHttpServletResponse res = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        filter.doFilter(req, res, chain);

        verify(chain, never()).doFilter(req, res);
        assertEquals(SC_UNAUTHORIZED, res.getStatus());
    }

    @Test
    public void emptyAudList_returns401() throws Exception {
        setAuthenticated(buildJwt(Collections.emptyList()));

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/lookup/events");
        MockHttpServletResponse res = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        filter.doFilter(req, res, chain);

        verify(chain, never()).doFilter(req, res);
        assertEquals(SC_UNAUTHORIZED, res.getStatus());
    }

    @Test
    public void noJwtAuthentication_skipsFilter() throws Exception {
        // SecurityContext has no JwtAuthenticationToken (e.g. oauth.enabled=false path)
        SecurityContextHolder.clearContext();

        MockHttpServletRequest req = new MockHttpServletRequest("GET", "/lookup/events");
        MockHttpServletResponse res = new MockHttpServletResponse();
        FilterChain chain = mock(FilterChain.class);

        filter.doFilter(req, res, chain);

        verify(chain).doFilter(req, res);
        assertEquals(SC_OK, res.getStatus());
    }
}
