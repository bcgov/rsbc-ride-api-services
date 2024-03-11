package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.impl;

import java.sql.Types;

import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.EtkAgencyCodeTextMapper;
import bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure.StoredProcedureBase;


/**
 * The Class GetActiveAgenciesStoredProc.
 */
@Service
public class GetActiveAgenciesStoredProc extends StoredProcedureBase {
	/** The Constant NAME_STORE_PROCEDURE. */
	private static final String NAME_STORE_PROCEDURE = "jh_etk_get_active_agencies";

	/** The Constant PARAM_OUT_p_records. */
	public static final String PARAM_OUT_p_records = "p_records";

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
		SqlOutParameter p_records = new SqlOutParameter(PARAM_OUT_p_records, Types.REF_CURSOR, new EtkAgencyCodeTextMapper());
		SqlParameter[] paramArray = {p_records};
		return paramArray;
	}

}
