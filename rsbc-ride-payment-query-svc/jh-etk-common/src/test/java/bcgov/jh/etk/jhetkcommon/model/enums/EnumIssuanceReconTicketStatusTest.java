package bcgov.jh.etk.jhetkcommon.model.enums;

import static org.junit.Assert.*;

import org.junit.Test;

public class EnumIssuanceReconTicketStatusTest {

	@Test
	public void testGetCodeValue() {
		String status = "NA";
		EnumIssuanceReconTicketStatus eStatus = EnumIssuanceReconTicketStatus.getEnumfromCodeValue(status);
		assertNull(eStatus);
		
		status = null;
		eStatus = EnumIssuanceReconTicketStatus.getEnumfromCodeValue(status);
		assertNull(eStatus);
	}

}
