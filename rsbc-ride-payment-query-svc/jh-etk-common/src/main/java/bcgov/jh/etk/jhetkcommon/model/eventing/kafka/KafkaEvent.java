package bcgov.jh.etk.jhetkcommon.model.eventing.kafka;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class KafkaEvent implements Comparable<KafkaEvent>{
	Integer eventID;
	
	/** The evt issuance event. */
	EVTIssuanceEvent evtIssuanceEvent = null;
	
	/** The vt payment event. */
	VTPaymentEvent vtPaymentEvent = null;
	
	/** The vt dispute event. */
	VTDisputeEvent vtDisputeEvent = null;
	
	/** The vt dispute status update. */
	VTDisputeStatusUpdateEvent vtDisputeStatusUpdate = null;
	
	@Override
	public int compareTo(KafkaEvent evt) {
		return this.getEventID().compareTo(evt.getEventID());
	}
}
