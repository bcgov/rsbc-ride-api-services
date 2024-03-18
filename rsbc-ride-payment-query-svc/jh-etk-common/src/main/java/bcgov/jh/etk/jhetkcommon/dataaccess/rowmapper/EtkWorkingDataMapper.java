package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkWorkingData;

/**
 * The Class EtkWorkingDataMapper.
 * @author HLiang
 */
public class EtkWorkingDataMapper implements RowMapper<EtkWorkingData> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EtkWorkingData mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkWorkingData wd = new EtkWorkingData();
		wd.setDataID(BigInteger.valueOf(rs.getInt("DATA_ID")));
		wd.setComponentName(rs.getString("component_name"));
		wd.setDataName(rs.getString("data_name"));
		wd.setDataValue(rs.getString("data_value"));
		wd.setLockNumber(rs.getInt("lock_nbr"));
		return wd;
	}

}
