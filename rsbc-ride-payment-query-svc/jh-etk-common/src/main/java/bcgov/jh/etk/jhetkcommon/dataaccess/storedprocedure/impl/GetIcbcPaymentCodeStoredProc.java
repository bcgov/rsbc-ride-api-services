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
public class GetIcbcPaymentCodeStoredProc extends StoredProcedureBase {

	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_get_icbc_payment_code";
	
	/** The Constant PARAM_IN_P_ICBC_PAYMENT_MESSAGE_CD. */
	private static final String PARAM_IN_P_ICBC_PAYMENT_MESSAGE_CD = "P_ICBC_PAYMENT_MESSAGE_CD";
	
	/** The Constant PARAM_OUT_P_PAYMENT_MESSAGE_DSC. */
	public static final String PARAM_OUT_P_PAYMENT_MESSAGE_DSC = "P_PAYMENT_MESSAGE_DSC";
	
	/** The Constant PARAM_OUT_P_ICBC_PAYMENT_MESSAGE_DSC. */
	public static final String PARAM_OUT_P_ICBC_PAYMENT_MESSAGE_DSC = "P_ICBC_PAYMENT_MESSAGE_DSC";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_P_ICBC_PAYMENT_MESSAGE_CD, Types.INTEGER);
		SqlOutParameter pOut1 = new SqlOutParameter(PARAM_OUT_P_PAYMENT_MESSAGE_DSC, Types.VARCHAR);
		SqlOutParameter pOut2 = new SqlOutParameter(PARAM_OUT_P_ICBC_PAYMENT_MESSAGE_DSC, Types.VARCHAR);
		SqlParameter[] paramArray = {pIn0, pOut1, pOut2};
		return paramArray;
	}

}
