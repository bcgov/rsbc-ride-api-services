package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkJobRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetJobsStoredProc.
 * @author hliang
 */
@Service
public class GetJobsStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_GET_JOBS";
	
	/** The Constant PARAM_IN_P_JOBID. */
	private static final String PARAM_IN_P_JOBID = "P_JOB_ID";
	
	/** The Constant PARAM_OUT_P_JOBS. */
	public static final String PARAM_OUT_P_JOBS = "P_JOBS";
	
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
		SqlParameter pRecords0 = new SqlParameter(PARAM_IN_P_JOBID, Types.VARCHAR);
		SqlOutParameter pRecords1 = new SqlOutParameter(PARAM_OUT_P_JOBS, Types.REF_CURSOR, new EtkJobRecordMapper());
		SqlParameter[] paramArray = {pRecords0, pRecords1};
		return paramArray;
	}

}
