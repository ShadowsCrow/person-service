package com.person.person_service.config;

import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.config.MeterFilter;

import java.util.List;
import java.util.Arrays;

@Configuration
public class PrometheusMetricsConfig {

    // Lista de métricas que você deseja manter
    private final List<String> metricsToKeep = Arrays.asList("http.server.requests", "evento_valor_metric");

    // Define o customizador de registries que aplica o filtro
    @Bean
    public MeterRegistryCustomizer<MeterRegistry> meterRegistryCustomizer() {
        return registry -> registry.config().meterFilter(MeterFilter.denyUnless(id -> {
            boolean keepMetric = metricsToKeep.contains(id.getName());
            System.out.println("Filtering metric: " + id.getName() + " - " + (keepMetric ? "Allowed" : "Denied"));
            return keepMetric;
        }));
    }
}