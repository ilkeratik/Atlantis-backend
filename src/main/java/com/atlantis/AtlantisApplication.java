package com.atlantis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories
public class AtlantisApplication {

    public static void main(String[] args) {
        SpringApplication.run(AtlantisApplication.class, args);
    }

}
