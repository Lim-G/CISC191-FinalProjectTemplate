package edu.sdccd.cisc191.template;

public class Car {
    public String make;
    public String model;
    public String year;
    public String licensePlate;
    public String color;

    public Car(String givenMake, String givenModel, String givenLicensePlate, String givenColor, String givenYear) {
        this.make = givenMake;
        this.model = givenModel;
        this.year = givenYear;
        this.licensePlate = givenLicensePlate;
        this.color = givenColor;
    }

    public String printAll() {
        String description ="Make: " + this.make + "\nModel: " + this.model + "\nYear: " + this.year + "\nColor: " + this.color + "\nLicensePlate: " + this.licensePlate;
        return description;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    /*
    public String getMake() {
        return this.make;
    }

    public String getModel() {
        return this.model;
    }


    public String getColor() {
        return this.color;
    }

    public int getYear() {
        return this.year;
    }
     */

}
