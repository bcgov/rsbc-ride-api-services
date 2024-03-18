package bcgov.jh.etk.jhetkcommon.model.issuance;


import java.util.ArrayList;
import java.util.List;

public class EvtCanonicalFactory {

	public static EvtCanonical createEvtCanonical() {
		EvtCanonical evtCanonical = new EvtCanonical();
		evtCanonical.setEvtDetails(getEvtDetails());
		evtCanonical.setFilename("dir/test");
		evtCanonical.setPrimeFileXml("<?xml version='1.0' encoding='ISO-8859-1'?>\r\n" + 
				"<XML_FILE>\r\n" + 
				"  <TRANSFER/>\r\n" + 
				"  <MRE_VERSION/>\r\n" + 
				"  <MINOR_VERSION/>\r\n" + 
				"  <TABLE_RELEASE_DATE/>\r\n" + 
				"  <PROFILE_NAME/>\r\n" + 
				"  <AGENCY_NAME>DEAS ISLAND TRAFFIC RCMP</AGENCY_NAME>\r\n" + 
				"  <FILE_NAME>EZ04000112.xml</FILE_NAME>\r\n" + 
				"  <SUBMIT_BY/>\r\n" + 
				"  <SUBMIT_BY_TRANS/>\r\n" + 
				"  <ORG_UNIT/>\r\n" + 
				"  <ORG_UNIT_TRANS/>\r\n" + 
				"  <CASE_TYPE/>\r\n" + 
				"  <SUBMIT_DATE>2018-08-30</SUBMIT_DATE>\r\n" + 
				"  <SENT_TIME>0902</SENT_TIME>\r\n" + 
				"  <REPORT TYPE=\"TK\">\r\n" + 
				"    <JURISDICTION>1004</JURISDICTION>\r\n" + 
				"    <JURISDICTION_TRANS>DEAS ISLAND TRAFFIC RCMP</JURISDICTION_TRANS>\r\n" + 
				"    <OFFENCE_COUNT>3</OFFENCE_COUNT>\r\n" + 
				"    <TICKET_TYPE>EZ</TICKET_TYPE>\r\n" + 
				"    <TICKET_TYPE_TRANS>EVT SIT TESTING</TICKET_TYPE_TRANS>\r\n" + 
				"    <TICKET_NUM>EZ04000112</TICKET_NUM>\r\n" + 
				"    <TICKET_CLASS>P</TICKET_CLASS>\r\n" + 
				"    <TICKET_STATUS>I</TICKET_STATUS>\r\n" + 
				"    <TICKET_ORIGIN>1</TICKET_ORIGIN>\r\n" + 
				"    <TICKET_ORIGIN_TRANS>MV6010(082018)</TICKET_ORIGIN_TRANS>\r\n" + 
				"    <TK_ROLE>DRIVER</TK_ROLE>\r\n" + 
				"    <OFFENCE_DATE>2018-08-30</OFFENCE_DATE>\r\n" + 
				"    <OFFENCE_TIME>0852</OFFENCE_TIME>\r\n" + 
				"    <STREET_NAME>HIGHWAY</STREET_NAME>\r\n" + 
				"    <MUNICIPALITY>SRY</MUNICIPALITY>\r\n" + 
				"    <MUNICIPALITY_TRANS>SURREY</MUNICIPALITY_TRANS>\r\n" + 
				"    <OFFICER_ID>MW1234</OFFICER_ID>\r\n" + 
				"    <OFFICER_ID_TRANS>A. JOHNSON MCNALLY</OFFICER_ID_TRANS>\r\n" + 
				"    <AMOUNT_OF_PENALTY>138.00</AMOUNT_OF_PENALTY>\r\n" + 
				"    <PAYMENT_METHOD>V</PAYMENT_METHOD>\r\n" + 
				"    <PAYMENT_METHOD_TRANS>MV6000E(082018)</PAYMENT_METHOD_TRANS>\r\n" + 
				"    <PRINTED>Y</PRINTED>\r\n" + 
				"    <STATUTE_ACT>MV</STATUTE_ACT>\r\n" + 
				"    <STATUTE_ACT_TRANS>MOTOR VEHICLE ACT</STATUTE_ACT_TRANS>\r\n" + 
				"    <LEGAL_STATUTE_SECTION>146(1)</LEGAL_STATUTE_SECTION>\r\n" + 
				"    <CHARGE_TEXT>SPEED IN MUNICIPALITY (SPEED EXCEED LESS THAN 21 KMH)</CHARGE_TEXT>\r\n" + 
				"    <PERSON>\r\n" + 
				"      <INDIVIDUAL_SURNAME>MARIANO</INDIVIDUAL_SURNAME>\r\n" + 
				"      <GIVEN_ONE>FABIO</GIVEN_ONE>\r\n" + 
				"      <GIVEN_TWO>RSITEST</GIVEN_TWO>\r\n" + 
				"      <MUNICIPALITY_NAME>VICTORIA</MUNICIPALITY_NAME>\r\n" + 
				"      <PROV_STATE_CODE>BC</PROV_STATE_CODE>\r\n" + 
				"      <POSTAL_ZIPCODE>V8W3Y8</POSTAL_ZIPCODE>\r\n" + 
				"      <GENDER_CODE>M</GENDER_CODE>\r\n" + 
				"      <STREET_NAME>910 GOVERNMENT ST</STREET_NAME>\r\n" + 
				"      <DATE_OF_BIRTH>1960-01-01</DATE_OF_BIRTH>\r\n" + 
				"      <DRIVERS_LICENCE_NUMBER>1700167</DRIVERS_LICENCE_NUMBER>\r\n" + 
				"      <DRIVERS_LICENCE_PROV_STATE>BC</DRIVERS_LICENCE_PROV_STATE>\r\n" + 
				"      <DRIVERS_LICENCE_PROV_STATE_TRANS>BRITISH COLUMBIA</DRIVERS_LICENCE_PROV_STATE_TRANS>\r\n" + 
				"      <CHARGE>\r\n" + 
				"        <ID>2</ID>\r\n" + 
				"        <CHARGE_COUNTER>1</CHARGE_COUNTER>\r\n" + 
				"	      <STATUTE_NAME>MV</STATUTE_NAME>\r\n" + 
				"        <LEGAL_STATUTE_SECTION>129(1)</LEGAL_STATUTE_SECTION>\r\n" + 
				"	      <CHARGE_TEXT>FAIL TO STOP FOR RED LIGHT AT INTERSECTION</CHARGE_TEXT>\r\n" + 
				"        <CHARGE_COUNT>1</CHARGE_COUNT>\r\n" + 
				"        <SET_FINE_AMOUNT>167.00</SET_FINE_AMOUNT>\r\n" + 
				"        <AMOUNT_OF_PENALTY>167.00</AMOUNT_OF_PENALTY>\r\n" + 
				"      </CHARGE>\r\n" + 
				"      <CHARGE>\r\n" + 
				"        <ID>3</ID>\r\n" + 
				"        <CHARGE_COUNTER>2</CHARGE_COUNTER>\r\n" + 
				"	      <STATUTE_NAME>MV</STATUTE_NAME>\r\n" + 
				"        <LEGAL_STATUTE_SECTION>214.2(1)</LEGAL_STATUTE_SECTION>\r\n" + 
				"	      <CHARGE_TEXT>USE AN ELECTRONIC DEVICE WHILE DRIVING</CHARGE_TEXT>\r\n" + 
				"        <CHARGE_COUNT>1</CHARGE_COUNT>\r\n" + 
				"        <SET_FINE_AMOUNT>368.00</SET_FINE_AMOUNT>\r\n" + 
				"        <AMOUNT_OF_PENALTY>368.00</AMOUNT_OF_PENALTY>\r\n" + 
				"      </CHARGE>\r\n" + 
				"    </PERSON>\r\n" + 
				"    <VEHICLE>\r\n" + 
				"      <VEHICLE_LICENCE_NUMBER>MIL123</VEHICLE_LICENCE_NUMBER>\r\n" + 
				"      <LICENCE_PROVINCE>BC</LICENCE_PROVINCE>\r\n" + 
				"      <MODEL_YEAR>2015</MODEL_YEAR>\r\n" + 
				"      <VEHICLE_MAKE>HOND</VEHICLE_MAKE>\r\n" + 
				"      <VEHICLE_MAKE_TRANS>HONDA</VEHICLE_MAKE_TRANS>\r\n" + 
				"      <STYLE>SD</STYLE>\r\n" + 
				"      <ORIGINAL_COLOUR>PURPLE</ORIGINAL_COLOUR>\r\n" + 
				"    </VEHICLE>\r\n" + 
				"    <CUSTOMDATA>\r\n" + 
				"      <TK_YOUNG_PERSON>N</TK_YOUNG_PERSON>\r\n" + 
				"      <TK_DL_PRODUCED>Y</TK_DL_PRODUCED>\r\n" + 
				"      <TK_DL_EXPIRY>2019</TK_DL_EXPIRY>\r\n" + 
				"      <TK_CHANGE_ADDRESS>N</TK_CHANGE_ADDRESS>\r\n" + 
				"      <TK_REG_OWNER_NAME>FABIO RSITEST MARIANO</TK_REG_OWNER_NAME>\r\n" + 
				"      <TK_ACCIDENT>N</TK_ACCIDENT>\r\n" + 
				"      <TK_DISPUTE_LOCATION_ADDRESS_TRANS>1103-115 Fulford-Ganges Rd, Saltspring Island, B.C.</TK_DISPUTE_LOCATION_ADDRESS_TRANS>\r\n" + 
				"      <TK_COURT_HEARING_LOCATION_TRANS>VANCOUVER, B.C.</TK_COURT_HEARING_LOCATION_TRANS>\r\n" + 
				"      <TK_WITNESSING_OFFICER>ZZ0025</TK_WITNESSING_OFFICER>\r\n" + 
				"      <TK_WITNESSING_OFFICER_TRANS>GUPTA, VIVEK</TK_WITNESSING_OFFICER_TRANS>\r\n" + 
				"      <TK_CERTIFICATE_ENTITY_PERSON>Y</TK_CERTIFICATE_ENTITY_PERSON>\r\n" + 
				"      <TK_CERTIFICATE>Y</TK_CERTIFICATE>\r\n" + 
				"      <TK_CERTIFICATE_SERVED_TO>FABIO RSITEST MARIANO</TK_CERTIFICATE_SERVED_TO>\r\n" + 
				"      <TK_CERTIFICATE_DATE_OF_SERVICE>2018-08-30</TK_CERTIFICATE_DATE_OF_SERVICE>\r\n" + 
				"    </CUSTOMDATA>\r\n" + 
				"  </REPORT>\r\n" + 
				"</XML_FILE>\r\n" + 
				"");
		return evtCanonical;
	}

