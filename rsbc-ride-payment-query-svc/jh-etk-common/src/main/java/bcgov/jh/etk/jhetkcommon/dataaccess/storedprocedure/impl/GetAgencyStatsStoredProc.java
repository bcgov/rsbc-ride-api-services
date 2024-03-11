package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkAgencyBasedDisputeStatsMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkAgencyBasedMREDatesMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkAgencyBasedMREStatsMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkAgencyBasedStatsMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkAgencyBasedViolationStatsMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkAgencyCodeTextMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.KeyValueMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetAgencyStatsStoredProc.
 */
@Service
public class GetAgencyStatsStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_GET_AGENCY_STATS";
	
	/** The Constant PARAM_IN_p_mre_minor_version_list. */
	public static final String PARAM_IN_p_mre_minor_version_list = "p_mre_minor_version_list";
	
	/** The Constant PARAM_OUT_P_TICKET_STATS_PER_AGENCY. */
	public static final String PARAM_OUT_P_TICKET_STATS_PER_AGENCY = "P_TICKET_STATS_PER_AGENCY";
	
	/** The Constant PARAM_OUT_P_VIO_STATS_PER_AGENCY. */
	public static final String PARAM_OUT_P_VIO_STATS_PER_AGENCY = "P_VIO_STATS_PER_AGENCY";
	
	/** The Constant PARAM_OUT_P_MRE_STATS_PER_AGENCY. */
	public static final String PARAM_OUT_P_MRE_STATS_PER_AGENCY = "P_MRE_STATS_PER_AGENCY";
	
	/** The Constant PARAM_OUT_p_mre_stats_per_agency_warning. */
	public static final String PARAM_OUT_p_mre_stats_per_agency_warning = "p_mre_stats_per_agency_warning";
	
	/** The Constant PARAM_OUT_p_agency_mre_dates. */
	public static final String PARAM_OUT_p_agency_mre_dates = "p_agency_mre_dates";
	
	/** The Constant PARAM_OUT_p_top_charge_types. */
	public static final String PARAM_OUT_p_top_charge_types = "p_top_charge_types";
	
	/** The Constant PARAM_OUT_p_top_disputed_charge_count_per_agency. */
	public static final String PARAM_OUT_p_top_disputed_charge_count_per_agency = "p_top_disputed_charge_count_per_agency";
	
	/** The Constant PARAM_OUT_p_total_disputed_count_per_act. */
	public static final String PARAM_OUT_p_total_disputed_count_per_act = "p_total_disputed_count_per_act";
	
	/** The Constant PARAM_OUT_p_total_disputed_count_per_agency. */
	public static final String PARAM_OUT_p_total_disputed_count_per_agency = "p_total_disputed_count_per_agency";
	
	/** The Constant PARAM_OUT_p_agency_cd_txt_map. */
	public static final String PARAM_OUT_p_agency_cd_txt_map = "p_agency_cd_txt_map";
	
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
		SqlParameter p_mre_minor_version_list = new SqlParameter(PARAM_IN_p_mre_minor_version_list, Types.VARCHAR);
		SqlOutParameter ticketStats = new SqlOutParameter(PARAM_OUT_P_TICKET_STATS_PER_AGENCY, Types.REF_CURSOR, new EtkAgencyBasedStatsMapper());
		SqlOutParameter vioStats = new SqlOutParameter(PARAM_OUT_P_VIO_STATS_PER_AGENCY, Types.REF_CURSOR, new EtkAgencyBasedViolationStatsMapper());
		SqlOutParameter mreStats = new SqlOutParameter(PARAM_OUT_P_MRE_STATS_PER_AGENCY, Types.REF_CURSOR, new EtkAgencyBasedMREStatsMapper());
		SqlOutParameter mreStatsWarning = new SqlOutParameter(PARAM_OUT_p_mre_stats_per_agency_warning, Types.REF_CURSOR, new EtkAgencyBasedMREStatsMapper());
		SqlOutParameter top_charge_types = new SqlOutParameter(PARAM_OUT_p_top_charge_types, Types.REF_CURSOR, new KeyValueMapper());
		SqlOutParameter p_agency_mre_dates = new SqlOutParameter(PARAM_OUT_p_agency_mre_dates, Types.REF_CURSOR, new EtkAgencyBasedMREDatesMapper());
		SqlOutParameter top_disputed_charge_count_per_agency = new SqlOutParameter(PARAM_OUT_p_top_disputed_charge_count_per_agency, Types.REF_CURSOR, new EtkAgencyBasedDisputeStatsMapper());
		SqlOutParameter p_total_disputed_count_per_act = new SqlOutParameter(PARAM_OUT_p_total_disputed_count_per_act, Types.REF_CURSOR, new KeyValueMapper());
		SqlOutParameter p_total_disputed_count_per_agency = new SqlOutParameter(PARAM_OUT_p_total_disputed_count_per_agency, Types.REF_CURSOR, new KeyValueMapper());
		SqlOutParameter agency_cd_txt_map = new SqlOutParameter(PARAM_OUT_p_agency_cd_txt_map, Types.REF_CURSOR, new EtkAgencyCodeTextMapper());

		SqlParameter[] paramArray = {p_mre_minor_version_list, ticketStats, vioStats, mreStats,mreStatsWarning, p_agency_mre_dates, 
				top_charge_types, top_disputed_charge_count_per_agency, p_total_disputed_count_per_act, p_total_disputed_count_per_agency, agency_cd_txt_map};
		return paramArray;
	}

}
