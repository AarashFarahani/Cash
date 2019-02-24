package com.cashcoding.Strategy;

import com.cashcoding.model.Account;

/**
 * Created by m.karandish on 2/13/2019.
 */
public class InterestCalculator {

    private final InterestCalculationStrategyFactory interestCalculationStrategyFactory = new InterestCalculationStrategyFactory();

    public double calculateInterest(Account.AccountType accountTypes,
                                    double accountBalance) {
        InterestCalculationStrategy interestCalculationStrategy =
                interestCalculationStrategyFactory.getInterestCalculationStrategy(accountTypes);

        return interestCalculationStrategy.calculateInterest(accountBalance);

    }
}
