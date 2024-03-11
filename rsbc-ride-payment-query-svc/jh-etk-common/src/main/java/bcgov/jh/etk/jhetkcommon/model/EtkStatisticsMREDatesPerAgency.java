package bcgov.jh.etk.jhetkcommon.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EtkStatisticsMREDatesPerAgency {
	
	/** The agency code. */
	private String agencyCD;
	
	/** The latest mre minor version. */
	private String latestMreMinorVersion;
	
	/** The reset date. */
	private Date resetDate;

	/** The first ticket issued date using latest MRE. */
	private Date firstTicketIssuedDateUsingLatestMRE;
	
}
