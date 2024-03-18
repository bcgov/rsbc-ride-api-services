package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.PaymentCardBankingFeeRateDetail;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumCardType;

/**
 * The Class TicketBankingFeeMapper.
 */
public class PaymentCardBankingFeeRateMapper implements RowMapper<PaymentCardBankingFeeRateDetail>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk ticket
	 * @throws SQLException the SQL exception
	 */
	@Override
	public PaymentCardBankingFeeRateDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		PaymentCardBankingFeeRateDetail bankFeeRate = new PaymentCardBankingFeeRateDetail();
		bankFeeRate.setPaymentCardType(rs.getString("type_dsc") == null ? null : EnumCardType.getEnumfromCodeDescription(rs.getString("type_dsc").trim()));
		bankFeeRate.setPciBcmTxnFee(rs.getDouble("pci_bcm_txn_fee"));
		bankFeeRate.setBankFee(rs.getDouble("bank_fee"));
		bankFeeRate.setBankTxnFee(rs.getDouble("bank_txn_fee"));
		bankFeeRate.setBcmTxnFee(rs.getDouble("bcm_txn_fee"));
		return bankFeeRate;
	}
}
