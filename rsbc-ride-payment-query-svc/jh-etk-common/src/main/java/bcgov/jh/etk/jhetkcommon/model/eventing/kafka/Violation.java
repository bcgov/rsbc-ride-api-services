package bcgov.jh.etk.jhetkcommon.model.eventing.kafka;

import java.util.List;

import jakarta.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Violation.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"count_nbr",
	"act_cd",
	"section_txt",
	"section_dsc",
	"fine_amt",
	"wording_nbr"
})

@Generated("jsonschema2pojo")
@Setter
@Getter
public class Violation {

	/** The count nbr. */
	@JsonProperty("count_nbr")
	public String countNbr;
	
	/** The act cd. */
	@JsonProperty("act_cd")
	public String actCd;
	
	/** The section txt. */
	@JsonProperty("section_txt")
	public String sectionTxt;
	
	/** The section dsc. */
	@JsonProperty("section_dsc")
	public String sectionDsc;
	
	/** The fine amt. */
	@JsonProperty("fine_amt")
	public String fineAmt;
	
	/** The wording nbr. */
	@JsonProperty("wording_nbr")
	public String wordingNbr;

}