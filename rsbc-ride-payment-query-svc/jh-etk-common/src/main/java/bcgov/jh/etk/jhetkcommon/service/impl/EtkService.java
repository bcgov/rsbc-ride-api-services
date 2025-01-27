package bcgov.jh.etk.jhetkcommon.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.dao.EtkDaoService;
import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.service.IEtkService;

/**
 * The Class EtkService.
 * @author HLiang
 */
@Service
public class EtkService implements IEtkService {
	
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(EtkService.class);
	
	/** The vph dao. */
	@Autowired(required = false)
	EtkDaoService etkDao;

	/**
	 * Gets the icbc payment code.
	 *
	 * @param icbcPaymentMsgCode the icbc payment msg code
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public Map<String, String> GetIcbcPaymentCode(String icbcPaymentMsgCode) throws EtkDataAccessException {
		logger.debug("Get ICBC payment code details: {}", icbcPaymentMsgCode);
		return etkDao.GetIcbcPaymentCode(icbcPaymentMsgCode);
	}

	/**
	 * Record query event.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception.
	 */
	@Override
	public void RecordQueryEvent(final String ticketNO) throws EtkDataAccessException{
		logger.debug("Record query event, {}", ticketNO);
		etkDao.RecordQueryEvent(ticketNO);
	}

	/**
	 * Update interface.
	 *
	 * @param interfaceNM the interface NM
	 * @param interfaceStateCD the interface state CD
	 * @param updUserID the upd user ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public void UpdateInterface(final EnumInterface interfaceNM, final EnumInterfaceState interfaceStateCD, final String updUserID) throws EtkDataAccessException {
		logger.debug("Update interface, {}", interfaceNM);
		etkDao.UpdateInterface(interfaceNM, interfaceStateCD, updUserID);
	}

	/**
	 * Gets the DB version.
	 *
	 * @return the DB version
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Override
	public String getDBVersion() throws EtkDataAccessException {
		return etkDao.getDBVersion();
	}
}
