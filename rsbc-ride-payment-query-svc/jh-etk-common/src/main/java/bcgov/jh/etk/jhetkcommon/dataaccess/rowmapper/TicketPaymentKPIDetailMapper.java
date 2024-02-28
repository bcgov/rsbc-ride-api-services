package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.TicketPaymentKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class VPHReportEventMapper.
 * @author HLiang
 */
public class TicketPaymentKPIDetailMapper implements RowMapper<TicketPaymentKPIDetails> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public TicketPaymentKPIDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketPaymentKPIDetails paymentKPI = new TicketPaymentKPIDetails();
		paymentKPI.setEVENT_ID(BigInteger.valueOf(rs.getInt("EVENT_ID")));
		paymentKPI.setEVENT_DTM(rs.getTimestamp("EVENT_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("EVENT_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		paymentKPI.setEVENT_TYPE_CD(EnumEventType.getEnumfromCodeValue(rs.getString("EVENT_TYPE_CD")));
		paymentKPI.setTICKET_NO(rs.getString("TICKET_NO"));
		paymentKPI.setCOUNT_NBR(rs.getString("COUNT_NBR"));
		
		paymentKPI.setPAYMENT_AMT(rs.getDouble("PAYMENT_AMT"));
		paymentKPI.setENT_DTM(rs.getTimestamp("ENT_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("ENT_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		paymentKPI.setENT_USER_ID(rs.getString("ENT_USER_ID"));
		paymentKPI.setUPD_DTM(rs.getTimestamp("UPD_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("UPD_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		paymentKPI.setUPD_USER_ID(rs.getString("UPD_USER_ID"));
		paymentKPI.setPAYMENT_CARD_TYPE_TXT(rs.getString("PAYMENT_CARD_TYPE_TXT"));
		paymentKPI.setPAYMENT_TICKET_TYPE_CD(rs.getString("PAYMENT_TICKET_TYPE_CD") == null ? null : EnumTicketType.getEnumfromCodeValue(rs.getString("PAYMENT_TICKET_TYPE_CD")));
		paymentKPI.setRECEIPT_NBR(rs.getString("RECEIPT_NBR"));
		paymentKPI.setPAYMENT_TRANSACTION_ID(rs.getString("transaction_id"));
		return paymentKPI;
	}

}
