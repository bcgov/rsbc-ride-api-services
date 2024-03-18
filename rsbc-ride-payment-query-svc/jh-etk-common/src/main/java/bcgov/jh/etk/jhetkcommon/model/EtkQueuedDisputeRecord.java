package bcgov.jh.etk.jhetkcommon.model;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EtkQueuedDisputeRecord {
	
	/** The process ID. */
	private int processID;
	
	/** The data payload. */
	private String dataPayload;
	
	/** The process state. */
	private EnumProcessState processState;
	
	/** The data type. */
	private EnumEventType dataType;  
	
	/** The ticket number. */
	private String ticketNumber;
	
	/** The count number. */
	private String countNumber;
	
}
