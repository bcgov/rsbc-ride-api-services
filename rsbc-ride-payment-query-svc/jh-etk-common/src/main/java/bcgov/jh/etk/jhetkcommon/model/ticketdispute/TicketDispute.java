/**
 * Auto generated class. Using http://www.jsonschema2pojo.org/ 
 * Parameters: 
 * *Package: bcgov.jh.etk.jhetkcommon.model.ticketdispute
 * *Class name: TicketDispute
 * *Target language: Java
 * *Source type: JSON
 * *Annotation style: none
 * *Include getters and setters
 * *Property word delimiters: empty string
 */
package bcgov.jh.etk.jhetkcommon.model.ticketdispute;

import java.io.StringReader;
import java.util.Base64;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.JAXBIntrospector;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;

import bcgov.jh.etk.jhetkcommon.model.icbc.evt.mcot.BoolYN;
import bcgov.jh.etk.jhetkcommon.model.icbc.evt.mcot.CreateContraventionRequest;
import bcgov.jh.etk.jhetkcommon.util.EvtEnrichUtil;

/**
 * The Class TicketDispute.
 * 
 * @author HLiang
 */
public class TicketDispute {

	/** The contravention. */
	private Contravention contravention;

	/** The court. */
	private Court court;

	/** The evt data. */
	private Evt_data evt_data;

	/** The officers. */
	private Officers officers;

	/** The disputant. */
	private Disputant disputant;

	/** The vehicle. */
	private Vehicle vehicle;

	/** The in-memory only, Java representation of the eVT XML. */
	@JsonIgnore
	private CreateContraventionRequest evtXMLObject;

	/**
	 * Gets the contravention.
	 *
	 * @return the contravention
	 */
	public Contravention getContravention() {
		return contravention;
	}

	/**
	 * Sets the contravention.
	 *
	 * @param contravention
	 *            the new contravention
	 */
	public void setContravention(Contravention contravention) {
		this.contravention = contravention;
	}

	/**
	 * Gets the court.
	 *
	 * @return the court
	 */
	public Court getCourt() {
		return court;
	}

	/**
	 * Sets the court.
	 *
	 * @param court
	 *            the new court
	 */
	public void setCourt(Court court) {
		this.court = court;
	}

	/**
	 * Gets the evt data.
	 *
	 * @return the evt data
	 */
	public Evt_data getEvt_data() {
		return evt_data;
	}

	/**
	 * Sets the evt data.
	 *
	 * @param evt_data
	 *            the new evt data
	 */
	public void setEvt_data(Evt_data evt_data) {
		this.evt_data = evt_data;
	}

	/**
	 * Gets the officers.
	 *
	 * @return the officers
	 */
	public Officers getOfficers() {
		return officers;
	}

	/**
	 * Sets the officers.
	 *
	 * @param officers
	 *            the new officers
	 */
	public void setOfficers(Officers officers) {
		this.officers = officers;
	}

	/**
	 * Gets the disputant.
	 *
	 * @return the disputant
	 */
	public Disputant getDisputant() {
		return disputant;
	}

	/**
	 * Sets the disputant.
	 *
	 * @param disputant
	 *            the new disputant
	 */
	public void setDisputant(Disputant disputant) {
		this.disputant = disputant;
	}

	/**
	 * Gets the vehicle.
	 *
	 * @return the vehicle
	 */
	public Vehicle getVehicle() {
		return vehicle;
	}

