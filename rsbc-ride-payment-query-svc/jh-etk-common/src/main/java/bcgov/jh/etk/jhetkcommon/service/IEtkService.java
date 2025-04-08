/*
 * 
 */
package bcgov.jh.etk.jhetkcommon.service;

import java.util.Map;

import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;


/**
 * The Interface IEtkService.
 */
public interface IEtkService {

	/**
	 * Gets the icbc payment code.
	 *
	 * @param icbcPaymentMsgCode the icbc payment msg code
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String GetPaymentMessage(final String icbcPaymentMsgCode) throws EtkDataAccessException;

	/**
	 * Record query event.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void RecordQueryEvent(final String ticketNO) throws EtkDataAccessException;
}





