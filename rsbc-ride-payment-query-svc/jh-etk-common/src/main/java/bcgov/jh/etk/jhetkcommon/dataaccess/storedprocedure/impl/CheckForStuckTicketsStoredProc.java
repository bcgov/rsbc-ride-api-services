package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkQueuedDisputeRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkTicketRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;


/**
 * The Class CheckForStuckTicketsStoredProc.
 * @author HLiang
 */
@Service
public class CheckForStuckTicketsStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_check_for_stuck_tickets";
	
	/** The Constant PARAM_OUT_RECORDS. */
	public static final String PARAM_p_stuck_tickets_issuance = "p_stuck_tickets_issuance";
	
	/** The Constant PARAM_p_stuck_tickets_dispute. */
	public static final String PARAM_p_stuck_tickets_dispute = "p_stuck_tickets_dispute";
	
	/** The Constant PARAM_p_stuck_tickets_disputeSU. */
	public static final String PARAM_p_stuck_tickets_disputeSU = "p_stuck_tickets_disputeSU";
	
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
		SqlOutParameter p_stuck_tickets_issuance = new SqlOutParameter(PARAM_p_stuck_tickets_issuance, Types.REF_CURSOR, new EtkTicketRecordMapper());
		SqlOutParameter p_stuck_tickets_dispute = new SqlOutParameter(PARAM_p_stuck_tickets_dispute, Types.REF_CURSOR, new EtkQueuedDisputeRecordMapper());
		SqlOutParameter p_stuck_tickets_disputeSU = new SqlOutParameter(PARAM_p_stuck_tickets_disputeSU, Types.REF_CURSOR, new EtkQueuedDisputeRecordMapper());
		SqlParameter[] paramArray = {p_stuck_tickets_issuance, p_stuck_tickets_dispute, p_stuck_tickets_disputeSU};
		return paramArray;
	}

}
