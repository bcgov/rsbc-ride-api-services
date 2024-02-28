package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class CreateTicketKPIDetailsEventStoredProc.
 * @author eliang
 */
@Service
public class CreateTicketKPIDetailsEventStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_create_ticket_kpidetails_event";

	/** The Constant PARAM_IN_P_TICKET_ID. */
	public static final String PARAM_IN_P_TICKET_ID = "P_TICKET_ID";
	
	/** The Constant PARAM_OUT_P_ERROR_CODE. */
	public static final String PARAM_OUT_P_ERROR_CODE = "P_ERROR_CODE";
	
	/** The Constant PARAM_OUT_P_ERROR_DETAILS. */
	public static final String PARAM_OUT_P_ERROR_DETAILS = "P_ERROR_DETAILS";
	
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
		SqlParameter pTicketID = new SqlParameter(PARAM_IN_P_TICKET_ID, Types.INTEGER);
		SqlOutParameter pErrorCode = new SqlOutParameter(PARAM_OUT_P_ERROR_CODE, Types.VARCHAR);
		SqlOutParameter pErrorDetails = new SqlOutParameter(PARAM_OUT_P_ERROR_DETAILS, Types.VARCHAR);
		SqlParameter[] paramArray = {pTicketID, pErrorCode, pErrorDetails};
		return paramArray;
	}

}

