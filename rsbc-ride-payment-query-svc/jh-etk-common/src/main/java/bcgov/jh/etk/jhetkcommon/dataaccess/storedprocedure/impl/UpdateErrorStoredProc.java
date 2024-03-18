package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;


/**
 * The Class UpdateErrorStoredProc.
 * @author HLiang
 */
@Service
public class UpdateErrorStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_UPDATE_ERRORS";
	
	/** The Constant PARAM_IN_ERROR_ID. */
	private static final String PARAM_IN_ERROR_ID = "P_ERROR_ID";
	
	/** The Constant PARAM_IN_STATUSCD. */
	private static final String PARAM_IN_STATUSCD = "P_ERROR_STATUS_CD";
	
	/** The Constant PARAM_IN_SUBJECT_USER_ID. */
	private static final String PARAM_IN_SUBJECT_USER_ID = "P_SUBJECT_USER_ID";
	
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
		SqlParameter pErrorID = new SqlParameter(PARAM_IN_ERROR_ID, Types.INTEGER);
		SqlParameter pStatusCD = new SqlParameter(PARAM_IN_STATUSCD, Types.VARCHAR);
		SqlParameter pSubjectUserID = new SqlParameter(PARAM_IN_SUBJECT_USER_ID, Types.VARCHAR);
		SqlParameter[] paramArray = {pErrorID, pStatusCD, pSubjectUserID};
		return paramArray;
	}

}
