package bcgov.jh.etk.jhetkcommon.dataaccess.rowmapper.helper;

import java.math.BigInteger;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.lang3.StringUtils;

import bcgov.jh.etk.jhetkcommon.model.EtkReportKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.EtkTicket;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class MapHelper.
 */
public class MapHelper {
	
	/**
	 * Map to etk report KPI details.
	 *
	 * @param rs the rs
	 * @return the etk report KPI details
	 * @throws SQLException the SQL exception
	 */
	public static EtkReportKPIDetails mapToEtkReportKPIDetails(ResultSet rs) throws SQLException{
		EtkReportKPIDetails kpiDetails = new EtkReportKPIDetails();
		kpiDetails.setACCIDENT_YN(rs.getString("ACCIDENT_YN"));
		if (rs.getDate("CERTIFICATE_OF_SERVICE_DT") != null) {
			kpiDetails.setCERTIFICATE_OF_SERVICE_DT(DateUtil.DateToString(rs.getDate("CERTIFICATE_OF_SERVICE_DT"), DateUtil.YYYY_MM_DD));
		}
		
		kpiDetails.setCERTIFICATE_OF_SERVICE_NO(rs.getString("CERTIFICATE_OF_SERVICE_NO"));
		kpiDetails.setCOURT_LOCATION_CD(rs.getString("COURT_LOCATION_CD"));
		kpiDetails.setDISPUTE_ADDRESS_TXT(rs.getString("DISPUTE_ADDRESS_TXT"));
		kpiDetails.setDRIVERS_LICENCE_PROVINCE_CD(rs.getString("DRIVERS_LICENCE_PROVINCE_CD"));
		kpiDetails.setMRE_AGENCY_TXT(rs.getString("MRE_AGENCY_TXT"));
		kpiDetails.setENFORCEMENT_JURISDICTION_CD(rs.getString("ENFORCEMENT_JURISDICTION_CD"));
		kpiDetails.setENFORCEMENT_JURISDICTION_TXT(rs.getString("ENFORCEMENT_JURISDICTION_TXT"));
		kpiDetails.seteViolationFormNo(rs.getString("E_VIOLATION_FORM_NO"));
		kpiDetails.setOFFENDER_TYPE_CD(rs.getString("OFFENDER_TYPE_CD"));
		kpiDetails.setPERSON_GENDER_CD(rs.getString("PERSON_GENDER_CD"));
		kpiDetails.setPERSON_RESIDENCE_CITY_NM(rs.getString("PERSON_RESIDENCE_CITY_NM"));
		kpiDetails.setPERSON_RESIDENCE_PROVINCE_CD(rs.getString("PERSON_RESIDENCE_PROVINCE_CD"));
		
		if (rs.getDate("SUBMIT_DT") != null) {
			kpiDetails.setSUBMIT_DT(DateUtil.DateToString(rs.getDate("SUBMIT_DT"), DateUtil.YYYY_MM_DD));
			
		}
		kpiDetails.setSENT_TM(rs.getString("SENT_TM"));
		kpiDetails.setTICKET_ID(BigInteger.valueOf(rs.getInt("TICKET_ID")));
		kpiDetails.setTICKET_NO(rs.getString("TICKET_NO"));
		kpiDetails.setVEHICLE_MAKE_CD(rs.getString("VEHICLE_MAKE_CD"));
		kpiDetails.setVEHICLE_NSC_PUJ_CD(rs.getString("VEHICLE_NSC_PUJ_CD"));
		kpiDetails.setVEHICLE_PROVINCE_CD(rs.getString("VEHICLE_PROVINCE_CD"));
		kpiDetails.setVEHICLE_TYPE_CD(rs.getString("VEHICLE_TYPE_CD"));
		kpiDetails.setVEHICLE_YY(rs.getString("VEHICLE_YY"));
		kpiDetails.setVIOLATION_CITY_CD(rs.getString("VIOLATION_CITY_CD"));
		kpiDetails.setVIOLATION_CITY_NM(rs.getString("VIOLATION_CITY_NM"));
		
		if (rs.getDate("VIOLATION_DT") != null) {
			kpiDetails.setVIOLATION_DT(DateUtil.DateToString(rs.getDate("VIOLATION_DT"), DateUtil.YYYY_MM_DD));
		}
		
		kpiDetails.setVIOLATION_HIGHWAY_NM(rs.getString("VIOLATION_HIGHWAY_NM"));
		kpiDetails.setVIOLATION_TM(rs.getString("VIOLATION_TM"));
		kpiDetails.setYOUNG_PERSON_YN(rs.getString("YOUNG_PERSON_YN"));
		
		kpiDetails.setDL_PRODUCED_YN(rs.getString("DL_PRODUCED_YN"));
		kpiDetails.setCHANGE_ADDRESS_YN(rs.getString("CHANGE_ADDRESS_YN"));

		kpiDetails.setENT_DTM(rs.getTimestamp("ENT_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("ENT_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		kpiDetails.setENT_USER_ID(rs.getString("ENT_USER_ID"));
		
		kpiDetails.setUPD_DTM(rs.getTimestamp("UPD_DTM") == null ? null : DateUtil.localDateTimeToString(rs.getTimestamp("UPD_DTM").toLocalDateTime(), DateUtil.YYYY_MM_DD_HH_MM_SS));
		kpiDetails.setUPD_USER_ID(rs.getString("UPD_USER_ID"));
		kpiDetails.setMRE_MINOR_VERSION_TXT(rs.getString("MRE_MINOR_VERSION_TXT"));
		kpiDetails.setCOUNT_QTY(rs.getInt("COUNT_QTY"));
		kpiDetails.setENFORCEMENT_OFFICER_NO(rs.getString("ENFORCEMENT_OFFICER_NO"));
		kpiDetails.setENFORCEMENT_OFFICER_NM(rs.getString("ENFORCEMENT_OFFICER_NM"));
		
		kpiDetails.setProfile_nm(rs.getString("PROFILE_NM"));
		kpiDetails.setEnforcement_org_unit_cd(rs.getString("enforcement_org_unit_cd"));
		kpiDetails.setEnforcement_org_unit_cd_txt(rs.getString("enforcement_org_unit_cd_txt"));
		kpiDetails.setVehicle_style_txt(rs.getString("vehicle_style_txt"));
		kpiDetails.setVehicle_original_colour_cd(rs.getString("vehicle_original_colour_cd"));
		kpiDetails.setVehicle_original_colour_txt(rs.getString("vehicle_original_colour_txt"));
		kpiDetails.setReg_owner_nm(rs.getString("reg_owner_nm"));
		kpiDetails.setFile_nm(rs.getString("file_nm"));
		return kpiDetails;
	}
	
	/**
	 * Map to etk ticket.
	 *
	 * @param rs the rs
	 * @return the etk ticket
	 * @throws SQLException the SQL exception
	 */
	public static EtkTicket mapToEtkTicket(ResultSet rs) throws SQLException {
		EtkTicket ticket = new EtkTicket();
		
		ticket.setOutbound_xml_txt(rs.getString("OUTBOUND_XML_TXT"));
		ticket.setTicketFileName(rs.getString("FILE_NM"));
		ticket.setSource_xml_txt(rs.getString("SOURCE_XML_TXT"));
		ticket.setTicketID(rs.getString("TICKET_ID") == null ? null : new BigInteger(rs.getString("TICKET_ID")));
		ticket.setTicketNumber(rs.getString("TICKET_NO"));
		
		ticket.setTicketEnteredDateTime(rs.getTimestamp("ticketEnteredDT") == null ? null : rs.getTimestamp("ticketEnteredDT").toLocalDateTime());
		ticket.setTicketLastUpdatedDateTime(rs.getTimestamp("ticketLastUpdatedDT") == null ? null : rs.getTimestamp("ticketLastUpdatedDT").toLocalDateTime());
		if (StringUtils.isNotBlank(rs.getString("PROCESS_STATE_TYPE_CD"))) {
			ticket.setProcess_state_type_cd(EnumProcessState.getEnumfromCodeValue(rs.getString("PROCESS_STATE_TYPE_CD")));
		}
		
		return ticket;
	}
}
