package com.pluralsight.dealership.models;

public class Vehicle {
    private String vinNum;
    private String type;
    private int year;
    private String model;
    private String color;
    private double price;
    private Status status;

    public Vehicle(String vinNum, String type, int year, String model, String color, double price, Status status) {
        this.vinNum = vinNum;
        this.type = type;
        this.year = year;
        this.model = model;
        this.color = color;
        this.price = price;
        this.status = status;
    }

    public String getVinNum() {
        return vinNum;
    }

    public void setVinNum(String vinNum) {
        this.vinNum = vinNum;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //toString so when display vehicles it no display gibberish
    @Override
    public String toString() {
        return String.format(
                "%-6s  %-4d  %-12s  %-12s  %-8s  %-7s  %,8d  $%,8.2f",
                vinNum,
                year,
                model,
                type,
                color,
                price
        );
    }

}