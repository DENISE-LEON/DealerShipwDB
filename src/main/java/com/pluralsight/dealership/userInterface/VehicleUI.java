package com.pluralsight.dealership.userInterface;

import com.pluralsight.dealership.managers.DealershipManager;
import com.pluralsight.dealership.managers.VehicleManager;

import javax.sql.DataSource;
import java.util.InputMismatchException;
import java.util.Scanner;

public class VehicleUI extends BaseUserInterface {

    DataSource dataSource;
    DealershipManager dealershipManager;
    VehicleManager vehicleManager;

    public VehicleUI( Scanner scanner, DataSource dataSource) {
        super(scanner);
        this.dataSource = dataSource;
        this.dealershipManager = new DealershipManager(dataSource);
        this.vehicleManager = new VehicleManager(dataSource);
    }


    public void menuDisplay() {
        boolean run = true;
        // objectInstantiator();

        do {
            //menu display
            //create the options
            System.out.println("""
                    === Welcome to the car dealership, choose an option below ===
                    
                    1) Find vehicles within a price range
                    2) Find vehicles by model
                    3) Find vehicles by year range
                    4) Find vehicles by color
                    6) Find vehicles by type (car, truck, SUV, van)
                    7) List ALL vehicles
                    8) Add a vehicle
                    9) Remove a vehicle
                    0) Quit
                    
                    """);
            int menuChoice = 0;
            try {
                menuChoice = Math.abs(scanner.nextInt());
                scanner.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Please enter a number from the choices provided!");
                scanner.nextLine();
            }

            switch (menuChoice) {
                case 1:
                    displayByPriceProcess();
                    break;
                case 2:
                    displayByModel();
                    break;
                case 3:
                    displayByYearProcess();
                    break;
                case 4:
                    displayByColorProcess();
                    break;
                case 6:
                    displayByTypeProcess();
                    break;
                case 7:
                    displayAllVehiclesProcess();
                    break;
                case 8:
                    addVehicleProcess();
                    break;
                case 9:
                    removeVehicleProcess();
                    break;
                case 0:
                    System.out.print("Exiting program ");


                    run = false;
            }
        } while (run);
    }

    public void displayByPriceProcess() {
        System.out.print("Enter minimum price: ");
        double min = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double max = scanner.nextDouble();
    }

    public void displayByModel() {
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

    }

    public void displayByYearProcess() {
        System.out.print("Enter minimum year: ");
        int min = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int max = scanner.nextInt();
    }

    public void displayByColorProcess() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();
    }


    public void displayByTypeProcess() {
        System.out.print("Enter vehicle type: ");
        String type = scanner.nextLine();
    }

    public void displayAllVehiclesProcess() {
        //dealership.getAllVehicles();
    }


    //methods
    //user input goes in user interfaace
    public void addVehicleProcess() {
        System.out.println("Enter vehicle details");
        System.out.println("Enter VIN number");
        String vinNum = scanner.nextLine();

        System.out.println("Enter year");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter car model");
        String model = scanner.nextLine();

        System.out.println("Enter car type");
        String type = scanner.nextLine();

        System.out.println("Enter car color");
        String color = scanner.nextLine();

        System.out.println("Enter price");
        double price = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Enter price");
        String status = scanner.nextLine();
        scanner.nextLine();


//        //need to create the new object to pass into the add vehicle parameter
//        Vehicle vehicle = new Vehicle(vinNum, year, make, model, type, color, odometer, price);
//        //calling add vehicle method from dealership class
//        dealership.addVehicle(vehicle);
//        manager.vehicleRecorder(vehicle);
    }

    //returns for comfirmation to the user
    public void removeVehicleProcess() {

        System.out.println("Enter the VIN number of the car you'd like to remove");
        String rVinNum = scanner.nextLine().trim();

        //for fancies
        System.out.println("Are you sure you want to remove this vehicle?(Y/N)");
        String confirmRemove = scanner.nextLine();

//           boolean removed = dealership.removeVehicle(rVinNum);

        if (confirmRemove.equalsIgnoreCase("Y")) {
            //using a terniary instead of if
            //since terniary's return or assign values must wrap the terniary in a sout
//                System.out.printf(removed ? "Car has been successfully removed. Bye bye vroom vroom" : "Vehicle with %s VIN number not found", rVinNum);

//            if (removed) {
//                System.out.println("Car has successfully been removed. Bye bye vroom vroom ");
//            } else {
//                System.out.printf("Vehicle with %s VIN number not found", rVinNum);
//            }
        } else {
            System.out.println("Removal cancelled");

        }
    }

    //print methods

    public void nowDisplayingMgs(String what) {
        System.out.print("Searching");
        loadingDots();
        System.out.println();
        System.out.print("Now displaying " + what);
        loadingDots();
        System.out.println();

    }

    public void loadingDots() {
        try {
            for (int i = 0; i < 4; i++) {
                Thread.sleep(500);
                System.out.print(".");
            }
        } catch (InterruptedException e) {
            // ignore
        }
    }
}
//
//    private void objectInstantiator () {
//        DealershipFileManager vroomManager = new DealershipFileManager("src/main/resources/VroomInventory.csv");
//        vroomManager.getDealershipInfo();
//
//        //DealershipFileManager kartManager = new DealershipFileManager("src/main/resources/VroomKartInventory.csv");
//        //kartManager.getDealershipInfo();
//
//    }
