package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketViolationStatsPerAgency;

public class EtkAgencyBasedViolationStatsMapper implements RowMapper<EtkStatisticsTicketViolationStatsPerAgency>{

	@Override
	public EtkStatisticsTicketViolationStatsPerAgency mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkStatisticsTicketViolationStatsPerAgency stats = new EtkStatisticsTicketViolationStatsPerAgency();
		stats.setAgencyCD(rs.getString("agency_cd"));
		stats.setCountType(rs.getString("countType"));
		stats.setTotalNumTicketPerCntType(rs.getInt("totalNumTicketPerCntType"));
		
		return stats;
	}

}
