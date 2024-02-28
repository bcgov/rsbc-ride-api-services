package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class CreateTicketStoredProc.
 * @author eliang
 */
@Service
public class CreateTicketStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_CREATE_TICKET";
	
	/** The Constant PARAM_IN_P_SOURCE_XML. */
	private static final String PARAM_IN_P_SOURCE_XML = "P_SOURCE_XML";
	
	/** The Constant PARAM_IN_P_FILE_NM. */
	private static final String PARAM_IN_P_FILE_NM = "P_FILE_NM";
	
	/** The Constant PARAM_IN_P_INTERFACE_NM. */
	private static final String PARAM_IN_P_INTERFACE_NM = "P_INTERFACE_NM";
	
	/** The Constant PARAM_OUT_P_TICKET_ID. */
	public static final String PARAM_OUT_P_TICKET_ID = "P_TICKET_ID";
	
	/** The Constant PARAM_OUT_P_TICKET_NO. */
	public static final String PARAM_OUT_P_TICKET_NO = "P_TICKET_NO";
	
	/** The Constant PARAM_OUT_p_dup_ticket_error_code. */
	public static final String PARAM_OUT_p_dup_ticket_error_code = "p_dup_ticket_error_code";
	
	/** The Constant PARAM_OUT_p_ticket_process_state. */
	public static final String PARAM_OUT_p_ticket_process_state = "p_ticket_process_state";
	
	/** The Constant PARAM_OUT_p_violation_time. */
	public static final String PARAM_OUT_p_violation_time = "p_violation_time";
	
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
		SqlParameter pIn2 = new SqlParameter(PARAM_IN_P_FILE_NM, Types.VARCHAR);
		SqlParameter pIn3 = new SqlParameter(PARAM_IN_P_INTERFACE_NM, Types.VARCHAR);
		SqlOutParameter pTicketID = new SqlOutParameter(PARAM_OUT_P_TICKET_ID, Types.INTEGER);
		SqlOutParameter pTicketNO = new SqlOutParameter(PARAM_OUT_P_TICKET_NO, Types.VARCHAR);
		SqlOutParameter p_dup_ticket_error_code = new SqlOutParameter(PARAM_OUT_p_dup_ticket_error_code, Types.VARCHAR);
		SqlOutParameter p_ticket_process_state = new SqlOutParameter(PARAM_OUT_p_ticket_process_state, Types.VARCHAR);
		SqlOutParameter p_violation_time = new SqlOutParameter(PARAM_OUT_p_violation_time, Types.VARCHAR);
		SqlParameter[] paramArray = {pIn1, pIn2, pIn3, pTicketNO, pTicketID, p_dup_ticket_error_code, p_ticket_process_state, p_violation_time};
		return paramArray;
	}

}
