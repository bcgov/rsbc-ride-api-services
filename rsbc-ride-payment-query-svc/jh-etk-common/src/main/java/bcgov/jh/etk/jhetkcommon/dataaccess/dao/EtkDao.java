/*
 * 
 */
package bcgov.jh.etk.jhetkcommon.dataaccess.dao;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.ETKStoredProcedure;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CreateErrorStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CreateIssuanceReconStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CreateJobStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CreateTicketKPIDetailsEventStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CreateTicketStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CreateUpdateWorkingDataLeadPodStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CreateUpdateWorkingDataStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.DeleteErrorsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.DeleteJobStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.DeleteOldTicketsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.DeleteSmoketestTicketStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.DeleteFileErrorTicketByTicketIDStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetActiveAgenciesStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.DeleteAllTicketDataStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.DeleteDupErrorTicketsByTicketIDStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateErrorsByTicketIDStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetActiveEventNotificationTargetsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetAgencyStatsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetAssociatedErrorsUnresolvedStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetAuditsByTicketNOStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.ClearWorkingDataStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetDBVersionStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetDisputeRecordsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetErrorsByTicketIDsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetIcbcPaymentCodeStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetJobsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetKnownUsersStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetNextQueuedDisputeRecordStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetNextQueuedTicketStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetProcessStatesByFileNamesStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetReportDataStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetTicketCntStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetTicketKpiDetailsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetTicketStatsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetTicketsByEvtstatesStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetTicketsKpiDetailsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetTicketsNotSentStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetTicketsReceivedTodayStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.GetWorkingDataStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.LogErrorAccessStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.LogErrorCommentStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.LogTicketAccessStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.AmILeadPodStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CalculateBankingFeeStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CheckForStuckTicketsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.CreateDisputeRecordStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RecordDisputeFindingstoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RecordIssuanceEventStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RecordPaymentEventStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RecordQueryEventStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RetrieveErrorCommentStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RetrieveErrorStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RetrieveEventsStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RetrieveInterfaceStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RetrieveTicketByTicketNOStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.RetrieveTicketStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.SetTicketStatusToSentStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateAgencyLatestMreDateStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateDisputeRecordStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateErrorStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateEventNotificationTargetStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateInterfaceStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateTicketDataStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.UpdateTicketProcessStateByTicketIDStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.ValidateIssuanceReconStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl.ValidateTicketStoredProc;
import bcgov.jh.etk.jhetkcommon.dataaccess.type.EtkDisputeFindingTypeArray;
import bcgov.jh.etk.jhetkcommon.dataaccess.type.IssuanceReconTypeArray;
import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.AuditRecord;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.EtkAgencyCodeText;
import bcgov.jh.etk.jhetkcommon.model.EtkAgencyStatistics;
import bcgov.jh.etk.jhetkcommon.model.EtkDisputeFinding;
import bcgov.jh.etk.jhetkcommon.model.EtkError;
import bcgov.jh.etk.jhetkcommon.model.EtkReport;
import bcgov.jh.etk.jhetkcommon.model.EtkReportEvents;
import bcgov.jh.etk.jhetkcommon.model.EtkReportKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.EtkReportViolationDetails;
import bcgov.jh.etk.jhetkcommon.model.TicketStatisticsDisputeCnt;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsDisputeTicketStatsPerAgency;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsMREDatesPerAgency;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketCntPerMrePerAgency;
import bcgov.jh.etk.jhetkcommon.model.EtkTicketCntStatistics;
import bcgov.jh.etk.jhetkcommon.model.EtkWorkingData;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketErrorDetail;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketErrorDetailWrapper;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketMREStatsPerAgency;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketStateCnt;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketStatsPerAgency;
import bcgov.jh.etk.jhetkcommon.model.EtkStatisticsTicketViolationStatsPerAgency;
import bcgov.jh.etk.jhetkcommon.model.EtkTicket;
import bcgov.jh.etk.jhetkcommon.model.EventNotificationTarget;
import bcgov.jh.etk.jhetkcommon.model.FTPFileInfo;
import bcgov.jh.etk.jhetkcommon.model.KeyValue;
import bcgov.jh.etk.jhetkcommon.model.PrimeIssuanceReconRecord;
import bcgov.jh.etk.jhetkcommon.model.BankingFeePerTicketType;
import bcgov.jh.etk.jhetkcommon.model.TicketDBPaymentTxnDetail;
import bcgov.jh.etk.jhetkcommon.model.TicketDisputeKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.MonthlyPaymentTxnDetailPerTicketTypeCardType;
import bcgov.jh.etk.jhetkcommon.model.PaymentCardBankingFeeRateDetail;
import bcgov.jh.etk.jhetkcommon.model.TicketPaymentKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.BankingFeePerTicketTypeCardType;
import bcgov.jh.etk.jhetkcommon.model.disputestatusupdate.DisputeStatusUpdate;
import bcgov.jh.etk.jhetkcommon.model.EtkErrorComments;
import bcgov.jh.etk.jhetkcommon.model.EtkQueuedDisputeRecord;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumAuditEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumCardType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorStatus;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import bcgov.jh.etk.jhetkcommon.model.eventing.Event;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.KafkaEvent;
import bcgov.jh.etk.jhetkcommon.model.task.TaskDetails;
import bcgov.jh.etk.jhetkcommon.model.ticketdispute.TicketDispute;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import bcgov.jh.etk.jhetkcommon.util.EvtEventingUtil;
import bcgov.jh.etk.jhetkcommon.util.KafkaEvtEventingUtil;
import bcgov.jh.etk.jhetkcommon.util.StringUtil;

/**
 * The Class VPHDao.
 * @author HLiang
 */
@Repository
public class EtkDao {
	
	/** The retrieve error stored proc. */
	@Autowired
	private RetrieveErrorStoredProc retrieveErrorStoredProc;
	
	/** The update ticket data stored proc. */
	@Autowired
	private UpdateTicketDataStoredProc updateTicketDataStoredProc;
	
	/** The update error stored proc. */
	@Autowired
	private UpdateErrorStoredProc updateErrorStoredProc;
	
	/** The log error access stored proc. */
	@Autowired
	private LogErrorAccessStoredProc logErrorAccessStoredProc;
	
	/** The log ticket access stored proc. */
	@Autowired
	private LogTicketAccessStoredProc logTicketAccessStoredProc;
	
	/** The log error comment stored proc. */
	@Autowired
	private LogErrorCommentStoredProc logErrorCommentStoredProc;
	
	/** The retrieve error comment stored proc. */
	@Autowired
	private RetrieveErrorCommentStoredProc retrieveErrorCommentStoredProc;
	
	/** The retrieve ticket stored proc. */
	@Autowired
	private RetrieveTicketStoredProc retrieveTicketStoredProc;
	
	@Autowired
	private RetrieveTicketByTicketNOStoredProc retrieveTicketByTicketNOStoredProc;
	
	/** The get ticket statistics stored proc. */
	@Autowired
	private GetTicketStatsStoredProc getTicketStatsStoredProc;
	
	@Autowired
	private GetTicketCntStoredProc getTicketCntStoredProc;
	
	@Autowired
	private GetAgencyStatsStoredProc getAgencyStatsStoredProc;
	
	/** The create error stored proc. */
	@Autowired
	private CreateErrorStoredProc createErrorStoredProc;
	
	/** The get report data stored proc. */
	@Autowired
	private GetReportDataStoredProc getReportDataStoredProc;
	
	/** The create ticket stored proc. */
	@Autowired
	private CreateTicketStoredProc createTicketStoredProc;
	
	/** The get icbc payment code stored proc. */
	@Autowired
	private GetIcbcPaymentCodeStoredProc getIcbcPaymentCodeStoredProc;
	
	/** The get next queued ticket stored proc. */
	@Autowired
	private GetNextQueuedTicketStoredProc getNextQueuedTicketStoredProc;
	
	/** The get tickets by evtstates stored proc. */
	@Autowired
	private GetTicketsByEvtstatesStoredProc getTicketsByEvtstatesStoredProc;
	
	/** The get ticket kpi details stored proc. */
	@Autowired
	private GetTicketKpiDetailsStoredProc getTicketKpiDetailsStoredProc;
	
	/** The get tickets kpi details stored proc. */
	@Autowired
	private GetTicketsKpiDetailsStoredProc getTicketsKpiDetailsStoredProc;
	

	/** The record issuance event stored proc. */
	@Autowired
	private RecordIssuanceEventStoredProc recordIssuanceEventStoredProc;
	
	/** The record payment event stored proc. */
	@Autowired
	private RecordPaymentEventStoredProc recordPaymentEventStoredProc;
	
	/** The record query event stored proc. */
	@Autowired
	private RecordQueryEventStoredProc recordQueryEventStoredProc;
	
	/** The retrieve interface stored proc. */
	@Autowired
	private RetrieveInterfaceStoredProc retrieveInterfaceStoredProc;
	
	/** The update interface stored proc. */
	@Autowired
	private UpdateInterfaceStoredProc updateInterfaceStoredProc;
	
	/** The delete old tickets stored proc. */
	@Autowired
	private DeleteOldTicketsStoredProc deleteOldTicketsStoredProc;
	
	@Autowired
	private DeleteErrorsStoredProc deleteErrorsStoredProc;
	
	/** The delete smoketest ticket stored proc. */
	@Autowired
	private DeleteSmoketestTicketStoredProc deleteSmoketestTicketStoredProc;
	
	@Autowired
	private CreateJobStoredProc createJobStoredProc;
	
	@Autowired
	private DeleteJobStoredProc deleteJobStoredProc;
	
	@Autowired
	private GetJobsStoredProc getJobsStoredProc;
	
	@Autowired
	private CreateIssuanceReconStoredProc createIssuanceReconStoredProc;
	
	@Autowired
	private ValidateIssuanceReconStoredProc validateIssuanceReconStoredProc;
	
	@Autowired
	private UpdateEventNotificationTargetStoredProc updateEventNotificationTargetStoredProc;
	
	@Autowired
	private GetActiveEventNotificationTargetsStoredProc getActiveEventNotificationTargetsStoredProc;
	
	@Autowired
	private CreateDisputeRecordStoredProc createDisputeRecordStoredProc;
	
	@Autowired
	private RetrieveEventsStoredProc retrieveEventsStoredProc;
	
	@Autowired
	private RecordDisputeFindingstoredProc recordDisputeFindingstoredProc;
	
	@Autowired
	private GetProcessStatesByFileNamesStoredProc getProcessStatesByFileNamesStoredProc;
	
	@Autowired
	private DeleteAllTicketDataStoredProc deleteTicketStoredProc;
	
	@Autowired
	private GetAuditsByTicketNOStoredProc getAuditsByTicketNOStoredProc;
	
	@Autowired
	private UpdateAgencyLatestMreDateStoredProc updateAgencyLatestMreDateStoredProc;
	
