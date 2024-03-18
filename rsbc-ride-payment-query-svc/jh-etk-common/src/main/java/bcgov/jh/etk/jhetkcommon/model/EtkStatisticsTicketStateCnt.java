package bcgov.jh.etk.jhetkcommon.model;

import java.math.BigInteger;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;


/**
 * The Class VPHStatisticsTicketStateCnt.
 * @author HLiang
 */
public class EtkStatisticsTicketStateCnt {
	
	/** The process state type cd. */
	private EnumProcessState process_state_type_cd;
	
	/** The ticket count. */
	private BigInteger ticketCount;
	
	/**
	 * Gets the process state type cd.
	 *
	 * @return the process state type cd
	 */
	public EnumProcessState getProcess_state_type_cd() {
		return process_state_type_cd;
	}
	
	/**
	 * Sets the process state type cd.
	 *
	 * @param process_state_type_cd the new process state type cd
	 */
	public void setProcess_state_type_cd(EnumProcessState process_state_type_cd) {
		this.process_state_type_cd = process_state_type_cd;
	}
	
	/**
	 * Gets the ticket count.
	 *
	 * @return the ticket count
	 */
	public BigInteger getTicketCount() {
		return ticketCount;
	}
	
	/**
	 * Sets the ticket count.
	 *
	 * @param ticketCount the new ticket count
	 */
	public void setTicketCount(BigInteger ticketCount) {
		this.ticketCount = ticketCount;
	}
	
	
}
