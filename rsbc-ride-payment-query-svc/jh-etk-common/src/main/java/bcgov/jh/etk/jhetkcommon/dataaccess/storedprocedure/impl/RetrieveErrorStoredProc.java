package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkErrorRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;


/**
 * The Class RetrieveErrorStoredProc.
 * @author HLiang
 */
@Service
public class RetrieveErrorStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_RETRIEVE_ERRORS";
	
	/** The Constant PARAM_IN_ERROR_ID. */
	private static final String PARAM_IN_ERROR_ID = "P_ERROR_ID";
	
	/** The Constant PARAM_IN_STARTDATE. */
	private static final String PARAM_IN_STARTDATE = "P_STARTDATE";
	
	/** The Constant PARAM_IN_ENDDATE. */
	private static final String PARAM_IN_ENDDATE = "P_ENDDATE";
	
	/** The Constant PARAM_IN_STATUS_CD, comma seperated statuses codes */
	private static final String PARAM_IN_STATUS_CDs = "P_STATUS_CDs";
	
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
		SqlParameter pErrorID = new SqlParameter(PARAM_IN_ERROR_ID, Types.INTEGER);
		SqlParameter pStartDate = new SqlParameter(PARAM_IN_STARTDATE, Types.VARCHAR);
		SqlParameter pEndDate = new SqlParameter(PARAM_IN_ENDDATE, Types.VARCHAR);
		SqlParameter pStatusesCD = new SqlParameter(PARAM_IN_STATUS_CDs, Types.VARCHAR);
		SqlOutParameter pRecords = new SqlOutParameter(PARAM_OUT_RECORDS, Types.REF_CURSOR, new EtkErrorRecordMapper());
		SqlParameter[] paramArray = {pErrorID, pStartDate, pEndDate, pStatusesCD, pRecords};
		return paramArray;
	}

}
