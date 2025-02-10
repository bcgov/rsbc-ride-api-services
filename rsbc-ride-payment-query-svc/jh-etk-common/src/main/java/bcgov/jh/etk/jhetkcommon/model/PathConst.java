package bcgov.jh.etk.jhetkcommon.model;

/**
 * The Class PathConst.
 * @author HLiang
 */
public class PathConst {

	/** The Constant PATH for ping services */
	public final static String PATH_PING_REQUEST = "/ping";

	/** The Constant PATH for ready service */
	public final static String PATH_READY_REQUEST = "/ready";

	/** The Constant PATH for actuator endpoints */
    public final static String PATH_ACTUATOR = "/actuator/**";
	
	/** The Constant PATH for test service. */
	public final static String PATH_TEST_REQUEST = "/test";

	/** The Constant PATH_TICKET_QUERY. */
	public final static String PATH_TICKET_QUERY_ICBC = "/invoices/evt"; 
	
	/** The Constant PATH_TICKET_QUERY. */
	public final static String PATH_TICKET_QUERY_V2 = "/ticket"; 
	
	/** The Constant PATH_INDIVIDUAL_TICKET_QUERY. */
	public final static String PATH_INDIVIDUAL_TICKET_QUERY_V2 = "/ticket";

	/** The Constant PATH_FTP_FILE_CHECK. */
	public final static String PATH_FTP_FILE_CHECK = "/FTPFileCheck";

	/** The Constant PATH_PAUSE_FILE_WRITE. */
	public final static String PATH_PAUSE_FILE_WRITE = "/PauseFileWrite";

}
