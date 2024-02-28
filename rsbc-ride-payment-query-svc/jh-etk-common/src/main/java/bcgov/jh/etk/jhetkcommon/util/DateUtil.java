package bcgov.jh.etk.jhetkcommon.util;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The Class DateUtil.
 * @author HLiang
 */
public class DateUtil {
	
	/** The Constant logger. */
	private final static Logger logger = LoggerFactory.getLogger(DateUtil.class);
	
	/** The Constant YYYY_MM_DD. */
	public static final String YYYY_MM_DD = "yyyy-MM-dd";
	
	/** The Constant YYYY_MM_DD_HH_MM_SS. */
	public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	
	/** The Constant YYYY_MM_DD_HH_MM_SS_Z. */
	public static final String YYYY_MM_DD_HH_MM_SS_Z = "yyyy-MM-dd HH:mm:ss Z";
	
	/** The Constant PATTERN_ICBC_DATE_TIME. */
	public static final String PATTERN_ICBC_DATE_TIME = "yyyy-MM-dd'T'HH:mm";
	
	public static final String PATTERN_LOCAL_DATE_TIME = "yyyy-MM-dd'T'HH:mm:ss";
	
	/** The Constant PATTERN_ISO_INSTANCE. */
	public static final String PATTERN_ISO_INSTANCE = "yyyy-MM-dd'T'HH:mm:ssX";
	
	/** The Constant PATTERN_TIME. */
	public static final String PATTERN_TIME = "HH:mm";
	
	/** The Constant PACIFIC_TIMEZONE_STR. */
	public static final String PACIFIC_TIMEZONE_STR = "America/Los_Angeles";
	
	/** The Constant PACIFIC_TIMEZONE. */
	public static final ZoneId PACIFIC_TIMEZONE = ZoneId.of(PACIFIC_TIMEZONE_STR);
	
	/** The Constant UTC_TIMEZONE. */
	public static final ZoneId UTC_TIMEZONE = ZoneId.of("UTC");
	
	/**
	 * Gets the current local datetime string.
	 *
	 * @return the current datetime string
	 */
	public static String getCurrentDatetimeString(){
		String strDate = null;
		try {
			strDate = localDateTimeToString(LocalDateTime.now(UTC_TIMEZONE), YYYY_MM_DD_HH_MM_SS);
		} catch (Exception e) {
			logger.error("Error occurred while execute getCurrentDatetimeString()");
		}
		return strDate;
	}

	/**
	 * Gets the local current datetime string.
	 *
	 * @return the local current datetime string
	 */
	public static String getLocalCurrentDatetimeString(){
		String strDate = null;
		try {
			strDate = localDateTimeToString(LocalDateTime.now(PACIFIC_TIMEZONE), YYYY_MM_DD_HH_MM_SS);
		} catch (Exception e) {
			logger.error("Error occurred while execute getCurrentDatetimeString()");
		}
		return strDate;
	}
	
	/**
	 * Gets the current local date string.
	 *
	 * @return the current date string
	 */
	public static String getCurrentDateString() {
		String strDate = null;
		try {
			strDate = localDateTimeToString(LocalDateTime.now(PACIFIC_TIMEZONE), YYYY_MM_DD);
		} catch (Exception e) {
			logger.error("Error occurred while execute getCurrentDateString()");
		}
		return strDate;
	}
	
