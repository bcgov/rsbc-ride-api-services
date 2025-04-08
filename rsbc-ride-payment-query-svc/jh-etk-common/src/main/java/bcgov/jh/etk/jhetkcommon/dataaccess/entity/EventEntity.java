package bcgov.jh.etk.jhetkcommon.dataaccess.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@Document("events")
public class EventEntity {
    @Id
    private String id;
    private String ticketNumber;
    private Date eventDate;
    private String eventTypeCode;

    public EventEntity() {
    }

    public EventEntity(String id, String ticketNumber, Date eventDate, String eventTypeCode) {
        this.id = id;
        this.ticketNumber = ticketNumber;
        this.eventDate = eventDate;
        this.eventTypeCode = eventTypeCode;
    }
}
