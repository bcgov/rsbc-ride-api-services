package bcgov.jh.etk.jhetkcommon.model;

import java.math.BigInteger;

import lombok.Getter;
import lombok.Setter;


/**
 * The Class EtkReportViolationDetails.
 */
@Setter
@Getter
public class EtkReportViolationDetails {
	
	/** The ticket ID. */
	private BigInteger TICKET_ID;
	
	/** The count nbr. */
	private int COUNT_NBR;
	
	/** The act cd. */
	private String ACT_CD;
	
	/** The section txt. */
	private String SECTION_TXT;
	
	/** The section dsc. */
	private String SECTION_DSC;
	
	/** The fine amt. */
	private String FINE_AMT;
	
	/** The wording nbr. */
	private String WORDING_NBR;

	/** The enter user ID. */
	private String ENT_USER_ID;
	
	/** The enter DTM. */
	private String ENT_DTM;
	
	/** The update user ID. */
	private String UPD_USER_ID;
	
	/** The update DTM. */
	private String UPD_DTM;
}
