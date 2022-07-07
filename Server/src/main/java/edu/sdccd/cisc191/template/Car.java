package edu.sdccd.cisc191.template;

/**
 * This class is for the creation of car objects. Cars have some attributes and they can be
 */
public class Car {
    public final String make;
    public final String model;
    public final String year;
    public final String licensePlate;
    public final String color;

    public Car(String givenMake, String givenModel, String givenLicensePlate, String givenColor, String givenYear) {
        this.make = givenMake;
        this.model = givenModel;
        this.year = givenYear;
        this.licensePlate = givenLicensePlate;
        this.color = givenColor;
    }

    //Method for creating a string that prints all the information stored in each car object.
    public String printAll() {
        String description ="Make: " + this.make + "\nModel: " + this.model + "\nYear: " + this.year + "\nColor: " + this.color + "\nLicensePlate: " + this.licensePlate;
        return description;
    }

    public String getLicensePlate() {
        return licensePlate;
    }


}
