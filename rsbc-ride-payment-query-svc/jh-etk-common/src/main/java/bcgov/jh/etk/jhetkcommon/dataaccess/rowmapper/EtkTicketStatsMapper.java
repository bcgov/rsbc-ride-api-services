package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkError;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketErrorDetail;
import bcgov.jh.etk.jhetkcommon.model.EtkTicket;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCategory;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;

/**
 * The Class EtkTicketRecordMapper.
 */
public class EtkTicketStatsMapper implements RowMapper<EtkStatisticsTicketErrorDetail>{

	/**
	 * Map row.
	 *
	 * @param rs the rs
	 * @param rowNum the row num
	 * @return the etk ticket
	 * @throws SQLException the SQL exception
	 */
	@Override
	public EtkStatisticsTicketErrorDetail mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkStatisticsTicketErrorDetail ted = new EtkStatisticsTicketErrorDetail();
		
		EtkTicket ticket = new EtkTicket();
		ticket.setTicketID(rs.getString("TICKET_ID") == null ? null : new BigInteger(rs.getString("TICKET_ID")));
		ticket.setTicketNumber(rs.getString("TICKET_NO"));
		ticket.setTicketEnteredDateTime(rs.getTimestamp("ticketEnteredDT") == null ? null : rs.getTimestamp("ticketEnteredDT").toLocalDateTime());
		ticket.setTicketLastUpdatedDateTime(rs.getTimestamp("ticketLastUpdatedDT") == null ? null : rs.getTimestamp("ticketLastUpdatedDT").toLocalDateTime());
		if (StringUtils.isNotBlank(rs.getString("PROCESS_STATE_TYPE_CD"))) {
			ticket.setProcess_state_type_cd(EnumProcessState.getEnumfromCodeValue(rs.getString("PROCESS_STATE_TYPE_CD")));
		}
		
		EtkError error = null;
		ArrayList<EtkError> errors = null;
		if (rs.getString("ERROR_ID") != null) {
			error = new EtkError();
			error.setErrorID(new Long(rs.getString("ERROR_ID")));
		}
		
		if (error != null && rs.getString("error_category_cd") != null) {
			error.setErrorCategory(EnumErrorCategory.getEnumfromCodeValue(rs.getString("error_category_cd")));
		}
		
		if (error != null && rs.getTimestamp("RECEIVED_DTM") != null) {
			error.setCreateDT(rs.getTimestamp("RECEIVED_DTM").toLocalDateTime());
		}
		
		if (error != null && rs.getString("DETAILS_TXT") != null) {
			error.setErrorDescription(rs.getString("DETAILS_TXT"));
		}
		if (error != null && rs.getString("ERROR_CD") != null) {
			error.setErrorCode(EnumErrorCode.getEnumfromCodeValue(rs.getString("ERROR_CD")));
		}
		if (error != null) {
			errors = new ArrayList<EtkError>();
			errors.add(error);
		}
		ted.setTicketDetails(ticket);
		ted.setErrors(errors);
		
		return ted;
	}

}
