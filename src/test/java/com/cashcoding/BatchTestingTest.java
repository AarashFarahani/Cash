package com.cashcoding;

import com.cashcoding.model.Bank;
import com.cashcoding.model.Customer;
import com.cashcoding.services.BankService;
import com.cashcoding.services.InvalidAmountException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

/**
 * Created on 1/25/2019.
 */
@RunWith(Parameterized.class)
/**
 * This class is designed for testing a customer with a list of inputs and
 * get result with the expected outputs
 */
public class BatchTestingTest {
    private BankService service = BankService.getInstance();
    private double input;
    private double expected;

    /**
     * sets the parameters
     * @return
     */
    @Parameterized.Parameters
    public static List<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {50.0, 130.0},
                {-20.0, 110.0},
                {60.0, 170.0},
                {-100.0, 70.0},
                {-50.0, 20.0},
                {-50.0, 20.0},

        });
    }

    public BatchTestingTest(double input, double expected) {
        this.input = input;
        this.expected = expected;
    }

    /**
     * This class tests withdrawing and depositing balance of a customer more than once
     * it is assumes that positive inputs are used for depositing
     * and negative inputs are used for withdrawing
     */
    @Test
    public void test() throws InvalidAmountException {
        Bank bank = service.getBank();
        Customer customer = service.createCustomer( "Jack", 80.0);
        if (input >= 0)
            service.depositAccount(input, customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        else
            service.withdrawAccount(input, customer.getUid(),customer.getListOfAccounts().get(0).getAccoutnNo());
        System.out.println("--------------");
    }

}
