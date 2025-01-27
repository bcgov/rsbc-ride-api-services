/*
 * 
 */
package bcgov.jh.etk.jhetkcommon.dataaccess.dao;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.ETKStoredProcedure;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CreateErrorStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetDBVersionStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetIcbcPaymentCodeStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.LogErrorCommentStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RecordQueryEventStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RetrieveInterfaceStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateInterfaceStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateTicketProcessStateByTicketIDStoredProc;
import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;

/**
 * The Class VPHDao.
 * @author HLiang
 */
@Repository
public class EtkDao {

	/** The log error comment stored proc. */
	@Autowired
	private LogErrorCommentStoredProc logErrorCommentStoredProc;

	/** The create error stored proc. */
	@Autowired
	private CreateErrorStoredProc createErrorStoredProc;

	/** The get icbc payment code stored proc. */
	@Autowired
	private GetIcbcPaymentCodeStoredProc getIcbcPaymentCodeStoredProc;

	/** The record query event stored proc. */
	@Autowired
	private RecordQueryEventStoredProc recordQueryEventStoredProc;
	
	/** The retrieve interface stored proc. */
	@Autowired
	private RetrieveInterfaceStoredProc retrieveInterfaceStoredProc;
	
	/** The update interface stored proc. */
	@Autowired
	private UpdateInterfaceStoredProc updateInterfaceStoredProc;

	@Autowired
	private GetDBVersionStoredProc getDBVersionStoredProc;

	@Autowired
	private UpdateTicketProcessStateByTicketIDStoredProc updateTicketProcessStateByTicketIDStoredProc;

	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(EtkDao.class);

	/**
	 * Log user comment.
	 *
	 * @param errorID the error ID
	 * @param authorUserID the author user ID
	 * @param comments the comments
	 */
	@Transactional
	public void logUserComment(final String errorID, final String authorUserID, final String comments) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure) logErrorCommentStoredProc.getStoredProc();
		sp.execute(new Integer(errorID), authorUserID, comments);
	}

	/**
	 * Creates the error stored proc.
	 *
	 * @param ticketID the ticket ID
	 * @param errorCategoryCD the error category CD
	 * @param errorSeverityLevel the error severity level
	 * @param ticketNO the ticket NO
	 * @param contraventionNO the contravention NO
	 * @param detailTxt the detail txt
	 * @param serviceNM the service NM
	 * @param primeTxt the prime txt
	 * @param ICBCTxt the ICBC txt
	 * @param justinTxt the justin txt
	 * @param errorCD the error CD
	 * @return the integer
	 */
	@Transactional
	public Integer createErrorStoredProc(final Integer ticketID, final String errorCategoryCD, final String errorSeverityLevel, final String ticketNO, 
		final String contraventionNO, final String detailTxt, final String serviceNM, final String primeTxt,
		final String ICBCTxt, final String justinTxt, final String errorCD) {
	
		ETKStoredProcedure sp = (ETKStoredProcedure)createErrorStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ticketID, errorCategoryCD, errorSeverityLevel, ticketNO, contraventionNO, detailTxt, 
				serviceNM, primeTxt, ICBCTxt, justinTxt, errorCD);
		if (result == null) {
			return null;
		}
		return (Integer) result.get(CreateErrorStoredProc.PARAM_OUT_P_ERROR_ID);

	}

	/**
	 * Gets the icbc payment code.
	 *
	 * @param icbcPaymentMsgCode the icbc payment msg code
	 * @return the map
	 */
	public Map<String, String> GetIcbcPaymentCode(final String icbcPaymentMsgCode) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure)getIcbcPaymentCodeStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(new Integer(icbcPaymentMsgCode));
		if (result == null) {
			return null;
		}
		String icbcPaymentMessageDsc = (String) result.get(GetIcbcPaymentCodeStoredProc.PARAM_OUT_P_ICBC_PAYMENT_MESSAGE_DSC);
		String paymentMessageDsc = (String) result.get(GetIcbcPaymentCodeStoredProc.PARAM_OUT_P_PAYMENT_MESSAGE_DSC);
		Map<String, String> returnObj = new HashMap<>();
		returnObj.put(Const.KEY_ICBC_PAYMENT_MESSAGE_DSC, icbcPaymentMessageDsc);
		returnObj.put(Const.KEY_PAYMENT_MESSAGE_DSC, paymentMessageDsc);
		return returnObj;
	}

	/**
	 * Record query event.
	 *
	 * @param ticketNO the ticket NO
	 */
	public void RecordQueryEvent(final String ticketNO){
		
		ETKStoredProcedure sp = (ETKStoredProcedure)recordQueryEventStoredProc.getStoredProc();
		
		sp.execute(ticketNO);
	}

	/**
	 * Retrieve interface.
	 *
	 * @param interfaceNM the interface NM
	 * @return the map
	 */
	public Map<String, Object> RetrieveInterface(final String interfaceNM) {
		ETKStoredProcedure sp = (ETKStoredProcedure)retrieveInterfaceStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(interfaceNM);
		if (result == null) {
			return null;
		}
		
		String interfaceStateCD = (String) result.get(RetrieveInterfaceStoredProc.PARAM_OUT_P_INTERFACE_STATE_CD);
		Timestamp lastUpdatedDTM = (Timestamp) result.get(RetrieveInterfaceStoredProc.PARAM_OUT_P_LAST_UPDATED_DTM);
		
		Map<String, Object> returnObj = new HashMap<>();
		returnObj.put(Const.KEY_INTEFACE_STATE_CD, EnumInterfaceState.getEnumfromCodeValue(interfaceStateCD));
		returnObj.put(Const.KEY_INTEFACE_LAST_UPDATE_DTM, lastUpdatedDTM == null ? null : lastUpdatedDTM.toLocalDateTime());
		return returnObj;
	}
	
	/**
	 * Update interface.
	 *
	 * @param interfaceNM the interface NM
	 * @param interfaceStateCD the interface state CD
	 * @param updUserID the upd user ID
	 */
	public void UpdateInterface(final EnumInterface interfaceNM, final EnumInterfaceState interfaceStateCD, final String updUserID) {
		ETKStoredProcedure sp = (ETKStoredProcedure)updateInterfaceStoredProc.getStoredProc();
		sp.execute(interfaceNM.getCodeValue(), interfaceStateCD.getCodeValue(), updUserID);
	}

	/**
	 * Gets the DB version.
	 *
	 * @return the DB version
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String getDBVersion() throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getDBVersionStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		
		String dbVersion = (String)result.get(GetDBVersionStoredProc.PARAM_OUT_p_db_version);
		return dbVersion;
	}

	/**
	 * Update ticket process state by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @param interfaceNM the interface NM
	 * @param ticketProcesState the ticket proces state
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void UpdateTickeProcessStateByTicketID(final Integer ticketID, final EnumInterface interfaceNM, final EnumProcessState ticketProcesState) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)updateTicketProcessStateByTicketIDStoredProc.getStoredProc();
		sp.execute(ticketID, interfaceNM.getCodeValue(), ticketProcesState.getCodeValue());
	}
}
