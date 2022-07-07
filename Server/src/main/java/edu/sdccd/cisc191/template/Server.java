package edu.sdccd.cisc191.template;

import java.net.*;
import java.io.*;
import java.util.*;


/**
 * This program is a server that takes connection requests on
 * the port specified by the constant LISTENING_PORT.  When a
 * connection is opened, the program sends the current time to
 * the connected socket.  The program will continue to receive
 * and process connections until it is killed (by a CONTROL-C,
 * for example).  Note that this server processes each connection
 * as it is received, rather than creating a separate thread
 * to process the connection.
 */
public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    //private String[][] parkingLot;

    private ParkingLot parkingLotA;
    private String userInput;
    private Boolean running;

    public void start(int port) throws Exception {
        serverSocket = new ServerSocket(port);
        clientSocket = serverSocket.accept();
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            CustomerRequest request = CustomerRequest.fromJSON(inputLine);
            CustomerResponse response = new CustomerResponse(request.getId(), "Jane", "Doe");
            out.println(CustomerResponse.toJSON(response));
        }
    }

    public void stop() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
        serverSocket.close();
    }

    public void makeConsole() {
        //this.parkingLot = new String[10][2];
        parkingLotA = new ParkingLot();
        running = true;
        while (running) {
            //ViewParkingLotConsole console = new ViewParkingLotConsole();
            System.out.println("Parking Lot Console");
            System.out.println("_____________________");
            System.out.println("Do you want to: \n1. getCarInfo \n2. parkCar \n3. findCar \n4. printParkingLot \n5. removeCar \n6. exit");

            Scanner input = new Scanner(System.in);
            userInput = input.nextLine();
            int givenRow;
            int givenColumn;
            String givenString;
            switch (userInput) {
                case "getCarInfo":
                    System.out.println("Give a row");
                    givenRow = input.nextInt();
                    System.out.println("Give a column");
                    givenColumn = input.nextInt();
                    System.out.println(parkingLotA.getCarInfo(givenRow, givenColumn));
                    break;
                case "parkCar":
                    System.out.println("Give a make");
                    String givenMake = input.nextLine();
                    System.out.println("Give a model");
                    String givenModel = input.nextLine();
                    System.out.println("Give a license palte");
                    String givenPlate = input.nextLine();
                    System.out.println("Give a color");
                    String givenColor = input.nextLine();
                    System.out.println("Give a year");
                    String givenYear = input.nextLine();
                    Car givenCar = new Car(givenMake, givenModel, givenPlate, givenColor, givenYear);
                    System.out.println("Give a row");
                    givenRow = input.nextInt();
                    System.out.println("Give a column");
                    givenColumn = input.nextInt();
                    parkingLotA.parkCar(givenCar, givenRow, givenColumn);
                    break;
                case "findCar":
                    System.out.println("Give a license plate.");
                    givenString = input.nextLine();
                    System.out.println(parkingLotA.findCar(givenString));
                    break;
                case "printParkingLot":
                    System.out.println(parkingLotA.printParkingLot());
                    break;
                case "removeCar":
                    System.out.println("Give a row");
                    givenRow = input.nextInt();
                    System.out.println("Give a column");
                    givenColumn = input.nextInt();
                    parkingLotA.removeCar(givenRow, givenColumn);
                    break;
                case "exit":
                    running = false;
                    break;
            }
        }
    }

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(4444);
            server.makeConsole();
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} //end class Server
