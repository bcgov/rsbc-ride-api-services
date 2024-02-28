package bcgov.jh.etk.jhetkcommon.util;

import java.util.List;

import org.slf4j.Logger;

import com.splunk.logging.HttpEventCollectorErrorHandler;
import com.splunk.logging.HttpEventCollectorEventInfo;

import bcgov.jh.etk.jhetkcommon.model.BusinessEventLog;
import bcgov.jh.etk.jhetkcommon.model.IssuanceEventLog;
import bcgov.jh.etk.jhetkcommon.model.PaymentEventLog;

/**
 * The Class LogUtil.
 */
public class LogUtil {
	
	/**
	 * Info.
	 *
	 * @param logger the logger
	 * @param logDetail the log detail
	 */
	public static void info(Logger logger, IssuanceEventLog logDetail) {
		splunkLoggingErrorCallback();
		logger.info(StringUtil.objectToJsonString(logDetail));
	}
	
	/**
	 * Error.
	 *
	 * @param logger the logger
	 * @param logDetail the log detail
	 */
	public static void error(Logger logger, IssuanceEventLog logDetail) {
		splunkLoggingErrorCallback();
		logger.error(StringUtil.objectToJsonString(logDetail));
	}
	
	/**
	 * Info.
	 *
	 * @param logger the logger
	 * @param logDetail the log detail
	 */
	public static void info(Logger logger, PaymentEventLog logDetail) {
		splunkLoggingErrorCallback();
		logger.info(StringUtil.objectToJsonString(logDetail));
	}
	
	/**
	 * Info.
	 *
	 * @param logger the logger
	 * @param logDetail the log detail
	 */
	public static void error(Logger logger, PaymentEventLog logDetail) {
		splunkLoggingErrorCallback();
		logger.info(StringUtil.objectToJsonString(logDetail));
	}
	
	/**
	 * Info
	 *
	 * @param logger the logger
	 * @param logDetail the log detail
	 */
	public static void info(Logger logger, BusinessEventLog logDetail) {
		// JHI-1979 Temporary disable the logging
		//logger.info(StringUtil.objectToJsonString(logDetail));
	}
	
	/**
	 * Debug.
	 *
	 * @param logger the logger
	 * @param logDetail the log detail
	 */
	public static void debug(Logger logger, BusinessEventLog logDetail) {
		// JHI-1979 Temporary disable the logging
		//logger.debug(StringUtil.objectToJsonString(logDetail));
	}
	
	/**
	 * Error.
	 *
	 * @param logger the logger
	 * @param logDetail the log detail
	 */
	public static void error(Logger logger, BusinessEventLog logDetail) {
		// JHI-1979 Temporary disable the logging
		//logger.error(StringUtil.objectToJsonString(logDetail));
	}
	
	
	/**
	 * Splunk logging error callback.
	 */
	private static void splunkLoggingErrorCallback() {
		//define error callback
        HttpEventCollectorErrorHandler.onError(new HttpEventCollectorErrorHandler.ErrorCallback() {
            public void error(final List<HttpEventCollectorEventInfo> data, final Exception ex) {
            	StringBuilder exceptionDetails = new StringBuilder();
            	if (data != null) {
	            	for (HttpEventCollectorEventInfo eventInfo : data) {
	            		exceptionDetails.append("Event message: " + eventInfo.getMessage() + "\n");
	            		exceptionDetails.append("Event time: " + eventInfo.getTime() + "\n");
	            		exceptionDetails.append("Event exception: " + eventInfo.getExceptionMessage() + "\n");
	            	}
            	}
            	exceptionDetails.append("Exception details: " + ex.getMessage() + "; " + ex.toString());
            	System.out.println("Exception occurred while sending event to splunk, exception details: " + exceptionDetails.toString());
            }
        });
	}
}
