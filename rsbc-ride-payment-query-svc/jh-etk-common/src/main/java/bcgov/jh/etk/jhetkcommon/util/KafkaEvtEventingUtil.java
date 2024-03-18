package bcgov.jh.etk.jhetkcommon.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.EtkReportKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.EtkReportViolationDetails;
import bcgov.jh.etk.jhetkcommon.model.TicketDisputeKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.TicketPaymentKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.VTDisputeEvent;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.VTDisputeStatusUpdateEvent;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.EVTIssuanceEvent;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.VTPaymentEvent;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.KafkaEvent;
import bcgov.jh.etk.jhetkcommon.model.eventing.kafka.Violation;

/**
 * The Class KafkaEvtEventingUtil.
 */
public class KafkaEvtEventingUtil {
	
	/**
	 * Gets the event publish page size.
	 *
	 * @param pageSize the page size
	 * @return the event publish page size
	 */
	public static Integer getEventPublishPageSize(Integer pageSize) {
		if (pageSize == null || pageSize < 0) {
			return Const.EVENTING_DEFAULT_PAGE_SIZE;
		}
		if (pageSize > Const.EVENTING_MAX_PAGE_SIZE) {
			return Const.EVENTING_MAX_PAGE_SIZE;
		}
		return pageSize;
	}
	
	/**
	 * Map EtkREportKPIDetails object to Event object.
	 *
	 * @param issuanceEvt the issuance evt
	 * @return the event
	 */
	public static KafkaEvent mapFromEtkREportKPIDetails(EtkReportKPIDetails issuanceEvt) {
		KafkaEvent evt = new KafkaEvent();
		evt.setEventID(issuanceEvt.getEVENT_ID());
		evt.setEvtIssuanceEvent(mapEtkREportKPIDetailsToEVTIssuance(issuanceEvt));
		return evt;
	}
	
	/**
	 * Map a list of EtkREportKPIDetailsList objects into a list of Event objects.
	 *
	 * @param issuanceEvents the issuance events
	 * @return the list
	 */
	public static List<KafkaEvent> mapFromEtkREportKPIDetailsList(List<EtkReportKPIDetails> issuanceEvents) {
		List<KafkaEvent> events = new ArrayList<KafkaEvent>();
		for (EtkReportKPIDetails kpi : issuanceEvents) {
			events.add(mapFromEtkREportKPIDetails(kpi));
		}
		return events;
	}
	
	/**
	 * Map EtkREportKPIDetails object to EVTIssuance object.
	 *
	 * @param issuanceEvt the issuance evt
	 * @return the EVT issuance
	 */
	private static EVTIssuanceEvent mapEtkREportKPIDetailsToEVTIssuance(EtkReportKPIDetails issuanceEvt) {
		EVTIssuanceEvent issuance = new EVTIssuanceEvent();
		issuance.setCountQty(String.valueOf(issuanceEvt.getCOUNT_QTY()));
		issuance.setChangeAddressYn(issuanceEvt.getCHANGE_ADDRESS_YN());
		issuance.setDlProducedYn(issuanceEvt.getDL_PRODUCED_YN());
		issuance.setEnforcementOrgUnitCd(issuanceEvt.getEnforcement_org_unit_cd());
		issuance.setEnforcementOrgUnitCdTxt(issuanceEvt.getEnforcement_org_unit_cd_txt());
		issuance.setFileNm(issuanceEvt.getFile_nm());
		issuance.setViolations(mapFromEtkReportViolationDetailsList(issuanceEvt.getViolations()));
		issuance.setEnforcementJurisdictionCd(issuanceEvt.getENFORCEMENT_JURISDICTION_CD());
		issuance.setEnforcementJurisdictionTxt(issuanceEvt.getENFORCEMENT_JURISDICTION_TXT());
		issuance.setEnforcementOfficerNm(issuanceEvt.getENFORCEMENT_OFFICER_NM());
		issuance.setEnforcementOfficerNo(issuanceEvt.getENFORCEMENT_OFFICER_NO());
		issuance.setTicketNo(issuanceEvt.getTICKET_NO());
		issuance.setVehicleMakeCd(issuanceEvt.getVEHICLE_MAKE_CD());
		issuance.setVehicleTypeCd(issuanceEvt.getVEHICLE_TYPE_CD());
		issuance.setViolationCityCd(issuanceEvt.getVIOLATION_CITY_CD());
		issuance.setViolationCityNm(issuanceEvt.getVIOLATION_CITY_NM());
		issuance.setViolationDt(issuanceEvt.getVIOLATION_DT());
		issuance.setViolationHighwayNm(issuanceEvt.getVIOLATION_HIGHWAY_NM());
		issuance.setViolationTm(issuanceEvt.getVIOLATION_TM());
		issuance.setOffenderTypeCd(issuanceEvt.getOFFENDER_TYPE_CD());
		issuance.setSubmitDt(issuanceEvt.getSUBMIT_DT());
		issuance.setSentTm(issuanceEvt.getSENT_TM());
		issuance.setDriversLicenceProvinceCd(issuanceEvt.getDRIVERS_LICENCE_PROVINCE_CD());
		issuance.setPersonGenderCd(issuanceEvt.getPERSON_GENDER_CD());
		issuance.setPersonResidenceCityNm(issuanceEvt.getPERSON_RESIDENCE_CITY_NM());
		issuance.setPersonResidenceProvinceCd(issuanceEvt.getPERSON_RESIDENCE_PROVINCE_CD());
		issuance.setProfileNm(issuanceEvt.getProfile_nm());
		issuance.setRegOwnerNm(issuanceEvt.getReg_owner_nm());
		issuance.setYoungPersonYn(issuanceEvt.getYOUNG_PERSON_YN());
		issuance.setVehicleProvinceCd(issuanceEvt.getVEHICLE_PROVINCE_CD());
		issuance.setVehicleStyleTxt(issuanceEvt.getVehicle_style_txt());
		issuance.setVehicleOriginalColourCd(issuanceEvt.getVehicle_original_colour_cd());
		issuance.setVehicleOriginalColourTxt(issuanceEvt.getVehicle_original_colour_txt());
		issuance.setVehicleNscPujCd(issuanceEvt.getVEHICLE_NSC_PUJ_CD());
		issuance.setVehicleYy(issuanceEvt.getVEHICLE_YY());
		issuance.setAccidentYn(issuanceEvt.getACCIDENT_YN());
		issuance.setDisputeAddressTxt(issuanceEvt.getDISPUTE_ADDRESS_TXT());
		issuance.setCourtLocationCd(issuanceEvt.getCOURT_LOCATION_CD());
		issuance.setMreAgencyTxt(issuanceEvt.getMRE_AGENCY_TXT());
		issuance.setCertificateOfServiceDt(issuanceEvt.getCERTIFICATE_OF_SERVICE_DT());
		issuance.setCertificateOfServiceNo(issuanceEvt.getCERTIFICATE_OF_SERVICE_NO());
		issuance.setEViolationFormNo(issuanceEvt.geteViolationFormNo());
		issuance.setMreMinorVersionTxt(issuanceEvt.getMRE_MINOR_VERSION_TXT());
	
		return issuance;
	}
	
