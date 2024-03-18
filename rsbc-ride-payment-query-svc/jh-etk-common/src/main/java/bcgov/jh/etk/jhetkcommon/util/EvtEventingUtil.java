package bcgov.jh.etk.jhetkcommon.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.EtkDisputeFinding;
import bcgov.jh.etk.jhetkcommon.model.EtkReportEvents;
import bcgov.jh.etk.jhetkcommon.model.EtkReportKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.EtkReportViolationDetails;
import bcgov.jh.etk.jhetkcommon.model.TicketDisputeKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.TicketPaymentKPIDetails;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import bcgov.jh.etk.jhetkcommon.model.eventing.EVTIssuance;
import bcgov.jh.etk.jhetkcommon.model.eventing.EVTIssuanceCount;
import bcgov.jh.etk.jhetkcommon.model.eventing.EVTSent;
import bcgov.jh.etk.jhetkcommon.model.eventing.Event;
import bcgov.jh.etk.jhetkcommon.model.eventing.Event.EventTypeEnum;
import bcgov.jh.etk.jhetkcommon.model.eventing.VTDispute;
import bcgov.jh.etk.jhetkcommon.model.eventing.VTDispute.DisputeTypeCodeEnum;
import bcgov.jh.etk.jhetkcommon.model.eventing.VTDisputeFinding;
import bcgov.jh.etk.jhetkcommon.model.eventing.VTDisputeStatusUpdate;
import bcgov.jh.etk.jhetkcommon.model.eventing.VTDisputeStatusUpdate.DisputeActionCodeEnum;
import bcgov.jh.etk.jhetkcommon.model.eventing.VTPayment;
import bcgov.jh.etk.jhetkcommon.model.eventing.VTPayment.PaymentTicketTypeCodeEnum;
import bcgov.jh.etk.jhetkcommon.model.eventing.VTQuery;

/**
 * The Class EvtEventingMapUtil.
 */
public class EvtEventingUtil {
	
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
	 * Gets the event lookup page size.
	 *
	 * @param pageSize the page size
	 * @return the event lookup page size
	 */
	public static Integer getEventLookupPageSize(Integer pageSize) {
		if (pageSize == null || pageSize < 0) {
			return Const.EVENT_LOOKUP_DEFAULT_PAGE_SIZE;
		}
		if (pageSize > Const.EVENT_LOOKUP_MAX_PAGE_SIZE) {
			return Const.EVENT_LOOKUP_MAX_PAGE_SIZE;
		}
		return pageSize;
	}
	
	/**
	 * Map from sent etk report events.
	 *
	 * @param etkReportEvents the etk report events
	 * @return the event
	 */
	public static Event mapFromSentEtkReportEvents(EtkReportEvents etkReportEvents) {
		Event evt = new Event();
		evt.setEventVersion(Const.HUB_EVENT_VERSION);
		evt.setEventDateTime(DateUtil.LocalDateTimeToOffsetDateTime(DateUtil.StringToLocalDateTime(etkReportEvents.getEVENT_DTM(), DateUtil.YYYY_MM_DD_HH_MM_SS)));
		evt.setEventId(etkReportEvents.getEVENT_ID().intValue());
		evt.setEventType(EvtEventingUtil.mapFromEnumEventType(EnumEventType.SENT));
		EVTSent evtSent = new EVTSent();
		evtSent.setTicketNumber(etkReportEvents.getTICKET_NO());
		evt.setEvtSent(evtSent);
		return evt;
	}
	
	/**
	 * Map from sent etk report events list.
	 *
	 * @param etkReportEvents the etk report events
	 * @return the list
	 */
	public static List<Event> mapFromSentEtkReportEventsList(List<EtkReportEvents> etkReportEvents) {
		List<Event> events = new ArrayList<Event>();
		for (EtkReportEvents kpi : etkReportEvents) {
			events.add(mapFromSentEtkReportEvents(kpi));
		}
		return events;
	}
	
