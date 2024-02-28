package bcgov.jh.etk.jhetkcommon.service.impl;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import bcgov.jh.etk.jhetkcommon.dataaccess.dao.EtkDaoService;
import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.disputestatusupdate.DisputeStatusUpdate;
import bcgov.jh.etk.jhetkcommon.model.ticketdispute.TicketDispute;
import bcgov.jh.etk.jhetkcommon.service.TicketDisputeService;


/**
 * The Class TicketDisputeServiceImpl.
 * @author HLiang
 */
@Component
public class TicketDisputeServiceImpl implements TicketDisputeService {

	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(TicketDisputeServiceImpl.class);

	@Autowired(required = false)
	EtkDaoService etkDao;
	
	/**
	 * Save ticket dispute.
	 *
	 * @param disputeTicket the dispute ticket
	 * @return the long
	 * @throws EtkDataAccessException 
	 */
	public Map<String, Integer> saveTicketDispute(final TicketDispute disputeTicket) throws DataIntegrityViolationException, EtkDataAccessException {
		logger.debug("Save ticket dispute.");
		return etkDao.saveTicketDispute(disputeTicket);
	}
	
	/**
	 * Save dispute status update.
	 *
	 * @param disputeStatusUpdate the dispute status update
	 * @return the long
	 */
	public Map<String, Integer> saveDisputeStatusUpdate(final DisputeStatusUpdate disputeStatusUpdate) throws DataIntegrityViolationException, EtkDataAccessException{
		logger.debug("Save ticket dispute status update.");
		return etkDao.saveDisputeStatusUpdate(disputeStatusUpdate);
	}

}
