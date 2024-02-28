package bcgov.jh.etk.jhetkcommon.model;

import java.util.ArrayList;

/**
 * The Class EtkStatisticsTicketErrorDetail.
 */
public class EtkStatisticsTicketErrorDetail {
	
	/** The ticket details. */
	private EtkTicket ticketDetails;
	
	/** The errors. */
	private ArrayList<EtkError> errors;	
	
	/**
	 * Gets the ticket details.
	 *
	 * @return the ticket details
	 */
	public EtkTicket getTicketDetails() {
		if (ticketDetails == null)
			ticketDetails = new EtkTicket();
		
		return ticketDetails;
	}

	/**
	 * Sets the ticket details.
	 *
	 * @param ticketDetails the new ticket details
	 */
	public void setTicketDetails(EtkTicket ticketDetails) {
		this.ticketDetails = ticketDetails;
	}

	/**
	 * Gets the errors.
	 *
	 * @return the errors
	 */
	public ArrayList<EtkError> getErrors() {
		if (errors == null) {
			errors = new ArrayList<EtkError>();
		}
		return errors;
	}

	/**
	 * Sets the errors.
	 *
	 * @param errors the new errors
	 */
	public void setErrors(ArrayList<EtkError> errors) {
		this.errors = errors;
	}

	
	
}
