package bcgov.jh.etk.jhetkcommon.model.paymentsvc;

import java.util.List;

/**
 * The Class PaymentReceiptRequestFromPayBC.
 */
public class PaymentReceiptRequest_paybc {
	
	/** The receipt number. */
	private String receipt_number;
	
	/** The receipt date. */
	private String receipt_date;
	
	/** The receipt amount. */
	private double receipt_amount;
	
	/** The payment method. */
	private String payment_method;
	
	/** The comments. */
	private String comments;
	
	/** The cardtype. */
	private String cardtype;	
	
	/** The transaction id. */
	private String transaction_id;
	
	/** The authorization id. */
	private String authorization_id;	
	
	/** The invoices. */
	private List<InvoiceSearchResponse_paybc> invoices;
	
	/**
	 * Gets the payment method.
	 *
	 * @return the payment method
	 */
	public String getPayment_method() {
		return payment_method;
	}
	
	/**
	 * Sets the payment method.
	 *
	 * @param payment_method the new payment method
	 */
	public void setPayment_method(String payment_method) {
		this.payment_method = payment_method;
	}
	
	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return comments;
	}
	
	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	/**
	 * Gets the cardtype.
	 *
	 * @return the cardtype
	 */
	public String getCardtype() {
		return cardtype;
	}
	
	/**
	 * Sets the cardtype.
	 *
	 * @param cardtype the new cardtype
	 */
	public void setCardtype(String cardtype) {
		this.cardtype = cardtype;
	}
	
	/**
	 * Gets the transaction id.
	 *
	 * @return the transaction id
	 */
	public String getTransaction_id() {
		return transaction_id;
	}
	
	/**
	 * Sets the transaction id.
	 *
	 * @param transaction_id the new transaction id
	 */
	public void setTransaction_id(String transaction_id) {
		this.transaction_id = transaction_id;
	}
	
	/**
	 * Gets the authorization id.
	 *
	 * @return the authorization id
	 */
	public String getAuthorization_id() {
		return authorization_id;
	}
	
	/**
	 * Sets the authorization id.
	 *
	 * @param authorization_id the new authorization id
	 */
	public void setAuthorization_id(String authorization_id) {
		this.authorization_id = authorization_id;
	}
	
	/**
	 * Gets the invoices.
	 *
	 * @return the invoices
	 */
	public List<InvoiceSearchResponse_paybc> getInvoices() {
		return invoices;
	}
	
	/**
	 * Sets the invoices.
	 *
	 * @param invoices the new invoices
	 */
	public void setInvoices(List<InvoiceSearchResponse_paybc> invoices) {
		this.invoices = invoices;
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
