package bcgov.jh.etk.jhetkcommon.model;

import bcgov.jh.etk.jhetkcommon.annotation.deserializer.OffsetDateTimeDeserializer;
import bcgov.jh.etk.jhetkcommon.annotation.serializer.OffsetDateTimeSerializer;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.time.OffsetDateTime;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
public class Event {
    @JsonProperty("id")
    private String eventId;

    @JsonProperty("version")
    private String eventVersion;

    @JsonProperty("date_time")
    @JsonDeserialize(using = OffsetDateTimeDeserializer.class)
    @JsonSerialize(using = OffsetDateTimeSerializer.class)
    private OffsetDateTime eventDateTime;

    @JsonProperty("type")
    private String type;
}
