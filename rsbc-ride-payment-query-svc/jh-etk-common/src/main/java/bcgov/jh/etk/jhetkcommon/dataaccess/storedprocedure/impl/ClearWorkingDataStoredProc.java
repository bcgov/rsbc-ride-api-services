package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;

/**
 * The Class GetClearWorkingDataStoredProc.
 */
@Service
public class ClearWorkingDataStoredProc extends StoredProcedureBase {
	
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_clear_working_data";
	
	/** The Constant PARAM_IN_p_component_name. */
	private String PARAM_IN_p_component_name = "p_component_name";
	
	/** The Constant PARAM_IN_p_data_name. */
	private String PARAM_IN_p_data_name = "p_data_name";
	
	/** The Constant PARAM_OUT_p_lock_nbr. */
	private static final String PARAM_IN_p_lock_nbr = "p_lock_nbr";
	
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
		SqlParameter p_component_name = new SqlParameter(PARAM_IN_p_component_name, Types.VARCHAR);
		SqlParameter p_data_name = new SqlParameter(PARAM_IN_p_data_name, Types.VARCHAR);
		SqlParameter p_lock_nbr = new SqlParameter(PARAM_IN_p_lock_nbr, Types.INTEGER);
		SqlOutParameter p_set_successful = new SqlOutParameter(PARAM_OUT_p_set_successful, Types.BOOLEAN);
		SqlParameter[] paramArray = {p_component_name, p_data_name, p_lock_nbr, p_set_successful};
		return paramArray;
	}
}
