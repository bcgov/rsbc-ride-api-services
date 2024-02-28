package bcgov.jh.etk.jhetkcommon.model;

import java.math.BigInteger;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import lombok.Getter;
import lombok.Setter;


/**
 * The Class VPHReportEvents.
 * @author HLiang
 */
@Setter
@Getter
public class TicketPaymentKPIDetails {
	
	/** The event ID. */
	private BigInteger EVENT_ID;
	
	/** The event date time. */
	private String EVENT_DTM;
	
	/** The event type. */
	private EnumEventType EVENT_TYPE_CD;
	
	/** The ticket NO. */
	private String TICKET_NO;
	
	/** The count nbr. */
	private String COUNT_NBR;
	
	/** The payment amount. */
	private Double PAYMENT_AMT;
	
	/** The enter user ID. */
	private String ENT_USER_ID;
	
	/** The enter DTM. */
	private String ENT_DTM;
	
	/** The update user ID. */
	private String UPD_USER_ID;
	
	/** The update DTM. */
	private String UPD_DTM;

	/** The payment ticket type cd. */
	private EnumTicketType PAYMENT_TICKET_TYPE_CD;
	
	/** The payment card type txt. */
	private String PAYMENT_CARD_TYPE_TXT;
	
	/** The receipt number. */
	private String RECEIPT_NBR;
	
	/** The payment transaction id. */
	private String PAYMENT_TRANSACTION_ID; 
	
}
