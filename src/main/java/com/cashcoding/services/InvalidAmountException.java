package com.cashcoding.services;

/**
 * Created on 1/25/2019.
 */
public class InvalidAmountException extends Exception {
    /**
     * set an input message to the Exception
     * So it throws exception with defined message
     * @param message
     */
    public InvalidAmountException(String message){
        super(message);
    }
}
