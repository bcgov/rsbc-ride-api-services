package bcgov.jh.etk.jhetkcommon.service;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.service.EtkRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ICBCRestService {

    @Autowired
    private ICBCPaymentRestService oauthRestTemplate;

    @Autowired
    private EtkRestService restTemplate;

    
	//@Value("${icbc_use_oauth2}")
	//private String ICBC_PAYMENT_SERVICE_URI;

    private static boolean USE_OAUTH2;

    @Value("${icbc_use_oauth2}")
    private void setUSE_OAUTH2(String USE_OAUTH2) {
	   this.USE_OAUTH2 = USE_OAUTH2.equalsIgnoreCase("Y");
    }


    /**
     * Secure restful exchange.
     *
     * @param endpointURL the endpoint URL
     * @param payload the payload
     * @param httpMethod the http method
     * @param username the username
     * @param password the password
     * @return the response entity
     */
    public ResponseEntity<String> secureRestfulExchange(final String endpointURL, final Object payload, final HttpMethod httpMethod, final String username, final String password, final MediaType contentType) {
        return ICBCRestService.USE_OAUTH2
                ? oauthRestTemplate.secureRestfulExchange(endpointURL, payload, httpMethod, contentType)
                : restTemplate.secureRestfulExchange(endpointURL, payload, httpMethod, username, password, contentType);
    }
}