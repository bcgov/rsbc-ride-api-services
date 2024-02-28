package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkTicketRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetNextQueuedTicketStoredProc.
 * @author eliang
 */
@Service
public class GetNextQueuedTicketStoredProc extends StoredProcedureBase {

	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_GET_NEXT_QUEUED_TICKET";
	
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
		SqlOutParameter pOut = new SqlOutParameter(PARAM_OUT_P_RECORDS, Types.REF_CURSOR, new EtkTicketRecordMapper());
		SqlParameter[] paramArray = {pOut};
		return paramArray;
	}

}
