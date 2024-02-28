package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class CreateUpdateWorkingDataStoredProc.
 */
@Service
public class CreateUpdateWorkingDataStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_create_update_working_data";
	
	/** The Constant PARAM_IN_p_component_name. */
	private static final String PARAM_IN_p_component_name = "p_component_name";
	
	/** The Constant PARAM_IN_p_data_name. */
	private static final String PARAM_IN_p_data_name = "p_data_name";
	
	/** The Constant PARAM_IN_p_data_value. */
	private static final String PARAM_IN_p_data_value = "p_data_value";
	
	/** The Constant PARAM_OUT_p_lock_nbr. */
	public static final String PARAM_IN_p_lock_nbr = "p_lock_nbr";
	
	/** The Constant PARAM_OUT_p_set_successful. */
	public static final String PARAM_OUT_p_set_successful = "p_set_successful";
	
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
		SqlParameter pIn0 = new SqlParameter(PARAM_IN_p_component_name, Types.VARCHAR);
		SqlParameter pIn1 = new SqlParameter(PARAM_IN_p_data_name, Types.VARCHAR);
		SqlParameter pIn2 = new SqlParameter(PARAM_IN_p_data_value, Types.VARCHAR);
		SqlParameter pIn3 = new SqlParameter(PARAM_IN_p_lock_nbr, Types.INTEGER);
		SqlParameter pOut = new SqlOutParameter(PARAM_OUT_p_set_successful, Types.BOOLEAN);
		SqlParameter[] paramArray = {pIn0, pIn1, pIn2, pIn3, pOut};
		return paramArray;
	}

}
