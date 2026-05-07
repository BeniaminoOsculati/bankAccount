package com.bankAccount;

import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.adapter.Transaction;
import com.bankAccount.business.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;

/**
 * Implementation of {@link BankAccountPorts}
 * Provider to handle calls to the bank account for application usage.
 */
@Controller
public class BankAccountProvider implements BankAccountPorts {
    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public BankAccount getBankAccountBalance(String userId) {
        return bankAccountService.getBankAccountBalance(userId);
    }

    @Override
    public List<Transaction> getTransactions(String userId) {
        return bankAccountService.getTransactions(userId);
    }

    @Override
    public boolean deposit(String userId, BigDecimal amount) {
        return bankAccountService.deposit(amount, userId);
    }

    @Override
    public boolean getMoney(String userId, BigDecimal amount) {
        return bankAccountService.getMoney(amount, userId);
    }
}
