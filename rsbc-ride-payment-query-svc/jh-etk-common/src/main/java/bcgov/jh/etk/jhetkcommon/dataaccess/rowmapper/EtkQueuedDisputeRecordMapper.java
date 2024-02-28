package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkQueuedDisputeRecord;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;

/**
 * The Class EtkQueuedDisputeRecordMapper.
 */
public class EtkQueuedDisputeRecordMapper implements RowMapper<EtkQueuedDisputeRecord>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk ticket
	 * @throws SQLException the SQL exception
	 */
	@Override
	public EtkQueuedDisputeRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkQueuedDisputeRecord dispute = new EtkQueuedDisputeRecord();
		dispute.setProcessID(rs.getInt("process_id"));
		dispute.setDataPayload(rs.getString("data_payload_txt"));
		dispute.setDataType(EnumEventType.getEnumfromCodeValue(rs.getString("data_type_cd")));
		dispute.setProcessState(EnumProcessState.getEnumfromCodeValue(rs.getString("process_state_type_cd")));
		dispute.setCountNumber(rs.getString("count_nbr"));
		dispute.setTicketNumber(rs.getString("ticket_no"));
		return dispute;
	}

}
