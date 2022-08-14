package edu.sdccd.cisc191.template;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class Manager extends Thread {

    private String name = "ManagerMan";

    private SuperMarket superMarket;

    public Manager(SuperMarket givenSuperMarket) {
        superMarket = givenSuperMarket;
    }

    public String returnName(){
        return name;
    }

    public void addToReport(){
        try{
            BufferedWriter outPutWriter = new BufferedWriter(new FileWriter("output.txt", true));
            outPutWriter.write("All Items Sold Today");
            outPutWriter.write(superMarket.soldListToString(superMarket.soldList, 0, ""));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void readReport(){
        try{
            BufferedReader reportReader = new BufferedReader(new FileReader("output.txt"));
            String line;
            while((line = reportReader.readLine()) != null){
                System.out.println(reportReader.readLine());
            }
            reportReader.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void run(){
        System.out.println("Daily report by: " + this.returnName());
        addToReport();
        readReport();
    }


}
