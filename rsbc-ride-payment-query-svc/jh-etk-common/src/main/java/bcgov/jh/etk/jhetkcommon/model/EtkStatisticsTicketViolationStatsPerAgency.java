package bcgov.jh.etk.jhetkcommon.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class VPHStatisticsTicketViolationStatsPerAgency.
 * @author eliang
 */
@Setter
@Getter
public class EtkStatisticsTicketViolationStatsPerAgency {
	
	/** The agency. */
	private String agencyCD;
	
	/** The count type. */
	private String countType;
	
	/** The total num ticket per cnt type. */
	private int totalNumTicketPerCntType;
}
