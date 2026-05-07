package com.bankAccount.business.util;

import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.adapter.Transaction;
import com.bankAccount.driven.record.BankAccountDTO;
import com.bankAccount.driven.record.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Util to convert DTO objects to presentation objects
 */
public class RecordToPresentationConverter {
    private static final Logger LOGGER = LoggerFactory.getLogger(RecordToPresentationConverter.class);

    /**
     * Method that converts a transactions list from integration object (DTO) to presentation object.
     * This method use a simply foreach to convert every single element of the list, with a long list can be expensive for the time,
     * but works good for ours easy examples.
     * @param transactionsDTO the list of TransactionDTO that must be converted
     * @return the list of transaction converted into presentation objects if there are no errors, empty list otherwise
     */
    public static List<Transaction> convertTransaction(List<TransactionDTO> transactionsDTO){
        try{
            List<Transaction> transactions = new ArrayList<Transaction>();
            for(TransactionDTO transactionDTO : transactionsDTO){
                transactions.add(new Transaction(transactionDTO.getDate(), transactionDTO.getLog()));
            }
            return transactions;
        }
        catch(Exception e){
            LOGGER.error("Error while converting transactions from record to presentation");
            return Collections.EMPTY_LIST;
        }
    }

    /**
     * Method that converts a bank account from integration object (DTO) to presentation object.
     * @param bankAccountDTO the bankAccount that must be converted
     * @return the bank account converted into presentation objects if there are no errors, null otherwise
     */
    public static BankAccount convertBankAccount(BankAccountDTO bankAccountDTO){
        try {
            return new BankAccount(bankAccountDTO);
        }
        catch (Exception e){
            LOGGER.error("Error while converting bank account from record to presentation");
            return null;
        }
    }
}
