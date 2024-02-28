package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import bcgov.jh.etk.jhetkcommon.model.PrimeIssuanceReconRecord;

/**
 * The Class VPHReportKPIDetailMapper.
 * @author HLiang
 */
public class EtkIssuanceReconMapper implements RowMapper<PrimeIssuanceReconRecord> {
	
	/* (non-Javadoc)
	 * @see org.springframework.jdbc.core.RowMapper#mapRow(java.sql.ResultSet, int)
	 */
	@Override
	public PrimeIssuanceReconRecord mapRow(ResultSet rs, int rowNum) throws SQLException {
		PrimeIssuanceReconRecord irRecord = new PrimeIssuanceReconRecord();
		
		irRecord.setPrimeServerName(rs.getString("PRIME_SERVER_NAME"));
		irRecord.setPrimeServerSentDTM(rs.getString("PRIME_SERVER_SENT_DTM"));
		irRecord.setReconFileName(rs.getString("RECON_FILE_NAME"));
		irRecord.setTicketFileName(rs.getString("TICKET_FILE_NAME"));
		irRecord.setTicketNO(rs.getString("TICKET_NO"));
		irRecord.setTicketStatus(rs.getString("TICKET_STATUS"));
		return irRecord;
		
	}

}
