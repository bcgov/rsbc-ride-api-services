package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkDisputeFindingKPIDetailMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkDisputeKPIDetailMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkErrorRecordMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkReportEventMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkReportKPIDetailMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkReportViolationDetailsMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.TicketPaymentKPIDetailMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetReportDataStoredProc.
 * @author HLiang
 */
@Service
public class GetReportDataStoredProc extends StoredProcedureBase {
	
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_RETRIEVE_EVENTS_KPI_DATA";
	
	/** The Constant PARAM_IN_P_TICKET_NO. */
	public static final String PARAM_IN_P_TICKET_NO = "P_TICKET_NO";
	
	/** The Constant PARAM_IN_P_START_DATE. */
	public static final String PARAM_IN_P_START_DATE = "P_STARTDATE";
	
	/** The Constant PARAM_IN_P_END_DATE. */
	public static final String PARAM_IN_P_END_DATE = "P_ENDDATE";
	
	/** The Constant PARAM_IN_P_FILTERS. */
	public static final String PARAM_IN_P_DATA_TYPE_FILTERS = "P_DATA_TYPE_FILTER";
	
	/** The Constant PARAM_IN_P_AGENCY_FILTER. */
	public static final String PARAM_IN_P_AGENCY_FILTER = "P_AGENCY_FILTER";
	
	/** The Constant PARAM_OUT_P_TICKET_KPI_DETAILS. */
	public static final String PARAM_OUT_P_TICKET_KPI_DETAILS = "P_TICKET_KPI_DETAILS";
	
	/** The Constant PARAM_OUT_P_TICKET_EVENTS. */
	public static final String PARAM_OUT_P_TICKET_EVENTS = "P_TICKET_EVENTS";
	
	/** The Constant PARAM_OUT_P_VIOLATION_KPI_DETAILS. */
	public static final String PARAM_OUT_P_VIOLATION_KPI_DETAILS = "P_VIOLATION_KPI_DETAILS";
	
	/** The Constant PARAM_OUT_P_DISPUTE_KPI_DETAILS. */
	public static final String PARAM_OUT_P_DISPUTE_KPI_DETAILS = "P_DISPUTE_KPI_DETAILS";
	
	/** The Constant PARAM_OUT_P_DISPUTE_FINDING_KPI_DETAILS. */
	public static final String PARAM_OUT_P_DISPUTE_FINDING_KPI_DETAILS = "P_DISPUTE_FINDING_KPI_DETAILS";
	
	/** The Constant PARAM_OUT_P_payment_kpi_details. */
	public static final String PARAM_OUT_P_payment_kpi_details = "p_payment_kpi_details";
	
	/** The Constant PARAM_OUT_p_error_kpi_details. */
	public static final String PARAM_OUT_p_error_kpi_details = "p_error_kpi_details";

	/**
	 * Gets the stored proc name.
	 *
	 * @return the stored proc
	 */
	@Override
	protected String getStoredProcName() {
		return NAME_STORE_PROCEDURE;
	}

	/**
	 * Gets the parameters.
	 *
	 * @return the sql parameter[]
	 */
	@Override
	protected SqlParameter[] getParameters() {
		SqlParameter P_TICKET_NO = new SqlParameter(PARAM_IN_P_TICKET_NO, Types.VARCHAR);
		SqlParameter P_START_DATE = new SqlParameter(PARAM_IN_P_START_DATE, Types.VARCHAR);
		SqlParameter P_END_DATE = new SqlParameter(PARAM_IN_P_END_DATE, Types.VARCHAR);
		SqlParameter P_FILTERS = new SqlParameter(PARAM_IN_P_DATA_TYPE_FILTERS, Types.VARCHAR);
		SqlParameter P_AGENCY_FILTER = new SqlParameter(PARAM_IN_P_AGENCY_FILTER, Types.VARCHAR);
		SqlOutParameter P_TICKET_KPI_DETAILS = new SqlOutParameter(PARAM_OUT_P_TICKET_KPI_DETAILS, Types.REF_CURSOR, new EtkReportKPIDetailMapper());
		SqlOutParameter P_TICKET_EVENTS = new SqlOutParameter(PARAM_OUT_P_TICKET_EVENTS, Types.REF_CURSOR, new EtkReportEventMapper());
		SqlOutParameter P_VIOLATION_KPI_DETAILS = new SqlOutParameter(PARAM_OUT_P_VIOLATION_KPI_DETAILS, Types.REF_CURSOR, new EtkReportViolationDetailsMapper());
		SqlOutParameter P_DISPUTE_KPI_DETAILS = new SqlOutParameter(PARAM_OUT_P_DISPUTE_KPI_DETAILS, Types.REF_CURSOR, new EtkDisputeKPIDetailMapper());
		SqlOutParameter P_DISPUTE_FINDING_KPI_DETAILS = new SqlOutParameter(PARAM_OUT_P_DISPUTE_FINDING_KPI_DETAILS, Types.REF_CURSOR, new EtkDisputeFindingKPIDetailMapper());
		SqlOutParameter P_payment_kpi_details = new SqlOutParameter(PARAM_OUT_P_payment_kpi_details, Types.REF_CURSOR, new TicketPaymentKPIDetailMapper());
		SqlOutParameter p_error_kpi_details = new SqlOutParameter(PARAM_OUT_p_error_kpi_details, Types.REF_CURSOR, new EtkErrorRecordMapper());
		SqlParameter[] paramArray = {P_TICKET_NO, P_START_DATE, P_END_DATE, P_FILTERS, P_AGENCY_FILTER, P_TICKET_KPI_DETAILS, P_TICKET_EVENTS, P_VIOLATION_KPI_DETAILS, P_DISPUTE_KPI_DETAILS, P_DISPUTE_FINDING_KPI_DETAILS, P_payment_kpi_details, p_error_kpi_details};
		return paramArray;
	}
}
