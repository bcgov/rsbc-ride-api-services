package bcgov.jh.etk.jhetkcommon.model.eventing.kafka;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EVTDisputeStatusUpdateEvent.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"ticket_no",
	"count_nbr",
	"dispute_action_dt",
	"dispute_action_code"
})
@Generated("jsonschema2pojo")

@Setter
@Getter
public class VTDisputeStatusUpdateEvent {

	/** The ticket no. */
	@JsonProperty("ticket_no")
	public String ticketNo;
	
	/** The count nbr. */
	@JsonProperty("count_nbr")
	public String countNbr;
	
	/** The dispute action dt. */
	@JsonProperty("dispute_action_dt")
	public String disputeActionDt;
	
	/** The dispute action code. */
	@JsonProperty("dispute_action_code")
	public String disputeActionCode;

}