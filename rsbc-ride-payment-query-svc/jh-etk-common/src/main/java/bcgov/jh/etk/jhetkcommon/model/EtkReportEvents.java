package bcgov.jh.etk.jhetkcommon.model;

import java.math.BigInteger;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import lombok.Getter;
import lombok.Setter;


/**
 * The Class VPHReportEvents.
 * @author HLiang
 */
@Setter
@Getter
public class EtkReportEvents {
	
	/** The event ID. */
	private BigInteger EVENT_ID;
	
	/** The event date time. */
	private String EVENT_DTM;
	
	/** The event type. */
	private EnumEventType EVENT_TYPE_CD;
	
	/** The ticket NO. */
	private String TICKET_NO;
	
	/** The enter user ID. */
	private String ENT_USER_ID;
	
	/** The enter DTM. */
	private String ENT_DTM;
	
	/** The update user ID. */
	private String UPD_USER_ID;
	
	/** The update DTM. */
	private String UPD_DTM;
	
	/** The count nbr. */
	private String COUNT_NBR;
	
	/** The dispute action cd. */
	private String DISPUTE_ACTION_CD;
	
	/** The dispute action dt. */
	private String DISPUTE_ACTION_DT; 
}
