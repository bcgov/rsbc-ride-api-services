package bcgov.jh.etk.jhetkcommon.model;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;

/**
 * The Class TicketDisputeKPIDetails.
 * @author HLiang
 */
public class TicketDisputeKPIDetails{
	
	/** The event ID. */
	private Long eventID;

	/** The kpi ID. */
	private Long kpiID;
	
	/** The contravention NO. */
	private String contraventionNO;
	
	/** The dispute type. */
	private String disputeType;
	
	/** The offender type description. */
	private String offenderTypeDescription;
	
	/** The tickted amount. */
	private double ticktedAmount;

	/** The count act regulation. */
	private String countActRegulation;
	
	/** The compressed section. */
	private String compressedSection;
	
	/** The created DT. */
	private String createdDT;
	
	/** The dispute action CD. */
	private String disputeActionCD;
	
	/** The dispute action DT. */
	private String disputeActionDT;

	/** The event type. */
	private EnumEventType event_type_cd;
	
	
	/**
	 * Gets the event type cd.
	 *
	 * @return the event type cd
	 */
	public EnumEventType getEvent_type_cd() {
		return event_type_cd;
	}

	/**
	 * Sets the event type cd.
	 *
	 * @param event_type_cd the new event type cd
	 */
	public void setEvent_type_cd(EnumEventType event_type_cd) {
		this.event_type_cd = event_type_cd;
	}

	

	/**
	 * Gets the dispute action CD.
	 *
	 * @return the dispute action CD
	 */
	public String getDisputeActionCD() {
		return disputeActionCD;
	}

	/**
	 * Sets the dispute action CD.
	 *
	 * @param disputeActionCD the new dispute action CD
	 */
	public void setDisputeActionCD(String disputeActionCD) {
		this.disputeActionCD = disputeActionCD;
	}

	/**
	 * Gets the dispute action DT.
	 *
	 * @return the dispute action DT
	 */
	public String getDisputeActionDT() {
		return disputeActionDT;
	}

	/**
	 * Sets the dispute action DT.
	 *
	 * @param disputeActionDT the new dispute action DT
	 */
	public void setDisputeActionDT(String disputeActionDT) {
		this.disputeActionDT = disputeActionDT;
	}

	/**
	 * Gets the event ID.
	 *
	 * @return the event ID
	 */
	public Long getEventID() {
		return eventID;
	}

	/**
	 * Sets the event ID.
	 *
	 * @param eventID the new event ID
	 */
	public void setEventID(Long eventID) {
		this.eventID = eventID;
	}

	/**
	 * Gets the kpi ID.
	 *
	 * @return the kpi ID
	 */
	public Long getKpiID() {
		return kpiID;
	}

	/**
	 * Sets the kpi ID.
	 *
	 * @param kpiID the new kpi ID
	 */
	public void setKpiID(Long kpiID) {
		this.kpiID = kpiID;
	}

	/**
	 * Gets the contravention NO.
	 *
	 * @return the contravention NO
	 */
	public String getContraventionNO() {
		return contraventionNO;
	}

	/**
	 * Sets the contravention NO.
	 *
	 * @param contraventionNO the new contravention NO
	 */
	public void setContraventionNO(String contraventionNO) {
		this.contraventionNO = contraventionNO;
	}

	
	/**
	 * Gets the dispute type.
	 *
	 * @return the dispute type
	 */
	public String getDisputeType() {
		return disputeType;
	}

	/**
	 * Sets the dispute type.
	 *
	 * @param disputeType the new dispute type
	 */
	public void setDisputeType(String disputeType) {
		this.disputeType = disputeType;
	}

	/**
	 * Gets the offender type description.
	 *
	 * @return the offender type description
	 */
	public String getOffenderTypeDescription() {
		return offenderTypeDescription;
	}

	/**
	 * Sets the offender type description.
	 *
	 * @param offenderTypeDescription the new offender type description
	 */
	public void setOffenderTypeDescription(String offenderTypeDescription) {
		this.offenderTypeDescription = offenderTypeDescription;
	}

	/**
	 * Gets the tickted amount.
	 *
	 * @return the tickted amount
	 */
	public double getTicktedAmount() {
		return ticktedAmount;
	}

	/**
	 * Sets the tickted amount.
	 *
	 * @param ticktedAmount the new tickted amount
	 */
	public void setTicktedAmount(double ticktedAmount) {
		this.ticktedAmount = ticktedAmount;
	}

	/**
	 * Gets the count act regulation.
	 *
	 * @return the count act regulation
	 */
	public String getCountActRegulation() {
		return countActRegulation;
	}

	/**
	 * Sets the count act regulation.
	 *
	 * @param countActRegulation the new count act regulation
	 */
	public void setCountActRegulation(String countActRegulation) {
		this.countActRegulation = countActRegulation;
	}

	/**
	 * Gets the compressed section.
	 *
	 * @return the compressed section
	 */
	public String getCompressedSection() {
		return compressedSection;
	}

	/**
	 * Sets the compressed section.
	 *
	 * @param compressedSection the new compressed section
	 */
	public void setCompressedSection(String compressedSection) {
		this.compressedSection = compressedSection;
	}

	/**
	 * Gets the created DT.
	 *
	 * @return the created DT
	 */
	public String getCreatedDT() {
		return createdDT;
	}

	/**
	 * Sets the created DT.
	 *
	 * @param createdDT the new created DT
	 */
	public void setCreatedDT(String createdDT) {
		this.createdDT = createdDT;
	}
	
	
}
