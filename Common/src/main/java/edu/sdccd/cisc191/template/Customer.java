package edu.sdccd.cisc191.template;

import java.util.NoSuchElementException;
import java.util.Random;

//public class Customer extends Thread{
public class Customer implements Runnable {

    private int id;
    private SuperMarket superMarket;

    private int itemsToTake = 3;

    public Customer(int givenId, SuperMarket givenSuperMarket) {
        this.id = givenId;
        this.superMarket = givenSuperMarket;
    }

    public int generateRand(){
        Random rand = new Random();
        int randomInt = rand.nextInt(4);
        return randomInt;
    }

    @Override
    public void run(){
        for (int i = 0; i < itemsToTake; i++) {
            int arrayChoice = generateRand();
            superMarket.purchased(superMarket.hashArray.get(arrayChoice));
            /*
            try {
                superMarket.purchased(superMarket.hashArray.get(arrayChoice));
            } catch (NoSuchElementException e) {
                System.out.println("Mo more stock");
            }

             */
        }
    }
}
