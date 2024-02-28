/*
 * RSBC Hub Event API
 * # Summary Event API for the Road Safety BC Hub.  # Version naming convention  X.y - where x = major (breaking) changes, and y = minor (non-breaking) changes.  # Change log  | version | date       | description | | ------- | ---------- | -----------------------------| | 1.1     | 2020-01-30 | JHI-815 Initial event publish API| | 1.2     | 2020-02-19 | JHi-940 Add offender_type_name to EVT_Issuance data model| |         |            | JHi-940 Add non-breaking changes to support event lookup| | 1.3     | 2020-04-21 | JHI-1145 updated API to include additional data elements identified in BI-852 | | 1.4     | 2020-06-19 | JHI-1246 updated API to include payment transaction id | | 1.5     | 2020-08-05 | JHI-1474 Wording Number Field Required for Violation Records | | 1.6     | 2021-06-30 | JHI-1329 Add the ability to record additional ETK processing events | 
 *
 * OpenAPI spec version: 1.6
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package bcgov.jh.etk.jhetkcommon.model.eventing;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Electronic violation ticket issuance
 */
@ApiModel(description = "Electronic violation ticket issuance")
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaClientCodegen", date = "2021-07-02T15:57:15.869-07:00")
public class EVTIssuance {
  @JsonProperty("ticket_number")
  private String ticketNumber = null;

  @JsonProperty("violation_date")
  private String violationDate = null;

  @JsonProperty("violation_time")
  private String violationTime = null;

  @JsonProperty("vehicle_make_name")
  private String vehicleMakeName = null;

  @JsonProperty("vehicle_type_code")
  private String vehicleTypeCode = null;

  @JsonProperty("violation_city_name")
  private String violationCityName = null;

  @JsonProperty("violation_city_code")
  private String violationCityCode = null;

  @JsonProperty("violation_highway_desc")
  private String violationHighwayDesc = null;

  @JsonProperty("enforcement_jurisdiction_name")
  private String enforcementJurisdictionName = null;

  @JsonProperty("enforcement_jurisdiction_code")
  private String enforcementJurisdictionCode = null;

  @JsonProperty("enforcement_officer_number")
  private String enforcementOfficerNumber = null;

  @JsonProperty("enforcement_officer_name")
  private String enforcementOfficerName = null;
  
  @JsonProperty("enforcement_org_unit_cd")
  private String enforcementOrgUnitCode = null;
  
  @JsonProperty("enforcement_org_unit_cd_txt")
  private String enforcementOrgUnitTranslation = null;

  @JsonProperty("offender_type_name")
  private String offenderTypeName = null;

  @JsonProperty("count_quantity")
  private Integer countQuantity = null;

  @JsonProperty("submit_date")
  private String submitDate = null;

  @JsonProperty("sent_time")
  private String sentTime = null;

  @JsonProperty("drivers_licence_province_code")
  private String driversLicenceProvinceCode = null;

  @JsonProperty("person_gender_code")
  private String personGenderCode = null;

  @JsonProperty("person_residence_city_name")
  private String personResidenceCityName = null;

  @JsonProperty("person_residence_province_code")
  private String personResidenceProvinceCode = null;

  @JsonProperty("young_person_yn")
  private String youngPersonYn = null;

  @JsonProperty("vehicle_province_code")
  private String vehicleProvinceCode = null;

  @JsonProperty("vehicle_nsc_puj_code")
  private String vehicleNscPujCode = null;

  @JsonProperty("vehicle_year")
  private String vehicleYear = null;

  @JsonProperty("accident_yn")
  private String accidentYn = null;

  @JsonProperty("dispute_address_text")
  private String disputeAddressText = null;

  @JsonProperty("court_location_code")
  private String courtLocationCode = null;

  @JsonProperty("mre_agency_text")
  private String mreAgencyText = null;

  @JsonProperty("certificate_of_service_date")
  private String certificateOfServiceDate = null;

  @JsonProperty("certificate_of_service_number")
  private String certificateOfServiceNumber = null;

  @JsonProperty("e_violation_form_number")
  private String eViolationFormNumber = null;

  @JsonProperty("mre_minor_version_text")
  private String mreMinorVersionText = null;

