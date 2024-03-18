package bcgov.jh.etk.jhetkcommon.model.paymentsvc;

import java.util.List;

import bcgov.jh.etk.jhetkcommon.model.paymentsvc.ref.LineDocType;

/**
 * The Class InvoiceSearchResponse.
 */
public class InvoiceSearchResponse_paybc {
	
	/** The trx number. */
	private String trx_number;
	
	/** The amount to apply. */
	private String amount_to_apply;
	
	/** The lines. */
	private List<LineDocType> lines;
	
	/**
	 * Gets the trx number.
	 *
	 * @return the trx number
	 */
	public String getTrx_number() {
		return trx_number;
	}
	
	/**
	 * Sets the trx number.
	 *
	 * @param trx_number the new trx number
	 */
	public void setTrx_number(String trx_number) {
		this.trx_number = trx_number;
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
	
	/**
	 * Gets the lines.
	 *
	 * @return the lines
	 */
	public List<LineDocType> getLines() {
		return lines;
	}
	
	/**
	 * Sets the lines.
	 *
	 * @param lines the new lines
	 */
	public void setLines(List<LineDocType> lines) {
		this.lines = lines;
	}
}
