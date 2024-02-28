package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkTicketRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;


/**
 * The Class RetrieveTicketByTicketNOStoredProc.
 * @author HLiang
 */
@Service
public class RetrieveTicketByTicketNOStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_retrieve_ticket_by_ticket_no";
	
	/** The Constant PARAM_IN_ERROR_ID. */
	private static final String PARAM_IN_TICKET_NO = "P_TICKET_NO";
	
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

	/* (non-Javadoc)
	 * @see ca.bc.gov.pssg.vph.sc.dataaccess.storedprocedure.StoredProcedureBase#setParameters()
	 */
	@Override
	protected SqlParameter[] getParameters() {
		SqlParameter pTicketNO = new SqlParameter(PARAM_IN_TICKET_NO, Types.VARCHAR);
		SqlOutParameter pRecords = new SqlOutParameter(PARAM_OUT_RECORDS, Types.REF_CURSOR, new EtkTicketRecordMapper());
		SqlParameter[] paramArray = {pTicketNO, pRecords};
		return paramArray;
	}

}