	private static EvtDetails getEvtDetails() {
		EvtDetails evtDetails = new EvtDetails();
		evtDetails.setAmountOfPenalty("167.00");
		evtDetails.setChangeOfAddressFlag("N");
		evtDetails.setCarrierJurisdiction("BC");
		evtDetails.setCarrierRegNumber("017001709");

		evtDetails.setCertificateOfServiceCompleted("Y");
		evtDetails.setCertificateOfServiceServedTo("KATE RSITEST  B&#39;YUN");
		evtDetails.setCertificateOfServiceDateOfService("2018-01-18");
		evtDetails.setCertificateOfServiceEntityPerson("Y");

		evtDetails.setCourtLocationCode("VANCOUVER, B.C.");
		evtDetails.setDateOfBirth("1960-01-01");
		evtDetails.setDisputeAddress("1103-115 Fulford-Ganges Rd, Ganges (Saltspring Island), B.C.");



		evtDetails.setEnforcementOfficerName("A. JOHNSON MCNALLY");
		evtDetails.setEnforcementOfficerNumber("MW1234");
		evtDetails.setEnforcementOfficerPartnerName("GUPTA, VIVEK");
		evtDetails.setEnforcementOfficerPartnerNumber("ZZ0025");

		evtDetails.setEnforcementOrganizationJurisdiction("DEAS ISLAND TRAFFIC RCMP");
		evtDetails.setEnforcementOrganizationJurisdictionCode("1004");
		evtDetails.setEvtPrinted("Y");
		evtDetails.setGivenNameOne("KATE");
		evtDetails.setGivenNameTwo("RSITEST");
		evtDetails.setGivenNameThree("MILLICENT");
		evtDetails.setOffenceDescription("FAIL TO STOP FOR RED LIGHT AT INTERSECTION");
		evtDetails.setOffenderType("DRIVER");
		evtDetails.setOrganizationUnit("");

		evtDetails.setRegisteredUserName("JOE BLOGGS");
		evtDetails.setReportDate("2018-01-18");
		evtDetails.setReportTime("2315");
		evtDetails.setSex("F");
		evtDetails.setSurname("B&#39;YUN");
		evtDetails.setStatuteAct("MV");
		evtDetails.setStatuteSection("129(1)");

		evtDetails.setTicketClass("P");
		evtDetails.setTicketNumber("EZ99999999");
		evtDetails.setTicketStatus("I");
		evtDetails.setTicketCOSFormNumber("eCOS(012018)");
		evtDetails.setTicketEVTFormNumber("MV6000E");

		evtDetails.setYoungPersonFlag("Y");
		
		evtDetails.setPerson(getPerson());
		evtDetails.setDriversLicense(getDriversLicense());
		evtDetails.setViolation(getViolation());
		evtDetails.setVehicle(getVehicle());
		
		evtDetails.setCounts(GetCounts());
		
		return evtDetails;
	}

