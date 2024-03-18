package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkIssuanceReconMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class CreateIssuanceReconStoredProc.
 */
@Service
public class ValidateIssuanceReconStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_validate_issuance_recon";
	
	/** The Constant PARAM_IN_P_RECORDS. */
	private static final String PARAM_IN_P_RECON_FILE_NAME = "p_recon_file_name";
	
	/** The Constant PARAM_OUT_P_MISMATCH_RECORDS. */
	public static final String PARAM_OUT_P_MISMATCH_RECORDS = "P_MISMATCH_RECORDS";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_P_RECON_FILE_NAME, Types.VARCHAR);
		SqlOutParameter pOut = new SqlOutParameter(PARAM_OUT_P_MISMATCH_RECORDS, Types.REF_CURSOR, new EtkIssuanceReconMapper());
		SqlParameter[] paramArray = {pIn0, pOut};
		return paramArray;
	}

}
