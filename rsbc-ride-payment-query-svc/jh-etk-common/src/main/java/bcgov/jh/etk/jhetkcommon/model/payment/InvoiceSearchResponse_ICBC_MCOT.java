package bcgov.jh.etk.jhetkcommon.model.payment;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import bcgov.jh.etk.jhetkcommon.model.payment.ref.Contravention;
import bcgov.jh.etk.jhetkcommon.model.payment.ref.Vehicle;

/**
 * The Class IcbcResponseMCOT.
 */
public class InvoiceSearchResponse_ICBC_MCOT {
	
	/** The ticket number. */
	private String ticketNumber;
	
	/** The service date. */
	private String serviceDate;
	
	/** The violation date. */
	private String violationDate;
	
	/** The vehicle. */
	private Vehicle vehicle;
	
	/** The contraventions. */
	private List<Contravention> contraventions;

	/** The Form number. */
	@JsonProperty("evt_form_number")
	private String evtFormNumber;


	/**
	 * Gets the form number.
	 * @return the form number
	 */
	public String getFormNumber() {
		return evtFormNumber;
	}

	/**
	 * Sets the form number.
	 *
	 * @param formNumber the new form number
	 */
	public void setFormNumber(String formNumber) {
		this.evtFormNumber = formNumber;
	}
	
	/**
	 * Gets the ticket number.
	 *
	 * @return the ticket number
	 */
	public String getTicketNumber() {
		return ticketNumber;
	}
	
	/**
	 * Sets the ticket number.
	 *
	 * @param ticketNumber the new ticket number
	 */
	public void setTicketNumber(String ticketNumber) {
		this.ticketNumber = ticketNumber;
	}
	
	/**
	 * Gets the service date.
	 *
	 * @return the service date
	 */
	public String getServiceDate() {
		return serviceDate;
	}
	
	/**
	 * Sets the service date.
	 *
	 * @param serviceDate the new service date
	 */
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	
	/**
	 * Gets the violation date.
	 *
	 * @return the violation date
	 */
	public String getViolationDate() {
		return violationDate;
	}
	
	/**
	 * Sets the violation date.
	 *
	 * @param violationDate the new violation date
	 */
	public void setViolationDate(String violationDate) {
		this.violationDate = violationDate;
	}
	
	/**
	 * Gets the vehicle.
	 *
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}
	
	/**
	 * Sets the vehicle.
	 *
	 * @param vehicle the new vehicle
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	/**
	 * Gets the contraventions.
	 *
	 * @return the contraventions
	 */
	public List<Contravention> getContraventions() {
		return contraventions;
	}
	
	/**
	 * Sets the contraventions.
	 *
	 * @param contraventions the new contraventions
	 */
	public void setContraventions(List<Contravention> contraventions) {
		this.contraventions = contraventions;
	}
}

