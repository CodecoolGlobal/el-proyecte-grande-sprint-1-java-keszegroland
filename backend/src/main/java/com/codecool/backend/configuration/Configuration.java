package com.codecool.backend.configuration;

import com.codecool.backend.dao.UserDAO;
import com.codecool.backend.dao.UserDAOjdbc;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootConfiguration
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

    @Bean
    public UserDAO usersDaoJDBC(DatabaseConnection databaseConnection) {
        return new UserDAOjdbc(databaseConnection);
    }

}
