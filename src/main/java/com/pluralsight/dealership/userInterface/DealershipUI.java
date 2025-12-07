package com.pluralsight.dealership.userInterface;

import com.pluralsight.dealership.managers.DealershipField;
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
        try {
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
        }catch (Exception e) {
            System.out.println("Please input a number");
            System.out.println("Example:");
            System.out.println("I want to: view all dealerships");
            System.out.println("Your input: 1");
            scanner.nextLine();
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
        try {
            System.out.println("Tip: if you don't know the ID on the dealership go back to main menu and select option 1 to view all dealerships");
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
        }catch (Exception e){
            System.out.println("Please input a number");
            System.out.println("Example:");
            System.out.println("I want to: remove dealership with ID #3");
            System.out.println("Your input: 3");
            scanner.nextLine();
        }
    }

    public void updateDealershipProcess() {
        try {
            System.out.println("Tip: if you don't know the ID on the dealership go back to main menu and select option 1 to view all dealerships");
            System.out.println("Which field would you like to update?");
            System.out.println("""
                    1) Dealership name
                    2) Dealership address
                    3) Dealership Phone number
                    0) Return to main menu
                    """);
            int fieldChoice = scanner.nextInt();
            scanner.nextLine();

            DealershipField field = null;
            switch (fieldChoice) {
                case 1:
                    field = DealershipField.NAME;
                    break;
                case 2:
                    field = DealershipField.ADDRESS;
                    break;
                case 3:
                    field = DealershipField.PHONE;
                    break;
                case 0:
                    System.out.print("Returning to main menu");
                    loadingDots();
                    System.out.println();
                    return;
            }
            try {
                System.out.println("Please enter the ID of the shipper you'd like to update");
                int shipperID = scanner.nextInt();
                scanner.nextLine();
                System.out.println("What would you like to update the field to?");
                String newVal = scanner.nextLine();
                nowDoingMgs("Updating");
                dealershipManager.updateField(shipperID, field, newVal);

            } catch (Exception e) {
                System.out.println("Please input a number");
                System.out.println("Example:");
                System.out.println("I want to: update with ID #3");
                System.out.println("Your input: 3");
                scanner.nextLine();
            }

        } catch (Exception e) {
            System.out.println("Please input a number");
            System.out.println("Example:");
            System.out.println("I want to: update dealership name");
            System.out.println("Your input: 1");
            scanner.nextLine();
        }
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


