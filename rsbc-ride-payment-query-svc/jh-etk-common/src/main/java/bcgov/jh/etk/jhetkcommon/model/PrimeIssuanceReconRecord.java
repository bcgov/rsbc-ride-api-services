package bcgov.jh.etk.jhetkcommon.model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumIssuanceReconTicketStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new prime issuance recon file.
 */
@NoArgsConstructor

/**
 * Instantiates a new prime issuance recon file.
 *
 * @param primeServerSentDTM the prime server sent DTM
 * @param fileName the file name
 * @param ticketStatus the ticket status
 * @param primeServerName the prime server name
 */
@AllArgsConstructor

/**
 * Gets the prime server name.
 *
 * @return the prime server name
 */
@Getter

/**
 * Sets the prime server name.
 *
 * @param primeServerName the new prime server name
 */
@Setter
public class PrimeIssuanceReconRecord {
	/** The Constant PATTERN_TICKET_NO. */
	private static final String PATTERN_TICKET_NO = "[eE][a-zA-Z][0-9]{8}";
	private static final Pattern ticketNORegex = Pattern.compile(PATTERN_TICKET_NO);
	
	/** The prime server sent DTM. */
	private String primeServerSentDTM;
	
	/** The ticket file name. */
	private String ticketFileName;
	
	/** The ticket status. */
	private String ticketStatus;
	
	/** The prime server name. */
	private String primeServerName;
	
	/** The ticket NO. */
	private String ticketNO;
	
	/** The recon file name. */
	private String reconFileName;
	
	/**
	 * Checks if the record is valid.
	 *
	 * @return true, if is valid
	 */
	public boolean isValid() {
		if (StringUtils.isBlank(primeServerSentDTM) 
				|| StringUtils.isBlank(ticketFileName) 
				|| StringUtils.isBlank(ticketStatus)
				|| EnumIssuanceReconTicketStatus.getEnumfromCodeValue(ticketStatus) == null
				|| StringUtils.isBlank(primeServerName) 
				|| StringUtils.isBlank(ticketNO)) {
			return false;
		}
		return true;
	}
	
	/**
	 * Extract ticket NO.
	 *
	 * @param fileName the file name, sample value: tkea85018741_98051l1439u.xml OR incoming/tkea00023210_x4f6my
	 * @return the string
	 */
	public static String extractTicketNO(String fileName) {
		String ticketNO = null;
		if (StringUtils.isNotBlank(fileName)) {
			Matcher matcher = ticketNORegex.matcher(fileName);
	        if(matcher.find()) {
	        	ticketNO = matcher.group(0).toUpperCase();
	        }
		}
		return ticketNO;
	}
}
