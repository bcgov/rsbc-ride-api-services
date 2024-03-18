package bcgov.jh.etk.jhetkcommon.model;

import java.util.ArrayList;

import org.junit.Test;

import bcgov.jh.etk.jhetkcommon.util.DateUtil;

public class EtkStatisticsTest {

	@Test
	public void test() {
		EtkAgencyStatistics statistics = new EtkAgencyStatistics();
    	
    	ArrayList<EtkStatisticsTicketStatsPerAgency> statisticsTicketStatsPerAgency = new ArrayList<EtkStatisticsTicketStatsPerAgency>();
    	statisticsTicketStatsPerAgency.add(buildAgency("2019-02-01", "Vancouver Police department", 1, 2, 3, 5));
    	statisticsTicketStatsPerAgency.add(buildAgency("2019-01-01", "Victoria Police department", 0, 2, 4, 2));
    	statisticsTicketStatsPerAgency.add(buildAgency("2019-06-04", "RCMP", 7, 4, 1, 0));
    	statisticsTicketStatsPerAgency.add(buildAgency("2019-03-013", "Surry Police department", 1, 6, 9, 1));
    	
    	statistics.setStatisticsTicketStatsPerAgency(statisticsTicketStatsPerAgency);
    	String agencyCSV = statistics.getStatisticsTicketStatsPerAgencyCSV();
    	//assertNotNull(agencyCSV);
	}

	private EtkStatisticsTicketStatsPerAgency buildAgency(String dtm, String agencyName, int cnt1, int cnt2, int cnt3, int ticketnotissued) {
    	EtkStatisticsTicketStatsPerAgency agency = new EtkStatisticsTicketStatsPerAgency();
    	agency.setDateOfFirstTicketIssued(DateUtil.StringToDate(dtm, DateUtil.YYYY_MM_DD));
    	agency.setEnforcementAgencyName(agencyName);
    	agency.setTotalNoOf1CntTicketsIssued(cnt1);
    	agency.setTotalNoOf2CntTicketsIssued(cnt2);
    	agency.setTotalNoOf3CntTicketsIssued(cnt3);
    	agency.setTotalNoOfTicketsIssued(ticketnotissued);
    	return agency;
    }
}
