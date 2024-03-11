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
