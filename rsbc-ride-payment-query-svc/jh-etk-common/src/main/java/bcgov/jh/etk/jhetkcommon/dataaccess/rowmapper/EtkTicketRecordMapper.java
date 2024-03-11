package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.helper.MapHelper;
import bcgov.jh.etk.jhetkcommon.model.EtkTicket;

/**
 * The Class EtkTicketRecordMapper.
 */
public class EtkTicketRecordMapper implements RowMapper<EtkTicket>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk ticket
	 * @throws SQLException the SQL exception
	 */
	@Override
	public EtkTicket mapRow(ResultSet rs, int rowNum) throws SQLException {
		return MapHelper.mapToEtkTicket(rs);
	}

}
