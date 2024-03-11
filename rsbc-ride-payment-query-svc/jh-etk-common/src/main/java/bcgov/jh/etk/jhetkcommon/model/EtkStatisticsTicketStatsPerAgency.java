package bcgov.jh.etk.jhetkcommon.model;

import java.util.Collections;
import java.util.Date;
import java.util.TreeMap;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class VPHStatisticsTicketStatsPerAgency.
 * @author eliang
 */
@Setter
@Getter
public class EtkStatisticsTicketStatsPerAgency implements Comparable<EtkStatisticsTicketStatsPerAgency>{
	
	/** The Enforcement agency icon name. */
	private String EnforcementAgencyIconName;
	
	/** The agency. */
	private String EnforcementAgencyName;
	
	/** The Enforcement agency CD. */
	private String EnforcementAgencyCD;
	
	/** The total num ticket. */
	private int TotalNoOfTicketsIssued;
	
	/** The first ticket ent dtm. */
	private Date DateOfFirstTicketIssued;
	
	/** The Date of first ticket issued using latest MRE. */
	private Date DateOfFirstTicketIssuedUsingLatestMRE;
	
	/** The reset date. */
	private Date ResetDate;
	
	/** The one cnt total ticket num. */
	private int TotalNoOf1CntTicketsIssued;
	
	/** The two cnt total ticket num. */
	private int TotalNoOf2CntTicketsIssued;
	
	/** The three cnt total ticket num. */
	private int TotalNoOf3CntTicketsIssued;
	
	/** The mre stats. */
	private TreeMap<String, EtkStatisticsTicketCntPerMrePerAgency> mreStats;
	
	private TreeMap<String, Integer> disputeCntPerChargeType;
	
	public EtkStatisticsTicketStatsPerAgency() {
		
	}
	
	/**
	 * Gets the mre stats.
	 *
	 * @return the mre stats
	 */
	public TreeMap<String, EtkStatisticsTicketCntPerMrePerAgency> getMreStats() {
		if (mreStats == null) {
			//init the treemap in reversed order (descending order)
			mreStats = new TreeMap<String, EtkStatisticsTicketCntPerMrePerAgency>(Collections.reverseOrder());
		}
		return mreStats;
	}

	/**
	 * Sets the mre stats.
	 *
	 * @param mreStats the mre stats
	 */
	public void setMreStats(TreeMap<String, EtkStatisticsTicketCntPerMrePerAgency> mreStats) {
		this.mreStats = mreStats;
	}

	public TreeMap<String, Integer> getDisputeCntPerChargeType() {
		if (disputeCntPerChargeType == null) {
			//init the treemap in reversed order (descending order)
			disputeCntPerChargeType = new TreeMap<String, Integer>(Collections.reverseOrder());
		}
		return disputeCntPerChargeType;
	}

	/**
	 * Sets the mre stats.
	 *
	 * @param mreStats the mre stats
	 */
	public void setDisputeCntPerChargeType(TreeMap<String, Integer> disputeCntPerChargeType) {
		this.disputeCntPerChargeType = disputeCntPerChargeType;
	}
	
	/**
	 * Gets the enforcement agency icon name.
	 *
	 * @return the enforcement agency icon name
	 */
	public String getEnforcementAgencyIconName() {
		switch (EnforcementAgencyCD) {
			case "AB": return "AbbotsfordPolice";
			case "CS": return "CentralSaanichPD";
			case "DE": return "DeltaPolice";
			case "NP": return "NelsonPolice";
			case "OB": return "OakBayPolice";
			case "PO": return "PortMoodyPD";
			case "SA": return "SaanichPolice";
			case "GV": return "MetroTransit";
			case "VA": return "VancouverPolice";
			case "VI": return "VictoriaPolice";
			case "WV": return "WestVanPD";
			case "NW": return "NewWestPD";
			case "1002":
			case "1003":
			case "1004":
			case "1101":
			case "1103":
			case "1104":
			case "1105":
			case "1106":
			case "1202":
			case "1203":
			case "1206":
			case "1207":
			case "1301":
			case "1303":
			case "1304":
			case "1305":
			case "1401":
			case "1402":
			case "1501":
			case "1503":
			case "1510":
			case "2100":
			case "2101":
			case "2102":
			case "2103":
			case "2104":
			case "2105":
			case "2106":
			case "2111":
			case "2112":
			case "2113":
			case "2114":
			case "2201":
			case "2203":
			case "2204":
			case "2205":
			case "2206":
			case "2207":
			case "2208":
			case "2209":
			case "2210":
			case "2211":
			case "2301":
			case "2303":
			case "2304":
			case "2305":
			case "2401":
			case "2403":
			case "2404":
			case "2405":
			case "2406":
			case "3100":
			case "3101":
			case "3102":
			case "3103":
			case "3104":
			case "3105":
			case "3201":
			case "3202":
			case "3203":
			case "3204":
			case "3205":
			case "3301":
			case "3303":
			case "3304":
			case "3305":
			case "3306":
			case "3307":
			case "3308":
			case "3401":
			case "3402":
			case "3403":
			case "3404":
			case "3405":
			case "3406":
			case "3501":
			case "3502":
			case "3503":
			case "3505":
			case "3506":
			case "3601":
			case "3602":
			case "3603":
			case "3604":
			case "3606":
			case "3607":
			case "3608":
			case "4100":
			case "4105":
			case "4106":
			case "4107":
			case "4108":
			case "4109":
			case "4110":
			case "4201":
			case "4202":
			case "4203":
			case "4204":
			case "4205":
			case "4206":
			case "4207":
			case "4208":
			case "4301":
			case "4302":
			case "4303":
			case "4304":
			case "4305":
			case "4306":
			case "4307":
			case "4308":
			case "4315":
			case "4316":
			case "4317":
			case "4318":
			case "4319":
			case "4320":
			case "4321":
			case "4322":
			case "4323":
			case "5000":
			case "5001":
			case "9000":
			case "9001":
			case "9002": return "RCMPLogo2";
			case "1107": 
			case "2212": return "StlAtlImxTribal";
		}
		return "unknown";
	}
	
	/**
	 * Compare to.
	 *
	 * @param arg0 the arg 0
	 * @return the int
	 */
	@Override
	public int compareTo(EtkStatisticsTicketStatsPerAgency arg0) {
		String agency1 = arg0.getEnforcementAgencyName();
		return this.EnforcementAgencyName.compareTo(agency1);
	}

}
