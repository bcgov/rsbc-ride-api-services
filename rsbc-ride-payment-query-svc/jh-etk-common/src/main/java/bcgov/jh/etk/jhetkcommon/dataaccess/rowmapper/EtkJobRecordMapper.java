package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.task.TaskDetails;
import bcgov.jh.etk.jhetkcommon.model.task.TaskInputs;

public class EtkJobRecordMapper implements RowMapper<TaskDetails>{

	@Override
	public TaskDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		
//		EnumInterface
		
		
		TaskDetails td = new TaskDetails();
		if (rs.getTimestamp("NEXT_RUN_DTM") != null) {
			td.setNextRun(rs.getTimestamp("NEXT_RUN_DTM").toLocalDateTime());
		}
		td.setTaskID(rs.getString("JOB_ID"));
		String serviceName = rs.getString("SERVICE_NAME");
		td.setService(serviceName);
		
		EnumInterface enumInterface = EnumInterface.getEnumfromCodeValue(rs.getString("INTERFACE_NM"));
				
		TaskInputs ti = new TaskInputs(rs.getString("JOB_CREATION_USER_ID"), rs.getString("NEW_STATE_CD"), 
				rs.getString("COMMENTS_TXT"), serviceName, enumInterface);
		td.setInputs(ti);
		
		return td;
	}

}
