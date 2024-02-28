package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkDisputeFindingKPIDetailMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkDisputeKPIDetailMapper;
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
public class RetrieveEventsStoredProc extends StoredProcedureBase {
	
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_retrieve_events";
	
	/** The Constant PARAM_IN_p_start_event_id. */
	public static final String PARAM_IN_p_start_event_id = "p_start_event_id";
	
	/** The Constant PARAM_IN_p_end_event_id. */
	public static final String PARAM_IN_p_end_event_id = "p_end_event_id";
	
	/** The Constant PARAM_IN_p_event_start_date. */
	public static final String PARAM_IN_p_event_start_date = "p_event_start_date";
	
	/** The Constant PARAM_IN_p_event_end_date. */
	public static final String PARAM_IN_p_event_end_date = "p_event_end_date";
	
	/** The Constant PARAM_IN_p_page_size. */
	public static final String PARAM_IN_p_page_size = "p_page_size";
	
	/** The Constant PARAM_IN_p_event_types. */
	public static final String PARAM_IN_p_event_types = "p_event_types";
	
	/** The Constant PARAM_IN_p_issuing_gency_codes. */
	public static final String PARAM_IN_p_issuing_agency_codes = "p_issuing_agency_codes";
	
	/** The Constant PARAM_OUT_p_ticket_issuance. */
	public static final String PARAM_OUT_p_ticket_issuance = "p_ticket_issuance";
	
	/** The Constant PARAM_OUT_p_ticket_issuance_v. */
	public static final String PARAM_OUT_p_ticket_issuance_v = "p_ticket_issuance_v";
	
	/** The Constant PARAM_OUT_p_ticket_query. */
	public static final String PARAM_OUT_p_ticket_query_payment = "p_ticket_query_payment";
	
	/** The Constant PARAM_OUT_p_ticket_dispute. */
	public static final String PARAM_OUT_p_ticket_dispute = "p_ticket_dispute";
	
	/** The Constant PARAM_OUT_p_dispute_finding. */
	public static final String PARAM_OUT_p_dispute_finding = "p_dispute_finding";
	
	/** The Constant PARAM_OUT_p_ticket_sent. */
	public static final String PARAM_OUT_p_ticket_sent = "p_ticket_sent";
	
	/** The Constant PARAM_OUT_p_ticket_purged. */
	public static final String PARAM_OUT_p_ticket_purged = "p_ticket_purged";
	
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
		SqlParameter p_start_event_id = new SqlParameter(PARAM_IN_p_start_event_id, Types.INTEGER);
		SqlParameter p_end_event_id = new SqlParameter(PARAM_IN_p_end_event_id, Types.INTEGER);
		SqlParameter p_event_start_date = new SqlParameter(PARAM_IN_p_event_start_date, Types.VARCHAR);
		SqlParameter p_event_end_date = new SqlParameter(PARAM_IN_p_event_end_date, Types.VARCHAR);
		SqlParameter p_page_size = new SqlParameter(PARAM_IN_p_page_size, Types.INTEGER);
		SqlParameter p_event_types = new SqlParameter(PARAM_IN_p_event_types, Types.VARCHAR);
		SqlParameter p_issuing_gency_codes = new SqlParameter(PARAM_IN_p_issuing_agency_codes, Types.VARCHAR);
		SqlOutParameter p_ticket_issuance = new SqlOutParameter(PARAM_OUT_p_ticket_issuance, Types.REF_CURSOR, new EtkReportKPIDetailMapper());
		SqlOutParameter p_ticket_issuance_v = new SqlOutParameter(PARAM_OUT_p_ticket_issuance_v, Types.REF_CURSOR, new EtkReportViolationDetailsMapper());
		SqlOutParameter p_ticket_query_payment = new SqlOutParameter(PARAM_OUT_p_ticket_query_payment, Types.REF_CURSOR, new TicketPaymentKPIDetailMapper());
		SqlOutParameter p_ticket_dispute = new SqlOutParameter(PARAM_OUT_p_ticket_dispute, Types.REF_CURSOR, new EtkDisputeKPIDetailMapper());
		SqlOutParameter p_dispute_finding = new SqlOutParameter(PARAM_OUT_p_dispute_finding, Types.REF_CURSOR, new EtkDisputeFindingKPIDetailMapper());
		SqlOutParameter p_ticket_sent = new SqlOutParameter(PARAM_OUT_p_ticket_sent, Types.REF_CURSOR, new EtkReportEventMapper());
		SqlOutParameter p_ticket_purged = new SqlOutParameter(PARAM_OUT_p_ticket_purged, Types.REF_CURSOR, new EtkReportEventMapper());
		SqlParameter[] paramArray = {p_start_event_id, p_end_event_id, p_event_start_date, p_event_end_date, p_page_size, p_event_types, p_issuing_gency_codes, p_ticket_issuance, p_ticket_issuance_v, p_ticket_query_payment, p_ticket_dispute, p_dispute_finding, p_ticket_sent, p_ticket_purged};
		return paramArray;
	}
}
