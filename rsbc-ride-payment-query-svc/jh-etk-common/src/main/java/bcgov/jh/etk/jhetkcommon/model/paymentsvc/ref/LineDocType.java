package bcgov.jh.etk.jhetkcommon.model.paymentsvc.ref;

/**
 * The Class LineDocType.
 */
public class LineDocType {
	
	/** The line number. */
	private String line_number;
	
	/** The amount to apply. */
	private String amount_to_apply;
	
	/**
	 * Gets the line number.
	 *
	 * @return the line number
	 */
	public String getLine_number() {
		return line_number;
	}
	
	/**
	 * Sets the line number.
	 *
	 * @param line_number the new line number
	 */
	public void setLine_number(String line_number) {
		this.line_number = line_number;
	}
	
	/**
	 * Gets the amount to apply.
	 *
	 * @return the amount to apply
	 */
	public String getAmount_to_apply() {
		return amount_to_apply;
	}
	
	/**
	 * Sets the amount to apply.
	 *
	 * @param amount_to_apply the new amount to apply
	 */
	public void setAmount_to_apply(String amount_to_apply) {
		this.amount_to_apply = amount_to_apply;
	}
}
