package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkDisputeFinding;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class VPHReportKPIDetailMapper.
 * @author HLiang
 */
public class EtkDisputeFindingKPIDetailMapper implements RowMapper<EtkDisputeFinding> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EtkDisputeFinding mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkDisputeFinding kpiDetails = new EtkDisputeFinding();
		kpiDetails.setEvent_id(rs.getInt("event_id"));
		kpiDetails.setAppearance_dt(DateUtil.DateToString(rs.getDate("appearance_dt"), DateUtil.YYYY_MM_DD));
		kpiDetails.setContravention_no(rs.getString("contravention_no"));
		kpiDetails.setFinding_cd(rs.getString("finding_cd"));
		kpiDetails.setFinding_desc(rs.getString("finding_desc"));
		kpiDetails.setJustin_record_id(rs.getString("justin_record_id"));
		if (rs.getTimestamp("ent_dtm") != null) {
			kpiDetails.setEnt_dtm(rs.getTimestamp("ent_dtm").toLocalDateTime());
		}
		
		return kpiDetails;
	}
}
