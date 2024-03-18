package bcgov.jh.etk.paymentsvc.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.web.annotation.RestControllerEndpoint;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.integration.leader.Context;
import org.springframework.integration.leader.event.OnGrantedEvent;
import org.springframework.integration.leader.event.OnRevokedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.service.impl.WorkingDataService;

@Component
@RestControllerEndpoint(id = "leader")
public class LeaderController {

	/** The logger. */
    private final Logger log = LoggerFactory.getLogger(getClass());

    /** The host. */
	private final String host;

	/** The context. */
	private Context context;
	
	@Autowired
	WorkingDataService workingDataService;
	
	public LeaderController() throws UnknownHostException {
		this.host = InetAddress.getLocalHost().getHostName();
	}

	/**
	 * Return a message whether this instance is a leader or not.
	 * @return info
	 */
	@GetMapping
	public String getInfo() {
		if (this.context == null) {
			return String.format("I am '%s' but I am not a leader.", this.host);
		}

		return String.format("I am '%s' and I am the leader.", this.host);
	}

	/**
	 * PUT request to try and revoke a leadership of this instance. If the instance is not
	 * a leader, leadership cannot be revoked. Thus "HTTP Bad Request" response. If the
	 * instance is a leader, it must have a leadership context instance which can be used
	 * to give up the leadership.
	 * @return info about leadership
	 */
	@PutMapping
	public ResponseEntity<String> revokeLeadership() {
		if (this.context == null) {
			String message = String.format("Cannot revoke leadership because '%s' is not a leader", this.host);
			return ResponseEntity.badRequest().body(message);
		}

		this.context.yield();

		String message = String.format("Leadership revoked for '%s'", this.host);
		return ResponseEntity.ok(message);
	}

	/**
	 * Handle a notification that this instance has become a leader.
	 * @param event on granted event
	 */
	@EventListener
	public void handleEvent(OnGrantedEvent event) {
		log.debug(String.format("'%s' leadership granted", event.getRole() + "; podIP: " + ApplicationProperties.podIP + "; podName: " + ApplicationProperties.podName));
		this.context = event.getContext();
		
		//register the pod as leader
		workingDataService.registerAsLeader();
	}

	/**
	 * Handle a notification that this instance's leadership has been revoked.
	 * @param event on revoked event
	 */
	@EventListener
	public void handleEvent(OnRevokedEvent event) {
		log.debug(String.format("'%s' leadership revoked", event.getRole()));
		this.context = null;
	}

}
