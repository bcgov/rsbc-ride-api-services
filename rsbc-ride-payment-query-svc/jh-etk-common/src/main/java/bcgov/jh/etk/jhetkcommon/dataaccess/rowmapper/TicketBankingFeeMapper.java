package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.TicketDBPaymentTxnDetail;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumCardType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;

/**
 * The Class TicketBankingFeeMapper.
 */
public class TicketBankingFeeMapper implements RowMapper<TicketDBPaymentTxnDetail>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk ticket
	 * @throws SQLException the SQL exception
	 */
	@Override
	public TicketDBPaymentTxnDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketDBPaymentTxnDetail paymentTxn = new TicketDBPaymentTxnDetail();
		paymentTxn.setPayment_amt(rs.getDouble("payment_amt"));
		paymentTxn.setPayment_card_type_txt(rs.getString("payment_card_type_txt") == null ? null : EnumCardType.getEnumfromCodeDescription(rs.getString("payment_card_type_txt").trim()));
		paymentTxn.setPayment_ticket_type_cd(rs.getString("payment_ticket_type_cd") == null ? null : EnumTicketType.getEnumfromCodeValue(rs.getString("payment_ticket_type_cd").trim()));
		paymentTxn.setQmonth(rs.getString("qmonth") == null ? null : Integer.valueOf(rs.getString("qmonth")));
		paymentTxn.setTxn_cnt(rs.getInt("txn_cnt"));
		return paymentTxn;
	}
}
