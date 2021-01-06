/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Deadlock;

/**
 *
 * @author Team 6
 */
public class Deadlock {
    
    public static final int NACCOUNT = 10; //number of accounts
    public static final double INITIAL_BALANCE = 1000; //initial balance of all accounts
    public static final double MAX_AMOUNT = 2 * 1000; //maximum transfer amount
    public static final int DELAY = 10; //delay between threads
    
    public static void main(String[] args)
    {
        //Create new bank object
        Bank bank = new Bank(NACCOUNT, INITIAL_BALANCE);
        
        //For loop to create threads for each account
        for (int i = 0; i < NACCOUNT; i++)
        {
            int fromAccount = i;
            
            //Create Runnable instance
            Runnable r = () -> {
                
                //Try statement to catch interrupts
                try
                {
                    //while loop continuously runs
                    while (true)
                    {
                        //Randomly determine account to transfer to
                        int toAccount = (int) (bank.size() * Math.random());
                        
                        //Randomly determine amount to transfer
                        double amount = MAX_AMOUNT * Math.random();
                        
                        //Call transfer method
                        bank.transfer(fromAccount, toAccount, amount);
                        
                        //Temporarily stop thread for a random amount of time
                        Thread.sleep((int) (DELAY * Math.random()));
                    }
                }
                
                catch (InterruptedException e)
                {
                    //Thread was interrupted during sleep
                }
            };
            
            //Create a new thread
            Thread t = new Thread(r);
            
            //Start the newly created thread
            t.start();
        }
    }
}
