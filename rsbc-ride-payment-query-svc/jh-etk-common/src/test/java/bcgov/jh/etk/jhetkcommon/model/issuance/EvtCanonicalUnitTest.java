package bcgov.jh.etk.jhetkcommon.model.issuance;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import java.io.StringReader;
import java.io.StringWriter;

public class EvtCanonicalUnitTest {

	private EvtCanonical evtCanonical;
	private final String EXPECTED_SOURCE_XML = "<?xml version='1.0' encoding='ISO-8859-1'?>\r\n" + 
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
			"";
	private final String EXPECTED_CANONICAL = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
			"<EVTCanonical>\r\n" + 
			"    <EVTDetails>\r\n" +
			"        <amountOfPenalty>167.00</amountOfPenalty>\r\n" + 
			"        <changeOfAddressFlag>N</changeOfAddressFlag>\r\n" + 
			"        <carrierJurisdiction>BC</carrierJurisdiction>\r\n" + 
			"        <carrierRegNumber>017001709</carrierRegNumber>\r\n" + 
			"        <certificateOfServiceCompleted>Y</certificateOfServiceCompleted>\r\n" + 
			"        <certificateOfServiceServedTo>KATE RSITEST  B&amp;#39;YUN</certificateOfServiceServedTo>\r\n" + 
			"        <certificateOfServiceDateOfService>2018-01-18</certificateOfServiceDateOfService>\r\n" + 
			"        <certificateOfServiceEntityPerson>Y</certificateOfServiceEntityPerson>\r\n" + 
			"        <courtLocationCode>VANCOUVER, B.C.</courtLocationCode>\r\n" + 
			"        <dateOfBirth>1960-01-01</dateOfBirth>\r\n" + 
			"        <disputeAddress>1103-115 Fulford-Ganges Rd, Ganges (Saltspring Island), B.C.</disputeAddress>\r\n" + 
			"        <driversLicenseExpiryDate>2018</driversLicenseExpiryDate>\r\n" + 
			"        <driversLicenseNumber>1700170</driversLicenseNumber>\r\n" + 
			"        <driversLicenseProducedFlag>Y</driversLicenseProducedFlag>\r\n" + 
			"        <driversLicenseProvinceCode>BC</driversLicenseProvinceCode>\r\n" + 
			"        <enforcementOfficerName>A. JOHNSON MCNALLY</enforcementOfficerName>\r\n" + 
			"        <enforcementOfficerNumber>MW1234</enforcementOfficerNumber>\r\n" + 
			"        <enforcementOfficerPartnerName>GUPTA, VIVEK</enforcementOfficerPartnerName>\r\n" + 
			"        <enforcementOfficerPartnerNumber>ZZ0025</enforcementOfficerPartnerNumber>\r\n" + 
			"        <enforcementOrganizationJurisdiction>DEAS ISLAND TRAFFIC RCMP</enforcementOrganizationJurisdiction>\r\n" + 
			"        <enforcementOrganizationJurisdictionCode>1004</enforcementOrganizationJurisdictionCode>\r\n" + 
			"        <evtPrinted>Y</evtPrinted>\r\n" + 
			"        <givenNameOne>KATE</givenNameOne>\r\n" + 
			"        <givenNameTwo>RSITEST</givenNameTwo>\r\n" + 
			"        <givenNameThree>MILLICENT</givenNameThree>\r\n" + 
			"        <offenceDescription>FAIL TO STOP FOR RED LIGHT AT INTERSECTION</offenceDescription>\r\n" + 
			"        <offenderType>DRIVER</offenderType>\r\n" + 
			"        <organizationUnit></organizationUnit>\r\n" + 
			"        <personAddress>910 GOVERNMENT ST, VICTORIA BC  V8W 3Y8</personAddress>\r\n" + 
			"        <personResidenceCity>VICTORIA</personResidenceCity>\r\n" + 
			"        <personResidenceProvinceCode>BC</personResidenceProvinceCode>\r\n" + 
			"        <personResidenceProvinceName></personResidenceProvinceName>\r\n" + 
			"        <personResidencePostalCode>V8W3Y8</personResidencePostalCode>\r\n" + 
			"        <registeredUserName>JOE BLOGGS</registeredUserName>\r\n" + 
			"        <reportDate>2018-01-18</reportDate>\r\n" + 
			"        <reportTime>2315</reportTime>\r\n" + 
			"        <sex>F</sex>\r\n" + 
			"        <surname>B&amp;#39;YUN</surname>\r\n" + 
			"        <statuteAct>MV</statuteAct>\r\n" + 
			"        <statuteSection>129(1)</statuteSection>\r\n" + 
			"        <ticketClass>P</ticketClass>\r\n" + 
			"        <ticketNumber>EZ99999999</ticketNumber>\r\n" + 
			"        <ticketStatus>I</ticketStatus>\r\n" + 
			"        <ticketCOSFormNumber>eCOS(012018)</ticketCOSFormNumber>\r\n" + 
			"        <ticketEVTFormNumber>MV6000E</ticketEVTFormNumber>\r\n" + 
			"        <violationDate>2018-01-18</violationDate>\r\n" + 
			"        <violationTime>2315</violationTime>\r\n" + 
			"        <violationLocation>HIGHWAY</violationLocation>\r\n" + 
			"        <violationCityCode>SRY</violationCityCode>\r\n" + 
			"        <violationCityName>SURREY</violationCityName>\r\n" + 
			"        <vehicleAccidentFlag>N</vehicleAccidentFlag>\r\n" + 
			"        <vehicleColour>PURPLE</vehicleColour>\r\n" + 
			"        <vehicleLicenseProvinceCode>BC</vehicleLicenseProvinceCode>\r\n" + 
			"        <vehicleLicenseProvinceName></vehicleLicenseProvinceName>\r\n" + 
			"        <vehiclePlateNo>MIL123</vehiclePlateNo>\r\n" + 
			"        <vehicleMakeCode>HOND</vehicleMakeCode>\r\n" + 
			"        <vehicleMakeDescription>HONDA</vehicleMakeDescription>\r\n" + 
			"        <vehicleTypeCode>SD</vehicleTypeCode>\r\n" + 
			"        <vehicleTypeDescription></vehicleTypeDescription>\r\n" + 
			"        <vehicleYear>2015</vehicleYear>\r\n" + 
			"        <youngPersonFlag>Y</youngPersonFlag>\r\n" + 
			"        <counts>\r\n" + 
			"            <countNumber>1</countNumber>\r\n" + 
			"            <act>MV</act>\r\n" + 
			"            <section>129(1)</section>\r\n" + 
			"            <offenseDescription>FAIL TO STOP FOR RED LIGHT AT INTERSECTION</offenseDescription>\r\n" + 
			"            <amount>167.00</amount>\r\n" + 
			"        </counts>\r\n" + 
			"        <counts>\r\n" + 
			"            <countNumber>2</countNumber>\r\n" + 
			"            <act>MV</act>\r\n" + 
			"            <section>214.2(1)</section>\r\n" + 
			"            <offenseDescription>USE AN ELECTRONIC DEVICE WHILE DRIVING</offenseDescription>\r\n" + 
			"            <amount>368.00</amount>\r\n" + 
			"        </counts>" +
			"    </EVTDetails>\r\n" + 
			"    <primeFileXml>PD94bWwgdmVyc2lvbj0nMS4wJyBlbmNvZGluZz0nSVNPLTg4NTktMSc/Pg0KPFhNTF9GSUxFPg0KICA8VFJBTlNGRVIvPg0KICA8TVJFX1ZFUlNJT04vPg0KICA8TUlOT1JfVkVSU0lPTi8+DQogIDxUQUJMRV9SRUxFQVNFX0RBVEUvPg0KICA8UFJPRklMRV9OQU1FLz4NCiAgPEFHRU5DWV9OQU1FPkRFQVMgSVNMQU5EIFRSQUZGSUMgUkNNUDwvQUdFTkNZX05BTUU+DQogIDxGSUxFX05BTUU+RVowNDAwMDExMi54bWw8L0ZJTEVfTkFNRT4NCiAgPFNVQk1JVF9CWS8+DQogIDxTVUJNSVRfQllfVFJBTlMvPg0KICA8T1JHX1VOSVQvPg0KICA8T1JHX1VOSVRfVFJBTlMvPg0KICA8Q0FTRV9UWVBFLz4NCiAgPFNVQk1JVF9EQVRFPjIwMTgtMDgtMzA8L1NVQk1JVF9EQVRFPg0KICA8U0VOVF9USU1FPjA5MDI8L1NFTlRfVElNRT4NCiAgPFJFUE9SVCBUWVBFPSJUSyI+DQogICAgPEpVUklTRElDVElPTj4xMDA0PC9KVVJJU0RJQ1RJT04+DQogICAgPEpVUklTRElDVElPTl9UUkFOUz5ERUFTIElTTEFORCBUUkFGRklDIFJDTVA8L0pVUklTRElDVElPTl9UUkFOUz4NCiAgICA8T0ZGRU5DRV9DT1VOVD4zPC9PRkZFTkNFX0NPVU5UPg0KICAgIDxUSUNLRVRfVFlQRT5FWjwvVElDS0VUX1RZUEU+DQogICAgPFRJQ0tFVF9UWVBFX1RSQU5TPkVWVCBTSVQgVEVTVElORzwvVElDS0VUX1RZUEVfVFJBTlM+DQogICAgPFRJQ0tFVF9OVU0+RVowNDAwMDExMjwvVElDS0VUX05VTT4NCiAgICA8VElDS0VUX0NMQVNTPlA8L1RJQ0tFVF9DTEFTUz4NCiAgICA8VElDS0VUX1NUQVRVUz5JPC9USUNLRVRfU1RBVFVTPg0KICAgIDxUSUNLRVRfT1JJR0lOPjE8L1RJQ0tFVF9PUklHSU4+DQogICAgPFRJQ0tFVF9PUklHSU5fVFJBTlM+TVY2MDEwKDA4MjAxOCk8L1RJQ0tFVF9PUklHSU5fVFJBTlM+DQogICAgPFRLX1JPTEU+RFJJVkVSPC9US19ST0xFPg0KICAgIDxPRkZFTkNFX0RBVEU+MjAxOC0wOC0zMDwvT0ZGRU5DRV9EQVRFPg0KICAgIDxPRkZFTkNFX1RJTUU+MDg1MjwvT0ZGRU5DRV9USU1FPg0KICAgIDxTVFJFRVRfTkFNRT5ISUdIV0FZPC9TVFJFRVRfTkFNRT4NCiAgICA8TVVOSUNJUEFMSVRZPlNSWTwvTVVOSUNJUEFMSVRZPg0KICAgIDxNVU5JQ0lQQUxJVFlfVFJBTlM+U1VSUkVZPC9NVU5JQ0lQQUxJVFlfVFJBTlM+DQogICAgPE9GRklDRVJfSUQ+TVcxMjM0PC9PRkZJQ0VSX0lEPg0KICAgIDxPRkZJQ0VSX0lEX1RSQU5TPkEuIEpPSE5TT04gTUNOQUxMWTwvT0ZGSUNFUl9JRF9UUkFOUz4NCiAgICA8QU1PVU5UX09GX1BFTkFMVFk+MTM4LjAwPC9BTU9VTlRfT0ZfUEVOQUxUWT4NCiAgICA8UEFZTUVOVF9NRVRIT0Q+VjwvUEFZTUVOVF9NRVRIT0Q+DQogICAgPFBBWU1FTlRfTUVUSE9EX1RSQU5TPk1WNjAwMEUoMDgyMDE4KTwvUEFZTUVOVF9NRVRIT0RfVFJBTlM+DQogICAgPFBSSU5URUQ+WTwvUFJJTlRFRD4NCiAgICA8U1RBVFVURV9BQ1Q+TVY8L1NUQVRVVEVfQUNUPg0KICAgIDxTVEFUVVRFX0FDVF9UUkFOUz5NT1RPUiBWRUhJQ0xFIEFDVDwvU1RBVFVURV9BQ1RfVFJBTlM+DQogICAgPExFR0FMX1NUQVRVVEVfU0VDVElPTj4xNDYoMSk8L0xFR0FMX1NUQVRVVEVfU0VDVElPTj4NCiAgICA8Q0hBUkdFX1RFWFQ+U1BFRUQgSU4gTVVOSUNJUEFMSVRZIChTUEVFRCBFWENFRUQgTEVTUyBUSEFOIDIxIEtNSCk8L0NIQVJHRV9URVhUPg0KICAgIDxQRVJTT04+DQogICAgICA8SU5ESVZJRFVBTF9TVVJOQU1FPk1BUklBTk88L0lORElWSURVQUxfU1VSTkFNRT4NCiAgICAgIDxHSVZFTl9PTkU+RkFCSU88L0dJVkVOX09ORT4NCiAgICAgIDxHSVZFTl9UV08+UlNJVEVTVDwvR0lWRU5fVFdPPg0KICAgICAgPE1VTklDSVBBTElUWV9OQU1FPlZJQ1RPUklBPC9NVU5JQ0lQQUxJVFlfTkFNRT4NCiAgICAgIDxQUk9WX1NUQVRFX0NPREU+QkM8L1BST1ZfU1RBVEVfQ09ERT4NCiAgICAgIDxQT1NUQUxfWklQQ09ERT5WOFczWTg8L1BPU1RBTF9aSVBDT0RFPg0KICAgICAgPEdFTkRFUl9DT0RFPk08L0dFTkRFUl9DT0RFPg0KICAgICAgPFNUUkVFVF9OQU1FPjkxMCBHT1ZFUk5NRU5UIFNUPC9TVFJFRVRfTkFNRT4NCiAgICAgIDxEQVRFX09GX0JJUlRIPjE5NjAtMDEtMDE8L0RBVEVfT0ZfQklSVEg+DQogICAgICA8RFJJVkVSU19MSUNFTkNFX05VTUJFUj4xNzAwMTY3PC9EUklWRVJTX0xJQ0VOQ0VfTlVNQkVSPg0KICAgICAgPERSSVZFUlNfTElDRU5DRV9QUk9WX1NUQVRFPkJDPC9EUklWRVJTX0xJQ0VOQ0VfUFJPVl9TVEFURT4NCiAgICAgIDxEUklWRVJTX0xJQ0VOQ0VfUFJPVl9TVEFURV9UUkFOUz5CUklUSVNIIENPTFVNQklBPC9EUklWRVJTX0xJQ0VOQ0VfUFJPVl9TVEFURV9UUkFOUz4NCiAgICAgIDxDSEFSR0U+DQogICAgICAgIDxJRD4yPC9JRD4NCiAgICAgICAgPENIQVJHRV9DT1VOVEVSPjE8L0NIQVJHRV9DT1VOVEVSPg0KCSAgICAgIDxTVEFUVVRFX05BTUU+TVY8L1NUQVRVVEVfTkFNRT4NCiAgICAgICAgPExFR0FMX1NUQVRVVEVfU0VDVElPTj4xMjkoMSk8L0xFR0FMX1NUQVRVVEVfU0VDVElPTj4NCgkgICAgICA8Q0hBUkdFX1RFWFQ+RkFJTCBUTyBTVE9QIEZPUiBSRUQgTElHSFQgQVQgSU5URVJTRUNUSU9OPC9DSEFSR0VfVEVYVD4NCiAgICAgICAgPENIQVJHRV9DT1VOVD4xPC9DSEFSR0VfQ09VTlQ+DQogICAgICAgIDxTRVRfRklORV9BTU9VTlQ+MTY3LjAwPC9TRVRfRklORV9BTU9VTlQ+DQogICAgICAgIDxBTU9VTlRfT0ZfUEVOQUxUWT4xNjcuMDA8L0FNT1VOVF9PRl9QRU5BTFRZPg0KICAgICAgPC9DSEFSR0U+DQogICAgICA8Q0hBUkdFPg0KICAgICAgICA8SUQ+MzwvSUQ+DQogICAgICAgIDxDSEFSR0VfQ09VTlRFUj4yPC9DSEFSR0VfQ09VTlRFUj4NCgkgICAgICA8U1RBVFVURV9OQU1FPk1WPC9TVEFUVVRFX05BTUU+DQogICAgICAgIDxMRUdBTF9TVEFUVVRFX1NFQ1RJT04+MjE0LjIoMSk8L0xFR0FMX1NUQVRVVEVfU0VDVElPTj4NCgkgICAgICA8Q0hBUkdFX1RFWFQ+VVNFIEFOIEVMRUNUUk9OSUMgREVWSUNFIFdISUxFIERSSVZJTkc8L0NIQVJHRV9URVhUPg0KICAgICAgICA8Q0hBUkdFX0NPVU5UPjE8L0NIQVJHRV9DT1VOVD4NCiAgICAgICAgPFNFVF9GSU5FX0FNT1VOVD4zNjguMDA8L1NFVF9GSU5FX0FNT1VOVD4NCiAgICAgICAgPEFNT1VOVF9PRl9QRU5BTFRZPjM2OC4wMDwvQU1PVU5UX09GX1BFTkFMVFk+DQogICAgICA8L0NIQVJHRT4NCiAgICA8L1BFUlNPTj4NCiAgICA8VkVISUNMRT4NCiAgICAgIDxWRUhJQ0xFX0xJQ0VOQ0VfTlVNQkVSPk1JTDEyMzwvVkVISUNMRV9MSUNFTkNFX05VTUJFUj4NCiAgICAgIDxMSUNFTkNFX1BST1ZJTkNFPkJDPC9MSUNFTkNFX1BST1ZJTkNFPg0KICAgICAgPE1PREVMX1lFQVI+MjAxNTwvTU9ERUxfWUVBUj4NCiAgICAgIDxWRUhJQ0xFX01BS0U+SE9ORDwvVkVISUNMRV9NQUtFPg0KICAgICAgPFZFSElDTEVfTUFLRV9UUkFOUz5IT05EQTwvVkVISUNMRV9NQUtFX1RSQU5TPg0KICAgICAgPFNUWUxFPlNEPC9TVFlMRT4NCiAgICAgIDxPUklHSU5BTF9DT0xPVVI+UFVSUExFPC9PUklHSU5BTF9DT0xPVVI+DQogICAgPC9WRUhJQ0xFPg0KICAgIDxDVVNUT01EQVRBPg0KICAgICAgPFRLX1lPVU5HX1BFUlNPTj5OPC9US19ZT1VOR19QRVJTT04+DQogICAgICA8VEtfRExfUFJPRFVDRUQ+WTwvVEtfRExfUFJPRFVDRUQ+DQogICAgICA8VEtfRExfRVhQSVJZPjIwMTk8L1RLX0RMX0VYUElSWT4NCiAgICAgIDxUS19DSEFOR0VfQUREUkVTUz5OPC9US19DSEFOR0VfQUREUkVTUz4NCiAgICAgIDxUS19SRUdfT1dORVJfTkFNRT5GQUJJTyBSU0lURVNUIE1BUklBTk88L1RLX1JFR19PV05FUl9OQU1FPg0KICAgICAgPFRLX0FDQ0lERU5UPk48L1RLX0FDQ0lERU5UPg0KICAgICAgPFRLX0RJU1BVVEVfTE9DQVRJT05fQUREUkVTU19UUkFOUz4xMTAzLTExNSBGdWxmb3JkLUdhbmdlcyBSZCwgU2FsdHNwcmluZyBJc2xhbmQsIEIuQy48L1RLX0RJU1BVVEVfTE9DQVRJT05fQUREUkVTU19UUkFOUz4NCiAgICAgIDxUS19DT1VSVF9IRUFSSU5HX0xPQ0FUSU9OX1RSQU5TPlZBTkNPVVZFUiwgQi5DLjwvVEtfQ09VUlRfSEVBUklOR19MT0NBVElPTl9UUkFOUz4NCiAgICAgIDxUS19XSVRORVNTSU5HX09GRklDRVI+WlowMDI1PC9US19XSVRORVNTSU5HX09GRklDRVI+DQogICAgICA8VEtfV0lUTkVTU0lOR19PRkZJQ0VSX1RSQU5TPkdVUFRBLCBWSVZFSzwvVEtfV0lUTkVTU0lOR19PRkZJQ0VSX1RSQU5TPg0KICAgICAgPFRLX0NFUlRJRklDQVRFX0VOVElUWV9QRVJTT04+WTwvVEtfQ0VSVElGSUNBVEVfRU5USVRZX1BFUlNPTj4NCiAgICAgIDxUS19DRVJUSUZJQ0FURT5ZPC9US19DRVJUSUZJQ0FURT4NCiAgICAgIDxUS19DRVJUSUZJQ0FURV9TRVJWRURfVE8+RkFCSU8gUlNJVEVTVCBNQVJJQU5PPC9US19DRVJUSUZJQ0FURV9TRVJWRURfVE8+DQogICAgICA8VEtfQ0VSVElGSUNBVEVfREFURV9PRl9TRVJWSUNFPjIwMTgtMDgtMzA8L1RLX0NFUlRJRklDQVRFX0RBVEVfT0ZfU0VSVklDRT4NCiAgICA8L0NVU1RPTURBVEE+DQogIDwvUkVQT1JUPg0KPC9YTUxfRklMRT4NCg==</primeFileXml>\r\n" + 
			"    <filename>dir/test</filename>\r\n" + 
			"</EVTCanonical>\r\n";

