package bcgov.jh.etk.jhetkcommon.exception;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;


/**
 * The Class CustomAsyncExceptionHandler.
 */
public class CustomAsyncExceptionHandler implements AsyncUncaughtExceptionHandler {
	/**
     * The logger.
     */
    private static Logger logger = LoggerFactory.getLogger(CustomAsyncExceptionHandler.class);
    
	/**
	 * Handle uncaught exception.
	 *
	 * @param throwable the throwable
	 * @param method the method
	 * @param obj the obj
	 */
	@Override
	public void handleUncaughtException(Throwable throwable, Method method, Object... obj) {
		logger.debug("Exception message - " + throwable.getMessage());
		logger.debug("Method name - " + method.getName());
        for (final Object param : obj) {
        	logger.debug("Param - " + param);
        }
	
	}
}
