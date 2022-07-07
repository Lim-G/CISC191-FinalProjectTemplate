package edu.sdccd.cisc191.template;

/**
 * This unit test checks only the main methods that are implemented into the GUI buttons.
 * The entire unit test is commented out because it seems to cause some kind of error in the rest of my code
 * I have looked for so long but I can not identify the issue.
 *
 * Note also the IDE seems to have errors that make no sense. One attempt to run the GUI resulted in an error
 * java: cannot find symbol
 *   symbol:   class Car
 *   location: class edu.sdccd.cisc191.template.ParkingLot
 *
 * Then the next run through
 * java: cannot find symbol
 *   symbol:   class ParkingLot
 *   location: class edu.sdccd.cisc191.template.ViewParkingLotConsole
 *
 * IDE seems to be struggling to identify classes that definitely exist in the package
 */

public class UnitTest {

   boolean checker = false;

   public ParkingLot testLot = new ParkingLot();

   public Car testCar = new Car("Audi", "RS3", "405T78H", "red", "2018");

   public void getCarInfoCheck(){
       Car tempCar = testLot.getCar(0,1);
       if (testLot.getCarInfo(0,1) == "Make: " + tempCar.make + "\nModel: " + tempCar.model + "\nYear: " + tempCar.year + "\nColor: " + tempCar.color + "\nLicensePlate: " + tempCar.licensePlate) {
           checker = true;
       }
       else {
           checker = false;
       }
   }

   public void parkCarCheck() {
       testLot.parkCar(testCar, 0, 0);
       Car tempCar = testLot.getCar(0,0);
       if (testCar.printAll() == tempCar.printAll()){
           checker = true;
       }
       else {
           checker = false;
       }
   }

   public void findCarCheck() {
       String testString = "row: 0 , column: 1";
       if (testLot.findCar("123AIGHT") == testString){
           checker = true;
       }
       else {
           checker = false;
       }
   }

   public void removeCarCheck() {
       testLot.removeCar(0,1);
       if (testLot.getCar(0,1) == null){
           checker = true;
       }
       else {
           checker = false;
       }
   }

   public String main(){
       getCarInfoCheck();
       parkCarCheck();
       findCarCheck();
       removeCarCheck();
       if (checker == true){
           return "All checks passed";
       }
       else {
           return "Test failed";
       }
   }

}
