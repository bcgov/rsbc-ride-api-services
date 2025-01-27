package bcgov.jh.etk.jhetkcommon.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.model.EtkInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.service.IWorkerService;
import bcgov.jh.etk.jhetkcommon.util.InterfaceStateUtil;

/**
 * The Class RepublishService.
 */
@Service
public class WorkerService implements IWorkerService {
    /** The interface state util. */
	@Autowired
	InterfaceStateUtil interfaceStateUtil;

	/**
	 * Checks if the interface is stopped.
	 *
	 * @param interfaceCD the interface CD
	 * @return true, if the interface state is null or stopped
	 */
	@Override
	public boolean isInterfaceStopped (EnumInterface interfaceCD) {
		EtkInterfaceState interfaceState = interfaceStateUtil.getInterfaceState(interfaceCD);
		if (interfaceState == null || 
			interfaceState.getInterfaceState() == null || 
			EnumInterfaceState.STOPPED.equals(interfaceState.getInterfaceState())) {
			return true;
		}
		return false;
	}
}
