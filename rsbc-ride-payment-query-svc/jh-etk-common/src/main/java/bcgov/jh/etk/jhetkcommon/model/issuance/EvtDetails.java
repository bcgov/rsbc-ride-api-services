package bcgov.jh.etk.jhetkcommon.model.issuance;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;
import jakarta.xml.bind.annotation.XmlType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "EVTDetails")
@XmlType(propOrder = { "amountOfPenalty", "changeOfAddressFlag", "carrierJurisdiction", "carrierRegNumber", 
		"certificateOfServiceCompleted", "certificateOfServiceServedTo", "certificateOfServiceDateOfService", 
		"certificateOfServiceEntityPerson", "courtLocationCode", "dateOfBirth", "disputeAddress", "driversLicenseExpiryDate", 
		"driversLicenseNumber", "driversLicenseProducedFlag", "driversLicenseProvinceCode","driversLicenseProvinceName","enforcementOfficerName",
		"enforcementOfficerLastName", "enforcementOfficerNumber", "enforcementOfficerPartnerName", "enforcementOfficerPartnerLastName", "enforcementOfficerPartnerNumber", 
		"enforcementOrganizationJurisdiction", "enforcementOrganizationJurisdictionCode", "evtPrinted", "givenNameOne", 
		"givenNameTwo", "givenNameThree", "MREMinorVersion", "offenceDescription", "offenderType", "organizationUnit", "personAddress",
		"personResidenceCity", "personResidenceProvinceCode", "personResidenceProvinceName", "personResidencePostalCode", 
		"registeredUserName", "reportDate", "reportTime", "sex", "surname", "statuteAct", "statuteSection", "ticketClass", 
		"ticketNumber", "ticketStatus", "ticketCOSFormNumber", "ticketEVTFormNumber", "violationDate", "violationTime", 
		"violationLocation", "violationCityCode", "violationCityName", "vehicleAccidentFlag", "vehicleColour", 
		"vehicleLicenseProvinceCode", "vehicleLicenseProvinceName", "vehiclePlateNo", "vehicleMakeCode", "vehicleMakeDescription", 
		"vehicleTypeCode", "vehicleTypeDescription", "vehicleYear", "youngPersonFlag", "counts"})
@XmlAccessorType(XmlAccessType.NONE)
public class EvtDetails {

	@XmlElement(name = "amountOfPenalty")
	private String amountOfPenalty;
	
	@XmlElement(name = "changeOfAddressFlag")
	private String changeOfAddressFlag;
	
	@XmlElement(name = "carrierJurisdiction")
	private String carrierJurisdiction;
	
	@XmlElement(name = "carrierRegNumber")
	private String carrierRegNumber;
	
	@XmlTransient
	private CertificateOfService certificateOfService = new CertificateOfService();
	
	@XmlElement(name = "courtLocationCode")
	private String courtLocationCode;
	
	@XmlElement(name = "dateOfBirth")
	private String dateOfBirth;
	
	@XmlElement(name = "disputeAddress")
	private String disputeAddress;
	
	@XmlTransient
	private DriversLicense driversLicense = new DriversLicense();
	
	@XmlTransient
	private EnforcementOfficer enforcementOfficer = new EnforcementOfficer();

	@XmlElement(name = "enforcementOrganizationJurisdiction")
	private String enforcementOrganizationJurisdiction;

	@XmlElement(name = "enforcementOrganizationJurisdictionCode")
	private String enforcementOrganizationJurisdictionCode;
	
	@XmlElement(name = "evtPrinted")
	private String evtPrinted;
	
	@XmlElement(name = "givenNameOne")
	private String givenNameOne;
	
	@XmlElement(name = "givenNameTwo")
	private String givenNameTwo;
	
	@XmlElement(name = "givenNameThree")
	private String givenNameThree;

	@XmlElement(name = "MREMinorVersion")
	private String MREMinorVersion;

	@XmlElement(name = "offenceDescription")
	private String offenceDescription;
	
