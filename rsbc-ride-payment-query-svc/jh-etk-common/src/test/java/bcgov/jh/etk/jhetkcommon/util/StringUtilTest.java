package bcgov.jh.etk.jhetkcommon.util;

import org.junit.Test;

import static org.junit.Assert.*;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class StringUtilTest {

	@Test
	public void format() {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String str = df.format(0.125);
		assertEquals("0.13", str);
	}
}