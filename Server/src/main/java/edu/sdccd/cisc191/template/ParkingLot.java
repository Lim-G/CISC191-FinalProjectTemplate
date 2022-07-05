package edu.sdccd.cisc191.template;

public class ParkingLot {
    public Car[][] parkingLot;

    Car carA = new Car("Ford", "Mustang", "123AIGHT", "Blue", 2018);
    Car carB = new Car("Mazda", "Miata", "234AIGHT", "Red", 2022);
    Car carC = new Car("Honda", "Civic Type R", "345AIGHT", "Green", 2019);
    Car carD = new Car("Kia", "Sorrento", "456AIGHT", "Silver", 2011);
    Car carE = new Car("Lexus", "RC500", "567AIGHT", "Red", 2018);
    Car carF = new Car("BMW", "M3", "678AIGHT", "White", 2018);
    Car carG = new Car("Mercedes Benz", "C300", "789AIGHT", "Black", 2018);
    Car carH = new Car("Infinity", "G36", "890AIGHT", "Black", 2018);


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
                if (givenLicensePlate.equals(parkingLot[row][column].getLicensePlate())) {
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

    //This stuff is not applicable to a parking lot.
    /*
    public void expand(int givenRow, int givenColumn) {
        String[][] newParkingLot;
        newParkingLot = new String[parkingLot.length + givenRow][parkingLot[0].length + givenColumn];
        int row = 0;
        int column = 0;
        for (row = 0; row < parkingLot.length; row++) {
            for (column = 0; column < parkingLot[0].length; column++) {
                newParkingLot[row][column] = parkingLot[row][column];
            }
        }
        parkingLot = newParkingLot;
    }

    public void shrink(int givenRow, int givenColumn) {
        String[][] newParkingLot;
        int row = 0;
        int column = 0;
        if (givenRow > parkingLot.length || givenColumn > parkingLot[0].length) {
            System.out.println("Can't shrink by that much.");
            return;
        }
        else {
            newParkingLot = new String[parkingLot.length - givenRow][parkingLot[0].length - givenColumn];
            for (row = 0; row < newParkingLot.length; row++) {
                for (column = 0; column < newParkingLot[0].length; column++) {
                    newParkingLot[row][column] = parkingLot[row][column];
                }
            }
        }
        parkingLot = newParkingLot;
    }
    */
}
