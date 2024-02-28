package bcgov.jh.etk.jhetkcommon.exception;

/**
 * The Class RestCallException.
 */
public class RestCallException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8716971298855433574L;
	
	/**
	 * Instantiates a new missing key info exception.
	 *
	 * @param description the description
	 * @param e the e
	 */
    public RestCallException(final String description, Throwable e) {
        super(description, e);
    }

}
