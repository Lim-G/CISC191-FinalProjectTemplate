package edu.sdccd.cisc191.template;

public class RunSimulation {

    public static void main(String[] args) throws InterruptedException{
        SuperMarket superMarket = new SuperMarket();

        Manager managerMan = new Manager(superMarket);

        Customer customer1 = new Customer(1, superMarket);

        /*
        int numberCustomers = 5;

        Customer[] customers = new Customer[numberCustomers];

        for (int i = 0; i < numberCustomers; i++)
        {
            customers[i] = new Customer(i, superMarket);
        }
        for (int i = 0; i < numberCustomers; i++)
        {
            customers[i].run();
        }
        for (int i = 0; i < numberCustomers; i++)
        {
            customers[i].join();
        }
        managerMan.run();

         */

    }

}
