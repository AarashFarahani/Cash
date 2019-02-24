package com.cashcoding.model;

import com.cashcoding.services.InvalidAmountException;

import java.util.ArrayList;

/**
 * Created on 1/25/2019.
 */
public class Customer {
    /**
     * Private Fields
     */
//    private double accountBalance;
    private String name;
    private String uid;

    private ArrayList<Account> listOfAccounts = new ArrayList<Account>();
    /**
     * The constructor of the class
     * It checks the validation to not assign any Negative value to customer balance
     * @param name
     * @param balance
     * @throws InvalidAmountException
     */
    public Customer(String name, double balance) throws InvalidAmountException {
        if (balance < 0)
            throw new InvalidAmountException("Negative Balance is not allowed!");
        this.name = name;
    }


    /**
     * Class Constructor
     */
    public Customer() {

    }

    /**
     * Gets accountBalance
     * @return
     */
//    public double getAccountBalance() {
//        return accountBalance;
//    }
//
    /**
     * Sets accountBalance
     * Check the validation of not passing any negative value to the variable
     * @param accountBalance
     * @throws InvalidAmountException
     */
//    public void setAccountBalance(double accountBalance) throws InvalidAmountException {
//        if (accountBalance < 0)
//            throw new InvalidAmountException("Negative Balance is not allowed!");
//        this.accountBalance = accountBalance;
//    }

    /**
     * Gets Name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Sets Name
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets Unique ID
     * @return
     */
    public String getUid() {
        return uid;
    }

    /**
     * Sets Unique ID
     * @param uid
     */
    public void setUid(String uid) {
        this.uid = uid;
    }

    public ArrayList<Account> getListOfAccounts() {
        return listOfAccounts;
    }

    public void setListOfAccounts(ArrayList<Account> listOfAccounts) {
        this.listOfAccounts = listOfAccounts;
    }
}
