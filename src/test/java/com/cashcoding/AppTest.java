package com.cashcoding;

import com.cashcoding.Builder.BankAccount;
import com.cashcoding.model.Account;
import com.cashcoding.model.Customer;
import com.cashcoding.services.BankService;
import com.cashcoding.services.IBankService;
import com.cashcoding.services.InvalidAmountException;
import org.junit.*;
import org.junit.experimental.categories.Category;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Unit test for CashCoding App.
 */
public class AppTest {

    private static IBankService services;
    private static  Customer customer;
    private static  Account accounts;

    @BeforeClass
    public static  void init() throws InvalidAmountException {
        services = BankService.getInstance();
        customer = services.createCustomer("Maryam", 50);
        accounts = customer.getListOfAccounts().get(0);

    }


    /**
     * This Method tests withdrawing value in positive case
     * @throws InvalidAmountException
     */
    @Test
    @Category(PositiveCategoryTest.class)
    public void customerWithdrawalTest() throws InvalidAmountException {
        customer.getListOfAccounts().get(0).setBalance(20);
        System.out.println(customer.getName() + "'s Balance Changed to " +
                accounts.getBalance());
        assertEquals(10.0, this.services.withdrawAccount(10.0, this.customer.getUid(), customer.getListOfAccounts().get(0).getAccoutnNo()));
        System.out.println("--------------");
    }

    /**
     * This method test the depositing process in positive case
     * @throws InvalidAmountException
     */
    @Test
    @Category(PositiveCategoryTest.class)
    public void customerDepositTest() throws InvalidAmountException {
        this.customer.getListOfAccounts().get(0).setBalance(40);
        System.out.println(customer.getName() + "'s Balance Changed to " +
                accounts.getBalance());
        assertEquals(50.0, this.services.depositAccount(10.0, this.customer.getUid(), this.customer.getListOfAccounts().get(0).getAccoutnNo()));
        System.out.println("--------------");

    }

    /**
     * This method is just for creating a list of customer.
     * @throws InvalidAmountException
     */
    @Test
    @Ignore
    public void createListofCustomer() throws InvalidAmountException {
        this.customer = services.createCustomer("Alice", 80);
        this.customer = services.createCustomer("Ana", 20);
        this.customer = services.createCustomer("Joun", 10);
        this.customer = services.createCustomer("Mark", 30);

        services.getBank().getCustomers().forEach(c -> System.out.println(c.getName() + "    " + c.getListOfAccounts().get(0).getBalance() + "     " + c.getUid()));
    }

    /**
     * This method tests in case of withdrawing greater amount than balance
     * returns error
     * @throws InvalidAmountException
     */
    @Test
    @Category(NegativeCategoryTest.class)
    public void customerWithdrawalTestGreaterThanBalance() throws InvalidAmountException {
        customer.getListOfAccounts().get(0).setBalance(10);
        System.out.println(customer.getName()+"'s balance changed into "+ accounts.getBalance());
        assertEquals("The withdrawing amount is more than Balance!!",
                0.0, services.withdrawAccount(30,customer.getUid(), customer.getListOfAccounts().get(0).getAccoutnNo()));
        System.out.println("--------------");

    }

    /**
     * This method tests that in case of assigning negative value directly
     * to the balance throws correct Exception
     * @throws InvalidAmountException
     */
    @Test(expected = InvalidAmountException.class)
    @Category(NegativeCategoryTest.class)
    public void negatinveValueAssignedToBalance() throws InvalidAmountException {
        accounts.setBalance(-50);
        System.out.println("--------------");
    }


    @Rule
    public ExpectedException exception = ExpectedException.none();

    // This method Tests in case of Negative value the appropriate
    // Exception would occur
    @Test
    @Category(NegativeCategoryTest.class)
    public void negatinveValueAssignedToBalanceCreate() throws InvalidAmountException {
        exception.expect(InvalidAmountException.class);
        exception.expectMessage(containsString("Negative"));
        services.createCustomer("Paul", -50);
        System.out.println("--------------");
    }

    /**
     * This method is a positive testing case
     * pass a value and expect a specific balance after
     * executing withdrawAccount method
     * @throws InvalidAmountException
     */
    @Test
    @Category(PositiveCategoryTest.class)
    public void testWithdrawalWithID() throws InvalidAmountException {
        this.customer.getListOfAccounts().get(0).setBalance(60);
        System.out.println(customer.getName() + "'s Balance Changed to " +
                accounts.getBalance());
        assertEquals(10.0, services.withdrawAccount(50,this.customer.getUid(), this.customer.getListOfAccounts().get(0).getAccoutnNo()));
        System.out.println("--------------");
    }

    /**
     * This method returns total balance of the bank
     * first calculate sum of current list then
     * execute the related method and expect to have same value.
     * @throws InvalidAmountException
     */
    @Test
    public void testGetBankTotalBalance() throws InvalidAmountException {

        services.getBank().getCustomers().forEach(c ->
                System.out.println(c.getName() + "    " + c.getListOfAccounts().get(0).getBalance() + "     " + c.getUid()));
        Double testBankBalance = services.getBank().getCustomers()
                .stream().mapToDouble(p -> p.getListOfAccounts().get(0).getBalance()).sum();
        assertEquals(testBankBalance,services.getBankBalance());
        System.out.println("--------------");

    }

