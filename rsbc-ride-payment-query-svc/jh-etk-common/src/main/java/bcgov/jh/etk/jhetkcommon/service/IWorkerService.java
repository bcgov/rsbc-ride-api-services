package bcgov.jh.etk.jhetkcommon.service;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;

/**
 * The Interface IWorkerService.
 */
public interface IWorkerService {

	/**
	 * Checks if the interface is stopped.
	 *
	 * @param interfaceCD the interface CD
	 * @return true, if the interface state is null or stopped
	 */
	public boolean isInterfaceStopped (EnumInterface interfaceCD);

}
