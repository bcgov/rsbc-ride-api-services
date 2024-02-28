package bcgov.jh.etk.jhetkcommon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.service.impl.ErrorService;
import bcgov.jh.etk.jhetkcommon.util.XmlUtils;

/**
 * The Class PrimeFileValidation.
 */
@Service
public class PrimeFileValidation {

	/** The prime file schema MCOT. */
	@Value("classpath:/schema/Ticket_MCOT.xsd")
	private Resource primeFileSchemaMCOT;

	/** The prime file schema OCOT. */
	@Value("classpath:/schema/Ticket_OCOT.xsd")
	private Resource primeFileSchemaOCOT;

	@Autowired
	private ErrorService errorService;
	
	
	/**
	 * Validate and raise error if encountered
	 *
	 * @param ticketID the ticket id
	 * @param sourceXML the source XML
	 * @param fileName the file name
	 * @return the string
	 */
	public String validateAndRaiseError(Integer ticketID, String sourceXML, String fileName) {
		return validateAndRaiseError(ticketID, sourceXML, fileName, false);
	}
	
	/**
	 * Validate and raise error.
	 *
	 * @param ticketID the ticket ID
	 * @param sourceXML the source XML
	 * @param fileName the file name
	 * @param silent the silent
	 * @return the string
	 */
	public String validateAndRaiseError(Integer ticketID, String sourceXML, String fileName, boolean silent) {
		Boolean isMCOT = true;
		String exceptionText = "";
		String primeXMLVersion = null;
		try {
			XmlUtils.validateXML(sourceXML, primeFileSchemaMCOT.getInputStream());
			primeXMLVersion = "MCOT";
		}catch (Exception e) {
			isMCOT = false;
			exceptionText = "MCOT schema Validation Exception: " + e.toString() + "; " + e.getMessage();
		}
		if (!isMCOT) {
			try {
				XmlUtils.validateXML(sourceXML, primeFileSchemaOCOT.getInputStream());
				primeXMLVersion = "OCOT";
			}catch (Exception e) {
				exceptionText += "\r\nOCOT schema Validation Exception: " + e.toString() + "; " + e.getMessage();
				if (!silent) {
					//raise an I01 error
					errorService.saveError(ticketID, EnumErrorCode.I01, "PrimeFileValidation.validate", exceptionText, Const.CONST_JH_ETK, null, null, sourceXML, true);
				}
			}
		}
		
		return primeXMLVersion;
	}
}
