package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EntityScan({"org.example"})
@EnableMongoRepositories(value = "org.example")
@EnableTransactionManagement
@EnableMongoAuditing
@EnableScheduling
@SpringBootApplication
@ComponentScan(basePackages = {"org.example"})
public class AppApiServer
{
    public static void main( String[] args )
    {
        SpringApplication.run(AppApiServer.class);
    }
}