	/**
	 * Map a EtkReportViolationDetails object to EVTIssuanceCount object.
	 *
	 * @param vd the vd
	 * @return the EVT issuance count
	 */
	private static Violation mapFromEtkReportViolationDetails(EtkReportViolationDetails vd) {
		Violation ic = new Violation();
		ic.setActCd(vd.getACT_CD());
		ic.setCountNbr(String.valueOf(vd.getCOUNT_NBR()));
		ic.setFineAmt(vd.getFINE_AMT());
		ic.setSectionDsc(vd.getSECTION_DSC());
		ic.setSectionTxt(vd.getSECTION_TXT());
		ic.setWordingNbr(vd.getWORDING_NBR());
		return ic;				
	}
	
	/**
	 * Map a list of EtkReportViolationDetails object to a list of EVTIssuanceCount object.
	 *
	 * @param vd the vd
	 * @return the list
	 */
	private static List<Violation> mapFromEtkReportViolationDetailsList(HashMap<String, EtkReportViolationDetails> vd) {
		List<Violation> icl = new ArrayList<Violation>();
		for (Map.Entry<String, EtkReportViolationDetails> entry : vd.entrySet()) {
			icl.add(mapFromEtkReportViolationDetails(entry.getValue()));
	    }
		return icl;
	}
	
	/**
	 * Map a list of TicketPaymentKPIDetails objects to a list of Event object.
	 *
	 * @param reportEvents the report events
	 * @return the list
	 */
	public static List<KafkaEvent> mapFromTicketPaymentKPIDetailsList(List<TicketPaymentKPIDetails> reportEvents) {
		List<KafkaEvent> events = new ArrayList<KafkaEvent>();
		for (TicketPaymentKPIDetails kpi : reportEvents) {
			events.add(mapFromTicketPaymentKPIDetails(kpi));
		}
		return events;
	}
	
	/**
	 * Map a TicketPaymentKPIDetails object to Event object.
	 *
	 * @param reportEvent the report event
	 * @return the event
	 */
	public static KafkaEvent mapFromTicketPaymentKPIDetails(TicketPaymentKPIDetails reportEvent) {
		KafkaEvent evt = new KafkaEvent();
		evt.setEventID(reportEvent.getEVENT_ID().intValue());
		if (EnumEventType.PAYMENT.equals(reportEvent.getEVENT_TYPE_CD())) {
			evt.setVtPaymentEvent(mapTicketPaymentKPIDetailsToVTPayment(reportEvent));
		}
		
		// Pushing 'QUERY' event to kafka is out of scope
		//if (EnumEventType.QUERY.equals(reportEvent.getEVENT_TYPE_CD())) {
		//	evt.setVtQuery(mapTicketPaymentKPIDetailsToVTQuery(reportEvent));
		//}
		return evt;
	}
	
