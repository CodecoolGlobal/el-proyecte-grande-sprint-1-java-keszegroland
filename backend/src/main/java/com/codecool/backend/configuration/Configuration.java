package com.codecool.backend.configuration;

import com.codecool.backend.dao.DatabaseConnection;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Configuration {
    @Value("${strings.database.url}")
    private String databaseURL;
    @Value("${strings.database.username}")
    private String databaseUsername;
    @Value("${strings.database.userPassword}")
    private String databasePassword;

    @Bean
    public DatabaseConnection databaseConnection() {
        return new DatabaseConnection(databaseURL, databaseUsername, databasePassword);
    }

}
