package bcgov.jh.etk.paymentsvc.controller;

import bcgov.jh.etk.jhetkcommon.controller.BaseController;
import bcgov.jh.etk.jhetkcommon.exception.EtkDataAccessException;
import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.PathConst;
import bcgov.jh.etk.jhetkcommon.model.PaymentEventLog;
import bcgov.jh.etk.jhetkcommon.model.BusinessEventLog;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterface;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumBusinessEventLogEndpoints;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTicketType;
import bcgov.jh.etk.jhetkcommon.model.eventing.Event.EventTypeEnum;
import bcgov.jh.etk.jhetkcommon.model.payment.InvoiceSearchResponse_ICBC_MCOT;
import bcgov.jh.etk.jhetkcommon.model.payment.PaymentReceiptRequest_ICBC;
import bcgov.jh.etk.jhetkcommon.model.payment.ref.Contravention;
import bcgov.jh.etk.jhetkcommon.model.payment.ref.Vehicle;
import bcgov.jh.etk.jhetkcommon.service.EtkRestService;
import bcgov.jh.etk.jhetkcommon.service.impl.ErrorService;
import bcgov.jh.etk.jhetkcommon.service.impl.EtkService;
import bcgov.jh.etk.jhetkcommon.service.impl.WorkerService;
import bcgov.jh.etk.jhetkcommon.util.DateUtil;
import bcgov.jh.etk.jhetkcommon.util.EvtEnrichUtil;
import bcgov.jh.etk.jhetkcommon.util.LogUtil;
import bcgov.jh.etk.jhetkcommon.util.StringUtil;
import bcgov.jh.etk.jhetkcommon.model.paymentsvc.IndividualInvoiceResponse_paybc;
import bcgov.jh.etk.jhetkcommon.model.paymentsvc.InvoiceSearchResponse_ICBC;
import bcgov.jh.etk.jhetkcommon.model.paymentsvc.PaymentReceiptRequest_paybc;
import bcgov.jh.etk.jhetkcommon.model.paymentsvc.PaymentReceiptResponse_paybc;
import bcgov.jh.etk.jhetkcommon.model.paymentsvc.ref.Item;
import bcgov.jh.etk.jhetkcommon.model.paymentsvc.ref.Items;
import bcgov.jh.etk.jhetkcommon.model.paymentsvc.ref.keyValue;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static bcgov.jh.etk.jhetkcommon.model.PathConst.PATH_PING_REQUEST;

/**
 * The Class PaymentRestController.
 */
@RestController
public class PaymentRestController extends BaseController {
	/** The log. */
	private static Logger log = LoggerFactory.getLogger(PaymentRestController.class);

	/** The paybc individual invoice endpoint url prefix. */
	@Value("${paybc_individual_invoice_endpoint_url_prefix}")
    private String PAYBC_INDIVIDUAL_INVOICE_ENDPOINT_URL_PREFIX;

	/** The icbc payment service uri. */
	@Value("${icbc.payment.service.uri}")
	private String ICBC_PAYMENT_SERVICE_URI;

	@Value("${icbc.payment.service.username}")
	private String ICBC_PAYMENT_SERVICE_USERNAME;

	@Value("${icbc.payment.service.password}")
	private String ICBC_PAYMENT_SERVICE_PASSWORD;

	/** The etk service. */
	@Autowired
	EtkService etkService;

	/** The rest service. */
    @Autowired
    EtkRestService restService;

    /** The error service. */
	@Autowired
	ErrorService errorService;

	/** The helper service. */
	@Autowired
	WorkerService helperService;

    /**  Ticket number provided is not found. */
    private final static String ICBC_PAYMENT_MESSAGE_CODE_TICKET_NO_FOUND = "101";

    /** The Constant ICBC_PAYMENT_MESSAGE_CODE_ZERO_OUTSTANDING. */
    private final static String ICBC_PAYMENT_MESSAGE_CODE_ZERO_OUTSTANDING = "102";

    /** The Constant ICBC_PAYMENT_MESSAGE_CODE_TICKET_NOT_PAYABLE. */
    private final static String ICBC_PAYMENT_MESSAGE_CODE_TICKET_NOT_PAYABLE = "103";

    /** ICBC Service is Not Available. */
    private final static String ICBC_PAYMENT_MESSAGE_CODE_SYSTEM_ERROR = "201";

	private static final String PAYMENT_CONTROLLER_ICBC_INVOICE_SEARCH_HELPER = "PaymentController.icbcInvoiceSearchHelper";

