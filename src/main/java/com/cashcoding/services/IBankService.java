package com.cashcoding.services;

import com.cashcoding.model.Account;
import com.cashcoding.model.Bank;
import com.cashcoding.model.Customer;
import com.cashcoding.model.Transaction;

import java.util.ArrayList;
import java.util.List;

/**
 * Created on 1/25/2019.
 */
public interface IBankService {

    /**
     * @see BankService#withdrawAccount(double, String)
     * @param amount
     * @param uid
     * @return
     * @throws InvalidAmountException
     */
    double withdrawAccount(double amount, String uid,String acctNo) throws InvalidAmountException;

    /**
     * @see BankService#depositAccount(double, String)
     * @param amount
     * @param uid
     * @return
     * @throws InvalidAmountException
     */
    double depositAccount(double amount, String uid,String acctNo) throws InvalidAmountException;

    /**
     * @see BankService#createCustomer(String, double)
     * @param name
     * @param balance
     * @return
     * @throws InvalidAmountException
     */
    Customer createCustomer(String name, double balance) throws InvalidAmountException;

    /**
     * @see BankService#getBankBalance()
     * @return
     */
    double getBankBalance();

    /**
     * @see BankService#getCustomers()
     * @return
     */
    ArrayList<Customer> getCustomers();

    Bank getBank();

    public ArrayList<Account> createCustomerAcconts(Customer customer, double initialDeposit, Account.AccountType acctType) throws InvalidAmountException;

    public Account createCustomerAccont(Customer customer, double initialDeposit, Account.AccountType acctType) throws InvalidAmountException;
    public List<Transaction> getTransactions();
    public void transferAccount(String drCustId, String debtorAcctNo ,String crCustomerId, String creditorAcctNo, double amount) throws InvalidAmountException;
}
