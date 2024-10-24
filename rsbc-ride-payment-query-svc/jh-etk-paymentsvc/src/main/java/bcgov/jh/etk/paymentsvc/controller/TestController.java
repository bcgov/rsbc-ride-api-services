package bcgov.jh.etk.paymentsvc.controller;

import bcgov.jh.etk.jhetkcommon.model.ComponentTestResult;
import bcgov.jh.etk.jhetkcommon.model.PathConst;
import bcgov.jh.etk.jhetkcommon.service.impl.WorkingDataService;
import bcgov.jh.etk.jhetkcommon.util.ComponentTestUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller

public class TestController {

	private static final Logger logger = LoggerFactory.getLogger(TestController.class);

	@Autowired
	ComponentTestUtil componentTestUtil;
	
	/** The icbc adatper service uri. */
	@Value("${icbcadapter_paymentservice_endpoint_url}")
    private String urlPrefix;

	@Autowired
	WorkingDataService workingDataSerice;
	
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
		
		// check DBVersion
		ComponentTestResult dbCheckResult = componentTestUtil.dbVersionCheck();
		message.append(dbCheckResult.getMessage());

		// access icbcadapter/ping service
		ComponentTestResult pingCheckResult = componentTestUtil.pingCheck(urlPrefix, "icbcAdapter");
		message.append(pingCheckResult.getMessage());
		
		// return the result
		if (!(dbCheckResult.isSuccess() && pingCheckResult.isSuccess())) {
			return ResponseEntity
    				.status(HttpStatus.INTERNAL_SERVER_ERROR)
    				.body(message.toString());
		} else {
			return ResponseEntity.status(HttpStatus.OK).body("");
		}
	}

}