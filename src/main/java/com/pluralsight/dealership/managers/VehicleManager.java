package com.pluralsight.dealership.managers;

import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

public class VehicleManager {

    DataSource dataSource;

    public VehicleManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> viewAllVehicles(){
        List<Vehicle> vehicles = new ArrayList<>();
    }


}
