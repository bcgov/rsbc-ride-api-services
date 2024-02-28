/*
 * 
 */
package bcgov.jh.etk.jhetkcommon.service;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;

import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.AuditRecord;
import bcgov.jh.etk.jhetkcommon.model.EtkAgencyCodeText;
import bcgov.jh.etk.jhetkcommon.model.EtkAgencyStatistics;
import bcgov.jh.etk.jhetkcommon.model.EtkDisputeFinding;
import bcgov.jh.etk.jhetkcommon.model.EtkError;
import bcgov.jh.etk.jhetkcommon.model.EtkReport;
import bcgov.jh.etk.jhetkcommon.model.EtkReportKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketErrorDetail;
import bcgov.jh.etk.jhetkcommon.model.EtkTicketCntStatistics;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketErrorDetailWrapper;
import bcgov.jh.etk.jhetkcommon.model.EtkTicket;
import bcgov.jh.etk.jhetkcommon.model.EventNotificationTarget;
import bcgov.jh.etk.jhetkcommon.model.FTPFileInfo;
import bcgov.jh.etk.jhetkcommon.model.PrimeIssuanceReconRecord;
import bcgov.jh.etk.jhetkcommon.model.BankingFeePerTicketType;
import bcgov.jh.etk.jhetkcommon.model.EtkErrorComments;
import bcgov.jh.etk.jhetkcommon.model.EtkInterfaceStateTransition;
import bcgov.jh.etk.jhetkcommon.model.EtkQueuedDisputeRecord;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumAuditEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorStatus;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTransitionMode;
import bcgov.jh.etk.jhetkcommon.model.eventing.Event;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.KafkaEvent;
import bcgov.jh.etk.jhetkcommon.model.task.TaskDetails;


/**
 * The Interface IEtkService.
 */
public interface IEtkService {
	
	
	/**
	 * Gets the VPH errors.
	 *
	 * @param startDateTime the start date time
	 * @param endDateTime the end date time
	 * @param errorStatuses the error statuses
	 * @param errorID the error ID
	 * @return the VPH errors
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	List<EtkError> getEtkErrors(final String startDateTime, final String endDateTime, final List<EnumErrorStatus> errorStatuses, final String errorID) throws EtkDataAccessException;
	
	
	/**
	 * Update ticket data.
	 *
	 * @param ticketID the ticket ID
	 * @param outboundXMl the outbound XML
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	void updateTicketData(final Integer ticketID, final String outboundXMl) throws EtkDataAccessException;
	
	
	/**
	 * Gets the VPH error comments by error ID.
	 *
	 * @param errorID the error ID
	 * @return the VPH error comments by error ID
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	List<EtkErrorComments> getVPHErrorCommentsByErrorID(final String errorID) throws EtkDataAccessException;
	
	/**
	 * Update error.
	 *
	 * @param errorId the error id
	 * @param errorStatus the error status
	 * @param assignToUserID the assign to user ID
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	void updateError(final String errorId, final EnumErrorStatus errorStatus, final String assignToUserID) throws EtkDataAccessException;
	
		
	/**
	 * Log user access.
	 *
	 * @param errorId the error id
	 * @param accessingUserID the accessing user ID
	 * @param auditEventType the audit event type
	 * @return the integer
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	Integer logUserAccess(final String errorId, final String accessingUserID, final EnumAuditEventType auditEventType) throws EtkDataAccessException;
	
	/**
	 * Log user ticket access.
	 *
	 * @param ticketID the ticket ID
	 * @param accessingUserID the accessing user ID
	 * @param auditEventType the audit event type
	 * @return the integer
	 * @throws EtkDataAccessException the etk data access exception
	 */
	Integer logUserTicketAccess(final Integer ticketID, final String accessingUserID, final EnumAuditEventType auditEventType) throws EtkDataAccessException;

	
	/**
	 * Log user comment.
	 *
	 * @param errorId the error id
	 * @param authorUserID the author user ID
	 * @param comments the comments
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	void logUserComment(final String errorId, final String authorUserID, final String comments) throws EtkDataAccessException;
	
	/**
	 * Gets the VPH ticket.
	 *
	 * @param ticketID the ticket ID
	 * @return the VPH ticket
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	EtkTicket getEtkTicket(final Integer ticketID) throws EtkDataAccessException;
	
	
	/**
	 * Gets the ticket detail by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @return the ticket detail by ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	List<EtkTicket> getTicketDetailByTicketNO(final String ticketNO) throws EtkDataAccessException;
	
	/**
	 * Gets the VPH ticket statistics.
	 *
	 * @param endEnteredDT the end entered DT
	 * @param displayStart the display start
	 * @param displaySize the display size
	 * @param searchText the search text
	 * @param sortField the sort field
	 * @param sortDirection the sort direction
	 * @return the VPH ticket statistics
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	EtkStatisticsTicketErrorDetailWrapper getEtkTicketStats(OffsetDateTime endEnteredDT, Integer displayStart, Integer displaySize, String searchText,
			String sortField, String sortDirection) throws EtkDataAccessException;
	
	/**
	 * Gets the etk ticket cnt.
	 *
	 * @return the etk ticket cnt
	 * @throws EtkDataAccessException the etk data access exception
	 */
	EtkTicketCntStatistics getEtkTicketCnt() throws EtkDataAccessException;
	
