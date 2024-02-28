package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.TicketDisputeCntMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkTicketStatusCntMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;


/**
 * The Class GetTicketCntStoredProc.
 */
@Service
public class GetTicketCntStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_GET_TICKET_CNT";
	
	/** The Constant PARAM_OUT_P_TICKET_CNT_TODAY. */
	public static final String PARAM_OUT_P_TICKET_CNT_TODAY = "P_TICKET_CNT_TODAY";
	
	/** The Constant PARAM_OUT_P_TICKET_CNT_THIS_MONTH. */
	public static final String PARAM_OUT_P_TICKET_CNT_NOT_SENT = "P_TICKET_CNT_NOT_SENT";
	
	/** The Constant PARAM_OUT_P_ERROR_CNT_NOT_RESOLVED. */
	public static final String PARAM_OUT_P_ERROR_CNT_NOT_RESOLVED = "P_ERROR_CNT_NOT_RESOLVED";
	
	/** The Constant PARAM_OUT_P_TICKET_CNT_STATUS. */
	public static final String PARAM_OUT_P_TICKET_CNT_STATUS = "P_TICKET_CNT_STATUS";
	
	/** The Constant PARAM_OUT_p_dispute_cnt. */
	public static final String PARAM_OUT_p_dispute_cnt = "p_dispute_cnt";

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
	/* (non-Javadoc)
	 * @see ca.bc.gov.pssg.vph.sc.dataaccess.storedprocedure.StoredProcedureBase#setParameters()
	 */
	@Override
	protected SqlParameter[] getParameters() {
		SqlOutParameter pRecords1 = new SqlOutParameter(PARAM_OUT_P_TICKET_CNT_TODAY, Types.VARCHAR);
		SqlOutParameter pRecords2 = new SqlOutParameter(PARAM_OUT_P_TICKET_CNT_NOT_SENT, Types.VARCHAR);
		SqlOutParameter pRecords3 = new SqlOutParameter(PARAM_OUT_P_ERROR_CNT_NOT_RESOLVED, Types.VARCHAR);
		SqlOutParameter pRecords4 = new SqlOutParameter(PARAM_OUT_P_TICKET_CNT_STATUS, Types.REF_CURSOR, new EtkTicketStatusCntMapper());
		SqlOutParameter pRecords5 = new SqlOutParameter(PARAM_OUT_p_dispute_cnt, Types.REF_CURSOR, new TicketDisputeCntMapper());
		SqlParameter[] paramArray = {pRecords1, pRecords2, pRecords3, pRecords4, pRecords5};
		return paramArray;
	}

}
