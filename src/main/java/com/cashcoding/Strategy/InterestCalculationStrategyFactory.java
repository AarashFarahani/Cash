package com.cashcoding.Strategy;

import com.cashcoding.Strategy.Strategies.*;
import com.cashcoding.model.Account;

/**
 * Created by m.karandish on 2/13/2019.
 */
public class InterestCalculationStrategyFactory {
    //strategies for calculation interest
    private final InterestCalculationStrategy currentAccountInterestCalculation = new CurrentAccountInterestCalculation();
    private final InterestCalculationStrategy savingAccountInterestCalculation = new SavingAccountInterestCalculation();
    private final InterestCalculationStrategy standardMarketAccountInterestCalculation = new StandardMarketAccountInterestCalculation();
    private final InterestCalculationStrategy highRollerAccountInterestCalculation = new HighRollerAccountInterestCalculation();
    private final InterestCalculationStrategy noInterestCalculation = new NoInterestCalculation();

    public InterestCalculationStrategy getInterestCalculationStrategy(Account.AccountType accountTypes) {
        switch (accountTypes){
            case SAVING:
                return savingAccountInterestCalculation;
            case CURRENT:
                return currentAccountInterestCalculation;
            default:
                return noInterestCalculation;
        }
    }
}
