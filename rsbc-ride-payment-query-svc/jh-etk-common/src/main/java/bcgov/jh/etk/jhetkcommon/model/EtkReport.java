package bcgov.jh.etk.jhetkcommon.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.io.StringWriter;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.opencsv.CSVWriter;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class VPHReport.
 * @author HLiang
 */
@Setter
@Getter
public class EtkReport {
	
	/** The report KPI details. */
	private ArrayList<EtkReportKPIDetails> reportKPIDetails;
	
	/** The report events. */
	private ArrayList<EtkReportEvents> reportEvents;
	
	/** The report dispute KPI details. */
	private ArrayList<TicketDisputeKPIDetails> reportDisputeKPIDetails;
	
	/** The report dispute finding. */
	private ArrayList<EtkDisputeFinding> reportDisputeFinding;
	
	/** The report payment KPI details. */
	private ArrayList<TicketPaymentKPIDetails> reportPaymentKPIDetails;
	
	/** The report error KPI. */
	private ArrayList<EtkError> reportErrorKPI;
	
	/** The KPI data CSV. */
	private String kpiDataCSV;
	
	/** The event data CSV. */
	@SuppressWarnings("unused")
	private String eventDataCSV;
	
	/** The KPI data CSV. */
	@SuppressWarnings("unused")
	private String disputeKpiDataCSV;
	
	/** The dispute finding kpi data CSV. */
	@SuppressWarnings("unused")
	private String disputeFindingKpiDataCSV;
	
	/** The payment kpi data CSV. */
	@SuppressWarnings("unused")
	private String paymentKpiDataCSV;
	
	/** The error kpi data CSV. */
	@SuppressWarnings("unused")
	private String errorKpiDataCSV;
	
	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(EtkReport.class);
	
	/**
	 * Convert event data to CSV.
	 *
	 * @return the string
	 */
	public String getEventDataCSV() {
		//return the value if it's already populated
		if (StringUtils.isNoneBlank(eventDataCSV)) {
			return eventDataCSV;
		}
		
		if (this.getReportEvents() == null || this.getReportEvents().size() == 0) {
			return eventDataCSV;
		}
		
		String eventCSV = "";
		
		StringWriter writer = new StringWriter();
		try {
		    StatefulBeanToCsvBuilder<EtkReportEvents> builder = new StatefulBeanToCsvBuilder<>(writer);
		    StatefulBeanToCsv<EtkReportEvents> beanWriter = builder
		              .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
		    		  .withSeparator('\t')
		              .build();
	
		    beanWriter.write(this.reportEvents);
		    
		    eventCSV = writer.toString();
		} catch(Exception ee) {
			logger.error(ee.getMessage());
		} finally {
			try {
				//closing the writer
				writer.close();
			} catch(Exception ee) {
				logger.error(ee.getMessage());
			}
		}
	
		eventDataCSV = eventCSV;
		return eventCSV;
	}

