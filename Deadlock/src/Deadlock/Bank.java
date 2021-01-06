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

import java.util.*;

public class Bank {

    private final double[] accounts;
    
    public Bank(int n, double initialBalance)
    {
        //number of accounts
        accounts = new double[n];
        
        //initial balance of each account
        Arrays.fill(accounts, initialBalance);
    }
    
    /*
    This transfers money from one account to another
    from is the account to transfer from
    to is the account to transfer to
    amount is the amount to transfer
    */
    public synchronized void transfer(int from, int to, double amount) throws InterruptedException
    {
        
        //while statement keeps thread open until the amount is less than the account balance
        while (accounts[from] < amount)
        wait();
        
        //Print current thread information
        System.out.print(Thread.currentThread());
        
        //Deduct the amount from the from account balance
        accounts[from] -= amount;
        
        //Print out the transfer information
        System.out.printf(" %10.2f from %d to %d", amount, from, to);
        
        //Add the amount to the to account balance
        accounts[to] += amount;
        
        //Print out the total balance of all accounts
        System.out.printf(" Total Balance: %10.2f%n", getTotalBalance());

        //Notify all other threads to recheck wait condition
        notifyAll();

        }

    /* 
    Gets the sum of all account balances.
    */
    
    public synchronized double getTotalBalance()
    {
        double sum = 0;
        for (double a : accounts)
            sum += a;
        
        return sum;
    }
    
    /* 
    Gets the number of accounts in the bank.
    */
    
    public int size()
    {
        return accounts.length;
    }
        
}