	private static Person getPerson() {
		Person person = new Person();
		person.setAddress("910 GOVERNMENT ST, VICTORIA BC  V8W 3Y8");
		person.setResidenceCity("VICTORIA");
		person.setResidenceProvinceCode("BC");
		person.setResidenceProvinceName("");
		person.setResidencePostalCode("V8W3Y8");
		return person;
	}

	private static Vehicle getVehicle() {
		Vehicle vehicle = new Vehicle();

		vehicle.setAccidentFlag("N");
		vehicle.setColour("PURPLE");
		vehicle.setLicenseProvinceCode("BC");
		vehicle.setLicenseProvinceName("");
		vehicle.setPlateNo("MIL123");
		vehicle.setMakeCode("HOND");
		vehicle.setMakeDescription("HONDA");
		vehicle.setTypeCode("SD");
		vehicle.setTypeDescription("");
		vehicle.setYear("2015");

		return vehicle;
	}

	private static Violation getViolation() {
		Violation violation = new Violation();

		violation.setDate("2018-01-18");
		violation.setTime("2315");
		violation.setLocation("HIGHWAY");
		violation.setCityCode("SRY");
		violation.setCityName("SURREY");
		
		return violation;
	}
	
	private static DriversLicense getDriversLicense() {
		DriversLicense driverLicense = new DriversLicense();
		driverLicense.setExpiryDate("2018");
		driverLicense.setNumber("1700170");
		driverLicense.setProducedFlag("Y");
		driverLicense.setProvinceCode("BC");
		return driverLicense;
	}
	
	private static List<Counts> GetCounts() {
		List<Counts> counts = new ArrayList<>();
		counts.add( new Counts("1", "MV", "129(1)", "FAIL TO STOP FOR RED LIGHT AT INTERSECTION", "167.00") );
		counts.add( new Counts("2", "MV", "214.2(1)", "USE AN ELECTRONIC DEVICE WHILE DRIVING", "368.00") );
		return counts;
	}
}
