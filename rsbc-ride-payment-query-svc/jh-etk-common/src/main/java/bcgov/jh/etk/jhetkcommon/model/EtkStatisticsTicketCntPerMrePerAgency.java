package bcgov.jh.etk.jhetkcommon.model;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EtkStatisticsTicketCntPerMrePerAgency.
 */

@Setter
@Getter
public class EtkStatisticsTicketCntPerMrePerAgency {
	
	/** The Constant TOOLTIP_TXT. */
	private static final String TOOLTIP_TXT = " been issued by this version since the reset date. The last eVT issued using this old version was on ";

	/** The ticket count total. */
	private String ticketCountTotal;
	
	/** The ticket count warning. */
	private String ticketCountWarning;
	
	/** The violation date last ticket issued. */
	private String violationDateLastTicketIssued;
	
	/**
	 * Gets the tool tip.
	 *
	 * @return the tool tip
	 */
	public String getToolTip() {
		if (isHasWarningCnt()) {
			String prefix = "1".equals(ticketCountWarning) ? " ticket has" : " tickets have";
			String toolTipText = ticketCountWarning + prefix + TOOLTIP_TXT + violationDateLastTicketIssued;
			return toolTipText;
		}
		return null;
	}
	
	/**
	 * Checks if is checks for warning cnt.
	 *
	 * @return true, if is checks for warning cnt
	 */
	public boolean isHasWarningCnt() {
		return StringUtils.isNoneBlank(ticketCountWarning);
	}
	
	/**
	 * Gets the ticket cnt txt.
	 *
	 * @return the ticket cnt txt
	 */
	public String getTicketCntTxt() {
		if (isHasWarningCnt()) {
			return ticketCountTotal + " (" + ticketCountWarning + ")";
		} 
		
		return ticketCountTotal;
	}
}
