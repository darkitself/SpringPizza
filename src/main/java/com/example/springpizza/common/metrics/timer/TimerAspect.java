package com.example.springpizza.common.metrics.timer;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Timer;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
@Component
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TimerAspect {

    Map<String, Timer> timers;
    MeterRegistry meterRegistry;

    public TimerAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.timers = new ConcurrentHashMap<>();
    }

    @Around("@annotation(TimerMetric)")
    public Object incrementCount(ProceedingJoinPoint jp) {
        var method = ((MethodSignature) jp.getSignature()).getMethod();
        String metricName = method.getAnnotation(TimerMetric.class).name();
        return timers.computeIfAbsent(metricName, meterRegistry::timer).record(() -> {
            try {
                return jp.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
    }
}
