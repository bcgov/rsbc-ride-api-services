package bcgov.jh.etk.jhetkcommon.model.eventing.kafka;

import java.util.List;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class EVTIssuanceEvent.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"violations",
	"submit_dt",
	"sent_tm",
	"ticket_no",
	"drivers_licence_province_cd",
	"person_gender_cd",
	"person_residence_city_nm",
	"person_residence_province_cd",
	"young_person_yn",
	"offender_type_cd",
	"violation_dt",
	"violation_tm",
	"violation_highway_nm",
	"violation_city_cd",
	"violation_city_nm",
	"vehicle_province_cd",
	"vehicle_nsc_puj_cd",
	"vehicle_make_cd",
	"vehicle_type_cd",
	"vehicle_yy",
	"accident_yn",
	"dispute_address_txt",
	"court_location_cd",
	"mre_agency_txt",
	"enforcement_jurisdiction_cd",
	"enforcement_jurisdiction_txt",
	"certificate_of_service_dt",
	"certificate_of_service_no",
	"e_violation_form_no",
	"count_qty",
	"mre_minor_version_txt",
	"enforcement_officer_no",
	"enforcement_officer_nm",
	"file_nm",
	"profile_nm",
	"enforcement_org_unit_cd",
	"enforcement_org_unit_cd_txt",
	"vehicle_style_txt",
	"vehicle_original_colour_cd",
	"vehicle_original_colour_txt",
	"reg_owner_nm",
	"dl_produced_yn",
	"change_address_yn"
})

@Generated("jsonschema2pojo")
@Setter
@Getter
public class EVTIssuanceEvent {

	/** The violations. */
	@JsonProperty("violations")
	public List<Violation> violations = null;
	
	/** The submit dt. */
	@JsonProperty("submit_dt")
	public String submitDt = "null";
	
	/** The sent tm. */
	@JsonProperty("sent_tm")
	public String sentTm = "null";
	
	/** The ticket no. */
	@JsonProperty("ticket_no")
	public String ticketNo = "null";
	
	/** The drivers licence province cd. */
	@JsonProperty("drivers_licence_province_cd")
	public String driversLicenceProvinceCd = "null";
	
	/** The person gender cd. */
	@JsonProperty("person_gender_cd")
	public String personGenderCd = "null";
	
	/** The person residence city nm. */
	@JsonProperty("person_residence_city_nm")
	public String personResidenceCityNm = "null";
	
	/** The person residence province cd. */
	@JsonProperty("person_residence_province_cd")
	public String personResidenceProvinceCd = "null";
	
	/** The young person yn. */
	@JsonProperty("young_person_yn")
	public String youngPersonYn = "null";
	
	/** The offender type cd. */
	@JsonProperty("offender_type_cd")
	public String offenderTypeCd = "null";
	
	/** The violation dt. */
	@JsonProperty("violation_dt")
	public String violationDt = "null";
	
	/** The violation tm. */
	@JsonProperty("violation_tm")
	public String violationTm = "null";
	
	/** The violation highway nm. */
	@JsonProperty("violation_highway_nm")
	public String violationHighwayNm = "null";
	
	/** The violation city cd. */
	@JsonProperty("violation_city_cd")
	public String violationCityCd = "null";
	
	/** The violation city nm. */
	@JsonProperty("violation_city_nm")
	public String violationCityNm = "null";
	
	/** The vehicle province cd. */
	@JsonProperty("vehicle_province_cd")
	public String vehicleProvinceCd = "null";
	
	/** The vehicle nsc puj cd. */
	@JsonProperty("vehicle_nsc_puj_cd")
	public String vehicleNscPujCd = "null";
	
	/** The vehicle make cd. */
	@JsonProperty("vehicle_make_cd")
	public String vehicleMakeCd = "null";
	
	/** The vehicle type cd. */
	@JsonProperty("vehicle_type_cd")
	public String vehicleTypeCd = "null";
	
	/** The vehicle yy. */
	@JsonProperty("vehicle_yy")
	public String vehicleYy = "null";
	
	/** The accident yn. */
	@JsonProperty("accident_yn")
	public String accidentYn = "null";
	
	/** The dispute address txt. */
	@JsonProperty("dispute_address_txt")
	public String disputeAddressTxt = "null";
	
	/** The court location cd. */
	@JsonProperty("court_location_cd")
	public String courtLocationCd = "null";
	
	/** The mre agency txt. */
	@JsonProperty("mre_agency_txt")
	public String mreAgencyTxt = "null";
	
	/** The enforcement jurisdiction cd. */
	@JsonProperty("enforcement_jurisdiction_cd")
	public String enforcementJurisdictionCd = "null";
	
	/** The enforcement jurisdiction txt. */
	@JsonProperty("enforcement_jurisdiction_txt")
	public String enforcementJurisdictionTxt = "null";
	
	/** The certificate of service dt. */
	@JsonProperty("certificate_of_service_dt")
	public String certificateOfServiceDt = "null";
	
	/** The certificate of service no. */
	@JsonProperty("certificate_of_service_no")
	public String certificateOfServiceNo = "null";
	
	/** The e violation form no. */
	@JsonProperty("e_violation_form_no")
	public String eViolationFormNo = "null";
	
	/** The count qty. */
	@JsonProperty("count_qty")
	public String countQty = "null";
	
	/** The mre minor version txt. */
	@JsonProperty("mre_minor_version_txt")
	public String mreMinorVersionTxt = "null";
	
	/** The enforcement officer no. */
	@JsonProperty("enforcement_officer_no")
	public String enforcementOfficerNo = "null";
	
	/** The enforcement officer nm. */
	@JsonProperty("enforcement_officer_nm")
	public String enforcementOfficerNm = "null";
	
	/** The file nm. */
	@JsonProperty("file_nm")
	public String fileNm = "null";
	
	/** The profile nm. */
	@JsonProperty("profile_nm")
	public String profileNm = "null";
	
	/** The enforcement org unit cd. */
	@JsonProperty("enforcement_org_unit_cd")
	public String enforcementOrgUnitCd = "null";
	
	/** The enforcement org unit cd txt. */
	@JsonProperty("enforcement_org_unit_cd_txt")
	public String enforcementOrgUnitCdTxt = "null";
	
	/** The vehicle style txt. */
	@JsonProperty("vehicle_style_txt")
	public String vehicleStyleTxt = "null";
	
	/** The vehicle original colour cd. */
	@JsonProperty("vehicle_original_colour_cd")
	public String vehicleOriginalColourCd = "null";
	
	/** The vehicle original colour txt. */
	@JsonProperty("vehicle_original_colour_txt")
	public String vehicleOriginalColourTxt = "null";
	
	/** The reg owner nm. */
	@JsonProperty("reg_owner_nm")
	public String regOwnerNm = "null";
	
	/** The dl produced yn. */
	@JsonProperty("dl_produced_yn")
	public String dlProducedYn = "null";
	
	/** The change address yn. */
	@JsonProperty("change_address_yn")
	public String changeAddressYn = "null";

}