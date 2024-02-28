package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketMREStatsPerAgency;

/**
 * The Class EtkAgencyBasedMREStatsMapper.
 */
public class EtkAgencyBasedMREStatsMapper implements RowMapper<EtkStatisticsTicketMREStatsPerAgency>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk statistics ticket MRE stats per agency
	 * @throws SQLException the SQL exception
	 */
	@Override
	public EtkStatisticsTicketMREStatsPerAgency mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkStatisticsTicketMREStatsPerAgency stats = new EtkStatisticsTicketMREStatsPerAgency();
		stats.setAgencyCD(rs.getString("agency_cd"));
		stats.setMreMinorVersion(rs.getString("mreMinorVersion"));
		stats.setTotalNumTicketPerMREMinorVersion(rs.getInt("totalNumMREMinorVersion"));
		stats.setViolationDtmLastIssuedTicket(rs.getString("violation_dtm_lastIssuedTicket"));
		return stats;
	}

}
