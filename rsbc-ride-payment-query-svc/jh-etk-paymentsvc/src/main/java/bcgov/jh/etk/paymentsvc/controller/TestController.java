package bcgov.jh.etk.paymentsvc.controller;

import bcgov.jh.etk.jhetkcommon.model.PathConst;
import bcgov.jh.etk.jhetkcommon.service.EtkRestService;
import bcgov.jh.etk.jhetkcommon.util.ComponentTestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	ComponentTestUtil componentTestUtil;

	@Autowired
	EtkRestService restService;
	
	/** The icbc ping service uri. */
	@Value("${icbc.ping.service.uri}")
    private String urlPrefix;

	@Value("${icbc.payment.service.username}")
	private String icbcPaymentServiceUsername;

	@Value("${icbc.payment.service.password}")
	private String icbcPaymentServicePassword;

	/**
	 * Start tests
	 *
	 * @return the response entity
	 */
	@GetMapping(value = PathConst.PATH_READY_REQUEST)
	public @ResponseBody
	ResponseEntity<String> startReadyTest() {
		logger.trace("Received start tests request");

		StringBuilder message = new StringBuilder();
		boolean isSuccess = true;

		String serverUrl = urlPrefix + "?request=ping";
		logger.trace("ICBC Ping to this url: {}", serverUrl);

		try {
			ResponseEntity<String> response = restService.secureRestfulExchange(serverUrl, null, HttpMethod.GET, icbcPaymentServiceUsername, icbcPaymentServicePassword, MediaType.APPLICATION_JSON);
			HttpStatusCode respCode = response.getStatusCode();
			String responseBody = response.getBody();

			// HttpStatus.OK is expected return from MockSvc
			if (respCode != HttpStatus.OK) {
				message.append("Failed doing ping request to ICBC. Http status: ").append(respCode).append("; body: ").append(responseBody).append("\n");
				isSuccess = false;
			}
		} catch (Exception e) {
			message.append("Failed doing ping request to ICBC. Exception details: ").append(e.toString()).append("; ").append(e.getMessage()).append("\n");
			isSuccess = false;
		}

		// return the result
		if (isSuccess) {
			return ResponseEntity.status(HttpStatus.OK).body("");
		} else {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(message.toString());
		}
	}

}