  @JsonProperty("counts")
  private List<EVTIssuanceCount> counts = null;

  public EVTIssuance ticketNumber(String ticketNumber) {
    this.ticketNumber = ticketNumber;
    return this;
  }

   /**
   * Get ticketNumber
   * @return ticketNumber
  **/
  @ApiModelProperty(example = "EZ12345678", value = "")
  public String getTicketNumber() {
    return ticketNumber;
  }

  public void setTicketNumber(String ticketNumber) {
    this.ticketNumber = ticketNumber;
  }

  public EVTIssuance violationDate(String violationDate) {
    this.violationDate = violationDate;
    return this;
  }

   /**
   * Get violationDate
   * @return violationDate
  **/
  @ApiModelProperty(example = "2019-09-01", value = "")
  public String getViolationDate() {
    return violationDate;
  }

  public void setViolationDate(String violationDate) {
    this.violationDate = violationDate;
  }

  public EVTIssuance violationTime(String violationTime) {
    this.violationTime = violationTime;
    return this;
  }

   /**
   * Get violationTime
   * @return violationTime
  **/
  @ApiModelProperty(example = "12:01", value = "")
  public String getViolationTime() {
    return violationTime;
  }

  public void setViolationTime(String violationTime) {
    this.violationTime = violationTime;
  }

  public EVTIssuance vehicleMakeName(String vehicleMakeName) {
    this.vehicleMakeName = vehicleMakeName;
    return this;
  }

   /**
   * Get vehicleMakeName
   * @return vehicleMakeName
  **/
  @ApiModelProperty(example = "FORD", value = "")
  public String getVehicleMakeName() {
    return vehicleMakeName;
  }

  public void setVehicleMakeName(String vehicleMakeName) {
    this.vehicleMakeName = vehicleMakeName;
  }

  public EVTIssuance vehicleTypeCode(String vehicleTypeCode) {
    this.vehicleTypeCode = vehicleTypeCode;
    return this;
  }

   /**
   * Get vehicleTypeCode
   * @return vehicleTypeCode
  **/
  @ApiModelProperty(example = "4DR", value = "")
  public String getVehicleTypeCode() {
    return vehicleTypeCode;
  }

  public void setVehicleTypeCode(String vehicleTypeCode) {
    this.vehicleTypeCode = vehicleTypeCode;
  }

  public EVTIssuance violationCityName(String violationCityName) {
    this.violationCityName = violationCityName;
    return this;
  }

   /**
   * Get violationCityName
   * @return violationCityName
  **/
  @ApiModelProperty(example = "ABBOTSFORD", value = "")
  public String getViolationCityName() {
    return violationCityName;
  }

  public void setViolationCityName(String violationCityName) {
    this.violationCityName = violationCityName;
  }

  public EVTIssuance violationCityCode(String violationCityCode) {
    this.violationCityCode = violationCityCode;
    return this;
  }

   /**
   * Get violationCityCode
   * @return violationCityCode
  **/
  @ApiModelProperty(example = "ABB", value = "")
  public String getViolationCityCode() {
    return violationCityCode;
  }

  public void setViolationCityCode(String violationCityCode) {
    this.violationCityCode = violationCityCode;
  }

  public EVTIssuance violationHighwayDesc(String violationHighwayDesc) {
    this.violationHighwayDesc = violationHighwayDesc;
    return this;
  }

   /**
   * Get violationHighwayDesc
   * @return violationHighwayDesc
  **/
  @ApiModelProperty(example = "HWY # 1 / CLEARBROOK ON RAMP", value = "")
  public String getViolationHighwayDesc() {
    return violationHighwayDesc;
  }

  public void setViolationHighwayDesc(String violationHighwayDesc) {
    this.violationHighwayDesc = violationHighwayDesc;
  }

  public EVTIssuance enforcementJurisdictionName(String enforcementJurisdictionName) {
    this.enforcementJurisdictionName = enforcementJurisdictionName;
    return this;
  }

   /**
   * Get enforcementJurisdictionName
   * @return enforcementJurisdictionName
  **/
  @ApiModelProperty(example = "UPPER FRASER VALLEY REG RCMP", value = "")
  public String getEnforcementJurisdictionName() {
    return enforcementJurisdictionName;
  }

