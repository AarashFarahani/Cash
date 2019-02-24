package com.cashcoding.model;

import java.time.LocalDateTime;

/**
 * Created by m.karandish on 2/24/2019.
 */
public class Transaction {
    private LocalDateTime txnDate;
    private String  particulars;
    private double txnAmount;

    public Transaction(LocalDateTime date, String particulars , double txnAmount){
        this.particulars = particulars;
        this.txnAmount = txnAmount;
        this.txnDate = date;
    }

    public LocalDateTime getTxnDate() {
        return txnDate;
    }

    public void setTxnDate(LocalDateTime txnDate) {
        this.txnDate = txnDate;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public double getTxnAmount() {
        return txnAmount;
    }

    public void setTxnAmount(double txnAmount) {
        this.txnAmount = txnAmount;
    }
}
