package com.finalproject.authorizationserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class DataSourceConfig {

    private DataSource dataSource;

    @Autowired
    public DataSourceConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

}