    @Value("${spring.application.name}")
	private String componentName;

	@Value("${pod.name}")
	private String podName;

    /**
     * Home.
     *
     * @return the string
     */
    @GetMapping(PATH_PING_REQUEST)
    public String home(){
        return "{\"message\" : \"Welcome to Paymentsvc!\"}";
    }

	/**
	 * Individual ticket query from ICBC.
	 *
	 * @param contraventionNum the contravention number
	 * @return the response entity
	 */
	@RequestMapping(method = RequestMethod.GET,value = PathConst.PATH_INDIVIDUAL_TICKET_QUERY_V2 + "/{id}", produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody ResponseEntity<String> individualTicketQueryToICBC(@PathVariable("id") String contraventionNum) {
		//check the current ICBC_QT interface state, simply return 'Service not available' if it's STOPPED
		if (helperService.isInterfaceStopped(EnumInterface.ICBC_QT)) {
			log.debug("Ticket query interface is STOPPED, return 'Service not available'");
			//prepare response
			String responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_SYSTEM_ERROR);
			return new ResponseEntity<> (responseString, HttpStatus.SERVICE_UNAVAILABLE);
		}

		log.debug("Individual ticket query from ICBC, contraventionNum: {}", contraventionNum);
		String ticketNum = "";
		contraventionNum = contraventionNum.toUpperCase();
		ticketNum = EvtEnrichUtil.getTicketNumber(contraventionNum);

		return ticketQueryWrapper(ticketNum, contraventionNum, null, true, "PaymentController.individualTicketQueryToICBC");
	}

	/**
	 * Ticket query from ICBC.
	 *
	 * @param ticketNum the ticket num
	 * @param prn the prn
	 * @param time the time
	 * @return the search invoice from ICBC
	 */
	@RequestMapping(method = RequestMethod.GET, value = PathConst.PATH_TICKET_QUERY_V2, produces = "application/json")
	@ResponseStatus(code = HttpStatus.OK)
	public @ResponseBody ResponseEntity<String> ticketQueryToICBC(@RequestParam("in") String ticketNum,@RequestParam("prn") String prn,@RequestParam("time") String time) {
		//check the current ICBC_QT interface state, simply return 'Service not available' if it's STOPPED
		if (helperService.isInterfaceStopped(EnumInterface.ICBC_QT)) {
			log.debug("Ticket query interface is STOPPED, return 'Service not available'");
			//prepare response
			String responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_SYSTEM_ERROR);
			return new ResponseEntity<> (responseString, HttpStatus.SERVICE_UNAVAILABLE);
		}

		// Log sankey diagram friendly log
		BusinessEventLog logDetail = new BusinessEventLog(EventTypeEnum.VT_QUERY, ticketNum,
				componentName, EnumBusinessEventLogEndpoints.PAYBC.getCodeValue(), EnumBusinessEventLogEndpoints.ICBCADAPTER.getCodeValue(), podName);
		LogUtil.info(log, logDetail);

		log.debug("Ticket query from PayBC, ticketNum: {}, time: {}", ticketNum, time);
		String responseString = "";

