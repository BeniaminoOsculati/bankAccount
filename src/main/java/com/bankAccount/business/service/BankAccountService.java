package com.bankAccount.business.service;

import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.adapter.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Declaration of business logics for bank account management
 */
public interface BankAccountService {
    /**
     * Get the bank account related to the given user
     * @param userId the userId of the owner of the bank account
     * @return the bank account related to the userId if present, null otherwise
     */
    BankAccount getBankAccountBalance(String userId);

    /**
     * Get all the transactions made into the bank account of the given user
     * @param userId the userId of the owner of the bank account
     * @return the list of the transactions made into the bank account, if there are no transactions empty list
     */
    List<Transaction> getTransactions(String userId);

    /**
     * Deposit a specific amount into the bank account related to the given user
     * @param amount the amount to deposit into the bank account
     * @param userId the userId of the owner of the bank account
     * @return true if the deposit works, false otherwise
     */
    boolean deposit(BigDecimal amount, String userId);

    /**
     * Get money from the bank account of the given user
     * @param amount the amount to retreat from the bank account
     * @param userId the userId of the owner of the bank account
     * @return if there are enough money on the bank account and the retreat works, false otherwise
     */
    boolean getMoney(BigDecimal amount, String userId);
}
