package ca.bc.gov.ride.geocodersvc.metrics;


import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Metrics;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Slf4j
@Aspect
@Component
@Profile("!test")
public class CustomMetrics {

    /**
     * Collecting DataBC API Metrics
     * dataBcApiCounterMethod: This method is used to count the number of times the method is called.
     * dataBcApiErrorCounter: This method is used to count the number of times the method is called on error.
     * dataBcApiExecutionTimeMethod: This method is used to measure the execution time of the method.
     */
    private final Counter dataBcApiCounter = Metrics.counter("geocodersvc.dataBcApi.count");
    private final Counter dataBcApiErrorCounter = Metrics.counter("geocodersvc.dataBcApiError.count");
    private final TimeMetrics dataBcApiExecutionTime = new TimeMetrics("geocodersvc.dataBcApi.execution.time");

    @Pointcut("execution(* ca.bc.gov.ride.geocodersvc.domain.service.BCDataService.getBcDataApi(..))")
    public void dataBcApiCounterMethod() {}
    @Pointcut("execution(* ca.bc.gov.ride.geocodersvc.domain.service.BCDataService.getErrorResponse(..))")
    public void dataBcApiErrorCounterMethod() {}
    @After("dataBcApiCounterMethod()")
    public void dataBcApiCall(JoinPoint joinPoint) { countMethodCall(joinPoint, dataBcApiCounter); }
    @After("dataBcApiErrorCounterMethod()")
    public void dataBcApiError(JoinPoint joinPoint) { countMethodCall(joinPoint, dataBcApiErrorCounter); }
    @Around("execution(* ca.bc.gov.ride.geocodersvc.domain.service.BCDataService.getBcDataApi(..))")
    public Object dataBcApiExecutionTimeMethod(ProceedingJoinPoint joinPoint){ return measureExecutionTime(joinPoint, dataBcApiExecutionTime); }


    /**
     * Collecting Google API Metrics
     * googleApiCounterMethod: This method is used to count the number of times the method is called.
     * googleApiErrorCounter: This method is used to count the number of times the method is called on error.
     * googleApiExecutionTimeMethod: This method is used to measure the execution time of the method.
     */
    private final Counter googleApiCounter = Metrics.counter("geocodersvc.googleApi.count");
    private final Counter googleApiErrorCounter = Metrics.counter("geocodersvc.googleApiError.count");
    private final TimeMetrics googleApiExecutionTime = new TimeMetrics("geocodersvc.googleApi.execution.time");

    @Pointcut("execution(* ca.bc.gov.ride.geocodersvc.domain.service.GoogleService.getGoogleApi(..))")
    public void googleApiCounterMethod() {}
    @Pointcut("execution(* ca.bc.gov.ride.geocodersvc.domain.service.GoogleService.getErrorResponse(..))")
    public void googleApiErrorCounterMethod() {}
    @After("googleApiCounterMethod()")
    public void countGoogleApiCall(JoinPoint joinPoint) { countMethodCall(joinPoint, googleApiCounter);}
    @After("googleApiErrorCounterMethod()")
    public void googleApiError(JoinPoint joinPoint) { countMethodCall(joinPoint, googleApiErrorCounter); }
    @Around("execution(* ca.bc.gov.ride.geocodersvc.domain.service.GoogleService.getGoogleApi(..))")
    public Object googleApiExecutionTimeMethod(ProceedingJoinPoint joinPoint){ return measureExecutionTime(joinPoint, googleApiExecutionTime); }


    /**
     * countMethodCall: Method to count the number of times the method is called.
     * measureExecutionTime: Method to measure the execution time of the method.
     */
    public void countMethodCall(JoinPoint joinPoint, Counter methodCounter){
        methodCounter.increment();
        String className = joinPoint.getTarget().getClass().getSimpleName();
        String methodName = joinPoint.getSignature().getName();
        log.info("Method executed: " + className + "." + methodName);
    }

    private Object measureExecutionTime(ProceedingJoinPoint joinPoint, TimeMetrics timeMetrics){
        long startTime = System.currentTimeMillis();
        try {
            return joinPoint.proceed();
        } catch (Throwable e) {
            throw new RuntimeException(e);
        } finally {
            long executionTime = System.currentTimeMillis() - startTime;
            timeMetrics.recordTime(executionTime);
        }
    }
}
