package bcgov.jh.etk.jhetkcommon.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class FtpFileList {
	
	/** The ftp file list. */
	private List<FtpFileInstance> ftpFileList;
	
	/** The num files. */
	private String numFiles;
	
	/** The error msg. */
	private String errorMsg;
	
}
