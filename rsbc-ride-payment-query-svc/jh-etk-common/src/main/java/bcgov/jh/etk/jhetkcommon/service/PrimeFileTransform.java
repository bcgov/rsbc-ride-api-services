package bcgov.jh.etk.jhetkcommon.service;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import bcgov.jh.etk.jhetkcommon.model.Const;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumErrorCode;
import bcgov.jh.etk.jhetkcommon.service.impl.ErrorService;
import bcgov.jh.etk.jhetkcommon.model.issuance.CanonicalFile;

/**
 * The Class PrimeFileTransform.
 */
@Service
public class PrimeFileTransform {

	/** The logger. */
	private final Logger logger = LoggerFactory.getLogger(PrimeFileTransform.class);
	
	/** The transformer MCOT. */
	@Autowired
	private Transformer transformerMCOT;

	/** The transformer OCOT. */
	@Autowired
	private Transformer transformerOCOT;
	
	/** The error service. */
	@Autowired
	private ErrorService errorService;

	
	/**
	 * Transform to canonical model, raise an error if encountered.
	 *
	 * @param ticketID the ticket id
	 * @param sourceXML the source XML
	 * @param fileName the file name
	 * @param primeXMLVersion the prime XML version
	 * @return the canonical file
	 */
	public CanonicalFile transformToCanonicalModel(Integer ticketID, String sourceXML, String fileName, String primeXMLVersion) {
		return transformToCanonicalModel(ticketID, sourceXML, fileName, primeXMLVersion, false);
	}
	
	/**
	 * Transform to canonical model.
	 *
	 * @param ticketID the ticket id
	 * @param sourceXML the source XML
	 * @param fileName the file name
	 * @param primeXMLVersion the prime XML version
	 * @param silent whether or not raise an error
	 * @return the canonical file
	 */
	public CanonicalFile transformToCanonicalModel(Integer ticketID, String sourceXML, String fileName, String primeXMLVersion, boolean silent) {
		logger.debug("Transform to canonical starts, sourceXML: {}, fileName: {}, primeXMLVersion: {}", sourceXML, fileName, primeXMLVersion);
		String errorDetails = null;
		try {
			String canonicalXML = transform(sourceXML, primeXMLVersion);
			CanonicalFile canonicalFile = new CanonicalFile(canonicalXML);
			canonicalFile.setSourceXML(sourceXML);
			canonicalFile.setFilename(fileName);
			return canonicalFile;
		} catch (Exception e) {
			//raise an I01 error
			errorDetails = "Failed to transform evtXML to canonicalXML for file " + fileName + ", it's primeXMLVersion: " + primeXMLVersion + ", exception: " + e.toString() + "; " + e.getMessage();

			logger.error(errorDetails);
			if (!silent) {
				//raise an I01 error
				errorDetails = "Failed to transform evtXML to canonicalXML for file " + fileName + ", it's primeXMLVersion: " + primeXMLVersion + ", exception: " + e.toString() + "; " + e.getMessage();
				logger.error(errorDetails);
				errorService.saveError(ticketID, EnumErrorCode.I01, "PrimeFileTransform.transformToCanonicalModel", errorDetails, Const.CONST_JH_ETK, null, null, sourceXML, true);
			}
		}
		return null;
	}
	
	/**
	 * Transform.
	 *
	 * @param primeFile the prime file
	 * @param sourceXML the source XML
	 * @param primeXMLVersion the prime XML version
	 * @return the string
	 * @throws TransformerException the transformer exception
	 * @throws ParserConfigurationException the parser configuration exception
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private String transform(String sourceXML, String primeXMLVersion) throws TransformerException, ParserConfigurationException, SAXException, IOException {
		if (!StringUtils.isBlank(sourceXML)) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(new InputSource(new StringReader(sourceXML)));
			DOMSource source = new DOMSource(document);

		    StringWriter outWriter = new StringWriter();
		    StreamResult result = new StreamResult(outWriter);
			if (primeXMLVersion.equals("MCOT")) {
				transformerMCOT.transform(source, result);
			}
			else {
				transformerOCOT.transform(source, result);
			}
			return outWriter.getBuffer().toString();
		}
	    return null;
	}
}