    /**
     * This method manipulate balance of different customers
     * To test that balance of each customer changed correctly
     * @throws InvalidAmountException
     */
    @Test
    public void testListOfCutomerChangedBalance()
            throws InvalidAmountException
    {
        createListofCustomer();
        services.getBankBalance();
        ArrayList<Customer> listOfCustomer = services.getCustomers();

        services.withdrawAccount(20, listOfCustomer.get(3).getUid(),listOfCustomer.get(3).getListOfAccounts().get(0).getAccoutnNo());
        services.withdrawAccount(10, listOfCustomer.get(1).getUid(),listOfCustomer.get(1).getListOfAccounts().get(0).getAccoutnNo());
        services.depositAccount(50, listOfCustomer.get(0).getUid(),listOfCustomer.get(0).getListOfAccounts().get(0).getAccoutnNo());
        services.depositAccount(60, listOfCustomer.get(4).getUid(),listOfCustomer.get(4).getListOfAccounts().get(0).getAccoutnNo());
        services.depositAccount(20, listOfCustomer.get(3).getUid(),listOfCustomer.get(3).getListOfAccounts().get(0).getAccoutnNo());
        services.getBankBalance();

        ArrayList<Double> customersBalance = new ArrayList<Double>();
        listOfCustomer.forEach(l -> {
            customersBalance.add(l.getListOfAccounts().get(0).getBalance());
        });

        List<Double> expected = Arrays.asList(100.0, 70.0, 20.0, 30.0, 90.0);

        assertThat(customersBalance, is(expected));
        System.out.println("--------------");

    }

    @Test
    public void requestedTestCase() throws InvalidAmountException {

        customer.getListOfAccounts().get(0).setBalance(0.0);
        customer.setName("Alice");
        System.out.println(customer.getName() + "'s Balance Changed to " +
                customer.getListOfAccounts().get(0).getBalance());

/*
           These three line are commented because if these codes run
           they will return total bank balance as it is requested
           in the question test case.
           It means that it returns total bank balance equals to 10,
           but with commenting this three lines we will have
           the total bank balance consist of list of customers

           ArrayList<Customer> bankCustomers = new ArrayList<Customer>();
           bankCustomers.add(customer);
           services.getBank().setCustomers(bankCustomers);
*/
        services.depositAccount(30, customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        services.withdrawAccount(20, customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        services.getBankBalance();
        services.withdrawAccount(20, customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        assertThat(customer.getListOfAccounts().get(0).getBalance(), is(10.0));
        System.out.println("--------------");

    }

    @Test
    public void testCreatingAccountForCustomer() throws InvalidAmountException {

        services.createCustomerAccont(customer,30.0, Account.AccountType.NORMAL);
        customer.getListOfAccounts().forEach(acc -> System.out.println("customer Name: " + customer.getName() +
                " customer Account Balance: " + acc.getBalance() ));
        System.out.println( " Total customer Balance: " + customer.getListOfAccounts().get(0).getBalance() );
        assertThat(services.createCustomerAccont(customer,50.0,Account.AccountType.NORMAL).getBalance(), is(50.0));
        System.out.println("--------------");
    }

    @Test
    public void testInterestCalculation() throws InvalidAmountException {

        customer.getListOfAccounts().forEach(acc -> System.out.println("customer Name: " + customer.getName() +
                " customer Account Balance: " + acc.getBalance() ));
        customer.getListOfAccounts().get(0).setAccountType(Account.AccountType.SAVING);
       customer.getListOfAccounts().forEach(a -> System.out.println("Name: " + customer.getName()+ " account Balance:  " + a.getBalance()));
       services.withdrawAccount(30.0,customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        System.out.println("**********");
        customer.getListOfAccounts().forEach(a -> System.out.println("Name: " + customer.getName()+ " account Balance:  " + a.getBalance()));

        //assertThat(services.createCustomerAccont(customer,50.0).get(0).getBalance(), is(50.0));
        System.out.println("--------------");
    }

    @Test
    public void testbuilderDesignPattern(){
        BankAccount bankAccount = new BankAccount.Builder(1234)
                .atBranch("1002")
                .atRate(0.02)
                .openingBalance(20.0)
                .withOwner("Maryam").build();
        System.out.println("bankAccount balance is : " + bankAccount.getBalance());
        assertEquals(20.0,bankAccount.getBalance());
    }

    @Test
    public void testTransactionHistory() throws InvalidAmountException {
        int numberOfTransactions = 5;
        customer.getListOfAccounts().get(0).setBalance(100.0);
        System.out.println(customer.getListOfAccounts().get(0).getBalance());

        services.withdrawAccount(10.0,customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        services.depositAccount(20.0,customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        services.withdrawAccount(30.0,customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        services.withdrawAccount(40.0,customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        services.depositAccount(50.0,customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        services.depositAccount(60.0,customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        services.withdrawAccount(70.0,customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        services.getTransactions().forEach( tra -> System.out.println("amount: " + tra.getTxnAmount()+ " particular: " + tra.getParticulars()
                +" date: " + tra.getTxnDate()));
    }

    @Test
    public void testTransferTwoAccounts() throws InvalidAmountException {
        Customer debtorCutomer = services.createCustomer("Jack", 120.0);
        Customer creditorCustomer = services.createCustomer("Sara", 50.0);

        System.out.println("Sara " + creditorCustomer.getListOfAccounts().get(0).getBalance());
        System.out.println("Jack " + debtorCutomer.getListOfAccounts().get(0).getBalance());

        System.out.println("Transferring...");
        services.transferAccount(debtorCutomer.getUid(),debtorCutomer.getListOfAccounts().get(0).getAccoutnNo(),
                creditorCustomer.getUid(),creditorCustomer.getListOfAccounts().get(0).getAccoutnNo(),60);

        System.out.println("Sara" + creditorCustomer.getListOfAccounts().get(0).getBalance());
        System.out.println("Jack" + debtorCutomer.getListOfAccounts().get(0).getBalance());

    }

    @AfterClass
    public static void afterTest(){
        System.out.println("Thank YOU :) !!!");
        System.out.println("--------------");
    }

}
