package bcgov.jh.etk.jhetkcommon.config;

import java.util.concurrent.Executor;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import bcgov.jh.etk.jhetkcommon.exception.CustomAsyncExceptionHandler;

/**
 * The Class AsyncConfig.
 * Enabling asynchronous processing
 * @author hliang
 */
@Configuration
@EnableAsync(proxyTargetClass=true)
@ComponentScan("bcgov.jh.etk.jhetkcommon.service")
public class AsyncConfig implements AsyncConfigurer{
	
	/**
	 * Thread pool task executor.
	 *
	 * @return the executor
	 */
	@Bean(name = "threadPoolTaskExecutor")
    public Executor threadPoolTaskExecutor() {
        return new ThreadPoolTaskExecutor();
    }

    /**
     * Gets the async executor.
     *
     * @return the async executor
     */
    @Override
    public Executor getAsyncExecutor() {
        return new SimpleAsyncTaskExecutor();
    }
    
	/**
	 * Gets the async uncaught exception handler.
	 *
	 * @return the async uncaught exception handler
	 */
	@Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return new CustomAsyncExceptionHandler();
    }
}
