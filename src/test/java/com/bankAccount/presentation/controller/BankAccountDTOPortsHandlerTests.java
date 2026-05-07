package com.bankAccount.presentation.controller;

import com.bankAccount.TestUtil;
import com.bankAccount.BankAccountHandler;
import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.service.BankAccountService;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

/**
 * Tests for {@link BankAccountHandler}.
 */
@RunWith(EasyMockRunner.class)
public class BankAccountDTOPortsHandlerTests extends EasyMockSupport{
    @Mock
    private BankAccountService bankAccountService;
    @TestSubject
    private final BankAccountHandler bankAccountHandler = new BankAccountHandler();

    @Test
    public void testBankAccountBalance(){
        EasyMock.expect(bankAccountService.getBankAccountBalance(TestUtil.ID_USER1)).andReturn(TestUtil.bankAccountUser1());
        EasyMock.expect(bankAccountService.getBankAccountBalance(TestUtil.ID_USER2)).andReturn(TestUtil.bankAccountUser2());
        EasyMock.expect(bankAccountService.getBankAccountBalance(TestUtil.ID_USER3)).andReturn(TestUtil.bankAccountUser3());

        replayAll();
        Assert.assertEquals(bankAccountHandler.getBankAccountBalance(TestUtil.ID_USER1).getBody(), TestUtil.bankAccountUser1());
        Assert.assertEquals(bankAccountHandler.getBankAccountBalance(TestUtil.ID_USER2).getBody(), TestUtil.bankAccountUser2());
        Assert.assertEquals(bankAccountHandler.getBankAccountBalance(TestUtil.ID_USER3).getBody(), TestUtil.bankAccountUser3());
    }

    @Test
    public void testDeposit(){
        EasyMock.expect(bankAccountService.deposit(new BigDecimal(10), TestUtil.ID_USER1)).andReturn(true);
        EasyMock.expect(bankAccountService.deposit(new BigDecimal(100), TestUtil.ID_USER2)).andReturn(true);
        EasyMock.expect(bankAccountService.deposit(new BigDecimal(1), TestUtil.ID_USER3)).andReturn(true);

        replayAll();
        Assert.assertTrue(bankAccountHandler.deposit(TestUtil.ID_USER1, new BigDecimal(10)).getStatusCode().equals(HttpStatus.OK));
        Assert.assertTrue(bankAccountHandler.deposit(TestUtil.ID_USER2, new BigDecimal(100)).getStatusCode().equals(HttpStatus.OK));
        Assert.assertTrue(bankAccountHandler.deposit(TestUtil.ID_USER3, new BigDecimal(1)).getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void testGetMoney(){
        EasyMock.expect(bankAccountService.getMoney(new BigDecimal(10), TestUtil.ID_USER1)).andReturn(false);
        EasyMock.expect(bankAccountService.getMoney(new BigDecimal(10), TestUtil.ID_USER2)).andReturn(true);
        EasyMock.expect(bankAccountService.getMoney(new BigDecimal(50.1), TestUtil.ID_USER3)).andReturn(false);

        replayAll();
        Assert.assertTrue(bankAccountHandler.getMoney(TestUtil.ID_USER1, new BigDecimal(10)).getStatusCode().equals(HttpStatus.BAD_REQUEST));
        Assert.assertTrue(bankAccountHandler.getMoney(TestUtil.ID_USER2, new BigDecimal(10)).getStatusCode().equals(HttpStatus.OK));
        Assert.assertFalse(bankAccountHandler.getMoney(TestUtil.ID_USER3, new BigDecimal(50.1)).getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    public void testTransactions(){
        EasyMock.expect(bankAccountService.getTransactions(TestUtil.ID_USER1)).andReturn(TestUtil.transactionUser1());
        EasyMock.expect(bankAccountService.getBankAccountBalance(TestUtil.ID_USER1)).andReturn(new BankAccount(TestUtil.ID_USER1, new BigDecimal(109)));

        EasyMock.expect(bankAccountService.getTransactions(TestUtil.ID_USER2)).andReturn(TestUtil.transactionUser2());
        EasyMock.expect(bankAccountService.getBankAccountBalance(TestUtil.ID_USER2)).andReturn(new BankAccount(TestUtil.ID_USER2, new BigDecimal(1)));

        EasyMock.expect(bankAccountService.getTransactions(TestUtil.ID_USER3)).andReturn(TestUtil.transactionUser3());
        EasyMock.expect(bankAccountService.getBankAccountBalance(TestUtil.ID_USER3)).andReturn(new BankAccount(TestUtil.ID_USER3, new BigDecimal(100)));

        replayAll();
        Assert.assertEquals(bankAccountHandler.getTransactions(TestUtil.ID_USER1).getBody(), TestUtil.transactionUser1());
        Assert.assertEquals(bankAccountHandler.getBankAccountBalance(TestUtil.ID_USER1).getBody(), new BankAccount(TestUtil.ID_USER1, new BigDecimal(109)));

        Assert.assertEquals(bankAccountHandler.getTransactions(TestUtil.ID_USER2).getBody(), TestUtil.transactionUser2());
        Assert.assertEquals(bankAccountHandler.getBankAccountBalance(TestUtil.ID_USER2).getBody(), new BankAccount(TestUtil.ID_USER2, new BigDecimal(1)));

        Assert.assertEquals(bankAccountHandler.getTransactions(TestUtil.ID_USER3).getBody(), TestUtil.transactionUser3());
        Assert.assertEquals(bankAccountHandler.getBankAccountBalance(TestUtil.ID_USER3).getBody(), new BankAccount(TestUtil.ID_USER3, new BigDecimal(100)));
    }
}
