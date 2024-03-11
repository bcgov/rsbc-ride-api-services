package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

@Service
public class UpdateTicketProcessStateByTicketIDStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_update_ticket_process_state_by_ticketid";
	
	/** The Constant PARAM_IN_P_TICKET_NO. */
	private static final String PARAM_IN_P_TICKET_ID = "P_TICKET_ID";
	
	/** The Constant PARAM_IN_P_INTERFACE_NM. */
	private static final String PARAM_IN_P_INTERFACE_NM = "P_INTERFACE_NM";
	
	/** The Constant PARAM_IN_p_ticket_process_state. */
	private static final String PARAM_IN_p_ticket_process_state = "p_ticket_process_state";
	
	/**
	 * Gets the stored proc name.
	 *
	 * @return the stored proc
	 */
	@Override
	protected String getStoredProcName() {
		return NAME_STORE_PROCEDURE;
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the sql parameter[]
	 */
	@Override
	protected SqlParameter[] getParameters() {
		SqlParameter P_TICKET_ID = new SqlParameter(PARAM_IN_P_TICKET_ID, Types.INTEGER);
		SqlParameter P_INTERFACE_NM = new SqlParameter(PARAM_IN_P_INTERFACE_NM, Types.VARCHAR);
		SqlParameter p_ticket_process_state = new SqlParameter(PARAM_IN_p_ticket_process_state, Types.VARCHAR);
		SqlParameter[] paramArray = {P_TICKET_ID, P_INTERFACE_NM, p_ticket_process_state};
		return paramArray;
	}

}
