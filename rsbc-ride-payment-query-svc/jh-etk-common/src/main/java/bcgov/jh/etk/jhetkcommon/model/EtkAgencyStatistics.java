package bcgov.jh.etk.jhetkcommon.model;

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EtkAgencyStatistics {
	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(EtkAgencyStatistics.class);
	
	/** The statistics ticket stats per agency. */
	private ArrayList<EtkStatisticsTicketStatsPerAgency> statisticsTicketStatsPerAgency;
	
	/** The top charge types. */
	private ArrayList<KeyValue> topChargeTypes;
	
	/** The dispute total cnt per act. */
	private Map<String, String> disputeTotalCntPerAct;
	
	/** The dispute total cnt per agency. */
	private Map<String, String> disputeTotalCntPerAgency;
	
	private int totalDisputedCount;
	
	/** The statistics ticket stats per agency CSV. */
	private String statisticsTicketStatsPerAgencyCSV;
	
	/**
	 * Gets the statistics ticket stats per agency CSV.
	 *
	 * @return the statistics ticket stats per agency CSV
	 */
	public String getStatisticsTicketStatsPerAgencyCSV() {
		//return the value if it's already populated
		if (StringUtils.isNoneBlank(statisticsTicketStatsPerAgencyCSV)) {
			return statisticsTicketStatsPerAgencyCSV;
		}
		
		if (this.getStatisticsTicketStatsPerAgency() == null || this.getStatisticsTicketStatsPerAgency().size() == 0) {
			return statisticsTicketStatsPerAgencyCSV;
		}
		
		StringWriter writer = new StringWriter();
		
	    //File header
	    ArrayList<String> HEADER = new ArrayList<String>(Arrays.asList("Enforcement agency code", "Enforcement agency name", "Date of first ticket issued", "Total # of tickets issued", "Total # of 1-count tickets issued", "Total # of 2-count tickets issued",
		"Total # of 3-count tickets issued"));
	    
	    //add the MRE versions into HEADER
	    TreeMap<String, EtkStatisticsTicketCntPerMrePerAgency> mreStats = this.getStatisticsTicketStatsPerAgency().get(0).getMreStats();
    	Iterator<Entry<String, EtkStatisticsTicketCntPerMrePerAgency>> im = mreStats.entrySet().iterator();
    	while (im.hasNext()) {
    		@SuppressWarnings("rawtypes")
			Map.Entry mapElement = im.next(); 
            HEADER.add("MRE version (" + (String)mapElement.getKey() + ")");
    	}
	    
    	// add the top charge types into HEADER
    	if (topChargeTypes != null) {
    		int index = 1;
	    	for (KeyValue charge : topChargeTypes) {
	    		HEADER.add("#" + index + " dispute charge type (" + charge.getKey() + ")");
	    		index++;
	    	}
    	}
    	
	    //sort the data by agency ascending order
	    Collections.sort(this.statisticsTicketStatsPerAgency);
	    try
    	{
	    	//Adding the header
	    	int j = 0;
	    	while (j < HEADER.size() - 1) {
	    		writer.append(HEADER.get(j));
	    		writer.append(Const.COLUMN_DELIMITER);
	    		j++;
	    	}
    		 
	    	writer.append(HEADER.get(j));
    		//New Line after the header
    		writer.append(Const.LINE_SEPARATOR);
    		
    		//Iterate the agency stats List
    		Iterator<EtkStatisticsTicketStatsPerAgency> it = statisticsTicketStatsPerAgency.iterator();
    		while(it.hasNext())
    		{
    			EtkStatisticsTicketStatsPerAgency e = it.next();
    			writer.append(e.getEnforcementAgencyCD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getEnforcementAgencyName());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getDateOfFirstTicketIssued().toString());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(String.valueOf(e.getTotalNoOfTicketsIssued()));
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(String.valueOf(e.getTotalNoOf1CntTicketsIssued()));
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(String.valueOf(e.getTotalNoOf2CntTicketsIssued()));
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(String.valueOf(e.getTotalNoOf3CntTicketsIssued()));
    			writer.append(Const.COLUMN_DELIMITER);
    			
    			//added mreVersions
    			Iterator<Entry<String, EtkStatisticsTicketCntPerMrePerAgency>> sim = e.getMreStats().entrySet().iterator();
    	    	while (sim.hasNext()) {
    	    		Entry<String, EtkStatisticsTicketCntPerMrePerAgency> mapElement = sim.next(); 
    	    		
    	    		EtkStatisticsTicketCntPerMrePerAgency countList = (EtkStatisticsTicketCntPerMrePerAgency)mapElement.getValue();
    	    		writer.append(countList != null ? countList.getTicketCountTotal() : "0");
    	    		writer.append(Const.COLUMN_DELIMITER);
    	    	}
    	    	
    	    	//add the top charge
    	    	if (topChargeTypes != null) {
    	    		j = 0;
    		    	while (j <= topChargeTypes.size() - 1) {
    		    		String chargeCnt = "0";
	    	    		if (e.getDisputeCntPerChargeType().containsKey(topChargeTypes.get(j).getKey())) {
	    	    			chargeCnt = String.valueOf((int)e.getDisputeCntPerChargeType().get(topChargeTypes.get(j).getKey()));
	    	    		}
	    	    		writer.append(chargeCnt);
	    	    		
	    	    		if (j < topChargeTypes.size() - 1) {
	    	    			writer.append(Const.COLUMN_DELIMITER);
	    	    		}
    		    		j++;
    		    	}
    	    	}
    	    	writer.append(Const.LINE_SEPARATOR);
    		}
    		statisticsTicketStatsPerAgencyCSV = writer.toString();
    	}
    	catch(Exception ee) {
    		logger.error(ee.getMessage());
		} finally {
			try {
				//closing the writer
				writer.close();
			} catch(Exception ee) {
				logger.error(ee.getMessage());
			}
		}
		return statisticsTicketStatsPerAgencyCSV;
	}
}