	@XmlElement(name = "offenderType")
	private String offenderType;
	
	@XmlElement(name = "organizationUnit")
	private String organizationUnit;
	
	@XmlTransient
	private Person person = new Person();
	
	@XmlElement(name = "registeredUserName")
	private String registeredUserName;
	
	@XmlElement(name = "reportDate")
	private String reportDate;
	
	@XmlElement(name = "reportTime")
	private String reportTime;
	
	@XmlElement(name = "sex")
	private String sex;
	
	@XmlElement(name = "surname")
	private String surname;
	
	@XmlElement(name = "statuteAct")
	private String statuteAct;
	
	@XmlElement(name = "statuteSection")
	private String statuteSection;
	
	@XmlTransient
	private Ticket ticket = new Ticket();
	
	@XmlTransient
	private Violation violation = new Violation();
	
	@XmlTransient
	private Vehicle vehicle = new Vehicle();
	
	@XmlElement(name = "counts")
	private List<Counts> counts;
	
	
	@XmlElement(name = "youngPersonFlag")
	private String youngPersonFlag;

	@XmlElement(name = "certificateOfServiceCompleted")
	public String getCertificateOfServiceCompleted() {
		return certificateOfService.getCompleted();
	}
	public void setCertificateOfServiceCompleted(String completed) {
		this.certificateOfService.setCompleted(completed);
	}

	@XmlElement(name = "certificateOfServiceServedTo")
	public String getCertificateOfServiceServedTo() {
		return certificateOfService.getServedTo();
	}
	public void setCertificateOfServiceServedTo(String servedTo) {
		this.certificateOfService.setServedTo(servedTo);
	}

	@XmlElement(name = "certificateOfServiceDateOfService")
	public String getCertificateOfServiceDateOfService() {
		return certificateOfService.getDateOfService();
	}
	public void setCertificateOfServiceDateOfService(String dateOfService) {
		this.certificateOfService.setDateOfService(dateOfService);
	}

	@XmlElement(name = "certificateOfServiceEntityPerson")
	public String getCertificateOfServiceEntityPerson() {
		return certificateOfService.getEntityPerson();
	}
	public void setCertificateOfServiceEntityPerson(String entityPerson) {
		this.certificateOfService.setEntityPerson(entityPerson);
	}
	
	@XmlElement(name = "driversLicenseExpiryDate")
	public String getDriversLicenseExpiryDate() {
		return driversLicense.getExpiryDate();
	}
	public void setDriversLicenseExpiryDate(String expiryDate) {
		this.driversLicense.setExpiryDate(expiryDate);
	}
	
	@XmlElement(name = "driversLicenseNumber")
	public String getDriversLicenseNumber() {
		return driversLicense.getNumber();
	}
	public void setDriversLicenseNumber(String number) {
		this.driversLicense.setNumber(number);
	}
	
	@XmlElement(name = "driversLicenseProducedFlag")
	public String getDriversLicenseProducedFlag() {
		return driversLicense.getProducedFlag();
	}
	public void setDriversLicenseProducedFlag(String producedFlag) {
		this.driversLicense.setProducedFlag(producedFlag);
	}
	
	@XmlElement(name = "driversLicenseProvinceCode")
	public String getDriversLicenseProvinceCode() {
		return driversLicense.getProvinceCode();
	}
	public void setDriversLicenseProvinceCode(String provinceCode) {
		this.driversLicense.setProvinceCode(provinceCode);
	}

	@XmlElement(name = "driversLicenseProvinceName")
	public String getDriversLicenseProvinceName() {
		return driversLicense.getProvinceName();
	}
	public void setDriversLicenseProvinceName(String provinceName) {
		this.driversLicense.setProvinceName(provinceName);
	}

	@XmlElement(name = "enforcementOfficerName")
	public String getEnforcementOfficerName() {
		return enforcementOfficer.getName();
	}
	public void setEnforcementOfficerName(String name) {
		this.enforcementOfficer.setName(name);
	}
	
