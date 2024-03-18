package bcgov.jh.etk.jhetkcommon.model;

import java.time.LocalDateTime;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;


/**
 * The Class VPHInterfaceState.
 * @author HLiang
 */
public class EtkInterfaceState implements Comparable<EtkInterfaceState>{
	
	/** The interface state. */
	private EnumInterfaceState interfaceState;
	
	/** The interface name. */
	private EnumInterface interfaceName;
	
	/** The last updated date time. */
	private LocalDateTime lastUpdatedDateTime;
	
	
	/**
	 * Gets the last updated date time.
	 *
	 * @return the last updated date time
	 */
	public LocalDateTime getLastUpdatedDateTime() {
		return DateUtil.toPacificLDT(lastUpdatedDateTime);
	}

	/**
	 * Sets the last updated date time.
	 *
	 * @param lastUpdatedDateTime the new last updated date time
	 */
	public void setLastUpdatedDateTime(LocalDateTime lastUpdatedDateTime) {
		this.lastUpdatedDateTime = lastUpdatedDateTime;
	}

	/**
	 * Gets the interface state.
	 *
	 * @return the interface state
	 */
	public EnumInterfaceState getInterfaceState() {
		return interfaceState;
	}
	
	/**
	 * Sets the interface state.
	 *
	 * @param interfaceState the new interface state
	 */
	public void setInterfaceState(EnumInterfaceState interfaceState) {
		this.interfaceState = interfaceState;
	}
	
	/**
	 * Gets the interface name.
	 *
	 * @return the interface name
	 */
	public EnumInterface getInterfaceName() {
		return interfaceName;
	}
	
	/**
	 * Sets the interface name.
	 *
	 * @param interfaceName the new interface name
	 */
	public void setInterfaceName(EnumInterface interfaceName) {
		this.interfaceName = interfaceName;
	}

	@Override
	public int compareTo(EtkInterfaceState state) {
		return (this.getInterfaceName().getDisplayOrder() - state.getInterfaceName().getDisplayOrder());
	}
	
}
