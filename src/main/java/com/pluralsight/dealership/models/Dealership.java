package com.pluralsight.dealership.models;


import java.util.ArrayList;
import java.util.List;


public class Dealership {
    private int DealershipID;
    private String name;
    private String address;
    private String phoneNum;
    private List<Vehicle> inventory;


    public Dealership(int dealershipID, String name, String address, String phoneNum) {
        DealershipID = dealershipID;
        this.name = name;
        this.address = address;
        this.phoneNum = phoneNum;
        this.inventory = new ArrayList<>();
    }


    public int getDealershipID() {
        return DealershipID;
    }

    public void setDealershipID(int dealershipID) {
        DealershipID = dealershipID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<Vehicle> getInventory() {
        return inventory;
    }

    public void setInventory(List<Vehicle> inventory) {
        this.inventory = inventory;
    }

    //pretty formatting
    @Override
    public String toString() {
        return String.format(
                "%n════════════════════════════════════════%n" +
                        "🏢  DEALERSHIP INFORMATION%n" +
                        "────────────────────────────────────────%n" +
                        "Name            : %s%n" +
                        "Address         : %s%n" +
                        "Phone           : %s%n" +
                        "════════════════════════════════════════%n",
                name,
                address,
                phoneNum
        );
    }
}
