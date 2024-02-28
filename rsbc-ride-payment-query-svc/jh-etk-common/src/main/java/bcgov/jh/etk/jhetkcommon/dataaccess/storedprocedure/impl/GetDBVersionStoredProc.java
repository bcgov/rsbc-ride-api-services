package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetDBVersionStoredProc.
 */
@Service
public class GetDBVersionStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_get_db_version";
	
	/** The Constant PARAM_OUT_P_JOBS. */
	public static final String PARAM_OUT_p_db_version = "p_db_version";
	
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
		SqlOutParameter p_db_version = new SqlOutParameter(PARAM_OUT_p_db_version, Types.VARCHAR);
		SqlParameter[] paramArray = {p_db_version};
		return paramArray;
	}

}