	/**
	 * Map from purged etk report events.
	 *
	 * @param etkReportEvents the etk report events
	 * @return the event
	 */
	public static Event mapFromPurgedEtkReportEvents(EtkReportEvents etkReportEvents) {
		Event evt = new Event();
		evt.setEventVersion(Const.HUB_EVENT_VERSION);
		evt.setEventDateTime(DateUtil.LocalDateTimeToOffsetDateTime(DateUtil.StringToLocalDateTime(etkReportEvents.getEVENT_DTM(), DateUtil.YYYY_MM_DD_HH_MM_SS)));
		evt.setEventId(etkReportEvents.getEVENT_ID().intValue());
		evt.setEventType(EvtEventingUtil.mapFromEnumEventType(EnumEventType.PURGED));
		EVTSent evtSent = new EVTSent();
		evtSent.setTicketNumber(etkReportEvents.getTICKET_NO());
		evt.setEvtSent(evtSent);
		return evt;
	}
	
	/**
	 * Map from purged etk report events list.
	 *
	 * @param etkReportEvents the etk report events
	 * @return the list
	 */
	public static List<Event> mapFromPurgedEtkReportEventsList(List<EtkReportEvents> etkReportEvents) {
		List<Event> events = new ArrayList<Event>();
		for (EtkReportEvents kpi : etkReportEvents) {
			events.add(mapFromPurgedEtkReportEvents(kpi));
		}
		return events;
	}
	
	
	/**
	 * Map EtkREportKPIDetails object to Event object.
	 *
	 * @param issuanceEvt the issuance evt
	 * @return the event
	 */
	public static Event mapFromEtkREportKPIDetails(EtkReportKPIDetails issuanceEvt) {
		Event evt = new Event();
		evt.setEventVersion(Const.HUB_EVENT_VERSION);
		evt.setEventDateTime(DateUtil.LocalDateTimeToOffsetDateTime(DateUtil.StringToLocalDateTime(issuanceEvt.getENT_DTM(), DateUtil.YYYY_MM_DD_HH_MM_SS)));
		evt.setEventId(issuanceEvt.getEVENT_ID());
		evt.setEventType(EvtEventingUtil.mapFromEnumEventType(EnumEventType.ISSUANCE));
		evt.setEvtIssuance(mapEtkREportKPIDetailsToEVTIssuance(issuanceEvt));
		return evt;
	}
	