	/**
	 * Gets the statistics ticket stats per agency CSV.
	 *
	 * @return the statistics ticket stats per agency CSV
	 */
	public String getKpiDataCSV() {
		//return the value if it's already populated
		if (StringUtils.isNoneBlank(kpiDataCSV)) {
			return kpiDataCSV;
		}
		
		if (this.getReportKPIDetails() == null || this.getReportKPIDetails().size() == 0) {
			return kpiDataCSV;
		}
		
		StringWriter writer = new StringWriter();
		
	    //File header
	    ArrayList<String> HEADER = new ArrayList<String>(Arrays.asList("TICKET_ID", "SUBMIT_DT", "SENT_TM", "TICKET_NO", "COUNT_QTY", "DRIVERS_LICENCE_PROVINCE_CD",
	    		"PERSON_GENDER_CD", "PERSON_RESIDENCE_CITY_NM", "PERSON_RESIDENCE_PROVINCE_CD", "YOUNG_PERSON_YN", "OFFENDER_TYPE_CD", "REG_OWNER_NM", 
	    		"VIOLATION_DT", "VIOLATION_TM", 
	    		"VIOLATION_HIGHWAY_NM", "VIOLATION_CITY_CD", "VIOLATION_CITY_NM", "VEHICLE_PROVINCE_CD", "VEHICLE_NSC_PUJ_CD", "VEHICLE_MAKE_CD", "VEHICLE_TYPE_CD", "VEHICLE_STYLE_TXT", 
	    		"VEHICLE_ORIGINAL_COLOUR_CD", "VEHICLE_ORIGINAL_COLOUR_TXT", 
	    		"VEHICLE_YY", "ACCIDENT_YN", "DL_PRODUCED_YN", "CHANGE_ADDRESS_YN", "DISPUTE_ADDRESS_TXT", "COURT_LOCATION_CD", "MRE_AGENCY_TXT", "ENFORCEMENT_JURISDICTION_CD", "CERTIFICATE_OF_SERVICE_DT", 
	    		"CERTIFICATE_OF_SERVICE_NO", "E_VIOLATION_FORM_NO", "MRE_MINOR_VERSION_TXT", "PROFILE_NM", "ENT_DTM", "ENFORCEMENT_JURISDICTION_TXT", "ENFORCEMENT_ORG_UNIT_CD", "ENFORCEMENT_ORG_UNIT_CD_TXT",  
	    		"ENFORCEMENT_OFFICER_NO", "ENFORCEMENT_OFFICE_NM", 
	    		"COUNT_NBR_1", "ACT_CD_1", "SECTION_TXT_1", "SECTION_DSC_1", "FINE_AMT_1", "WORDING_NBR_1",
	    		"COUNT_NBR_2", "ACT_CD_2", "SECTION_TXT_2", "SECTION_DSC_2", "FINE_AMT_2", "WORDING_NBR_2",
	    		"COUNT_NBR_3", "ACT_CD_3", "SECTION_TXT_3", "SECTION_DSC_3", "FINE_AMT_3", "WORDING_NBR_3"));
	    
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
    		
    		//Iterate the reportKPIDetails list
    		Iterator<EtkReportKPIDetails> it = reportKPIDetails.iterator();
    		while(it.hasNext())
    		{
    			EtkReportKPIDetails e = (EtkReportKPIDetails)it.next();
    			writer.append(String.valueOf(e.getTICKET_ID()));
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getSUBMIT_DT());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getSENT_TM());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getTICKET_NO());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(String.valueOf(e.getCOUNT_QTY()));
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getDRIVERS_LICENCE_PROVINCE_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getPERSON_GENDER_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getPERSON_RESIDENCE_CITY_NM());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getPERSON_RESIDENCE_PROVINCE_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getYOUNG_PERSON_YN());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getOFFENDER_TYPE_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getReg_owner_nm());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVIOLATION_DT());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVIOLATION_TM());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVIOLATION_HIGHWAY_NM());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVIOLATION_CITY_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVIOLATION_CITY_NM());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVEHICLE_PROVINCE_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVEHICLE_NSC_PUJ_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVEHICLE_MAKE_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVEHICLE_TYPE_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVehicle_style_txt());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVehicle_original_colour_cd());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVehicle_original_colour_txt());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getVEHICLE_YY());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getACCIDENT_YN());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getDL_PRODUCED_YN());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getCHANGE_ADDRESS_YN());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getDISPUTE_ADDRESS_TXT());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getCOURT_LOCATION_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getMRE_AGENCY_TXT());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getENFORCEMENT_JURISDICTION_CD());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getCERTIFICATE_OF_SERVICE_DT());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getCERTIFICATE_OF_SERVICE_NO());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.geteViolationFormNo());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getMRE_MINOR_VERSION_TXT());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getProfile_nm());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getENT_DTM());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getENFORCEMENT_JURISDICTION_TXT());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getEnforcement_org_unit_cd());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getEnforcement_org_unit_cd_txt());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getENFORCEMENT_OFFICER_NO());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getENFORCEMENT_OFFICER_NM());
    			writer.append(Const.COLUMN_DELIMITER);
    			
    			//added violation count1
    			addViolation(writer, e.getViolations() == null ? null : e.getViolations().get("1"), false);
    			
    			//added violation count2
    			addViolation(writer, e.getViolations() == null ? null : e.getViolations().get("2"), false);
    			
    			//added violation count3
    			addViolation(writer, e.getViolations() == null ? null : e.getViolations().get("3"), true);
    			
    			//add a line separator
    			writer.append(Const.LINE_SEPARATOR);
    		}
    		kpiDataCSV = writer.toString();
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
		return kpiDataCSV;
	}
	
	
	/**
	 * Adds the violation.
	 *
	 * @param writer the writer
	 * @param violation the violation
	 */
	private void addViolation(StringWriter writer, EtkReportViolationDetails violation, boolean skipLastColumnDelimiter) {
		writer.append(violation == null ? "" : String.valueOf(violation.getCOUNT_NBR()));
		writer.append(Const.COLUMN_DELIMITER);
		
		writer.append(violation == null ? "" : violation.getACT_CD());
		writer.append(Const.COLUMN_DELIMITER);
		
		writer.append(violation == null ? "" : violation.getSECTION_TXT());
		writer.append(Const.COLUMN_DELIMITER);
		
		writer.append(violation == null ? "" : violation.getSECTION_DSC());
		writer.append(Const.COLUMN_DELIMITER);
		
		writer.append(violation == null ? "" : violation.getFINE_AMT());
		writer.append(Const.COLUMN_DELIMITER);
		
		writer.append(violation == null || violation.getWORDING_NBR() == null ? "" : violation.getWORDING_NBR().toString());
		if (!skipLastColumnDelimiter) {
			writer.append(Const.COLUMN_DELIMITER);
		}
	}
	
	/**
	 * Convert KPI data to CSV.
	 *
	 * @return the string
	 */
	public String getDisputeKpiDataCSV() {
		//return the value if it's already populated
		if (StringUtils.isNoneBlank(disputeKpiDataCSV)) {
			return disputeKpiDataCSV;
		}
		
		if (this.getReportDisputeKPIDetails() == null || this.getReportDisputeKPIDetails().size() == 0) {
			return disputeKpiDataCSV;
		}
		
		String kpiCSV = "";
		StringWriter writer = new StringWriter();
		try {
		    StatefulBeanToCsvBuilder<TicketDisputeKPIDetails> builder = new StatefulBeanToCsvBuilder<>(writer);
		    StatefulBeanToCsv<TicketDisputeKPIDetails> beanWriter = builder
		              .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
		    		  .withSeparator('\t')
		              .build();
	
		    beanWriter.write(this.reportDisputeKPIDetails);
		    
		    kpiCSV = writer.toString();
		} catch(Exception ee) {
			logger.error(ee.getMessage());
		} finally {
			try {
				//closing the writer
				writer.close();
			} catch(Exception ee) {
				logger.error(ee.getMessage());
			}
		}
		
		disputeKpiDataCSV = kpiCSV;
		return kpiCSV;
	}
	
	
	/**
	 * Gets the dispute finding kpi data CSV.
	 *
	 * @return the dispute finding kpi data CSV
	 */
	public String getDisputeFindingKpiDataCSV() {
		//return the value if it's already populated
		if (StringUtils.isNoneBlank(disputeFindingKpiDataCSV)) {
			return disputeFindingKpiDataCSV;
		}
		
		if (this.getReportDisputeFinding() == null || this.getReportDisputeFinding().size() == 0) {
			return disputeFindingKpiDataCSV;
		}
		
		String kpiCSV = "";
		StringWriter writer = new StringWriter();
		try {
		    StatefulBeanToCsvBuilder<EtkDisputeFinding> builder = new StatefulBeanToCsvBuilder<>(writer);
		    StatefulBeanToCsv<EtkDisputeFinding> beanWriter = builder
		              .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
		    		  .withSeparator('\t')
		              .build();
	
		    beanWriter.write(this.reportDisputeFinding);
		    
		    kpiCSV = writer.toString();
		} catch(Exception ee) {
			logger.error(ee.getMessage());
		} finally {
			try {
				//closing the writer
				writer.close();
			} catch(Exception ee) {
				logger.error(ee.getMessage());
			}
		}
		disputeFindingKpiDataCSV = kpiCSV;
		return kpiCSV;
	}
	
	/**
	 * Gets the payment kpi data CSV.
	 *
	 * @return the payment kpi data CSV
	 */
	public String getPaymentKpiDataCSV() {
		//return the value if it's already populated
		if (StringUtils.isNoneBlank(paymentKpiDataCSV)) {
			return paymentKpiDataCSV;
		}
		
		if (this.getReportPaymentKPIDetails() == null || this.getReportPaymentKPIDetails().size() == 0) {
			return paymentKpiDataCSV;
		}
		
		String kpiCSV = "";
		StringWriter writer = new StringWriter();
		try {
		    StatefulBeanToCsvBuilder<TicketPaymentKPIDetails> builder = new StatefulBeanToCsvBuilder<>(writer);
		    StatefulBeanToCsv<TicketPaymentKPIDetails> beanWriter = builder
		              .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
		    		  .withSeparator('\t')
		              .build();
	
		    beanWriter.write(this.reportPaymentKPIDetails);
		    
		    kpiCSV = writer.toString();
		} catch(Exception ee) {
			logger.error(ee.getMessage());
		} finally {
			try {
				//closing the writer
				writer.close();
			} catch(Exception ee) {
				logger.error(ee.getMessage());
			}
		}
		paymentKpiDataCSV = kpiCSV;
		return kpiCSV;
	}
	
	/**
	 * Gets the error kpi data CSV.
	 *
	 * @return the error kpi data CSV
	 */
	public String getErrorKpiDataCSV() {
		//return the value if it's already populated
		if (StringUtils.isNoneBlank(errorKpiDataCSV)) {
			return errorKpiDataCSV;
		}
		
		if (this.getReportErrorKPI() == null || this.getReportErrorKPI().size() == 0) {
			return errorKpiDataCSV;
		}
		
		String kpiCSV = "";
		StringWriter writer = new StringWriter();
		
		//File header
	    ArrayList<String> HEADER = new ArrayList<String>(Arrays.asList("Error ID", "Error code", "Error summary", "Related ticket number", "Error occurred on",
	    		"Error severity", "Error type", "Error status", "Error assigned to"));
	    
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
    		
    		//Iterate the reportKPIDetails list
    		Iterator<EtkError> it = reportErrorKPI.iterator();
    		while(it.hasNext())
    		{
    			EtkError e = (EtkError)it.next();
    			writer.append(String.valueOf(e.getErrorID()));
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getErrorCode().getErrorCode());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getErrorCode().getErrorSummary());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getCorrelationTicketNumber());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(DateUtil.localDateTimeToString(e.getCreateDT(), DateUtil.YYYY_MM_DD_HH_MM_SS));
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getErrorSeverity().getCodeValue());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getErrorCategory().getCodeValue());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getErrorStatus().getCodeValue());
    			writer.append(Const.COLUMN_DELIMITER);
    			writer.append(e.getAssignedRole());
    			
    			//add a line separator
    			writer.append(Const.LINE_SEPARATOR);
    		}
    		kpiCSV = writer.toString();
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

	    errorKpiDataCSV = kpiCSV;
		return kpiCSV;
	}
}
