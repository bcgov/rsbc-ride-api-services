package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class CreateJobStoredProc.
 * @author hliang
 */
@Service
public class CreateJobStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_CREATE_JOB";
	
	/** The Constant PARAM_IN_P_JOB_ID. */
	private static final String PARAM_IN_P_JOB_ID = "P_JOB_ID";
	
	/** The Constant PARAM_IN_P_NEXT_RUN_DTM. */
	private static final String PARAM_IN_P_NEXT_RUN_DTM = "P_NEXT_RUN_DTM";
	
	/** The Constant PARAM_IN_P_NEW_STATE_CD. */
	private static final String PARAM_IN_P_NEW_STATE_CD = "P_NEW_STATE_CD ";
	
	/** The Constant PARAM_IN_P_JOB_CREATION_USER_ID. */
	private static final String PARAM_IN_P_JOB_CREATION_USER_ID = "P_JOB_CREATION_USER_ID";
	
	/** The Constant PARAM_IN_P_SERVICE_NAME. */
	private static final String PARAM_IN_P_SERVICE_NAME = "P_SERVICE_NAME";
	
	/** The Constant PARAM_IN_p_comments. */
	private static final String PARAM_IN_p_comments = "p_comments";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_P_JOB_ID, Types.VARCHAR);
		SqlParameter pIn1 = new SqlParameter(PARAM_IN_P_NEXT_RUN_DTM, Types.VARCHAR);
		SqlParameter pIn2 = new SqlParameter(PARAM_IN_P_NEW_STATE_CD, Types.VARCHAR);
		SqlParameter pIn3 = new SqlParameter(PARAM_IN_P_JOB_CREATION_USER_ID, Types.VARCHAR);
		SqlParameter pIn4 = new SqlParameter(PARAM_IN_P_SERVICE_NAME, Types.VARCHAR);
		SqlParameter p_comments = new SqlParameter(PARAM_IN_p_comments, Types.VARCHAR);
		SqlParameter p_interface_nm = new SqlParameter(PARAM_IN_P_INTERFACE_NM, Types.VARCHAR);
		SqlParameter[] paramArray = {pIn0, pIn1, pIn2, pIn3, pIn4, p_comments, p_interface_nm};
		return paramArray;
	}

}
