package com.bankAccount;

import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.adapter.Transaction;

import java.math.BigDecimal;
import java.util.List;

/**
 * Dispatcher to manage bank account.
 */
public interface BankAccountPorts {

    /**
     *  Get a bank account balance from a given userId.
     * @param userId the userId of the user.
     * @return the bank account of the given user
     */
    BankAccount getBankAccountBalance(String userId);

    /**
     *  Get all the transaction on the bank account of the given user.
     * @param userId the userId of the user.
     * @return list of transactions made into the bank account of the given user
     */
    List<Transaction> getTransactions(String userId);

    /**
     *  Deposit a specific amount of money into the given user bank account.
     * @param userId the userId of the user.
     * @param amount the amount to deposit into the bank account.
     * @return true if the deposit works, false otherwise
     */
    boolean deposit(String userId, BigDecimal amount);

    /**
     *  Get a specific amount of money from the given user bank account.
     * @param userId the userId of the user.
     * @param amount the amount to get from the bank account.
     * @return true if the retreat works, false otherwise
     */
    boolean getMoney(String userId, BigDecimal amount);
}
