package bcgov.jh.etk.jhetkcommon.model.paymentsvc;

import bcgov.jh.etk.jhetkcommon.model.paymentsvc.ref.Items;

/**
 * The Class InvoiceResponseWrapper.
 */
public class InvoiceResponseWrapper {
	
	/** The items. */
	private Items items;
	
	/** The status code. */
	private String statusCode;
	
	/**
	 * Instantiates a new invoice response wrapper.
	 */
	public InvoiceResponseWrapper() {
		super();
	}
	
	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public Items getItems() {
		return items;
	}
	
	/**
	 * Sets the items.
	 *
	 * @param items the new items
	 */
	public void setItems(Items items) {
		this.items = items;
	}
	
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
}
