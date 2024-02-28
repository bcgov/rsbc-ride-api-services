package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

@Service
public class RecordPaymentEventStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_record_payment_event";
	
	/** The Constant PARAM_IN_P_TICKET_NO. */
	private static final String PARAM_IN_P_TICKET_NO = "P_TICKET_NO";
	
	private static final String PARAM_IN_P_AMOUNT = "P_AMOUNT";
	
	/** The Constant P_TICKET_TYPE_CD. */
	private static final String PARAM_P_TICKET_TYPE_CD = "P_TICKET_TYPE_CD";
	
	/** The Constant P_CARD_TYPE. */
	private static final String PARAM_P_CARD_TYPE = "P_CARD_TYPE";
	
	/** The Constant PARAM_P_RECEIPT_NUMBER. */
	private static final String PARAM_P_RECEIPT_NUMBER = "P_RECEIPT_NUMBER";
	
	/** The Constant PARAM_p_transaction_id. */
	private static final String PARAM_p_transaction_id = "p_transaction_id";
	
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
		SqlParameter P_TICKET_NO = new SqlParameter(PARAM_IN_P_TICKET_NO, Types.VARCHAR);
		SqlParameter P_AMOUNT = new SqlParameter(PARAM_IN_P_AMOUNT, Types.DECIMAL);
		SqlParameter P_TICKET_TYPE_CD = new SqlParameter(PARAM_P_TICKET_TYPE_CD, Types.VARCHAR);
		SqlParameter P_CARD_TYPE = new SqlParameter(PARAM_P_CARD_TYPE, Types.VARCHAR);
		SqlParameter P_RECEIPT_NUMBER = new SqlParameter(PARAM_P_RECEIPT_NUMBER, Types.VARCHAR);
		SqlParameter P_RECEIPT_TXN_NUMBER = new SqlParameter(PARAM_p_transaction_id, Types.VARCHAR);
		SqlParameter[] paramArray = {P_TICKET_NO, P_AMOUNT, P_TICKET_TYPE_CD, P_CARD_TYPE, P_RECEIPT_NUMBER, P_RECEIPT_TXN_NUMBER};
		return paramArray;
	}

}
