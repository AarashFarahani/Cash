package com.cashcoding.model;

import java.util.ArrayList;

/**
 * Created  on 1/25/2019.
 */
public class Bank {
    /**
     * Bank class properties
     */
    private ArrayList<Customer> customers = new ArrayList<Customer>();
    private String bankName;

    /**
     * Gets Cutomers
     * List of cutomers passed here as parameter
     * @return
     */
    public ArrayList<Customer> getCustomers() {
        return customers;
    }

    /**
     * Sets Customers
     * @param customers
     */
    public void setCustomers(ArrayList<Customer> customers) {
        this.customers = customers;
    }

    /**
     * Gets bankName
     * it is not used in the project. it is a reserved variable
     * @return
     */
    public String getBankName() {
        return bankName;
    }

    /**
     * sets bankName
     * @param bankName
     */
    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
}
