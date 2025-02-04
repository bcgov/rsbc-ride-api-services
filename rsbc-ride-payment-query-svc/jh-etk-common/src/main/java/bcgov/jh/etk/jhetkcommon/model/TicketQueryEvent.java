package bcgov.jh.etk.jhetkcommon.model;

import bcgov.jh.etk.jhetkcommon.dataaccess.entity.EventEntity;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumEventType;
import com.fasterxml.jackson.annotation.*;
import lombok.Data;

import java.time.ZoneOffset;

@Data
public class TicketQueryEvent {
    @JsonProperty("ticket_number")
    private String ticketNumber;
    @JsonProperty("event")
    private Event event;

    public TicketQueryEvent(EventEntity eventEntity) {
        this.ticketNumber = eventEntity.getTicketNumber();
        this.event = new Event();
        event.setEventId(eventEntity.getId());
        event.setEventDateTime(eventEntity.getEventDate().toInstant().atOffset(ZoneOffset.UTC));
        event.setType(EnumEventType.VT_QUERY.getCodeValue().toLowerCase());
        event.setEventVersion(Const.HUB_EVENT_VERSION);
    }
}