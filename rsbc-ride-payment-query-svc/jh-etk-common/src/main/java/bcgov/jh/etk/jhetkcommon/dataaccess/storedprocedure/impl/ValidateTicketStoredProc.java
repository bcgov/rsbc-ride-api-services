package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class ValidateTicketStoredProc.
 * @author eliang
 */
@Service
public class ValidateTicketStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_validate_ticket";
	
	/** The Constant PARAM_IN_P_SOURCE_XML. */
	private static final String PARAM_IN_P_SOURCE_XML = "P_SOURCE_XML";
	
	/** The Constant PARAM_OUT_P_ERROR_CODE. */
	public static final String PARAM_OUT_P_ERROR_CODE = "p_error_code";
	
	/** The Constant PARAM_OUT_P_ERROR_DETAILS. */
	public static final String PARAM_OUT_P_ERROR_DETAILS = "p_error_details";
	
	/** The Constant PARAM_OUT_P_CONTRACENTION_NO. */
	public static final String PARAM_OUT_P_CONTRACENTION_NO = "p_error_contraventionNOs";
	
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
		SqlParameter pIn1 = new SqlParameter(PARAM_IN_P_SOURCE_XML, Types.VARCHAR);
		SqlOutParameter pErrorCode = new SqlOutParameter(PARAM_OUT_P_ERROR_CODE, Types.VARCHAR);
		SqlOutParameter pErrorDetails = new SqlOutParameter(PARAM_OUT_P_ERROR_DETAILS, Types.VARCHAR);
		SqlOutParameter pContravetionNOs = new SqlOutParameter(PARAM_OUT_P_CONTRACENTION_NO, Types.VARCHAR);
		
		SqlParameter[] paramArray = {pIn1, pErrorCode, pErrorDetails, pContravetionNOs};
		return paramArray;
	}

}
