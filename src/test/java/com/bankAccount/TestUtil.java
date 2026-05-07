package com.bankAccount;

import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.adapter.Transaction;
import com.bankAccount.driven.record.BankAccountDTO;
import com.bankAccount.driven.record.TransactionDTO;
import com.bankAccount.driven.record.UserDTO;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Test utils class to support tests classes
 */
public class TestUtil {
    public static final String ID_USER1 = "1";
    public static final String ID_USER2 = "2";
    public static final String ID_USER3 = "3";

    /**
     * Get bankAccountDTO object for user 1
     * @return the bankAccountDTO of user 1
     */
    public static BankAccountDTO bankAccountDTOUser1(){
        return new BankAccountDTO(ID_USER1, new BigDecimal(0));
    }

    /**
     * Get bankAccountDTO object for user 2
     * @return the bankAccountDTO of user 2
     */
    public static BankAccountDTO bankAccountDTOUser2(){
        return new BankAccountDTO(ID_USER2, new BigDecimal(100));
    }

    /**
     * Get bankAccountDTO object for user 3
     * @return the bankAccountDTO of user 3
     */
    public static BankAccountDTO bankAccountDTOUser3(){
        return new BankAccountDTO(ID_USER3, new BigDecimal(50));
    }

    /**
     * Get bankAccount object for user 1
     * @return the bankAccount of user 1
     */
    public static BankAccount bankAccountUser1(){
        return new BankAccount(ID_USER1, new BigDecimal(0));
    }

    /**
     * Get bankAccount object for user 2
     * @return the bankAccount of user 2
     */
    public static BankAccount bankAccountUser2(){
        return new BankAccount(ID_USER2, new BigDecimal(100));
    }

    /**
     * Get bankAccount object for user 3
     * @return the bankAccount of user 3
     */
    public static BankAccount bankAccountUser3(){
        return new BankAccount(ID_USER3, new BigDecimal(50));
    }

    /**
     * Get transactions (DTO) list of user 1
     * @return list of transactions (DTO) user 1
     */
    public static List<TransactionDTO> transactionDTOUser1(){
        return new ArrayList<>() {
            {
                add(new TransactionDTO(new Date(), "Transaction deposit with amount 10"));
                add(new TransactionDTO(new Date(), "Transaction get with amount 1"));
                add(new TransactionDTO(new Date(), "Transaction deposit with amount 100"));
            }
        };
    }

    /**
     * Get transactions (DTO) list of user 2
     * @return list of transactions (DTO) user 2
     */
    public static List<TransactionDTO> transactionDTOUser2(){
        return new ArrayList<>() {
            {
                add(new TransactionDTO(new Date(), "Transaction get with amount 100"));
                add(new TransactionDTO(new Date(), "Transaction deposit with amount 1"));
            }
        };
    }

    /**
     * Get transactions (DTO) list of user 3
     * @return list of transactions (DTO) user 3
     */
    public static List<TransactionDTO> transactionDTOUser3(){
        return new ArrayList<>() {
            {
                add(new TransactionDTO(new Date(), "Transaction deposit with amount 50"));
            }
        };
    }

    /**
     * Get transactions list of user 1
     * @return list of transactions user 1
     */
    public static List<Transaction> transactionUser1(){
        return new ArrayList<>() {
            {
                add(new Transaction(new Date(), "Transaction deposit with amount 10"));
                add(new Transaction(new Date(), "Transaction get with amount 1"));
                add(new Transaction(new Date(), "Transaction deposit with amount 100"));
            }
        };
    }

    /**
     * Get transactions list of user 2
     * @return list of transactions user 2
     */
    public static List<Transaction> transactionUser2(){
        return new ArrayList<>() {
            {
                add(new Transaction(new Date(), "Transaction get with amount 100"));
                add(new Transaction(new Date(), "Transaction deposit with amount 1"));
            }
        };
    }

    /**
     * Get transactions list of user 3
     * @return list of transactions user 3
     */
    public static List<Transaction> transactionUser3(){
        return new ArrayList<>() {
            {
                add(new Transaction(new Date(), "Transaction deposit with amount 50"));
            }
        };
    }

    /**
     * Get user 1 object
     * @return user 1
     */
    public static UserDTO user1(){
        return new UserDTO(ID_USER1, "User1", "User1", "user1@mail.com", new Date());
    }

    /**
     * Get user 2 object
     * @return user 2
     */
    public static UserDTO user2(){
        return new UserDTO(ID_USER2, "User2", "User2", "user2@mail.com", new Date());
    }

    /**
     * Get user 3 object
     * @return user 3
     */
    public static UserDTO user3(){
        return new UserDTO(ID_USER3, "User3", "User3", "user3@mail.com", new Date());
    }
}