  public void setEnforcementJurisdictionName(String enforcementJurisdictionName) {
    this.enforcementJurisdictionName = enforcementJurisdictionName;
  }

  public EVTIssuance enforcementJurisdictionCode(String enforcementJurisdictionCode) {
    this.enforcementJurisdictionCode = enforcementJurisdictionCode;
    return this;
  }

   /**
   * Get enforcementJurisdictionCode
   * @return enforcementJurisdictionCode
  **/
  @ApiModelProperty(example = "1501", value = "")
  public String getEnforcementJurisdictionCode() {
    return enforcementJurisdictionCode;
  }

  public void setEnforcementJurisdictionCode(String enforcementJurisdictionCode) {
    this.enforcementJurisdictionCode = enforcementJurisdictionCode;
  }

  public EVTIssuance enforcementOfficerNumber(String enforcementOfficerNumber) {
    this.enforcementOfficerNumber = enforcementOfficerNumber;
    return this;
  }

   /**
   * Get enforcementOfficerNumber
   * @return enforcementOfficerNumber
  **/
  @ApiModelProperty(example = "1111", value = "")
  public String getEnforcementOfficerNumber() {
    return enforcementOfficerNumber;
  }

  public void setEnforcementOfficerNumber(String enforcementOfficerNumber) {
    this.enforcementOfficerNumber = enforcementOfficerNumber;
  }

  public EVTIssuance enforcementOfficerName(String enforcementOfficerName) {
    this.enforcementOfficerName = enforcementOfficerName;
    return this;
  }

   /**
   * Get enforcementOfficerName
   * @return enforcementOfficerName
  **/
  @ApiModelProperty(example = "SMITH, JANE", value = "")
  public String getEnforcementOfficerName() {
    return enforcementOfficerName;
  }

  public void setEnforcementOfficerName(String enforcementOfficerName) {
    this.enforcementOfficerName = enforcementOfficerName;
  }

  public EVTIssuance offenderTypeName(String offenderTypeName) {
    this.offenderTypeName = offenderTypeName;
    return this;
  }
  
  public String getEnforcementOrgUnitCode() {
      return enforcementOrgUnitCode;
  }

  public void setEnforcementOrgUnitCode(String enforcementOrgUnitCode) {
      this.enforcementOrgUnitCode = enforcementOrgUnitCode;
  }

  public String getEnforcementOrgUnitTranslation() {
      return enforcementOrgUnitTranslation;
  }

  public void setEnforcementOrgUnitTranslation(String enforcementOrgUnitTranslation) {
      this.enforcementOrgUnitTranslation = enforcementOrgUnitTranslation;
  }

/**
   * Get offenderTypeName
   * @return offenderTypeName
  **/
  @ApiModelProperty(example = "DRIVER", value = "")
  public String getOffenderTypeName() {
    return offenderTypeName;
  }

  public void setOffenderTypeName(String offenderTypeName) {
    this.offenderTypeName = offenderTypeName;
  }

  public EVTIssuance countQuantity(Integer countQuantity) {
    this.countQuantity = countQuantity;
    return this;
  }

   /**
   * Get countQuantity
   * minimum: 1
   * @return countQuantity
  **/
  @ApiModelProperty(example = "1", value = "")
  public Integer getCountQuantity() {
    return countQuantity;
  }

  public void setCountQuantity(Integer countQuantity) {
    this.countQuantity = countQuantity;
  }

  public EVTIssuance submitDate(String submitDate) {
    this.submitDate = submitDate;
    return this;
  }

   /**
   * Get submitDate
   * @return submitDate
  **/
  @ApiModelProperty(example = "2019-09-01", value = "")
  public String getSubmitDate() {
    return submitDate;
  }

  public void setSubmitDate(String submitDate) {
    this.submitDate = submitDate;
  }

  public EVTIssuance sentTime(String sentTime) {
    this.sentTime = sentTime;
    return this;
  }

   /**
   * Get sentTime
   * @return sentTime
  **/
  @ApiModelProperty(example = "1220", value = "")
  public String getSentTime() {
    return sentTime;
  }

  public void setSentTime(String sentTime) {
    this.sentTime = sentTime;
  }

