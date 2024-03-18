package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkWorkingDataMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetWorkingDataStoredProc.
 */
@Service
public class GetWorkingDataStoredProc extends StoredProcedureBase {
	
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_get_working_data";
	
	/** The Constant PARAM_IN_p_component_name. */
	private String PARAM_IN_p_component_name = "p_component_name";
	
	/** The Constant PARAM_IN_p_data_name. */
	private String PARAM_IN_p_data_name = "p_data_name";
	
	/** The Constant PARAM_OUT_p_records. */
	public static String PARAM_OUT_p_records = "p_records";
	
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
		SqlParameter p_component_name = new SqlParameter(PARAM_IN_p_component_name, Types.VARCHAR);
		SqlParameter p_data_name = new SqlParameter(PARAM_IN_p_data_name, Types.VARCHAR);
		SqlOutParameter P_TICKET_KPI_DETAILS = new SqlOutParameter(PARAM_OUT_p_records, Types.REF_CURSOR, new EtkWorkingDataMapper());
		SqlParameter[] paramArray = {p_component_name, p_data_name, P_TICKET_KPI_DETAILS};
		return paramArray;
	}
}
