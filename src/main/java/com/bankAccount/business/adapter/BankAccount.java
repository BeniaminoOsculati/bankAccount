package com.bankAccount.business.adapter;

import com.bankAccount.business.util.RecordToPresentationConverter;
import com.bankAccount.driven.record.BankAccountDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Presentation data for the bank account.
 */
public class BankAccount {
    private String id;
    private BigDecimal amount;
    private List<Transaction> transactions;

    /**
     * Constructor with the initial amount for the bank account (used for the tests)
     * @param id the id of the bank account
     * @param amount the initial amount of the bank account
     */
    public BankAccount(String id, BigDecimal amount) {
        this.id = id;
        this.amount = amount;
        this.transactions = new ArrayList<>();
    }

    /**
     * Constructor to copy bank account from a DTO object.
     * @param bankAccountDTO the bank account to copy
     */
    public BankAccount(BankAccountDTO bankAccountDTO){
        this.id = bankAccountDTO.getId();
        this.amount = bankAccountDTO.getAmount();
        this.transactions = RecordToPresentationConverter.convertTransaction(bankAccountDTO.getTransactions());
    }

    /**
     * Override of the generic toString method, to print the complete state of the bank account.
     */
    @Override
    public String toString(){
        return "Bank account: current amount: " + amount + " after " + transactions.size() + " transactions made";
    }

    /**
     * Override of generic equal to catch only the id.
     * @param o bank account to compare.
     */
    @Override
    public boolean equals(Object o){
        return o instanceof BankAccount bankAccount && bankAccount.id == this.id;
    }

    /**
     * Get the bank account id
     * @return the id of the bank account
     */
    public String getId() {
        return id;
    }

    /**
     * Get the amount of the bank account
     * @return the amount of the bank account
     */
    public BigDecimal getAmount() {
        return amount;
    }

    /**
     * Get the transactions list of the bank account
     * @return the transaction list of the bank account
     */
    public List<Transaction> getTransactions() {
        return transactions;
    }
}