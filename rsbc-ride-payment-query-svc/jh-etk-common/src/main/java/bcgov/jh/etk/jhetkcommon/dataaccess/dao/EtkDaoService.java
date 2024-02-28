package bcgov.jh.etk.jhetkcommon.dataaccess.dao;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.AuditRecord;
import bcgov.jh.etk.jhetkcommon.model.EtkAgencyCodeText;
import bcgov.jh.etk.jhetkcommon.model.EtkAgencyStatistics;
import bcgov.jh.etk.jhetkcommon.model.EtkDisputeFinding;
import bcgov.jh.etk.jhetkcommon.model.EtkError;
import bcgov.jh.etk.jhetkcommon.model.EtkErrorComments;
import bcgov.jh.etk.jhetkcommon.model.EtkQueuedDisputeRecord;
import bcgov.jh.etk.jhetkcommon.model.EtkReport;
import bcgov.jh.etk.jhetkcommon.model.EtkReportKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketErrorDetail;
import bcgov.jh.etk.jhetkcommon.model.EtkTicketCntStatistics;
import bcgov.jh.etk.jhetkcommon.model.EtkWorkingData;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketErrorDetailWrapper;
import bcgov.jh.etk.jhetkcommon.model.EtkTicket;
import bcgov.jh.etk.jhetkcommon.model.EventNotificationTarget;
import bcgov.jh.etk.jhetkcommon.model.FTPFileInfo;
import bcgov.jh.etk.jhetkcommon.model.PrimeIssuanceReconRecord;
import bcgov.jh.etk.jhetkcommon.model.BankingFeePerTicketType;
import bcgov.jh.etk.jhetkcommon.model.disputestatusupdate.DisputeStatusUpdate;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumAuditEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorStatus;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import bcgov.jh.etk.jhetkcommon.model.eventing.Event;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.KafkaEvent;
import bcgov.jh.etk.jhetkcommon.model.task.TaskDetails;
import bcgov.jh.etk.jhetkcommon.model.ticketdispute.TicketDispute;
import bcgov.jh.etk.jhetkcommon.service.RestService;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import bcgov.jh.etk.jhetkcommon.util.PauseResumeUtil;

/**
 * The Class EtkDaoService.
 */
@Service
public class EtkDaoService {
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(EtkDaoService.class);
	
	/** The vph dao. */
	@Autowired(required = false)
	EtkDao etkDao;
	
	/** The email sender. */
	@Autowired(required = false)
    public JavaMailSender emailSender;
	
	/** The rest service. */
    @Autowired
    RestService restService;
	
