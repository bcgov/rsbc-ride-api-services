package bcgov.jh.etk.jhetkcommon.model;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumCardType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class TicketDBPaymentTxnDetail.
 */

@Setter
@Getter
public class TicketDBPaymentTxnDetail {
	
	/** The payment ticket type cd. */
	private EnumTicketType payment_ticket_type_cd;
		
	/** The payment card type txt. */
	private EnumCardType payment_card_type_txt;
	
	/** The qmonth. */
	private Integer qmonth;
	
	/** The payment amt. */
	private double payment_amt;
	
	/** The txn cnt. */
	private int txn_cnt;	
}
