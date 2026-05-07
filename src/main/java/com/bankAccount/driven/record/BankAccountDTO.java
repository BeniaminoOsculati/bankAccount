package com.bankAccount.driven.record;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * POJO with all the data for the bank account.
 */
public class BankAccountDTO {
    private String id;
    private BigDecimal amount;
    private List<TransactionDTO> transactionsDTO;

    /**
     * Constructor with the initial amount for the bank account
     * @param amount the initial amount of the bank account
     */
    public BankAccountDTO(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
        this.transactionsDTO = new ArrayList<>();
    }

    /**
     * Get the amount of the bank account
     * @return the current amount of the bank account
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Add the given amount to the already present into the bank account
     * @param amount to add to the bank account
     */
    public void setAmount(BigDecimal amount) {
        this.amount = this.amount.add(amount);
    }

    /**
     * Remove the given amount to the already present into the bank account
     * @param amount for decrease the bank account
     */
    public void getMoney(BigDecimal amount){
        this.amount = this.amount.subtract(amount);
    }

    /**
     * Get all the transaction made into the bank account
     * @return list of transactions made into the bank account
     */
    public List<TransactionDTO> getTransactions() {
        return transactionsDTO;
    }

    /**
     * Add a new transaction to the bank account
     * @param operation the operation that we will log into the transactions
     * @param amount the amount involved into the transaction
     */
    public void addTransaction(String operation, BigDecimal amount){
        transactionsDTO.add(new TransactionDTO(new Date(), "Transaction "+ operation +" with amount "+amount));
    }

    /**
     * Get the id of the bank account
     * @return the id of the bank account
     */
    public String getId() {
        return id;
    }

    /**
     * Override of the generic toString method, to print the complete state of the bank account.
     */
    @Override
    public String toString(){
        return "Bank account: current amount: " + amount + " after " + transactionsDTO.size() + " transactions made";
    }

    /**
     * Override of generic equal to catch only the id.
     * @param o bank account to compare.
     */
    @Override
    public boolean equals(Object o){
        return o instanceof BankAccountDTO bankAccountDTO && bankAccountDTO.id == this.id;
    }
}