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
public class CertificateOfService {
	
	@XmlElement(name = "certificateOfServiceCompleted")
	private String completed;
	
	@XmlElement(name = "certificateOfServiceServedTo")
	private String servedTo;
	
	@XmlElement(name = "certificateOfServiceDateOfService")
	private String dateOfService;
	
	@XmlElement(name = "certificateOfServiceEntityPerson")
	private String entityPerson;
	
}
