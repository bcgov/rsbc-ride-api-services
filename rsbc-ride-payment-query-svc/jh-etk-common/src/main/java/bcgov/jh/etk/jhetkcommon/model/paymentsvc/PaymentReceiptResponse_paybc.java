package bcgov.jh.etk.jhetkcommon.model.paymentsvc;

/**
 * The Class PaymentReceiptResponse.
 */
public class PaymentReceiptResponse_paybc {
	
	/** The status. */
	private String status;
	
	/** The receipt number. */
	private String receipt_number;
	
	/** The receipt date. */
	private String receipt_date;
	
	/** The receipt amount. */
	private double receipt_amount;
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}
	
	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the receipt number.
	 *
	 * @return the receipt number
	 */
	public String getReceipt_number() {
		return receipt_number;
	}
	
	/**
	 * Sets the receipt number.
	 *
	 * @param receipt_number the new receipt number
	 */
	public void setReceipt_number(String receipt_number) {
		this.receipt_number = receipt_number;
	}
	
	/**
	 * Gets the receipt date.
	 *
	 * @return the receipt date
	 */
	public String getReceipt_date() {
		return receipt_date;
	}
	
	/**
	 * Sets the receipt date.
	 *
	 * @param receipt_date the new receipt date
	 */
	public void setReceipt_date(String receipt_date) {
		this.receipt_date = receipt_date;
	}
	
	/**
	 * Gets the receipt amount.
	 *
	 * @return the receipt amount
	 */
	public double getReceipt_amount() {
		return receipt_amount;
	}
	
	/**
	 * Sets the receipt amount.
	 *
	 * @param receipt_amount the new receipt amount
	 */
	public void setReceipt_amount(double receipt_amount) {
		this.receipt_amount = receipt_amount;
	}
}
