package com.pluralsight.dealership.managers;

import com.pluralsight.dealership.models.Dealership;
import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipManager {
     DataSource dataSource;

    public DealershipManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dealership> ViewAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();

        try(
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT *
                        FROM Dealerships;
                        """)
                ) {
            try(
                    ResultSet resultSet = preparedStatement.executeQuery()
                    ) {
                addDealershipToList(resultSet, dealerships);
            }

        }catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());

        }
        return dealerships;
    }

    public void addDealershipToList(ResultSet resultSet, List<Dealership> list) throws SQLException {

        while (resultSet.next()) {
            int dealershipID = resultSet.getInt("DealershipID");
            String name = resultSet.getString("Name");
            String address = resultSet.getString("Address");
            String phone = resultSet.getString("Phone");
            Dealership dealership = new Dealership(dealershipID, name, address, phone);
            list.add(dealership);
        }
    }
}
