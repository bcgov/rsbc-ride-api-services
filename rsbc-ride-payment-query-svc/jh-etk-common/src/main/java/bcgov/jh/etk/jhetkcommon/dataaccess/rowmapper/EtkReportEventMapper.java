package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkReportEvents;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class VPHReportEventMapper.
 * @author HLiang
 */
public class EtkReportEventMapper implements RowMapper<EtkReportEvents> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EtkReportEvents mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkReportEvents event = new EtkReportEvents();
		event.setEVENT_DTM(rs.getTimestamp("EVENT_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("EVENT_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		event.setEVENT_ID(BigInteger.valueOf(rs.getInt("EVENT_ID")));
		event.setEVENT_TYPE_CD(EnumEventType.getEnumfromCodeValue(rs.getString("EVENT_TYPE_CD")));
		event.setTICKET_NO(rs.getString("TICKET_NO"));
		event.setENT_DTM(rs.getTimestamp("ENT_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("ENT_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		event.setENT_USER_ID(rs.getString("ENT_USER_ID"));
		event.setUPD_DTM(rs.getTimestamp("UPD_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("UPD_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		event.setUPD_USER_ID(rs.getString("UPD_USER_ID"));
		event.setCOUNT_NBR(rs.getString("COUNT_NBR"));
		
		event.setDISPUTE_ACTION_CD(rs.getString("DISPUTE_ACTION_CD"));
		event.setDISPUTE_ACTION_DT(DateUtil.DateToString(rs.getDate("DISPUTE_ACTION_DT"), DateUtil.YYYY_MM_DD));
		return event;
	}

}
