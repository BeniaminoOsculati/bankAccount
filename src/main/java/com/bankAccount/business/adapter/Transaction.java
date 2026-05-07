package com.bankAccount.business.adapter;

import com.bankAccount.driven.record.TransactionDTO;
import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;

/**
 * Presentation data for a transaction into a bank account.
 */
public class Transaction {
    private Date date;
    private String log;

    /**
     * Constructor with data for the transaction (used for the tests)
     * @param date the date of the transaction
     * @param log the string with the details to be logged
     */
    public Transaction(Date date, String log) {
        this.date = date;
        this.log = log;
    }

    /**
     * Constructor to copy transaction from a DTO object.
     * @param transactionDTO the transaction to copy.
     */
    public Transaction(TransactionDTO transactionDTO){
        this(transactionDTO.getDate(), transactionDTO.getLog());
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
        return o instanceof Transaction transaction && DateUtils.isSameDay(transaction.date, this.date) && transaction.log.equals(this.log);
    }

    /**
     * Get the date of the transaction
     * @return the date of the transaction
     */
    public Date getDate() {
        return date;
    }

    /**
     * Get the log of the transaction
     * @return the log of the transaction
     */
    public String getLog() {
        return log;
    }
}
