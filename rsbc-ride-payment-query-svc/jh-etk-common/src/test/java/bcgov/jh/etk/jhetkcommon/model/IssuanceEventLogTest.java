package bcgov.jh.etk.jhetkcommon.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class IssuanceEventLogTest {

	@Test
	public void testGetTimeSlotString() {
		String candidate = "0701";
		String ts = IssuanceEventLog.getTimeSlot(candidate);
		assertEquals("07:00" + " to " + "09:59", ts);
		
		candidate = "2301";
		ts = IssuanceEventLog.getTimeSlot(candidate);
		assertEquals("22:00" + " to " + "02:59", ts);
		
		candidate = "0001";
		ts = IssuanceEventLog.getTimeSlot(candidate);
		assertEquals("22:00" + " to " + "02:59", ts);
		
		candidate = "1111";
		ts = IssuanceEventLog.getTimeSlot(candidate);
		assertEquals("10:00" + " to " + "15:59", ts);
	}
	
	@Test
	public void testGetprimecorpServerRegion() {
		String ticketNO = "EA00000101";
		String region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertEquals(IssuanceEventLog.PRIMECORP_SERVER_REGION_LMD, region);
		
		ticketNO = "EA599999999";
		region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertEquals(IssuanceEventLog.PRIMECORP_SERVER_REGION_LMD, region);
		
		ticketNO = "EA00010101";
		region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertEquals(IssuanceEventLog.PRIMECORP_SERVER_REGION_LMD, region);
		
		ticketNO = "EA84999999";
		region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertEquals(IssuanceEventLog.PRIMECORP_SERVER_REGION_NSE, region);
		
		ticketNO = "EA74900099";
		region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertEquals(IssuanceEventLog.PRIMECORP_SERVER_REGION_NSE, region);
		
		ticketNO = "EA60000000";
		region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertEquals(IssuanceEventLog.PRIMECORP_SERVER_REGION_NSE, region);
		
		ticketNO = "EA89781000";
		region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertEquals(IssuanceEventLog.PRIMECORP_SERVER_REGION_VIR, region);
		
		ticketNO = "EA85000000";
		region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertEquals(IssuanceEventLog.PRIMECORP_SERVER_REGION_VIR, region);
		
		ticketNO = "EA99999999";
		region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertEquals(IssuanceEventLog.PRIMECORP_SERVER_REGION_VIR, region);
		
		ticketNO = "EA00000005";
		region = IssuanceEventLog.getprimecorpServerRegion(ticketNO);
		assertNull(region);
		
	}
}
