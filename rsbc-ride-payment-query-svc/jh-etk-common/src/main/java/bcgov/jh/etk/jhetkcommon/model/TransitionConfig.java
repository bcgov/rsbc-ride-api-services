package bcgov.jh.etk.jhetkcommon.model;

import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTransitionMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new transition config.
 */
@NoArgsConstructor

/**
 * Instantiates a new transition config.
 *
 * @param userId the user id
 * @param currentHubState the current hub state
 * @param newHubState the new hub state
 * @param canTransition the can transition
 * @param canEmail the can email
 * @param mode the mode
 * @param emailSubject the email subject
 * @param emailBody the email body
 */
@AllArgsConstructor

/**
 * Gets the email body.
 *
 * @return the email body
 */
@Getter

/**
 * Sets the email body.
 *
 * @param emailBody the new email body
 */
@Setter
public class TransitionConfig {
	
	/** The user id. */
	private String userId;
	
	/** The current hub state. */
	private EnumInterfaceState currentHubState;
	
	/** The new hub state. */
	private EnumInterfaceState newHubState;
	
	/** The can transition. */
	private boolean canTransition = false;
	
	/** The can email. */
	private boolean canEmail = false;
	
	/** The mode. */
	private EnumTransitionMode mode;
	
	/** The email subject. */
	private String emailSubject;
	
	/** The email body. */
	private String emailBody;
}
