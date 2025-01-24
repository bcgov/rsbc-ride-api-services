package bcgov.jh.etk.jhetkcommon.model;

/**
 * The Class PathConst.
 * @author HLiang
 */
public class PathConst {

	/** The Constant PATH_PROCESS_NEW_QUEUED_TICKETS. */
	public final static String PATH_PROCESS_NEW_QUEUED_TICKETS = "/processNewQueuedTickets";
	
	/** The Constant PATH for ping services */
	public final static String PATH_PING_REQUEST = "/ping";

	/** The Constant PATH for ready service */
	public final static String PATH_READY_REQUEST = "/ready";

	/** The Constant PATH_TICKET_QUERY. */
	public final static String PATH_TICKET_QUERY_ICBC = "/invoices/evt"; 
	
	/** The Constant PATH_TICKET_QUERY. */
	public final static String PATH_TICKET_QUERY_V2 = "/ticket"; 
	
	/** The Constant PATH_INDIVIDUAL_TICKET_QUERY. */
	public final static String PATH_INDIVIDUAL_TICKET_QUERY_V2 = "/ticket";

	/** The Constant PATH_ICBC_PING. */
	public final static String PATH_ICBC_PING= "/icbc/utility/echo";

	/** The Constant PATH_TICKET_REPROCESS. */
	public final static String PATH_TICKET_REPROCESS = "/reProcessTicket";

	/** The Constant PATH_FTP_FILE_CHECK. */
	public final static String PATH_FTP_FILE_CHECK = "/FTPFileCheck";
	
	/** The Constant PATH_FTP_CONNECT_CHECK. */
	public final static String PATH_FTP_CONNECT_CHECK = "/FTPConnectCheck";
	
	/** The Constant PATH_FTP_FILE_DELETE. */
	public final static String PATH_FTP_FILE_DELETE = "/FTPFileDelete";
	
	/** The Constant PATH_FTP_BATCH_FILE_DELETE. */
	public final static String PATH_FTP_BATCH_FILE_DELETE = "/FTPFileBatchDelete";
	
	/** The Constant PATH_FTP_FILE_GET. */
	public final static String PATH_FTP_FILE_GET = "/FTPFilesGet";
	
	/** The Constant PATH_PAUSE_FILE_WRITE. */
	public final static String PATH_PAUSE_FILE_WRITE = "/PauseFileWrite";

}