	/**
	 * Date string to local date.
	 *
	 * @param dateString the date string
	 * @param datePattern the date pattern
	 * @return the local date
	 */
	public static LocalDate dateStringToLocalDate (final String dateString, final String datePattern) {
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}
		LocalDate date = null;
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
			formatter = formatter.withLocale( Locale.US );  // Locale specifies human language for translating, and cultural norms for lowercase/uppercase and abbreviations and such. Example: Locale.US or Locale.CANADA_FRENCH
			date = LocalDate.parse(dateString, formatter);
		} catch (Exception e) {
			logger.error("Error converting dateString[{}] to localdate", dateString);
		}
		return date;
	}
	
	/**
	 * Local date to string.
	 *
	 * @param ld the ld
	 * @param datePattern the date pattern
	 * @return the string
	 */
	public static String LocalDateToString (final LocalDate ld, final String datePattern) {
		if (ld == null) {
			return "";
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
			return toPacificLD(ld).format(formatter);
		} catch (DateTimeException e) {
			logger.error("Error converting locationDate to string: {}", e.getMessage());
		}
		return "";
	}
	
	/**
	 * String to local date time.
	 *
	 * @param dateString the date string
	 * @param datePattern the date pattern
	 * @return the local date time
	 */
	public static LocalDateTime StringToLocalDateTime(final String dateString, final String datePattern) {
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}
		try {
			//the dateString doesn't contain time portion.
			if (YYYY_MM_DD.equals(datePattern)) {
				LocalDateTime ldt = dateStringToLocalDate(dateString, datePattern).atStartOfDay(PACIFIC_TIMEZONE).toLocalDateTime();
				return ldt;
			}
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
			formatter = formatter.withLocale( Locale.US );  
			return LocalDateTime.parse(dateString, formatter);
		} catch (Exception e) {
			logger.error("Error converting date string to localDatetime: {}", e.getMessage());
		}
		
	    return null;
	}
	
	/**
	 * String to date.
	 *
	 * @param dateString the date string
	 * @param datePattern the date pattern
	 * @return the date
	 */
	public static Date StringToDate (final String dateString, final String datePattern) {
		if (StringUtils.isEmpty(dateString)) {
			return null;
		}
		try {
			return new SimpleDateFormat(datePattern).parse(dateString);  
		} catch (Exception e) {
			logger.error("Error converting date string to Date: {}", e.getMessage());
		}
		
	    return null;
	}
	
	/**
	 * Date to LDT.
	 *
	 * @param dateToConvert the date to convert
	 * @return the local date time
	 */
	public static LocalDateTime dateToLDT (Date dateToConvert) {
		if (dateToConvert == null) {
			return null;
		}
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
	}
	
	/**
	 * Date to string.
	 *
	 * @param date the date
	 * @param datePattern the date pattern
	 * @return the string
	 */
	public static String DateToString (final Date date, final String datePattern) {
		if (date == null) {
			return null;
		}
		try {
			Format formatter = new SimpleDateFormat(datePattern);
			return formatter.format(date);
		} catch (Exception e) {
			logger.error("Exception occurred converting date to string: {}", e.toString() + "; " + e.getMessage());
		}
		return null;
	}
	
	/**
	 * Checks if is date string valid.
	 *
	 * @param dateString the date string
	 * @return true, if is date string valid
	 */
	public static boolean isDateStringValid(final String dateString) {
		try {
			LocalDate.parse(dateString, DateTimeFormatter.ISO_LOCAL_DATE);
		} catch (DateTimeParseException e) {
			return false;
		}
		return true;
	}
	
	/**
	 * Checks if is date string valid.
	 *
	 * @param dateString the date string
	 * @param dateFormat the date format
	 * @return true, if is date string valid
	 */
	public static boolean isDateStringValid(final String dateString, final String dateFormat) {
		DateFormat sdf = new SimpleDateFormat(dateFormat);
        sdf.setLenient(false);
        try {
            sdf.parse(dateString);
        } catch (ParseException e) {
            return false;
        }
        return true;
	}
	
	/**
	 * Local date time to string.
	 *
	 * @param ldt the ldt
	 * @param datePattern the date pattern
	 * @return the string
	 */
	public static String localDateTimeToString(final LocalDateTime ldt, final String datePattern) {
		if (ldt == null) {
			return "";
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
			return toPacificLDT(ldt).format(formatter);
		} catch (DateTimeException e) {
			logger.error("Error converting locationDateTime to string: {}", e.getMessage());
		}
		return "";
	}
	
	/**
	 * PDT local date time to string.
	 *
	 * @param ldt the ldt
	 * @param datePattern the date pattern
	 * @return the string
	 */
	public static String PDTLocalDateTimeToString(final LocalDateTime ldt, final String datePattern) {
		if (ldt == null) {
			return "";
		}
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(datePattern);
			return ldt.format(formatter);
		} catch (DateTimeException e) {
			logger.error("Error converting locationDateTime to string: {}", e.getMessage());
		}
		return "";
	}
	
	/**
	 * Local date time to date.
	 *
	 * @param ldt the ldt
	 * @return the date
	 */
	public static Date localDateTimeToDate(final LocalDateTime ldt) {
		if (ldt == null) {
			return null;
		}
		ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
		Date output = Date.from(zdt.toInstant());
		return output;
	}
	
	/**
	 * Date compare.
	 *
	 * @param sdate1 the sdate 1
	 * @param date1Format the date 1 format
	 * @param sdate2 the sdate 2
	 * @param date2Format the date 2 format
	 * @return the integer
	 */
	public static Integer dateCompare(String sdate1, String date1Format, String sdate2, String date2Format) {
		try {
			Date date1 = new SimpleDateFormat(date1Format).parse(sdate1);
			Date date2 = new SimpleDateFormat(date2Format).parse(sdate2);
			return date1.compareTo(date2);
		} catch (ParseException e) {
			logger.error("Error while doing date compare: {}", e.getMessage());
		}
		
		return null;
	}
	
	/**
	 * To zoned LDT.
	 *
	 * @param ldt the ldt
	 * @param zoneId the zone id
	 * @return the local date time
	 */
	public static LocalDateTime toZonedLDT(final LocalDateTime ldt, final ZoneId zoneId) {
		if (ldt == null) {
			return null;
		}
		//get the zonedDateTime based on the current systemDefault timezone
		ZonedDateTime ldtZoned = ldt.atZone(ZoneId.systemDefault());
		//convert the zonedDateTime into the one based on the specified zoneId
		ZonedDateTime pZoned = ldtZoned.withZoneSameInstant(zoneId);
		return pZoned.toLocalDateTime();
	}
	
	
	/**
	 * To pacific LDT.
	 *
	 * @param ldt the ldt
	 * @return the local date time
	 */
	public static LocalDateTime toPacificLDT(final LocalDateTime ldt) {
		if (ldt == null) {
			return null;
		}
		
		return toZonedLDT(ldt, PACIFIC_TIMEZONE);
	}
	
	/**
	 * To pacific LD.
	 *
	 * @param ld the ld
	 * @return the local date
	 */
	public static LocalDate toPacificLD(final LocalDate ld) {
		if (ld == null) {
			return null;
		}
		LocalDateTime ldt = ld.atStartOfDay();
		LocalDateTime zonedLdt = toZonedLDT(ldt, PACIFIC_TIMEZONE);
		return zonedLdt.toLocalDate();
	}
	
	/**
	 * Date string to date string.
	 *
	 * @param fromDateString the from date string
	 * @param fromDateFormat the from date format
	 * @param toDateFormat the to date format
	 * @return the string
	 */
	public static String DateStringToDateString(final String fromDateString, final String fromDateFormat, final String toDateFormat) {
		try {
			DateFormat formatter;
			Date date;
			formatter = new SimpleDateFormat(fromDateFormat);
			date = formatter.parse(fromDateString);
			formatter = new SimpleDateFormat(toDateFormat);
			return formatter.format(date);
		} catch (ParseException e) {
			logger.error("Exception converting date: {}", e.getMessage());
		}
		
		return "";
	}
	
	/**
	 * Validate time.
	 *
	 * @param inTime the in time
	 * @return the string
	 */
	public static String validateTime(String inTime) {
		String outTime = inTime.trim();
		
		Pattern pattern = Pattern.compile("[0-9]{4}");
		Matcher matcher = pattern.matcher(outTime);
		if (matcher.matches()) {
			outTime = String.format("%1$s:%2$s", outTime.substring(0, 2),outTime.substring(2, 4));
		}
		
		pattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
		matcher = pattern.matcher(outTime);
		if (matcher.matches()) {
			return outTime;
		} else  {
			return null;
		}
	}
	
	/**
	 * Compare violation time.
	 *
	 * @param violationDateTime the violation date time, in the format of yyyy-MM-ddTHH:MM, e.g., 2019-06-18T12:27
	 * @param violationTime the violation time
	 * @return true, if successful
	 */
	public static boolean compareViolationTime(String violationDateTime, String violationTime) {
		String timeString = DateStringToDateString(violationDateTime, PATTERN_ICBC_DATE_TIME, PATTERN_TIME);
		return violationTime.equals(timeString);
	}
	
	/**
	 * Local date time to offset date time.
	 *
	 * @param ldt the ldt
	 * @return the offset date time
	 */
	public static OffsetDateTime LocalDateTimeToOffsetDateTime(LocalDateTime ldt) {
		ZoneOffset offset = ZoneOffsetFromZoneID(PACIFIC_TIMEZONE);
        OffsetDateTime offsetDateTime = ldt.atOffset(offset);
        return offsetDateTime;
	}
	
	/**
	 * Offset date time to local date time string.
	 *
	 * @param odt the odt
	 * @return the string
	 */
	public static String OffsetDateTimeToLocalDateTimeString(OffsetDateTime odt) {
		LocalDateTime ldt = odt.toLocalDateTime();
		return ldt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
	
	/**
	 * Offset date time to string (no time zone translation).
	 *
	 * @param odt the odt
	 * @return the string
	 */
	public static String OffsetDateTimeToString(OffsetDateTime odt) {
		// https://www.logicbig.com/how-to/code-snippets/jcode-java-8-date-time-api-offsetdatetime-format.html
		return odt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
	}
	
	/**
	 * Util parse date.
	 *
	 * @param paramName the param name
	 * @param paramValue the param value
	 * @param errors the errors
	 * @return the string
	 */
	public static String util_parseDate(String paramName, String paramValue, List<String> errors) {
		if (paramValue != null) {
			try {
				paramValue = java.sql.Date.valueOf(paramValue).toString();
			} catch (Exception e) {
				// failed to parse date string
				errors.add("      {\"error\": \"Invalid " + paramName + " format; expecting 'YYYY-MM-DD'\"}");
			}
		}
		
		return paramValue;
	}
	
	/**
	 * Util parse integer.
	 *
	 * @param paramName the param name
	 * @param paramValue the param value
	 * @param errors the errors
	 * @return the integer
	 */
	public static Integer util_parseInteger(String paramName, String paramValue, List<String> errors) {
		Integer i = null;
		
		if (paramValue != null) {
			try {
				i = Integer.valueOf(paramValue);
			}
			catch (Exception e) {
				// failed ot parse integer string
				errors.add("      {\"error\": \"Invalid " + paramName + " format; expecting integer value\"}");
			}
		}
		
		return i;
	}
	
	/**
	 * Zone offset from zone ID.
	 *
	 * @param zoneID the zone ID
	 * @return the zone offset
	 */
	private static ZoneOffset ZoneOffsetFromZoneID(ZoneId zoneID) {
		LocalDateTime now = LocalDateTime.now();
		ZoneOffset zoneOffSet = zoneID.getRules().getOffset(now);
		return zoneOffSet;
	}
}
