package bcgov.jh.etk.jhetkcommon.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@Document("events")
public class EventEntity {
    private String ticketNumber;
    private Date eventDate;
    private String eventTypeCode;

    public EventEntity(String ticketNumber, Date eventDate, String eventTypeCode) {
        this.ticketNumber = ticketNumber;
        this.eventDate = eventDate;
        this.eventTypeCode = eventTypeCode;
    }
}
