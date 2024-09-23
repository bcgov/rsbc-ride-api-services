package bcgov.jh.etk.jhetkcommon.model.issuance;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.util.Base64Adapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;

/**
 * Instantiates a new evt canonical.
 */
@NoArgsConstructor

/**
 * Instantiates a new evt canonical.
 *
 * @param evtDetails the evt details
 * @param primeFileXml the prime file xml
 * @param filename the filename
 */
@AllArgsConstructor

/**
 * Gets the filename.
 *
 * @return the filename
 */
@Getter

/**
 * Sets the filename.
 *
 * @param filename the new filename
 */
@Setter
@XmlRootElement(name = "EVTCanonical")
@XmlType(propOrder = {"evtDetails", "primeFileXml", "filename", "ticketID"})
@XmlAccessorType(XmlAccessType.NONE)
public class EvtCanonical {

	/**
	 * The evt details.
	 */
	@XmlElement(name = "EVTDetails")
	private EvtDetails evtDetails;

	/**
	 * The prime file xml.
	 */
	@XmlElement(name = "primeFileXml")
	@XmlJavaTypeAdapter(Base64Adapter.class)
	private String primeFileXml;

	/**
	 * The filename.
	 */
	@XmlElement(name = "filename")
	private String filename;

	/** The ticket ID. */
	@XmlElement(name = "ticketID")
	private Integer ticketID;
	
	/**
	 * Checks if is valid.
	 *
	 * @return true, if is valid
	 */
	public String isValid() {
		String errorDesc = "";

		if (!evtDetails.getEvtPrinted().equals("Y")) {
			errorDesc += "Value of EVT_PRINTED should be 'Y'\n";
		}
		if (!evtDetails.getTicketStatus().equals("I")) {
			errorDesc += "Value of TICKET_STATUS should be 'I'\n";
		}
		if (!evtDetails.getTicketClass().equals("P")) {
			errorDesc += "Value of TICKET_CLASS should be 'P'\n";
		}
		if (evtDetails.getTicketNumber().isEmpty()) {
			errorDesc += "TicketNumber is empty\n";
		}
		return errorDesc;
	}

