package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkReportViolationDetails;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;


/**
 * The Class VPHReportViolationDetailsMapper.
 */
public class EtkReportViolationDetailsMapper implements RowMapper<EtkReportViolationDetails> {

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EtkReportViolationDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkReportViolationDetails v = new EtkReportViolationDetails();
		v.setACT_CD(rs.getString("ACT_CD"));
		v.setFINE_AMT(rs.getString("FINE_AMT"));
		v.setCOUNT_NBR(rs.getInt("COUNT_NBR"));
		v.setSECTION_DSC(rs.getString("SECTION_DSC"));
		v.setWORDING_NBR(rs.getString("WORDING_NBR"));
		v.setENT_DTM(rs.getTimestamp("ENT_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("ENT_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		v.setENT_USER_ID(rs.getString("ENT_USER_ID"));
		v.setUPD_DTM(rs.getTimestamp("UPD_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("UPD_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		
		v.setUPD_USER_ID(rs.getString("UPD_USER_ID"));
		v.setSECTION_TXT(rs.getString("SECTION_TXT"));
		v.setTICKET_ID(BigInteger.valueOf(rs.getInt("TICKET_ID")));
		return v;
	}

}
