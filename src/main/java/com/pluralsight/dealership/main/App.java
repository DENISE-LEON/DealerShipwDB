package com.pluralsight.dealership.main;

import com.pluralsight.dealership.userInterface.MainMenuUI;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.SQLException;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (args.length != 2) {
            System.out.println("Two arguments are needed: username and password");
            System.exit(1);
        }

        String username = args[0];
        String password = args[1];
        String url = "jdbc:mysql://localhost:3306/CarDealership";


        try(
                BasicDataSource dataSource = new BasicDataSource()
                ) {
            dataSource.setUrl(url);
            dataSource.setUsername(username);
            dataSource.setPassword(password);

            //insert ui in here and call menu method
            MainMenuUI mainMenuUI = new MainMenuUI(scanner,dataSource);
            mainMenuUI.MenuDisplay();
        }catch (SQLException e) {
            System.out.println(e.getErrorCode() + e.getMessage());
        }
    }

}