	@XmlElement(name = "enforcementOfficerLastName")
	public String getEnforcementOfficerLastName() {
		return enforcementOfficer.getLastName();
	}
	
	public void setEnforcementOfficerLastName(String name) {
		this.enforcementOfficer.setLastName(name);
	}
	
	@XmlElement(name = "enforcementOfficerNumber")
	public String getEnforcementOfficerNumber() {
		return enforcementOfficer.getNumber();
	}
	public void setEnforcementOfficerNumber(String number) {
		this.enforcementOfficer.setNumber(number);
	}
	
	@XmlElement(name = "enforcementOfficerPartnerName")
	public String getEnforcementOfficerPartnerName() {
		return enforcementOfficer.getPartnerName();
	}
	public void setEnforcementOfficerPartnerName(String partnerName) {
		this.enforcementOfficer.setPartnerName(partnerName);
	}
	
	@XmlElement(name = "enforcementOfficerPartnerLastName")
	public String getEnforcementOfficerPartnerLastName() {
		return enforcementOfficer.getPartnerLastName();
	}
	public void setEnforcementOfficerPartnerLastName(String partnerName) {
		this.enforcementOfficer.setPartnerLastName(partnerName);
	}
	
	@XmlElement(name = "enforcementOfficerPartnerNumber")
	public String getEnforcementOfficerPartnerNumber() {
		return enforcementOfficer.getPartnerNumber();
	}
	public void setEnforcementOfficerPartnerNumber(String partnerNumber) {
		this.enforcementOfficer.setPartnerNumber(partnerNumber);
	}
	
	@XmlElement(name = "personAddress")
	public String getPersonAddress() {
		return person.getAddress();
	}
	public void setPersonAddress(String address) {
		this.person.setAddress(address);
	}
	
	@XmlElement(name = "personResidenceCity")
	public String getPersonResidenceCity() {
		return person.getResidenceCity();
	}
	public void setPersonResidenceCity(String residenceCity) {
		this.person.setResidenceCity(residenceCity);
	}
	
	@XmlElement(name = "personResidenceProvinceCode")
	public String getPersonResidenceProvinceCode() {
		return person.getResidenceProvinceCode();
	}
	public void setPersonResidenceProvinceCode(String residenceProvinceCode) {
		this.person.setResidenceProvinceCode(residenceProvinceCode);
	}
	
	@XmlElement(name = "personResidenceProvinceName")
	public String getPersonResidenceProvinceName() {
		return person.getResidenceProvinceName();
	}
	public void setPersonResidenceProvinceName(String residenceProvinceName) {
		this.person.setResidenceProvinceName(residenceProvinceName);
	}
	
	@XmlElement(name = "personResidencePostalCode")
	public String getPersonResidencePostalCode() {
		return person.getResidencePostalCode();
	}
	public void setPersonResidencePostalCode(String residencePostalCode) {
		this.person.setResidencePostalCode(residencePostalCode);
	}
	
	@XmlElement(name = "ticketClass")
	public String getTicketClass() {
		return ticket.getTicketClass();
	}
	public void setTicketClass(String ticketClass) {
		this.ticket.setTicketClass(ticketClass);
	}
	
	@XmlElement(name = "ticketNumber")
	public String getTicketNumber() {
		return ticket.getNumber();
	}
	public void setTicketNumber(String number) {
		this.ticket.setNumber(number);
	}
	
	@XmlElement(name = "ticketStatus")
	public String getTicketStatus() {
		return ticket.getStatus();
	}
	public void setTicketStatus(String status) {
		this.ticket.setStatus(status);
	}
	
	@XmlElement(name = "ticketCOSFormNumber")
	public String getTicketCOSFormNumber() {
		return ticket.getCosFormNumber();
	}
	public void setTicketCOSFormNumber(String cosFormNumber) {
		this.ticket.setCosFormNumber(cosFormNumber);
	}
	
