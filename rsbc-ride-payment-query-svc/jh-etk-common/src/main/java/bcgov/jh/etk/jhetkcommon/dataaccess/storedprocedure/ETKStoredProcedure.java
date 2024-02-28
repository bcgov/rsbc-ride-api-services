package bcgov.jh.etk.jhetkcommon.dataaccess.storedprocedure;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.object.StoredProcedure;

/**
 * The Class VPHStoredProcedure.
 * @author HLiang
 */
public class ETKStoredProcedure extends StoredProcedure {
	
	/**
	 * Instantiates a new VPH stored procedure.
	 */
	public ETKStoredProcedure() {
		super();
	}

	/**
	 * Instantiates a new VPH stored procedure.
	 *
	 * @param ds the ds
	 * @param name the name
	 */
	public ETKStoredProcedure(DataSource ds, String name) {
		super(ds, name);
	}

	/**
	 * Instantiates a new VPH stored procedure.
	 *
	 * @param jdbcTemplate the jdbc template
	 * @param name the name
	 */
	public ETKStoredProcedure(JdbcTemplate jdbcTemplate, String name) {
		super(jdbcTemplate, name);
	}
}
