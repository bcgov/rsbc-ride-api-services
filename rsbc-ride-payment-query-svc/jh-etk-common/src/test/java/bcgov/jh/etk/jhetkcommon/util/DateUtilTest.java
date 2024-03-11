package bcgov.jh.etk.jhetkcommon.util;

import org.junit.Test;

import static bcgov.jh.etk.jhetkcommon.util.DateUtil.*;
import static org.junit.Assert.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class DateUtilTest {

	@Test
	public void validateTimeTest() {

		String okTimeNoColon = "1414";
		String okTime = "14:14";
		String badTime = "333";

		assertEquals("14:14", validateTime(okTimeNoColon));
		assertEquals("14:14", validateTime(okTime));
		assertEquals(null, validateTime(badTime));
	}

	@Test
	public void isDateStringValidTest() {
		assertTrue(isDateStringValid("2019-01-31"));
		assertFalse(isDateStringValid("2019-31-01"));
		assertFalse(isDateStringValid("20190131"));
	}

	@Test
	public void dateCompareTest() {
		assertEquals(-1, (long) dateCompare("2019-01-31", YYYY_MM_DD, "2019-02-28", YYYY_MM_DD));
		assertEquals(0, (long) dateCompare("2019-01-31", YYYY_MM_DD, "2019-01-31", YYYY_MM_DD));
		assertEquals(1, (long) dateCompare("2019-01-31", YYYY_MM_DD, "2018-01-31", YYYY_MM_DD));
	}

	@Test
	public void compareViolationTimeTest() {
		assertTrue(compareViolationTime("2019-06-18T12:27", "12:27"));
		assertFalse(compareViolationTime("2019-06-18T12:27", "12:37"));
	}
	
	@Test
	public void test1() {
		String dateStr = "2019-09-16T16:30:20Z";
		String dateStrExpect = "2019-09-16T09:30";
		
		String convertedStr = DateUtil.localDateTimeToString(DateUtil.dateToLDT(DateUtil.StringToDate(dateStr, DateUtil.PATTERN_ISO_INSTANCE)), DateUtil.PATTERN_ICBC_DATE_TIME);
		assertEquals(dateStrExpect, convertedStr);
	}
	
	@Test
	public void assertOffsetDateTimeToString() {
		String dateString = "2020-05-15T12:00:00";
		LocalDateTime ldt = LocalDateTime.parse(dateString);
		OffsetDateTime odt = DateUtil.LocalDateTimeToOffsetDateTime(ldt);
		String odtString = DateUtil.OffsetDateTimeToString(odt);
		assertEquals(dateString, odtString);
	}
	
	@Test
	public void testOffsetDataTimeToFromString() {
		OffsetDateTime lastSentDTM = OffsetDateTime.now(DateUtil.PACIFIC_TIMEZONE);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern(DateUtil.PATTERN_ISO_INSTANCE);
		String dateString = fmt.format(lastSentDTM);
		
		OffsetDateTime date = OffsetDateTime.parse(dateString, DateTimeFormatter.ofPattern(DateUtil.PATTERN_ISO_INSTANCE));
		
		assertNotNull(date);
	}
	
	@Test
	public void testTime() {
		String receiptDateTime = "2019-06-21T16:30:03Z";
		String receiptTime = DateUtil.localDateTimeToString(DateUtil.dateToLDT(DateUtil.StringToDate(receiptDateTime, DateUtil.PATTERN_ISO_INSTANCE)), DateUtil.PATTERN_TIME);
		String expectedTime = "09:30";
		LocalTime vt = LocalTime.parse(receiptTime, DateTimeFormatter.ofPattern("HH:mm"));
		//assertEquals(receiptTime, expectedTime);
		
		String ICBCReceiptDateTime = "2019-06-21T09:30";
		String time = DateUtil.localDateTimeToString(DateUtil.StringToLocalDateTime(ICBCReceiptDateTime, DateUtil.PATTERN_ICBC_DATE_TIME), DateUtil.PATTERN_TIME);
		//assertEquals(time, "09:30");
	}

}