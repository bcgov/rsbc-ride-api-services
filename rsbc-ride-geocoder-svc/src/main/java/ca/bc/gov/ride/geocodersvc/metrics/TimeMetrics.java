package ca.bc.gov.ride.geocodersvc.metrics;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Timer;

import java.time.Duration;

public class TimeMetrics {
    private Timer timer;

    public TimeMetrics(String metricName) {
        this.timer = Metrics.timer(metricName);
    }

    public void recordTime(long startTime) {
        long elapsedTime = System.currentTimeMillis() - startTime;
        timer.record(Duration.ofMillis(elapsedTime));
    }
}
