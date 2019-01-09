package com.hendisantika.healthcheckprometheus;

import com.github.strengthened.prometheus.healthchecks.HealthCheck;
import com.github.strengthened.prometheus.healthchecks.HealthStatus;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 * Project : healthcheck-prometheus
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 2019-01-10
 * Time: 05:45
 * To change this template use File | Settings | File Templates.
 */
class DbHealthCheck extends HealthCheck {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    @Override
    public HealthStatus check() {
        return checkDbConnection() ? HealthStatus.HEALTHY : HealthStatus.UNHEALTHY;
    }

    private boolean checkDbConnection() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://mysql-container:3306/prometheus?useSSL=false", "root", "root")) {
            logger.info("Database connected!");
            return true;
        } catch (SQLException e) {
            logger.info("Can not connect the database!");
            return false;
        }
    }
}