	/**
	 * Gets the VPH errors.
	 *
	 * @param startDateTime the start date time
	 * @param endDateTime the end date time
	 * @param errorStatuses the error statuses
	 * @param errorID the error ID
	 * @return the VPH errors
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkError> getVPHErrors(final String startDateTime, final String endDateTime, final List<EnumErrorStatus> errorStatuses, final String errorID) throws EtkDataAccessException {
		try {
			return etkDao.getVPHErrors(startDateTime, endDateTime, errorStatuses, errorID);
		} catch (Exception e) {
			logger.error("Exception occurred while executing getVPHErrors: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Update ticketdata.
	 *
	 * @param ticketID the ticket ID
	 * @param outboundXML the outbound XML
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	public void updateTicketdata(final Integer ticketID, final String outboundXML) throws EtkDataAccessException {
		try {
			etkDao.updateTicketdata(ticketID, outboundXML);
		} catch (Exception e) {
			logger.error("Exception occurred while executing updateTicketdata: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Gets the comments by error ID.
	 *
	 * @param errorID the error ID
	 * @return the comments by error ID
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	public List<EtkErrorComments> getCommentsByErrorID(final String errorID) throws EtkDataAccessException {
		try {
			return etkDao.getCommentsByErrorID(errorID);
		} catch (Exception e) {
			logger.error("Exception occurred while executing getCommentsByErrorID: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	

	/**
	 * Update error.
	 *
	 * @param errorID the error ID
	 * @param statusCD the status CD
	 * @param subjectUserID the subject user ID
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	public void updateError(final String errorID, final EnumErrorStatus statusCD, final String subjectUserID) throws EtkDataAccessException {
		try {
			etkDao.updateError(errorID, statusCD, subjectUserID);
		} catch (Exception e) {
			logger.error("Exception occurred while executing updateError: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	

	/**
	 * Log user access.
	 *
	 * @param errorID the error ID
	 * @param accessingUserID the accessing user ID
	 * @param auditEventType the audit event type
	 * @return the integer
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	public Integer logUserAccess(final String errorID, final String accessingUserID, final EnumAuditEventType auditEventType) throws EtkDataAccessException {
		try {
			return etkDao.logUserAccess(errorID, accessingUserID, auditEventType);
		} catch (Exception e) {
			logger.error("Exception occurred while executing logUserAccess: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	
	/**
	 * Log user ticket access.
	 *
	 * @param ticketID the ticket ID
	 * @param accessingUserID the accessing user ID
	 * @param auditEventType the audit event type
	 * @return the integer
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Integer logUserTicketAccess(final Integer ticketID, final String accessingUserID, final EnumAuditEventType auditEventType) throws EtkDataAccessException {
		try {
			return etkDao.logUserTicketAccess(ticketID, accessingUserID, auditEventType);
		} catch (Exception e) {
			logger.error("Exception occurred while executing logUserTicketAccess: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	/**
	 * Log user comment.
	 *
	 * @param errorID the error ID
	 * @param authorUserID the author user ID
	 * @param comments the comments
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	public void logUserComment(final String errorID, final String authorUserID, final String comments) throws EtkDataAccessException {
		try {
			etkDao.logUserComment(errorID, authorUserID, comments);
		} catch (Exception e) {
			logger.error("Exception occurred while executing logUserComment: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Gets the ticket detail.
	 *
	 * @param ticketID the ticket ID
	 * @return the ticket detail
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	public EtkTicket getTicketDetail(final Integer ticketID) throws EtkDataAccessException {
		try {
			return etkDao.getTicketDetail(ticketID);
		} catch (Exception e) {
			logger.error("Exception occurred while executing getTicketDetail: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the tickets detail.
	 *
	 * @param ticketNO ticket number
	 * @return the tickets detail
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List <EtkTicket> getTicketDetailByTicketNO (final String ticketNO) throws EtkDataAccessException {
		try {
			return etkDao.getTicketDetailByTicketNO(ticketNO);
		} catch (Exception e) {
			logger.error("Exception occurred while executing getTicketDetailByTicketNO: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the ticket statistics.
	 *
	 * @param endEnteredDT the end entered DT
	 * @param displayStart the display start
	 * @param displaySize the display size
	 * @param searchText the search text
	 * @param sortField the sort field
	 * @param sortDirection the sort direction
	 * @return the ticket statistics
	 * @throws EtkDataAccessException the VPH data access exception
	 */
	public EtkStatisticsTicketErrorDetailWrapper getTicketStatistics(OffsetDateTime endEnteredDT, Integer displayStart, Integer displaySize, String searchText,
			String sortField, String sortDirection) throws EtkDataAccessException {
		try {
			return etkDao.getTicketStatistics(endEnteredDT, displayStart, displaySize, searchText, sortField, sortDirection);
		} catch (Exception e) {
			logger.error("Exception occurred while executing getTicketStatistics: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the ticket cnt.
	 *
	 * @return the ticket cnt
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkTicketCntStatistics getTicketCnt() throws EtkDataAccessException {
		try {
			return etkDao.getTicketCnt();
		} catch (Exception e) {
			logger.error("Exception occurred while executing getTicketCnt: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the agency stats.
	 *
	 * @return the agency stats
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkAgencyStatistics getAgencyStats() throws EtkDataAccessException {
		try {
			return etkDao.getAgencyStats();
		} catch (Exception e) {
			logger.error("Exception occurred while executing getAgencyStats: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the VPH report data.
	 *
	 * @param ticketNO the ticket NO
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param dataTypeFilter the data type filter
	 * @param agencyFilter the agency filter
	 * @return the VPH report data
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkReport getEtkReportData(String ticketNO, final String startDate, final String endDate, final String dataTypeFilter, final String agencyFilter) throws EtkDataAccessException {
		try {
			return etkDao.getEtkReportData(ticketNO, startDate, endDate, dataTypeFilter, agencyFilter);
		} catch (Exception e) {
			logger.error("Exception occurred while executing getVPHReportData: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Creates the error stored proc.
	 *
	 * @param ticketID the ticket ID
	 * @param errorCategoryCD the error category CD
	 * @param errorSeverityLevel the error severity level
	 * @param ticketNO the ticket NO
	 * @param contraventionNO the contravention NO
	 * @param detailTxt the detail txt
	 * @param serviceNM the service NM
	 * @param primeTxt the prime txt
	 * @param ICBCTxt the ICBC txt
	 * @param justinTxt the justin txt
	 * @param errorCD the error CD
	 * @return the integer
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Integer createErrorStoredProc(final Integer ticketID, final String errorCategoryCD, final String errorSeverityLevel, final String ticketNO, 
		final String contraventionNO, final String detailTxt, final String serviceNM, final String primeTxt,
		final String ICBCTxt, final String justinTxt, final String errorCD) throws EtkDataAccessException {
	
		try {
			return etkDao.createErrorStoredProc(ticketID, errorCategoryCD, errorSeverityLevel, ticketNO, contraventionNO, detailTxt, 
					serviceNM, primeTxt, ICBCTxt, justinTxt, errorCD);
		} catch (Exception e) {
			logger.error("Exception occurred while executing createErrorStoredProc: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Creates the ticket store proc.
	 *
	 * @param sourceXML the source XML
	 * @param fileName the file name
	 * @param interfaceName the interface name
	 * @return the integer
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> createTicketStoreProc(String sourceXML, String fileName, String interfaceName) throws EtkDataAccessException {
		try {
			return etkDao.createTicketStoreProc(sourceXML, fileName, interfaceName);
		} catch (Exception e) {
			logger.error("Exception occurred while executing createTicketStoreProc: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	
	/**
	 * Gets the errors by ticket I ds.
	 *
	 * @param ticketIDs the ticket I ds
	 * @return the hash map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public HashMap<Integer, List<EtkError>> GetErrorsByTicketIDs(final String ticketIDs) throws EtkDataAccessException {
		try {
			return etkDao.GetErrorsByTicketIDs(ticketIDs);
		} catch (Exception e) {
			logger.error("Exception occurred while executing GetErrorsByTicketIDs: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the icbc payment code.
	 *
	 * @param icbcPaymentMsgCode the icbc payment msg code
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, String> GetIcbcPaymentCode(final String icbcPaymentMsgCode) throws EtkDataAccessException {
		try {
			return etkDao.GetIcbcPaymentCode(icbcPaymentMsgCode);
		} catch (Exception e) {
			logger.error("Exception occurred while executing GetIcbcPaymentCode: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the next queued ticket.
	 *
	 * @return the etk ticket
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkTicket GetNextQueuedTicket() throws EtkDataAccessException {
		try {
			return etkDao.GetNextQueuedTicket();
		} catch (Exception e) {
			logger.error("Exception occurred while executing GetNextQueuedTicket: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the tickets by evtstates.
	 *
	 * @param evtStates the evt states
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkTicket> GetTicketsByEvtstates(List<EnumProcessState> evtStates) throws EtkDataAccessException {
		try {
			return etkDao.GetTicketsByEvtstates(evtStates);
		} catch (Exception e) {
			logger.error("Exception occurred while executing GetTicketsByEvtstates: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the ticket kpi details.
	 *
	 * @param ticketNO the ticket NO
	 * @return the etk report KPI details
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkReportKPIDetails GetTicketKpiDetails(final String ticketNO) throws EtkDataAccessException {
		try {
			return etkDao.GetTicketKpiDetails(ticketNO);
		} catch (Exception e) {
			logger.error("Exception occurred while executing GetTicketKpiDetails: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	
	/**
	 * Gets the tickets kpi details.
	 *
	 * @param ticketNOs a list of comma deliminated ticket numbers
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, EtkReportKPIDetails> GetTicketsKpiDetails(final String ticketNOs) throws EtkDataAccessException {
		try {
			return etkDao.GetTicketsKpiDetails(ticketNOs);
		} catch (Exception e) {
			logger.error("Exception occurred while executing GetTicketKpiDetails: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Record issuance event.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void RecordIssuanceEvent(final String ticketNO) throws EtkDataAccessException {
		try {
			etkDao.RecordIssuanceEvent(ticketNO);
		} catch (Exception e) {
			logger.error("Exception occurred while executing RecordIssuanceEvent: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Record query event.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void RecordQueryEvent(final String ticketNO) throws EtkDataAccessException {
		try {
			etkDao.RecordQueryEvent(ticketNO);
		} catch (Exception e) {
			logger.error("Exception occurred while executing RecordQueryEvent: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
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
	public void RecordPaymentEvent(final String ticketNO, final Double paymentAmount, final EnumTicketType ticketType, 
			final String cardType, final String receiptNumber, final String txn_number) throws DataIntegrityViolationException, EtkDataAccessException {
		try {
			etkDao.RecordPaymentEvent(ticketNO, paymentAmount, ticketType, cardType, receiptNumber, txn_number);
		} catch (DataIntegrityViolationException dve) {
			logger.error("Duplicate payment event found, ticketNO: {}, receiptNumber: {}, exception: {}", ticketNO, receiptNumber, dve.toString() + "; " + dve.getMessage());
			throw dve;
		} catch (Exception e) {
			logger.error("Exception occurred while executing RecordPaymentEvent: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Retrieve interface.
	 *
	 * @param interfaceNM the interface NM
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> RetrieveInterface(final String interfaceNM) throws EtkDataAccessException {
		try {
			return etkDao.RetrieveInterface(interfaceNM);
		} catch (Exception e) {
			logger.error("Exception occurred while executing RetrieveInterface: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Update interface.
	 *
	 * @param interfaceNM the interface NM
	 * @param interfaceStateCD the interface state CD
	 * @param updUserID the upd user ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void UpdateInterface(final EnumInterface interfaceNM, final EnumInterfaceState interfaceStateCD, final String updUserID) throws EtkDataAccessException {
		try {
			etkDao.UpdateInterface(interfaceNM, interfaceStateCD, updUserID);
		} catch (Exception e) {
			logger.error("Exception occurred while executing UpdateInterface: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Delete old tickets.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void DeleteOldTickets() throws EtkDataAccessException {
		try {
			etkDao.DeleteOldTickets();
		} catch (Exception e) {
			logger.error("Exception occurred while executing DeleteOldTickets: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Delete errors.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void DeleteErrors() throws EtkDataAccessException {
		try {
			etkDao.DeleteErrors();
		} catch (Exception e) {
			logger.error("Exception occurred while executing DeleteErrors: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Delete smoketest ticket.
	 *
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void DeleteSmoketestTicket() throws EtkDataAccessException {
		try {
			etkDao.DeleteSmoketestTicket();
		} catch (Exception e) {
			logger.error("Exception occurred while executing DeleteSmoketestTicket: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Db exception handling.
	 *
	 * @param e the e
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void dbExceptionHandling (Exception e) throws EtkDataAccessException {
		String summary = "";
		String resolutionSteps = "Perform the each of the following steps until issue is resolved: " + "\n\n" +
			" * Check for general database connectivity error" + "\n\n" + 
			" * Contact eTK application support for further investigation";
			
		if (e instanceof DataAccessException) {
			summary = "Database access exception";
			logger.error("Data access exception occurred: {}", e.toString());
		} else {
			summary = "Database exception occurred";
			logger.error("DB exception [{}] occurred, file pause the hub. Exception details: {}", e.toString(), e.getMessage());
			String subject = "Hub has entered the paused mode due to a database error ";
			String emailBody = e.toString() + "; \n\n" + e.getMessage() +
					"\n\n" + 
					"Note: In the case of a database connectivity error, there will not be a corresponding error record in the support console.";
			
			StringBuilder stringBuilder = new StringBuilder();
			
			stringBuilder.append("Error summary: " + summary + "\n\n");
			stringBuilder.append("Error occurred on: " + DateUtil.getLocalCurrentDatetimeString() + "\n\n");
			stringBuilder.append("Error technical details: " + "\n\n" + emailBody + "\n\n");
			stringBuilder.append("Error resolution steps: " + "\n\n" + resolutionSteps);
			
			PauseResumeUtil.sendSimpleMessage(emailSender, subject, stringBuilder.toString());
			PauseResumeUtil.WritePauseFile(restService);
		}
		throw new EtkDataAccessException(e);
	}
	
	
	/**
	 * Gets the schedule jobs from DB.
	 *
	 * @param taskID the task ID
	 * @return the schedule jobs from DB
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<TaskDetails> getScheduleJobsFromDB(String taskID) throws EtkDataAccessException {
		try {
			return etkDao.getScheduleJobsFromDB(taskID);
		} catch (Exception e) {
			logger.error("Exception occurred while executing getScheduleJobsFromDB: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
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
	public void saveScheduledJob(String taskID, String nextRun, String partnerFacingInterface, String nextState, String updUserID, String serviceName, String comments) throws EtkDataAccessException{
		try {
			etkDao.saveScheduledJob(taskID, nextRun, partnerFacingInterface, nextState, updUserID, serviceName, comments);
		} catch (Exception e) {
			logger.error("Exception occurred while executing saveScheduledJob: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Removes the scheduled job.
	 *
	 * @param taskID the task ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void removeScheduledJob(String taskID) throws EtkDataAccessException {
		try {
			etkDao.removeScheduledJob(taskID);
		} catch (Exception e) {
			logger.error("Exception occurred while executing removeScheduledJob: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Save issuance recon record.
	 *
	 * @param primeIssuanceRecon the prime issuance recon
	 * @return the string
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String saveIssuanceReconRecord(List<PrimeIssuanceReconRecord> primeIssuanceRecon) throws EtkDataAccessException {
		try {
			return etkDao.saveIssuanceReconRecord(primeIssuanceRecon);
		} catch (Exception e) {
			logger.error("Exception occurred while executing saveIssuanceReconRecord: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Validate issuance recon record.
	 *
	 * @param reconFileName the recon file name
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<PrimeIssuanceReconRecord> validateIssuanceReconRecord(String reconFileName) throws EtkDataAccessException {
		try {
			return etkDao.validateIssuanceReconRecord(reconFileName);
		} catch (Exception e) {
			logger.error("Exception occurred while executing validateIssuanceReconRecord: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Gets the active event notification targets.
	 *
	 * @return the active event notification targets
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EventNotificationTarget> getActiveEventNotificationTargets() throws EtkDataAccessException {
		try {
			return etkDao.getActiveEventNotificationTargets();
		} catch (Exception e) {
			logger.error("Exception occurred while executing getActiveEventNotificationTargets: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}

	
	/**
	 * Update event notification target.
	 *
	 * @param eventNotificationTarget the event notification target
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateEventNotificationTarget(EventNotificationTarget eventNotificationTarget) throws EtkDataAccessException {
		try {
			etkDao.updateEventNotificationTarget(eventNotificationTarget);
		} catch (Exception e) {
			logger.error("Exception occurred while executing updateEventNotificationTarget: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
	}
	
	/**
	 * Save ticket dispute.
	 *
	 * @param disputeTicket the dispute ticket
	 * @return true, if successful
	 * @throws DataIntegrityViolationException the data integrity violation exception
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Integer> saveTicketDispute(final TicketDispute disputeTicket) throws DataIntegrityViolationException, EtkDataAccessException {
		try {
			return etkDao.saveTicketDispute(disputeTicket);
		} catch (DataIntegrityViolationException dve) {
			logger.error("Duplicate dispute found, contraventionNO: {}, exception: {}", disputeTicket.getContravention().getNumber(), dve.toString() + "; " + dve.getMessage());
			throw dve;
		} catch (Exception e) {
			logger.error("Exception occurred while executing saveTicketDisputeKPI: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Save dispute status update.
	 *
	 * @param disputeStatusUpdate the dispute status update
	 * @return true, if successful
	 * @throws DataIntegrityViolationException the data integrity violation exception
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Integer> saveDisputeStatusUpdate(final DisputeStatusUpdate disputeStatusUpdate) throws DataIntegrityViolationException, EtkDataAccessException {
		try {
			return etkDao.saveDisputeStatusUpdate(disputeStatusUpdate);
		} catch (DataIntegrityViolationException dve) {
			logger.error("Duplicate ticket status update found, contraventionNO: {}, exception: {}", disputeStatusUpdate.getContravention().getNumber(), dve.toString() + "; " + dve.getMessage());
			throw dve;
		} catch (Exception e) {
			logger.error("Exception occurred while executing saveDisputeStatusUpdateKPI: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);
		}
		return null;
	}
	
	/**
	 * Retrieve events by start event ID.
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
	public List<Event> retrieveEvents(Integer startEventID, Integer endEventID, String startEventDate, String endEventDate, Integer pageSize, String eventTypes, String issuingAgencyCodeList) throws EtkDataAccessException {
		try {
			List<Event> events = etkDao.retrieveEvents(startEventID, endEventID, startEventDate, endEventDate, pageSize, eventTypes, issuingAgencyCodeList);
			return events;
		} catch (Exception e) {
			logger.error("Exception occurred while executing retrieveEventsByStartEventID: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Retrieve events kafka.
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
	public List<KafkaEvent> retrieveEvents_kafka(Integer startEventID, Integer endEventID, String startEventDate, String endEventDate, Integer pageSize, String eventTypes) throws EtkDataAccessException {
		try {
			List<KafkaEvent> events = etkDao.retrieveEvents_kafka(startEventID, endEventID, startEventDate, endEventDate, pageSize, eventTypes, null);
			return events;
		} catch (Exception e) {
			logger.error("Exception occurred while executing retrieveEventsByStartEventID: {}", e.toString() + "; " + e.getMessage());
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Record dispute findings.
	 *
	 * @param etkDisputeFindings the etk dispute findings
	 * @return the string
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String recordDisputeFindings(List<EtkDisputeFinding> etkDisputeFindings) throws EtkDataAccessException {
		String returnStr = null;
		try {
			returnStr = etkDao.recordDisputeFindings(etkDisputeFindings);
		} catch (Exception e) {
			returnStr = "Exception occurred while executing recordDisputeFindings: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return returnStr;
	}
	
	/**
	 * Gets the evt files stats.
	 *
	 * @param fileNames a list of comma deliminated file names
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<FTPFileInfo> GetEvtFilesStats(final String fileNames) throws EtkDataAccessException {
		try {
			return etkDao.GetEvtFilesStats(fileNames);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing GetEvtFilesStats: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return null;
	}
	
	/**
	 * Delete ticket by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void deleteTicketByTicketNO(final String ticketNO) throws EtkDataAccessException {
		try {
			etkDao.deleteTicketByTicketNO(ticketNO);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing deleteTicketByTicketNO: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
	}
	
	/**
	 * Gets the audit records by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @return the audit records by ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<AuditRecord> getAuditRecordsByTicketNO(final String ticketNO) throws EtkDataAccessException {
		try {
			return etkDao.getAuditRecordsByTicketNO(ticketNO);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing getAuditRecordsByTicketNO: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return null;
	}
	
	/**
	 * Update agency latest mre date.
	 *
	 * @param agencyCD the agency CD
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateAgencyLatestMreDate(String agencyCD) throws EtkDataAccessException {
		try {
			etkDao.updateAgencyLatestMreDate(agencyCD);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing updateAgencyLatestMreDate: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
	}
	
	/**
	 * Gets the DB version.
	 *
	 * @return the DB version
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String getDBVersion() throws EtkDataAccessException {
		try {
			return etkDao.getDBVersion();
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing getDBVersion: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return null;
	}
	
	/**
	 * Gets the associated unresolved errors.
	 *
	 * @param errorID the error ID
	 * @return the associated errors
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkError> getAssociatedErrorsUnresolved(final String errorID) throws EtkDataAccessException {
		try {
			return etkDao.getAssociatedErrorsUnresolved(errorID);
		} catch (Exception e) {
			String returnStr = "Exception occurred while getting associated errors: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return null;
	}
	
	/**
	 * Gets the tickets sent today.
	 *
	 * @return the tickets sent today
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkTicket> getTicketsReceivedToday() throws EtkDataAccessException {
		try {
			return etkDao.getTicketsReceivedToday();
		} catch (Exception e) {
			String returnStr = "Exception occurred while getting tickets sent today: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return null;
	}
	
	/**
	 * Gets the tickets not sent.
	 *
	 * @return the tickets not sent
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkStatisticsTicketErrorDetail> getTicketsNotSent() throws EtkDataAccessException {
		try {
			return etkDao.getTicketsNotSent();
		} catch (Exception e) {
			String returnStr = "Exception occurred while getting tickets not sent: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return null;
	}
	
	/**
	 * Update errors by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @param errorStatus the error status
	 * @param updUserID the upd user ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateErrorsByTicketID(Integer ticketID, String errorStatus, String updUserID) throws EtkDataAccessException {
		try {
			etkDao.updateErrorsByTicketID(ticketID, errorStatus, updUserID);
		} catch (Exception e) {
			String returnStr = "Exception occurred while updating error status: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
	}
	
	/**
	 * Gets the next queued dispute record.
	 * 
	 * @param dataTypeCode the data type code, values: DISPUTE or DISPUTE_STATUS_UPDATE
	 * @return the etk queued dispute record
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public EtkQueuedDisputeRecord GetNextQueuedDisputeRecord(String dataTypeCode) throws EtkDataAccessException {
		try {
			return etkDao.GetNextQueuedDisputeRecord(dataTypeCode);
		} catch (Exception e) {
			String returnStr = "Exception occurred while getting next queued dispute record: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return null;
	}
	
	/**
	 * Update dispute record.
	 *
	 * @param processID the process ID
	 * @param newProcessState the new process state
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateDisputeRecord(int processID, EnumProcessState newProcessState) throws EtkDataAccessException {
		try {
			etkDao.updateDisputeRecord(processID, newProcessState);
		} catch (Exception e) {
			String returnStr = "Exception occurred while updating dispute record: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
	}
	
	/**
	 * Update ticke process state by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @param interfaceNM the interface NM
	 * @param ticketProcesState the ticket proces state
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void UpdateTickeProcessStateByTicketID(final Integer ticketID, final EnumInterface interfaceNM, final EnumProcessState ticketProcesState) throws EtkDataAccessException {
		try {
			etkDao.UpdateTickeProcessStateByTicketID(ticketID, interfaceNM, ticketProcesState);
		} catch (Exception e) {
			String returnStr = "Exception occurred while updating ticket processing state: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
	}
	
	/**
	 * Creates the ticket KPI details event.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> createTicketKPIDetailsEvent(Integer ticketID) throws EtkDataAccessException {
		try {
			return etkDao.createTicketKPIDetailsEvent(ticketID);
		} catch (Exception e) {
			String returnStr = "Exception occurred while creating ticket kpi details and event: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		} 
		return null;
	}
	
	/**
	 * Gets the active agencies.
	 *
	 * @return the active agencies
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkAgencyCodeText> getActiveAgencies() throws EtkDataAccessException {
		try {
			return etkDao.getActiveAgencies();
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing getActiveAgencies: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Delete ticket by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void deleteTicketByTicketID(final Integer ticketID) throws EtkDataAccessException {
		try {
			etkDao.deleteTicketByTicketID(ticketID);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing deleteTicketByTicketID: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
	}
	
	/**
	 * Delete dup tickets by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void deleteDupTicketsByTicketID(final Integer ticketID) throws EtkDataAccessException {
		try {
			etkDao.deleteDupTicketsByTicketID(ticketID);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing deleteTicketByTicketID: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
	}
	
	/**
	 * Creates/Updates the working data.
	 *
	 * @param componentName the component name
	 * @param dataName the data name
	 * @param dataValue the data value
	 * @param lockNumber the lock number
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Boolean createUpdateWorkingData(String componentName, String dataName, String dataValue, Integer lockNumber) throws EtkDataAccessException {
		try {
			Boolean isSuccessful = etkDao.createUpdateWorkingData(componentName, dataName, dataValue, lockNumber);
			if (!Boolean.TRUE.equals(isSuccessful)) {
				logger.error("The pod doesn't have permission to create/update the working data[componentName: {}, dataName: {}, lockNumber: {}]", componentName, dataName, lockNumber);
			}
			return isSuccessful;
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing createUpdateWorkingData: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		
		return null;
	}
	
	/**
	 * Creates update working data lead pod.
	 *
	 * @param componentName the component name
	 * @param podInfo the pod info
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Integer createUpdateWorkingDataLeadPod(String componentName, String podInfo) throws EtkDataAccessException {
		try {
			return etkDao.createUpdateWorkingDataLeadPod(componentName, podInfo);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing createUpdateWorkingDataLeadPod: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Am I lead pod.
	 *
	 * @param componentName the component name
	 * @param podInfo the pod info
	 * @return the boolean
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Boolean amILeadPod(String componentName, String podInfo) throws EtkDataAccessException {
		try {
			return etkDao.amILeadPod(componentName, podInfo);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing amILeadPod: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Gets the working data.
	 *
	 * @param componentName the component name
	 * @param dataName the data name
	 * @return the working data
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkWorkingData> getWorkingData(String componentName, String dataName) throws EtkDataAccessException {
		try {
			return etkDao.getWorkingData(componentName, dataName);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing getWorkingData: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Gets the lead pod info.
	 *
	 * @param componentName the component name
	 * @param dataName the data name
	 * @return the lead pod info
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String getLeadPodInfo(String componentName, String dataName) throws EtkDataAccessException {
		try {
			List<EtkWorkingData> rtnObj = etkDao.getWorkingData(componentName, dataName);
			if (rtnObj != null && rtnObj.size() == 1 && rtnObj.get(0) != null) {
				return rtnObj.get(0).getDataValue();
			}
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing getLeadPodInfo: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Clears working data.
	 *
	 * @param componentName the component name
	 * @param dataName the data name
	 * @param lockNumber the current lockNumber
	 * @return the clear data
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Boolean clearData(String componentName, String dataName, Integer lockNumber) throws EtkDataAccessException {
		try {
			Boolean isSuccessful = etkDao.clearWorkingData(componentName, dataName, lockNumber);
			if (!Boolean.TRUE.equals(isSuccessful)) {
				logger.error("The pod doesn't have permission to clear working data [componentName: {}, dataName: {}, lockNumber: {}]", componentName, dataName, lockNumber);
				return null;
			}
			return isSuccessful;
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing getClearWorkingData: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}

	/**
	 * Gets the known users.
	 *
	 * @return the known users
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<String> getKnownUsers() throws EtkDataAccessException {
		try {
			return etkDao.getKnownUsers();
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing getKnownUsers: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Validate ticket.
	 *
	 * @param sourceXML the source XML
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> validateTicket(final String sourceXML) throws EtkDataAccessException {
		try {
			return etkDao.validateTicket(sourceXML);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing validateTicket: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Sets the ticke status to sent.
	 *
	 * @param ticketID the ticket ID
	 * @param interfaceNM the interface NM
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void SetTickeStatusToSent(final Integer ticketID, final EnumInterface interfaceNM) throws EtkDataAccessException {
		try {
			etkDao.SetTickeStatusToSent(ticketID, interfaceNM);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing SetTickeStatusToSent: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
	}
	
	/**
	 * Check for stuck tickets.
	 *
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Object> CheckForStuckTickets() throws EtkDataAccessException {
		try {
			return etkDao.checkForStuckTickets();
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing checkForStuckTickets: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Gets the dispute records.
	 *
	 * @param dataTypeCode the data type code
	 * @param processStateTypeCode the process state type code
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public List<EtkQueuedDisputeRecord> GetDisputeRecords(String dataTypeCode, String processStateTypeCode) throws EtkDataAccessException {
		try {
			return etkDao.GetDisputeRecords(dataTypeCode, processStateTypeCode);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing GetDisputeRecords: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
	
	/**
	 * Banking fee calculation.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<EnumTicketType, BankingFeePerTicketType> bankingFeeCalculation(String startDate, String endDate) throws EtkDataAccessException {
		try {
			return etkDao.bankingFeeCalculation(startDate, endDate);
		} catch (Exception e) {
			String returnStr = "Exception occurred while executing bankingFeeCalculation: " + e.toString() + "; " + e.getMessage();
			logger.error(returnStr);
			dbExceptionHandling(e);			
		}
		return null;
	}
}
