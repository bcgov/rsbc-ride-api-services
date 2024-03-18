package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class PaymentEventLog {
	
	public static final String BUSINESS_EVENT_STATUS_SUCCESS = "success";
	public static final String BUSINESS_EVENT_STATUS_FAILURE = "failure";

	/** The log type. */
	String logType = "payment_business_event";
	
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
	public PaymentEventLog(String businessEventKey, String violationTime, String componentPodName, 
			String businessEventStatus, String sourceName, String targetName, List<String> componentNameList) {
		this.businessEventDatetime = DateUtil.localDateTimeToString(LocalDateTime.now(), DateUtil.YYYY_MM_DD_HH_MM_SS);
		this.businessEventKey = businessEventKey;
		this.timeSlot = getTimeSlot(violationTime);
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
	 * @param receiptTime the receipt time. E.g., 22:38
	 * @return the time slot
	 */
	public static String getTimeSlot(String receiptTime) {
		LocalTime vt = LocalTime.parse(receiptTime, DateTimeFormatter.ofPattern("HH:mm"));
		
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
	
}
