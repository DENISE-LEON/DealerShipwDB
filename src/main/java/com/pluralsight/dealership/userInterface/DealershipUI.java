package com.pluralsight.dealership.userInterface;

import com.pluralsight.dealership.managers.DealershipManager;
import com.pluralsight.dealership.models.Dealership;
import org.apache.commons.dbcp2.BasicDataSource;

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
            System.out.println();
            System.out.println("""
                    What would you like to do?
                    1) View all Dealerships
                    2) Add dealership
                    3) Remove Dealership
                    4) Update Dealership
                    0) Return to main menu
                    """);
            int menuChoice = scanner.nextInt();
            scanner.nextLine();

            switch (menuChoice) {
                case 1:
                    viewAllDealershipsProcess();
                    break;
                case 2:
                    addDealershipProcess();
                    break;
                case 3:
                    removeDealershipProcess();
                    break;
                case 4:
                    updateDealershipProcess();
                    break;
                case 0:
                    nowDoingMgs("returning to main menu");
                    run = false;
                    break;
            }
        }
    }

    public void viewAllDealershipsProcess() {
        nowDisplayingMgs("all dealerships");
        printResults(dealershipManager.ViewAllDealerships());
    }


    public void addDealershipProcess() {
        System.out.println("Enter the name of the dealership");
        String dealershipName = scanner.nextLine();

        System.out.println("Enter the dealership phone number");
        System.out.println("Please use the following format:");
        System.out.println("Example: (555) 555-5555");

        String phoneNum = scanner.nextLine();

        System.out.println("Enter the dealership address");
        String address = scanner.nextLine();


        nowDoingMgs("adding" + " " + dealershipName);

        //can use the returned value AND run everything in the method if stored in variable
        Integer id = dealershipManager.insertDealership(dealershipName, phoneNum, address);
        System.out.println("Done!");
        System.out.println("Dealership ID is:" + " " + id);
    }

    public void removeDealershipProcess() {
        System.out.println("Enter the Dealership ID of the dealership you wish to remove");
        String dealershipID = scanner.nextLine();

        System.out.println("Are you sure you want to remove this dealership?(Y/N)");
        String confirmRemove = scanner.nextLine();

        boolean removed;
        if (confirmRemove.equalsIgnoreCase("Y")) {

            nowDoingMgs("removing dealership");

            removed = dealershipManager.removeDealership(dealershipID);

            System.out.printf(removed ? "Car has been successfully removed. Bye bye vroom vroom" : "Vehicle with %s VIN number not found", dealershipID);
        } else {
            System.out.println("Removal cancelled");
        }
    }

    public void updateDealershipProcess() {

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


