package bcgov.jh.etk.jhetkcommon.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FtpFileInstance {
	
	/** The file name. */
	private String fileName;
	
	/** The ticket number. */
	private String ticketNumber;
	
	/** The last modified DT. */
	private String lastModifiedDT;
}
