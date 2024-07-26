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
public class Person {
	
	@XmlElement(name = "personAddress")
	private String address;

	@XmlElement(name = "personResidenceCity")
	private String residenceCity;

	@XmlElement(name = "personResidenceProvinceCode")
	private String residenceProvinceCode;

	@XmlElement(name = "personResidenceProvinceName")
	private String residenceProvinceName;

	@XmlElement(name = "personResidencePostalCode")
	private String residencePostalCode;
	
}
