package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EventNotificationTargetRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetActiveEventNotificationTargetsStoredProc.
 */
@Service
public class GetActiveEventNotificationTargetsStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_get_active_event_targets";
	
	/** The Constant PARAM_OUT_P_RECORDS. */
	public static final String PARAM_OUT_P_RECORDS = "p_targets";
	
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
		SqlOutParameter pRecords = new SqlOutParameter(PARAM_OUT_P_RECORDS, Types.REF_CURSOR, new EventNotificationTargetRecordMapper());
		SqlParameter[] paramArray = {pRecords};
		return paramArray;
	}

}