	    //validate the time, and convert it to hh:mm format
		String validatedTime = DateUtil.validateTime(time);
		if (validatedTime == null) {
			log.debug("Time [{}] is invalid", time);
			responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_TICKET_NO_FOUND);
			return new ResponseEntity<> (responseString, HttpStatus.OK);
		}

		return ticketQueryWrapper(ticketNum.toUpperCase(), null, validatedTime, false, "PaymentController.ticketQueryToICBC");
	}

	/**
	 * Icbc invoice search helper.
	 *
	 * @param ticketNumber the ticket number
	 * @param contraventionNumber the contravention number
	 * @param time the time
	 * @param individualInvoiceSearch the individual invoice search
	 * @return the response entity
	 * @throws EtkDataAccessException the etk data access exception
	 */
	private ResponseEntity<String> icbcInvoiceSearchHelper(String ticketNumber, final String contraventionNumber, final String time,
			final boolean individualInvoiceSearch) {
		HttpStatusCode responseCode = HttpStatus.OK;
		String responseString = "";
		try {
			ticketNumber = URLEncoder.encode(ticketNumber, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			String errorDetails = "Exception occurred while doing URLEncode " + ticketNumber + "; error details: " + e.toString() + "; " + e.getMessage();
			log.error(errorDetails);
			return new ResponseEntity<> (getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_TICKET_NO_FOUND), HttpStatus.BAD_REQUEST);
		}

		String url = ICBC_PAYMENT_SERVICE_URI + PathConst.PATH_TICKET_QUERY_ICBC + "/" + ticketNumber;

		EnumInterface ICBC_QT = EnumInterface.ICBC_QT;

		log.debug("Send ticket query [ticket number: {}, contraventionNumber: {}, time: {}] to ICBC: {}", ticketNumber, contraventionNumber, time, url);

		ResponseEntity<String> response;
		String errorDetails;
		try {
			response = restService.secureRestfulExchange(url, null, HttpMethod.GET, ICBC_PAYMENT_SERVICE_USERNAME, ICBC_PAYMENT_SERVICE_PASSWORD, MediaType.APPLICATION_JSON);
		} catch (HttpStatusCodeException | UnknownHttpStatusCodeException e) {
			responseCode = e.getStatusCode();

			// If the errorCode is 404, return 'ticket not found' response
			if (responseCode == HttpStatus.NOT_FOUND) {
				log.debug("Ticket not found");
				return new ResponseEntity<> (getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_TICKET_NO_FOUND), HttpStatus.OK);
			}

			// only raise an error if it's client exception (except 404 which is used by ICBC for ticket not found)
			if (!(e instanceof HttpServerErrorException)) {
				errorDetails = "Exception occurred while sending ticket query to " + url + ", exception details: " + e.toString() + "; " + e.getMessage();
				errorService.saveError(contraventionNumber, ticketNumber, EnumErrorCode.Q00, PAYMENT_CONTROLLER_ICBC_INVOICE_SEARCH_HELPER, errorDetails, null, null, null, null, true);
			}
			return new ResponseEntity<> (getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_SYSTEM_ERROR), HttpStatus.SERVICE_UNAVAILABLE);
		}

        assert response != null;
        responseCode = response.getStatusCode();
		//success, process the response from ICBC
		if (HttpStatus.OK == responseCode || HttpStatus.CREATED == responseCode) {
			//update ICBC_QT interfaces to RUNNING
			try {
				etkService.UpdateInterface(ICBC_QT, EnumInterfaceState.RUNNING, Const.CONST_JH_ETK);
			} catch (EtkDataAccessException e) {
				errorDetails = "Failed to update ICBC_QT to RUNNING; error details: " + e.toString() + "; " + e.getMessage();
    			log.error(errorDetails);
    			errorService.saveError(contraventionNumber, ticketNumber, EnumErrorCode.Q00, PAYMENT_CONTROLLER_ICBC_INVOICE_SEARCH_HELPER, errorDetails, null, null, null, null, true);
			}

			InvoiceSearchResponse_ICBC_MCOT icbcResponse = null;
	        if (StringUtils.isNotBlank(response.getBody())) {
            	log.debug("Ticket query to ICBC succeeded: {}", response.getBody());
            	// convert JSON string to object
            	ObjectMapper objectMapper = new ObjectMapper();
            	try {
        			icbcResponse = objectMapper.readValue(response.getBody(), InvoiceSearchResponse_ICBC_MCOT.class);
        		} catch (Exception e) {
        			log.error("Failed to convert ticket query result to object, error: {}", e.toString() + "; " + e.getMessage());
        		}
            } else {
            	log.debug("Ticket query to ICBC error, nothing returned");
            }

	        // ICBC returns 200 or 201 statusCode, but with empty body, do the following:
	        // 1. Return 'Ticket not found' error
	        if (icbcResponse == null) {
	        	responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_TICKET_NO_FOUND);
	        	return new ResponseEntity<> (responseString, HttpStatus.OK);
	        }

	        boolean timeMatch = false;
	        //if doing individual invoice search or ticket search and time provided match what ICBC returns, match true to timeMatch
	        if (time == null || DateUtil.compareViolationTime(icbcResponse.getViolationDate(), time)) {
	        	timeMatch = true;
	        }

			log.debug("ICBC response violation date: {}", icbcResponse.getViolationDate());

	        if (timeMatch) {
	        	// individual invoice search
	        	if (individualInvoiceSearch) {
	        		//loop through the list of ICBC returned Contraventions,
	        		//find the one where the contravention number matches the passed incontraventionNumber,
	        		//and map it to InvoiceSearchResponse_ICBC object
	        		InvoiceSearchResponse_ICBC returnResponse = null;
	        		for (Contravention contravention : icbcResponse.getContraventions()) {
	        			if (contraventionNumber.equals(contravention.getContraventionNumber())) {
	        				returnResponse = buildICBCResponse(contravention, icbcResponse);
	        				responseString = StringUtil.objectToJsonString(mapIcbcResponseToPaybcResponce(returnResponse, icbcResponse));
	        				break;
	        			}
	        		}
	        		// contravention not found, prepare ticket not found response
	        		if (returnResponse == null) {
	        			responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_TICKET_NO_FOUND);
	        		}
	        	// invoice search
	        	} else {
	        		//save a query event to db
	        		try {
	        			etkService.RecordQueryEvent(ticketNumber);
	        		} catch (EtkDataAccessException e) {
	        			errorDetails = "Exception occurred while record query event, details: " + e.toString() + "; " + e.getMessage();
	        			log.error(errorDetails);
	        			errorService.saveError(contraventionNumber, ticketNumber, EnumErrorCode.Q21, PAYMENT_CONTROLLER_ICBC_INVOICE_SEARCH_HELPER, errorDetails, null, null, null, null, true);
	        		}

	        		// init false to found102, found103, found100
	        		boolean found102 = false;
	        		boolean found103 = false;
	        		boolean found100 = false;

	        		ArrayList<Item> tmpItems = new ArrayList<Item>();
	        		for (Contravention contravention : icbcResponse.getContraventions()) {
	        			if ("100".equals(contravention.getStatusCode())) {
	        				found100 = true;
	        				Item tmpItem = new Item();
	        				keyValue kv = new keyValue();
	        				// kv.set$ref(PAYBC_INDIVIDUAL_INVOICE_ENDPOINT_URL_PREFIX + PathConst.PATH_INDIVIDUAL_TICKET_QUERY + "/" + contravention.getContraventionNumber());
							kv.set$ref(PAYBC_INDIVIDUAL_INVOICE_ENDPOINT_URL_PREFIX + PathConst.PATH_INDIVIDUAL_TICKET_QUERY_V2 + "/" + contravention.getContraventionNumber());
	        				tmpItem.setSelected_invoice(kv);
	        				tmpItems.add(tmpItem);
	        			} else if ("102".equals(contravention.getStatusCode())) {
	        				found102 = true;
	        			} else if ("103".equals(contravention.getStatusCode())) {
	        				found103 = true;
	        			} else {
	        				log.error("ICBC returns an unexpected statusCode: {}", contravention.getStatusCode());
	        			}
	        		}

	        		// payable ticket found, return it to paybc
	        		if (found100) {
	        			Items items = new Items();
	        			items.setItems(tmpItems);
	        			responseString = StringUtil.objectToJsonString(items);
	        		// all the contraventions in the ticket are in the zero outstanding status (102),
	        		// Hub returns "No payment required for this traffic violation ticket." to PayBC
	        		} else if (!found100 && !found103 && found102) {
	        			responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_ZERO_OUTSTANDING);
	        		// all the contraventions in the ticket are either in 103 or 102.
	        		// If at least one of the contraventions is 103, Hub returns "the ticket not payable message" to PayBC.
	        		} else if (!found100 && found103) {
	        			responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_TICKET_NOT_PAYABLE);
	        		// unexpected statusCode combination, log it, and return service not available message back to paybc
	        		} else {
	        			log.error("Cannot derive message for paybc, unexpected statusCode combination: found 100: {}; found 102: {}; found 103: {}", found100, found102, found103);
	        			responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_SYSTEM_ERROR);
	        		}
	        		log.debug("Found 100: {}; found 102: {}; found 103: {}. Ticket query returns: {}", found100, found102, found103, responseString);
	        	}
	        } else {
	        	// time doesn't match, prepare ticket not found response
				responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_TICKET_NO_FOUND);
	        }
		} else {
			// bad response...
            log.error("Ticket query to ICBC failed, status: {}, response: {}", response.getStatusCode(), response.getBody());
			log.debug("Service not available error");

			//prepare response
			responseString = getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_SYSTEM_ERROR);

			//update ICBC_QT interfaces to FAILED
			try {
				etkService.UpdateInterface(ICBC_QT, EnumInterfaceState.FAILED, Const.CONST_JH_ETK);
			} catch (EtkDataAccessException e) {
				errorDetails = "Failed to update ICBC_QT to FAILED; error details: " + e.toString() + "; " + e.getMessage();
    			log.error(errorDetails);
    			errorService.saveError(contraventionNumber, ticketNumber, EnumErrorCode.Q00, PAYMENT_CONTROLLER_ICBC_INVOICE_SEARCH_HELPER, errorDetails, null, null, null, null, true);
			}

			//error handling
			log.error("Error occurred while doing ticket query to ICBC");
			errorDetails = String.format("HTTP status: %s \r\n HTTP Body: %s", response.getStatusCode().value(), response.getBody());
			errorService.saveError(contraventionNumber, ticketNumber, EnumErrorCode.Q11, PAYMENT_CONTROLLER_ICBC_INVOICE_SEARCH_HELPER, errorDetails, null, null, null, null, true);

		}
		return new ResponseEntity<> (responseString, responseCode);
	}

	/**
	 * Ticket query wrapper.
	 *
	 * @param ticketNum the ticket number
	 * @param contraventionNum the contravention number
	 * @param time the time
	 * @param individualInvoiceSearch the individual invoice search
	 * @param errorSource the error source
	 * @return the response entity
	 */
	private ResponseEntity<String> ticketQueryWrapper(String ticketNum, final String contraventionNum, final String time,
			final boolean individualInvoiceSearch, final String errorSource) {
		EnumErrorCode errorCode = null;
	    String errorDetails = null;
		try {
			return icbcInvoiceSearchHelper(ticketNum, contraventionNum, time, individualInvoiceSearch);
		} catch (ResourceAccessException e) {
			//ICBC is not available, don't raise a Hub error; let PayBC automatically retry.
			errorDetails = "Cannot reach " + ICBC_PAYMENT_SERVICE_URI + PathConst.PATH_TICKET_QUERY_ICBC + "/" + ticketNum + ". Exception details: " + e.toString() + "; " + e.getMessage();;
		} catch (HttpStatusCodeException e) {
			errorDetails = "Exception occurred while querying ticket to icbcadapter, httpStatusCode: " + e.getStatusCode().value() + "; details: " + e.toString() + "; " + e.getMessage();
			// proper http status code returned
			// if the exception is an instance of HttpClientErrorException, raise an error;
			// otherwise, don't raise any error
			if (e instanceof HttpClientErrorException) {
				errorCode = EnumErrorCode.Q00;
			}
		} catch (UnknownHttpStatusCodeException e) {
			// unknown http status code returned; raise a general error
			errorDetails = "UnknownHttpStatusCodeException occurred while querying ticket to icbcadapter: " +  e.toString() + "; " + e.getMessage();
			errorCode = EnumErrorCode.Q00;
		} finally {
			if (errorDetails != null) {
		    	log.error(errorDetails);
		    	try {
					etkService.UpdateInterface(EnumInterface.ICBC_QT, EnumInterfaceState.FAILED, Const.CONST_JH_ETK);
				} catch (Exception e) {
					String error = "DB exception occurred while changing ICBC_QT to FAILED, details: " + e.toString() + "; " + e.getMessage();
					log.error(error);
					errorService.saveError(contraventionNum, ticketNum, EnumErrorCode.Q00, "PaymentController.ticketQueryWrapper", error, null, null, null, null, true);
				}
		    }
		    if (errorCode != null) {
		    	errorService.saveError(contraventionNum, ticketNum, errorCode, errorSource, errorDetails, null, null, null, null, true);
		    }
		}

		return new ResponseEntity<> (getPaybcPaymentMessage(ICBC_PAYMENT_MESSAGE_CODE_SYSTEM_ERROR), HttpStatus.SERVICE_UNAVAILABLE);
	}

	/**
	 * Builds the ICBC response.
	 *
	 * @param contravention the contravention
	 * @param icbcResponse the icbc response
	 * @return the invoice search response ICBC
	 */
	@NotNull
	private InvoiceSearchResponse_ICBC buildICBCResponse(Contravention contravention, InvoiceSearchResponse_ICBC_MCOT icbcResponse) {
		InvoiceSearchResponse_ICBC returnResponse = new InvoiceSearchResponse_ICBC();
		returnResponse.setAmountDue(contravention.getAmountDue());
		returnResponse.setDiscountedAmount(contravention.getDiscountedAmount());
		returnResponse.setFineAmount(contravention.getFineAmount());
		returnResponse.setOffenseDescription(contravention.getOffenseDescription());
		returnResponse.setServiceDate(icbcResponse.getServiceDate());
		returnResponse.setStatusCode(contravention.getStatusCode());
		returnResponse.setTicketNumber(contravention.getContraventionNumber());
		returnResponse.setViolationDate(icbcResponse.getViolationDate());
		Vehicle vehicle = new Vehicle();
		vehicle.setMake(icbcResponse.getVehicle() == null ? null : icbcResponse.getVehicle().getMake());
		vehicle.setModel(icbcResponse.getVehicle() == null ? null : icbcResponse.getVehicle().getModel());
		returnResponse.setVehicle(vehicle);

		returnResponse.setAct(contravention.getAct());
		returnResponse.setSectionNumber(contravention.getSectionNumber());
		return returnResponse;
	}

	/**
	 * Map icbc response to paybc responce.
	 *
	 * @param returnResponse the return response
	 * @param icbcResponse the icbc response
	 * @return the individual invoice response paybc
	 */
	private IndividualInvoiceResponse_paybc mapIcbcResponseToPaybcResponce(InvoiceSearchResponse_ICBC returnResponse, InvoiceSearchResponse_ICBC_MCOT icbcResponse) {
		// init Paybc response consts
		String pbc_ref_number = "10006";
		String party_number = "n/a";
		String party_name = "n/a";
		String account_number = "n/a";
		String site_number = "0";
		String cust_trx_type = "Electronic Violation Ticket";

		//map icbcResponse to paybcResponce
		IndividualInvoiceResponse_paybc paybcResponse = new IndividualInvoiceResponse_paybc();
		paybcResponse.setAccount_number(account_number);
		paybcResponse.setAmount_due(returnResponse.getAmountDue());
		paybcResponse.setAttribute1(returnResponse.getOffenseDescription());
		paybcResponse.setAttribute2(returnResponse.getVehicle().getMake() + returnResponse.getVehicle().getModel());
		//remove the time portion
		paybcResponse.setAttribute3(DateUtil.DateStringToDateString(icbcResponse.getViolationDate(), DateUtil.PATTERN_ICBC_DATE_TIME, DateUtil.YYYY_MM_DD));
		paybcResponse.setAttribute4(mapDiscountedAmountToAttribute4(returnResponse.getDiscountedAmount()));
		paybcResponse.setCust_trx_type(cust_trx_type);
		paybcResponse.setInvoice_number(returnResponse.getTicketNumber());
		paybcResponse.setParty_name(party_name);
		paybcResponse.setParty_surname(icbcResponse.getParty() == null ? null:icbcResponse.getParty().getSurname());
		paybcResponse.setParty_given_name1(icbcResponse.getParty() == null ? null:icbcResponse.getParty().getGivenName1());
		paybcResponse.setParty_given_name2(icbcResponse.getParty() == null ? null:icbcResponse.getParty().getGivenName2());
		paybcResponse.setParty_number(party_number);
		paybcResponse.setPbc_ref_number(pbc_ref_number);
		paybcResponse.setSite_number(site_number);
		paybcResponse.setTerm_due_date(icbcResponse.getViolationDate());
		paybcResponse.setTotal(returnResponse.getFineAmount());
		paybcResponse.setEvt_form_number(icbcResponse.getFormNumber());

		paybcResponse.setAct(returnResponse.getAct());
		paybcResponse.setSection_number(returnResponse.getSectionNumber());

		return paybcResponse;
	}

	/**
	 * Gets the paybc payment message.
	 *
	 * @param icbcPaymentMessageCode the icbc payment message code
	 * @return the paybc payment message
	 */
	private String getPaybcPaymentMessage (final String icbcPaymentMessageCode) {
		// time doesn't match, prepare ticket not found response
		try {
			Map<String, String> paymentCodeMap = etkService.GetIcbcPaymentCode(icbcPaymentMessageCode);
			String message = paymentCodeMap.get(Const.KEY_PAYMENT_MESSAGE_DSC);
			return String.format("{\"error\": \"%s\"}", message);
		} catch (EtkDataAccessException e) {
			log.error("Fail to get ICBC payment code[{}], error: {}", icbcPaymentMessageCode, e.getMessage());
		}
		return null;
	}

	/**
	 * Map discounted amount to attribute 4.
	 *
	 * @param discountedAmount the discounted amount
	 * @return the string
	 */
	private String mapDiscountedAmountToAttribute4(double discountedAmount) {
		String attribute4 = null;
		if (discountedAmount == 0.0) {
			attribute4 = "n/a";
		} else {
			NumberFormat numberFormatter = new DecimalFormat("##.00");
			attribute4 = numberFormatter.format(discountedAmount);
		}
		return attribute4;
	}
}
