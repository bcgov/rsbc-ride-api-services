package bcgov.jh.etk.jhetkcommon.model.issuance;

import java.io.StringReader;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import lombok.Setter;
import lombok.Getter;

@Setter
@Getter
public class CanonicalFile {

	private String canonicalXML;
	private String sourceXML;
	private String filename;
	private Integer ticketID;

	public CanonicalFile(String canonicalXML) {
		this.canonicalXML = canonicalXML;
	}

	public EvtCanonical getCanonical() throws JAXBException {
		return toObject(getCanonicalXML());
	}
	
	private EvtCanonical toObject(String xml) throws JAXBException {
		JAXBContext context = JAXBContext.newInstance(EvtCanonical.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		StringReader reader = new StringReader(xml);
		EvtCanonical evtCanonical = (EvtCanonical) unmarshaller.unmarshal(reader);
		evtCanonical.setFilename(filename);
		evtCanonical.setPrimeFileXml(sourceXML);
				
		return evtCanonical;
	}

}
