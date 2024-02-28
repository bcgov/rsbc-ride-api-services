package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class UpdateDisputeRecordStoredProc.
 */
@Service
public class UpdateDisputeRecordStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_update_dispute_process_state";
	
	/** The Constant PARAM_IN_p_process_id. */
	private static final String PARAM_IN_p_process_id = "p_process_id";
	
	/** The Constant PARAM_IN_p_process_state. */
	private static final String PARAM_IN_p_process_state = "p_process_state";
	
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
		SqlParameter p_process_id = new SqlParameter(PARAM_IN_p_process_id, Types.INTEGER);
		SqlParameter p_process_state = new SqlParameter(PARAM_IN_p_process_state, Types.VARCHAR);
		SqlParameter[] paramArray = {p_process_id, p_process_state};
		return paramArray;
	}

}
