package bcgov.jh.etk.jhetkcommon.model.paymentsvc.ref;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Item.
 */
@Setter
@Getter
public class Item {
	
	/** The selected invoice. */
	private keyValue selected_invoice;
	
	/** The open invoices for site. */
	private keyValue open_invoices_for_site;

}
