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
	
	/** The Constant PATH for test service. */
	public final static String PATH_TEST_REQUEST = "/test";

	/** The Constant PATH_TICKET_QUERY. */
	public final static String PATH_TICKET_QUERY_ICBC = "/invoices/evt"; 
	
	/** The Constant PATH_TICKET_QUERY. */
	public final static String PATH_TICKET_QUERY = "/rest/PSSGVPHPAYBC/vph"; 
	public final static String PATH_TICKET_QUERY_V2 = "/ticket"; 
	
	/** The Constant PATH_INDIVIDUAL_TICKET_QUERY. */
	public final static String PATH_INDIVIDUAL_TICKET_QUERY = "/rest/PSSGVPHPAYBC/vph/invs";
	public final static String PATH_INDIVIDUAL_TICKET_QUERY_V2 = "/ticket";
	
	/** The Constant PATH_RECEIPT_CREATION. */
	public final static String PATH_RECEIPT_CREATION_ICBC= "/payments";
	
	/** The Constant PATH_RECEIPT_CREATION. */
	public final static String PATH_RECEIPT_CREATION= "/rest/PSSGVPHPAYBC/vph/rcpts";
	
	/** The Constant PATH_ICBC_PING. */
	public final static String PATH_ICBC_PING= "/icbc/utility/echo";
	
	/** The Constant PATH_CONTRAVENTION_CREATION. */
	public final static String PATH_CONTRAVENTION_CREATION = "/evt";
	
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
	
	/** The Constant PATH_FILE_UPLOAD. */
	public final static String PATH_FILE_UPLOAD = "/evtUpload";

	/** The Constant PATH_FILE_SFTP_UPLOAD. */
	public final static String PATH_FILE_SFTP_UPLOAD = "/uploadToSFEG";

	/** The Constant PATH_FILE_SFTP_UPLOAD. */
	public final static String PATH_LIST_SFTP_FILES = "/listSFEGFiles";
	
	/** The Constant PATH_EVENT_NOTIFICATION. */
	public final static String PATH_EVENT_NOTIFICATION = "/publish/event";
	
	/** The Constant PATH_EVENT_LOOKUP. */
	public final static String PATH_EVENT_LOOKUP = "/eventLookup";
	
	/** The Constant PATH_DISPUTE_COURT_RESULTS. */
	public final static String PATH_DISPUTE_COURT_RESULTS = "dispute-court-results";
	
	/** The Constant PATH_DISPUTE_COURT_RESULTS_NOTIFY_PROCESS_STATUS. */
	public final static String PATH_DISPUTE_COURT_RESULTS_NOTIFY_PROCESS_STATUS = "notify-process-status";

	/** The Constant PATH_TICKET_DISPUTE_REQUEST. */
	public final static String PATH_TICKET_DISPUTE_REQUEST = "/ticket-dispute";
	
	/** The Constant PATH_REQUEST_RESUME_PROCESSING. */
	public final static String PATH_REQUEST_RESUME_PROCESSING = "/request/resumeProcessing";

	/** The Constant PATH_TICKET_DISPUTE_STATUS_UPDATE. */
	public final static String PATH_TICKET_DISPUTE_STATUS_UPDATE = "/dispute-status-update";
}
