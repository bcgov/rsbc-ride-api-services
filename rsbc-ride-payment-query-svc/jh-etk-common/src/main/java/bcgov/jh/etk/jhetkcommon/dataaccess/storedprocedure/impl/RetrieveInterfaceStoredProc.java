package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetIcbcPaymentCodeStoredProc.
 * @author eliang
 */
@Service
public class RetrieveInterfaceStoredProc extends StoredProcedureBase {

	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_retrieve_interface";
	
	private static final String PARAM_IN_P_INTERFACE_NM = "P_INTERFACE_NM";
	
	public static final String PARAM_OUT_P_LAST_UPDATED_DTM = "P_LAST_UPDATED_DTM";
	
	public static final String PARAM_OUT_P_INTERFACE_STATE_CD = "P_INTERFACE_STATE_CD";
	
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
		SqlOutParameter pOut1 = new SqlOutParameter(PARAM_OUT_P_INTERFACE_STATE_CD, Types.VARCHAR);
		SqlOutParameter pOut2 = new SqlOutParameter(PARAM_OUT_P_LAST_UPDATED_DTM, Types.TIMESTAMP);
		SqlParameter[] paramArray = {pIn0, pOut1, pOut2};
		return paramArray;
	}

}
