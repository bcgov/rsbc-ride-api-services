package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.TicketStatisticsDisputeCnt;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;


/**
 * The Class TicketDisputeCntMapper.
 * @author HLiang
 */
public class TicketDisputeCntMapper implements RowMapper<TicketStatisticsDisputeCnt>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public TicketStatisticsDisputeCnt mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketStatisticsDisputeCnt disputeTicketCnt = new TicketStatisticsDisputeCnt();
		if (StringUtils.isNotBlank(rs.getString("ticketType"))) {
			disputeTicketCnt.setTicketType(EnumTicketType.getEnumfromCodeValue(rs.getString("ticketType")));
		}
		disputeTicketCnt.setEventType(rs.getString("eventType"));
		disputeTicketCnt.setTicketCount(rs.getBigDecimal("ticketCnt") == null ? null : rs.getBigDecimal("ticketCnt").floatValue());
		return disputeTicketCnt;
	}

}
