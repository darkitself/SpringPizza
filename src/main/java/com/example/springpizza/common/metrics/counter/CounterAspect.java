package com.example.springpizza.common.metrics.counter;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
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
public class CounterAspect {

    Map<String, Counter> counters;
    MeterRegistry meterRegistry;

    public CounterAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.counters = new ConcurrentHashMap<>();
    }

    @Around("@annotation(CounterMetric)")
    public Object incrementCount(ProceedingJoinPoint jp) {
        Object result;
        var method = ((MethodSignature) jp.getSignature()).getMethod();
        String metricName = method.getAnnotation(CounterMetric.class).name();
        try {
            result = jp.proceed();
        } catch (Throwable ex) {
            throw new RuntimeException(ex);
        } finally {
            counters.computeIfAbsent(metricName, meterRegistry::counter).increment();
        }
        return result;
    }
}
