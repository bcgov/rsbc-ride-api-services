package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

@Service
public class SetTicketStatusToSentStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_set_ticket_status_to_sent";
	
	/** The Constant PARAM_IN_P_TICKET_NO. */
	private static final String PARAM_IN_P_TICKET_ID = "P_TICKET_ID";
	
	/** The Constant PARAM_IN_P_INTERFACE_NM. */
	private static final String PARAM_IN_P_INTERFACE_NM = "P_INTERFACE_NM";
	
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
		SqlParameter[] paramArray = {P_TICKET_ID, P_INTERFACE_NM};
		return paramArray;
	}

}
