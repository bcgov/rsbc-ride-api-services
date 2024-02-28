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
public class Ticket {

	@XmlElement(name = "ticketClass")	
	private String ticketClass;

	@XmlElement(name = "ticketNumber")
	private String number;
	
	@XmlElement(name = "ticketStatus")
	private String status;

	@XmlElement(name = "ticketCOSFormNumber")
	private String cosFormNumber;

	@XmlElement(name = "ticketEVTFormNumber")
	private String evtFormNumber;
	
//	@XmlTransient
//	private Date submitDate;
//	
//	@XmlTransient
//	private String submitTime;
	
}