	/**
	 * Map a EtkREportKPIDetailsToVTPayment object to VTPayment object.
	 *
	 * @param reportEvt the report evt
	 * @return the VT payment
	 */
	private static VTPaymentEvent mapTicketPaymentKPIDetailsToVTPayment(TicketPaymentKPIDetails reportEvt) {
		VTPaymentEvent vp = new VTPaymentEvent();
		vp.setCountNbr(reportEvt.getCOUNT_NBR());
		vp.setPaymentAmt(String.valueOf(reportEvt.getPAYMENT_AMT()));
		vp.setPaymentCardTypeTxt(reportEvt.getPAYMENT_CARD_TYPE_TXT());
		vp.setPaymentTicketTypeCd(reportEvt.getPAYMENT_TICKET_TYPE_CD().getCodeValue());
		vp.setTicketNo(reportEvt.getTICKET_NO());
		vp.setTransactionId(reportEvt.getPAYMENT_TRANSACTION_ID());
		vp.setReceiptNbr(reportEvt.getRECEIPT_NBR());
		return vp;
	}

	
	/**
	 * Map a list of TicketDisputeKPIDetailsList objects to a list of Event object.
	 *
	 * @param disputeEvents the dispute events
	 * @return the list
	 */
	public static List<KafkaEvent> mapFromTicketDisputeKPIDetailsList(List<TicketDisputeKPIDetails> disputeEvents) {
		List<KafkaEvent> events = new ArrayList<KafkaEvent>();
		for (TicketDisputeKPIDetails kpi : disputeEvents) {
			events.add(mapFromTicketDisputeKPIDetails(kpi));
		}
		return events;
	}
	
	/**
	 * Map a TicketDisputeKPIDetails object to an Event object.
	 *
	 * @param disputeEvents the dispute events
	 * @return the event
	 */
	public static KafkaEvent mapFromTicketDisputeKPIDetails(TicketDisputeKPIDetails disputeEvents) {
		KafkaEvent evt = new KafkaEvent();
		evt.setEventID(disputeEvents.getEventID().intValue());
		if (EnumEventType.DISPUTE.equals(disputeEvents.getEvent_type_cd())) {
			evt.setVtDisputeEvent(mapTicketDisputeKPIDetailsToVTDispute(disputeEvents));
		} else if (EnumEventType.DISPUTE_STATUS_UPDATE.equals(disputeEvents.getEvent_type_cd())) {
			evt.setVtDisputeStatusUpdate(mapTicketDisputeKPIDetailsToVTDisputeStatusUpdate(disputeEvents));
		}
		
		return evt;
	}
	
	/**
	 * Map a TicketDisputeKPIDetails object to a VTDispute object.
	 *
	 * @param disputeEvents the dispute events
	 * @return the VT dispute
	 */
	private static VTDisputeEvent mapTicketDisputeKPIDetailsToVTDispute(TicketDisputeKPIDetails disputeEvents) {
		VTDisputeEvent vd = new VTDisputeEvent();
		vd.setCompressedSection(disputeEvents.getCompressedSection());
		vd.setCountActRegulation(disputeEvents.getCountActRegulation());
		vd.setContraventionNo(disputeEvents.getContraventionNO());
		vd.setCreatedDtmz(disputeEvents.getDisputeActionDT());
		vd.setDisputeType(disputeEvents.getDisputeType());
		vd.setTicketedAmt(String.valueOf(disputeEvents.getTicktedAmount()));
		return vd;
	}
	
	/**
	 * Map a TicketDisputeKPIDetails object to a VTDisputeStatusUpdate object.
	 *
	 * @param disputeEvents the dispute events
	 * @return the VT dispute status update
	 */
	private static VTDisputeStatusUpdateEvent mapTicketDisputeKPIDetailsToVTDisputeStatusUpdate(TicketDisputeKPIDetails disputeEvents) {
		VTDisputeStatusUpdateEvent su = new VTDisputeStatusUpdateEvent();
		su.setCountNbr(EvtEnrichUtil.getCountNumber(disputeEvents.getContraventionNO()));
		su.setDisputeActionCode(disputeEvents.getDisputeActionCD());
		su.setDisputeActionDt(disputeEvents.getDisputeActionDT());
		su.setTicketNo(disputeEvents.getContraventionNO());
		return su;
	}
	

	/**
	 * Extract hour.
	 *
	 * @param violicationTime the violication time
	 * @return the integer
	 */
	public static Integer extractHour(String violicationTime) {
		if (StringUtils.isNoneBlank(violicationTime)) {
			String hourStr = violicationTime.substring(0, 2);
			return Integer.valueOf(hourStr);
		}
		return null;
	}
	
}
