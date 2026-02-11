package bcgov.jh.etk.jhetkcommon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ICBCPaymentRestService {

    @Autowired
    @Qualifier("icbcPaymentRestTemplate")
    private RestTemplate icbcPaymentRestTemplate;

    /**
     * Secure restful exchange.
     */
    public ResponseEntity<String> secureRestfulExchange(
            final String endpointURL,
            final Object payload,          // <-- add this
            final HttpMethod httpMethod,
            final MediaType contentType) {

        HttpEntity<Object> request = getRequest(payload, contentType);
        return icbcPaymentRestTemplate.exchange(endpointURL, httpMethod, request, String.class);
    }

    /**
     * Build request with headers
     */
    private HttpEntity<Object> getRequest(final Object payload, final MediaType contentType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);

        if (payload != null) {
            return new HttpEntity<>(payload, headers);
        } else {
            return new HttpEntity<>(headers);
        }
    }
}