	/**
	 * Sets the vehicle.
	 *
	 * @param vehicle
	 *            the new vehicle
	 */
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	/**
	 * Gets the evt obj.
	 *
	 * @return the evt obj
	 */
	@JsonIgnore
	private CreateContraventionRequest util_getEvtXMLObject() {
		if (this.evtXMLObject != null) {
			return this.evtXMLObject;
		}
		if (this.getEvt_data() != null && !this.getEvt_data().getEvt_xml().isEmpty()) {
			try {
				// base64 decode the evtXML
				String evtXML = this.getEvt_data().getEvt_xml();
				String base64DecodedEvtXML = new String(Base64.getDecoder().decode(evtXML));

				JAXBContext jaxbContext = JAXBContext.newInstance(CreateContraventionRequest.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				this.evtXMLObject = (CreateContraventionRequest) JAXBIntrospector
						.getValue(jaxbUnmarshaller.unmarshal(new StreamSource(new StringReader(base64DecodedEvtXML))));
			} catch (JAXBException e) {
				// logger.error("Fail to parse evtXML, error message: [{}] ",
				// e.getMessage());
			}
		}
		return this.evtXMLObject;
	}

	/**
	 * Gets the ticket number.
	 *
	 * @return the ticket number
	 */
	@JsonIgnore
	public String getTicket_number() {
		if (StringUtils.isEmpty(this.contravention)) {
			return null;
		}
		return EvtEnrichUtil.getTicketNumber(this.contravention.getNumber());
	}

	/**
	 * Gets the count number.
	 *
	 * @return the count number
	 */
	@JsonIgnore
	public String getCount_number() {
		if (StringUtils.isEmpty(this.contravention)) {
			return null;
		}
		return EvtEnrichUtil.getCountNumber(this.contravention.getNumber());
	}

	/**
	 * Gets the accident.
	 *
	 * @return the accident
	 */
	@JsonIgnore
	public Boolean getAccident() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getVehicle() != null &&
				obj.getVehicle().getValue() != null &&
				obj.getVehicle().getValue().getAccident() != null) {
			
			if (BoolYN.N.equals(obj.getVehicle().getValue().getAccident().getValue())) {
				return false;
			} else if (BoolYN.Y.equals(obj.getVehicle().getValue().getAccident().getValue())) {
				return true;
			} else {
				return null;
			}
			
		} else {
			return null;
		}
	}

	/**
	 * Gets the cos form number.
	 *
	 * @return the cos form number
	 */
	@JsonIgnore
	public String getCos_form_number() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getCertificateOfService() != null) {
			return obj.getCertificateOfService().getValue().getCOSFormNumber();
		} else {
			return null;
		}
	}

	/**
	 * Gets the evt form number.
	 *
	 * @return the evt form number
	 */
	@JsonIgnore
	public String getEvt_form_number() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getViolation() != null) {
			return obj.getViolation().getEVTFormNumber();
		} else {
			return null;
		}
	}

	/**
	 * Gets the mre minor version.
	 *
	 * @return the mre minor version
	 */
	@JsonIgnore
	public String getMre_minor_version() {
		// RSIFC-5820 Return SchemaStyleVersion if it exists, otherwise return null
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getSchemaStyleVersion() != null) {
			return obj.getSchemaStyleVersion().getValue();
		} else {
			return null;
		}
	}

	/**
	 * Gets the enforcement officer 1 name.
	 *
	 * @return the enforcement officer 1 name
	 */
	@JsonIgnore
	public String getEnforcement_officer1_name() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getEnforcement() != null) {
			if(obj.getEnforcement().getOfficerName1()!=null){
				return obj.getEnforcement().getOfficerName1().getValue();
			}else{
				return null;
			}
//			return obj.getEnforcement().getOfficerName1().getValue();
		} else {
			return null;
		}
	}

	/**
	 * Gets the enforcement detachment.
	 *
	 * @return the enforcement detachment
	 */
	@JsonIgnore
	public String getEnforcement_detachment() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getEnforcement() != null) {
			if(obj.getEnforcement().getDetachment()!=null){
				return obj.getEnforcement().getDetachment().getValue();
			}else{
				return null;
			}

		} else {
			return null;
		}
	}

	/**
	 * Gets the enforcement detachment code.
	 *
	 * @return the enforcement detachment code
	 */
	@JsonIgnore
	public String getEnforcement_detachment_code() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getEnforcement() != null) {
			if(obj.getEnforcement().getDetachmentCode()!=null){
				return obj.getEnforcement().getDetachmentCode().getValue();
			}else{
				return null;
			}
//			return obj.getEnforcement().getDetachmentCode().getValue();
		} else {
			return null;
		}
	}

	/**
	 * Gets the witnessing officer number.
	 *
	 * @return the witnessing officer number
	 */
	@JsonIgnore
	public String getWitnessing_officer_number() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getEnforcement() != null &&
				obj.getEnforcement().getWitnessingOfficerID() != null) {
			return obj.getEnforcement().getWitnessingOfficerID().getValue();
		} else {
			return null;
		}
	}

	/**
	 * Gets the witnessing officer name.
	 *
	 * @return the witnessing officer name
	 */
	@JsonIgnore
	public String getWitnessing_officer_name() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getEnforcement() != null &&
				obj.getEnforcement().getWitnessingOfficerName() != null) {
			return obj.getEnforcement().getWitnessingOfficerName().getValue();
		} else {
			return null;
		}
	}

	/**
	 * Gets the registered owner name.
	 *
	 * @return the registered owner name
	 */
	@JsonIgnore
	public String getRegistered_owner_name() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null &&
				obj.getVehicle() != null &&
				obj.getVehicle().getValue() != null &&
				obj.getVehicle().getValue().getRegisteredOwnerName() != null) {
			return obj.getVehicle().getValue().getRegisteredOwnerName().getValue();
		} else {		
			return null;
		}
	}

	/**
	 * Gets the type code.
	 *
	 * @return the type code
	 */
	@JsonIgnore
	public String getType_code() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null && 
				obj.getVehicle() != null && 
				obj.getVehicle().getValue() != null && 
				obj.getVehicle().getValue().getTypeCode() != null) {
			return obj.getVehicle().getValue().getTypeCode().getValue();
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the young person YN.
	 *
	 * @return the young person YN
	 */
	@JsonIgnore
	public Boolean getYoungPersonYN() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null && 
				obj.getParty() != null && 
				obj.getParty().getIndividualParty() != null && 
				obj.getParty().getIndividualParty().getYoungPerson() != null) {
			if (BoolYN.N.equals(obj.getParty().getIndividualParty().getYoungPerson().getValue())) {
				return false;
			} else if (BoolYN.Y.equals(obj.getParty().getIndividualParty().getYoungPerson().getValue())) {
				return true;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the DL produced YN.
	 *
	 * @return the DL produced YN
	 */
	@JsonIgnore
	public Boolean getDLProducedYN() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null && 
				obj.getParty() != null && 
				obj.getParty().getIndividualParty() != null && 
				obj.getParty().getIndividualParty().getDriverLicense() != null &&
				obj.getParty().getIndividualParty().getDriverLicense().getValue() != null &&
				obj.getParty().getIndividualParty().getDriverLicense().getValue().getLicenseProduced() != null) {
			if (BoolYN.N.equals(obj.getParty().getIndividualParty().getDriverLicense().getValue().getLicenseProduced().getValue())) {
				return false;
			} else if (BoolYN.Y.equals(obj.getParty().getIndividualParty().getDriverLicense().getValue().getLicenseProduced().getValue())) {
				return true;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
	
	/**
	 * Gets the change addres YN.
	 *
	 * @return the change addres YN
	 */
	@JsonIgnore
	public Boolean getChangeAddressYN() {
		CreateContraventionRequest obj = util_getEvtXMLObject();
		if (
				obj != null && 
				obj.getParty() != null && 
				obj.getParty().getIndividualParty() != null && 
				obj.getParty().getIndividualParty().getChangeOfAddressFlag() != null) {
			if (BoolYN.N.equals(obj.getParty().getIndividualParty().getChangeOfAddressFlag().getValue())) {
				return false;
			} else if (BoolYN.Y.equals(obj.getParty().getIndividualParty().getChangeOfAddressFlag().getValue())) {
				return true;
			} else {
				return null;
			}
		} else {
			return null;
		}
	}
}
