package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.StoredProcedure;

public abstract class StoredProcedureBase {
	/** The auth jdbc template. */
	@Autowired(required = false)
	//public NamedParameterJdbcTemplate vphJdbcTemplate;
	public JdbcTemplate vphJdbcTemplate;
	
	/** The stored proc. */
	public ETKStoredProcedure storedProc;
	
	/**
	 * Gets the stored proc.
	 *
	 * @param jdbcTemplate the jdbc template
	 * @return the stored proc
	 */
	public synchronized StoredProcedure getStoredProc() {
		if (this.storedProc == null) {
			this.storedProc = new ETKStoredProcedure(vphJdbcTemplate, getStoredProcName());
			this.storedProc.setFunction(false);
			this.storedProc.setParameters(getParameters());
			this.storedProc.compile();
		}
		return this.storedProc;
	}
	
	/**
	 * Gets the stored proc name.
	 *
	 * @return the String
	 */
	protected abstract String getStoredProcName();
	
	/**
	 * Gets the parameters.
	 *
	 * @return the sql parameter[]
	 */
	protected abstract SqlParameter[] getParameters();
}
