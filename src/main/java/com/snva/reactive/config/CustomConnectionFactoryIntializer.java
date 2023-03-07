package com.snva.reactive.config;

import io.r2dbc.spi.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.r2dbc.connection.init.CompositeDatabasePopulator;
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer;
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator;

@org.springframework.web.reactive.config.EnableWebFlux
@org.springframework.boot.autoconfigure.SpringBootApplication
public class CustomConnectionFactoryIntializer {
    @Bean
    ConnectionFactoryInitializer initializer(ConnectionFactory connectionFactory) {

        ConnectionFactoryInitializer initializer = new ConnectionFactoryInitializer();
        initializer.setConnectionFactory(connectionFactory);
        initializer.setDatabasePopulator(new ResourceDatabasePopulator(new ClassPathResource("schema.sql")));

        return initializer;
    }

    @Bean
    public org.springframework.boot.CommandLineRunner demo(com.snva.reactive.respository.UserRepository repository, com.snva.reactive.respository.DepartmentRepository departmentRepository) {

        return (args) -> {
            // save a few customers
            repository.saveAll(java.util.Arrays.asList(new com.snva.reactive.model.User(1,"Dheeraj", 1,1),
                            new com.snva.reactive.model.User(2,"Dheeraj1", 1,1),
                            new com.snva.reactive.model.User(3,"Dheeraj2", 1,1),
                            new com.snva.reactive.model.User(4,"Dheeraj3", 1,1),
                            new com.snva.reactive.model.User(5,"Dheeraj4", 1,1)));


            // fetch all customers
        };
    }
}
