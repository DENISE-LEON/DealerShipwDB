package com.pluralsight.dealership.userInterface;

import org.apache.commons.dbcp2.BasicDataSource;

import java.util.Scanner;

public class MainMenuUI extends BaseUserInterface{

    BasicDataSource basicDataSource;
    VehicleUI vehicleUI;
    DealershipUI dealershipUI;
    SalesContractsUI salesContractsUI;

    public MainMenuUI(Scanner scanner, BasicDataSource basicDataSource) {
        super(scanner);
        this.basicDataSource = basicDataSource;
       // this.vehicleUI = new VehicleUI(scanner,basicDataSource);
        this.dealershipUI = new DealershipUI(scanner,basicDataSource);
        this.salesContractsUI = new SalesContractsUI();
    }

    public void MenuDisplay(){
        boolean run = true;
        while (run) {
            System.out.println();
            System.out.println("Hello");
            System.out.println("""
                    What would you like to do?
                    1) Manage Dealerships
                    2) Manage Vehicles
                    3) Manage Sale Contracts
                    0) Exit
                    """);
            int menuChoice = scanner.nextInt();
            switch (menuChoice) {
                case 1:
                    dealershipUI.menuDisplay();
                    break;
                case 2:
                    vehicleUI.menuDisplay();
                    break;
                case 3:
                    salesContractsUI.menuDisplay();
                    break;
                case 0:
                    nowDoingMgs("exiting");
                    System.out.println("\nGoodbye!");
                    run = false;
                    break;
            }
        }
    }


}
