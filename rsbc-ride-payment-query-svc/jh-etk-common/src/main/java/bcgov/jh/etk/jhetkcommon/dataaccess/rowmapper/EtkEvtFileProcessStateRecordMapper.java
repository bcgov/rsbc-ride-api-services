package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.FTPFileInfo;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;

public class EtkEvtFileProcessStateRecordMapper implements RowMapper<FTPFileInfo>{

	@Override
	public FTPFileInfo mapRow(ResultSet rs, int rowNum) throws SQLException {
		FTPFileInfo fi = new FTPFileInfo();
		fi.setTicketID(rs.getString("TICKET_ID") == null ? null : new Integer(rs.getString("TICKET_ID")));
		fi.setDbFileName(rs.getString("FILE_NM"));
		fi.setFtpFileName(Const.FTP_PROCESSING_FOLDER + "/" + fi.getDbFileName());
		
		if (StringUtils.isNotBlank(rs.getString("PROCESS_STATE_TYPE_CD"))) {
			fi.setProcessingState(EnumProcessState.getEnumfromCodeValue(rs.getString("PROCESS_STATE_TYPE_CD")));
		}
		fi.setTicketNumber(rs.getString("TICKET_NO"));
		fi.setShowTicketFileFlag(rs.getBoolean("showTicketFileFlag"));
		return fi;
	}

}
