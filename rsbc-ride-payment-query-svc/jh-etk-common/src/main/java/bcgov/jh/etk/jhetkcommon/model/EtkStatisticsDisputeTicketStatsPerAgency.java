package bcgov.jh.etk.jhetkcommon.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EtkStatisticsDisputeTicketStatsPerAgency.
 */
@Setter
@Getter
public class EtkStatisticsDisputeTicketStatsPerAgency {
	
	/** The agency. */
	private String agencyCD;
	
	/** The act section. */
	private String act_section;
	
	/** The total num disputes. */
	private int totalNumDisputes;

}
