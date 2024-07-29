package bcgov.jh.etk.jhetkcommon.util.icbc;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import java.lang.String;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import bcgov.jh.etk.jhetkcommon.model.icbc.evt.mcot.BoolYN;
import bcgov.jh.etk.jhetkcommon.model.icbc.evt.mcot.CreateContraventionRequest;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * The Class IcbcUtils.
 */
@Service
public class ICBCUtil {

	/**
	 * The Constant log.
	 */
	private static final Logger log = LoggerFactory.getLogger(ICBCUtil.class);

	/** The resource. */
	@Value("classpath:/schema/ICBC/RSI_InformationManager.xsd") 
	Resource resource;
	
	/**
	 * Gets the ICBC schema.
	 *
	 * @return the ICBC schema
	 * @throws IOException 
	 */
	public InputStream getICBCSchema() throws IOException {
		return resource.getInputStream();
	}

	/**
	 * Gets the resource.
	 *
	 * @return the resource
	 */
	public Resource getResource() {
		return resource;
	}
	
	/**
	 * Format date.
	 *
	 * @param date String date to be converted
	 * @return Return date converted to GregorianCalendar
	 */
	public static XMLGregorianCalendar formatDate(String date) {
		if (StringUtils.isEmpty(date)) {
			return null;
		}
		GregorianCalendar c = new GregorianCalendar();
		try {
			Date formatedDate = new SimpleDateFormat("yyyy-MM-dd").parse(date);
			c.setTime(formatedDate);

			XMLGregorianCalendar cal = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
			//Timestamps aren't allowed at ICBC endpoint
			cal.setMillisecond(DatatypeConstants.FIELD_UNDEFINED);
			cal.setTimezone(DatatypeConstants.FIELD_UNDEFINED);
			cal.setMinute(DatatypeConstants.FIELD_UNDEFINED);
			cal.setHour(DatatypeConstants.FIELD_UNDEFINED);
			cal.setSecond(DatatypeConstants.FIELD_UNDEFINED);
			return cal;
		} catch (Exception e) {
			log.error(e.toString());
			return null;
		}

	}

	/**
	 * Gets the bool enum from bool str.
	 *
	 * @param boolStr String boolean value
	 * @return Enum value coresponding to bool
	 */
	public static BoolYN getBoolEnumFromBoolStr(String boolStr) {
		if (!StringUtils.hasText(boolStr)) {
			return null;
		} else if ("Y".equals(boolStr)) {
			return BoolYN.Y;
		} else {
			return BoolYN.N;
		}
	}

	/**
	 * Trim string.
	 *
	 * @param value String to be checked
	 * @return Trimmed String in cases of empty space, blank or null a null value is returned
	 */
	public static String trimString(String value) {
		if (!StringUtils.hasText(value)) {
			return null;
		}
		return value.trim();

	}

	/**
	 * Object to xml string.
	 *
	 * @param objectToBeConverted Convert an object into xml
	 * @return String xml of object
	 * @throws JAXBException
	 */
	public static String objectToXmlString(Object objectToBeConverted) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(CreateContraventionRequest.class);
		Marshaller m = context.createMarshaller();

		m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		StringWriter sw = new StringWriter();
		m.marshal(objectToBeConverted, sw);
		return sw.toString();
	}

	public static String removeNulls(String xmlStr) throws ParserConfigurationException, IOException, SAXException, TransformerException {

		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document document = builder.parse(new InputSource(new StringReader(xmlStr)));
		removeEmptyNodes(document);

		Transformer transformer = TransformerFactory.newInstance().newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		StreamResult result = new StreamResult(new StringWriter());
		transformer.transform(new DOMSource(document), result);
		return result.getWriter().toString();
	}

	public static void removeEmptyNodes(Node node) {

		NodeList list = node.getChildNodes();
		List<Node> nodesToRecursivelyCall = new LinkedList<Node>();

		for (int i = 0; i < list.getLength(); i++) {
			nodesToRecursivelyCall.add(list.item(i));
		}

		for (Node tempNode : nodesToRecursivelyCall) {
			removeEmptyNodes(tempNode);
		}

		String nodeValue = node.getNodeValue();
		boolean emptyElement = node.getNodeType() == Node.ELEMENT_NODE && node.getChildNodes().getLength() == 0;
		boolean emptyText = node.getNodeType() == Node.TEXT_NODE && !StringUtils.hasText(nodeValue);

		// Encode node value if not null or empty
		/* This check is not required, successfully tested with the logic commented out.
		if (nodeValue != null && !emptyText)  {
			String escaped= escapeXml10(nodeValue);
			if (nodeValue.length() != escaped.length()) {
				node.setNodeValue(escaped);
			}
		}*/
		// remove empty elements and look for nil attribute, remove if found
		if (emptyElement || emptyText) {
			if (!node.hasAttributes()) {
				node.getParentNode().removeChild(node);
			} else {
				NamedNodeMap nodeMap = node.getAttributes();
				for (int i = 0; i < nodeMap.getLength(); i++) {
					if (nodeMap.item(i).getNodeName().equals("xsi:nil") && nodeMap.item(i).getNodeValue().equals("true")) {
						node.getParentNode().removeChild(node);
					}
				}
			}
		}
	}
	
	/**
	 * Gets the file name.
	 *
	 * @param fileFullName the file full name
	 * @return the file name
	 */
	public static String getFileName(final String fileFullName) {
		String fileShortName = fileFullName;
		
		if (!StringUtils.isEmpty(fileFullName)) {
			String[] fnParts = fileFullName.split("/");
			if (fnParts != null && fnParts.length > 0) {
				fileShortName = fnParts[fnParts.length - 1];
			}
		}
		return fileShortName;
	}
}