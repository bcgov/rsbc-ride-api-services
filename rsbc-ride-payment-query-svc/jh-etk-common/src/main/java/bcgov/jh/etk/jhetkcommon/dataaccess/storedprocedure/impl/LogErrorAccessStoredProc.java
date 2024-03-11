package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class LogErrorAccessStoredProc.
 * @author HLiang
 */
@Service
public class LogErrorAccessStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_CREATE_ERROR_ACCESS";
	
	/** The Constant PARAM_IN_ERROR_ID. */
	private static final String PARAM_IN_ERROR_ID = "P_ERROR_ID";
	
	/** The Constant PARAM_IN_ACCESSING_USERID. */
	private static final String PARAM_IN_ACCESSING_USERID = "P_ACCESSING_USER_ID";
	
	/** The Constant PARAM_IN_AUDIT_EVENT_CD. */
	private static final String PARAM_IN_AUDIT_EVENT_CD = "P_AUDIT_EVENT_CD";
	
	/** The Constant PARAM_OUT_P_AUDIT_EVENT_ID. */
	public static final String PARAM_OUT_P_AUDIT_EVENT_ID = "P_AUDIT_EVENT_ID";
	
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
		SqlParameter pAccessingUserID = new SqlParameter(PARAM_IN_ACCESSING_USERID, Types.VARCHAR);
		SqlParameter pAuditEventCD = new SqlParameter(PARAM_IN_AUDIT_EVENT_CD, Types.VARCHAR);
		SqlOutParameter pOutID = new SqlOutParameter(PARAM_OUT_P_AUDIT_EVENT_ID, Types.INTEGER);
		SqlParameter[] paramArray = {pErrorID, pAccessingUserID, pAuditEventCD, pOutID};
		return paramArray;
	}

}