  public EVTIssuance driversLicenceProvinceCode(String driversLicenceProvinceCode) {
    this.driversLicenceProvinceCode = driversLicenceProvinceCode;
    return this;
  }

   /**
   * Get driversLicenceProvinceCode
   * @return driversLicenceProvinceCode
  **/
  @ApiModelProperty(example = "BC", value = "")
  public String getDriversLicenceProvinceCode() {
    return driversLicenceProvinceCode;
  }

  public void setDriversLicenceProvinceCode(String driversLicenceProvinceCode) {
    this.driversLicenceProvinceCode = driversLicenceProvinceCode;
  }

  public EVTIssuance personGenderCode(String personGenderCode) {
    this.personGenderCode = personGenderCode;
    return this;
  }

   /**
   * Get personGenderCode
   * @return personGenderCode
  **/
  @ApiModelProperty(example = "X", value = "")
  public String getPersonGenderCode() {
    return personGenderCode;
  }

  public void setPersonGenderCode(String personGenderCode) {
    this.personGenderCode = personGenderCode;
  }

  public EVTIssuance personResidenceCityName(String personResidenceCityName) {
    this.personResidenceCityName = personResidenceCityName;
    return this;
  }

   /**
   * Get personResidenceCityName
   * @return personResidenceCityName
  **/
  @ApiModelProperty(example = "VICTORIA", value = "")
  public String getPersonResidenceCityName() {
    return personResidenceCityName;
  }

  public void setPersonResidenceCityName(String personResidenceCityName) {
    this.personResidenceCityName = personResidenceCityName;
  }

  public EVTIssuance personResidenceProvinceCode(String personResidenceProvinceCode) {
    this.personResidenceProvinceCode = personResidenceProvinceCode;
    return this;
  }

   /**
   * Get personResidenceProvinceCode
   * @return personResidenceProvinceCode
  **/
  @ApiModelProperty(example = "BC", value = "")
  public String getPersonResidenceProvinceCode() {
    return personResidenceProvinceCode;
  }

  public void setPersonResidenceProvinceCode(String personResidenceProvinceCode) {
    this.personResidenceProvinceCode = personResidenceProvinceCode;
  }

  public EVTIssuance youngPersonYn(String youngPersonYn) {
    this.youngPersonYn = youngPersonYn;
    return this;
  }

   /**
   * Get youngPersonYn
   * @return youngPersonYn
  **/
  @ApiModelProperty(example = "Y", value = "")
  public String getYoungPersonYn() {
    return youngPersonYn;
  }

  public void setYoungPersonYn(String youngPersonYn) {
    this.youngPersonYn = youngPersonYn;
  }

  public EVTIssuance vehicleProvinceCode(String vehicleProvinceCode) {
    this.vehicleProvinceCode = vehicleProvinceCode;
    return this;
  }

   /**
   * Get vehicleProvinceCode
   * @return vehicleProvinceCode
  **/
  @ApiModelProperty(example = "BC", value = "")
  public String getVehicleProvinceCode() {
    return vehicleProvinceCode;
  }

  public void setVehicleProvinceCode(String vehicleProvinceCode) {
    this.vehicleProvinceCode = vehicleProvinceCode;
  }

  public EVTIssuance vehicleNscPujCode(String vehicleNscPujCode) {
    this.vehicleNscPujCode = vehicleNscPujCode;
    return this;
  }

   /**
   * Get vehicleNscPujCode
   * @return vehicleNscPujCode
  **/
  @ApiModelProperty(example = "BC", value = "")
  public String getVehicleNscPujCode() {
    return vehicleNscPujCode;
  }

  public void setVehicleNscPujCode(String vehicleNscPujCode) {
    this.vehicleNscPujCode = vehicleNscPujCode;
  }

  public EVTIssuance vehicleYear(String vehicleYear) {
    this.vehicleYear = vehicleYear;
    return this;
  }

   /**
   * Get vehicleYear
   * @return vehicleYear
  **/
  @ApiModelProperty(example = "2015", value = "")
  public String getVehicleYear() {
    return vehicleYear;
  }

  public void setVehicleYear(String vehicleYear) {
    this.vehicleYear = vehicleYear;
  }

  public EVTIssuance accidentYn(String accidentYn) {
    this.accidentYn = accidentYn;
    return this;
  }

