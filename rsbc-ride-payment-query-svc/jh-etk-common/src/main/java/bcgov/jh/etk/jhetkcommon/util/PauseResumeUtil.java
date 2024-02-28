package bcgov.jh.etk.jhetkcommon.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import bcgov.jh.etk.jhetkcommon.config.ApplicationProperties;
import bcgov.jh.etk.jhetkcommon.model.FTPFileInfo;
import bcgov.jh.etk.jhetkcommon.model.FileDeletable;
import bcgov.jh.etk.jhetkcommon.model.PathConst;
import bcgov.jh.etk.jhetkcommon.model.TransitionConfig;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumInterfaceState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumProcessState;
import bcgov.jh.etk.jhetkcommon.model.enums.EnumTransitionMode;
import bcgov.jh.etk.jhetkcommon.service.RestService;

/**
 * The Class PauseResumeUtil.
 */
public class PauseResumeUtil {
	/** The logger. */
	private static Logger logger = LoggerFactory.getLogger(PauseResumeUtil.class);
	
	/**
	 * Extract ticket number.
	 *
	 * @param fileContent the file content
	 * @return the string
	 */
	public static String extractTicketNumber (final String fileContent) {
		String ticketNumber = null;
		try {
			Pattern p = Pattern.compile(".*<TICKET_NUM>(.*?)<\\/TICKET_NUM>");
			Matcher m = p.matcher(fileContent);
			if (m.find()) {
				ticketNumber = m.group(1).trim();
			}
		} catch (Exception e) {
			logger.error("Exception occurred while extracting ticketNumber from ftp fileContent: {}", fileContent);
		}
		return ticketNumber;
	}
	
	/**
	 * Checks if is deletable.
	 *
	 * @param fileDeletable the file deletable
	 * @param ftpFileName the ftp file name
	 * @param dbFileName the db file name
	 * @param processState the process state
	 */
	public static void isDeletable(FileDeletable fileDeletable, FTPFileInfo fl) {
		boolean deletable = false;
		boolean reQueue = false;
		String deletableRule = null;
		String reQueueRule = null;
		
		EnumProcessState processState = fl.getProcessingState();
		
		//R1: new eVT
		if (EnumProcessState.NEW.equals(processState)) {
			//deletable = false;
			//deletableRule = "Ticket to be processed";
		//R2 and R7: ticket is purged; but file deletion failed
		} else if (EnumProcessState.PURGED.equals(processState)) {
			deletable = true;
			deletableRule = "Hub auto delete failed";
		//R3 and R5a: ticket is SENT or BYPASSED and file deletion failed
		} else if ((EnumProcessState.SENT.equals(processState) || EnumProcessState.BYPASSED.equals(processState))) {
			deletable = true;
			deletableRule = "Hub auto delete failed";
		//R4a ticket is in DATAERROR state
		} else if (EnumProcessState.DATAERROR.equals(processState)) {
			reQueue = true;
			reQueueRule = "Ticket contains data related error(s)";
		//R8: ticket is in FILEERROR, can delete and requeue if malformed eVT file or failed MRE schema validation
		} else if (EnumProcessState.FILEERROR.equals(processState)) {
			deletable = true;
			deletableRule = "Malformed eVT file or failed MRE schema validation";
			reQueue = true;
			reQueueRule = "Malformed eVT file or failed MRE schema validation";
		} else if (EnumProcessState.DUPLICATEERROR.equals(processState)) {
			deletable = true;
			deletableRule = "Duplicate ticket";
		}
		//If lastModifiedDT is not set, it means the file no longer exists, set deletable to false.
		if (fl.getLastModifiedDT() == null) {
			deletable = false;
			deletableRule = "File no longer exists";
		}
		fileDeletable.setCanReQueue(reQueue);
		fileDeletable.setDeletable(deletable);
		fileDeletable.setIsDeletableRule(deletableRule);
		fileDeletable.setReQueueRule(reQueueRule);
	}
	
