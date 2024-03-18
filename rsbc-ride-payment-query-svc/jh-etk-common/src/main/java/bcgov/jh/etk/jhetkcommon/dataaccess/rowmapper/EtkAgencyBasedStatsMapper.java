package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketStatsPerAgency;

public class EtkAgencyBasedStatsMapper implements RowMapper<EtkStatisticsTicketStatsPerAgency>{

	@Override
	public EtkStatisticsTicketStatsPerAgency mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkStatisticsTicketStatsPerAgency stats = new EtkStatisticsTicketStatsPerAgency();
		stats.setDateOfFirstTicketIssued(rs.getDate("firstTicketViolationDate"));
		stats.setEnforcementAgencyCD(rs.getString("agency_cd"));
		stats.setTotalNoOfTicketsIssued(rs.getInt("totalNumTicket"));
		
		return stats;
	}

}
