package com.bankAccount.business;

import com.bankAccount.TestUtil;
import com.bankAccount.business.serviceimpl.BankAccountServiceImpl;
import com.bankAccount.driven.repository.DataRepository;
import org.easymock.EasyMock;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.Mock;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;

/**
 * Tests for {@link BankAccountServiceImpl}.
 */
@RunWith(EasyMockRunner.class)
public class BankAccountDTOServiceImplTests extends EasyMockSupport{
    @Mock
    private DataRepository dataRepository;
    @TestSubject
    private final BankAccountServiceImpl bankAccountServiceImpl = new BankAccountServiceImpl();

    @Before
    public void setUp() {
        EasyMock.expect(dataRepository.enrolledUser(TestUtil.ID_USER1)).andReturn(TestUtil.user1()).anyTimes();
        EasyMock.expect(dataRepository.enrolledUser(TestUtil.ID_USER2)).andReturn(TestUtil.user2()).anyTimes();
        EasyMock.expect(dataRepository.enrolledUser(TestUtil.ID_USER3)).andReturn(TestUtil.user3()).anyTimes();
    }

    @Test
    public void testBankAccountBalance(){
        EasyMock.expect(dataRepository.getBankAccountBalance(TestUtil.user1())).andReturn(TestUtil.bankAccountDTOUser1());
        EasyMock.expect(dataRepository.getBankAccountBalance(TestUtil.user2())).andReturn(TestUtil.bankAccountDTOUser2());
        EasyMock.expect(dataRepository.getBankAccountBalance(TestUtil.user3())).andReturn(TestUtil.bankAccountDTOUser3());

        replayAll();
        Assert.assertEquals(bankAccountServiceImpl.getBankAccountBalance(TestUtil.ID_USER1), TestUtil.bankAccountUser1());
        Assert.assertEquals(bankAccountServiceImpl.getBankAccountBalance(TestUtil.ID_USER2), TestUtil.bankAccountUser2());
        Assert.assertEquals(bankAccountServiceImpl.getBankAccountBalance(TestUtil.ID_USER3), TestUtil.bankAccountUser3());
    }

    @Test
    public void testDeposit(){
        EasyMock.expect(dataRepository.deposit(new BigDecimal(10), TestUtil.user2())).andReturn(true);
        EasyMock.expect(dataRepository.deposit(new BigDecimal(5), TestUtil.user3())).andReturn(true);

        replayAll();
        Assert.assertFalse(bankAccountServiceImpl.deposit(new BigDecimal(-10), TestUtil.ID_USER1));
        Assert.assertTrue(bankAccountServiceImpl.deposit(new BigDecimal(10), TestUtil.ID_USER2));
        Assert.assertTrue(bankAccountServiceImpl.deposit(new BigDecimal(5), TestUtil.ID_USER3));
    }

    @Test
    public void testGetMoney(){
        EasyMock.expect(dataRepository.getAmount(TestUtil.user1())).andReturn(new BigDecimal((0)));

        EasyMock.expect(dataRepository.getAmount(TestUtil.user3())).andReturn(new BigDecimal(50));
        EasyMock.expect(dataRepository.getMoney(new BigDecimal(50), TestUtil.user3())).andReturn(true);

        replayAll();
        Assert.assertFalse(bankAccountServiceImpl.getMoney(new BigDecimal(10), TestUtil.ID_USER1));
        Assert.assertFalse(bankAccountServiceImpl.getMoney(new BigDecimal(-10), TestUtil.ID_USER2));
        Assert.assertTrue(bankAccountServiceImpl.getMoney(new BigDecimal(50), TestUtil.ID_USER3));
    }

    @Test
    public void testTransactions(){
        EasyMock.expect(dataRepository.getTransactions(TestUtil.user1())).andReturn(TestUtil.transactionDTOUser1());
        EasyMock.expect(dataRepository.getTransactions(TestUtil.user2())).andReturn(TestUtil.transactionDTOUser2());
        EasyMock.expect(dataRepository.getTransactions(TestUtil.user3())).andReturn(TestUtil.transactionDTOUser3());

        replayAll();
        Assert.assertEquals(bankAccountServiceImpl.getTransactions(TestUtil.ID_USER1), TestUtil.transactionUser1());
        Assert.assertEquals(bankAccountServiceImpl.getTransactions(TestUtil.ID_USER2), TestUtil.transactionUser2());
        Assert.assertEquals(bankAccountServiceImpl.getTransactions(TestUtil.ID_USER3), TestUtil.transactionUser3());
    }
}
