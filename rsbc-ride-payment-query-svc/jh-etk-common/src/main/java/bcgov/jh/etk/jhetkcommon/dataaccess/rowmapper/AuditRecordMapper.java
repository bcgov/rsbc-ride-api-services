package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.AuditRecord;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumAuditEventType;

/**
 * The Class AuditRecordMapper.
 */
public class AuditRecordMapper implements RowMapper<AuditRecord>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the audit record
	 * @throws SQLException the SQL exception
	 */
	@Override
	public AuditRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		AuditRecord ar = new AuditRecord();
		ar.setTicket_no(rs.getString("ticket_no"));
		if (rs.getTimestamp("audit_event_dtm") != null) {
			ar.setAudit_event_dtm(rs.getTimestamp("audit_event_dtm").toLocalDateTime());
		}
		ar.setAudit_event_id(rs.getInt("audit_event_id"));
		ar.setAudit_event_type_cd(rs.getString("audit_event_cd") == null ? null : EnumAuditEventType.getEnumfromCodeValue(rs.getString("audit_event_cd")));
		ar.setSubject_user_id(rs.getString("subject_user_id"));
		return ar;
	}

}
