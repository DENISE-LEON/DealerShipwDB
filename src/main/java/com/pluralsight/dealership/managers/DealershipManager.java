package com.pluralsight.dealership.managers;

import com.pluralsight.dealership.models.Dealership;
import com.pluralsight.dealership.models.Vehicle;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DealershipManager {
    DataSource dataSource;

    public DealershipManager(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Dealership> ViewAllDealerships() {
        List<Dealership> dealerships = new ArrayList<>();

        try (
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        SELECT *
                        FROM Dealerships;
                        """)
        ) {
            try (
                    ResultSet resultSet = preparedStatement.executeQuery()
            ) {
                addDealershipToList(resultSet, dealerships);
            }

        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());

        }
        return dealerships;
    }

    public Integer insertDealership(String name, String phoneNum, String address) {
        try (
                //step 1: connetction
                Connection connection = dataSource.getConnection();

                //step 2: prepare statement for desired query
                PreparedStatement preparedStatement = connection.prepareStatement("""
                        INSERT INTO Dealerships
                        VALUES(NULL, ?, ?, ?);
                        """, Statement.RETURN_GENERATED_KEYS)
        ) {
            //step 3 set the placeholders
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, phoneNum);
            preparedStatement.setString(3, address);

            //step 4: create var for rows affected to be later used for verification that query was successful
            //checks how many rows were affected
            int rows = preparedStatement.executeUpdate();

            //step 5: verification using the rows var
            if (rows == 0) {
                System.out.println("No rows inserted, shipper not added.");
            }

            try (
                    //get the keys which are later used to create the dealership(ID field)
                    ResultSet keys = preparedStatement.getGeneratedKeys();
            ) {
                if (keys.next()) {
                    //the column that contains the key
                    int newID = keys.getInt(1);

                    return newID;
                    //since auto increment is on, this piece is unecessary but realistically it is best practice to have safety net bc others will be manipulating db as well
                } else {
                    System.out.println("Insert succeeded but no ID was returned.");
                    return null;
                }

            }
        } catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
            return null;
        }
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

    public boolean removeDealership(String vinNum){
        try(
                Connection connection = dataSource.getConnection();

                PreparedStatement preparedStatement = connection.prepareStatement("""
                        DELETE
                        FROM Dealerships
                        WHERE DealershipID = ?;
                        """)
                ) {

            preparedStatement.setString(1, vinNum);
            int rows = preparedStatement.executeUpdate();


            if (rows == 0) {
                System.out.println("No rows inserted, shipper not added.");
                return false;
        }
            return true;

}catch (SQLException e) {
            System.out.println(e.getErrorCode() + " " + e.getMessage());
            return false;
        }
    }


}
