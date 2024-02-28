package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.helper.MapHelper;
import bcgov.jh.etk.jhetkcommon.model.EtkReportKPIDetails;

/**
 * The Class VPHReportKPIDetailMapper.
 * @author HLiang
 */
public class EtkTicketKPIDetailMapper implements RowMapper<EtkReportKPIDetails> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EtkReportKPIDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkReportKPIDetails kpiDetails = MapHelper.mapToEtkReportKPIDetails(rs);
		
		return kpiDetails;
	}

}