	/**
	 * Checks if is transitionable.
	 *
	 * @param config the config
	 */
	public static void isTransitionable(TransitionConfig config, boolean isPostponed) {
		String manResumed = "has been manually resumed";
		String manStopped = "has been manually stopped";
		String autoResumed = "has been automatically resumed";
		String autoStopped = "has been automatically stopped";
		String autoPaused = "has been automatically paused";
		String scheduledManResume = "has been manually scheduled to RUNNING";
		String scheduledManStop = "has been manually scheduled to STOPPED";
		String transitionedBy = String.format("by %s", config.getUserId());
		
		//mannual mode, done through support console
		if (EnumTransitionMode.MAN.equals(config.getMode())) {
			if (EnumInterfaceState.STOPPED.equals(config.getCurrentHubState()) && EnumInterfaceState.RUNNING.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				String subjectSecondPart = manResumed;
				if(isPostponed) {
				    subjectSecondPart = scheduledManResume;
				}
				
				config.setEmailSubject(String.format("%s %s %s", config.getEmailSubject(), subjectSecondPart, transitionedBy));
			}
			if (EnumInterfaceState.RUNNING.equals(config.getCurrentHubState()) && EnumInterfaceState.STOPPED.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				String subjectSecondPart = manStopped;
				if(isPostponed) {
                    subjectSecondPart = scheduledManStop;
                }
				
				config.setEmailSubject(String.format("%s %s %s", config.getEmailSubject(), subjectSecondPart, transitionedBy));
			}
			if (EnumInterfaceState.PAUSED.equals(config.getCurrentHubState()) && EnumInterfaceState.STOPPED.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				config.setEmailSubject(String.format("%s %s %s", config.getEmailSubject(), manStopped, transitionedBy));
			}
			if (EnumInterfaceState.PAUSED.equals(config.getCurrentHubState()) && EnumInterfaceState.RUNNING.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				config.setEmailSubject(String.format("%s %s %s", config.getEmailSubject(), manResumed, transitionedBy));
			}
			if (EnumInterfaceState.FILEPAUSED.equals(config.getCurrentHubState()) && EnumInterfaceState.STOPPED.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				config.setEmailSubject(String.format("%s %s %s", config.getEmailSubject(), manStopped, transitionedBy));
			}
			if (EnumInterfaceState.FILEPAUSED.equals(config.getCurrentHubState()) && EnumInterfaceState.RUNNING.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				config.setEmailSubject(String.format("%s %s %s", config.getEmailSubject(), manResumed, transitionedBy));
			}
		}
		
		//auto mode, done either through process service, scheduled job
		if (EnumTransitionMode.AUTO.equals(config.getMode())) {
			if (EnumInterfaceState.RUNNING.equals(config.getCurrentHubState()) && EnumInterfaceState.STOPPED.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				config.setEmailSubject(String.format("%s %s", config.getEmailSubject(), autoStopped));
			}
			if (EnumInterfaceState.RESUMING.equals(config.getCurrentHubState()) && EnumInterfaceState.STOPPED.equals(config.getNewHubState())) {
				config.setCanTransition(true);
			}
			if (EnumInterfaceState.RUNNING.equals(config.getCurrentHubState()) && EnumInterfaceState.PAUSED.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				config.setEmailSubject(String.format("%s %s", config.getEmailSubject(), autoPaused));
			}
			if (EnumInterfaceState.RESUMING.equals(config.getCurrentHubState()) && EnumInterfaceState.PAUSED.equals(config.getNewHubState())) {
				config.setCanTransition(true);				
			}
			if (EnumInterfaceState.PAUSED.equals(config.getCurrentHubState()) && EnumInterfaceState.RESUMING.equals(config.getNewHubState())) {
				config.setCanTransition(true);
			}
			if (EnumInterfaceState.PAUSED.equals(config.getCurrentHubState()) && EnumInterfaceState.RUNNING.equals(config.getNewHubState())) {
				// this is valid case; when auto-resume occurs, and that there are no queued tickets (e.g., failure to access SFEG server JHI-851) 
				config.setCanTransition(true);
				config.setCanEmail(true);
				config.setEmailSubject(String.format("%s %s", config.getEmailSubject(), autoResumed));
			}
			if (EnumInterfaceState.FILEPAUSED.equals(config.getCurrentHubState()) && EnumInterfaceState.RESUMING.equals(config.getNewHubState())) {
				config.setCanTransition(true);
			}
			// this is a valid case, when issuance state is FILEPAUSED, auto-resume occurs, and there are no queued tickets
			if (EnumInterfaceState.FILEPAUSED.equals(config.getCurrentHubState()) && EnumInterfaceState.RUNNING.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				config.setEmailSubject(String.format("%s %s", config.getEmailSubject(), autoResumed));
			}
			if (EnumInterfaceState.RESUMING.equals(config.getCurrentHubState()) && EnumInterfaceState.RUNNING.equals(config.getNewHubState())) {
				config.setCanTransition(true);
				config.setCanEmail(true);
				config.setEmailSubject(String.format("%s %s", config.getEmailSubject(), autoResumed));
			}
		}
		// map emailSubject to emailBody
		config.setEmailBody(config.getEmailSubject());
	}
	
	/**
	 * Send simple message.
	 *
	 * @param emailSender the email sender
	 * @param subject the subject
	 * @param text the text
	 */
	public static void sendSimpleMessage(final JavaMailSender emailSender, final String subject, final String text) {
		logger.debug("Send a simple email message, to: {}, subject: {}, text: {}", ApplicationProperties.emailTo, subject, text);
    	try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(StringUtils.isEmpty(ApplicationProperties.emailTo) ? null : ApplicationProperties.emailTo.split(","));
            message.setSubject(subject);
            message.setText(text);
            message.setFrom(ApplicationProperties.emailFrom);

            emailSender.send(message);
            logger.debug("Send a simple email message done.");
        } catch (MailException exception) {
        	logger.error("Send a simple email message failed: {}", exception.getMessage());
        }
	}
	
	/**
	 * Write pause file.
	 *
	 * @param restService the rest service
	 */
	public static void WritePauseFile(final RestService restService) {
		if (!StringUtil.isPropertyValueConfigured(ApplicationProperties.FTP_FILE_MANAGEMENT_URI)) {
			return;
		}
		
		String url = ApplicationProperties.FTP_FILE_MANAGEMENT_URI + PathConst.PATH_PAUSE_FILE_WRITE;
		logger.info("Write pause file, send the request to this URL: {}", url);
		try {
	    	ResponseEntity<String> response = restService.restfulSave(url, null, HttpMethod.POST, MediaType.APPLICATION_JSON);

			//success, process the response from ICBC
			if (HttpStatus.OK.equals(response.getStatusCode()) || HttpStatus.CREATED.equals(response.getStatusCode())) {
				logger.debug("Pause file successfully written");
			}
		} catch (Exception e) {
			logger.error("Fail writing pause file, error message: {}", e.toString() + "; " + e.getMessage());
    	}
	}
}
