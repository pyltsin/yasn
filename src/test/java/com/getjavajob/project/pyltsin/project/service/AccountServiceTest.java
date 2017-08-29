package com.getjavajob.project.pyltsin.project.service;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.dao.datajpa.AccountDataJpaDAO;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

/**
 * Created by Pyltsin on 21.12.2016. Algo8
 */
public class AccountServiceTest {
    private AccountDataJpaDAO accountDAO;

    @After
    public void tearDown() throws Exception {
        accountDAO = null;
    }

    @Before
    public void setUp() throws Exception {
        accountDAO = mock(AccountDataJpaDAO.class);
    }

    @Test
    public void deleteAccount() throws Exception {
        AccountService accountService = new AccountService(accountDAO);
        Account account = new Account(1);
        accountService.delete(account);
        verify(accountDAO).delete(account);
    }

    @Test
    public void createAccount() throws Exception {
        AccountService accountService = new AccountService(accountDAO);

        Account account = new Account("TestName", "123");
        account.setEmail("123@123");
        accountService.create(account);
        verify(accountDAO).persist(account);
        assertEquals("testPassword", account.getPassword(), "123");
        assertEquals("testLogin", account.getLogin(), "TestName");
    }


    @Test
    public void editAccount() throws Exception {
        Account account = new Account(1);
        account.setLogin("abc");
        account.setPassword("abcd");
        account.setFirstName("Mike");
        account.setMiddleName("Mikel");
        account.setLastName("Mikelov");
        account.setEmail("m@m");
        account.setDate(new SimpleDateFormat("yyyy-DD-mm").parse("1988-11-12"));

        AccountService accountService = new AccountService(accountDAO);
        accountService.edit(account);

        verify(accountDAO).update(account);


        assertEquals("testPassword", account.getPassword(), "abcd");
        assertEquals("testLogin", account.getLogin(), "abc");
        assertEquals("testFirstName", account.getFirstName(), "Mike");
        assertEquals("testMiddleName", account.getMiddleName(), "Mikel");
        assertEquals("testLastName", account.getLastName(), "Mikelov");
        assertEquals("testEmail", account.getEmail(), "m@m");

    }
}