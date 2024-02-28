package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkQueuedDisputeRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetDisputeRecordsStoredProc.
 */
@Service
public class GetDisputeRecordsStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_get_dispute_records";
	
	/** The Constant PARAM_IN_p_data_type_cd. */
	public static final String PARAM_IN_p_data_type_cd = "p_data_type_cd";
	
	/** The Constant PARAM_IN_p_process_state_type_cd. */
	public static final String PARAM_IN_p_process_state_type_cd = "p_process_state_type_cd";
	
	/** The Constant PARAM_OUT_P_RECORD. */
	public static final String PARAM_OUT_P_RECORD = "P_RECORD";
	
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
		SqlParameter p_data_type_cd = new SqlParameter(PARAM_IN_p_data_type_cd, Types.VARCHAR);
		SqlParameter p_process_state_type_cd = new SqlParameter(PARAM_IN_p_process_state_type_cd, Types.VARCHAR);
		SqlOutParameter pOut = new SqlOutParameter(PARAM_OUT_P_RECORD, Types.REF_CURSOR, new EtkQueuedDisputeRecordMapper());
		SqlParameter[] paramArray = {p_data_type_cd, p_process_state_type_cd, pOut};
		return paramArray;
	}

}
