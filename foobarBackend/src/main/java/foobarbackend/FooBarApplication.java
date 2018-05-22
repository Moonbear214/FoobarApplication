package foobarbackend;

import java.util.EnumSet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import foobarbackend.db.*;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import foobarbackend.health.TemplateHealthCheck;
import foobarbackend.resources.FooBarResource;
import foobarbackend.core.*;

public class FooBarApplication extends Application<FooBarConfiguration> {

    public static void main(final String[] args) throws Exception {
        new FooBarApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<FooBarConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final FooBarConfiguration configuration, final Environment environment) {
    	// Setup CORS
    	//=================================================================================================================
        // Enable CORS headers
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

        // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    	//==================================================================================================================
        
        
        // Health check
    	//==================================================================================================================
        final Template template = configuration.buildTemplate();
        environment.healthChecks().register("template", new TemplateHealthCheck(template));
        environment.jersey().register(new FooBarResource(template));
    	//==================================================================================================================
        

        // Start foobar repository
    	//==================================================================================================================
        new FooBarRepository();
    	//==================================================================================================================
    }
}
