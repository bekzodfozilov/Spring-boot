package com.example.spring_book_project.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.*;

@Configuration
public class DBConfig {
    @Bean
    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "998090816");
            return connection;
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Bean
    public Statement getStatement() {
        try {
            return this.getConnection().createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
