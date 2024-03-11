package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkError;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCategory;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorSeverity;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorStatus;

public class EtkErrorRecordMapper implements RowMapper<EtkError>{

	@Override
	public EtkError mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkError error = new EtkError();
		if (rs.getTimestamp("RECEIVED_DTM") != null) {
			error.setCreateDT(rs.getTimestamp("RECEIVED_DTM").toLocalDateTime());
		}
		error.setCorrelationTicketNumber(rs.getString("TICKET_NO"));
		error.setErrorID(new Long(rs.getString("ERROR_ID")));
		error.setErrorSeverity(rs.getString("ERROR_SEVERITY_LEVEL_CD") == null ? null : EnumErrorSeverity.getEnumfromCodeValue(rs.getString("ERROR_SEVERITY_LEVEL_CD")));
		error.setErrorStatus(rs.getString("ERROR_STATUS_TYPE_CD") == null ? null : EnumErrorStatus.getEnumfromCodeValue(rs.getString("ERROR_STATUS_TYPE_CD")));
		if (!EnumErrorStatus.NEW.equals(error.getErrorStatus()) && !EnumErrorStatus.VIEWED.equals(error.getErrorStatus())) {
			error.setAssignedRole(rs.getString("SUBJECT_USER_ID"));
		}
		error.setErrorCategory(rs.getString("ERROR_CATEGORY_CD") == null ? null : EnumErrorCategory.getEnumfromCodeValue(rs.getString("ERROR_CATEGORY_CD")));
		error.setPRIMEText(rs.getString("PRIME_TXT"));
		error.setICBCText(rs.getString("ICBC_TXT"));
		error.setJUSTINText(rs.getString("JUSTIN_TXT"));
		if (rs.getDate("UPD_DTM") != null) {
			error.setUpdateDT(rs.getTimestamp("UPD_DTM").toLocalDateTime());
		}
		error.setUpdateUserID(rs.getString("UPD_USER_ID"));
		error.setErrorSource(rs.getString("SERVICE_NM"));
		
		error.setErrorDescription(rs.getString("DETAILS_TXT"));
		
		error.setErrorCode(EnumErrorCode.getEnumfromCodeValue(rs.getString("ERROR_CD")));
		
		error.setCorrelationTicketID(rs.getString("TICKET_ID") == null ? null : new Integer(rs.getString("TICKET_ID")));
		
		return error;
	}

}
