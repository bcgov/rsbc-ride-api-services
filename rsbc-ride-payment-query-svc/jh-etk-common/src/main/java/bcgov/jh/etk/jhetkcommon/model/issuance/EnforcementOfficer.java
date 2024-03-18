package bcgov.jh.etk.jhetkcommon.model.issuance;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "EVTDetails")
@XmlAccessorType(XmlAccessType.NONE)
public class EnforcementOfficer {

	@XmlElement(name = "enforcementOfficerName")
	private String name;

	@XmlElement(name = "enforcementOfficerLastName")
	private String lastName;
	
	@XmlElement(name = "enforcementOfficerNumber")
	private String number;

	@XmlElement(name = "enforcementOfficerPartnerName")
	private String partnerName;

	@XmlElement(name = "enforcementOfficerPartnerLastName")
	private String partnerLastName;
	
	@XmlElement(name = "enforcementOfficerPartnerNumber")
	private String partnerNumber;
	
}
