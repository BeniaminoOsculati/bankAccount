package com.bankAccount.driven.record;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * POJO for a transaction into a bank account, used to keep track of the operations done.
 */
public class TransactionDTO {
    private Date date;
    private String log;

    /**
     * Constructor with data for the transaction.
     * @param date the date of the transaction
     * @param log the string with the details to be logged
     */
    public TransactionDTO(Date date, String log) {
        this.date = date;
        this.log = log;
    }

    /**
     * Get the date of the transaction
     * @return the date of the transaction
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get log of the transaction
     * @return the log of the transaction
     */
    public String getLog() {
        return log;
    }

    /**
     * Override of the generic toString method, to print the complete state of the transaction.
     */
    @Override
    public String toString(){
        return "Date: " + date.toString() + ", transaction: " + log;
    }

    /**
     * Override of generic equal to catch only the log field.
     * Here for simplicity we check only that the transaction happens in the same day
     * @param o transaction to compare.
     */
    @Override
    public boolean equals(Object o){
        return o instanceof TransactionDTO transactionDTO && DateUtils.isSameDay(transactionDTO.date, this.date) && transactionDTO.log.equals(this.log);
    }
}
