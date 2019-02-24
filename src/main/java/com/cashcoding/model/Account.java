package com.cashcoding.model;

import com.cashcoding.services.InvalidAmountException;

/**
 * Created by m.karandish on 2/12/2019.
 */
public class Account {



    private double balance;
    private AccountType accountType;
    private String accoutnNo;

    public enum AccountType {CURRENT,SAVING,NORMAL}

    public Account(){

    }

    public Account(double balance, AccountType accountType){
        this.accountType = accountType;
        this.balance = balance;
    }
    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) throws InvalidAmountException {

        if (balance < 0)
            throw new InvalidAmountException("Negative Balance is not allowed!");
        this.balance = balance;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    public String getAccoutnNo() {
        return accoutnNo;
    }

    public void setAccoutnNo(String accoutnNo) {
        this.accoutnNo = accoutnNo;
    }

}
