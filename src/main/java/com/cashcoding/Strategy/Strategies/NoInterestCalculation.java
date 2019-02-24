package com.cashcoding.Strategy.Strategies;

import com.cashcoding.Strategy.InterestCalculationStrategy;

/**
 * Created by m.karandish on 2/13/2019.
 */
public class NoInterestCalculation implements InterestCalculationStrategy{
    @Override
    public double calculateInterest(double accountBalance) {
        return accountBalance;
    }
}
