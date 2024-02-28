package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkAgencyCodeText;

/**
 * The Class EtkAgencyCodeTextMapper.
 */
public class EtkAgencyCodeTextMapper implements RowMapper<EtkAgencyCodeText>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk statistics ticket MRE stats per agency
	 * @throws SQLException the SQL exception
	 */
	@Override
	public EtkAgencyCodeText mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkAgencyCodeText stats = new EtkAgencyCodeText();
		stats.setAgencyCD(rs.getString("agency_cd"));
		stats.setAgencyText(rs.getString("agency_txt"));
		
		return stats;
	}

}
