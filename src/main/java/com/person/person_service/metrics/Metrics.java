package com.person.person_service.metrics;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Counter;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.concurrent.atomic.AtomicInteger;

@Component
public class Metrics {

    private final MeterRegistry meterRegistry;
    private final AtomicInteger valorGauge;

    public Metrics(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
        this.valorGauge = new AtomicInteger(0);
    
        meterRegistry.gauge("evento_valor_metric", valorGauge);
    }

    public void addTag(String description) {

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();

        String normalizedUri = (String) request.getAttribute("normalizedUri");

        if (normalizedUri == null) {
            normalizedUri = request.getRequestURI();
        }

        String method = request.getMethod();
        String status = (response != null) ? String.valueOf(response.getStatus()) : "UNKNOWN"; 

    
        Counter.builder("http.server.requests")
            .tag("uri", normalizedUri) 
            .tag("method", method)
            .tag("status", status)
            .tag("application", "person-service")
            .tag("eventos", description)
            .register(meterRegistry)
            .increment();
    }

    public void captureValorMetrica(Integer valor) {
        valorGauge.set(valor);
    }
}
