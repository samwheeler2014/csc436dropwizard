package csc435hw06.health;

import com.codahale.metrics.health.HealthCheck;

import org.jdbi.v3.core.Jdbi;
import org.skife.jdbi.v2.DBI;
import org.skife.jdbi.v2.Handle;

import com.codahale.metrics.health.HealthCheck;

public class DatabaseHealthCheck extends HealthCheck {

    private final Jdbi dbi;
    private final String validationQuery;

    public DatabaseHealthCheck(Jdbi dbi, String validationQuery) {
        this.dbi = dbi;
        this.validationQuery = validationQuery;
    }

    @Override
    protected Result check() {

        try {
            final Handle handle = (Handle) dbi.open();

            handle.execute(validationQuery);

            handle.close();
        } catch (Exception e) {
            return Result.unhealthy("Database is not running!");
        }

        return Result.healthy();
    }
}