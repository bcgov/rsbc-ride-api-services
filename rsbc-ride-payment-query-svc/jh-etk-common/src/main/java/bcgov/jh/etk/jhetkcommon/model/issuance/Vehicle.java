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
public class Vehicle {

	@XmlElement(name = "vehicleAccidentFlag")
	private String accidentFlag;
	
	@XmlElement(name = "vehicleColour")
	private String colour;
	
	@XmlElement(name = "vehicleLicenseProvinceCode")
	private String licenseProvinceCode;
	
	@XmlElement(name = "vehicleLicenseProvinceName")
	private String licenseProvinceName;
	
	@XmlElement(name = "vehiclePlateNo")
	private String plateNo;
	
	@XmlElement(name = "vehicleMakeCode")
	private String makeCode;
	
	@XmlElement(name = "vehicleMakeDescription")
	private String makeDescription;
	
	@XmlElement(name = "vehicleTypeCode")
	private String typeCode;
	
	@XmlElement(name = "vehicleTypeDescription")
	private String typeDescription;
	
	@XmlElement(name = "vehicleYear")
	private String year;
	
}
