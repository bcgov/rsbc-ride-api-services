package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsMREDatesPerAgency;

/**
 * The Class EtkAgencyBasedMREDatesMapper.
 */
public class EtkAgencyBasedMREDatesMapper implements RowMapper<EtkStatisticsMREDatesPerAgency>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk statistics ticket MRE stats per agency
	 * @throws SQLException the SQL exception
	 */
	@Override
	public EtkStatisticsMREDatesPerAgency mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkStatisticsMREDatesPerAgency stats = new EtkStatisticsMREDatesPerAgency();
		stats.setAgencyCD(rs.getString("enforcement_jurisdiction_cd"));
		stats.setFirstTicketIssuedDateUsingLatestMRE(rs.getDate("first_issued_date"));
		stats.setLatestMreMinorVersion(rs.getString("latest_mre_version"));
		stats.setResetDate(rs.getDate("reset_date"));
		return stats;
	}

}