	@Before
	public void createEvtCanonical() {
		evtCanonical = EvtCanonicalFactory.createEvtCanonical();
	}

	@Test
	public void test_marshal() throws JAXBException {
		String xmlCanonical = toXML(evtCanonical);
		Assert.assertEquals(removeLineBreaksSpacesAndTabs(EXPECTED_CANONICAL), xmlCanonical);
	}
	
	@Test
	public void test_unmarshal() throws JAXBException {
		
		EvtCanonical evtCanonicalObj = toObject(EXPECTED_CANONICAL);
		
		Assert.assertNotNull(evtCanonicalObj);
		Assert.assertNotNull(evtCanonicalObj.getEvtDetails());
		Assert.assertNotNull(evtCanonicalObj.getFilename());
		Assert.assertNotNull(evtCanonicalObj.getPrimeFileXml());
		
		Assert.assertEquals("dir/test", evtCanonicalObj.getFilename());
		Assert.assertEquals(EXPECTED_SOURCE_XML, evtCanonicalObj.getPrimeFileXml());

		//counts
		Assert.assertNotNull(evtCanonicalObj.getEvtDetails().getCounts());
		Assert.assertEquals(2, evtCanonicalObj.getEvtDetails().getCounts().size());
		Assert.assertEquals("MV", evtCanonicalObj.getEvtDetails().getCounts().get(0).getAct());
		Assert.assertEquals("1", evtCanonicalObj.getEvtDetails().getCounts().get(0).getCountNumber());
		Assert.assertEquals("129(1)", evtCanonicalObj.getEvtDetails().getCounts().get(0).getSection());
		Assert.assertEquals("FAIL TO STOP FOR RED LIGHT AT INTERSECTION", evtCanonicalObj.getEvtDetails().getCounts().get(0).getOffenseDescription());
		Assert.assertEquals("167.00", evtCanonicalObj.getEvtDetails().getCounts().get(0).getAmount());
		
		Assert.assertEquals("MV", evtCanonicalObj.getEvtDetails().getCounts().get(1).getAct());
		Assert.assertEquals("2", evtCanonicalObj.getEvtDetails().getCounts().get(1).getCountNumber());
		Assert.assertEquals("214.2(1)", evtCanonicalObj.getEvtDetails().getCounts().get(1).getSection());
		Assert.assertEquals("USE AN ELECTRONIC DEVICE WHILE DRIVING", evtCanonicalObj.getEvtDetails().getCounts().get(1).getOffenseDescription());
		Assert.assertEquals("368.00", evtCanonicalObj.getEvtDetails().getCounts().get(1).getAmount());
	}
	
	private String removeLineBreaksSpacesAndTabs(String text) {
		return text.replaceAll("\r|\n|\t", "").trim();
	}
	
	private String toXML(EvtCanonical evtCanonical) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(EvtCanonical.class);
		StringWriter outWriter = new StringWriter();
		Marshaller m = context.createMarshaller();
		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StreamResult result = new StreamResult(outWriter);
		
		m.marshal(evtCanonical, result);
		
		return removeLineBreaksSpacesAndTabs(outWriter.getBuffer().toString());
	}
	
	private EvtCanonical toObject(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(EvtCanonical.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		StringReader reader = new StringReader(xml);
		return (EvtCanonical) unmarshaller.unmarshal(reader);
	}
	
}
