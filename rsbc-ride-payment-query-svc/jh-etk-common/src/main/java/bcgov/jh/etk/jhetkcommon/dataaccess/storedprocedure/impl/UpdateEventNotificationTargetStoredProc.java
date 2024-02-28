package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetActiveEventNotificationTargetsStoredProc.
 */
@Service
public class UpdateEventNotificationTargetStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_update_event_target";
	
	public static final String PARAM_IN_P_TARGET_ID = "p_target_id";
	
	public static final String PARAM_IN_P_LAST_SENT_EVENT_ID = "p_last_sent_event_id";
	
	public static final String PARAM_IN_P_LAST_SENT_DTM = "p_last_sent_dtm";
	
	public static final String PARAM_IN_LAST_NOTICIATION_SUCCEEDED = "p_last_notification_succeeded_yn";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_P_TARGET_ID, Types.INTEGER);
		SqlParameter pIn1 = new SqlParameter(PARAM_IN_P_LAST_SENT_EVENT_ID, Types.INTEGER);
		SqlParameter pIn2 = new SqlParameter(PARAM_IN_P_LAST_SENT_DTM, Types.TIMESTAMP_WITH_TIMEZONE);
		SqlParameter pIn3 = new SqlParameter(PARAM_IN_LAST_NOTICIATION_SUCCEEDED, Types.VARCHAR);
		SqlParameter[] paramArray = {pIn0, pIn1, pIn2, pIn3};
		return paramArray;
	}

}
