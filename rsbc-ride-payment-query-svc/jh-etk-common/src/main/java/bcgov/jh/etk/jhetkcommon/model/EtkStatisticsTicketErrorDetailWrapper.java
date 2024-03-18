package bcgov.jh.etk.jhetkcommon.model;

import java.util.ArrayList;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EtkStatisticsTicketErrorDetailWrapper.
 */
@Setter
@Getter
public class EtkStatisticsTicketErrorDetailWrapper {
	
	private Integer totalNumOfRecords;
	
	/** The errors. */
	private ArrayList<EtkStatisticsTicketErrorDetail> statisticsTicketErrorDetail;	
	
	
}