	/**
	 * Gets the etk agency stats.
	 *
	 * @return the etk agency stats
	 * @throws EtkDataAccessException the etk data access exception
	 */
	EtkAgencyStatistics getEtkAgencyStats() throws EtkDataAccessException;
	
	/**
	 * Gets the etk report data.
	 *
	 * @param ticketNO the ticket NO
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param dataTypeFilter the data type filter
	 * @param agencyFilter the agency filter
	 * @return the etk report data
	 * @throws EtkDataAccessException the etk data access exception
	 */
	EtkReport getEtkReportData(final String ticketNO, final String startDate, final String endDate, final String dataTypeFilter, final String agencyFilter) throws EtkDataAccessException;
		
	/**
	 * Creates the ticket.
	 *
	 * @param sourceXML the source XML
	 * @param fileName the file name
	 * @param interfaceName the interface name
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> createTicket(final String sourceXML, final String fileName, final String interfaceName) throws EtkDataAccessException; 

	
	/**
	 * Creates the ticket KPI details and event.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> createTicketKPIDetailsEvent(final Integer ticketID) throws EtkDataAccessException; 

	
	/**
	 * Gets the errors by ticket I ds.
	 *
	 * @param ticketIDs the ticket I ds
	 * @return the hash map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public HashMap<Integer, List<EtkError>> GetErrorsByTicketIDs(String ticketIDs) throws EtkDataAccessException;
	
	/**
	 * Gets the icbc payment code.
	 *
	 * @param icbcPaymentMsgCode the icbc payment msg code
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, String> GetIcbcPaymentCode(final String icbcPaymentMsgCode) throws EtkDataAccessException;
	
	/**
	 * Gets the next queued ticket.
	 *
	 * @return the etk ticket
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkTicket GetNextQueuedTicket() throws EtkDataAccessException;
	
	/**
	 * Gets the tickets by evtstates.
	 *
	 * @param evtStates the evt states
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkTicket> GetTicketsByEvtstates(final List<EnumProcessState> evtStates) throws EtkDataAccessException;
	
	/**
	 * Gets the ticket kpi details.
	 *
	 * @param ticketNO the ticket NO
	 * @return the etk report KPI details
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkReportKPIDetails GetTicketKpiDetails(final String ticketNO) throws EtkDataAccessException;
	
	
	/**
	 * Gets the tickets kpi details.
	 *
	 * @param ticketNOs a list of comma deliminated ticket numbers
	 * @return the etk report KPI details
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, EtkReportKPIDetails> GetTicketsKpiDetails(final String ticketNOs) throws EtkDataAccessException;
	

	/**
	 * Update ticke process state by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @param interfaceNM the interface NM
	 * @param ticketProcesState the ticket proces state
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void UpdateTickeProcessStateByTicketID(final Integer ticketID, final EnumInterface interfaceNM, final EnumProcessState ticketProcesState) throws EtkDataAccessException;
	
	/**
	 * Record issuance event.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void RecordIssuanceEvent(final String ticketNO) throws EtkDataAccessException;
	
	/**
	 * Record query event.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void RecordQueryEvent(final String ticketNO) throws EtkDataAccessException;
	
	/**
	 * Record payment event.
	 *
	 * @param ticketNO the ticket NO
	 * @param paymentAmount the payment amount
	 * @param ticketType the ticket type
	 * @param cardType the card type
	 * @param receiptNumber the receipt number
	 * @param txn_number the txn number
	 * @throws DataIntegrityViolationException the data integrity violation exception
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void RecordPaymentEvent(final String ticketNO, final Double paymentAmount, final EnumTicketType ticketType, final String cardType, 
			final String receiptNumber, final String txn_number) throws DataIntegrityViolationException, EtkDataAccessException;
	
	/**
	 * Update interface.
	 *
	 * @param interfaceNM the interface NM
	 * @param interfaceStateCD the interface state CD
	 * @param updUserID the upd user ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void UpdateInterface(final EnumInterface interfaceNM, final EnumInterfaceState interfaceStateCD, final String updUserID) throws EtkDataAccessException;
	
	/**
	 * Delete old tickets.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void DeleteOldTickets() throws EtkDataAccessException;
	
	/**
	 * Delete errors.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void DeleteErrors() throws EtkDataAccessException;
	
	/**
	 * Delete smoketest ticket.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void DeleteSmoketestTicket() throws EtkDataAccessException;
	
	/**
	 * Gets the interface states.
	 *
	 * @param getTicketIssuanceState true: get the get ticket issuance state; otherwise, get all interfaces' states
	 * @return the interface states
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkInterfaceStateTransition getInterfaceStates(final boolean getTicketIssuanceState) throws EtkDataAccessException;
	

	/**
	 * Issuance interface state transition.
	 *
	 * @param updUserID the upd user ID
	 * @param newState the new state
	 * @param mode the mode
	 * @param callbackURL the callback URL
	 * @param emailBody the email body
	 * @param posponed TODO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void issuanceInterfaceStateTransitionWithEmailBody (final String updUserID, EnumInterfaceState newState, EnumTransitionMode mode, String callbackURL, String emailBody, boolean posponed) throws EtkDataAccessException;
	
	/**
	 * Issuance interface state transition.
	 *
	 * @param updUserID the upd user ID
	 * @param newState the new state
	 * @param mode the mode
	 * @param callbackURL the callback URL
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void issuanceInterfaceStateTransition (final String updUserID, EnumInterfaceState newState, EnumTransitionMode mode, String callbackURL) throws EtkDataAccessException;
	
	/**
	 * Re queue.
	 *
	 * @param updUserID the upd user ID
	 * @param ticketID the ticket ID
	 * @param errorID the error ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void reQueue(final String updUserID, final Integer ticketID, final String errorID) throws EtkDataAccessException;

	/**
	 * Check pause file existance.
	 *
	 * @param fileName the file name
	 * @return true, if successful
	 */
	public boolean checkFileExistance(final String fileName);
	