	/**
	 * Validate business warning rules.
	 *
	 * @return the string
	 */
	public String validateBusinessWarningRules() {

		String warningReport = "";
		if (StringUtils.isEmpty(evtDetails.getCourtLocationCode())) {
			warningReport += "COURT LOCATION (XML_FILE/REPORT/CUSTOMDATA/TK_COURT_HEARING_LOCATION_TRANS) not found - Potential court table mapping error\n";
		}
		if (StringUtils.isEmpty(evtDetails.getDisputeAddress())) {
			warningReport += "DISPUTE ADDRESS (XML_FILE/REPORT/CUSTOMDATA/TK_DISPUTE_LOCATION_ADDRESS_TRANS)not found - Grounds for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getSurname())) {
			warningReport += "PERSON SURNAME (XML_FILE/REPORT/PERSON/INDIVIDUAL_SURNAME) not found - Grounds for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getViolationLocation())) {
			warningReport += "VIOLATION HIGHWAY (XML_FILE/REPORT/STREET_NAME) not found - Grounds for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getEnforcementOfficerName())) {
			warningReport += "ENFORCEMENT OFFICER NAME (XML_FILE/REPORT/OFFICER_ID_TRANS) not found - Grounds for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getEnforcementOfficerNumber())) {
			warningReport += "ENFORCEMENT OFFICER NUMBER # (XML_FILE/REPORT/OFFICER_ID) not found - Grounds for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getViolationCityName())) {
			warningReport += "VIOLATION CITY (XML_FILE/REPORT/MUNICIPALITY_TRANS) not found - Potential ground for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getViolationCityCode())) {
			warningReport += "VIOLATION CITY CODE (XML_FILE/REPORT/MUNICIPALITY) not found - Potential ground for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getEnforcementOrganizationJurisdiction())) {
			warningReport += "ENFORCEMENT ORGANIZATION DETACHMENT (XML_FILE/REPORT/JURISDICTION_TRANS) not found - Potential ground for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getEnforcementOrganizationJurisdictionCode())) {
			warningReport += "ENFORCEMENT ORGANIZATION DETACHMENT CODE (XML_FILE/REPORT/JURISDICTION) not found - Potential ground for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getOffenceDescription())) {
			warningReport += "CHARGE_TEXT (XML_FILE/REPORT/CHARGE_TEXT) not found - Potential charge table mapping error; Grounds for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getStatuteAct())) {
			warningReport += "STATUTE_ACT (XML_FILE/REPORT/STATUTE_ACT) not found - Potential charge table mapping error; Grounds for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getStatuteSection())) {
			warningReport += "LEGAL STATUTE SECTION (XML_FILE/REPORT/LEGAL_STATUTE_SECTION) not found - Potential charge table mapping error; Grounds for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getAmountOfPenalty())) {
			warningReport += "FINE_AMOUNT (XML_FILE/REPORT/SET_FINE_AMOUNT) not found - Potential charge table mapping error; Grounds for ICBC to cancel\n";
		}
		if (StringUtils.isEmpty(evtDetails.getCertificateOfServiceDateOfService())) {
			warningReport += "TK_CERTIFICATE_DATE_OF_SERVICE (XML_FILE/REPORT/CUSTOMDATA/TK_CERTIFICATE_DATE_OF_SERVICE) not found\n";
		}
		if (StringUtils.isEmpty(evtDetails.getViolationDate())) {
			warningReport += "OFFENCE_DATE (XML_FILE/REPORT/OFFENCE_DATE) not found\n";
		}
		if (StringUtils.isEmpty(evtDetails.getCertificateOfServiceServedTo())) {
			warningReport += "TK_CERTIFICATE_SERVED_TO (XML_FILE/REPORT/CUSTOMDATA/TK_CERTIFICATE_SERVED_TO) not found\n";
		}
		if (StringUtils.isEmpty(evtDetails.getTicketCOSFormNumber())) {
			warningReport += "TICKET_ORIGIN_TRANS (XML_FILE/REPORT/TICKET_ORIGIN_TRANS) not found\n";
		}
				
		if (evtDetails.getCounts() != null) {
			for (Counts evtCount : evtDetails.getCounts()) {
				if (StringUtils.isEmpty(evtCount.getAct())) {
					warningReport += "COUNT ACT OR REGULATION (XML_FILE/REPORT/PERSON/CHARGE[" + evtCount.getCountNumber() + "]/STATUTE_NAME)  not found - Potential charge table mapping error; Grounds for ICBC to cancel\n";
				}
				if (StringUtils.isEmpty(evtCount.getSection())) {
					warningReport += "COUNT SECTION  (XML_FILE/REPORT/PERSON/CHARGE[" + evtCount.getCountNumber() + "]/LEGAL_STATUTE_SECTION) not found - Potential charge table mapping error; Grounds for ICBC to cancel\n";
				}
				if (StringUtils.isEmpty(evtCount.getOffenseDescription())) {
					warningReport += "COUNT DESCRIPTION OF OFFENCE (XML_FILE/REPORT/PERSON/CHARGE[" + evtCount.getCountNumber() + "]/CHARGE_TEXT) not found - Potential charge table mapping error; Grounds for ICBC to cancel\n";
				}
				if (StringUtils.isEmpty(evtCount.getAmount())) {
					warningReport += "COUNT TICKETED AMOUNT (XML_FILE/REPORT/PERSON/CHARGE[" + evtCount.getCountNumber() + "]/AMOUNT_OF_PENALTY)  not found - Potential charge table mapping error; Grounds for ICBC to cancel\n";
				}
			}
		}
		return warningReport;
	}
	
	
	/**
	 * Map MRE minor version to ICBC schema style version.
	 *
	 * @return the string
	 */
	public String mapMREMinorVersionToICBCSchemaStyleVersion() {
		String schemaStyleVersion = "";
		String MREMinorVersion = evtDetails.getMREMinorVersion();
		switch (MREMinorVersion) {
			case "7.3.367.1":
			case "7.3.369.1":
			case "7.3.377.1":
			case "7.3.381.1":
				break;
			case "7.3.385.1":
			case "7.3.388.1":
			case "7.3.393.1":
			case "7.3.394.1":
			case "7.3.399.1":
				schemaStyleVersion = Const.ICBC_SCHEMA_STYLE_VERSION_1_2;
				break;
			default:
				return null;
		}
		return schemaStyleVersion;
	}
}
