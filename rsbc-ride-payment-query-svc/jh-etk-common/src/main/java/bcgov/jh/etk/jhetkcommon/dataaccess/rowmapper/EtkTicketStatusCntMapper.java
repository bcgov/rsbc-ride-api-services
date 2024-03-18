package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketStateCnt;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;


/**
 * The Class VPHTicketStatusCntMapper.
 * @author HLiang
 */
public class EtkTicketStatusCntMapper implements RowMapper<EtkStatisticsTicketStateCnt>{

	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public EtkStatisticsTicketStateCnt mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkStatisticsTicketStateCnt EtkStatisticsTicketStateCnt = new EtkStatisticsTicketStateCnt();
		if (StringUtils.isNotBlank(rs.getString("PROCESS_STATE_TYPE_CD"))) {
			EtkStatisticsTicketStateCnt.setProcess_state_type_cd(EnumProcessState.getEnumfromCodeValue(rs.getString("PROCESS_STATE_TYPE_CD")));
		}
		EtkStatisticsTicketStateCnt.setTicketCount(new BigInteger(rs.getString("ticketCnt")));
		return EtkStatisticsTicketStateCnt;
	}

}
