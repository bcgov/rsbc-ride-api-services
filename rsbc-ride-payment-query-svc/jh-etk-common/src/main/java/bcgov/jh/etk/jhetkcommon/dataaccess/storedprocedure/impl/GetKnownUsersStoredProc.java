package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkKnownUserRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetJobsStoredProc.
 * @author hliang
 */
@Service
public class GetKnownUsersStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_get_known_users";
	
	/** The Constant PARAM_OUT_P_RECORDS. */
	public static final String PARAM_OUT_P_RECORDS = "p_records";
	
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
		SqlOutParameter pRecords1 = new SqlOutParameter(PARAM_OUT_P_RECORDS, Types.REF_CURSOR, new EtkKnownUserRecordMapper());
		SqlParameter[] paramArray = {pRecords1};
		return paramArray;
	}

}
