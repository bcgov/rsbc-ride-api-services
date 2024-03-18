package bcgov.jh.etk.jhetkcommon.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventNotificationTarget {
	
	/** The target id. */
	private Integer targetId;
	
	/** The target name. */
	private String targetName;
	
	/** The target URL. */
	private String targetURL;
	
	/** The last successfully sent event id. */
	private Integer lastSuccessfullySentEventId;
	
	/** The last notification DTM. */
	private OffsetDateTime lastSentDTM;
	
	/** The last notification succeeded. */
	private Boolean lastNotificationSucceeded;
	
	/** The notification event types. */
	private String notificationEventTypes;
	
}
