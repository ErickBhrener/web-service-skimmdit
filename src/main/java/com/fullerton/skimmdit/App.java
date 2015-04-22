package com.fullerton.skimmdit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import org.skife.jdbi.v2.DBI;
import io.dropwizard.jdbi.DBIFactory;
import com.fullerton.skimmdit.resources.UserResource;
import com.fullerton.skimmdit.resources.LinkResource;
import io.dropwizard.auth.basic.BasicAuthProvider;
import io.dropwizard.auth.basic.BasicCredentials;
import io.dropwizard.auth.CachingAuthenticator;

public class App extends Application<SkimmditConfiguration> {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);
    private static final String URL = "jdbc:hsqldb:hsql://localhost/cpsc476";
    private static final String USER = "SA";
    private static final String PASS = "Passw0rd";

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public void initialize(Bootstrap<SkimmditConfiguration> b) {
    }

    @Override
    public void run(SkimmditConfiguration c, Environment e) throws Exception {
        LOGGER.info("Method App#run() called");
        for (int i = 0; i < c.getMessageRepetitions(); i++) {
            System.out.println(c.getMessage());
        }
    
        System.out.println(c.getAdditionalMessage());

        // Create a DBI factory and build a JDBI instance
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = new DBI(URL,USER,PASS);//factory
                //.build(e, hsqlConfig, "hsql");
        // Add the resource to the environment
        e.jersey().register(new BasicAuthProvider<Boolean>(
        new SkimmditAuthenticator(jdbi), "Web Service Realm"));
        e.jersey().register(new UserResource(jdbi));
        e.jersey().register(new LinkResource(jdbi));
    }
}
