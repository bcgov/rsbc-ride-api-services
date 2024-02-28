package bcgov.jh.etk.jhetkcommon.model;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EtkAgencyCodeText.
 */
@Setter
@Getter
public class EtkAgencyCodeText implements Comparable<EtkAgencyCodeText>{
	
	/** The agency. */
	private String agencyCD;
	
	/** The agency text. */
	private String agencyText;
	
	public EtkAgencyCodeText() {
		
	}
	
	public EtkAgencyCodeText(String agencyCD, String agencyText) {
		this.agencyCD = agencyCD;
		this.agencyText = agencyText;
	}

	@Override
	public int compareTo(EtkAgencyCodeText arg0) {
		return this.agencyText.compareTo(arg0.getAgencyText());
	}
}
