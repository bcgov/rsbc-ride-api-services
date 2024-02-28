package bcgov.jh.etk.paymentsvc;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;

//@RunWith(SpringRunner.class)
public class PaymentsvcApplicationTests {

	//@Test
	public void dateTime() {
		String dateString = "2019-06-21T16:30:03Z";
		String dateString1 = "2019-06-21T16:30:03Z";
		String currentDateTime_utc = DateTimeFormatter.ISO_INSTANT.format(Instant.now());
		
		String dateStr = DateUtil.DateStringToDateString(dateString, DateUtil.PATTERN_ISO_INSTANCE, DateUtil.PATTERN_ICBC_DATE_TIME);
		
		String dateStr1 = DateUtil.localDateTimeToString(LocalDateTime.now(), DateUtil.PATTERN_ICBC_DATE_TIME);
		String dateStr2 = dateStr1;
	}

	@Test
	public void testMapTicketType () {
		String ticketNumber = "AZ04501180";
		String ticketType = ticketNumber.substring(0, 1);
		EnumTicketType tt = EnumTicketType.getEnumfromCodeValue(ticketType);
		String ab = "";
	}
}
