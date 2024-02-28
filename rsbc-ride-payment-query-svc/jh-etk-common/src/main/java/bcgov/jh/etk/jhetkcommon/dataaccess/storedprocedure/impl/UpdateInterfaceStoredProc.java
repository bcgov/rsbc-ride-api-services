package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

@Service
public class UpdateInterfaceStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_update_interface";
	
	/** The Constant PARAM_IN_P_INTERFACE_NM. */
	private static final String PARAM_IN_P_INTERFACE_NM = "P_INTERFACE_NM";
	
	/** The Constant PARAM_IN_P_INTERFACE_STATE_CD. */
	private static final String PARAM_IN_P_INTERFACE_STATE_CD = "P_INTERFACE_STATE_CD";
	
	private static final String PARAM_IN_P_UPD_USER_ID = "P_UPD_USER_ID";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_P_INTERFACE_NM, Types.VARCHAR);
		SqlParameter pIn1 = new SqlParameter(PARAM_IN_P_INTERFACE_STATE_CD, Types.VARCHAR);
		SqlParameter pIn2 = new SqlParameter(PARAM_IN_P_UPD_USER_ID, Types.VARCHAR);
		SqlParameter[] paramArray = {pIn0, pIn1, pIn2};
		return paramArray;
	}

}
