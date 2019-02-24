package com.cashcoding.Strategy.Strategies;

import com.cashcoding.Strategy.InterestCalculationStrategy;

/**
 * Created by m.karandish on 2/13/2019.
 */
public class CurrentAccountInterestCalculation implements InterestCalculationStrategy {

    private static final String CURRENT = "CURRENT";

    @Override
    public double calculateInterest(double accountBalance) {
        return accountBalance*(0.02/12);
    }
}
