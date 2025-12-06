package com.pluralsight.dealership.userInterface;

import com.pluralsight.dealership.managers.DealershipManager;
import com.pluralsight.dealership.models.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Scanner;

public class DealershipUI extends BaseUserInterface {

    BasicDataSource basicDataSource;
    DealershipManager dealershipManager;

    public DealershipUI(Scanner scanner, BasicDataSource basicDataSource) {
        super(scanner);
        this.basicDataSource = basicDataSource;
        this.dealershipManager = new DealershipManager(basicDataSource);
    }

    public void menuDisplay() {
        boolean run = true;
        while (run) {
            System.out.println("""
                    What would you like to do?
                    1) View all Dealerships
                    2) Add dealership
                    3) Remove Dealership
                    4) Update Dealership
                    0) Return to main menu
                    """);
            int menuChoice = scanner.nextInt();

            switch (menuChoice) {
                case 1:
                    viewAllDealershipsProcess();
                    break;
            }
        }
    }

    public void viewAllDealershipsProcess() {
        nowDisplayingMgs("all dealerships");
       printResults(dealershipManager.ViewAllDealerships());
    }

    public void printResults(List<Dealership> dealerships) {
        if (dealerships.isEmpty()) {
            System.out.println("No dealerships found");
            System.out.println();
            return;
        }
        System.out.printf("%-10s %-30s %-20s %-40s%n",
                "ID", "Name", "Phone Number", "Address");

        // Divider
        System.out.println("-----------------------------------------------------------------------------------------------");

        // Rows
        for (Dealership d : dealerships) {
            System.out.printf("%-10d %-30s %-20s %-40s%n",
                    d.getDealershipID(),
                    d.getName(),
                    d.getPhoneNum(),
                    d.getAddress());
        }

        System.out.println();
    }
}


