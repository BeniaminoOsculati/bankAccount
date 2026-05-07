package com.bankAccount.driven;

import com.bankAccount.TestUtil;
import com.bankAccount.driven.record.BankAccountDTO;
import com.bankAccount.driven.record.UserDTO;
import com.bankAccount.driven.repositoryimpl.LocalDataRepositoryImpl;
import com.bankAccount.driven.record.TransactionDTO;
import org.easymock.EasyMockRunner;
import org.easymock.EasyMockSupport;
import org.easymock.TestSubject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.math.BigDecimal;
import java.util.List;

/**
 * Tests for {@link LocalDataRepositoryImpl}.
 */
@RunWith(EasyMockRunner.class)
public class LocalDataRepositoryImplTests extends EasyMockSupport{
    @TestSubject
    private LocalDataRepositoryImpl localDataRepository = new LocalDataRepositoryImpl();
    private UserDTO user1 = localDataRepository.enrolledUser(TestUtil.ID_USER1);
    private UserDTO user2 = localDataRepository.enrolledUser(TestUtil.ID_USER2);
    private UserDTO user3 = localDataRepository.enrolledUser(TestUtil.ID_USER3);

    @Test
    public void testBankAccountBalance(){
        replayAll();
        BankAccountDTO bankAccountDTOUser1 = localDataRepository.getBankAccountBalance(user1);
        BankAccountDTO bankAccountDTOUser2 = localDataRepository.getBankAccountBalance(user2);
        BankAccountDTO bankAccountDTOUser3 = localDataRepository.getBankAccountBalance(user3);
        verifyAll();
        Assert.assertEquals(bankAccountDTOUser1, TestUtil.bankAccountDTOUser1());
        Assert.assertEquals(bankAccountDTOUser2, TestUtil.bankAccountDTOUser2());
        Assert.assertEquals(bankAccountDTOUser3, TestUtil.bankAccountDTOUser3());
    }

    @Test
    public void testDeposit(){
        replayAll();
        boolean depositUser1 = localDataRepository.deposit(new BigDecimal(10), user1);
        boolean depositUser2 = localDataRepository.deposit(new BigDecimal(100), user2);
        boolean depositUser3 = localDataRepository.deposit(new BigDecimal(10.5), user3);
        verifyAll();

        Assert.assertTrue(depositUser1);
        Assert.assertEquals(localDataRepository.getBankAccountBalance(user1).getAmount(), new BigDecimal(10));

        Assert.assertTrue(depositUser2);
        Assert.assertEquals(localDataRepository.getBankAccountBalance(user2).getAmount(), new BigDecimal(200));

        Assert.assertTrue(depositUser3);
        Assert.assertEquals(localDataRepository.getBankAccountBalance(user3).getAmount(), new BigDecimal(60.5));
    }

    @Test
    public void testGetMoney(){
        replayAll();
        localDataRepository.getMoney(new BigDecimal(10), user2);
        localDataRepository.getMoney(new BigDecimal(50), user3);
        verifyAll();

        Assert.assertEquals(localDataRepository.getBankAccountBalance(user2).getAmount(), new BigDecimal(90));
        Assert.assertEquals(localDataRepository.getBankAccountBalance(user3).getAmount(), new BigDecimal(0));
    }

    /**
     * Test of a complete flow for the data repository
     */
    @Test
    public void testFlow(){
        replayAll();
        localDataRepository.deposit(new BigDecimal(10), user1);
        localDataRepository.getMoney(new BigDecimal(1), user1);
        localDataRepository.deposit(new BigDecimal(100), user1);
        BankAccountDTO bankAccountDTOUser1 = localDataRepository.getBankAccountBalance(user1);
        List<TransactionDTO> transactionDTOListUser1 = localDataRepository.getTransactions(user1);

        localDataRepository.getMoney(new BigDecimal(100), user2);
        localDataRepository.deposit(new BigDecimal(1), user2);
        BankAccountDTO bankAccountDTOUser2 = localDataRepository.getBankAccountBalance(user2);
        List<TransactionDTO> transactionDTOListUser2 = localDataRepository.getTransactions(user2);

        localDataRepository.deposit(new BigDecimal(50), user3);
        BankAccountDTO bankAccountDTOUser3 = localDataRepository.getBankAccountBalance(user3);
        List<TransactionDTO> transactionDTOListUser3 = localDataRepository.getTransactions(user3);
        verifyAll();

        Assert.assertTrue(transactionDTOListUser1.equals(TestUtil.transactionDTOUser1()));
        Assert.assertEquals(bankAccountDTOUser1.getAmount(), new BigDecimal(109));

        Assert.assertEquals(transactionDTOListUser2, TestUtil.transactionDTOUser2());
        Assert.assertEquals(bankAccountDTOUser2.getAmount(), new BigDecimal(1));

        Assert.assertEquals(transactionDTOListUser3, TestUtil.transactionDTOUser3());
        Assert.assertEquals(bankAccountDTOUser3.getAmount(), new BigDecimal(100));
    }
}
