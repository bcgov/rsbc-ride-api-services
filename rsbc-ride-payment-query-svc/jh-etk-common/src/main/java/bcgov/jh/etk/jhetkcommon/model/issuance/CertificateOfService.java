package bcgov.jh.etk.jhetkcommon.model.issuance;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

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