	@Autowired
	private GetDBVersionStoredProc getDBVersionStoredProc;
	
	@Autowired
	private GetAssociatedErrorsUnresolvedStoredProc getAssociatedErrorsStoredProc;
	
	@Autowired
	private GetTicketsReceivedTodayStoredProc getTicketsReceivedTodayStoredProc;
	
	@Autowired
	private GetTicketsNotSentStoredProc getTicketsNotSentStoredProc;
	
	@Autowired
	private UpdateErrorsByTicketIDStoredProc updateErrorsByTicketIDStoredProc;
	
	@Autowired
	private GetNextQueuedDisputeRecordStoredProc getNextQueuedDisputeRecordStoredProc;
	
	@Autowired
	private UpdateDisputeRecordStoredProc updateDisputeRecordStoredProc;
	
	@Autowired
	private UpdateTicketProcessStateByTicketIDStoredProc updateTicketProcessStateByTicketIDStoredProc;
	
	@Autowired
	private GetErrorsByTicketIDsStoredProc getErrorsByTicketIDsStoredProc;
	
	@Autowired
	private CreateTicketKPIDetailsEventStoredProc createTicketKPIDetailsEventStoredProc;
	
	@Autowired
	private GetActiveAgenciesStoredProc getActiveAgenciesStoredProc;
	
	@Autowired
	private DeleteFileErrorTicketByTicketIDStoredProc deleteTicketByTicketIDStoredProc;
	
	@Autowired
	private DeleteDupErrorTicketsByTicketIDStoredProc deleteDupErrorTicketsByTicketIDStoredProc;
	
	@Autowired
	private CreateUpdateWorkingDataStoredProc createUpdateWorkingDataStoredProc;
	
	@Autowired
	private CreateUpdateWorkingDataLeadPodStoredProc createUpdateWorkingDataLeadPodStoredProc;

	@Autowired
	private AmILeadPodStoredProc amILeadPodStoredProc;
	
	@Autowired
	private GetWorkingDataStoredProc getWorkingDataStoredProc;
	
	@Autowired
	private ClearWorkingDataStoredProc clearWorkingDataStoredProc;
	
	@Autowired
	private GetKnownUsersStoredProc getKnownUsersStoredProc;

	@Autowired
	private ValidateTicketStoredProc validateTicketStoredProc;
	
	@Autowired
	private SetTicketStatusToSentStoredProc setTicketStatusToSentStoredProc;
	
	@Autowired
	private CheckForStuckTicketsStoredProc checkForStuckTicketsStoredProc;
	
	@Autowired
	private GetDisputeRecordsStoredProc getDisputeRecordsStoredProc ;
	
	@Autowired
	private CalculateBankingFeeStoredProc calculateBankingFeeStoredProc ;
	
