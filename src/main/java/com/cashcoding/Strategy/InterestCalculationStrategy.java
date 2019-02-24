package com.cashcoding.Strategy;

/**
 * Created by m.karandish on 2/13/2019.
 */
public interface InterestCalculationStrategy {
    double calculateInterest( double accountBalance); // we need to remove access modifier here
    // this method is not concerned about account type because of specific implementation

}