   /**
   * Get accidentYn
   * @return accidentYn
  **/
  @ApiModelProperty(example = "Y", value = "")
  public String getAccidentYn() {
    return accidentYn;
  }

  public void setAccidentYn(String accidentYn) {
    this.accidentYn = accidentYn;
  }

  public EVTIssuance disputeAddressText(String disputeAddressText) {
    this.disputeAddressText = disputeAddressText;
    return this;
  }

   /**
   * Get disputeAddressText
   * @return disputeAddressText
  **/
  @ApiModelProperty(example = "955 Wharf St, Victoria, B.C.", value = "")
  public String getDisputeAddressText() {
    return disputeAddressText;
  }

  public void setDisputeAddressText(String disputeAddressText) {
    this.disputeAddressText = disputeAddressText;
  }

  public EVTIssuance courtLocationCode(String courtLocationCode) {
    this.courtLocationCode = courtLocationCode;
    return this;
  }

   /**
   * Get courtLocationCode
   * @return courtLocationCode
  **/
  @ApiModelProperty(example = "VICTORIA, B.C.", value = "")
  public String getCourtLocationCode() {
    return courtLocationCode;
  }

  public void setCourtLocationCode(String courtLocationCode) {
    this.courtLocationCode = courtLocationCode;
  }

  public EVTIssuance mreAgencyText(String mreAgencyText) {
    this.mreAgencyText = mreAgencyText;
    return this;
  }

   /**
   * Get mreAgencyText
   * @return mreAgencyText
  **/
  @ApiModelProperty(example = "CENTRAL SAANICH POLICE SERVICE", value = "")
  public String getMreAgencyText() {
    return mreAgencyText;
  }

  public void setMreAgencyText(String mreAgencyText) {
    this.mreAgencyText = mreAgencyText;
  }

  public EVTIssuance certificateOfServiceDate(String certificateOfServiceDate) {
    this.certificateOfServiceDate = certificateOfServiceDate;
    return this;
  }

   /**
   * Get certificateOfServiceDate
   * @return certificateOfServiceDate
  **/
  @ApiModelProperty(example = "2019-07-10", value = "")
  public String getCertificateOfServiceDate() {
    return certificateOfServiceDate;
  }

  public void setCertificateOfServiceDate(String certificateOfServiceDate) {
    this.certificateOfServiceDate = certificateOfServiceDate;
  }

  public EVTIssuance certificateOfServiceNumber(String certificateOfServiceNumber) {
    this.certificateOfServiceNumber = certificateOfServiceNumber;
    return this;
  }

   /**
   * Get certificateOfServiceNumber
   * @return certificateOfServiceNumber
  **/
  @ApiModelProperty(example = "MV6010", value = "")
  public String getCertificateOfServiceNumber() {
    return certificateOfServiceNumber;
  }

  public void setCertificateOfServiceNumber(String certificateOfServiceNumber) {
    this.certificateOfServiceNumber = certificateOfServiceNumber;
  }

  public EVTIssuance eViolationFormNumber(String eViolationFormNumber) {
    this.eViolationFormNumber = eViolationFormNumber;
    return this;
  }

   /**
   * Get eViolationFormNumber
   * @return eViolationFormNumber
  **/
  @ApiModelProperty(example = "MV6000E (102018)", value = "")
  public String getEViolationFormNumber() {
    return eViolationFormNumber;
  }

  public void setEViolationFormNumber(String eViolationFormNumber) {
    this.eViolationFormNumber = eViolationFormNumber;
  }

  public EVTIssuance mreMinorVersionText(String mreMinorVersionText) {
    this.mreMinorVersionText = mreMinorVersionText;
    return this;
  }

   /**
   * Get mreMinorVersionText
   * @return mreMinorVersionText
  **/
  @ApiModelProperty(example = "7.3.388.1", value = "")
  public String getMreMinorVersionText() {
    return mreMinorVersionText;
  }

  public void setMreMinorVersionText(String mreMinorVersionText) {
    this.mreMinorVersionText = mreMinorVersionText;
  }

  public EVTIssuance counts(List<EVTIssuanceCount> counts) {
    this.counts = counts;
    return this;
  }

