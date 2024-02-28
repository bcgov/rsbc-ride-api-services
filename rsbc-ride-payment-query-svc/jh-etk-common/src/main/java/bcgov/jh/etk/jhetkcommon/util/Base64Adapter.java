package bcgov.jh.etk.jhetkcommon.util;


import java.nio.charset.Charset;
import java.util.Base64;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class Base64Adapter extends XmlAdapter<String, String> {

	@Override
	public String marshal(String arg0) throws Exception {
		return Base64.getEncoder().encodeToString(arg0.getBytes());
	}

	@Override
	public String unmarshal(String arg0) throws Exception {
		byte[] valueDecoded = Base64.getDecoder().decode(arg0);
		return new String(valueDecoded, Charset.forName("UTF-8"));
	}
}