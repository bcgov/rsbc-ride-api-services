package bcgov.jh.etk.jhetkcommon.model.eventing;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EventWrapper {

	/** The events. */
	private List<Event> events;
}
