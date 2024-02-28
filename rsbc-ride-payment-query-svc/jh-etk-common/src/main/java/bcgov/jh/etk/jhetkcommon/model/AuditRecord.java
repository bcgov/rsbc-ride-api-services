package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumAuditEventType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuditRecord {
	
	/** The audit event id. */
	private int audit_event_id;
	
	/** The audit event dtm. */
	private LocalDateTime audit_event_dtm;
	
	/** The subject user id. */
	private String subject_user_id;
	
	/** The ticket no. */
	private String ticket_no;
	
	/** The audit event type cd. */
	private EnumAuditEventType audit_event_type_cd;
	
}
