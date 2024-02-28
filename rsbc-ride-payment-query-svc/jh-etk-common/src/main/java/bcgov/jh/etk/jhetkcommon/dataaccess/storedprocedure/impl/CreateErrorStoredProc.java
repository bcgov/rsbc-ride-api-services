package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class CreateErrorStoredProc.
 * @author hliang
 */
@Service
public class CreateErrorStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_CREATE_ERROR";
	
	/** The Constant PARAM_IN_P_ERROR_CATEGORY_CD. */
	private static final String PARAM_IN_P_ERROR_CATEGORY_CD = "P_ERROR_CATEGORY_CD";
	
	/** The Constant PARAM_IN_P_ERROR_SEVERITY_LEVEL_CD. */
	private static final String PARAM_IN_P_ERROR_SEVERITY_LEVEL_CD = "P_ERROR_SEVERITY_LEVEL_CD";
	
	/** The Constant PARAM_IN_P_TICKET_NO. */
	private static final String PARAM_IN_P_TICKET_NO = "P_TICKET_NO";
	
	/** The Constant PARAM_IN_P_CONTRAVENTION_NO. */
	private static final String PARAM_IN_P_CONTRAVENTION_NO = "P_CONTRAVENTION_NO";
	
	/** The Constant PARAM_IN_P_DETAILS_TXT. */
	private static final String PARAM_IN_P_DETAILS_TXT = "P_DETAILS_TXT";
	
	/** The Constant PARAM_IN_P_SERVICE_NM. */
	private static final String PARAM_IN_P_SERVICE_NM = "P_SERVICE_NM";
	
	/** The Constant PARAM_IN_P_PRIME_TXT. */
	private static final String PARAM_IN_P_PRIME_TXT = "P_PRIME_TXT";
	
	/** The Constant PARAM_IN_P_ICBC_TXT. */
	private static final String PARAM_IN_P_ICBC_TXT = "P_ICBC_TXT";
	
	/** The Constant PARAM_IN_P_JUSTIN_TXT. */
	private static final String PARAM_IN_P_JUSTIN_TXT = "P_JUSTIN_TXT";
	
	/** The Constant PARAM_IN_P_ERROR_CD. */
	private static final String PARAM_IN_P_ERROR_CD = "P_ERROR_CD";
	
	/** The Constant PARAM_IN_P_ticket_id. */
	private static final String PARAM_IN_P_ticket_id = "P_ticket_id";
	
	/** The Constant PARAM_OUT_P_ERROR_ID. */
	public static final String PARAM_OUT_P_ERROR_ID = "P_ERROR_ID";
	
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
		SqlParameter P_ticket_id = new SqlParameter(PARAM_IN_P_ticket_id, Types.INTEGER);
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_P_ERROR_CATEGORY_CD, Types.VARCHAR);
		SqlParameter pIn1 = new SqlParameter(PARAM_IN_P_ERROR_SEVERITY_LEVEL_CD, Types.VARCHAR);
		SqlParameter pIn2 = new SqlParameter(PARAM_IN_P_TICKET_NO, Types.VARCHAR);
		SqlParameter pIn3 = new SqlParameter(PARAM_IN_P_CONTRAVENTION_NO, Types.VARCHAR);
		SqlParameter pIn5 = new SqlParameter(PARAM_IN_P_DETAILS_TXT, Types.VARCHAR);
		SqlParameter pIn6 = new SqlParameter(PARAM_IN_P_SERVICE_NM, Types.VARCHAR);
		SqlParameter pIn7 = new SqlParameter(PARAM_IN_P_PRIME_TXT, Types.VARCHAR);
		SqlParameter pIn8 = new SqlParameter(PARAM_IN_P_ICBC_TXT, Types.VARCHAR);
		SqlParameter pIn9 = new SqlParameter(PARAM_IN_P_JUSTIN_TXT, Types.VARCHAR);
		SqlParameter pIn11 = new SqlParameter(PARAM_IN_P_ERROR_CD, Types.VARCHAR);
		SqlOutParameter pErrorID = new SqlOutParameter(PARAM_OUT_P_ERROR_ID, Types.INTEGER);
		SqlParameter[] paramArray = {P_ticket_id, pIn0, pIn1, pIn2, pIn3, pIn5, pIn6, pIn7, pIn8, pIn9, pIn11, pErrorID};
		return paramArray;
	}

}
