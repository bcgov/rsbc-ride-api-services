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
@XmlRootElement(name = "counts")
@XmlAccessorType(XmlAccessType.NONE)
public class Counts {

	@XmlElement(name = "countNumber")	
	private String countNumber;

	@XmlElement(name = "act")
	private String act;
	
	@XmlElement(name = "section")
	private String section;

	@XmlElement(name = "offenseDescription")
	private String offenseDescription;

	@XmlElement(name = "amount")
	private String amount;
}
