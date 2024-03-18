package bcgov.jh.etk.jhetkcommon.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EtkDisputeFindings {
	
	/** The dispute findings. */
	private List<EtkDisputeFinding> disputeFindings;
	
	/** The item quantity. */
	private Integer itemQuantity;
	
}
