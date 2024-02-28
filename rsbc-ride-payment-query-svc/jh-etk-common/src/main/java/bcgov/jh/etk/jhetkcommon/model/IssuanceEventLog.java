package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class IssuanceEventLog {
	
	public static final String BUSINESS_EVENT_STATUS_SUCCESS = "success";
	public static final String BUSINESS_EVENT_STATUS_FAILURE = "failure";
	
	public static final String PRIMECORP_SERVER_REGION_LMD = "LMD";
	public static final String PRIMECORP_SERVER_REGION_NSE = "NSE";
	public static final String PRIMECORP_SERVER_REGION_VIR = "VIR";
	
	//LMD: EA00000100-EA599999999
	private static final String REGEX_PATTERN_LMD = "[eE][aA]0*([1-8][0-9]{2}|9[0-8][0-9]|99[0-9]|[1-8][0-9]{3}|9[0-8][0-9]{2}|99[0-8][0-9]|999[0-9]|[1-8][0-9]{4}|9[0-8][0-9]{3}|99[0-8][0-9]{2}|999[0-8][0-9]|9999[0-9]|[1-8][0-9]{5}|9[0-8][0-9]{4}|99[0-8][0-9]{3}|999[0-8][0-9]{2}|9999[0-8][0-9]|99999[0-9]|[1-8][0-9]{6}|9[0-8][0-9]{5}|99[0-8][0-9]{4}|999[0-8][0-9]{3}|9999[0-8][0-9]{2}|99999[0-8][0-9]|999999[0-9]|[1-8][0-9]{7}|9[0-8][0-9]{6}|99[0-8][0-9]{5}|999[0-8][0-9]{4}|9999[0-8][0-9]{3}|99999[0-8][0-9]{2}|999999[0-8][0-9]|9999999[0-9]|[1-4][0-9]{8}|5[0-8][0-9]{7}|59[0-8][0-9]{6}|599[0-8][0-9]{5}|5999[0-8][0-9]{4}|59999[0-8][0-9]{3}|599999[0-8][0-9]{2}|5999999[0-8][0-9]|59999999[0-9])";
	private static final Pattern lmdPattern = Pattern.compile(REGEX_PATTERN_LMD);
	
	//NSE: EA60000000- EA84999999
	private static final String REGEX_PATTERN_NSE = "[eE][aA]([67][0-9]{7}|8[0-3][0-9]{6}|84[0-8][0-9]{5}|849[0-8][0-9]{4}|8499[0-8][0-9]{3}|84999[0-8][0-9]{2}|849999[0-8][0-9]|8499999[0-9])";
	private static final Pattern nsePattern = Pattern.compile(REGEX_PATTERN_NSE);
	
	//VIR: EA85000000- EA99999999
	private static final String REGEX_PATTERN_VIR = "[eE][aA](8[5-8][0-9]{6}|89[0-8][0-9]{5}|899[0-8][0-9]{4}|8999[0-8][0-9]{3}|89999[0-8][0-9]{2}|899999[0-8][0-9]|8999999[0-9]|9[0-9]{7})";
	private static final Pattern virPattern = Pattern.compile(REGEX_PATTERN_VIR);
	
	/** The log type. */
	String logType = "issuance_business_event";
	
	/** The business event datetime. */
	String businessEventDatetime;
	
	/** The business event status. */
	String businessEventStatus;
	
	/** The business event key. */
	String businessEventKey;
	
	/** The component pod name. */
	String componentPodName;
	
	/** The time slot. */
	String timeSlot;
	
	/** The violation time. */
	String violationTime;
	
	/** The primecorp region. */
	String primecorpRegion;
	
	/** The source name. */
	String sourceName;
	
	/** The target name. */
	String targetName;
	
	/** The component name list. */
	List<String> componentNameList;
	
	/**
	 * Instantiates a new issuance event log.
	 *
	 * @param businessEventKey the business event key
	 * @param violationTime the violation time
	 * @param componentPodName the component pod name
	 * @param businessEventStatus the business event status
	 */
	public IssuanceEventLog(String businessEventKey, String violationTime, String componentPodName, 
			String businessEventStatus, String sourceName, String targetName, List<String> componentNameList) {
		this.businessEventDatetime = DateUtil.localDateTimeToString(LocalDateTime.now(), DateUtil.YYYY_MM_DD_HH_MM_SS);
		this.businessEventKey = businessEventKey;
		this.timeSlot = getTimeSlot(violationTime);
		this.primecorpRegion = getprimecorpServerRegion(businessEventKey);
		this.componentPodName = componentPodName;
		this.businessEventStatus = businessEventStatus;
		this.violationTime = violationTime;
		this.sourceName = sourceName;
		this.targetName = targetName;
		this.componentNameList = componentNameList;
	}

	/**
	 * Gets the time slot.
	 *
	 * @param violationTime the violation time
	 * @return the time slot
	 */
	public static String getTimeSlot(String violationTime) {
		LocalTime vt = LocalTime.parse(violationTime, DateTimeFormatter.ofPattern("HHmm"));
		
		// violation time slots
		//A - 0700 to 0959
		//D - 1000 to 1559
		//B - 1600 to 1859
		//E - 1900 to 2159
		//C - 2200 to 0259
		//F - 0300 to 0659
		String timeSlot = null;
		if (StringUtils.isNoneBlank(timeSlot = isBetween(vt, "07:00", "09:59"))) {
			return timeSlot;
		}
		if (StringUtils.isNoneBlank(timeSlot = isBetween(vt, "10:00", "15:59"))) {
			return timeSlot;
		}
		if (StringUtils.isNoneBlank(timeSlot = isBetween(vt, "16:00", "18:59"))) {
			return timeSlot;
		}
		if (StringUtils.isNoneBlank(timeSlot = isBetween(vt, "19:00", "21:59"))) {
			return timeSlot;
		}
		if (StringUtils.isNoneBlank(timeSlot = isBetween(vt, "22:00", "23:59")) ||
			StringUtils.isNoneBlank(timeSlot = isBetween(vt, "00:00", "02:59"))	) {
			return "22:00 to 02:59";
		}
		if (StringUtils.isNoneBlank(timeSlot = isBetween(vt, "03:00", "06:59"))) {
			return timeSlot;
		}
		
		return null;
	}
	
	/**
	 * Checks if is between.
	 *
	 * @param candidate the candidate
	 * @param start the start
	 * @param end the end
	 * @return true, if candidate >= start and candidate <= end.
	 */
	private static String isBetween(LocalTime candidate, String startDTString, String endDTString) {
		if (!candidate.isBefore(LocalTime.parse(startDTString)) && !candidate.isAfter(LocalTime.parse(endDTString))) {
			return startDTString + " to " + endDTString;
		}
		return null;
	}
	
	/**
	 * Gets the primecorp server region based on the ticketNO.
	 *
	 * @param ticketNO the ticketNO
	 * @return the primecorp server region
	 */
	public static String getprimecorpServerRegion(String ticketNO) {
		//LMD: EA00000100-EA599999999
		//NSE: EA60000000- EA84999999
		//VIR: EA85000000- EA99999999
		Matcher matcher = nsePattern.matcher(ticketNO);
		if (matcher.matches()) {
			return PRIMECORP_SERVER_REGION_NSE;
		}
		
		matcher = virPattern.matcher(ticketNO);
		if (matcher.matches()) {
			return PRIMECORP_SERVER_REGION_VIR;
		}
		
		matcher = lmdPattern.matcher(ticketNO);
		if (matcher.matches()) {
			return PRIMECORP_SERVER_REGION_LMD;
		}
		
		return null;
	}
}
