package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.AuditRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

@Service
public class GetAuditsByTicketNOStoredProc extends StoredProcedureBase {

	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_get_audits";
	
	private static final String PARAM_IN_p_ticket_no = "p_ticket_no";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_p_ticket_no, Types.VARCHAR);
		SqlOutParameter pRecords = new SqlOutParameter(PARAM_OUT_P_RECORDS, Types.REF_CURSOR, new AuditRecordMapper());
		SqlParameter[] paramArray = {pIn0, pRecords};
		return paramArray;
	}

}
