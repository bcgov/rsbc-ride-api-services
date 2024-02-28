package bcgov.jh.etk.jhetkcommon.model.eventing.kafka;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EVTDisputeEvent.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"contravention_no",
	"dispute_type",
	"ticketed_amt",
	"compressed_section",
	"count_act_regulation",
	"created_dtmz"
})

@Generated("jsonschema2pojo")
@Setter
@Getter
public class VTDisputeEvent {

	/** The contravention no. */
	@JsonProperty("contravention_no")
	public String contraventionNo;
	
	/** The dispute type. */
	@JsonProperty("dispute_type")
	public String disputeType;
	
	/** The ticketed amt. */
	@JsonProperty("ticketed_amt")
	public String ticketedAmt;
	
	/** The compressed section. */
	@JsonProperty("compressed_section")
	public String compressedSection;
	
	/** The count act regulation. */
	@JsonProperty("count_act_regulation")
	public String countActRegulation;
	
	/** The created dtmz. */
	@JsonProperty("created_dtmz")
	public String createdDtmz;

}