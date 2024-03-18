package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkTicketRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;


/**
 * The Class RetrieveTicketStoredProc.
 * @author HLiang
 */
@Service
public class RetrieveTicketStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_RETRIEVE_TICKET";
	
	/** The Constant PARAM_IN_TICKET_ID. */
	private static final String PARAM_IN_TICKET_ID = "P_TICKET_ID";
	
	/** The Constant PARAM_OUT_RECORDS. */
	public static final String PARAM_OUT_RECORDS = "P_RECORDS";
	
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
		SqlParameter pTicketID = new SqlParameter(PARAM_IN_TICKET_ID, Types.INTEGER);
		SqlOutParameter pRecords = new SqlOutParameter(PARAM_OUT_RECORDS, Types.REF_CURSOR, new EtkTicketRecordMapper());
		SqlParameter[] paramArray = {pTicketID, pRecords};
		return paramArray;
	}

}
