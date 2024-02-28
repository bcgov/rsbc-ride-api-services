package bcgov.jh.etk.jhetkcommon.model.eventing.kafka;

import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EVTPaymentEvent.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"ticket_no",
	"count_nbr",
	"transaction_id",
	"payment_card_type_txt",
	"payment_ticket_type_cd",
	"payment_amt",
	"receipt_nbr"
})

@Generated("jsonschema2pojo")
@Setter
@Getter
public class VTPaymentEvent {

	/** The ticket no. */
	@JsonProperty("ticket_no")
	public String ticketNo;
	
	/** The count nbr. */
	@JsonProperty("count_nbr")
	public String countNbr;
	
	/** The transaction id. */
	@JsonProperty("transaction_id")
	public String transactionId;
	
	/** The payment card type txt. */
	@JsonProperty("payment_card_type_txt")
	public String paymentCardTypeTxt;
	
	/** The payment ticket type cd. */
	@JsonProperty("payment_ticket_type_cd")
	public String paymentTicketTypeCd;
	
	/** The payment amt. */
	@JsonProperty("payment_amt")
	public String paymentAmt;
	
	/** The receipt nbr. */
	@JsonProperty("receipt_nbr")
	public String receiptNbr;

}