package bcgov.jh.etk.jhetkcommon.model.payment;

/**
 * The Class PaymentReceiptRequest.
 */
public class PaymentReceiptRequest_ICBC {
	
	/** The receipt number. */
	private String receiptNumber;
	
	/** The transaction id. */
	private String transactionId;
	
	/** The contravention number. */
	private String contraventionNumber;
	
	/** The transaction amount. */
	private double transactionAmount;
	
	/** The transaction date. */
	private String transactionDate;
	
	/** The payment method. */
	private String paymentMethod;
	
	/** The authorization number. */
	private String authorizationNumber;
	
	/** The payment date. */
	private String paymentDate;
	
	/**
	 * Gets the receipt number.
	 *
	 * @return the receipt number
	 */
	public String getReceiptNumber() {
		return receiptNumber;
	}
	
	/**
	 * Sets the receipt number.
	 *
	 * @param receiptNumber the new receipt number
	 */
	public void setReceiptNumber(String receiptNumber) {
		this.receiptNumber = receiptNumber;
	}
	
	/**
	 * Gets the transaction id.
	 *
	 * @return the transaction id
	 */
	public String getTransactionId() {
		return transactionId;
	}
	
	/**
	 * Sets the transaction id.
	 *
	 * @param transactionId the new transaction id
	 */
	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
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
	 * Gets the transaction amount.
	 *
	 * @return the transaction amount
	 */
	public double getTransactionAmount() {
		return transactionAmount;
	}
	
	/**
	 * Sets the transaction amount.
	 *
	 * @param transactionAmount the new transaction amount
	 */
	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	
	/**
	 * Gets the transaction date.
	 *
	 * @return the transaction date
	 */
	public String getTransactionDate() {
		return transactionDate;
	}
	
	/**
	 * Sets the transaction date.
	 *
	 * @param transactionDate the new transaction date
	 */
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	/**
	 * Gets the payment method.
	 *
	 * @return the payment method
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}
	
	/**
	 * Sets the payment method.
	 *
	 * @param paymentMethod the new payment method
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
	
	/**
	 * Gets the authorization number.
	 *
	 * @return the authorization number
	 */
	public String getAuthorizationNumber() {
		return authorizationNumber;
	}
	
	/**
	 * Sets the authorization number.
	 *
	 * @param authorizationNumber the new authorization number
	 */
	public void setAuthorizationNumber(String authorizationNumber) {
		this.authorizationNumber = authorizationNumber;
	}
	
	/**
	 * Gets the payment date.
	 *
	 * @return the payment date
	 */
	public String getPaymentDate() {
		return paymentDate;
	}
	
	/**
	 * Sets the payment date.
	 *
	 * @param paymentDate the new payment date
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
}
