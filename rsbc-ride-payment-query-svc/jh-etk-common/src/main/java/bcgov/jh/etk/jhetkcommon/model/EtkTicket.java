package bcgov.jh.etk.jhetkcommon.model;

import java.math.BigInteger;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import lombok.Getter;
import lombok.Setter;


/**
 * The Class VPHTicket.
 * @author HLiang
 */
@Setter
@Getter
public class EtkTicket {
	
	/** The ticket ID. */
	private BigInteger ticketID;
	
	/** The ticket number. */
	private String ticketNumber;
	
	/** The create date time. */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime ticketEnteredDateTime;
	
	/** The update date time. */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss", iso = ISO.DATE_TIME)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime ticketLastUpdatedDateTime;
	
	/** The accused entity type dsc. */
	private String accused_entity_type_dsc;
	
	/** The accused organization nm. */
	private String accused_organization_nm;
	
	/** The accused surname nm. */
	private String accused_surname_nm;
	
	/** The accused first given nm. */
	private String accused_first_given_nm;
	
	/** The accused second given nm. */
	private String accused_second_given_nm;
	
	/** The young person yn. */
	private String young_person_yn;
	
	/** The drivers licence province cd. */
	private String drivers_licence_province_cd;
	
	/** The drivers licence no. */
	private String drivers_licence_no;
	
	/** The drivers licence produced yn. */
	private String drivers_licence_produced_yn;
	
	/** The drivers licence expiry dt. */
	private String drivers_licence_expiry_dt;
	
	/** The accused gender cd. */
	private String accused_gender_cd;
	
	/** The accused birth dt. */
	private String accused_birth_dt;
	
	/** The accused address detail txt. */
	private String accused_address_detail_txt;
	
	/** The change of address yn. */
	private String change_of_address_yn;
	
	/** The accused city nm. */
	private String accused_city_nm;
	
	/** The accused province cd. */
	private String accused_province_cd;
	
	/** The accused postal code txt. */
	private String accused_postal_code_txt;
	
	/** The accused role txt. */
	private String accused_role_txt;
	
	/** The on location txt. */
	private String on_location_txt;
	
	/** The at or near location txt. */
	private String at_or_near_location_txt;
	
	/** The statute dsc. */
	private String statute_dsc;
	
	/** The vehicle province cd. */
	private String vehicle_province_cd;
	
	/** The nsc puj. */
	private String nsc_puj;
	
	/** The nsc no. */
	private String nsc_no;
	
	/** The registered owner nm. */
	private String registered_owner_nm;
	
	/** The vehicle make dsc. */
	private String vehicle_make_dsc;
	
	/** The vehicle type dsc. */
	private String vehicle_type_dsc;
	
	/** The vehicle colour dsc. */
	private String vehicle_colour_dsc;
	
	/** The vehicle yy. */
	private String vehicle_yy;
	
	/** The accident yn. */
	private String accident_yn;
	
	/** The dispute delivery address txt. */
	private String dispute_delivery_address_txt;
	
	/** The court hearing location txt. */
	private String court_hearing_location_txt;
	
	/** The enforcement officer id. */
	private String enforcement_officer_id;
	
	/** The enforcement organization nm. */
	private String enforcement_organization_nm;
	
	/** The certification nm. */
	private String certification_nm;
	
	/** The certification occupation txt. */
	private String certification_occupation_txt;
	
	/** The certification dt. */
	private String certification_dt;
	
	/** The service recipient nm. */
	private String service_recipient_nm;
	
	/** The service method dsc. */
	private String service_method_dsc;
	
	/** The source xml txt. */
	private String source_xml_txt;
	
	/** The outbound xml txt. */
	private String outbound_xml_txt;
	
	/** The count nbr. */
	private String count_nbr;
	
	/** The offence description txt. */
	private String offence_description_txt;
	
	/** The act cd. */
	private String act_cd;
	
	/** The legislation indicator cd. */
	private String legislation_indicator_cd;
	
	/** The section txt. */
	private String section_txt;
	
	/** The ticketed amt. */
	private String ticketed_amt;
	
	/** The process state type cd. */
	private EnumProcessState process_state_type_cd;
	
	/** The ticket file name. */
	private String ticketFileName;
	
	/** The ticket file exist. */
	private boolean ticketFileExist;
	
	/**
	 * Gets the ticket entered date time.
	 *
	 * @return the ticket entered date time
	 */
	public LocalDateTime getTicketEnteredDateTime() {
		return DateUtil.toPacificLDT(ticketEnteredDateTime);
	}

	/**
	 * Gets the ticket last updated date time.
	 *
	 * @return the ticket last updated date time
	 */
	public LocalDateTime getTicketLastUpdatedDateTime() {
		return DateUtil.toPacificLDT(ticketLastUpdatedDateTime);
	}
}
