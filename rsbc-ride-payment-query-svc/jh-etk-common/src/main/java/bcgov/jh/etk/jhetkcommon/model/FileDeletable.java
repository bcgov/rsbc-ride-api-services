package bcgov.jh.etk.jhetkcommon.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Sets the re queue rule.
 *
 * @param reQueueRule the new re queue rule
 */
@Setter

/**
 * Gets the re queue rule.
 *
 * @return the re queue rule
 */
@Getter
public class FileDeletable {
	
	/** The deletable. */
	private boolean deletable;
	
	/** The is deletable rule. */
	private String isDeletableRule;
	
	/** The can re queue. */
	private boolean canReQueue;
	
	/** The re queue rule. */
	private String reQueueRule;
	
}
