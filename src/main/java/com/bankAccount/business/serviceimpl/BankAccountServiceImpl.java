package com.bankAccount.business.serviceimpl;

import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.adapter.Transaction;
import com.bankAccount.business.util.RecordToPresentationConverter;
import com.bankAccount.driven.record.BankAccountDTO;
import com.bankAccount.driven.record.UserDTO;
import com.bankAccount.driven.repository.DataRepository;
import com.bankAccount.business.service.BankAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 *  Implementation of {@link BankAccountService}
 *  Business logics for bank account management
 */
@Service
public class BankAccountServiceImpl implements BankAccountService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    @Autowired
    private DataRepository dataRepository;

    @Override
    public BankAccount getBankAccountBalance(String userId) {
        UserDTO userDTO = enrolledUser(userId);
        if(userDTO != null){
            BankAccountDTO bankAccountDTO = dataRepository.getBankAccountBalance(userDTO);
            if(bankAccountDTO == null){
                LOGGER.error("User with id {} have no bank account associated", userId);
            }
            return RecordToPresentationConverter.convertBankAccount(bankAccountDTO);
        }
        LOGGER.error("User with id {} not enrolled", userId);
        return null;
    }

    @Override
    public List<Transaction> getTransactions(String userId) {
        UserDTO userDTO = enrolledUser(userId);
        if(userDTO != null){
            return RecordToPresentationConverter.convertTransaction(dataRepository.getTransactions(userDTO));
        }
        LOGGER.error("User with id {} not enrolled", userId);
        return Collections.EMPTY_LIST;
    }

    @Override
    public boolean deposit(BigDecimal amount, String userId) {
        UserDTO userDTO = enrolledUser(userId);
        if(userDTO != null){
            if(amount.compareTo(new BigDecimal(0)) > 0){
                return dataRepository.deposit(amount, userDTO);
            }
            LOGGER.error("Impossible to add amount {} to bank account of user {}", amount, userId);
            return false;
        }
        LOGGER.error("User with id {} not enrolled", userId);
        return false;
    }

    @Override
    public boolean getMoney(BigDecimal amount, String userId) {
        UserDTO userDTO = enrolledUser(userId);
        if(amount.compareTo(new BigDecimal(0)) > 0){
            if(userDTO != null){
                if(dataRepository.getAmount(userDTO).compareTo(amount) >= 0){
                    return dataRepository.getMoney(amount, userDTO);
                }
                LOGGER.error("Not enough money on the account of user {} for get amount {}", userId, amount);
                return false;
            }
            LOGGER.error("User with id {} not enrolled", userId);
            return false;
        }
        LOGGER.error("Import not valid {}", amount);
        return false;
    }

    /**
     * Method to check if the given userId is associated to an enrolled user
     * @param userId the id of the user
     * @return the user if it's enrolled, null otherwise
     */
    private UserDTO enrolledUser(String userId){
        try{
            return dataRepository.enrolledUser(userId);
        }
        catch (Exception e){
            return null;
        }
    }
}
