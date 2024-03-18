package bcgov.jh.etk.jhetkcommon.model;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import lombok.Getter;
import lombok.Setter;


/**
 * The Class TicketStatisticsDisputeCnt.
 */
@Setter
@Getter
public class TicketStatisticsDisputeCnt {
	
	/** The event type. */
	private String eventType;
	
	/** The ticket type. */
	private EnumTicketType ticketType;
	
	/** The ticket count. */
	private Float ticketCount;
		
}
