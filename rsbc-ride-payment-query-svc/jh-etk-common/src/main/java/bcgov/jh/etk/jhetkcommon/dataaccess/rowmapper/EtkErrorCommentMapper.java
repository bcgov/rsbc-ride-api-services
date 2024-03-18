package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.EtkErrorComments;


public class EtkErrorCommentMapper implements RowMapper<EtkErrorComments>{

	@Override
	public EtkErrorComments mapRow(ResultSet rs, int rowNum) throws SQLException {
		EtkErrorComments error = new EtkErrorComments();
		error.setComments(rs.getString("DETAILS_TXT"));
		error.setUpdatedBy(rs.getString("AUTHOR_USER_ID"));
		if (rs.getDate("COMMENT_DTM") != null) {
			error.setUpdatedDT(rs.getTimestamp("COMMENT_DTM").toLocalDateTime());
		}
		return error;
	}

}
