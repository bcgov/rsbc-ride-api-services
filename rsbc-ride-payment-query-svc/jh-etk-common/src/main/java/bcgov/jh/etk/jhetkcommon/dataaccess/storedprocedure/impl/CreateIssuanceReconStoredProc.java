package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class CreateIssuanceReconStoredProc.
 */
@Service
public class CreateIssuanceReconStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_create_issuance_recon";
	
	/** The Constant PARAM_IN_P_RECORDS. */
	private static final String PARAM_IN_P_RECORDS = "p_records";
	
	/** The Constant PARAM_OUT_P_DUPS. */
	public static final String PARAM_OUT_P_DUPS = "p_dups";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_P_RECORDS, Types.ARRAY);
		SqlOutParameter pDups = new SqlOutParameter(PARAM_OUT_P_DUPS, Types.VARCHAR);

		SqlParameter[] paramArray = {pIn0, pDups};
		return paramArray;
	}

}
