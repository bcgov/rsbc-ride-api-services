package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class UpdateErrorsByTicketIDStoredProc.java.
 */
@Service
public class UpdateErrorsByTicketIDStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_update_errors_by_ticketid";
	
	/** The Constant PARAM_IN_P_ticketid. */
	private static final String PARAM_IN_P_ticketid = "p_ticketID";
	
	private static final String PARAM_IN_P_SUBJECT_USER_ID = "P_SUBJECT_USER_ID";
	
	private static final String PARAM_IN_P_ERROR_STATUS_CD = "P_ERROR_STATUS_CD";

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
		SqlParameter P_FILENAME = new SqlParameter(PARAM_IN_P_ticketid, Types.INTEGER);
		SqlParameter P_SUBJECT_USER_ID = new SqlParameter(PARAM_IN_P_SUBJECT_USER_ID, Types.VARCHAR);
		SqlParameter P_ERROR_STATUS_CD = new SqlParameter(PARAM_IN_P_ERROR_STATUS_CD, Types.VARCHAR);
		SqlParameter[] paramArray = {P_FILENAME, P_SUBJECT_USER_ID, P_ERROR_STATUS_CD};
		return paramArray;
	}

}
