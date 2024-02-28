package bcgov.jh.etk.jhetkcommon.util;

import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static bcgov.jh.etk.jhetkcommon.util.XmlUtils.validateXML;
import static org.junit.Assert.*;

public class XmlUtilsTest {

	private String xsdStr = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
			"<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\">\n" +
			"\n" +
			"<xs:element name=\"shiporder\">\n" +
			"  <xs:complexType>\n" +
			"    <xs:sequence>\n" +
			"      <xs:element name=\"orderperson\" type=\"xs:string\"/>\n" +
			"      <xs:element name=\"shipto\">\n" +
			"        <xs:complexType>\n" +
			"          <xs:sequence>\n" +
			"            <xs:element name=\"name\" type=\"xs:string\"/>\n" +
			"            <xs:element name=\"address\" type=\"xs:string\"/>\n" +
			"            <xs:element name=\"city\" type=\"xs:string\"/>\n" +
			"            <xs:element name=\"country\" type=\"xs:string\"/>\n" +
			"          </xs:sequence>\n" +
			"        </xs:complexType>\n" +
			"      </xs:element>\n" +
			"      <xs:element name=\"item\" maxOccurs=\"unbounded\">\n" +
			"        <xs:complexType>\n" +
			"          <xs:sequence>\n" +
			"            <xs:element name=\"title\" type=\"xs:string\"/>\n" +
			"            <xs:element name=\"note\" type=\"xs:string\" minOccurs=\"0\"/>\n" +
			"            <xs:element name=\"quantity\" type=\"xs:positiveInteger\"/>\n" +
			"            <xs:element name=\"price\" type=\"xs:decimal\"/>\n" +
			"          </xs:sequence>\n" +
			"        </xs:complexType>\n" +
			"      </xs:element>\n" +
			"    </xs:sequence>\n" +
			"    <xs:attribute name=\"orderid\" type=\"xs:string\" use=\"required\"/>\n" +
			"  </xs:complexType>\n" +
			"</xs:element>\n" +
			"\n" +
			"</xs:schema>";
		private String validXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"\n" +
				"<shiporder orderid=\"889923\"\n" +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
				"xsi:noNamespaceSchemaLocation=\"shiporder.xsd\">\n" +
				"  <orderperson>John Smith</orderperson>\n" +
				"  <shipto>\n" +
				"    <name>Ola Nordmann</name>\n" +
				"    <address>Langgt 23</address>\n" +
				"    <city>4000 Stavanger</city>\n" +
				"    <country>Norway</country>\n" +
				"  </shipto>\n" +
				"  <item>\n" +
				"    <title>Empire Burlesque</title>\n" +
				"    <note>Special Edition</note>\n" +
				"    <quantity>1</quantity>\n" +
				"    <price>10.90</price>\n" +
				"  </item>\n" +
				"  <item>\n" +
				"    <title>Hide your heart</title>\n" +
				"    <quantity>1</quantity>\n" +
				"    <price>9.90</price>\n" +
				"  </item>\n" +
				"</shiporder>";

		private String invalidXML = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
				"\n" +
				"<shiporder orderid=\"889923\"\n" +
				"xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
				"xsi:noNamespaceSchemaLocation=\"shiporder.xsd\">\n" +
				"  <orderperson>John Smith</orderperson>\n" +
				"  <shipto>\n" +
				"    <address>Langgt 23</address>\n" +
				"    <name>Ola Nordmann</name>\n" +
				"    <city>4000 Stavanger</city>\n" +
				"    <country>Norway</country>\n" +
				"  </shipto>\n" +
				"  <item>\n" +
				"    <title>Empire Burlesque</title>\n" +
				"    <note>Special Edition</note>\n" +
				"    <quantity>1</quantity>\n" +
				"    <price>10.90</price>\n" +
				"  </item>\n" +
				"  <item>\n" +
				"    <title>Hide your heart</title>\n" +
				"    <quantity>1</quantity>\n" +
				"    <price>9.90</price>\n" +
				"  </item>\n" +
				"</shiporder>";

	@Test
	public void validateXMLTest() throws IOException, SAXException {
		validateXML(validXML, xsdStr);
	}

	@Test(expected = SAXException.class)
	public void validateInvalidXMLTest() throws IOException, SAXException {
		validateXML(invalidXML, xsdStr);
	}
}