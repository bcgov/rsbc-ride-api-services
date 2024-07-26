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
public class Violation {

	@XmlElement(name = "violationDate")
	private String date;

	@XmlElement(name = "violationTime")
	private String time;

	@XmlElement(name = "violationLocation")
	private String location;

	@XmlElement(name = "violationCityCode")
	private String cityCode;
	
	@XmlElement(name = "violationCityName")
	private String cityName;
	
}
