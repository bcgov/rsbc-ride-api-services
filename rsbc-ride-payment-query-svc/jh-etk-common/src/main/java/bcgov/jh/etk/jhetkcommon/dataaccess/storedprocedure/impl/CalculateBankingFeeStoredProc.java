package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.PaymentCardBankingFeeRateMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.TicketBankingFeeMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class CalculateBankingFeeStoredProc.
 */
@Service
public class CalculateBankingFeeStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_calculate_banking_fee";
	
	/** The Constant PARAM_IN_p_start_date. */
	public static final String PARAM_IN_p_start_date = "p_start_date";
	
	/** The Constant PARAM_IN_p_end_date. */
	public static final String PARAM_IN_p_end_date = "p_end_date";
	
	/** The Constant PARAM_OUT_p_records_monthly_card_txn. */
	public static final String PARAM_OUT_p_records_monthly_card_txn = "p_records_monthly_card_txn";
	
	/** The Constant PARAM_OUT_p_records_monthly_total_txn. */
	public static final String PARAM_OUT_p_records_monthly_total_txn = "p_records_monthly_total_txn";
	
	/** The Constant PARAM_OUT_p_records_card_bank_fee. */
	public static final String PARAM_OUT_p_records_card_bank_fee = "p_records_card_bank_fee";
	
	/** The Constant PARAM_OUT_p_records_ticket_total_txn. */
	public static final String PARAM_OUT_p_records_ticket_total_txn = "p_records_ticket_total_txn";
	
	/** The Constant PARAM_OUT_p_bank_fee_rate. */
	public static final String PARAM_OUT_p_bank_fee_rate = "p_bank_fee_rate";

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
		SqlParameter p_start_date = new SqlParameter(PARAM_IN_p_start_date, Types.VARCHAR);
		SqlParameter p_end_date = new SqlParameter(PARAM_IN_p_end_date, Types.VARCHAR);
		SqlOutParameter p_records_monthly_card_txn = new SqlOutParameter(PARAM_OUT_p_records_monthly_card_txn, Types.REF_CURSOR, new TicketBankingFeeMapper());
		SqlOutParameter p_records_monthly_total_txn = new SqlOutParameter(PARAM_OUT_p_records_monthly_total_txn, Types.REF_CURSOR, new TicketBankingFeeMapper());
		SqlOutParameter p_records_card_bank_fee = new SqlOutParameter(PARAM_OUT_p_records_card_bank_fee, Types.REF_CURSOR, new TicketBankingFeeMapper());
		SqlOutParameter p_records_ticket_total_txn = new SqlOutParameter(PARAM_OUT_p_records_ticket_total_txn, Types.REF_CURSOR, new TicketBankingFeeMapper());
		SqlOutParameter p_bank_fee_rate = new SqlOutParameter(PARAM_OUT_p_bank_fee_rate, Types.REF_CURSOR, new PaymentCardBankingFeeRateMapper());
		SqlParameter[] paramArray = {p_start_date, p_end_date, p_records_monthly_card_txn, p_records_monthly_total_txn, p_records_card_bank_fee, p_records_ticket_total_txn, p_bank_fee_rate};
		return paramArray;
	}

}
