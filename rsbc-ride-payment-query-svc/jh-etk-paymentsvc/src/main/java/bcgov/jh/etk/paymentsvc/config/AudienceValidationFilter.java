package bcgov.jh.etk.paymentsvc.config;

import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_PING_REQUEST;
import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_READY_REQUEST;

import java.io.IOException;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Validates the JWT `aud` claim against a configured whitelist of permitted service accounts.
 * Must run after BearerTokenAuthenticationFilter so the SecurityContext is already populated.
 *
 * - Missing aud  → 401 (token cannot be attributed to a known client)
 * - Unknown aud  → 403 (client is identified but not registered for this service)
 * - Allowed aud  → passes through; aud is logged for auditability
 */
public class AudienceValidationFilter extends OncePerRequestFilter {

    private static final Logger log = LoggerFactory.getLogger(AudienceValidationFilter.class);

    private final List<String> allowedAudiences;

    public AudienceValidationFilter(List<String> allowedAudiences) {
        this.allowedAudiences = allowedAudiences;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if (!(auth instanceof JwtAuthenticationToken)) {
            chain.doFilter(request, response);
            return;
        }

        JwtAuthenticationToken jwtAuth = (JwtAuthenticationToken) auth;
        // NimbusJwtDecoder normalises a single-string aud to List<String> via MappedJwtClaimSetConverter
        List<String> audiences = jwtAuth.getToken().getAudience();

        if (audiences == null || audiences.isEmpty()) {
            log.warn("Rejected {} {} — JWT missing aud claim", request.getMethod(), request.getRequestURI());
            SecurityContextHolder.clearContext();
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing audience claim");
            return;
        }

        log.info("JWT aud={} on {} {}", audiences, request.getMethod(), request.getRequestURI());

        boolean permitted = audiences.stream().anyMatch(allowedAudiences::contains);
        if (!permitted) {
            log.warn("Rejected {} {} — aud {} not in allowed list", request.getMethod(), request.getRequestURI(), audiences);
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "Audience not permitted");
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) {
        String path = request.getServletPath();
        return PATH_PING_REQUEST.equals(path) || PATH_READY_REQUEST.equals(path);
    }
}