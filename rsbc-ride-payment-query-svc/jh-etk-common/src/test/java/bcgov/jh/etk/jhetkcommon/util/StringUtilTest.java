package bcgov.jh.etk.jhetkcommon.util;

import bcgov.jh.etk.jhetkcommon.model.EtkDisputeFindings;
import bcgov.jh.etk.jhetkcommon.model.PrimeIssuanceReconRecord;
import bcgov.jh.etk.jhetkcommon.model.icbc.evt.mcot.AddressType;
import bcgov.jh.etk.jhetkcommon.model.icbc.evt.mcot.ObjectFactory;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import static bcgov.jh.etk.jhetkcommon.util.StringUtil.objectToJsonString;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import java.io.IOException;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class StringUtilTest {

	@Test
	public void objectToJsonStringTest() {
		ObjectFactory factory = new ObjectFactory();
		AddressType address = new AddressType();
		address.setCity(factory.createAddressTypeCity("Victoria"));
		address.setPostalCode(factory.createAddressTypePostalCode("1A12B2"));
		assertThat(objectToJsonString(address), containsString("Victoria"));
		assertThat(objectToJsonString(address), containsString("1A12B2"));
	}
	
	@Test
	public void jsonStringToObject() throws IOException {
		String jsonString = "{" + 
				"  \"disputeFindings\": [" + 
				"    {" + 
				"      \"justin_record_id\": \"ai1\"," + 
				"      \"contravention_no\": \"cn1\"," + 
				"      \"appearance_dt\": \"2020-01-09\"," + 
				"      \"finding_cd\": \"vdn1\"," + 
				"      \"finding_desc\": \"\"" + 
				"    }," + 
				"	{" + 
				"      \"justin_record_id\": \"ai2\"," + 
				"      \"contravention_no\": \"cn2\"," + 
				"      \"appearance_dt\": \"2020-01-19\"," + 
				"      \"finding_cd\": \"vdn2\"," + 
				"      \"finding_desc\": \"\"" + 
				"    }" + 
				"  ]," + 
				"  \"itemQuantity\": 2" + 
				"}";
		EtkDisputeFindings edf = (EtkDisputeFindings) StringUtil.jsonStringToObject(jsonString, EtkDisputeFindings.class);
		assertNotNull(edf);
	}
	
	@Test
	public void extractTicketNO () {
		String fileName = "tkea00017668_xs4e180amb.xml";
		String ticketNumber = PrimeIssuanceReconRecord.extractTicketNO(fileName);
		assertNotNull(ticketNumber);
	}
	
	@Test
	public void format() {
		DecimalFormat df = new DecimalFormat("0.00");
		df.setRoundingMode(RoundingMode.HALF_UP);
		String str = df.format(0.125);
		assertEquals("0.13", str);
	}
}