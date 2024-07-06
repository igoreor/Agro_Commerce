package com.example.agro_commerce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(System.getenv("DRIVE_CLASS_NAME" ));
        dataSource.setUrl("jURL_BANCO");
        dataSource.setUsername("USUARIO");
        dataSource.setPassword("SENHA");
        return dataSource;
    }
}
