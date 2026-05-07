package com.bankAccount.presentation.provider;

import com.bankAccount.TestUtil;
import com.bankAccount.business.adapter.BankAccount;
import com.bankAccount.business.service.BankAccountService;
import com.bankAccount.BankAccountProvider;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

/**
 * Tests for {@link BankAccountProvider}.
 */
@RunWith(EasyMockRunner.class)
public class BankAccountDTOProviderTests extends EasyMockSupport {
    @Mock
    private BankAccountService bankAccountService;
    @TestSubject
    private final BankAccountProvider bankAccountProvider = new BankAccountProvider();

    @Test
    public void testBankAccountBalance(){
        EasyMock.expect(bankAccountService.getBankAccountBalance(TestUtil.ID_USER1)).andReturn(TestUtil.bankAccountUser1());
        EasyMock.expect(bankAccountService.getBankAccountBalance(TestUtil.ID_USER2)).andReturn(TestUtil.bankAccountUser2());
        EasyMock.expect(bankAccountService.getBankAccountBalance(TestUtil.ID_USER3)).andReturn(TestUtil.bankAccountUser3());

        replayAll();
        Assert.assertEquals(bankAccountProvider.getBankAccountBalance(TestUtil.ID_USER1), TestUtil.bankAccountUser1());
        Assert.assertEquals(bankAccountProvider.getBankAccountBalance(TestUtil.ID_USER2), TestUtil.bankAccountUser2());
        Assert.assertEquals(bankAccountProvider.getBankAccountBalance(TestUtil.ID_USER3), TestUtil.bankAccountUser3());
    }

    @Test
    public void testDeposit(){
        EasyMock.expect(bankAccountService.deposit(new BigDecimal(10), TestUtil.ID_USER1)).andReturn(true);
        EasyMock.expect(bankAccountService.deposit(new BigDecimal(100), TestUtil.ID_USER2)).andReturn(true);
        EasyMock.expect(bankAccountService.deposit(new BigDecimal(1), TestUtil.ID_USER3)).andReturn(true);

        replayAll();
        Assert.assertTrue(bankAccountProvider.deposit(TestUtil.ID_USER1, new BigDecimal(10)));
        Assert.assertTrue(bankAccountProvider.deposit(TestUtil.ID_USER2, new BigDecimal(100)));
        Assert.assertTrue(bankAccountProvider.deposit(TestUtil.ID_USER3, new BigDecimal(1)));
    }

    @Test
    public void testGetMoney(){
        EasyMock.expect(bankAccountService.getMoney(new BigDecimal(10), TestUtil.ID_USER1)).andReturn(false);
        EasyMock.expect(bankAccountService.getMoney(new BigDecimal(10), TestUtil.ID_USER2)).andReturn(true);
        EasyMock.expect(bankAccountService.getMoney(new BigDecimal(50.1), TestUtil.ID_USER3)).andReturn(false);

        replayAll();
        Assert.assertFalse(bankAccountProvider.getMoney(TestUtil.ID_USER1, new BigDecimal(10)));
        Assert.assertTrue(bankAccountProvider.getMoney(TestUtil.ID_USER2, new BigDecimal(10)));
        Assert.assertFalse(bankAccountProvider.getMoney(TestUtil.ID_USER3, new BigDecimal(50.1)));
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
        Assert.assertEquals(bankAccountProvider.getTransactions(TestUtil.ID_USER1), TestUtil.transactionUser1());
        Assert.assertEquals(bankAccountProvider.getBankAccountBalance(TestUtil.ID_USER1), new BankAccount(TestUtil.ID_USER1, new BigDecimal(109)));

        Assert.assertEquals(bankAccountProvider.getTransactions(TestUtil.ID_USER2), TestUtil.transactionUser2());
        Assert.assertEquals(bankAccountProvider.getBankAccountBalance(TestUtil.ID_USER2), new BankAccount(TestUtil.ID_USER2, new BigDecimal(1)));

        Assert.assertEquals(bankAccountProvider.getTransactions(TestUtil.ID_USER3), TestUtil.transactionUser3());
        Assert.assertEquals(bankAccountProvider.getBankAccountBalance(TestUtil.ID_USER3), new BankAccount(TestUtil.ID_USER3, new BigDecimal(100)));
    }
}