	/** The Constant log. */
	private static final Logger log = LoggerFactory.getLogger(EtkDao.class);

	
	/**
	 * Gets the VPH errors.
	 *
	 * @param startDateTime the start date time
	 * @param endDateTime the end date time
	 * @param errorStatuses the error statuses
	 * @param errorID the error ID
	 * @return the VPH errors
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<EtkError> getVPHErrors(final String startDateTime, final String endDateTime, final List<EnumErrorStatus> errorStatuses, final String errorID) {
		
		//Pass jdbcTemlate and name of the stored Procedure.
		ETKStoredProcedure sp = (ETKStoredProcedure) retrieveErrorStoredProc.getStoredProc();
		
		String errorStatusesStr = errorStatuses == null ? null : errorStatuses.stream().map(EnumErrorStatus::getCodeValue).collect(Collectors.joining(","));
		
		//Call stored procedure
		Map<String, Object> result = sp.execute(errorID == null ? null : new Integer(errorID), startDateTime, endDateTime, errorStatusesStr);
		if (result == null || result.get(RetrieveErrorStoredProc.PARAM_OUT_RECORDS) == null) {
			return null;
		}
					
		return (List<EtkError>)result.get(RetrieveErrorStoredProc.PARAM_OUT_RECORDS);
	}
	
	/**
	 * Update ticketdata.
	 *
	 * @param ticketID the ticket ID
	 * @param outboundXML the outbound XML
	 */
	@Transactional
	public void updateTicketdata(final Integer ticketID, final String outboundXML) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure) updateTicketDataStoredProc.getStoredProc();
		sp.execute(ticketID, outboundXML);
	}
	
	/**
	 * Gets the comments by error ID.
	 *
	 * @param errorID the error ID
	 * @return the comments by error ID
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<EtkErrorComments> getCommentsByErrorID(final String errorID) {
		//Pass jdbcTemlate and name of the stored Procedure.
		ETKStoredProcedure sp = (ETKStoredProcedure) retrieveErrorCommentStoredProc.getStoredProc();
		
		//Call stored procedure
		Map<String, Object> result = sp.execute(new Integer(errorID));
		if (result == null) {
			return null;
		}
		if (result.get(RetrieveErrorCommentStoredProc.PARAM_OUT_RECORDS) == null) {
			return null;
		}
		return (List<EtkErrorComments>)result.get(RetrieveErrorCommentStoredProc.PARAM_OUT_RECORDS);
	}
	

	/**
	 * Update error.
	 *
	 * @param errorID the error ID
	 * @param statusCD the status CD
	 * @param subjectUserID the subject user ID
	 */
	@Transactional
	public void updateError(final String errorID, final EnumErrorStatus statusCD, final String subjectUserID) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure) updateErrorStoredProc.getStoredProc();
		sp.execute(new Integer(errorID), statusCD.getCodeValue(), subjectUserID);
	}
	

	/**
	 * Log user access.
	 *
	 * @param errorID the error ID
	 * @param accessingUserID the accessing user ID
	 * @param auditEventType the audit event type
	 * @return the integer
	 */
	@Transactional
	public Integer logUserAccess(final String errorID, final String accessingUserID, final EnumAuditEventType auditEventType) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure) logErrorAccessStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(new Long(errorID), accessingUserID, auditEventType.getCodeValue());
		if (result == null) {
			return null;
		}
		return (Integer) result.get(LogErrorAccessStoredProc.PARAM_OUT_P_AUDIT_EVENT_ID);
	}
	
	
	/**
	 * Log user ticket access.
	 *
	 * @param ticketID the ticket ID
	 * @param accessingUserID the accessing user ID
	 * @param auditEventType the audit event type
	 * @return the integer
	 */
	@Transactional
	public Integer logUserTicketAccess(final Integer ticketID, final String accessingUserID, final EnumAuditEventType auditEventType) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure) logTicketAccessStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(ticketID, accessingUserID, auditEventType.getCodeValue());
		if (result == null) {
			return null;
		}
		return (Integer) result.get(LogTicketAccessStoredProc.PARAM_OUT_P_AUDIT_EVENT_ID);
	}
	
	/**
	 * Log user comment.
	 *
	 * @param errorID the error ID
	 * @param authorUserID the author user ID
	 * @param comments the comments
	 */
	@Transactional
	public void logUserComment(final String errorID, final String authorUserID, final String comments) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure) logErrorCommentStoredProc.getStoredProc();
		sp.execute(new Integer(errorID), authorUserID, comments);
	}
	
	/**
	 * Gets the ticket detail.
	 *
	 * @param ticketID the ticket ID
	 * @return the ticket detail
	 */
	@Transactional
	public EtkTicket getTicketDetail(final Integer ticketID) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure)retrieveTicketStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ticketID);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<EtkTicket> ticket = (List<EtkTicket>)result.get(RetrieveTicketStoredProc.PARAM_OUT_RECORDS);
		if (ticket == null || ticket.isEmpty()) {
			return null;
		}
		if (ticket.size() > 1) {
			log.error("Multiple tickets found for ticket ID: {}", ticketID);
		}
		return ticket.get(0);
	}
	
	
	/**
	 * Gets the ticket detail by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @return the ticket detail by ticket NO
	 */
	@Transactional
	public List<EtkTicket> getTicketDetailByTicketNO(final String ticketNO) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure)retrieveTicketByTicketNOStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ticketNO);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<EtkTicket> ticket = (List<EtkTicket>)result.get(RetrieveTicketStoredProc.PARAM_OUT_RECORDS);
		
		return ticket;
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
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public EtkStatisticsTicketErrorDetailWrapper getTicketStatistics(OffsetDateTime endEnteredDT, Integer displayStart, Integer displaySize, String searchText,
			String sortField, String sortDirection) {
		ETKStoredProcedure sp = (ETKStoredProcedure) getTicketStatsStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(endEnteredDT, displayStart, displaySize, searchText, sortField, sortDirection);
		if (result == null) {
			return null;
		}
		
		ArrayList<EtkStatisticsTicketErrorDetail> tickets = (ArrayList<EtkStatisticsTicketErrorDetail>) result.get(GetTicketStatsStoredProc.PARAM_OUT_P_TICKETS_DETAIL_NOT_PURGED);
		Integer totalNumberOfTickets = (Integer) result.get(GetTicketStatsStoredProc.PARAM_OUT_p_total_num_of_tickets);

		// re-build finalTicketErrorDetail.
		ArrayList<EtkStatisticsTicketErrorDetail> finalTicketErrorDetail = new ArrayList<EtkStatisticsTicketErrorDetail>();
		
		for (EtkStatisticsTicketErrorDetail ticket : tickets) {
			boolean found = false;
			EtkStatisticsTicketErrorDetail ftd = null;
			for (EtkStatisticsTicketErrorDetail ft : finalTicketErrorDetail) {
				if (ft.getTicketDetails().getTicketID().equals(ticket.getTicketDetails().getTicketID())) {
					found = true;
					ftd = ft;
					break;
				}
			}
			// it's a new EtkStatisticsTicketErrorDetail obj, add to the final list
			if (!found) {
				finalTicketErrorDetail.add(ticket);
			} else {
				// found the same EtkStatisticsTicketErrorDetail obj in the final list, append the error to the error list
				if (ftd != null && ftd.getErrors() != null && ticket.getErrors() != null) {
					ftd.getErrors().addAll(ticket.getErrors());
				}
			}
		}
		
		// sort the errors
		for (EtkStatisticsTicketErrorDetail ft : finalTicketErrorDetail) {
			Collections.sort(ft.getErrors());
		}
		EtkStatisticsTicketErrorDetailWrapper wrapper = new EtkStatisticsTicketErrorDetailWrapper();
		wrapper.setTotalNumOfRecords(totalNumberOfTickets);
		wrapper.setStatisticsTicketErrorDetail(finalTicketErrorDetail);
		return wrapper;
	}
	
	/**
	 * Gets the ticket cnt.
	 *
	 * @return the ticket cnt
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public EtkTicketCntStatistics getTicketCnt() {
		ETKStoredProcedure sp = (ETKStoredProcedure) getTicketCntStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		EtkTicketCntStatistics vPHTicketStatistics = new EtkTicketCntStatistics();
		vPHTicketStatistics.setTicketCntToday((String)result.get(GetTicketCntStoredProc.PARAM_OUT_P_TICKET_CNT_TODAY));
		vPHTicketStatistics.setTicketCntNotSent((String)result.get(GetTicketCntStoredProc.PARAM_OUT_P_TICKET_CNT_NOT_SENT));
		vPHTicketStatistics.setErrorCntNotResolved((String)result.get(GetTicketCntStoredProc.PARAM_OUT_P_ERROR_CNT_NOT_RESOLVED));
		vPHTicketStatistics.setTicketCntStatus((ArrayList<EtkStatisticsTicketStateCnt>) result.get(GetTicketCntStoredProc.PARAM_OUT_P_TICKET_CNT_STATUS));
		
		ArrayList<TicketStatisticsDisputeCnt> disputeTicketCntList = (ArrayList<TicketStatisticsDisputeCnt>) result.get(GetTicketCntStoredProc.PARAM_OUT_p_dispute_cnt);
		TreeMap<EnumTicketType, TreeMap<String, Object>> final_disputeTicketCntList = null;
		
		if (disputeTicketCntList != null) {
			final_disputeTicketCntList = new TreeMap<EnumTicketType, TreeMap<String, Object>>();
			// loop through the ticketTypes
			for (TicketStatisticsDisputeCnt e : disputeTicketCntList) {
				// loop through the eventTypes
				for (TicketStatisticsDisputeCnt f : disputeTicketCntList) {
					TreeMap<String, Object> curDisputeTicketCntList = null;
					if (final_disputeTicketCntList.get(e.getTicketType()) != null) {
						curDisputeTicketCntList = final_disputeTicketCntList.get(e.getTicketType());
					} else {
						curDisputeTicketCntList = new TreeMap<String, Object>();
					}
					if (e.getTicketType().equals(f.getTicketType())) {
						curDisputeTicketCntList.put(f.getEventType(), f.getTicketCount());
						final_disputeTicketCntList.put(e.getTicketType(), curDisputeTicketCntList);
					}
				}
			}
			
			// iterate through each ticket type and set count to 0 if missing
			for (EnumTicketType ticketType : EnumTicketType.values()) {
				final_disputeTicketCntList.putIfAbsent(ticketType, new TreeMap<String, Object>());
				
				final_disputeTicketCntList.get(ticketType).putIfAbsent(EnumEventType.DISPUTE.getCodeValue(), (float)0);
				final_disputeTicketCntList.get(ticketType).putIfAbsent(Const.KEY_DISPUTE_RATE, null);
			}
		}
	
		vPHTicketStatistics.setDisputeTicketCntStatus(final_disputeTicketCntList);
		return vPHTicketStatistics;
	}
	
	/**
	 * Gets the agency stats.
	 *
	 * @return the agency stats
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Transactional
	public EtkAgencyStatistics getAgencyStats() {
		ETKStoredProcedure sp = (ETKStoredProcedure) getAgencyStatsStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ApplicationProperties.MRE_VERSIONS_TO_INCLUDE);
		if (result == null) {
			return null;
		}
		
		ArrayList<EtkStatisticsTicketStatsPerAgency> agencyStatsList = (ArrayList<EtkStatisticsTicketStatsPerAgency>) result.get(GetAgencyStatsStoredProc.PARAM_OUT_P_TICKET_STATS_PER_AGENCY);
		ArrayList<EtkStatisticsTicketViolationStatsPerAgency> ticketViolationStatsPerAgency = (ArrayList<EtkStatisticsTicketViolationStatsPerAgency>) result.get(GetAgencyStatsStoredProc.PARAM_OUT_P_VIO_STATS_PER_AGENCY);
		ArrayList<EtkStatisticsTicketMREStatsPerAgency> ticketMREStatsPerAgency = (ArrayList<EtkStatisticsTicketMREStatsPerAgency>) result.get(GetAgencyStatsStoredProc.PARAM_OUT_P_MRE_STATS_PER_AGENCY);
		ArrayList<EtkStatisticsTicketMREStatsPerAgency> ticketMREStatsPerAgencyWarning = (ArrayList<EtkStatisticsTicketMREStatsPerAgency>) result.get(GetAgencyStatsStoredProc.PARAM_OUT_p_mre_stats_per_agency_warning);
		ArrayList<EtkStatisticsMREDatesPerAgency> mreDatesList = (ArrayList<EtkStatisticsMREDatesPerAgency>)result.get(GetAgencyStatsStoredProc.PARAM_OUT_p_agency_mre_dates);
		ArrayList<KeyValue> topCharges = (ArrayList<KeyValue>)result.get(GetAgencyStatsStoredProc.PARAM_OUT_p_top_charge_types);
		ArrayList<EtkStatisticsDisputeTicketStatsPerAgency> disputeStats = (ArrayList<EtkStatisticsDisputeTicketStatsPerAgency>) result.get(GetAgencyStatsStoredProc.PARAM_OUT_p_top_disputed_charge_count_per_agency);
		ArrayList<KeyValue> totalDisputeCntPerAct = (ArrayList<KeyValue>) result.get(GetAgencyStatsStoredProc.PARAM_OUT_p_total_disputed_count_per_act);
		ArrayList<KeyValue> totalDisputeCntPerAgency = (ArrayList<KeyValue>) result.get(GetAgencyStatsStoredProc.PARAM_OUT_p_total_disputed_count_per_agency);
		ArrayList<EtkAgencyCodeText> agencyCodeTextList = (ArrayList<EtkAgencyCodeText>) result.get(GetAgencyStatsStoredProc.PARAM_OUT_p_agency_cd_txt_map);

		EtkAgencyStatistics agencyStatistics = new EtkAgencyStatistics();
		
		//build mreVersions arrayList from the configuration: ApplicationProperties.MRE_VERSIONS_TO_INCLUDE
		String[] mreVersions = ApplicationProperties.MRE_VERSIONS_TO_INCLUDE.split(",");
		
		//set topCharges
		agencyStatistics.setTopChargeTypes(topCharges);
		
		int totalDisputedCount = 0;
		
		// build agencyStatsList
		for (EtkStatisticsTicketStatsPerAgency ag : agencyStatsList) {
			for (EtkStatisticsTicketViolationStatsPerAgency v : ticketViolationStatsPerAgency) {
				if (v.getAgencyCD().equals(ag.getEnforcementAgencyCD())) {
					if ("1".equals(v.getCountType())) {
						ag.setTotalNoOf1CntTicketsIssued(v.getTotalNumTicketPerCntType());
					}
					if ("2".equals(v.getCountType())) {
						ag.setTotalNoOf2CntTicketsIssued(v.getTotalNumTicketPerCntType());
					}
					if ("3".equals(v.getCountType())) {
						ag.setTotalNoOf3CntTicketsIssued(v.getTotalNumTicketPerCntType());
					}
				}
			}
			
			// add the resetDate to EtkStatisticsTicketStatsPerAgency object
			for (EtkStatisticsMREDatesPerAgency mreDate: mreDatesList) {
				if (ag.getEnforcementAgencyCD().equals(mreDate.getAgencyCD())) {
					ag.setResetDate(mreDate.getResetDate());
					ag.setDateOfFirstTicketIssuedUsingLatestMRE(mreDate.getFirstTicketIssuedDateUsingLatestMRE());
	            	break;
	            }
			}
						
			for (EtkStatisticsTicketMREStatsPerAgency mre : ticketMREStatsPerAgency) {
				//add the mreStats obj into EtkStatisticsTicketStatsPerAgency object
				if (mre.getAgencyCD().equals(ag.getEnforcementAgencyCD()) 
						&& isInTheList(mre.getMreMinorVersion(), mreVersions)) {
					EtkStatisticsTicketCntPerMrePerAgency ticketStatsPerMREMinorVersion = new EtkStatisticsTicketCntPerMrePerAgency();
					ticketStatsPerMREMinorVersion.setTicketCountTotal(String.valueOf(mre.getTotalNumTicketPerMREMinorVersion()));
					ticketStatsPerMREMinorVersion.setViolationDateLastTicketIssued(mre.getViolationDtmLastIssuedTicket());
					// add the warning ticket count
					for (EtkStatisticsTicketMREStatsPerAgency mrew : ticketMREStatsPerAgencyWarning) {
						if (mrew.getAgencyCD().equals(mre.getAgencyCD()) && mrew.getMreMinorVersion().equals(mre.getMreMinorVersion())) {
							ticketStatsPerMREMinorVersion.setTicketCountWarning(String.valueOf(mrew.getTotalNumTicketPerMREMinorVersion()));
							break;
						}
					}
					TreeMap<String, EtkStatisticsTicketCntPerMrePerAgency> mreStats = ag.getMreStats();
					mreStats.put(mre.getMreMinorVersion(), ticketStatsPerMREMinorVersion);
				}
			}
			
			// add dispute stats to agencyStatsList
			for (EtkStatisticsDisputeTicketStatsPerAgency disputeStat : disputeStats) {
				if (disputeStat.getAgencyCD().equals(ag.getEnforcementAgencyCD())) {
					TreeMap<String, Integer> disputeStatsMap = ag.getDisputeCntPerChargeType();
					disputeStatsMap.put(disputeStat.getAct_section(), disputeStat.getTotalNumDisputes());
					totalDisputedCount += disputeStat.getTotalNumDisputes();
				}
			}
			
			// map agencyName to agencyStatsList
			for (EtkAgencyCodeText codeText : agencyCodeTextList) {
				if (codeText.getAgencyCD().equals(ag.getEnforcementAgencyCD())) {
					ag.setEnforcementAgencyName(codeText.getAgencyText());
					break;
				}
			}
			
		}
		//add the missing mreVersion into each EtkStatisticsTicketStatsPerAgency object in the list
		for (EtkStatisticsTicketStatsPerAgency ag : agencyStatsList) {
			for (String mv : mreVersions) {
				mv = mv.trim();
				boolean mvFound = false;
				// Getting an iterator 
		        Iterator iterator = ag.getMreStats().entrySet().iterator(); 
		  
		        // Iterate through the hashmap 
		        while (iterator.hasNext()) { 
		            Map.Entry mapElement = (Map.Entry)iterator.next(); 
		            if (mv.equals((String)mapElement.getKey())) {
		            	mvFound = true;
		            	break;
		            }
		        } 
		        //add the mreVersion to the ag list
		        if (!mvFound) {
		        	EtkStatisticsTicketCntPerMrePerAgency ticketStatsPerMREMinorVersion = new EtkStatisticsTicketCntPerMrePerAgency();
		        	ticketStatsPerMREMinorVersion.setTicketCountTotal("0");;
		        	ag.getMreStats().put(mv, ticketStatsPerMREMinorVersion);
		        }
			}
		}
		
		Map<String, String> totalDisputeCntPerActMap = totalDisputeCntPerAct.stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
		agencyStatistics.setDisputeTotalCntPerAct(totalDisputeCntPerActMap);
		
		Map<String, String> totalDisputeCntPerAgencyMap = totalDisputeCntPerAgency.stream().collect(Collectors.toMap(KeyValue::getKey, KeyValue::getValue));
		agencyStatistics.setDisputeTotalCntPerAgency(totalDisputeCntPerAgencyMap);
		
		agencyStatistics.setTotalDisputedCount(totalDisputedCount);
		
		agencyStatistics.setStatisticsTicketStatsPerAgency(agencyStatsList);
		return agencyStatistics;
	}
	
	/**
	 * Checks if is in the list.
	 *
	 * @param mreVersion the mre version
	 * @param mreVersions the mre versions
	 * @return true, if is in the list
	 */
	private boolean isInTheList(String mreVersion, String[] mreVersions) {
		for (String mv : mreVersions) {
			if (mreVersion.equals(mv.trim())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Gets the etk report data.
	 *
	 * @param ticketNO the ticket NO
	 * @param startDate the start date
	 * @param endDate the end date
	 * @param dataTypeFilter the data type filter
	 * @param agencyFilter the agency filter
	 * @return the etk report data
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public EtkReport getEtkReportData(String ticketNO, final String startDate, final String endDate, final String dataTypeFilter, final String agencyFilter) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure)getReportDataStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ticketNO, startDate, endDate, dataTypeFilter, agencyFilter);
		if (result == null) {
			return null;
		}
		EtkReport etkReport = new EtkReport();
		etkReport.setReportEvents((ArrayList<EtkReportEvents>)result.get(GetReportDataStoredProc.PARAM_OUT_P_TICKET_EVENTS));
		ArrayList<EtkReportKPIDetails> kpiDetails = (ArrayList<EtkReportKPIDetails>)result.get(GetReportDataStoredProc.PARAM_OUT_P_TICKET_KPI_DETAILS);
		ArrayList<EtkReportViolationDetails> violations = (ArrayList<EtkReportViolationDetails>)result.get(GetReportDataStoredProc.PARAM_OUT_P_VIOLATION_KPI_DETAILS);
		etkReport.setReportDisputeKPIDetails((ArrayList<TicketDisputeKPIDetails>)result.get(GetReportDataStoredProc.PARAM_OUT_P_DISPUTE_KPI_DETAILS));
		etkReport.setReportDisputeFinding((ArrayList<EtkDisputeFinding>)result.get(GetReportDataStoredProc.PARAM_OUT_P_DISPUTE_FINDING_KPI_DETAILS));
		etkReport.setReportPaymentKPIDetails((ArrayList<TicketPaymentKPIDetails>)result.get(GetReportDataStoredProc.PARAM_OUT_P_payment_kpi_details));;
		etkReport.setReportErrorKPI((ArrayList<EtkError>)result.get(GetReportDataStoredProc.PARAM_OUT_p_error_kpi_details));
		
		//add violations to kpiDetails
		if (kpiDetails != null) {
			for (EtkReportKPIDetails kpi : kpiDetails) {
				BigInteger ticketID = kpi.getTICKET_ID();
				if (violations != null) {
					for (EtkReportViolationDetails v: violations) {
						if (ticketID.equals(v.getTICKET_ID())) {
							kpi.getViolations().put(String.valueOf(v.getCOUNT_NBR()), v);
						}
					}
				}
			}
		}
		
		etkReport.setReportKPIDetails(kpiDetails);
		return etkReport;
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
	 */
	@Transactional
	public Integer createErrorStoredProc(final Integer ticketID, final String errorCategoryCD, final String errorSeverityLevel, final String ticketNO, 
		final String contraventionNO, final String detailTxt, final String serviceNM, final String primeTxt,
		final String ICBCTxt, final String justinTxt, final String errorCD) {
	
		ETKStoredProcedure sp = (ETKStoredProcedure)createErrorStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ticketID, errorCategoryCD, errorSeverityLevel, ticketNO, contraventionNO, detailTxt, 
				serviceNM, primeTxt, ICBCTxt, justinTxt, errorCD);
		if (result == null) {
			return null;
		}
		return (Integer) result.get(CreateErrorStoredProc.PARAM_OUT_P_ERROR_ID);

	}
	
	/**
	 * Creates the ticket store proc.
	 *
	 * @param sourceXML the source XML
	 * @param fileName the file name
	 * @param interfaceName the interface name
	 * @return the integer
	 */
	@Transactional
	public Map<String, Object> createTicketStoreProc(String sourceXML, String fileName, String interfaceName) {
		ETKStoredProcedure sp = (ETKStoredProcedure)createTicketStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(sourceXML, fileName, interfaceName);
		if (result == null) {
			return null;
		}
		
		String ticketNO = (String) result.get(CreateTicketStoredProc.PARAM_OUT_P_TICKET_NO);
		Integer ticket_id = (Integer) result.get(CreateTicketStoredProc.PARAM_OUT_P_TICKET_ID);
		String dupErrorCode = (String) result.get(CreateTicketStoredProc.PARAM_OUT_p_dup_ticket_error_code);
		String ticketProcessState = (String) result.get(CreateTicketStoredProc.PARAM_OUT_p_ticket_process_state);
		String violationTime = (String) result.get(CreateTicketStoredProc.PARAM_OUT_p_violation_time);
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put(Const.KEY_TICKET_NO, ticketNO);
		returnObj.put(Const.KEY_TICKET_ID, ticket_id);
		returnObj.put(Const.KEY_DUP_TICKET_ERROR_CD, dupErrorCode);
		returnObj.put(Const.KEY_TICKET_PROCESS_STATE, ticketProcessState);
		returnObj.put(Const.KEY_VIOLATION_TIME, violationTime);
		return returnObj;
	}
	
	
	/**
	 * Gets the errors by ticket Ids.
	 *
	 * @param ticketIDs the ticket I ds
	 * @return the hash map
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public HashMap<Integer, List<EtkError>> GetErrorsByTicketIDs(final String ticketIDs) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure)getErrorsByTicketIDsStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ticketIDs);
		if (result == null || result.get(GetErrorsByTicketIDsStoredProc.PARAM_OUT_P_RECORDS) == null) {
			return null;
		}
		
		List<EtkError> errors = (List<EtkError>) result.get(GetErrorsByTicketIDsStoredProc.PARAM_OUT_P_RECORDS);
		HashMap<Integer, List<EtkError>> errorMap = null;
		if (errors != null && errors.size() > 0) {
			errorMap = new HashMap<Integer, List<EtkError>>();
			List<EtkError> es = null;
			for (EtkError error : errors) {
				if (error != null) {
					// it's a new item, add it to errorMap
					if (error.getCorrelationTicketID() != null && !errorMap.containsKey(error.getCorrelationTicketID())) {
						es = new ArrayList<EtkError>();
						es.add(error);
						errorMap.put(error.getCorrelationTicketID(), es);
					// the errorMap has an item with the same key, add the error to the existing list
					} else if (error.getCorrelationTicketID() != null && errorMap.containsKey(error.getCorrelationTicketID())) {
							es = (List<EtkError>)errorMap.get(error.getCorrelationTicketID());
							es.add(error);
							errorMap.put(error.getCorrelationTicketID(), es);
					}
				}
			}
		}
		
		return errorMap;
	}
	
	/**
	 * Gets the icbc payment code.
	 *
	 * @param icbcPaymentMsgCode the icbc payment msg code
	 * @return the map
	 */
	public Map<String, String> GetIcbcPaymentCode(final String icbcPaymentMsgCode) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure)getIcbcPaymentCodeStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(new Integer(icbcPaymentMsgCode));
		if (result == null) {
			return null;
		}
		String icbcPaymentMessageDsc = (String) result.get(GetIcbcPaymentCodeStoredProc.PARAM_OUT_P_ICBC_PAYMENT_MESSAGE_DSC);
		String paymentMessageDsc = (String) result.get(GetIcbcPaymentCodeStoredProc.PARAM_OUT_P_PAYMENT_MESSAGE_DSC);
		Map<String, String> returnObj = new HashMap<>();
		returnObj.put(Const.KEY_ICBC_PAYMENT_MESSAGE_DSC, icbcPaymentMessageDsc);
		returnObj.put(Const.KEY_PAYMENT_MESSAGE_DSC, paymentMessageDsc);
		return returnObj;
	}
	
	/**
	 * Gets the next queued ticket.
	 *
	 * @return the etk ticket
	 */
	@Transactional
	public EtkTicket GetNextQueuedTicket() {
		
		ETKStoredProcedure sp = (ETKStoredProcedure)getNextQueuedTicketStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<EtkTicket> tickets = (ArrayList<EtkTicket>)result.get(GetNextQueuedTicketStoredProc.PARAM_OUT_P_RECORDS);
		return tickets != null && tickets.size() > 0 ? tickets.get(0) : null;
	}
	
	/**
	 * Gets the tickets by evtstates.
	 *
	 * @param evtStates the evt states
	 * @return the list
	 */
	@Transactional
	public List<EtkTicket> GetTicketsByEvtstates(List<EnumProcessState> evtStates) {
		
		String evtStatesStr = evtStates == null ? null : evtStates.stream().map(EnumProcessState::getCodeValue).collect(Collectors.joining(","));
		
		ETKStoredProcedure sp = (ETKStoredProcedure)getTicketsByEvtstatesStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(evtStatesStr);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<EtkTicket> tickets = (List<EtkTicket>)result.get(GetTicketsByEvtstatesStoredProc.PARAM_OUT_P_RECORDS);
		return tickets;
	}
	
	/**
	 * Gets the ticket kpi details.
	 *
	 * @param ticketNO the ticket NO
	 * @return the etk report KPI details
	 */
	@Transactional
	public EtkReportKPIDetails GetTicketKpiDetails(final String ticketNO) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure)getTicketKpiDetailsStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ticketNO);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<EtkReportKPIDetails> kpis = (List<EtkReportKPIDetails>)result.get(GetTicketKpiDetailsStoredProc.PARAM_OUT_P_RECORDS);
		return kpis != null && kpis.size() > 0 ? kpis.get(0) : null;
	}
	
	
	/**
	 * Gets the tickets kpi details.
	 *
	 * @param ticketNOs a list of comma deliminated ticket numbers
	 * @return the map
	 */
	@Transactional
	public Map<String, EtkReportKPIDetails> GetTicketsKpiDetails(final String ticketNOs) {
		
		ETKStoredProcedure sp = (ETKStoredProcedure)getTicketsKpiDetailsStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ticketNOs);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<EtkReportKPIDetails> kpis = (List<EtkReportKPIDetails>)result.get(GetTicketsKpiDetailsStoredProc.PARAM_OUT_P_RECORDS);
		
		Map<String, EtkReportKPIDetails> kpiMap = null;
		if (kpis != null && kpis.size() > 0) {
			kpiMap = new HashMap<String, EtkReportKPIDetails>();
			for (EtkReportKPIDetails d : kpis) {
				kpiMap.put(d.getTICKET_NO(), d);
			}
		}
		return kpiMap;
	}
	
	/**
	 * Record issuance event.
	 *
	 * @param ticketNO the ticket NO
	 */
	public void RecordIssuanceEvent(final String ticketNO){
		
		ETKStoredProcedure sp = (ETKStoredProcedure)recordIssuanceEventStoredProc.getStoredProc();
		
		sp.execute(ticketNO);
	}
	
	/**
	 * Record query event.
	 *
	 * @param ticketNO the ticket NO
	 */
	public void RecordQueryEvent(final String ticketNO){
		
		ETKStoredProcedure sp = (ETKStoredProcedure)recordQueryEventStoredProc.getStoredProc();
		
		sp.execute(ticketNO);
	}
	
	/**
	 * Record payment event.
	 *
	 * @param ticketNO the ticket NO
	 * @param paymentAmount the payment amount
	 * @param ticket_type the ticket type
	 * @param cardType the card type
	 * @param receiptNumber the receipt number
	 * @param txn_number the txn number
	 */
	public void RecordPaymentEvent(final String ticketNO, final Double paymentAmount, final EnumTicketType ticket_type, 
			final String cardType, final String receiptNumber, final String txn_number){
		ETKStoredProcedure sp = (ETKStoredProcedure)recordPaymentEventStoredProc.getStoredProc();
		sp.execute(ticketNO, paymentAmount, ticket_type == null ? "" : ticket_type.getCodeValue(), cardType, receiptNumber, txn_number);
	}
	
	/**
	 * Retrieve interface.
	 *
	 * @param interfaceNM the interface NM
	 * @return the map
	 */
	public Map<String, Object> RetrieveInterface(final String interfaceNM) {
		ETKStoredProcedure sp = (ETKStoredProcedure)retrieveInterfaceStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(interfaceNM);
		if (result == null) {
			return null;
		}
		
		String interfaceStateCD = (String) result.get(RetrieveInterfaceStoredProc.PARAM_OUT_P_INTERFACE_STATE_CD);
		Timestamp lastUpdatedDTM = (Timestamp) result.get(RetrieveInterfaceStoredProc.PARAM_OUT_P_LAST_UPDATED_DTM);
		
		Map<String, Object> returnObj = new HashMap<>();
		returnObj.put(Const.KEY_INTEFACE_STATE_CD, EnumInterfaceState.getEnumfromCodeValue(interfaceStateCD));
		returnObj.put(Const.KEY_INTEFACE_LAST_UPDATE_DTM, lastUpdatedDTM == null ? null : lastUpdatedDTM.toLocalDateTime());
		return returnObj;
	}
	
	/**
	 * Update interface.
	 *
	 * @param interfaceNM the interface NM
	 * @param interfaceStateCD the interface state CD
	 * @param updUserID the upd user ID
	 */
	public void UpdateInterface(final EnumInterface interfaceNM, final EnumInterfaceState interfaceStateCD, final String updUserID) {
		ETKStoredProcedure sp = (ETKStoredProcedure)updateInterfaceStoredProc.getStoredProc();
		sp.execute(interfaceNM.getCodeValue(), interfaceStateCD.getCodeValue(), updUserID);
	}
	
	/**
	 * Delete old tickets.
	 */
	public void DeleteOldTickets() {
		log.debug("Delete old tickets");
		ETKStoredProcedure sp = (ETKStoredProcedure)deleteOldTicketsStoredProc.getStoredProc();
		sp.execute();
	}
	
	/**
	 * Delete errors.
	 */
	public void DeleteErrors() {
		log.debug("Delete errors");
		ETKStoredProcedure sp = (ETKStoredProcedure)deleteErrorsStoredProc.getStoredProc();
		sp.execute();
	}
	
	/**
	 * Delete smoketest ticket.
	 */
	public void DeleteSmoketestTicket() {
		log.debug("Delete smoketest ticket");
		ETKStoredProcedure sp = (ETKStoredProcedure)deleteSmoketestTicketStoredProc.getStoredProc();
		sp.execute();
	}

	/**
	 * Gets the schedule jobs from DB.
	 *
	 * @return the schedule jobs from DB
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public List<TaskDetails> getScheduleJobsFromDB(String taskID) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getJobsStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(taskID);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<TaskDetails> td = (List<TaskDetails>)result.get(GetJobsStoredProc.PARAM_OUT_P_JOBS);
		return td;
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
	public void saveScheduledJob(String taskID, String nextRun, String partnerFacingInterface, String nextState, String updUserID, String serviceName, String comments) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)createJobStoredProc.getStoredProc();
		sp.execute(taskID, nextRun, nextState, updUserID, serviceName, comments, partnerFacingInterface);
	}
	
	/**
	 * Removes the scheduled job.
	 *
	 * @param taskID the task ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void removeScheduledJob(String taskID) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)deleteJobStoredProc.getStoredProc();
		sp.execute(taskID);
	}
	
	/**
	 * Save issuance recon record.
	 *
	 * @param primeIssuanceRecon the prime issuance recon
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String saveIssuanceReconRecord(List<PrimeIssuanceReconRecord> primeIssuanceRecon) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)createIssuanceReconStoredProc.getStoredProc();
		PrimeIssuanceReconRecord[] inArray = null;
		if (primeIssuanceRecon != null && primeIssuanceRecon.size() > 0) {
			inArray = new PrimeIssuanceReconRecord[primeIssuanceRecon.size()];
			inArray = primeIssuanceRecon.toArray(inArray);
			Map<String, Object> result = sp.execute(new IssuanceReconTypeArray(inArray));
			
			return (String) result.get(CreateIssuanceReconStoredProc.PARAM_OUT_P_DUPS);
		}
		return null;
	}
	
	/**
	 * Validate issuance recon record.
	 *
	 * @param reconFileName the recon file name
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public List<PrimeIssuanceReconRecord> validateIssuanceReconRecord(String reconFileName) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)validateIssuanceReconStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(reconFileName);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<PrimeIssuanceReconRecord> td = (List<PrimeIssuanceReconRecord>)result.get(ValidateIssuanceReconStoredProc.PARAM_OUT_P_MISMATCH_RECORDS);
		return td;
	}
	
	@Transactional
	public List<EventNotificationTarget> getActiveEventNotificationTargets() throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getActiveEventNotificationTargetsStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<EventNotificationTarget> td = (List<EventNotificationTarget>)result.get(GetActiveEventNotificationTargetsStoredProc.PARAM_OUT_P_RECORDS);
		return td;
	}

	public void updateEventNotificationTarget(EventNotificationTarget eventNotificationTarget) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)updateEventNotificationTargetStoredProc.getStoredProc();
		String succeeded = null;
		if (eventNotificationTarget.getLastNotificationSucceeded() == null) {
			succeeded = null;
		} else if (Boolean.TRUE.equals(eventNotificationTarget.getLastNotificationSucceeded())) {
			succeeded = "Y";
		} else {
			succeeded = "N";
		}
		sp.execute(eventNotificationTarget.getTargetId(), eventNotificationTarget.getLastSuccessfullySentEventId(), eventNotificationTarget.getLastSentDTM(), succeeded);
	}
	
	/**
	 * Save ticket dispute.
	 *
	 * @param ticketDispute the ticket dispute
	 * @return the integer
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Integer> saveTicketDispute(final TicketDispute ticketDispute) {
		ETKStoredProcedure sp = (ETKStoredProcedure)createDisputeRecordStoredProc.getStoredProc();
		
		String ticketDisputeJSON = StringUtil.objectToJsonString(ticketDispute);
				
		Map<String, Object> result = sp.execute(ticketDisputeJSON, ticketDispute.getContravention().getNumber(), 
				"D", 
				DateUtil.StringToDate(ticketDispute.getContravention().getDispute_date(), DateUtil.YYYY_MM_DD),
				ticketDispute.getContravention().getDispute_type_code(),
				Double.parseDouble(ticketDispute.getContravention().getTicketed_amount()),
				ticketDispute.getContravention().getIcbc_compressed_section(),
				ticketDispute.getContravention().getIcbc_act_regulation());
		
		if (result == null) {
			return null;
		}
		
		HashMap<String, Integer> returnObj = new HashMap<String, Integer>();
		returnObj.put(Const.KEY_EVENT_ID, (Integer)result.get(CreateDisputeRecordStoredProc.PARAM_IN_p_event_id));
		returnObj.put(Const.KEY_PROCESS_ID, (Integer)result.get(CreateDisputeRecordStoredProc.PARAM_IN_p_process_id));
		return returnObj;
	}
	
	
	/**
	 * Save dispute status update.
	 *
	 * @param disputeStatusUpdate the dispute status update
	 * @return the integer
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Map<String, Integer> saveDisputeStatusUpdate(final DisputeStatusUpdate disputeStatusUpdate) {
		ETKStoredProcedure sp = (ETKStoredProcedure)createDisputeRecordStoredProc.getStoredProc();
		
		String statusUpdateJSON = StringUtil.objectToJsonString(disputeStatusUpdate);
		
		Map<String, Object> result = sp.execute(statusUpdateJSON, disputeStatusUpdate.getContravention().getNumber(),
				disputeStatusUpdate.getContravention().getAction_code(), 
				DateUtil.dateStringToLocalDate(disputeStatusUpdate.getContravention().getAction_date(), DateUtil.YYYY_MM_DD),
				null,
				null,
				null,
				null);
		
		if (result == null) {
			return null;
		}
		
		HashMap<String, Integer> returnObj = new HashMap<String, Integer>();
		returnObj.put(Const.KEY_EVENT_ID, (Integer)result.get(CreateDisputeRecordStoredProc.PARAM_IN_p_event_id));
		returnObj.put(Const.KEY_PROCESS_ID, (Integer)result.get(CreateDisputeRecordStoredProc.PARAM_IN_p_process_id));
		return returnObj;

	}
	
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Event> retrieveEvents(Integer startEventID, Integer endEventID, String startEventDate, String endEventDate, Integer pageSize, String eventTypes, String issuingAgencyCodeList) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)retrieveEventsStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(startEventID, endEventID, startEventDate, endEventDate, pageSize, eventTypes, issuingAgencyCodeList);
		if (result == null) {
			return null;
		}
		
		ArrayList<TicketDisputeKPIDetails> disputeEvents = (ArrayList<TicketDisputeKPIDetails>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_dispute);
		ArrayList<EtkReportKPIDetails> issuanceEvents = (ArrayList<EtkReportKPIDetails>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_issuance);
		ArrayList<EtkReportViolationDetails> violations = (ArrayList<EtkReportViolationDetails>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_issuance_v);
		ArrayList<TicketPaymentKPIDetails> queryPaymentEvents = (ArrayList<TicketPaymentKPIDetails>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_query_payment);
		ArrayList<EtkDisputeFinding> disputeFindingEvents = (ArrayList<EtkDisputeFinding>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_dispute_finding);
		ArrayList<EtkReportEvents> sentEvents = (ArrayList<EtkReportEvents>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_sent);
		ArrayList<EtkReportEvents> purgedEvents = (ArrayList<EtkReportEvents>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_purged);
		
		//add violations to issuanceEvents
		if (issuanceEvents != null) {
			for (EtkReportKPIDetails kpi : issuanceEvents) {
				BigInteger ticketID = kpi.getTICKET_ID();
				if (violations != null) {
					for (EtkReportViolationDetails v: violations) {
						if (ticketID.equals(v.getTICKET_ID())) {
							kpi.getViolations().put(String.valueOf(v.getCOUNT_NBR()), v);
						}
					}
				}
			}
		}
			
		List<Event> events = new ArrayList<Event>();
		
		// add SENT events to the final event list
		if (sentEvents != null && sentEvents.size() > 0) {
			events.addAll(EvtEventingUtil.mapFromSentEtkReportEventsList(sentEvents));
		}
		
		// add PURGED events to the final event list
		if (purgedEvents != null && purgedEvents.size() > 0) {
			events.addAll(EvtEventingUtil.mapFromPurgedEtkReportEventsList(purgedEvents));
		}
		
		// add dispute events to the final events list
		if (disputeEvents != null && disputeEvents.size() > 0) {
			events.addAll(EvtEventingUtil.mapFromTicketDisputeKPIDetailsList(disputeEvents));
		}
		
		// add query, payment events to the final events list
		if (queryPaymentEvents != null && queryPaymentEvents.size() > 0) {
			events.addAll(EvtEventingUtil.mapFromTicketPaymentKPIDetailsList(queryPaymentEvents));
		}

		// add issuance events to the final events list
		if (issuanceEvents != null && issuanceEvents.size() > 0) {
			events.addAll(EvtEventingUtil.mapFromEtkREportKPIDetailsList(issuanceEvents));
		}

		// add dispute finding events to the final events list
		if (disputeFindingEvents != null && disputeFindingEvents.size() > 0) {
			events.addAll(EvtEventingUtil.mapFromDisputeFindingKPIDetailsList(disputeFindingEvents));
		}
		//Sort the list by eventID ascending
		Collections.sort(events);
		
		return events;
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
	@Transactional
	@SuppressWarnings("unchecked")
	public List<KafkaEvent> retrieveEvents_kafka(Integer startEventID, Integer endEventID, String startEventDate, String endEventDate, Integer pageSize, String eventTypes, String issuingAgencyCodeList) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)retrieveEventsStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(startEventID, endEventID, startEventDate, endEventDate, pageSize, eventTypes, issuingAgencyCodeList);
		if (result == null) {
			return null;
		}
		
		ArrayList<TicketDisputeKPIDetails> disputeEvents = (ArrayList<TicketDisputeKPIDetails>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_dispute);
		ArrayList<EtkReportKPIDetails> issuanceEvents = (ArrayList<EtkReportKPIDetails>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_issuance);
		ArrayList<EtkReportViolationDetails> violations = (ArrayList<EtkReportViolationDetails>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_issuance_v);
		ArrayList<TicketPaymentKPIDetails> queryPaymentEvents = (ArrayList<TicketPaymentKPIDetails>)result.get(RetrieveEventsStoredProc.PARAM_OUT_p_ticket_query_payment);
		
		//add violations to issuanceEvents
		if (issuanceEvents != null) {
			for (EtkReportKPIDetails kpi : issuanceEvents) {
				BigInteger ticketID = kpi.getTICKET_ID();
				if (violations != null) {
					for (EtkReportViolationDetails v: violations) {
						if (ticketID.equals(v.getTICKET_ID())) {
							kpi.getViolations().put(String.valueOf(v.getCOUNT_NBR()), v);
						}
					}
				}
			}
		}
			
		List<KafkaEvent> events = new ArrayList<KafkaEvent>();
		
		// add dispute events to the final events list
		if (disputeEvents != null && disputeEvents.size() > 0) {
			events.addAll(KafkaEvtEventingUtil.mapFromTicketDisputeKPIDetailsList(disputeEvents));
		}
		
		// add query, payment events to the final events list
		if (queryPaymentEvents != null && queryPaymentEvents.size() > 0) {
			events.addAll(KafkaEvtEventingUtil.mapFromTicketPaymentKPIDetailsList(queryPaymentEvents));
		}

		// add issuance events to the final events list
		if (issuanceEvents != null && issuanceEvents.size() > 0) {
			events.addAll(KafkaEvtEventingUtil.mapFromEtkREportKPIDetailsList(issuanceEvents));
		}

		//Sort the list by eventID ascending
		Collections.sort(events);
		
		return events;
	}
	
	/**
	 * Record dispute findings.
	 *
	 * @param etkDisputeFindings the etk dispute findings
	 * @return the string
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String recordDisputeFindings(List<EtkDisputeFinding> etkDisputeFindings) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)recordDisputeFindingstoredProc.getStoredProc();
		EtkDisputeFinding[] inArray = null;
		if (etkDisputeFindings != null && etkDisputeFindings.size() > 0) {
			inArray = new EtkDisputeFinding[etkDisputeFindings.size()];
			inArray = etkDisputeFindings.toArray(inArray);
			Map<String, Object> result = sp.execute(new EtkDisputeFindingTypeArray(inArray));
			
			if (result == null) {
				return null;
			}
			return (String) result.get(RecordDisputeFindingstoredProc.PARAM_OUT_p_dups);
		}
		return null;
	}
	
	
	/**
	 * Gets the evt files stats.
	 *
	 * @param fileNames a list of comma deliminated file names
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public List<FTPFileInfo> GetEvtFilesStats(final String fileNames) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getProcessStatesByFileNamesStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(fileNames);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<FTPFileInfo> files = (List<FTPFileInfo>)result.get(GetProcessStatesByFileNamesStoredProc.PARAM_OUT_P_RECORDS);
		return files;
	}
	
	/**
	 * Delete ticket by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void deleteTicketByTicketNO(String ticketNO) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)deleteTicketStoredProc.getStoredProc();
		sp.execute(ticketNO);
	}
	
	/**
	 * Gets the audit records by ticket NO.
	 *
	 * @param ticketNO the ticket NO
	 * @return the audit records by ticket NO
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public List<AuditRecord> getAuditRecordsByTicketNO(final String ticketNO) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getAuditsByTicketNOStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(ticketNO);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<AuditRecord> auditRecordList = (List<AuditRecord>)result.get(GetAuditsByTicketNOStoredProc.PARAM_OUT_P_RECORDS);
		return auditRecordList;
		
	}
	
	/**
	 * Update agency latest mre date.
	 *
	 * @param agencyCD the agency CD
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateAgencyLatestMreDate(String agencyCD) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)updateAgencyLatestMreDateStoredProc.getStoredProc();
		sp.execute(agencyCD);
	}
	
	/**
	 * Gets the DB version.
	 *
	 * @return the DB version
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public String getDBVersion() throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getDBVersionStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		
		String dbVersion = (String)result.get(GetDBVersionStoredProc.PARAM_OUT_p_db_version);
		return dbVersion;
	}
	
	/**
	 * Gets the associated unresolved errors.
	 *
	 * @param errorID the error ID
	 * @return the associated errors
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public List<EtkError> getAssociatedErrorsUnresolved(final String errorID) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getAssociatedErrorsStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(errorID);
		if (result == null) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		List<EtkError> associatedErrors = (List<EtkError>)result.get(GetAssociatedErrorsUnresolvedStoredProc.PARAM_OUT_P_RECORDS);
		return associatedErrors;
	}
	
	/**
	 * Gets the tickets sent today.
	 *
	 * @return the tickets sent today
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public List<EtkTicket> getTicketsReceivedToday() throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getTicketsReceivedTodayStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		List<EtkTicket> ticketsSentToday = (List<EtkTicket>)result.get(GetTicketsReceivedTodayStoredProc.PARAM_OUT_P_RECORDS);
		return ticketsSentToday;
	}
	
	@Transactional
	public List<EtkStatisticsTicketErrorDetail> getTicketsNotSent() throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getTicketsNotSentStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		List<EtkStatisticsTicketErrorDetail> ticketsNotSent = (List<EtkStatisticsTicketErrorDetail>)result.get(GetTicketsNotSentStoredProc.PARAM_OUT_p_records);
		
		// re-build finalTicketErrorDetail.
		ArrayList<EtkStatisticsTicketErrorDetail> finalTicketsNotSent = new ArrayList<EtkStatisticsTicketErrorDetail>();
		
		for (EtkStatisticsTicketErrorDetail ticket : ticketsNotSent) {
			boolean found = false;
			EtkStatisticsTicketErrorDetail ftd = null;
			for (EtkStatisticsTicketErrorDetail ft : finalTicketsNotSent) {
				if (ft.getTicketDetails().getTicketID().equals(ticket.getTicketDetails().getTicketID())) {
					found = true;
					ftd = ft;
					break;
				}
			}
			// it's a new EtkStatisticsTicketErrorDetail obj, add to the final list
			if (!found) {
				finalTicketsNotSent.add(ticket);
			} else {
				// found the same EtkStatisticsTicketErrorDetail obj in the final list, append the error to the error list
				if (ftd != null && ftd.getErrors() != null && ticket.getErrors() != null) {
					ftd.getErrors().addAll(ticket.getErrors());
				}
			}
		}
		
		return finalTicketsNotSent;
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
		ETKStoredProcedure sp = (ETKStoredProcedure)updateErrorsByTicketIDStoredProc.getStoredProc();
		sp.execute(ticketID, updUserID, errorStatus);
	}
	
	/**
	 * Gets the next queued dispute record.
	 *
	 * @param dataTypeCode the data type code, values: DISPUTE or DISPUTE_STATUS_UPDATE
	 * @return the etk queued dispute record
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public EtkQueuedDisputeRecord GetNextQueuedDisputeRecord(String dataTypeCode) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getNextQueuedDisputeRecordStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(dataTypeCode);
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<EtkQueuedDisputeRecord> disputeRecords = (ArrayList<EtkQueuedDisputeRecord>)result.get(GetNextQueuedDisputeRecordStoredProc.PARAM_OUT_P_RECORD);
		return disputeRecords != null && disputeRecords.size() > 0 ? disputeRecords.get(0) : null;

	}
	
	/**
	 * Update dispute record.
	 *
	 * @param processID the process ID
	 * @param newProcessState the new process state
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void updateDisputeRecord(int processID, EnumProcessState newProcessState) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)updateDisputeRecordStoredProc.getStoredProc();
		sp.execute(processID, newProcessState.getCodeValue());
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
		ETKStoredProcedure sp = (ETKStoredProcedure)updateTicketProcessStateByTicketIDStoredProc.getStoredProc();
		sp.execute(ticketID, interfaceNM.getCodeValue(), ticketProcesState.getCodeValue());
	}
	
	/**
	 * Creates the ticket KPI details and event.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public Map<String, Object> createTicketKPIDetailsEvent(Integer ticketID) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)createTicketKPIDetailsEventStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(ticketID);
		
		if (result == null) {
			return null;
		}
		
		String errorCode = (String)result.get(CreateTicketKPIDetailsEventStoredProc.PARAM_OUT_P_ERROR_CODE);
		String errorDetails = (String)result.get(CreateTicketKPIDetailsEventStoredProc.PARAM_OUT_P_ERROR_DETAILS);

		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put(Const.KEY_TICKET_VALIDATION_ERROR_CD, errorCode);
		returnObj.put(Const.KEY_TICKET_VALIDATION_ERROR_DETAILS, errorDetails);
		return returnObj;
	}
	
	/**
	 * Gets the active agencies.
	 *
	 * @return the active agencies
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public List<EtkAgencyCodeText> getActiveAgencies() throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getActiveAgenciesStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<EtkAgencyCodeText> agencyList = (ArrayList<EtkAgencyCodeText>)result.get(GetActiveAgenciesStoredProc.PARAM_OUT_p_records);
		return agencyList;
	}
	
	/**
	 * Delete ticket by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void deleteTicketByTicketID(final Integer ticketID) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)deleteTicketByTicketIDStoredProc.getStoredProc();
		sp.execute(ticketID);
	}
	
	/**
	 * Delete dup tickets by ticket ID.
	 *
	 * @param ticketID the ticket ID
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void deleteDupTicketsByTicketID(final Integer ticketID) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)deleteDupErrorTicketsByTicketIDStoredProc.getStoredProc();
		sp.execute(ticketID);
	}
	
	/**
	 * Creates/updates the working data.
	 *
	 * @param componentName the component name
	 * @param dataName the data name
	 * @param dataValue the data value
	 * @param lockNumber the lock number
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Boolean createUpdateWorkingData(String componentName, String dataName, String dataValue, Integer lockNumber) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)createUpdateWorkingDataStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(componentName, dataName, dataValue, lockNumber);
		
		if (result == null) {
			return null;
		}
		
		return (Boolean)result.get(CreateUpdateWorkingDataStoredProc.PARAM_OUT_p_set_successful);
	}
	
	/**
	 * Creates update working data lead pod.
	 *
	 * @param componentName the component name
	 * @param podInfo the pod info
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Integer createUpdateWorkingDataLeadPod(String componentName, String podInfo) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)createUpdateWorkingDataLeadPodStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(componentName, podInfo);
		
		if (result == null) {
			return null;
		}
		
		return (Integer)result.get(CreateUpdateWorkingDataLeadPodStoredProc.PARAM_OUT_p_lock_nbr);
 	}
	
	/**
	 * Am I lead pod.
	 *
	 * @param componentName the component name
	 * @param podInfo the pod info
	 * @return the boolean
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Boolean amILeadPod(final String componentName, final String podInfo) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)amILeadPodStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(componentName, podInfo);
		if (result == null) {
			return null;
		}
		
		return (Boolean)result.get(AmILeadPodStoredProc.PARAM_OUT_results);
	}
	
	/**
	 * Gets the working data.
	 *
	 * @param dataID the data ID
	 * @param componentName the component name
	 * @param dataName the data name
	 * @return the working data
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public List<EtkWorkingData> getWorkingData(String componentName, String dataName) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getWorkingDataStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(componentName, dataName);
		if (result == null) {
			return null;
		}
		
		List<EtkWorkingData> workingData = (ArrayList<EtkWorkingData>)result.get(GetWorkingDataStoredProc.PARAM_OUT_p_records);
		return workingData;
	}
	
	/**
	 * Clear data.
	 *
	 * @param dataID the data ID
	 * @param componentName the component name
	 * @param dataName the data name
	 * @param lockNumber the lock number
	 * @return the clear data
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public Boolean clearWorkingData(String componentName, String dataName, Integer lockNumber) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)clearWorkingDataStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute(componentName, dataName, lockNumber);
		if (result == null) {
			return null;
		}
		
		Boolean isClearSuccessful = (Boolean)result.get(ClearWorkingDataStoredProc.PARAM_OUT_p_set_successful);
		
		return isClearSuccessful;
	}
	
	/**
	 * Gets the known users.
	 *
	 * @return the known users
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public List<String> getKnownUsers() throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getKnownUsersStoredProc.getStoredProc();
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		@SuppressWarnings("unchecked")
		List<String> records = (ArrayList<String>)result.get(GetKnownUsersStoredProc.PARAM_OUT_P_RECORDS);
		return records;
	}

	/**
	 * Validate ticket.
	 *
	 * @param sourceXML the source XML
	 * @return the map
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public Map<String, Object> validateTicket(final String sourceXML) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)validateTicketStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(sourceXML);
		if (result == null) {
			return null;
		}
		
		String errorCode = (String) result.get(ValidateTicketStoredProc.PARAM_OUT_P_ERROR_CODE);
		String errorDetails = (String) result.get(ValidateTicketStoredProc.PARAM_OUT_P_ERROR_DETAILS);
		String contraventionNOs = (String) result.get(ValidateTicketStoredProc.PARAM_OUT_P_CONTRACENTION_NO);
		
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put(Const.KEY_TICKET_VALIDATION_ERROR_CD, errorCode);
		returnObj.put(Const.KEY_TICKET_VALIDATION_ERROR_DETAILS, errorDetails);
		returnObj.put(Const.KEY_TICKET_VALIDATION_CONTRAVENTION_NOS, contraventionNOs);
		return returnObj;
	}
	
	/**
	 * Sets the ticke status to sent.
	 *
	 * @param ticketID the ticket ID
	 * @param interfaceNM the interface NM
	 * @throws EtkDataAccessException the etk data access exception
	 */
	public void SetTickeStatusToSent(final Integer ticketID, final EnumInterface interfaceNM) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)setTicketStatusToSentStoredProc.getStoredProc();
		sp.execute(ticketID, interfaceNM.getCodeValue());
	}
	
	/**
	 * Check for stuck tickets.
	 *
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public Map<String, Object> checkForStuckTickets() throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)checkForStuckTicketsStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute();
		if (result == null) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		List<EtkTicket> ticketsStuckIssuance = (List<EtkTicket>)result.get(CheckForStuckTicketsStoredProc.PARAM_p_stuck_tickets_issuance);
		@SuppressWarnings("unchecked")
		List<EtkQueuedDisputeRecord> ticketsStuckDispute = (List<EtkQueuedDisputeRecord>)result.get(CheckForStuckTicketsStoredProc.PARAM_p_stuck_tickets_dispute);
		@SuppressWarnings("unchecked")
		List<EtkQueuedDisputeRecord> ticketsStuckDisputeSU = (List<EtkQueuedDisputeRecord>)result.get(CheckForStuckTicketsStoredProc.PARAM_p_stuck_tickets_disputeSU);
		
		Map<String, Object> returnObj = new HashMap<String, Object>();
		returnObj.put(Const.KEY_STUCK_ISSUANCE_RECORDS, ticketsStuckIssuance);
		returnObj.put(Const.KEY_STUCK_DISPUTE_RECORDS, ticketsStuckDispute);
		returnObj.put(Const.KEY_STUCK_DISPUTE_SU_RECORDS, ticketsStuckDisputeSU);
		
		return returnObj;
	}
	
	/**
	 * Gets the dispute records.
	 *
	 * @param dataTypeCode the data type code
	 * @param processStateTypeCode the process state type code
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@Transactional
	public List<EtkQueuedDisputeRecord> GetDisputeRecords(String dataTypeCode, String processStateTypeCode) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)getDisputeRecordsStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(dataTypeCode, processStateTypeCode);
		if (result == null) {
			return null;
		}
		
		@SuppressWarnings("unchecked")
		List<EtkQueuedDisputeRecord> disputeTickets = (List<EtkQueuedDisputeRecord>)result.get(GetDisputeRecordsStoredProc.PARAM_OUT_P_RECORD);
		return disputeTickets;
	}
	
	/**
	 * Banking fee calculation.
	 *
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the list
	 * @throws EtkDataAccessException the etk data access exception
	 */
	@SuppressWarnings("unchecked")
	@Transactional
	public Map<EnumTicketType, BankingFeePerTicketType> bankingFeeCalculation(String startDate, String endDate) throws EtkDataAccessException {
		ETKStoredProcedure sp = (ETKStoredProcedure)calculateBankingFeeStoredProc.getStoredProc();
		
		Map<String, Object> result = sp.execute(startDate, endDate);
		if (result == null) {
			return null;
		}
		
		List<TicketDBPaymentTxnDetail> records_monthly_card_txn = (List<TicketDBPaymentTxnDetail>)result.get(CalculateBankingFeeStoredProc.PARAM_OUT_p_records_monthly_card_txn);
		List<TicketDBPaymentTxnDetail> records_monthly_total_txn = (List<TicketDBPaymentTxnDetail>)result.get(CalculateBankingFeeStoredProc.PARAM_OUT_p_records_monthly_total_txn);
		List<TicketDBPaymentTxnDetail> records_card_bank_fee = (List<TicketDBPaymentTxnDetail>)result.get(CalculateBankingFeeStoredProc.PARAM_OUT_p_records_card_bank_fee);
		List<TicketDBPaymentTxnDetail> records_ticket_total_txn = (List<TicketDBPaymentTxnDetail>)result.get(CalculateBankingFeeStoredProc.PARAM_OUT_p_records_ticket_total_txn);
		List<PaymentCardBankingFeeRateDetail> paymentCardBankingFeeRate = (List<PaymentCardBankingFeeRateDetail>)result.get(CalculateBankingFeeStoredProc.PARAM_OUT_p_bank_fee_rate);

		Map<EnumTicketType, BankingFeePerTicketType> retVal = null;
		
		// Build the response
		if (records_monthly_card_txn != null && records_monthly_card_txn.size() > 0) {
			retVal = new LinkedHashMap<EnumTicketType, BankingFeePerTicketType>();
			
			// Loop through records_monthly_card_txn 
			for (TicketDBPaymentTxnDetail bf : records_monthly_card_txn) {
				BankingFeePerTicketType bankingFeePerTicketType = null;
				//get banking fee rate
				PaymentCardBankingFeeRateDetail bankfeeRate = null;
				bankfeeRate = getBankFeeRate(paymentCardBankingFeeRate, bf.getPayment_card_type_txt());
				if (bankfeeRate == null) {
					log.error("Failed getting banking fee rate for {}", bf.getPayment_card_type_txt().getCodeValue());
					return null;
				}
				// Build List<TicketBankingFee> from records_monthly_card_txn, records_monthly_total_txn, records_card_bank_fee and records_ticket_total_txn
				if (retVal.containsKey(bf.getPayment_ticket_type_cd())) {
					bankingFeePerTicketType = retVal.get(bf.getPayment_ticket_type_cd());
				} else {
					bankingFeePerTicketType = new BankingFeePerTicketType();
					retVal.put(bf.getPayment_ticket_type_cd(), bankingFeePerTicketType);
				}
				
				// Calculate and set PCI compliance fee based on total number of txn per ticket type
				if (records_ticket_total_txn != null && records_ticket_total_txn.size() > 0) {
					for (TicketDBPaymentTxnDetail ticketTotalTxn: records_ticket_total_txn) {
						// TotalPCIComplianceFee hasn't been set yet, set it.
						if (bankingFeePerTicketType.getTotalPCIComplianceFee() == 0 && ticketTotalTxn.getPayment_ticket_type_cd().equals(bf.getPayment_ticket_type_cd())) {
							double toatlNumOfTxnPerTicketType = ticketTotalTxn.getTxn_cnt();
							
							bankingFeePerTicketType.setTotalPCIComplianceFee(toatlNumOfTxnPerTicketType * bankfeeRate.getPciBcmTxnFee());
							bankingFeePerTicketType.setTotalBankingFee(bankingFeePerTicketType.getTotalBankingFee() + bankingFeePerTicketType.getTotalPCIComplianceFee());
						}
					}
				}
				
				Map<EnumCardType, BankingFeePerTicketTypeCardType> bankingFeePerTicketTypeCardTypeMap = null;
				BankingFeePerTicketTypeCardType bankingFeePerTicketTypeCard = null;
				if (bankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() != null && bankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().containsKey(bf.getPayment_card_type_txt())) {
					bankingFeePerTicketTypeCard = bankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().get(bf.getPayment_card_type_txt());
				} else {
					if (bankingFeePerTicketType.getPaymentTxnDetailsPerCardtype() == null ) {
						bankingFeePerTicketTypeCardTypeMap = new LinkedHashMap<EnumCardType, BankingFeePerTicketTypeCardType>();
						bankingFeePerTicketTypeCard = new BankingFeePerTicketTypeCardType();
						
						// default monthlyPaymentTxnDetails for the cardtype
						bankingFeePerTicketTypeCard.setMonthlyPaymentTxDetailsPerCardtype(new LinkedHashMap<Integer, MonthlyPaymentTxnDetailPerTicketTypeCardType>());
						if (records_monthly_total_txn != null && records_monthly_total_txn.size() > 0) {
							for (TicketDBPaymentTxnDetail td : records_monthly_total_txn) {
								if (td.getPayment_ticket_type_cd().equals(bf.getPayment_ticket_type_cd())) {
									bankingFeePerTicketTypeCard.getMonthlyPaymentTxDetailsPerCardtype().put(td.getQmonth(), new MonthlyPaymentTxnDetailPerTicketTypeCardType());
								}
							}
						}
						
						bankingFeePerTicketTypeCardTypeMap.put(bf.getPayment_card_type_txt(), bankingFeePerTicketTypeCard);
					} else if (!bankingFeePerTicketType.getPaymentTxnDetailsPerCardtype().containsKey(bf.getPayment_card_type_txt())) {
						bankingFeePerTicketTypeCardTypeMap = bankingFeePerTicketType.getPaymentTxnDetailsPerCardtype();
						bankingFeePerTicketTypeCard = new BankingFeePerTicketTypeCardType();
						
						// default monthlyPaymentTxnDetails for the cardtype
						bankingFeePerTicketTypeCard.setMonthlyPaymentTxDetailsPerCardtype(new LinkedHashMap<Integer, MonthlyPaymentTxnDetailPerTicketTypeCardType>());
						if (records_monthly_total_txn != null && records_monthly_total_txn.size() > 0) {
							for (TicketDBPaymentTxnDetail td : records_monthly_total_txn) {
								if (td.getPayment_ticket_type_cd().equals(bf.getPayment_ticket_type_cd())) {
									bankingFeePerTicketTypeCard.getMonthlyPaymentTxDetailsPerCardtype().put(td.getQmonth(), new MonthlyPaymentTxnDetailPerTicketTypeCardType());
								}
							}
						}
						bankingFeePerTicketTypeCardTypeMap.put(bf.getPayment_card_type_txt(), bankingFeePerTicketTypeCard);
					}
					bankingFeePerTicketType.setPaymentTxnDetailsPerCardtype(bankingFeePerTicketTypeCardTypeMap);
				}
				
				MonthlyPaymentTxnDetailPerTicketTypeCardType monthlyPaymentTxnDetailPerTicketTypeCardType = null;
				if (bankingFeePerTicketTypeCard.getMonthlyPaymentTxDetailsPerCardtype() != null &&
					bankingFeePerTicketTypeCard.getMonthlyPaymentTxDetailsPerCardtype().containsKey(bf.getQmonth())) {
					monthlyPaymentTxnDetailPerTicketTypeCardType = (MonthlyPaymentTxnDetailPerTicketTypeCardType) bankingFeePerTicketTypeCard.getMonthlyPaymentTxDetailsPerCardtype().get(bf.getQmonth());
				} else {
					if (bankingFeePerTicketTypeCard.getMonthlyPaymentTxDetailsPerCardtype() == null) {
						bankingFeePerTicketTypeCard.setMonthlyPaymentTxDetailsPerCardtype(new LinkedHashMap<Integer, MonthlyPaymentTxnDetailPerTicketTypeCardType>());
					}
					monthlyPaymentTxnDetailPerTicketTypeCardType = new MonthlyPaymentTxnDetailPerTicketTypeCardType();
					bankingFeePerTicketTypeCard.getMonthlyPaymentTxDetailsPerCardtype().put(bf.getQmonth(), monthlyPaymentTxnDetailPerTicketTypeCardType);
				}
				
				monthlyPaymentTxnDetailPerTicketTypeCardType.setNumOfTxnThisMonth(bf.getTxn_cnt());
				monthlyPaymentTxnDetailPerTicketTypeCardType.setTotalPaymentThisMonth(bf.getPayment_amt());
				
				// Calculate and set the bank fee, bank txn fee and prov treasury fee per payment card type
				if (records_card_bank_fee != null && records_card_bank_fee.size() > 0) {
					for (TicketDBPaymentTxnDetail bankFeePerTicketType: records_card_bank_fee) {
						if (bankFeePerTicketType.getPayment_ticket_type_cd().equals(bf.getPayment_ticket_type_cd())
						 && bankFeePerTicketType.getPayment_card_type_txt().equals(bf.getPayment_card_type_txt())
						 && bankingFeePerTicketTypeCard.getBankFee() == 0 && bankingFeePerTicketTypeCard.getBankTxnFee() == 0 && bankingFeePerTicketTypeCard.getProvTreasuryFee() == 0) {
							if (bankfeeRate != null) {
								double totalPaymentAmoutPerTicketTypePerCardType = bankFeePerTicketType.getPayment_amt();
								int totalTxnPerTicketTypePerCardType = bankFeePerTicketType.getTxn_cnt();
								
								double bankFee = totalPaymentAmoutPerTicketTypePerCardType * bankfeeRate.getBankFee();
								double bankTxnFee = totalTxnPerTicketTypePerCardType * bankfeeRate.getBankTxnFee();
								double provTreasuryFee = totalTxnPerTicketTypePerCardType * bankfeeRate.getBcmTxnFee();
								
								bankingFeePerTicketTypeCard.setBankFee(bankingFeePerTicketTypeCard.getBankFee() + bankFee);
								bankingFeePerTicketTypeCard.setBankTxnFee(bankingFeePerTicketTypeCard.getBankTxnFee() + bankTxnFee);
								bankingFeePerTicketTypeCard.setProvTreasuryFee(bankingFeePerTicketTypeCard.getProvTreasuryFee() + provTreasuryFee);
								
								double totalBankingFeePerCardType = bankingFeePerTicketTypeCard.getBankFee() + bankingFeePerTicketTypeCard.getBankTxnFee() + bankingFeePerTicketTypeCard.getProvTreasuryFee();
								bankingFeePerTicketTypeCard.setTotalBankingFeePerCardType(bankingFeePerTicketTypeCard.getTotalBankingFeePerCardType() + totalBankingFeePerCardType);
								
								bankingFeePerTicketType.setTotalBankingFee(bankingFeePerTicketType.getTotalBankingFee() + bankingFeePerTicketTypeCard.getTotalBankingFeePerCardType());
							
							}
						}
					}
				}
			}
		}
		
		// Set totalNumOfTxnPermonth field and totalPaymentPerMonth field
		if (records_monthly_total_txn != null && records_monthly_total_txn.size() > 0) {
			for (TicketDBPaymentTxnDetail bf : records_monthly_total_txn) {
				BankingFeePerTicketType tbf = null;
				if (retVal.containsKey(bf.getPayment_ticket_type_cd())) {
					tbf = retVal.get(bf.getPayment_ticket_type_cd());
				} else {
					tbf = new BankingFeePerTicketType();
					retVal.put(bf.getPayment_ticket_type_cd(), tbf);
				}
				Map<Integer, Integer> totalNumOfTxnPermonth = tbf.getTotalNumOfTxnPermonth();
				if (totalNumOfTxnPermonth == null || !totalNumOfTxnPermonth.containsKey(bf.getQmonth())) {
					if (totalNumOfTxnPermonth == null) {
						totalNumOfTxnPermonth = new LinkedHashMap<Integer, Integer>();
					}
					totalNumOfTxnPermonth.put(bf.getQmonth(), new Integer(bf.getTxn_cnt()));
					tbf.setTotalNumOfTxnPermonth(totalNumOfTxnPermonth);
				}
				
				Map<Integer, Double> totalPaymentPerMonth = tbf.getTotalPaymentPerMonth();
				if (totalPaymentPerMonth == null || !totalPaymentPerMonth.containsKey(bf.getQmonth())) {
					if (totalPaymentPerMonth == null) {
						totalPaymentPerMonth = new LinkedHashMap<Integer, Double>();
					}
					totalPaymentPerMonth.put(bf.getQmonth(), new Double(bf.getPayment_amt()));
					tbf.setTotalPaymentPerMonth(totalPaymentPerMonth);
				}
			}
		}

		return retVal;
	}
	
	/**
	 * Gets the bank fee rate.
	 *
	 * @param paymentCardBankingFeeRateDetailList the payment card banking fee rate detail list
	 * @param catdType the catd type
	 * @return the bank fee rate
	 */
	private PaymentCardBankingFeeRateDetail getBankFeeRate(List<PaymentCardBankingFeeRateDetail> paymentCardBankingFeeRateDetailList, EnumCardType cardType) {
		if (paymentCardBankingFeeRateDetailList == null || cardType == null) {
			return null;
		}
		for (PaymentCardBankingFeeRateDetail paymentCardBankingFeeRateDetail : paymentCardBankingFeeRateDetailList) {
			if (paymentCardBankingFeeRateDetail != null && cardType.equals(paymentCardBankingFeeRateDetail.getPaymentCardType())) {
				return paymentCardBankingFeeRateDetail;
			}
		}
		return null;
	}
}
