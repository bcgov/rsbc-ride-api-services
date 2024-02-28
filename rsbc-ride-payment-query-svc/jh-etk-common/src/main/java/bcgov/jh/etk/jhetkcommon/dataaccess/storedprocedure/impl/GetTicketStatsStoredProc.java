package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkTicketStatsMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;


/**
 * The Class GetTicketStatsStoredProc.
 */
@Service
public class GetTicketStatsStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_GET_TICKET_STATS";
	
	/** The Constant PARAM_IN_p_end_ent_dtm. */
	public static final String PARAM_IN_p_end_ent_dtm = "p_end_ent_dtm";
	
	/** The Constant PARAM_IN_p_page_number. */
	public static final String PARAM_IN_p_display_start = "p_display_start";
	
	/** The Constant PARAM_IN_p_page_size. */
	public static final String PARAM_IN_p_display_size = "p_display_size";
	
	/** The Constant PARAM_IN_p_search_text. */
	public static final String PARAM_IN_p_search_text = "p_search_text";
	
	/** The Constant PARAM_IN_p_sort_field. */
	public static final String PARAM_IN_p_sort_field = "p_sort_field";
	
	/** The Constant PARAM_IN_p_sort_direction. */
	public static final String PARAM_IN_p_sort_direction = "p_sort_direction";
	
	
	/** The Constant PARAM_OUT_P_TICKETS_DETAIL_NOT_PURGED. */
	public static final String PARAM_OUT_P_TICKETS_DETAIL_NOT_PURGED = "P_TICKETS_DETAIL_NOT_PURGED";
	
	/** The Constant PARAM_OUT_p_total_num_of_tickets. */
	public static final String PARAM_OUT_p_total_num_of_tickets = "p_total_num_of_tickets";

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
	/* (non-Javadoc)
	 * @see ca.bc.gov.pssg.vph.sc.dataaccess.storedprocedure.StoredProcedureBase#setParameters()
	 */
	@Override
	protected SqlParameter[] getParameters() {
		SqlParameter p_end_ent_dtm = new SqlParameter(PARAM_IN_p_end_ent_dtm, Types.TIMESTAMP_WITH_TIMEZONE);
		SqlParameter p_display_start = new SqlParameter(PARAM_IN_p_display_start, Types.INTEGER);
		SqlParameter p_display_size = new SqlParameter(PARAM_IN_p_display_size, Types.INTEGER);
		SqlParameter p_search_text = new SqlParameter(PARAM_IN_p_search_text, Types.VARCHAR);
		SqlParameter p_sort_field = new SqlParameter(PARAM_IN_p_sort_field, Types.VARCHAR);
		SqlParameter p_sort_direction = new SqlParameter(PARAM_IN_p_sort_direction, Types.VARCHAR);
		SqlOutParameter totalTicketNum = new SqlOutParameter(PARAM_OUT_p_total_num_of_tickets, Types.INTEGER);
		SqlOutParameter ticketDetail = new SqlOutParameter(PARAM_OUT_P_TICKETS_DETAIL_NOT_PURGED, Types.REF_CURSOR, new EtkTicketStatsMapper());
		
		SqlParameter[] paramArray = {p_end_ent_dtm, p_display_start, p_display_size, p_search_text, p_sort_field, p_sort_direction, totalTicketNum, ticketDetail};
		return paramArray;
	}

}
