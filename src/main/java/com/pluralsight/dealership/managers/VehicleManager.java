package com.pluralsight.dealership.managers;

import javax.sql.DataSource;

public class VehicleManager {

    DataSource dataSource;

    public VehicleManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }
}
