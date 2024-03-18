package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;


/**
 * The Class LogErrorCommentStoredProc.
 * @author HLiang
 */
@Service
public class LogErrorCommentStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_CREATE_ERROR_COMMENT";
	
	/** The Constant PARAM_IN_ERROR_ID. */
	private static final String PARAM_IN_ERROR_ID = "P_ERROR_ID";
	
	/** The Constant PARAM_IN_AUTHOR_USERID. */
	private static final String PARAM_IN_AUTHOR_USERID = "P_AUTHOR_USER_ID";
	
	/** The Constant PARAM_IN_COMMENT. */
	private static final String PARAM_IN_COMMENT = "P_DETAILS_TXT";
	
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
		SqlParameter pAuthorUserID = new SqlParameter(PARAM_IN_AUTHOR_USERID, Types.VARCHAR);
		SqlParameter pComment = new SqlParameter(PARAM_IN_COMMENT, Types.VARCHAR);
		SqlParameter[] paramArray = {pErrorID, pAuthorUserID, pComment};
		return paramArray;
	}

}
