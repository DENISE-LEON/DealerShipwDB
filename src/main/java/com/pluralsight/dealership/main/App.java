package com.pluralsight.dealership.main;

import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;

public class App {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Two arguments are needed: username and password");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];
        String url = "jdbc:mysql://localhost:3306/CarDealership";


        try(
                BasicDataSource dataSource = new BasicDataSource();
                ) {
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            //insert ui in here and call menu method
        }catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
        }
    }

}

