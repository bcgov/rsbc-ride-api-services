package bcgov.jh.etk.jhetkcommon.service.impl;

import java.util.Date;
import java.util.Map;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.EventEntity;
import bcgov.jh.etk.jhetkcommon.dataaccess.repository.EventRepository;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.TicketQueryEvent;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.service.RestService;
import bcgov.jh.etk.jhetkcommon.util.StringUtil;
import com.google.gson.Gson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.service.IEtkService;

/**
 * The Class EtkService.
 * @author HLiang
 */
@Service
public class EtkService implements IEtkService {
	
	/** The logger. */
	private static final Logger logger = LoggerFactory.getLogger(EtkService.class);
	private static final Map<String, String> PAYMENT_MSG_CODE_MAP = Map.of(
			Const.ICBC_PAYMENT_MESSAGE_CODE_TICKET_NO_FOUND, "Electronic violation ticket not found. Electronic violation ticket numbers that begin with 'E' or 'S' are available to pay online. Please allow 48 hours for your ticket to appear. If you wish to pay now and your ticket is not available, please use one of the other methods of payments listed on the ticket.",
			Const.ICBC_PAYMENT_MESSAGE_CODE_ZERO_OUTSTANDING, "No payment required for this electronic violation ticket.",
			Const.ICBC_PAYMENT_MESSAGE_CODE_TICKET_NOT_PAYABLE, "This electronic violation ticket cannot be paid online. See ticket for other payment options.",
			Const.ICBC_PAYMENT_MESSAGE_CODE_SYSTEM_ERROR, "Service is Not Available. Please try later.");

	@Autowired
	EventRepository eventRepository;

	@Autowired
	RestService restService;

	@Autowired
	ErrorService errorService;

	@Value("${ride.producer.service.url}")
	private String rideProducerServiceUrl;

	@Value("${ride.producer.service.api.key}")
	private String rideProducerServiceApiKey;


	/**
	 * Gets the icbc payment code.
	 *
	 * @param icbcPaymentMsgCode the icbc payment msg code
	 * @return the message
	 */
	@Override
	public String GetPaymentMessage(String icbcPaymentMsgCode) {
		logger.debug("Get ICBC payment code details: {}", icbcPaymentMsgCode);
		return PAYMENT_MSG_CODE_MAP.get(icbcPaymentMsgCode);
	}

	/**
	 * Record query event.
	 *
	 * @param ticketNO the ticket NO
	 */
	@Override
	public void RecordQueryEvent(final String ticketNO) {
		logger.debug("Record query event, {}", ticketNO);
		long id = nextID();
		EventEntity newEvent = new EventEntity(String.valueOf(id), ticketNO, new Date(), EnumEventType.QUERY.getCodeValue());
		eventRepository.save(newEvent);

		SendEventToProducerAPI(newEvent);
	}

	private void SendEventToProducerAPI(EventEntity newEvent) {
        try {
            Map<String, String> requestHeaders = Map.of("ride-api-key", rideProducerServiceApiKey);
            TicketQueryEvent ticketQueryEvent = new TicketQueryEvent(newEvent);
            String payload = StringUtil.objectToJsonString(ticketQueryEvent);
            String url = rideProducerServiceUrl + "/etkevents/payquery";
            ResponseEntity<String> responseEntity = restService.restfulSave(url, payload, HttpMethod.POST, MediaType.APPLICATION_JSON, requestHeaders);
            logger.debug("Send event to producer API, response: {}", responseEntity);
            if (responseEntity.getStatusCode().isError()) {
                logger.error("Failed to send event to producer API, response: {}", responseEntity);
                errorService.saveError(
                        null,
                        newEvent.getTicketNumber(),
                        EnumErrorCode.Q31,
                        "PaymentSvc.SendEventToProducerAPI",
                        "Failed to send event to producer API",
                        "PaymentSvc",
                        null,
                        null,
                        null,
                        true
                );
            }
        } catch (Exception e) {
            logger.error("Failed to send event to producer API, ticket: {}, error: {} - {}", newEvent.getTicketNumber(), e.getMessage(), e.getStackTrace());
			errorService.saveError(
					null,
					newEvent.getTicketNumber(),
					EnumErrorCode.Q31,
					"PaymentSvc.SendEventToProducerAPI",
					e.getMessage(),
					"PaymentSvc",
					null,
					null,
					null,
					true
			);
        }
    }

	private static long previousTimeMillis = System.currentTimeMillis();
	private static long counter = 0L;
	private static synchronized long nextID() {
		long currentTimeMillis = System.currentTimeMillis();
		counter = (currentTimeMillis == previousTimeMillis) ? (counter + 1L) & 1048575L : 0L;
		previousTimeMillis = currentTimeMillis;
		long timeComponent = (currentTimeMillis & 8796093022207L) << 20;
		return timeComponent | counter;
	}
}