	/**
	 * Check pause existance.
	 *
	 * @return true, if successful
	 */
	public boolean checkPauseFileExistance();
	

	/**
	 * Query FTP files.
	 *
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<FTPFileInfo> queryFTPFiles() throws EtkDataAccessException;
	
	
	/**
	 * Check inbound files.
	 *
	 * @return the list
	 */
	public List<FTPFileInfo> checkInboundFiles();
	
	/**
	 * Gets the schedule jobs.
	 *
	 * @param taskID the task ID
	 * @return the schedule jobs
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<TaskDetails> getScheduleJobsFromDB(final String taskID) throws EtkDataAccessException;
	
	/**
	 * Save scheduled job.
	 *
	 * @param taskID the task ID
	 * @param nextRun the next run
	 * @param nextState the next state
	 * @param updUserID the upd user ID
	 * @param serviceName the service name
	 * @param comments the comments
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void saveScheduledJob(final String taskID, final String nextRun, String partnerFacingInterface, final String nextState, final String updUserID, final String serviceName, final String comments) throws EtkDataAccessException;
	
	/**
	 * Removes the scheduled job.
	 *
	 * @param taskID the task ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void removeScheduledJob(String taskID) throws EtkDataAccessException;
	
	/**
	 * Save issuance recon record.
	 *
	 * @param primeIssuanceRecon the prime issuance recon
	 * @return the string
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String saveIssuanceReconRecord(List<PrimeIssuanceReconRecord> primeIssuanceRecon) throws EtkDataAccessException;
	
	/**
	 * Validate issuance recon record.
	 *
	 * @param reconFileName the recon file name
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<PrimeIssuanceReconRecord> validateIssuanceReconRecord(final String reconFileName) throws EtkDataAccessException;
	
	
	/**
	 * Gets the active event notification targets.
	 *
	 * @return the active event notification targets
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EventNotificationTarget> getActiveEventNotificationTargets() throws EtkDataAccessException;
	
	/**
	 * Update event notification target.
	 *
	 * @param eventNotificationTarget the event notification target
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateEventNotificationTarget(EventNotificationTarget eventNotificationTarget) throws EtkDataAccessException;
	
	/**
	 * Retrieve events.
	 *
	 * @param startEventID the start event ID
	 * @param endEventID the end event ID
	 * @param startEventDate the start event date
	 * @param endEventDate the end event date
	 * @param pageSize the page size
	 * @param eventTypes the event types
	 * @param issuingAgencyCodeList the issuing agency code list
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<Event> retrieveEventsForEventPublish(Integer startEventID, Integer endEventID, String startEventDate, String endEventDate, Integer pageSize, String eventTypes, String issuingAgencyCodeList) throws EtkDataAccessException;
	
	
	/**
	 * Retrieve events for kafka.
	 *
	 * @param startEventID the start event ID
	 * @param endEventID the end event ID
	 * @param startEventDate the start event date
	 * @param endEventDate the end event date
	 * @param pageSize the page size
	 * @param eventTypes the event types
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<KafkaEvent> retrieveEventsForKafka(Integer startEventID, Integer endEventID, String startEventDate, String endEventDate, Integer pageSize, String eventTypes) throws EtkDataAccessException;

	
	/**
	 * Retrieve events for event lookup.
	 *
	 * @param startEventID the start event ID
	 * @param endEventID the end event ID
	 * @param startEventDate the start event date
	 * @param endEventDate the end event date
	 * @param pageSize the page size
	 * @param eventTypes the event types
	 * @param issuingAgencyCodeList the issuing agency code list
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<Event> retrieveEventsForEventLookup(Integer startEventID, Integer endEventID, String startEventDate, String endEventDate, Integer pageSize, String eventTypes, String issuingAgencyCodeList) throws EtkDataAccessException;

	
	/**
	 * Record dispute findings.
	 *
	 * @param etkDisputeFindings the etk dispute findings
	 * @return the string
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String recordDisputeFindings(List<EtkDisputeFinding> etkDisputeFindings) throws EtkDataAccessException;
	
	
	/**
	 * Gets the evt files stats.
	 *
	 * @param fileNames a list of comma deliminated file names
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<FTPFileInfo> GetEvtFilesStats(final String fileNames) throws EtkDataAccessException;

	/**
	 * Delete ticket by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void deleteTicketByTicketNO(final String ticketNO) throws EtkDataAccessException;
	
	/**
	 * Gets the audit records by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @return the audit records by ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<AuditRecord> getAuditRecordsByTicketNO(final String ticketNO) throws EtkDataAccessException;
	
	/**
	 * Update agency latest mre date.
	 *
	 * @param agencyCD the agency CD
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateAgencyLatestMreDate(final String agencyCD) throws EtkDataAccessException;
	
	/**
	 * Gets the DB version.
	 *
	 * @return the DB version
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String getDBVersion() throws EtkDataAccessException;
	
	/**
	 * Gets the associated unresolved errors.
	 *
	 * @param errorID the error ID
	 * @return the associated errors
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkError> getAssociatedErrorsUnresolved(final String errorID) throws EtkDataAccessException;
	
	/**
	 * Gets the tickets sent today.
	 *
	 * @return the tickets sent today
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkTicket> getTicketsReceivedToday() throws EtkDataAccessException;
	
	/**
	 * Gets the tickets not sent.
	 *
	 * @return the tickets not sent
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkStatisticsTicketErrorDetail> getTicketsNotSent() throws EtkDataAccessException;
	
	/**
	 * Update errors by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @param errorStatus the error status
	 * @param updUserID the upd user ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateErrorsByTicketID(Integer ticketID, String errorStatus, String updUserID) throws EtkDataAccessException;

	/**
	 * Gets the next queued dispute record.
	 * @param dataTypeCode the data type code, values: DISPUTE or DISPUTE_STATUS_UPDATE
	 * @return the etk queued dispute record
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkQueuedDisputeRecord GetNextQueuedDisputeRecord(String dataTypeCode) throws EtkDataAccessException;
	
	/**
	 * Update the dispute record.
	 *
	 * @param processID the process ID
	 * @param newProcessState the new process state
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateDisputeRecord(int processID, EnumProcessState newProcessState) throws EtkDataAccessException;
	
	/**
	 * Gets the active agencies.
	 *
	 * @return the active agencies
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkAgencyCodeText> getActiveAgencies() throws EtkDataAccessException;

	/**
	 * Delete ticket by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void deleteTicketByTicketID(final Integer ticketID) throws EtkDataAccessException;
	
	
	/**
	 * Delete dup tickets by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void deleteDupTicketsByTicketID(final Integer ticketID) throws EtkDataAccessException;

	/**
	 * Signal ticket process.
	 */
	public void signalTicketProcess();
	
