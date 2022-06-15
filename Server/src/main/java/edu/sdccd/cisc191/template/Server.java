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
    private String[] parkingLot;
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
        this.parkingLot = new String[10];
        running = true;
        while (running) {
            System.out.println("Array Modding Console");
            System.out.println("_____________________");
            System.out.println("Do you want to \n 1. getAtIndex \n 2. setAtIndex \n 3. findIndexOf \n 4. printAll \n 5. deleteAtIndex \n 6. expand \n 7. shrink \n 8. exit");
            Scanner input = new Scanner(System.in);
            userInput = input.nextLine();
            int givenInteger;
            String givenString;
            switch (userInput) {
                case "getAtIndex":
                    System.out.println("Give a number");
                    givenInteger = input.nextInt();
                    System.out.println(getAtIndex(givenInteger));
                    break;
                case "setAtIndex":
                    System.out.println("Give a number");
                    givenInteger = input.nextInt();
                    input.nextLine();
                    System.out.println("Give a string.");
                    givenString = input.nextLine();
                    setAtIndex(givenString, givenInteger);
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
                    System.out.println("Give a number");
                    givenInteger = input.nextInt();
                    deleteAtIndex(givenInteger);
                    break;
                case "expand":
                    System.out.println("Give a number");
                    givenInteger = input.nextInt();
                    expand(givenInteger);
                    break;
                case "shrink":
                    System.out.println("Give a number");
                    givenInteger = input.nextInt();
                    shrink(givenInteger);
                    break;
                case "exit":
                    running = false;
                    break;
            }
        }
    }


    public String getAtIndex(int givenInt) {
        if (givenInt > parkingLot.length) {
            return "Sorry this does not exist";
        }
        else {
            return parkingLot[givenInt];
        }
    }

    public void setAtIndex(String givenString, int givenInt) {
        if (givenInt > parkingLot.length) {
            System.out.println("Sorry this spot is out of bounds");
        }
        else {
            parkingLot[givenInt] = givenString;
        }
    }

    public int findIndexOf(String givenString) {
        int count = 0;
        for (int i = 0; i < parkingLot.length; i++) {
            if (givenString.equals(parkingLot[i])) {
                return count;
            }
            else {
                count++;
            }
        }
        return count;
    }

    public String printAll() {
        String printedArray = "";
        for (int i = 0; i < parkingLot.length; i++) {
            printedArray += parkingLot[i] + ", ";
        }
        return printedArray;
    }

    public void deleteAtIndex(int givenInt) {
        String[] newParkingLot;
        newParkingLot = new String[parkingLot.length-1];
        if (givenInt < 0 || givenInt > parkingLot.length) {
            System.out.println("Index out of range!");
        }
        else {
            for (int i = 0; i < givenInt; i++) {
                newParkingLot [i] = parkingLot [i];
            }
            for (int i = givenInt+1; i < parkingLot.length; i++) {
                newParkingLot[i-1] = parkingLot[i];
            }
        }
        parkingLot = newParkingLot;
        return;
    }

    public void expand(int givenInt) {
        String[] newParkingLot;
        newParkingLot = new String[parkingLot.length + givenInt];
        for (int i = 0; i < parkingLot.length; i++) {
            newParkingLot[i] = parkingLot[i];
        }
        parkingLot = newParkingLot;
    }

    public void shrink(int givenInt) {
        String[] newParkingLot;
        if (givenInt > parkingLot.length) {
            System.out.println("Can't shrink by that much.");
            return;
        }
        else {
            newParkingLot = new String[parkingLot.length - givenInt];
            for (int i = 0; i < newParkingLot.length; i++) {
                newParkingLot[i] = parkingLot[i];
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
