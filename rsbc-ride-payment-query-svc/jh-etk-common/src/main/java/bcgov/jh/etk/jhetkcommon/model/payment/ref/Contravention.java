package bcgov.jh.etk.jhetkcommon.model.payment.ref;

/**
 * The Class Contravention.
 */
public class Contravention {
	
	/** The status code. */
	private String statusCode;
	
	/** The count number. */
	private String countNumber;
	
	/** The contravention number. */
	private String contraventionNumber;
	
	/** The offense description. */
	private String offenseDescription;
	
	/** The fine amount. */
	private double fineAmount;
	
	/** The discounted amount. */
	private double discountedAmount;
	
	/** The amount due. */
	private double amountDue;

	/** The act. */
	private String act;

	/** The section number. */
	private String sectionNumber;
	
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
	 * Gets the count number.
	 *
	 * @return the count number
	 */
	public String getCountNumber() {
		return countNumber;
	}
	
	/**
	 * Sets the count number.
	 *
	 * @param countNumber the new count number
	 */
	public void setCountNumber(String countNumber) {
		this.countNumber = countNumber;
	}
	
	/**
	 * Gets the contravention number.
	 *
	 * @return the contravention number
	 */
	public String getContraventionNumber() {
		return contraventionNumber;
	}
	
	/**
	 * Sets the contravention number.
	 *
	 * @param contraventionNumber the new contravention number
	 */
	public void setContraventionNumber(String contraventionNumber) {
		this.contraventionNumber = contraventionNumber;
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
