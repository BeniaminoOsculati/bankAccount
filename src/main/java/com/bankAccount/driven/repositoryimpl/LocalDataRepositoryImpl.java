package com.bankAccount.driven.repositoryimpl;

import com.bankAccount.driven.record.BankAccountDTO;
import com.bankAccount.driven.record.TransactionDTO;
import com.bankAccount.driven.record.UserDTO;
import com.bankAccount.driven.repository.DataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementation of {@link DataRepository}
 * For simply the usage in local is managed with a list of users and a relation with the relative bank accounts
 * The first idea was to read a CSV file but, because it's only an example, I think it's the same
 * For all the objects we use String instead of UUID because it's easier to use in order to have as much as possible tests
 */
@Repository
public class LocalDataRepositoryImpl implements DataRepository {
    private static final Logger LOGGER = LoggerFactory.getLogger(LocalDataRepositoryImpl.class);
    private static final String DEPOSIT = "deposit";
    private static final String GET = "get";

    /**
     * user list, simulation of a table with USER(id, name, surname, email, date)
     */
    private List<UserDTO> usersList = Arrays.asList(new UserDTO("1", "User1", "User1", "user1@mail.com", new Date()),
            new UserDTO("2", "User2", "User2", "user2@mail.com", new Date()),
            new UserDTO("3", "User3", "User3", "user3@mail.com", new Date()));

    /**
     * simulation of a relation between users and bank accounts.
     * The bank account is like BANK_ACCOUNT(id, amount) with external table that have foreign keys for BANK_ACCOUNT and USER
     * for the transaction we use a list into the object,
     * with a relational DB can be implemented like TRANSACTION(id, bankAccountId, log, date) with foreign key on BANK_ACCOUNT
     * here the assumption is that a user have only one account associated, but it's made with a hash map for future scalability
     */
    private Map<UserDTO, BankAccountDTO> users = new HashMap<UserDTO, BankAccountDTO>() {{
        put(usersList.get(0), new BankAccountDTO("1", new BigDecimal(0.0)));
        put(usersList.get(1), new BankAccountDTO("2", new BigDecimal(100.0)));
        put(usersList.get(2), new BankAccountDTO("3", new BigDecimal(50.0)));
    }};

    @Override
    public UserDTO enrolledUser(String userId){
        int index = usersList.indexOf(new UserDTO(userId));
        if(index >= 0){
            return usersList.get(index);
        }
        return null;
    }

    @Override
    public BankAccountDTO getBankAccountBalance(UserDTO userDTO){
        return users.get(userDTO);
    }

    @Override
    public List<TransactionDTO> getTransactions(UserDTO userDTO){
        return users.get(userDTO).getTransactions();
    }

    @Override
    public boolean deposit(BigDecimal amount, UserDTO userDTO){
        try{
            users.get(userDTO).setAmount(amount);
            users.get(userDTO).addTransaction(DEPOSIT, amount);
            return true;
        }
        catch(Exception e){
            LOGGER.error("Error deposit {} amount for user {}", amount, userDTO.getId());
            return false;
        }
    }

    @Override
    public BigDecimal getAmount(UserDTO userDTO){
        return users.get(userDTO).getAmount();
    }

    @Override
    public boolean getMoney(BigDecimal amount, UserDTO userDTO){
        try{
            users.get(userDTO).getMoney(amount);
            users.get(userDTO).addTransaction(GET, amount);
            return true;
        }
        catch (Exception e){
            LOGGER.error("Error get {} amount for user {}", amount, userDTO.getId());
            return false;
        }
    }
}
