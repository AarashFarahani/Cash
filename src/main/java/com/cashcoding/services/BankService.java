package com.cashcoding.services;

import com.cashcoding.Strategy.InterestCalculator;
import com.cashcoding.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created on 1/25/2019.
 */
public class BankService implements IBankService {

    private static BankService instance;
    private Bank bank;
    private TransferGL transferDr;
    private TransferGL transferCr;

    List<Transaction> transactions = new ArrayList<Transaction>();
    List<TransferGL> bankTransfers = new ArrayList<TransferGL>();

    private BankService() {
    }

    public static BankService getInstance() {
        if (instance == null) {
            instance = new BankService();
            instance.bank = new Bank();
            instance.transferCr = new TransferGL(UUID.randomUUID().toString());
            instance.transferDr = new TransferGL(UUID.randomUUID().toString());
        }

        return instance;
    }

    /**
     * Returning a List of cutomers
     * @return
     */
    public ArrayList<Customer> getCustomers(){
        return this.bank.getCustomers();
    }

    /**
     * This method Withdraw a specific amount of money from a customer balance
     * It has double input parameter for money and String parameter for Unique ID
     * @param amount
     * @param uid
     * @return
     * @throws InvalidAmountException
     */
    public double withdrawAccount(double amount, String uid, String acctNo) throws InvalidAmountException {
        double withdrawalBalance = 0.0;
        Transaction transaction = null;
        Customer customer = this.bank.getCustomers()
                .stream().filter(a -> a.getUid().equals(uid)).findFirst().get();

        Account account = customer.getListOfAccounts()
                .stream().filter(a -> a.getAccoutnNo().equals(acctNo)).findFirst().get();

//        double interest = 0.0;

        LocalDateTime currentDate = LocalDateTime.now();

        InterestCalculator interestCalculator = new InterestCalculator();
//        interest = interestCalculator.calculateInterest(account.getAccountType(),amount);
//        amount = amount-interest;

        System.out.println(customer.getName() + " is withdrawing " + amount);

        if (preventWithdrawal(amount, account))
        {
            withdrawalBalance = account.getBalance() - amount;
            account.setBalance(withdrawalBalance);
        }

        transaction =  new Transaction(currentDate,"withdrawing "+ amount , account.getBalance());
        transactions.add(transaction);

        display(customer,account.getAccoutnNo());
        return withdrawalBalance;
    }


    /**
     * This method deposited a specific amount of money to customer balance
     * It has double input parameter for money and String parameter for Unique ID
     * @param amount
     * @param uid
     * @return
     * @throws InvalidAmountException
     */
    public double depositAccount(double amount, String uid, String acctNo) throws InvalidAmountException
    {
        Transaction transaction = null;
        LocalDateTime currentDate = LocalDateTime.now();

        Customer customer = this.bank.getCustomers()
                .stream().filter(a -> a.getUid().equals(uid)).findFirst().get();

        Account account = customer.getListOfAccounts()
                .stream().filter(a -> a.getAccoutnNo().equals(acctNo)).findFirst().get();

        double depositAmt = 0.0;

        depositAmt = account.getBalance() + amount;
        System.out.println(customer.getName() + " is depositing  " + amount);
        account.setBalance(depositAmt);

        transaction =  new Transaction(currentDate,"dipositing "+ amount , account.getBalance());
        transactions.add(transaction);

        display(customer,account.getAccoutnNo());
        return depositAmt;
    }

    /**
     * This private method check the validation of not withdrawing more than customer balance
     * @param amount
     * @param customer
     * @return
     */
    private boolean preventWithdrawal(double amount, Account account)
    {
        if (amount > account.getBalance())
        {
            System.out.println("The Requested Amount is greater than the Total balance of Account");
            return false;
        } else
            return true;

    }

    /**
     * This private method display customer's information
     * @param customer
     */
    private void display(Customer customer, String acctNo)
    {
        Account account = customer.getListOfAccounts()
                .stream().filter(a -> a.getAccoutnNo().equals(acctNo)).findFirst().get();
        System.out.println(customer.getName() + " " + " Balance is : " + account.getBalance());
    }

    /**
     * This method create a customer based on parameters which are passed into it
     * Then add each customer to the list of customers and return the customer Object
     * @param name
     * @param balance
     * @return
     * @throws InvalidAmountException
     */
    public Customer createCustomer(String name, double balance)
            throws InvalidAmountException
    {
        Customer customer = new Customer(name, balance);
        customer.setUid(UUID.randomUUID().toString());
        createCustomerAcconts(customer,balance, Account.AccountType.NORMAL);
        this.bank.getCustomers().add(customer);

        return customer;
    }

    /**
     * This method return Total bank Balance.
     * @return
     */
    public double getBankBalance()
    {
        Double sum = 0.0;
        for(Customer cust : this.bank.getCustomers())
         sum += cust.getListOfAccounts()
                .stream().mapToDouble(p -> p.getBalance()).sum();

        System.out.println("The Bank Total Balance is : " + sum);

        return sum;
    }

    public Bank getBank() {
        return this.bank;
    }

    public ArrayList<Account> createCustomerAcconts(Customer customer, double initialDeposit, Account.AccountType acctType) throws InvalidAmountException {
        Account account = createCustomerAccont(customer,initialDeposit,acctType);
        customer.getListOfAccounts().add(account);
        return customer.getListOfAccounts();
    }


    public Account createCustomerAccont(Customer customer, double initialDeposit, Account.AccountType acctType) throws InvalidAmountException {
        acctType = acctType!=null ? acctType:Account.AccountType.NORMAL;
        Account account = new Account(initialDeposit,acctType);
        account.setAccoutnNo(UUID.randomUUID().toString());
        return account;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void transferAccount(String drCustId, String debtorAcctNo ,String crCustomerId, String creditorAcctNo, double amount) throws InvalidAmountException {

        Customer crCustomer = this.bank.getCustomers()
                .stream().filter(a ->
                        a.getUid().equals(crCustomerId)).findFirst().get();

        Customer drCustomer = this.bank.getCustomers()
                .stream().filter(a ->
                        a.getUid().equals(drCustId)).findFirst().get();

        Account creditorAcct = crCustomer.getListOfAccounts()
                .stream().filter(a -> a.getAccoutnNo().equals(creditorAcctNo)).findFirst().get();
        Account debtorAcct = drCustomer.getListOfAccounts()
                .stream().filter(a -> a.getAccoutnNo().equals(debtorAcctNo)).findFirst().get();


        transferCr.setBalance(amount);
        transferCr.setCreditorAcct(creditorAcct);
        transferCr.setTxnIndicator("TRDR"); //todo
        bankTransfers.add(transferCr);

        transferDr.setDebtorAcct(debtorAcct);
        transferDr.setTxnIndicator("TRCR"); //todo
        transferDr.setBalance(amount);
        bankTransfers.add(transferDr);

        bankTransfers.forEach(b -> System.out.println(b.getTxnIndicator() + " " + b.getBalance()));
        depositAccount(amount,crCustomerId,creditorAcctNo);
        withdrawAccount(amount,drCustId,debtorAcctNo);


    }
}
