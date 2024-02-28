package bcgov.jh.etk.jhetkcommon.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EtkStatisticsTicketMREStatsPerAgency.
 */
@Setter
@Getter
public class EtkStatisticsTicketMREStatsPerAgency {
	
	/** The agency. */
	private String agencyCD;
	
	/** The mre minor version. */
	private String mreMinorVersion;
	
	/** The total num ticket per MRE minor version. */
	private int totalNumTicketPerMREMinorVersion;

	private String violationDtmLastIssuedTicket;
	
}
