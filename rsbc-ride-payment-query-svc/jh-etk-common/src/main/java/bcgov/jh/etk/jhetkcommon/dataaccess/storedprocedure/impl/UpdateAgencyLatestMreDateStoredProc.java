package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class UpdateAgencyLatestMreDateStoredProc.
 */
@Service
public class UpdateAgencyLatestMreDateStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_update_agency_latest_mre_date";
	
	/** The Constant PARAM_IN_p_agency_cd. */
	private static final String PARAM_IN_p_agency_cd = "p_agency_cd";
	
		
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_p_agency_cd, Types.VARCHAR);
		SqlParameter[] paramArray = {pIn0};
		return paramArray;
	}

}
