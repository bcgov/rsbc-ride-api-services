package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsDisputeTicketStatsPerAgency;

/**
 * The Class EtkAgencyBasedDisputeStatsMapper.
 */
public class EtkAgencyBasedDisputeStatsMapper implements RowMapper<EtkStatisticsDisputeTicketStatsPerAgency>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk statistics ticket MRE stats per agency
	 * @throws SQLException the SQL exception
	 */
	@Override
	public EtkStatisticsDisputeTicketStatsPerAgency mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkStatisticsDisputeTicketStatsPerAgency stats = new EtkStatisticsDisputeTicketStatsPerAgency();
		stats.setAgencyCD(rs.getString("agency_cd"));
		stats.setAct_section(rs.getString("act_section"));
		stats.setTotalNumDisputes(rs.getInt("ticket_count"));
		
		return stats;
	}

}
