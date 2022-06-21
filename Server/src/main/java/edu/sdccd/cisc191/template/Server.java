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
    private String[][] parkingLot;
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
        this.parkingLot = new String[10][10];
        running = true;
        while (running) {
            System.out.println("Array Modding Console");
            System.out.println("_____________________");
            System.out.println("Do you want to \n 1. getAtIndex \n 2. setAtIndex \n 3. findIndexOf \n 4. printAll \n 5. deleteAtIndex \n 6. expand \n 7. shrink \n 8. exit");
            Scanner input = new Scanner(System.in);
            userInput = input.nextLine();
            int givenRow;
            int givenColumn;
            String givenString;
            switch (userInput) {
                case "getAtIndex":
                    System.out.println("Give a row");
                    givenRow = input.nextInt();
                    System.out.println("Give a column");
                    givenColumn = input.nextInt();
                    System.out.println(getAtIndex(givenRow, givenColumn));
                    break;
                case "setAtIndex":
                    System.out.println("Give a row");
                    givenRow = input.nextInt();
                    System.out.println("Give a column");
                    givenColumn = input.nextInt();
                    System.out.println("Give a string.");
                    givenString = input.nextLine();
                    setAtIndex(givenString, givenRow, givenColumn);
                    break;
                case "findIndexOf":
                    System.out.println("Give a string.");
                    givenString = input.nextLine();
                    System.out.println(findIndexOf(givenString));
                    break;
                case "printAll":
                    System.out.println(printAll());
                    break;
                case "deleteAtIndex":
                    System.out.println("Give a row");
                    givenRow = input.nextInt();
                    System.out.println("Give a column");
                    givenColumn = input.nextInt();
                    deleteAtIndex(givenRow, givenColumn);
                    break;
                case "expand":
                    System.out.println("How many rows would you like to add?");
                    givenRow = input.nextInt();
                    System.out.println("How many columns would you like to add?");
                    givenColumn = input.nextInt();
                    expand(givenRow, givenColumn);
                    break;
                case "shrink":
                    System.out.println("How many rows would you like to shrink by?");
                    givenRow = input.nextInt();
                    System.out.println("How many columns would you like to shrink by?");
                    givenColumn = input.nextInt();
                    shrink(givenRow, givenColumn);
                    break;
                case "exit":
                    running = false;
                    break;
            }
        }
    }


    public String getAtIndex(int givenRow, int givenColumn) {
        if (givenRow > parkingLot.length || givenColumn > parkingLot[0].length) {
            return "Sorry this does not exist";
        }
        else {
            return parkingLot[givenRow][givenColumn];
        }
    }

    public void setAtIndex(String givenString, int givenRow, int givenColumn) {
        if (givenRow > parkingLot.length || givenColumn > parkingLot[0].length) {
            System.out.println("Sorry this spot is out of bounds");
        }
        else {
            parkingLot[givenRow][givenColumn] = givenString;
        }
    }

    public String findIndexOf(String givenString) {
        int row = 0;
        int column = 0;
        for (row = 0; row < parkingLot.length; row++) {
            for (column = 0; column < parkingLot[0].length; column++) {
                if (givenString.equals(parkingLot[row][column])) {
                    return "row: " + row + " , column: " + column ;
                }
            }
        }
        return  "row: " + row + " , column: " + column ;
    }

    public String printAll() {
        String printedArray = "";
        int row = 0;
        int column = 0;
        for (row = 0; row < parkingLot.length; row++) {
            for (column = 0; column < parkingLot[0].length; column++) {
                printedArray += (" " + parkingLot[row][column] + " ");
            }
        }
        return printedArray;
    }

    public void deleteAtIndex(int givenRow, int givenColumn) {
        if (givenRow < 0 || givenRow > parkingLot.length || givenColumn < 0 || givenColumn > parkingLot[0].length) {
            System.out.println("Index out of range!");
        }
        else {
            parkingLot[givenRow][givenColumn] = null;
        }
    }

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

    public static void main(String[] args) {
        Server server = new Server();
        try {
            server.start(4444);
            System.out.println("testing testing");
            server.makeConsole();
            server.stop();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
} //end class Server
