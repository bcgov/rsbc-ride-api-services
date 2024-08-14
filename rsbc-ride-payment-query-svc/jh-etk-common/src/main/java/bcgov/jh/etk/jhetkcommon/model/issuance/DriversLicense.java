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
public class DriversLicense {

	@XmlElement(name = "driversLicenseExpiryDate")
	private String expiryDate;
	
	@XmlElement(name = "driversLicenseNumber")
	private String number;
	
	@XmlElement(name = "driversLicenseProducedFlag")
	private String producedFlag;
	
	@XmlElement(name = "driversLicenseProvinceCode")
	private String provinceCode;

	@XmlElement(name = "driversLicenseProvinceName")
	private String provinceName;

}
