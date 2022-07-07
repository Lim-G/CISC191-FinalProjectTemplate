package edu.sdccd.cisc191.template;

/**
 * This class is created so that we can have a two dimensional parking array with functionality. Any methods involving the
 * modification of the parking lot array is involved in this class. Also it modifies a 2D Car object array.
 */
public class ParkingLot {
    public Car[][] parkingLot;
    public Boolean isCar = false;
    public Boolean outOfBounds = false;

    //I wanted to create a parking lot with other cars in it already to simulate a real parking lot
    Car carA = new Car("Ford", "Mustang", "123AIGHT", "Blue", "2018");
    Car carB = new Car("Mazda", "Miata", "234AIGHT", "Red", "2022");
    Car carC = new Car("Honda", "Civic Type R", "345AIGHT", "Green", "2019");
    Car carD = new Car("Kia", "Sorrento", "456AIGHT", "Silver", "2011");
    Car carE = new Car("Lexus", "RC500", "567AIGHT", "Red", "2018");
    Car carF = new Car("BMW", "M3", "678AIGHT", "White", "2018");
    Car carG = new Car("Mercedes Benz", "C300", "789AIGHT", "Black", "2018");
    Car carH = new Car("Infinity", "G36", "890AIGHT", "Black", "2018");

    //Parking lot constructor with some spaces filled out to test methods.
    public ParkingLot() {
        this.parkingLot = new Car[10][2];
        parkingLot[0][1] = carA;
        parkingLot[1][0] = carB;
        parkingLot[2][1] = carC;
        parkingLot[3][1] = carD;
        parkingLot[4][0] = carF;
        parkingLot[4][1] = carG;
        parkingLot[7][1] = carH;
        parkingLot[8][0] = carE;
    }

    /**
     * All the methods below are simple getters and setters I think its pretty self-explanatory
     */
    public String getCarInfo(int givenRow, int givenColumn) {
        if (givenRow > parkingLot.length || givenColumn > parkingLot[0].length) {
            return "Sorry this does not exist";
        }
        else {
            return parkingLot[givenRow][givenColumn].printAll();
        }
    }

    public void parkCar(Car givenCar, int givenRow, int givenColumn) {
        if (givenRow > parkingLot.length || givenColumn > parkingLot[0].length || parkingLot[givenRow][givenColumn] != null) {
            System.out.println("Sorry this spot is out of bounds");
        }
        else {
            parkingLot[givenRow][givenColumn] = givenCar;
        }
    }

    public String findCar(String givenLicensePlate) {
        int row = 0;
        int column = 0;
        for (row = 0; row < parkingLot.length; row++) {
            for (column = 0; column < parkingLot[0].length; column++) {
                if (parkingLot[row][column] == null) {
                    continue;
                }
                else if (givenLicensePlate.equals(parkingLot[row][column].getLicensePlate())) {
                    return "row: " + row + " , column: " + column ;
                }
            }
        }
        return  "row: " + row + " , column: " + column ;
    }

    public String printParkingLot() {
        String printedArray = "";
        int row = 0;
        int column = 0;
        for (row = 0; row < parkingLot.length; row++) {
            for (column = 0; column < parkingLot[0].length; column++) {
                if (parkingLot[row][column] == null) {
                    printedArray += "Empty \n------------------------------\n";
                }
                else {
                    printedArray += (parkingLot[row][column].printAll() + "\n------------------------------\n");
                }
            }
        }
        return printedArray;
    }

    public void removeCar(int givenRow, int givenColumn) {
        if (givenRow < 0 || givenRow > parkingLot.length || givenColumn < 0 || givenColumn > parkingLot[0].length) {
            System.out.println("Index out of range!");
        }
        else {
            parkingLot[givenRow][givenColumn] = null;
        }
    }

    public Boolean isCar(int givenRow, int givenColumn) {
        if (parkingLot[givenRow][givenColumn] != null) {
            isCar = true;
        }
        else {
            isCar = false;
        }
        return isCar;
    }

    public Boolean outOfBounds(int givenRow, int givenColumn) {
        if (givenRow > parkingLot.length || givenColumn > parkingLot[0].length) {
            outOfBounds = true;
        }
        else {
            outOfBounds = false;
        }
        return outOfBounds;
    }

}
