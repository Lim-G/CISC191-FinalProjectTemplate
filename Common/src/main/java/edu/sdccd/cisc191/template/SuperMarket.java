package edu.sdccd.cisc191.template;

import java.util.*;

import java.util.Random;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

public class SuperMarket {

    private static Hashtable<Integer, Produce> produceHt = new Hashtable<>();
    private static Hashtable<Integer, Meat> meatHt = new Hashtable<>();
    private static Hashtable<Integer, Alchohol> alchoholHt = new Hashtable<>();
    private static Hashtable<Integer, HeinzBeans> heinzBeansHt = new Hashtable<>();
    static ArrayList<Hashtable> hashArray = new ArrayList<>();
    static ArrayList<String> soldList = new ArrayList<>();

    public SuperMarket() {
        for(int i=0 ; i<10 ; i++){
            Produce produce = new Produce();
            Meat meat = new Meat();
            Alchohol alchohol = new Alchohol();
            HeinzBeans beans = new HeinzBeans();
            this.produceHt.put(i, produce);
            this.meatHt.put(i, meat);
            this.alchoholHt.put(i, alchohol);
            this.heinzBeansHt.put(i, beans);
        }
        hashArray.add(produceHt);
        hashArray.add(meatHt);
        hashArray.add(alchoholHt);
        hashArray.add(heinzBeansHt);
    }

    public static <T extends Food> void addToSoldList(ArrayList givenList, T givenItem){
        String purchasedItem = givenItem.getFoodType();
        givenList.add(purchasedItem);
    }

    public static void purchased(Hashtable givenHash) throws NoSuchElementException {
        if (givenHash.size() > 0) {
            Set<Integer> keys = givenHash.keySet();
            int key = keys.iterator().next();
            Food foodType = (Food) givenHash.get(key);
            addToSoldList(soldList, foodType);
            givenHash.remove(key);
        } else {
            System.out.println("Mo more stock");
            throw new NoSuchElementException();
        }
    }

    public static String soldListToString(ArrayList givenlist, int currentIndex, String givenString){
        if (currentIndex >= givenlist.size())
        {
            return givenString;
        }
        else
        {
            givenString = givenString + givenlist.get(currentIndex) + ", " ;
            return soldListToString(givenlist, currentIndex + 1, givenString);
        }
    }

    public static void restock(){
        int produceLeft = produceHt.size();
        int meatLeft = meatHt.size();
        int alchoholLeft = alchoholHt.size();
        int heinzBeansLeft = heinzBeansHt.size();
        for(int i = 9; i>=produceLeft; i--) {
            Produce produce = new Produce();
            produceHt.put(i, produce);
        }
        for(int i = 9; i>=meatLeft; i--) {
            Meat meat = new Meat();
            meatHt.put(i, meat);
        }
        for(int i = 9; i>=alchoholLeft; i--) {
            Alchohol alchohol = new Alchohol();
            alchoholHt.put(i, alchohol);
        }
        for(int i = 9; i>=heinzBeansLeft; i--) {
            HeinzBeans beans = new HeinzBeans();
            heinzBeansHt.put(i, beans);
        }
    }

    public static int generateRand(){
        Random rand = new Random();
        int randomInt = rand.nextInt(4);
        return randomInt;
    }

    public static void purchaseStuff() {
        for (int i = 0; i < 3; i++) {
            int arrayChoice = generateRand();
            purchased(hashArray.get(arrayChoice));
        }
    }

    public static void addToReport(){
        try{
            BufferedWriter outPutWriter = new BufferedWriter(new FileWriter("output.txt", true));
            outPutWriter.write("All Items Sold Today");
            outPutWriter.write(soldListToString(soldList, 0, ""));
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void readReport(){
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


    public static void main(String[] Args){
        for(int i=0 ; i<10 ; i++){
            Produce produce = new Produce();
            Meat meat = new Meat();
            Alchohol alchohol = new Alchohol();
            HeinzBeans beans = new HeinzBeans();
            produceHt.put(i, produce);
            meatHt.put(i, meat);
            alchoholHt.put(i, alchohol);
            heinzBeansHt.put(i, beans);
        }

        purchased(produceHt);
        purchased(produceHt);
        purchased(produceHt);
        purchased(meatHt);
        purchased(meatHt);
        purchased(meatHt);
        purchased(alchoholHt);
        purchased(alchoholHt);
        purchased(alchoholHt);
        purchased(heinzBeansHt);

        addToReport();
        readReport();

        Set<Integer> keys = produceHt.keySet();
        for(Integer key: keys){
            System.out.println(key);
        }
        System.out.println("------------------");
        restock();
        Set<Integer> keys2 = produceHt.keySet();
        for(Integer key: keys2){
            System.out.println(key);
        }
        System.out.println(soldListToString(soldList,0, ""));

    }

}
