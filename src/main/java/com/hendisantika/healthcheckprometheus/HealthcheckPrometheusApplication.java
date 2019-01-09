package com.hendisantika.healthcheckprometheus;

import com.github.strengthened.prometheus.healthchecks.HealthChecksCollector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HealthcheckPrometheusApplication {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(HealthcheckPrometheusApplication.class, args);
        HealthChecksCollector healthchecksMetrics = HealthChecksCollector.Builder.of().setGaugeMetricName("naruto_health_check").build();
        healthchecksMetrics.addHealthCheck("database", new DbHealthCheck());

        new HealthCheckExporter(healthchecksMetrics).export();
    }

}