	/**
	 * Gets the known users.
	 *
	 * @return the known users
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<String> getKnownUsers() throws EtkDataAccessException;
	
	/**
	 * Validate ticket.
	 *
	 * @param sourceXML the source XML
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> validateTicket(final String sourceXML) throws EtkDataAccessException;

	/**
	 * Sets the ticke status to sent.
	 *
	 * @param ticketID the ticket ID
	 * @param interfaceNM the interface NM
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void SetTickeStatusToSent(final Integer ticketID, final EnumInterface interfaceNM) throws EtkDataAccessException;

	/**
	 * Check for stuck tickets.
	 *
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> CheckForStuckTickets() throws EtkDataAccessException;
	
	/**
	 * Interface state transition.
	 *
	 * @param theInterface the the interface
	 * @param updUserID the upd user ID
	 * @param newState the new state
	 * @param mode the mode
	 * @param callbackURL the callback URL
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void disputeInterfaceStateTransition (EnumInterface theInterface, final String updUserID, EnumInterfaceState newState, EnumTransitionMode mode, boolean skipEmail) throws EtkDataAccessException;

	/**
	 * Gets the dispute records.
	 *
	 * @param dataTypeCode the data type code
	 * @param processStateTypeCode the process state type code
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkQueuedDisputeRecord> GetDisputeRecords(String dataTypeCode, String processStateTypeCode) throws EtkDataAccessException; 

	/**
	 * Banking fee calculation.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<EnumTicketType, BankingFeePerTicketType> bankingFeeCalculation(String startDate, String endDate) throws EtkDataAccessException;
}





