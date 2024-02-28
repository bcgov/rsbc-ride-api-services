package bcgov.jh.etk.jhetkcommon.annotation.serializer;

import java.io.IOException;
import java.time.OffsetDateTime;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import bcgov.jh.etk.jhetkcommon.util.DateUtil;

/**
 * The Class OffsetDateTimeSerializer.
 */
public class OffsetDateTimeSerializer extends StdSerializer<OffsetDateTime> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -8710906745962155955L;

	/**
	 * Instantiates a new offset date time serializer.
	 */
	protected OffsetDateTimeSerializer() {
		super(OffsetDateTime.class);
	}

	/**
	 * Serialize.
	 *
	 * @param value the value
	 * @param gen the gen
	 * @param sp the sp
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	@Override
	public void serialize(OffsetDateTime value, JsonGenerator gen, SerializerProvider sp) throws IOException {
		gen.writeString(DateUtil.OffsetDateTimeToLocalDateTimeString(value));
	}

}
