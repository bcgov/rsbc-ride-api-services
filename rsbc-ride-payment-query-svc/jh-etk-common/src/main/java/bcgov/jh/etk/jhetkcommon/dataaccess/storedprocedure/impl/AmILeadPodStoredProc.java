package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class AmILeadPodStoredProc.
 */
@Service
public class AmILeadPodStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_am_i_lead_pod";
	
	private static final String PARAM_IN_p_component_name = "p_component_name";
	
	private static final String PARAM_IN_p_pod_info = "p_pod_info";
	
	public static final String PARAM_OUT_results = "results";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_p_component_name, Types.VARCHAR);
		SqlParameter pIn1 = new SqlParameter(PARAM_IN_p_pod_info, Types.VARCHAR);
		SqlOutParameter pIn2 = new SqlOutParameter(PARAM_OUT_results, Types.BOOLEAN);
		SqlParameter[] paramArray = {pIn0, pIn1, pIn2};
		return paramArray;
	}

}
