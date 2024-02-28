package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkTicketRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetTicketsByEvtstatesStoredProc.
 * @author eliang
 */
@Service
public class GetTicketsByEvtstatesStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_get_tickets_by_evtstates";
	
	/** The Constant PARAM_IN_P_TICKET_STATES. */
	private static final String PARAM_IN_P_TICKET_STATES = "P_TICKET_STATES";
	
	/** The Constant PARAM_OUT_P_RECORDS. */
	public static final String PARAM_OUT_P_RECORDS = "P_RECORDS";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_P_TICKET_STATES, Types.VARCHAR);
		SqlOutParameter pOut = new SqlOutParameter(PARAM_OUT_P_RECORDS, Types.REF_CURSOR, new EtkTicketRecordMapper());
		SqlParameter[] paramArray = {pIn0, pOut};
		return paramArray;
	}

}
