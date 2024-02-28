package bcgov.jh.etk.jhetkcommon.model;

import java.math.BigInteger;
import java.util.HashMap;

import lombok.Getter;
import lombok.Setter;

// TODO: Auto-generated Javadoc
/**
 * The Class VPHReportKPIDetails.
 * @author HLiang
 */
/**
 * @author david
 *
 */
@Setter
@Getter
public class EtkReportKPIDetails {
	
	/** The event id. */
	private Integer EVENT_ID;
	
	/** The ticket ID. */
	private BigInteger TICKET_ID;
	
	/** The submit DT. */
	private String SUBMIT_DT;
	
	/** The send TM. */
	private String SENT_TM;
	
	/** The tiket NO. */
	private String TICKET_NO;
	
	/** The drivers licence province CD. */
	private String DRIVERS_LICENCE_PROVINCE_CD;
	
	/** The person gender CD. */
	private String PERSON_GENDER_CD;
	
	/** The person residence city NM. */
	private String PERSON_RESIDENCE_CITY_NM;
	
	/** The person residence province CD. */
	private String PERSON_RESIDENCE_PROVINCE_CD;
	
	/** The young person YN. */
	private String YOUNG_PERSON_YN;
	
	/** The offender type CD. */
	private String OFFENDER_TYPE_CD;
	
	/** The violation DT. */
	private String VIOLATION_DT;
	
	/** The violation TM. */
	private String VIOLATION_TM;
	
	/** The violation highway Name. */
	private String VIOLATION_HIGHWAY_NM;
	
	/** The violation city CD. */
	private String VIOLATION_CITY_CD;
	
	/** The violation city NM. */
	private String VIOLATION_CITY_NM;
	
	/** The vehicle privince code. */
	private String VEHICLE_PROVINCE_CD;
	
	/** The vehicle nsc puj code. */
	private String VEHICLE_NSC_PUJ_CD;
	
	/** The vehicle make CD. */
	private String VEHICLE_MAKE_CD;
	
	/** The vehicle type CD. */
	private String VEHICLE_TYPE_CD;
	
	/** The vehicle YY. */
	private String VEHICLE_YY;
	
	/** The accident YN. */
	private String ACCIDENT_YN;

	/** The DL produced YN. */
	private String DL_PRODUCED_YN;
	
	/** The change address YN. */
	private String CHANGE_ADDRESS_YN;
	
	/** The dispute address txt. */
	private String DISPUTE_ADDRESS_TXT;
	
	/** The court location CD. */
	private String COURT_LOCATION_CD;
	
	/** The mre agency txt. */
	private String MRE_AGENCY_TXT;
	
	/** The enforcement jurisdiction CD. */
	private String ENFORCEMENT_JURISDICTION_CD;
	
	/** The certificate of service DT. */
	private String CERTIFICATE_OF_SERVICE_DT;
	
	/** The certificate of service NO. */
	private String CERTIFICATE_OF_SERVICE_NO;
	
	/** The e violcation form NO. */
	private String eViolationFormNo;
	
	/** The enforcement jurisdiction txt. */
	private String ENFORCEMENT_JURISDICTION_TXT;
	
	/** The enter user ID. */
	private String ENT_USER_ID;
	
	/** The enter DTM. */
	private String ENT_DTM;
	
	/** The update user ID. */
	private String UPD_USER_ID;
	
	/** The update DTM. */
	private String UPD_DTM;
	
	/** The mre minor version txt. */
	private String MRE_MINOR_VERSION_TXT;
	
	/** The count qty. */
	private int COUNT_QTY;
	
	/** The enforcement officer no. */
	private String ENFORCEMENT_OFFICER_NO;
	
	/** The enforcement officer nm. */
	private String ENFORCEMENT_OFFICER_NM;
	
	/** The profile nm. */
	private String profile_nm;
	
	/** The enforcement org unit cd. */
	private String enforcement_org_unit_cd;
	
	/** The enforcement org unit cd txt. */
	private String enforcement_org_unit_cd_txt;
	
	/** The vehicle style txt. */
	private String vehicle_style_txt;
	
	/** The vehicle original colour cd. */
	private String vehicle_original_colour_cd;
	
	/** The vehicle original colour txt. */
	private String vehicle_original_colour_txt;
	
	/** The reg owner nm. */
	private String reg_owner_nm;
	
	/** The file nm. */
	private String file_nm;
	
	/**  The violations. */
	private HashMap<String, EtkReportViolationDetails> violations;

	/**
	 * Gets the violations.
	 *
	 * @return the violations
	 */
	public HashMap<String, EtkReportViolationDetails> getViolations() {
		if (violations == null) {
			violations = new HashMap<String, EtkReportViolationDetails>();
		}
		return violations;
	}

	/**
	 * Gets the e violation form no.
	 *
	 * @return the e violation form no
	 */
	public String geteViolationFormNo() {
		return eViolationFormNo;
	}

	/**
	 * Sets the e violation form no.
	 *
	 * @param eViolationFormNo the new e violation form no
	 */
	public void seteViolationFormNo(String eViolationFormNo) {
		this.eViolationFormNo = eViolationFormNo;
	}

}
