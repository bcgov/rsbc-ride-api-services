package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.OffsetDateTime;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EventNotificationTarget;

/**
 * The Class EventNotificationTargetRecordMapper.
 */
public class EventNotificationTargetRecordMapper implements RowMapper<EventNotificationTarget>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the event notification target
	 * @throws SQLException the SQL exception
	 */
	@Override
	public EventNotificationTarget mapRow(ResultSet rs, int rowNum) throws SQLException {
		EventNotificationTarget target = new EventNotificationTarget();
		target.setTargetId(rs.getInt("target_id"));
		if (rs.getTimestamp("last_sent_dtm") != null) {
			target.setLastSentDTM(rs.getObject("last_sent_dtm", OffsetDateTime.class));
		}
		target.setLastSuccessfullySentEventId(rs.getInt("last_sent_event_id"));
		target.setTargetName(rs.getString("target_name"));
		target.setTargetURL(rs.getString("target_endpoint_url"));
		target.setLastNotificationSucceeded(StringUtils.isBlank(rs.getString("last_notification_succeeded_yn")) ? null : "Y".equals(rs.getString("last_notification_succeeded_yn").toUpperCase()));
		
		target.setNotificationEventTypes(rs.getString("notification_event_types"));
		return target;
	}

}
