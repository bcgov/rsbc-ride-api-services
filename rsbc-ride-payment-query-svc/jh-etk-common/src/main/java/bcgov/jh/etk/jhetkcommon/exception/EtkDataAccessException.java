package bcgov.jh.etk.jhetkcommon.exception;

import org.springframework.dao.DataAccessException;

/**
 * The Class VPHDataAccessException.
 * @author HLiang
 */
public class EtkDataAccessException extends Exception {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4477658471623355769L; 

	/**
	 * Instantiates a new VPH data access exception.
	 *
	 * @param message the message
	 */
	public EtkDataAccessException(String message) {
		super(message);
	}

	/**
	 * Instantiates a new VPH data access exception.
	 *
	 * @param e the e
	 */
	public EtkDataAccessException(DataAccessException e) {
		super(e.getMessage());
	}
	
	public EtkDataAccessException(Exception e) {
		super(e.getMessage());
	}
}
