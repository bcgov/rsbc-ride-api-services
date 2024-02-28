package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.KeyValue;

/**
 * The Class EtkAgencyBasedDisputeStatsMapper.
 */
public class KeyValueMapper implements RowMapper<KeyValue>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk statistics ticket MRE stats per agency
	 * @throws SQLException the SQL exception
	 */
	@Override
	public KeyValue mapRow(ResultSet rs, int rowNum) throws SQLException {
		KeyValue kv = new KeyValue();
		kv.setKey(rs.getString("keyStr"));
		kv.setValue(rs.getString("valStr"));
		
		return kv;
	}

}
