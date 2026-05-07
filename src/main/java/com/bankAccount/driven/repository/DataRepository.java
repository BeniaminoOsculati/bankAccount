package com.bankAccount.driven.repository;

import com.bankAccount.driven.record.BankAccountDTO;
import com.bankAccount.driven.record.TransactionDTO;
import com.bankAccount.driven.record.UserDTO;

import java.math.BigDecimal;
import java.util.List;

/**
 * Repository used to retrieve the data from the DB.
 */
public interface DataRepository {

    /**
     * Check if a user is enrolled, from the given userId.
     * @param userId the id of the user to check
     * @return the user if present, null otherwise
     */
    UserDTO enrolledUser(String userId);

    /**
     * Retrieve bank account for a given user
     * @param userDTO the user owner of the bank account
     * @return the bank account of the user if present, null otherwise
     */
    BankAccountDTO getBankAccountBalance(UserDTO userDTO);

    /**
     * Retrieve transactions of a user into the bank account for the given user
     * @param userDTO the user owner of the bank account
     * @return all the transactions made into the bank account of the user
     */
    List<TransactionDTO> getTransactions(UserDTO userDTO);

    /**
     * Deposit the given amount into the bank account of the given user
     * @param amount the amount to deposit
     * @param userDTO the user owner of the bank account
     * @return true if the deposit works, false otherwise
     */
    boolean deposit(BigDecimal amount, UserDTO userDTO);

    /**
     * Returns the current amount of the bank account for the given user
     * @param userDTO the user owner of the bank account
     * @return the amount of the bank account of the user
     */
    BigDecimal getAmount(UserDTO userDTO);

    /**
     * Take the given account form the bank account of the given user
     * @param amount the amount to take from the bank account
     * @param userDTO the user owner of the bank account
     * @return true if the retreat is working, false otherwise
     */
    boolean getMoney(BigDecimal amount, UserDTO userDTO);
}
