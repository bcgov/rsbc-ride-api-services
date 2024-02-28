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
