package csc435hw06;

import csc435hw06.jdbi.DataDAO;
import csc435hw06.jdbi.LocationDAO;
import csc435hw06.health.DatabaseHealthCheck;
import csc435hw06.health.TemplateHealthCheck;
import csc435hw06.resources.DataResource;
import csc435hw06.resources.LocationResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import csc435hw06.resources.HelloWorldResource;
import io.dropwizard.jdbi3.*;
import org.jdbi.v3.core.Jdbi;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {
    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
    }

    @Override
    public void run(HelloWorldConfiguration configuration,
                    Environment environment) {
        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );
        final TemplateHealthCheck healthCheck =
                new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        environment.jersey().register(resource);
        final JdbiFactory factory = new JdbiFactory();
        final Jdbi jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        final LocationDAO ldao = jdbi.onDemand(LocationDAO.class);
        final DataDAO ddao = jdbi.onDemand(DataDAO.class);
        environment.jersey().register(new LocationResource(ldao, jdbi));
        environment.jersey().register(new DataResource(ddao, ldao, jdbi));
        environment.healthChecks().register("health",
                new DatabaseHealthCheck(jdbi, configuration.getDataSourceFactory().getValidationQuery()));
    }

}