	@XmlElement(name = "ticketEVTFormNumber")
	public String getTicketEVTFormNumber() {
		return ticket.getEvtFormNumber();
	}
	public void setTicketEVTFormNumber(String evtFormNumber) {
		this.ticket.setEvtFormNumber(evtFormNumber);
	}
	
	@XmlElement(name = "violationDate")
	public String getViolationDate() {
		return violation.getDate();
	}
	public void setViolationDate(String date) {
		this.violation.setDate(date);
	}
	
	@XmlElement(name = "violationTime")
	public String getViolationTime() {
		return violation.getTime();
	}
	public void setViolationTime(String time) {
		this.violation.setTime(time);
	}
	
	@XmlElement(name = "violationLocation")
	public String getViolationLocation() {
		return violation.getLocation();
	}
	public void setViolationLocation(String location) {
		this.violation.setLocation(location);
	}
	
	@XmlElement(name = "violationCityCode")
	public String getViolationCityCode() {
		return violation.getCityCode();
	}
	public void setViolationCityCode(String cityCode) {
		this.violation.setCityCode(cityCode);
	}
	
	@XmlElement(name = "violationCityName")
	public String getViolationCityName() {
		return violation.getCityName();
	}
	public void setViolationCityName(String cityName) {
		this.violation.setCityName(cityName);
	}
	
	@XmlElement(name = "vehicleAccidentFlag")
	public String getVehicleAccidentFlag() {
		return vehicle.getAccidentFlag();
	}
	public void setVehicleAccidentFlag(String accidentFlag) {
		this.vehicle.setAccidentFlag(accidentFlag);
	}
	
	@XmlElement(name = "vehicleColour")
	public String getVehicleColour() {
		return vehicle.getColour();
	}
	public void setVehicleColour(String colour) {
		this.vehicle.setColour(colour);
	}
	
	@XmlElement(name = "vehicleLicenseProvinceCode")
	public String getVehicleLicenseProvinceCode() {
		return vehicle.getLicenseProvinceCode();
	}
	public void setVehicleLicenseProvinceCode(String licenseProvinceCode) {
		this.vehicle.setLicenseProvinceCode(licenseProvinceCode);
	}
	
	@XmlElement(name = "vehicleLicenseProvinceName")
	public String getVehicleLicenseProvinceName() {
		return vehicle.getLicenseProvinceName();
	}
	public void setVehicleLicenseProvinceName(String licenseProvinceName) {
		this.vehicle.setLicenseProvinceName(licenseProvinceName);
	}
	
	@XmlElement(name = "vehiclePlateNo")
	public String getVehiclePlateNo() {
		return vehicle.getPlateNo();
	}
	public void setVehiclePlateNo(String plateNo) {
		this.vehicle.setPlateNo(plateNo);
	}
	
	@XmlElement(name = "vehicleMakeCode")
	public String getVehicleMakeCode() {
		return vehicle.getMakeCode();
	}
	public void setVehicleMakeCode(String makeCode) {
		this.vehicle.setMakeCode(makeCode);
	}
	
	@XmlElement(name = "vehicleMakeDescription")
	public String getVehicleMakeDescription() {
		return vehicle.getMakeDescription();
	}
	public void setVehicleMakeDescription(String makeDescription) {
		this.vehicle.setMakeDescription(makeDescription);
	}
	
	@XmlElement(name = "vehicleTypeCode")
	public String getVehicleTypeCode() {
		return vehicle.getTypeCode();
	}
	public void setVehicleTypeCode(String typeCode) {
		this.vehicle.setTypeCode(typeCode);
	}
	
	@XmlElement(name = "vehicleTypeDescription")
	public String getVehicleTypeDescription() {
		return vehicle.getTypeDescription();
	}
	public void setVehicleTypeDescription(String typeDescription) {
		this.vehicle.setTypeDescription(typeDescription);
	}
	
	@XmlElement(name = "vehicleYear")
	public String getVehicleYear() {
		return vehicle.getYear();
	}
	public void setVehicleYear(String year) {
		this.vehicle.setYear(year);
	}

}
