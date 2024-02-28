package bcgov.jh.etk.jhetkcommon.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class EvtEventingUtilTest {

	@Test
	public void testExtractHour() {
		String hourtime = "0920";
		Integer hour = EvtEventingUtil.extractHour(hourtime);
		assertEquals(hour, new Integer(9));
	}

}
