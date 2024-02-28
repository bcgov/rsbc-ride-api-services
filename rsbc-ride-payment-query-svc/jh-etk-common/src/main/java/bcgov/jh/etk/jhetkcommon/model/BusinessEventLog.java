package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;

import bcgov.jh.etk.jhetkcommon.model.eventing.Event.EventTypeEnum;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import lombok.Getter;
import lombok.Setter;


/**
 * The Class SankeyLog.
 */
@Setter
@Getter
public class BusinessEventLog {
	
	String logType = "business_event";
	
	/** The business event type. */
	EventTypeEnum businessEventType;
	
	/** The business event datetime. */
	String businessEventDatetime;
	
	/** The business event key. */
	String businessEventKey;
	
	/** The component name. */
	String componentName;
	
	/** The source name. */
	String sourceName;
	
	/** The target name. */
	String targetName;
	
	/** The component pod name. */
	String componentPodName;
	
	/**
	 * Instantiates a new sankey log.
	 *
	 * @param businessEventType the business event type
	 * @param businessEventKey the business event key
	 * @param componentName the component name
	 * @param sourceName the source name
	 * @param targetName the target name
	 * @param componentPodName the component pod name
	 */
	public BusinessEventLog(EventTypeEnum businessEventType, String businessEventKey, 
			String componentName, String sourceName, String targetName, String componentPodName) {
		this.businessEventType = businessEventType;
		this.businessEventDatetime = DateUtil.localDateTimeToString(LocalDateTime.now(), DateUtil.YYYY_MM_DD_HH_MM_SS);
		this.businessEventKey = businessEventKey;
		this.componentName = componentName;
		this.sourceName = sourceName;
		this.targetName = targetName;
		this.componentPodName = componentPodName;
	}
	
	/**
	 * To JSON string.
	 *
	 * @return the string
	 */
	public String toJSONString() {
		StringBuilder sb = new StringBuilder();
		sb.append("logType: " +  logType + ", ");
		sb.append("businessEventType: " +  businessEventType.getValue() + ", ");
		sb.append("businessEventDatetime: " +  businessEventDatetime + ", ");
		sb.append("businessEventKey: " +  businessEventKey + ", ");
		sb.append("componentName: " +  componentName + ", ");
		sb.append("sourceName: " +  sourceName + ", ");
		sb.append("targetName: " +  targetName + ", ");
		sb.append("componentPodName: " +  componentPodName);
		return sb.toString();
	}
}