  public EVTIssuance addCountsItem(EVTIssuanceCount countsItem) {
    if (this.counts == null) {
      this.counts = new ArrayList<EVTIssuanceCount>();
    }
    this.counts.add(countsItem);
    return this;
  }

   /**
   * Get counts
   * @return counts
  **/
  @ApiModelProperty(value = "")
  public List<EVTIssuanceCount> getCounts() {
    return counts;
  }

  public void setCounts(List<EVTIssuanceCount> counts) {
    this.counts = counts;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EVTIssuance evTIssuance = (EVTIssuance) o;
    return Objects.equals(this.ticketNumber, evTIssuance.ticketNumber) &&
        Objects.equals(this.violationDate, evTIssuance.violationDate) &&
        Objects.equals(this.violationTime, evTIssuance.violationTime) &&
        Objects.equals(this.vehicleMakeName, evTIssuance.vehicleMakeName) &&
        Objects.equals(this.vehicleTypeCode, evTIssuance.vehicleTypeCode) &&
        Objects.equals(this.violationCityName, evTIssuance.violationCityName) &&
        Objects.equals(this.violationCityCode, evTIssuance.violationCityCode) &&
        Objects.equals(this.violationHighwayDesc, evTIssuance.violationHighwayDesc) &&
        Objects.equals(this.enforcementJurisdictionName, evTIssuance.enforcementJurisdictionName) &&
        Objects.equals(this.enforcementJurisdictionCode, evTIssuance.enforcementJurisdictionCode) &&
        Objects.equals(this.enforcementOfficerNumber, evTIssuance.enforcementOfficerNumber) &&
        Objects.equals(this.enforcementOfficerName, evTIssuance.enforcementOfficerName) &&
        Objects.equals(this.enforcementOrgUnitCode, evTIssuance.enforcementOrgUnitCode) &&
        Objects.equals(this.enforcementOrgUnitTranslation, evTIssuance.enforcementOrgUnitTranslation) &&
        Objects.equals(this.offenderTypeName, evTIssuance.offenderTypeName) &&
        Objects.equals(this.countQuantity, evTIssuance.countQuantity) &&
        Objects.equals(this.submitDate, evTIssuance.submitDate) &&
        Objects.equals(this.sentTime, evTIssuance.sentTime) &&
        Objects.equals(this.driversLicenceProvinceCode, evTIssuance.driversLicenceProvinceCode) &&
        Objects.equals(this.personGenderCode, evTIssuance.personGenderCode) &&
        Objects.equals(this.personResidenceCityName, evTIssuance.personResidenceCityName) &&
        Objects.equals(this.personResidenceProvinceCode, evTIssuance.personResidenceProvinceCode) &&
        Objects.equals(this.youngPersonYn, evTIssuance.youngPersonYn) &&
        Objects.equals(this.vehicleProvinceCode, evTIssuance.vehicleProvinceCode) &&
        Objects.equals(this.vehicleNscPujCode, evTIssuance.vehicleNscPujCode) &&
        Objects.equals(this.vehicleYear, evTIssuance.vehicleYear) &&
        Objects.equals(this.accidentYn, evTIssuance.accidentYn) &&
        Objects.equals(this.disputeAddressText, evTIssuance.disputeAddressText) &&
        Objects.equals(this.courtLocationCode, evTIssuance.courtLocationCode) &&
        Objects.equals(this.mreAgencyText, evTIssuance.mreAgencyText) &&
        Objects.equals(this.certificateOfServiceDate, evTIssuance.certificateOfServiceDate) &&
        Objects.equals(this.certificateOfServiceNumber, evTIssuance.certificateOfServiceNumber) &&
        Objects.equals(this.eViolationFormNumber, evTIssuance.eViolationFormNumber) &&
        Objects.equals(this.mreMinorVersionText, evTIssuance.mreMinorVersionText) &&
        Objects.equals(this.counts, evTIssuance.counts);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ticketNumber, violationDate, violationTime, vehicleMakeName, vehicleTypeCode, violationCityName, violationCityCode, violationHighwayDesc, enforcementJurisdictionName, enforcementJurisdictionCode, enforcementOfficerNumber, enforcementOfficerName, enforcementOrgUnitCode, enforcementOrgUnitTranslation, offenderTypeName, countQuantity, submitDate, sentTime, driversLicenceProvinceCode, personGenderCode, personResidenceCityName, personResidenceProvinceCode, youngPersonYn, vehicleProvinceCode, vehicleNscPujCode, vehicleYear, accidentYn, disputeAddressText, courtLocationCode, mreAgencyText, certificateOfServiceDate, certificateOfServiceNumber, eViolationFormNumber, mreMinorVersionText, counts);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class EVTIssuance {\n");
    
    sb.append("    ticketNumber: ").append(toIndentedString(ticketNumber)).append("\n");
    sb.append("    violationDate: ").append(toIndentedString(violationDate)).append("\n");
    sb.append("    violationTime: ").append(toIndentedString(violationTime)).append("\n");
    sb.append("    vehicleMakeName: ").append(toIndentedString(vehicleMakeName)).append("\n");
    sb.append("    vehicleTypeCode: ").append(toIndentedString(vehicleTypeCode)).append("\n");
    sb.append("    violationCityName: ").append(toIndentedString(violationCityName)).append("\n");
    sb.append("    violationCityCode: ").append(toIndentedString(violationCityCode)).append("\n");
    sb.append("    violationHighwayDesc: ").append(toIndentedString(violationHighwayDesc)).append("\n");
    sb.append("    enforcementJurisdictionName: ").append(toIndentedString(enforcementJurisdictionName)).append("\n");
    sb.append("    enforcementJurisdictionCode: ").append(toIndentedString(enforcementJurisdictionCode)).append("\n");
    sb.append("    enforcementOrgUnitCode: ").append(toIndentedString(enforcementOrgUnitCode)).append("\n");
    sb.append("    enforcementOrgUnitTranslation: ").append(toIndentedString(enforcementOrgUnitTranslation)).append("\n");
    sb.append("    enforcementOfficerNumber: ").append(toIndentedString(enforcementOfficerNumber)).append("\n");
    sb.append("    enforcementOfficerName: ").append(toIndentedString(enforcementOfficerName)).append("\n");
    sb.append("    offenderTypeName: ").append(toIndentedString(offenderTypeName)).append("\n");
    sb.append("    countQuantity: ").append(toIndentedString(countQuantity)).append("\n");
    sb.append("    submitDate: ").append(toIndentedString(submitDate)).append("\n");
    sb.append("    sentTime: ").append(toIndentedString(sentTime)).append("\n");
    sb.append("    driversLicenceProvinceCode: ").append(toIndentedString(driversLicenceProvinceCode)).append("\n");
    sb.append("    personGenderCode: ").append(toIndentedString(personGenderCode)).append("\n");
    sb.append("    personResidenceCityName: ").append(toIndentedString(personResidenceCityName)).append("\n");
    sb.append("    personResidenceProvinceCode: ").append(toIndentedString(personResidenceProvinceCode)).append("\n");
    sb.append("    youngPersonYn: ").append(toIndentedString(youngPersonYn)).append("\n");
    sb.append("    vehicleProvinceCode: ").append(toIndentedString(vehicleProvinceCode)).append("\n");
    sb.append("    vehicleNscPujCode: ").append(toIndentedString(vehicleNscPujCode)).append("\n");
    sb.append("    vehicleYear: ").append(toIndentedString(vehicleYear)).append("\n");
    sb.append("    accidentYn: ").append(toIndentedString(accidentYn)).append("\n");
    sb.append("    disputeAddressText: ").append(toIndentedString(disputeAddressText)).append("\n");
    sb.append("    courtLocationCode: ").append(toIndentedString(courtLocationCode)).append("\n");
    sb.append("    mreAgencyText: ").append(toIndentedString(mreAgencyText)).append("\n");
    sb.append("    certificateOfServiceDate: ").append(toIndentedString(certificateOfServiceDate)).append("\n");
    sb.append("    certificateOfServiceNumber: ").append(toIndentedString(certificateOfServiceNumber)).append("\n");
    sb.append("    eViolationFormNumber: ").append(toIndentedString(eViolationFormNumber)).append("\n");
    sb.append("    mreMinorVersionText: ").append(toIndentedString(mreMinorVersionText)).append("\n");
    sb.append("    counts: ").append(toIndentedString(counts)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

}

