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
public class CreateDisputeRecordStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_create_dispute_record";
	
	/** The Constant PARAM_IN_p_payload. */
	private static final String PARAM_IN_p_payload = "p_payload";
	
	/** The Constant PARAM_IN_p_contravention_no. */
	private static final String PARAM_IN_p_contravention_no = "p_contravention_no";
	
	/** The Constant PARAM_IN_p_dispute_status_cd. */
	private static final String PARAM_IN_p_dispute_status_cd = "p_dispute_status_cd";
	
	/** The Constant PARAM_IN_p_dispute_status_dt. */
	private static final String PARAM_IN_p_dispute_status_dt = "p_dispute_status_dt";
	
	/** The Constant PARAM_IN_p_count_act_regulation. */
	private static final String PARAM_IN_p_count_act_regulation = "p_count_act_regulation";
	
	/** The Constant PARAM_IN_P_dispute_type. */
	private static final String PARAM_IN_P_dispute_type = "P_dispute_type";
	
	/** The Constant PARAM_IN_P_ticketed_amt. */
	private static final String PARAM_IN_P_ticketed_amt = "P_ticketed_amt";
	
	/** The Constant PARAM_IN_P_compressed_section. */
	private static final String PARAM_IN_P_compressed_section = "P_compressed_section";
	
	/** The Constant PARAM_IN_p_event_id. */
	public static final String PARAM_IN_p_event_id = "p_event_id";
	
	/** The Constant PARAM_IN_p_process_id. */
	public static final String PARAM_IN_p_process_id = "p_process_id";
	
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
		SqlParameter p_payload = new SqlParameter(PARAM_IN_p_payload, Types.VARCHAR);
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_p_contravention_no, Types.VARCHAR);
		SqlParameter pIn1 = new SqlParameter(PARAM_IN_p_dispute_status_cd, Types.VARCHAR);
		SqlParameter pIn2 = new SqlParameter(PARAM_IN_p_dispute_status_dt, Types.DATE);
		SqlParameter pIn3 = new SqlParameter(PARAM_IN_P_dispute_type, Types.VARCHAR);
		SqlParameter pIn4 = new SqlParameter(PARAM_IN_P_ticketed_amt, Types.REAL);
		SqlParameter pIn5 = new SqlParameter(PARAM_IN_P_compressed_section, Types.VARCHAR);
		SqlParameter pIn6 = new SqlParameter(PARAM_IN_p_count_act_regulation, Types.VARCHAR);
		SqlOutParameter pEventID = new SqlOutParameter(PARAM_IN_p_event_id, Types.INTEGER);
		SqlOutParameter pProcessID = new SqlOutParameter(PARAM_IN_p_process_id, Types.INTEGER);
		SqlParameter[] paramArray = {p_payload, pIn0, pIn1, pIn2, pIn3, pIn4, pIn5, pIn6, pEventID, pProcessID};
		return paramArray;
	}

}
