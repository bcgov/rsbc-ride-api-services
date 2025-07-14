package bcgov.jh.etk.jhetkcommon.model.paymentsvc;

import bcgov.jh.etk.jhetkcommon.model.payment.ref.Vehicle;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class IndividualInvoiceSearch.
 */
public class InvoiceSearchResponse_ICBC {
	
	/** The status code. */
	private String statusCode;
	
	/** The ticket number. */
	private String ticketNumber;
	
	/** The offense description. */
	private String offenseDescription;
	
	/** The service date. */
	private String serviceDate;
	
	/** The violation date. */
	private String violationDate;	
	
	/** The fine amount. */
	private double fineAmount;

	/** The court location. */
	private String courtLocation;
	
	/** The discounted amount. */
	private double discountedAmount;
	
	/** The amount due. */
	private double amountDue;
	
	/** The vehicle. */
	private Vehicle vehicle;

	/** The act. */
	private String act;

	/** The section number. */
	private String sectionNumber;

	/** The Form number. */
	@JsonProperty("evt_form_number")
	private String evtFormNumber;


	
	/**
	 * Gets the status code.
	 *
	 * @return the status code
	 */
	public String getStatusCode() {
		return statusCode;
	}
	
	/**
	 * Sets the status code.
	 *
	 * @param statusCode the new status code
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}


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
	public void setFormNumber(String evtFormNumber) {
		this.evtFormNumber = evtFormNumber;
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
	 * Gets the court location.
	 *
	 * @return the court location
	 */
	public String getCourtLocation() {
		return courtLocation;
	}
	
	/**
	 * Sets the court location.
	 *
	 * @param courtLocation court location
	 */
	public void setCourtLocation(String courtLocation) {
		this.courtLocation = courtLocation;
	}

	
	/**
	 * Gets the offense description.
	 *
	 * @return the offense description
	 */
	public String getOffenseDescription() {
		return offenseDescription;
	}
	
	/**
	 * Sets the offense description.
	 *
	 * @param offenseDescription the new offense description
	 */
	public void setOffenseDescription(String offenseDescription) {
		this.offenseDescription = offenseDescription;
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
	 * Gets the fine amount.
	 *
	 * @return the fine amount
	 */
	public double getFineAmount() {
		return fineAmount;
	}
	
	/**
	 * Sets the fine amount.
	 *
	 * @param fineAmount the new fine amount
	 */
	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}
	
	/**
	 * Gets the discounted amount.
	 *
	 * @return the discounted amount
	 */
	public double getDiscountedAmount() {
		return discountedAmount;
	}
	
	/**
	 * Sets the discounted amount.
	 *
	 * @param discountedAmount the new discounted amount
	 */
	public void setDiscountedAmount(double discountedAmount) {
		this.discountedAmount = discountedAmount;
	}
	
	/**
	 * Gets the amount due.
	 *
	 * @return the amount due
	 */
	public double getAmountDue() {
		return amountDue;
	}
	
	/**
	 * Sets the amount due.
	 *
	 * @param amountDue the new amount due
	 */
	public void setAmountDue(double amountDue) {
		this.amountDue = amountDue;
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
	 * Gets the act.
	 *
	 * @return the act
	 */
	public String getAct() {
		return act;
	}

	/**
	 * Sets the act.
	 *
	 * @param act the new act
	 */
	public void setAct(String act) {
		this.act = act;
	}

	/**
	 * Gets the section number.
	 *
	 * @return the section number
	 */
	public String getSectionNumber() {
		return sectionNumber;
	}

	/**
	 * Sets the section number.
	 *
	 * @param sectionNumber the new section number
	 */
	public void setSectionNumber(String sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
}
