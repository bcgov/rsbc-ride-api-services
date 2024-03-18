package bcgov.jh.etk.jhetkcommon.annotation.deserializer;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class OffsetDateTimeDeserializer.
 */
public class OffsetDateTimeDeserializer extends StdDeserializer<OffsetDateTime> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2268845068915183534L;

	/**
	 * Instantiates a new offset date time deserializer.
	 */
	protected OffsetDateTimeDeserializer() {
		super(OffsetDateTime.class);
	}

	/**
	 * Deserialize.
	 *
	 * @param jp the jp
	 * @param ctxt the ctxt
	 * @return the offset date time
	 * @throws IOException Signals that an I/O exception has occurred.
	 * @throws JsonProcessingException the json processing exception
	 */
	@Override
	public OffsetDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String dateString = jp.readValueAs(String.class);
		if (StringUtils.isNoneBlank(dateString)) {
			LocalDateTime ldt = DateUtil.StringToLocalDateTime(dateString, DateUtil.PATTERN_LOCAL_DATE_TIME);
			OffsetDateTime odt = DateUtil.LocalDateTimeToOffsetDateTime(ldt);
			
			return odt;
		}
		return null;
	}

}
