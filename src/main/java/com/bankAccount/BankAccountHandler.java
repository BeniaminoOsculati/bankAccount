package com.bankAccount;

import java.math.BigDecimal;
import java.util.List;

import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.adapter.Transaction;
import com.bankAccount.business.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller to manage API calls from the bank account.
 */
@RestController
public class BankAccountHandler {
    /**
     * Message for generic error
     */
    private static final String REQUEST_ERROR = "Error while processing request";

    /**
     * Message for deposit error
     */
    private static final String DEPOSIT_ERROR = "Error while deposit amount";

    /**
     * Message for retreat error
     */
    private static final String RETREAT_ERROR = "Error while retreat amount";

    /**
     * Message for deposit done
     */
    private static final String DEPOSIT_DONE = "Deposit done";

    /**
     * Message for retreat done
     */
    private static final String RETREAT_DONE = "Retreat done";

    @Autowired
    private BankAccountService bankAccountService;

    /**
     *  Endpoint to get a bank account balance from a given userId.
     * @param userId the userId of the user.
     * @return 200 with the bank account of the given user, 400 and relative error message otherwise
     */
    @GetMapping(value= "/bankAccount/{userId}")
    @ResponseBody
    public ResponseEntity<Object> getBankAccountBalance(@PathVariable String userId) {
        try{
            BankAccount response = bankAccountService.getBankAccountBalance(userId);
            return response != null ? responseEntityOk(response) : responseEntityBadRequest(REQUEST_ERROR);
        }
        catch (Exception e){
            return responseEntityBadRequest(REQUEST_ERROR);
        }
    }

    /**
     *  Endpoint to get all the transaction on the bank account of the given userId.
     * @param userId the userId of the user.
     * @return 200 with list of transactions made into the bank account of the given user, 400 and relative error message otherwise
     */
    @GetMapping(value = "/transactionList/{userId}")
    @ResponseBody
    public ResponseEntity<Object> getTransactions(@PathVariable String userId){
        try{
            List<Transaction> response = bankAccountService.getTransactions(userId);
            return response == null ? responseEntityBadRequest(REQUEST_ERROR) : responseEntityOk(response);
        }
        catch (Exception e){
            return responseEntityBadRequest(REQUEST_ERROR);
        }
    }

    /**
     *  Endpoint to deposit a specific amount of money into the given user bank account.
     * @param userId the userId of the user.
     * @return 200 with the relative message if it works, 400 and relative error message otherwise
     */
    @PostMapping(value = "/deposit/{userId}")
    public ResponseEntity<Object> deposit(@PathVariable String userId, @RequestParam BigDecimal amount){
        try {
            boolean response = bankAccountService.deposit(amount, userId);
            return response ? responseEntityOk(DEPOSIT_DONE) : responseEntityBadRequest(DEPOSIT_ERROR);
        }
        catch (Exception e){
            return responseEntityBadRequest(REQUEST_ERROR);
        }
    }

    /**
     *  Endpoint to get a specific amount of money from the given user bank account.
     * @param userId the userId of the user.
     * @return 200 with the relative message if it works, 400 and relative error message otherwise
     */
    @GetMapping(value = "/retreat/{userId}")
    public ResponseEntity<Object> getMoney(@PathVariable String userId, @RequestParam BigDecimal amount){
        try {
            boolean response = bankAccountService.getMoney(amount, userId);
            return response ? responseEntityOk(RETREAT_DONE) : responseEntityBadRequest(RETREAT_ERROR);
        }
        catch (Exception e){
            return responseEntityBadRequest(REQUEST_ERROR);
        }
    }

    /**
     * Generic method to create a response with error 400 and relative message error
     * @param error the error message
     * @return a response entity with 400 status code and the passed error message
     */
    private ResponseEntity<Object> responseEntityBadRequest(String error) {
        return new ResponseEntity<>(
                error, HttpStatus.BAD_REQUEST);
    }

    /**
     * Generic method to create a response with ok 200 and relative object
     * @param response object to return
     * @return a response entity with 200 status code and given object into the response
     */
    private ResponseEntity<Object> responseEntityOk(Object response){
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}