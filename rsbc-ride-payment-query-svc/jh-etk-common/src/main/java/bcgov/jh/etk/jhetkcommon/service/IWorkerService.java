package bcgov.jh.etk.jhetkcommon.service;

import java.util.concurrent.Future;

import bcgov.jh.etk.jhetkcommon.model.EtkInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.EtkTicket;
import bcgov.jh.etk.jhetkcommon.model.FtpFileList;
import bcgov.jh.etk.jhetkcommon.model.SftpAPIOutput;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;

/**
 * The Interface IWorkerService.
 */
public interface IWorkerService {

	/**
	 * Re publish helper.
	 *
	 * @param etkTicket the etk ticket
	 */
	public void rePublishHelper(EtkTicket etkTicket);	
	
	/**
	 * File check.
	 *
	 * @param fileName the file name
	 */
	public Future<SftpAPIOutput> fileCheck(String fileName);
	
	
	/**
	 * File check sync.
	 *
	 * @param fileName the file name
	 * @return the boolean
	 */
	public SftpAPIOutput fileCheck_sync(String fileName);
	
	/**
	 * FTP connectivity check
	 * @return boolean
	 */
	public boolean ftpConnectivityCheck();
	
	/**
	 * File delete.
	 *
	 * @param fileName the file name
	 * @return true, if successful
	 */
	public boolean fileDelete(String fileName);
	
	/**
	 * File batch delete.
	 *
	 * @param fileName the file name
	 * @return true, if successful
	 */
	public SftpAPIOutput fileBatchDelete(String fileName);
	
	/**
	 * Write pause file.
	 */
	public void WritePauseFile();
	
	/**
	 * Gets the list of FTP files.
	 *
	 * @return the list of FTP files
	 */
	public FtpFileList getListOfFTPFiles();
	
	/**
	 * Gets the interface state.
	 *
	 * @param interfaceCD the interface CD
	 * @return the interface state
	 */
	public EtkInterfaceState getInterfaceState (final EnumInterface interfaceCD);
	
	/**
	 * Checks if the interface is stopped.
	 *
	 * @param interfaceCD the interface CD
	 * @return true, if the interface state is null or stopped
	 */
	public boolean isInterfaceStopped (EnumInterface interfaceCD);
	
	/**
	 * Checks if is interface running.
	 *
	 * @param interfaceCD the interface CD
	 * @return true, if is interface running
	 */
	public boolean isInterfaceRunning (EnumInterface interfaceCD);

}
