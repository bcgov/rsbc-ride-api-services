package bcgov.jh.etk.jhetkcommon.model.paymentsvc.ref;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The Class Items.
 */
public class Items {
	
	/** The items lst. */
	@JsonProperty("items")
	private List<Item> itemsLst;

	/**
	 * Gets the items.
	 *
	 * @return the items
	 */
	public List<Item> getItems() {
		return itemsLst;
	}

	/**
	 * Sets the items.
	 *
	 * @param items the new items
	 */
	public void setItems(List<Item> items) {
		this.itemsLst = items;
	}
}