	/**
	 * Map a list of EtkREportKPIDetailsList objects into a list of Event objects.
	 *
	 * @param issuanceEvents the issuance events
	 * @return the list
	 */
	public static List<Event> mapFromEtkREportKPIDetailsList(List<EtkReportKPIDetails> issuanceEvents) {
		List<Event> events = new ArrayList<Event>();
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
	private static EVTIssuance mapEtkREportKPIDetailsToEVTIssuance(EtkReportKPIDetails issuanceEvt) {
		EVTIssuance issuance = new EVTIssuance();
		issuance.setCountQuantity(issuanceEvt.getCOUNT_QTY());
		issuance.setCounts(mapFromEtkReportViolationDetailsList(issuanceEvt.getViolations()));
		issuance.setEnforcementJurisdictionCode(issuanceEvt.getENFORCEMENT_JURISDICTION_CD());
		issuance.setEnforcementJurisdictionName(issuanceEvt.getENFORCEMENT_JURISDICTION_TXT());
		issuance.setEnforcementOfficerName(issuanceEvt.getENFORCEMENT_OFFICER_NM());
		issuance.setEnforcementOfficerNumber(issuanceEvt.getENFORCEMENT_OFFICER_NO());
		issuance.setEnforcementOrgUnitCode(issuanceEvt.getEnforcement_org_unit_cd());
		issuance.setEnforcementOrgUnitTranslation(issuanceEvt.getEnforcement_org_unit_cd_txt());
		issuance.setTicketNumber(issuanceEvt.getTICKET_NO());
		issuance.setVehicleMakeName(issuanceEvt.getVEHICLE_MAKE_CD());
		issuance.setVehicleTypeCode(issuanceEvt.getVEHICLE_TYPE_CD());
		issuance.setViolationCityCode(issuanceEvt.getVIOLATION_CITY_CD());
		issuance.setViolationCityName(issuanceEvt.getVIOLATION_CITY_NM());
		issuance.setViolationDate(issuanceEvt.getVIOLATION_DT());
		issuance.setViolationHighwayDesc(issuanceEvt.getVIOLATION_HIGHWAY_NM());
		issuance.setViolationTime(issuanceEvt.getVIOLATION_TM());
		issuance.setOffenderTypeName(issuanceEvt.getOFFENDER_TYPE_CD());
		issuance.setSubmitDate(issuanceEvt.getSUBMIT_DT());
		issuance.setSentTime(issuanceEvt.getSENT_TM());
		issuance.setDriversLicenceProvinceCode(issuanceEvt.getDRIVERS_LICENCE_PROVINCE_CD());
		issuance.setPersonGenderCode(issuanceEvt.getPERSON_GENDER_CD());
		issuance.setPersonResidenceCityName(issuanceEvt.getPERSON_RESIDENCE_CITY_NM());
		issuance.setPersonResidenceProvinceCode(issuanceEvt.getPERSON_RESIDENCE_PROVINCE_CD());
		issuance.setYoungPersonYn(issuanceEvt.getYOUNG_PERSON_YN());
		issuance.setVehicleProvinceCode(issuanceEvt.getVEHICLE_PROVINCE_CD());
		issuance.setVehicleNscPujCode(issuanceEvt.getVEHICLE_NSC_PUJ_CD());
		issuance.setVehicleYear(issuanceEvt.getVEHICLE_YY());
		issuance.setAccidentYn(issuanceEvt.getACCIDENT_YN());
		issuance.setDisputeAddressText(issuanceEvt.getDISPUTE_ADDRESS_TXT());
		issuance.setCourtLocationCode(issuanceEvt.getCOURT_LOCATION_CD());
		issuance.setMreAgencyText(issuanceEvt.getMRE_AGENCY_TXT());
		issuance.setCertificateOfServiceDate(issuanceEvt.getCERTIFICATE_OF_SERVICE_DT());
		issuance.setCertificateOfServiceNumber(issuanceEvt.getCERTIFICATE_OF_SERVICE_NO());
		issuance.setEViolationFormNumber(issuanceEvt.geteViolationFormNo());
		issuance.setMreMinorVersionText(issuanceEvt.getMRE_MINOR_VERSION_TXT());
		return issuance;
	}
	
	/**
	 * Map a EtkReportViolationDetails object to EVTIssuanceCount object.
	 *
	 * @param vd the vd
	 * @return the EVT issuance count
	 */
	private static EVTIssuanceCount mapFromEtkReportViolationDetails(EtkReportViolationDetails vd) {
		EVTIssuanceCount ic = new EVTIssuanceCount();
		ic.setActCode(vd.getACT_CD());
		ic.setCountNumber(vd.getCOUNT_NBR());
		ic.setFineAmount(vd.getFINE_AMT());
		ic.setSectionDesc(vd.getSECTION_DSC());
		ic.setSectionText(vd.getSECTION_TXT());
		Integer wordingNbr = null;
		try {
			wordingNbr = Integer.parseInt(vd.getWORDING_NBR());
		} catch (NumberFormatException e) {
			
		}
		ic.setWordingNbr(wordingNbr);
		return ic;				
	}
	
	/**
	 * Map a list of EtkReportViolationDetails object to a list of EVTIssuanceCount object.
	 *
	 * @param vd the vd
	 * @return the list
	 */
	private static List<EVTIssuanceCount> mapFromEtkReportViolationDetailsList(HashMap<String, EtkReportViolationDetails> vd) {
		List<EVTIssuanceCount> icl = new ArrayList<EVTIssuanceCount>();
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
	public static List<Event> mapFromTicketPaymentKPIDetailsList(List<TicketPaymentKPIDetails> reportEvents) {
		List<Event> events = new ArrayList<Event>();
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
	public static Event mapFromTicketPaymentKPIDetails(TicketPaymentKPIDetails reportEvent) {
		Event evt = new Event();
		evt.setEventVersion(Const.HUB_EVENT_VERSION);
		evt.setEventDateTime(DateUtil.LocalDateTimeToOffsetDateTime(DateUtil.StringToLocalDateTime(reportEvent.getENT_DTM(), DateUtil.YYYY_MM_DD_HH_MM_SS)));
		evt.setEventId(reportEvent.getEVENT_ID().intValue());
		evt.setEventType(EvtEventingUtil.mapFromEnumEventType(reportEvent.getEVENT_TYPE_CD()));
		if (EnumEventType.PAYMENT.equals(reportEvent.getEVENT_TYPE_CD())) {
			evt.setVtPayment(mapTicketPaymentKPIDetailsToVTPayment(reportEvent));
		}
		if (EnumEventType.QUERY.equals(reportEvent.getEVENT_TYPE_CD())) {
			evt.setVtQuery(mapTicketPaymentKPIDetailsToVTQuery(reportEvent));
		}
		return evt;
	}
	
	/**
	 * Map a EtkREportKPIDetailsToVTPayment object to VTPayment object.
	 *
	 * @param reportEvt the report evt
	 * @return the VT payment
	 */
	private static VTPayment mapTicketPaymentKPIDetailsToVTPayment(TicketPaymentKPIDetails reportEvt) {
		VTPayment vp = new VTPayment();
		vp.setCountNumber(Integer.parseInt(reportEvt.getCOUNT_NBR()));
		vp.setPaymentAmount(BigDecimal.valueOf(reportEvt.getPAYMENT_AMT()));
		vp.setPaymentCardType(reportEvt.getPAYMENT_CARD_TYPE_TXT());
		vp.setPaymentTicketTypeCode(mapFromEnumTicketType(reportEvt.getPAYMENT_TICKET_TYPE_CD()));
		vp.setTicketNumber(reportEvt.getTICKET_NO());
		vp.setTransactionId(reportEvt.getPAYMENT_TRANSACTION_ID());
		return vp;
	}
	
	/**
	 * Map from enum ticket type.
	 *
	 * @param ett the ett
	 * @return the payment ticket type code enum
	 */
	private static PaymentTicketTypeCodeEnum mapFromEnumTicketType(EnumTicketType ett) {
		PaymentTicketTypeCodeEnum pttcd = null;
		if (EnumTicketType.E.equals(ett)) {
			pttcd = PaymentTicketTypeCodeEnum.E;
		} 
		if (EnumTicketType.S.equals(ett)) {
			pttcd = PaymentTicketTypeCodeEnum.S;
		}
		return pttcd;
	}
	
	/**
	 * Map a TicketPaymentKPIDetails object to VTQuery object.
	 *
	 * @param reportEvt the report evt
	 * @return the VT query
	 */
	private static VTQuery mapTicketPaymentKPIDetailsToVTQuery(TicketPaymentKPIDetails reportEvt) {
		VTQuery ctq = new VTQuery();
		ctq.setTicketNumber(reportEvt.getTICKET_NO());
		return ctq;
	}
	
	
	
	/**
	 * Map a list of TicketDisputeKPIDetailsList objects to a list of Event object.
	 *
	 * @param disputeEvents the dispute events
	 * @return the list
	 */
	public static List<Event> mapFromTicketDisputeKPIDetailsList(List<TicketDisputeKPIDetails> disputeEvents) {
		List<Event> events = new ArrayList<Event>();
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
	public static Event mapFromTicketDisputeKPIDetails(TicketDisputeKPIDetails disputeEvents) {
		Event evt = new Event();
		evt.setEventVersion(Const.HUB_EVENT_VERSION);
		evt.setEventDateTime(DateUtil.LocalDateTimeToOffsetDateTime(DateUtil.StringToLocalDateTime(disputeEvents.getCreatedDT(), DateUtil.YYYY_MM_DD_HH_MM_SS)));
		evt.setEventId(disputeEvents.getEventID().intValue());
		evt.setEventType(EvtEventingUtil.mapFromEnumEventType(disputeEvents.getEvent_type_cd()));
		if (EnumEventType.DISPUTE.equals(disputeEvents.getEvent_type_cd())) {
			evt.setVtDispute(mapTicketDisputeKPIDetailsToVTDispute(disputeEvents));
		} else if (EnumEventType.DISPUTE_STATUS_UPDATE.equals(disputeEvents.getEvent_type_cd())) {
			evt.setVtDisputeStatusUpdate(mapTicketDisputeKPIDetailsToVTDisputeStatusUpdate(disputeEvents));
		}
		
		return evt;
	}
	
	
	/**
	 * Map from dispute finding KPI details list.
	 *
	 * @param disputeFindingEvents the dispute finding events
	 * @return the list
	 */
	public static List<Event> mapFromDisputeFindingKPIDetailsList(List<EtkDisputeFinding> disputeFindingEvents) {
		List<Event> events = new ArrayList<Event>();
		for (EtkDisputeFinding kpi : disputeFindingEvents) {
			events.add(mapFromDisputeFindingKPIDetails(kpi));
		}
		return events;
	}
	
	/**
	 * Map from dispute finding KPI details.
	 *
	 * @param disputeFinding the dispute finding
	 * @return the event
	 */
	private static Event mapFromDisputeFindingKPIDetails(EtkDisputeFinding disputeFinding) {
		Event evt = new Event();
		
		evt.setEventVersion(Const.HUB_EVENT_VERSION);
		evt.setEventDateTime(DateUtil.LocalDateTimeToOffsetDateTime(DateUtil.StringToLocalDateTime(disputeFinding.getAppearance_dt(), DateUtil.YYYY_MM_DD)));
		evt.setEventId(disputeFinding.getEvent_id().intValue());
		evt.setEventType(EvtEventingUtil.mapFromEnumEventType(EnumEventType.DISPUTE_FINDING));
		evt.setVtDisputeFinding(mapEtkDisputeFindingToVTDisputeFinding(disputeFinding));
		
		return evt;
	}
	
	/**
	 * Map etk dispute finding to VT dispute finding.
	 *
	 * @param disputeFinding the dispute finding
	 * @return the VT dispute finding
	 */
	private static VTDisputeFinding mapEtkDisputeFindingToVTDisputeFinding(EtkDisputeFinding disputeFinding) {
		VTDisputeFinding vtDisputeFinding = new VTDisputeFinding();
		vtDisputeFinding.setCountNumber(Integer.parseInt(EvtEnrichUtil.getCountNumber(disputeFinding.getContravention_no())));
		vtDisputeFinding.setFindingCode(disputeFinding.getFinding_cd());
		vtDisputeFinding.setFindingDate(disputeFinding.getAppearance_dt());
		vtDisputeFinding.setFindingDescription(disputeFinding.getFinding_desc());
		vtDisputeFinding.setTicketNumber(EvtEnrichUtil.getTicketNumber(disputeFinding.getContravention_no()));
		return vtDisputeFinding;
	}
	
	/**
	 * Map a TicketDisputeKPIDetails object to a VTDispute object.
	 *
	 * @param disputeEvents the dispute events
	 * @return the VT dispute
	 */
	private static VTDispute mapTicketDisputeKPIDetailsToVTDispute(TicketDisputeKPIDetails disputeEvents) {
		VTDispute vd = new VTDispute();
		vd.setCompressedSection(disputeEvents.getCompressedSection());
		vd.setCountActRegulation(disputeEvents.getCountActRegulation());
		vd.setCountNumber(Integer.parseInt(EvtEnrichUtil.getCountNumber(disputeEvents.getContraventionNO())));
		vd.setDisputeActionDate(disputeEvents.getDisputeActionDT());
		vd.setDisputeTypeCode(mapFromDisputeTypeStr(disputeEvents.getDisputeType()));
		vd.setTicketNumber(EvtEnrichUtil.getTicketNumber(disputeEvents.getContraventionNO()));
		return vd;
	}
	
	/**
	 * Map from dispute type str.
	 *
	 * @param disputeType the dispute type
	 * @return the dispute type code enum
	 */
	private static DisputeTypeCodeEnum mapFromDisputeTypeStr(String disputeType) {
		DisputeTypeCodeEnum dt = null;
		if ("A".equals(disputeType)) {
			dt = DisputeTypeCodeEnum.A;
		} 
		if ("F".equals(disputeType)) {
			dt = DisputeTypeCodeEnum.F;
		} 
		return dt;
	}
	
	/**
	 * Map a TicketDisputeKPIDetails object to a VTDisputeStatusUpdate object.
	 *
	 * @param disputeEvents the dispute events
	 * @return the VT dispute status update
	 */
	private static VTDisputeStatusUpdate mapTicketDisputeKPIDetailsToVTDisputeStatusUpdate(TicketDisputeKPIDetails disputeEvents) {
		VTDisputeStatusUpdate su = new VTDisputeStatusUpdate();
		su.setCountNumber(Integer.parseInt(EvtEnrichUtil.getCountNumber(disputeEvents.getContraventionNO())));
		su.setDisputeActionCode(mapFromDisputeActionCDStr(disputeEvents.getDisputeActionCD()));
		su.setDisputeActionDate(disputeEvents.getDisputeActionDT());
		su.setTicketNumber(EvtEnrichUtil.getTicketNumber(disputeEvents.getContraventionNO()));
		return su;
	}
	
	/**
	 * Map from dispute action CD str.
	 *
	 * @param disputeActionCD the dispute action CD
	 * @return the dispute action code enum
	 */
	private static DisputeActionCodeEnum mapFromDisputeActionCDStr(String disputeActionCD) {
		DisputeActionCodeEnum cd = null;
		if ("C".equals(disputeActionCD)) {
			cd = DisputeActionCodeEnum.C;
		}
		if ("P".equals(disputeActionCD)) {
			cd = DisputeActionCodeEnum.P;
		}
		return cd;
	}
	
	/**
	 * Map a EnumEventType to EventTypeEnum (used in eventing).
	 *
	 * @param etype the etype
	 * @return the event type enum
	 */
	public static EventTypeEnum mapFromEnumEventType(EnumEventType etype) {
		EventTypeEnum rtype = null;
		if (EnumEventType.ISSUANCE.equals(etype)) {
			rtype = EventTypeEnum.EVT_ISSUANCE;
		}
		if (EnumEventType.DISPUTE.equals(etype)) {
			rtype = EventTypeEnum.VT_DISPUTE;
		}
		if (EnumEventType.DISPUTE_STATUS_UPDATE.equals(etype)) {
			rtype = EventTypeEnum.VT_DISPUTE_STATUS_UPDATE;
		}
		if (EnumEventType.PAYMENT.equals(etype)) {
			rtype = EventTypeEnum.VT_PAYMENT;
		}
		if (EnumEventType.QUERY.equals(etype)) {
			rtype = EventTypeEnum.VT_QUERY;
		}
		if (EnumEventType.DISPUTE_FINDING.equals(etype)) {
			rtype = EventTypeEnum.VT_DISPUTE_FINDING;
		}
		return rtype;
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
