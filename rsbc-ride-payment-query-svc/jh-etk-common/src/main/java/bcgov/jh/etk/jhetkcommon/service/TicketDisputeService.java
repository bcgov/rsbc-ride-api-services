package bcgov.jh.etk.jhetkcommon.service;

import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.disputestatusupdate.DisputeStatusUpdate;
import bcgov.jh.etk.jhetkcommon.model.ticketdispute.TicketDispute;

/**
 * The Class TicketDisputeService.
 * @author HLiang
 */
@Service
public interface TicketDisputeService {
	
	/**
	 * Save ticket dispute.
	 *
	 * @param disputeTicket the dispute ticket
	 * @return true, if successful
	 */
	public Map<String, Integer> saveTicketDispute(final TicketDispute disputeTicket) throws DataIntegrityViolationException, EtkDataAccessException;
	
	/**
	 * Save dispute status update.
	 *
	 * @param disputeStatusUpdate the dispute status update
	 * @return true, if successful
	 */
	public Map<String, Integer> saveDisputeStatusUpdate(final DisputeStatusUpdate disputeStatusUpdate) throws DataIntegrityViolationException, EtkDataAccessException;
	

}
