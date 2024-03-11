package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EtkDisputeFinding {
	
	/** The event id. */
	private Integer event_id;
	
	/** The justin record id. */
	private String justin_record_id;
	
	/** The contravention no. */
	private String contravention_no;
	
	/** The appearance dt. */
	private String appearance_dt;
	
	/** The finding cd. */
	private String finding_cd;
	
	/** The finding desc. */
	private String finding_desc;
	
	/** The ent dtm. */
	private LocalDateTime ent_dtm;
	
}
