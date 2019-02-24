package com.cashcoding.model;

/**
 * Created by m.karandish on 2/24/2019.
 */
public class TransferGL extends Account{


    private String txnIndicator;
    private Account creditorAcct;
    private Account debtorAcct;

    public TransferGL(String accountNo){

        super.setAccoutnNo(accountNo);
    }

    public String getTxnIndicator() {
        return txnIndicator;
    }

    public void setTxnIndicator(String txnIndicator) {
        this.txnIndicator = txnIndicator;
    }

    public Account getCreditorAcct() {
        return creditorAcct;
    }

    public void setCreditorAcct(Account creditorAcct) {
        this.creditorAcct = creditorAcct;
    }

    public Account getDebtorAcct() {
        return debtorAcct;
    }

    public void setDebtorAcct(Account debtorAcct) {
        this.debtorAcct = debtorAcct;
    }
}


