package bcgov.jh.etk.jhetkcommon.util;

import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;

/**
 * The Class XmlUtils.
 */
public class XmlUtils {
	
	
	/**
	 * Validate XML.
	 *
	 * @param xml the xml
	 * @param xsd the xsd
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void validateXML(String xml, String xsd) throws SAXException, IOException {
		validateXML(new StreamSource(new StringReader(xml)), new StreamSource(new StringReader(xsd)));
	}

	/**
	 * Validate XML.
	 *
	 * @param xml the xml
	 * @param xsd the xsd
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void validateXML(String xml, File xsd) throws SAXException, IOException {
		validateXML(new StreamSource(new StringReader(xml)), new StreamSource(xsd));
	}
	
	/**
	 * Validate XML.
	 *
	 * @param xml the xml
	 * @param xsd the xsd
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void validateXML(String xml, InputStream xsd) throws SAXException, IOException {
		validateXML(new StreamSource(new StringReader(xml)), new StreamSource(xsd));
	}

	/**
	 * Validate XML.
	 *
	 * @param xml the xml
	 * @param xsd the xsd
	 * @throws SAXException the SAX exception
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static void validateXML(Source xml, Source xsd) throws SAXException, IOException {
		SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
		Schema schema = schemaFactory.newSchema(xsd);
		Validator validator = schema.newValidator();
		validator.validate(xml);
	}

	/**
	 * Unmarshal.
	 *
	 * @param <T> the generic type
	 * @param xml the xml
	 * @param objectClass the object class
	 * @return the t
	 * @throws JAXBException the JAXB exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T unmarshal(String xml, Class<T> objectClass) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(objectClass);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		return (T) unmarshaller.unmarshal(new StringReader(xml));
	}
}
