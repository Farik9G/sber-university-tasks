package ru.meeral.task16.database;

import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Component;


@Component
public class DataSource extends DriverManagerDataSource {

    public DataSource() {
        this.setUrl(DataConnection.URL);
        this.setUsername(DataConnection.USERNAME);
        this.setPassword(DataConnection.PASSWORD);
    }

}
