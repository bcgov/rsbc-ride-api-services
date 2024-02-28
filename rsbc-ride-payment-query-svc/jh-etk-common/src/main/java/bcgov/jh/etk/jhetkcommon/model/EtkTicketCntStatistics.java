package bcgov.jh.etk.jhetkcommon.model;

import java.util.ArrayList;
import java.util.TreeMap;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class VPHTicketStatistics.
 * @author HLiang
 */
@Setter
@Getter
public class EtkTicketCntStatistics {
	
	/** The ticket cnt today. */
	private String ticketCntToday;
	
	/** The ticket cnt not sent. */
	private String ticketCntNotSent;
	
	/** The error cnt not resolved. */
	private String errorCntNotResolved;
	
	/** The ticket cnt status this month. */
	private ArrayList<EtkStatisticsTicketStateCnt> ticketCntStatus;
	
	/** The evt dispute rate. */
	private double evtDisputeRate;
	
	/** The Dispute ticket cnt status. */
	private TreeMap<EnumTicketType, TreeMap<String, Object>> disputeTicketCntStatus;
}
