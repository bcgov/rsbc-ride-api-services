package bcgov.jh.etk.jhetkcommon.service.impl;

import java.util.Date;
import java.util.Map;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.EventEntity;
import bcgov.jh.etk.jhetkcommon.dataaccess.repository.EventRepository;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
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
		eventRepository.save(new EventEntity(ticketNO, new Date(), EnumEventType.QUERY.getCodeValue()));
	}
}
