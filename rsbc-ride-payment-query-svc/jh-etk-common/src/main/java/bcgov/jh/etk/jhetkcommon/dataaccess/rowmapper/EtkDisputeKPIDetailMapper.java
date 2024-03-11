package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.TicketDisputeKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class VPHReportKPIDetailMapper.
 * @author HLiang
 */
public class EtkDisputeKPIDetailMapper implements RowMapper<TicketDisputeKPIDetails> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public TicketDisputeKPIDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		TicketDisputeKPIDetails kpiDetails = new TicketDisputeKPIDetails();
		kpiDetails.setCompressedSection(rs.getString("compressed_section"));
		if (rs.getTimestamp("created_dtmz") != null) {
			kpiDetails.setCreatedDT(DateUtil.localDateTimeToString(rs.getTimestamp("created_dtmz").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		}
		
		kpiDetails.setContraventionNO(rs.getString("contravention_no"));
		kpiDetails.setCountActRegulation(rs.getString("count_act_regulation"));
		kpiDetails.setDisputeType(rs.getString("dispute_type"));
		kpiDetails.setOffenderTypeDescription(rs.getString("offender_type_description"));
		kpiDetails.setTicktedAmount(rs.getDouble("ticketed_amt"));
		kpiDetails.setKpiID(rs.getLong("kpi_id"));
		kpiDetails.setEventID(rs.getLong("event_id"));
		
		kpiDetails.setDisputeActionCD(rs.getString("dispute_action_cd"));
		kpiDetails.setDisputeActionDT(DateUtil.DateToString(rs.getDate("dispute_action_dt"), DateUtil.YYYY_MM_DD));
		
		kpiDetails.setEvent_type_cd(EnumEventType.getEnumfromCodeValue(rs.getString("event_type_cd")));
		
		return kpiDetails;
	}
}
