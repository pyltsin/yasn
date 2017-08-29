package com.getjavajob.project.pyltsin.project.dao;

import com.getjavajob.project.pyltsin.project.ProjectApplication;
import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.Phone;
import com.getjavajob.project.pyltsin.project.dao.datajpa.AccountDataJpaDAO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;


/**
 * Created by Pyltsin on 08.12.2016. Algo8
 */
@RunWith(SpringRunner.class)
@DataJpaTest
@SpringBootTest(classes = {
        ProjectApplication.class, AccountDataJpaDAO.class})
@Sql(scripts = "classpath:db/generate_content.sql", config = @SqlConfig(encoding = "UTF-8"))
public class AccountDAOTest {

    @Autowired
    @Qualifier("accountDataDAO")
    private AccountDataJpaDAO accountDataJpaDAO;

    @Test
    public void getById() throws Exception {
        Account account = accountDataJpaDAO.getById(1);
        assertEquals("ID", account.getId(), 1);
        assertEquals("LOGIN", account.getLogin(), "Vasya");
        assertEquals("FirstName", account.getFirstName(), "VASYa");
        assertEquals("MiddleName", account.getMiddleName(), "LITTLE");
        assertEquals("LastName", account.getLastName(), "PONY");
        assertEquals("AGE", account.getDate().toString(), "1988-11-11");
        HashSet<String> txtPhone = new HashSet<>();
        for (Phone phone : account.getTelephones()) {
            txtPhone.add(phone.getTelephone());
        }
        assertEquals("Telephone", txtPhone.contains("123"), true);
    }

    @Test
    public void getAll() throws Exception {
        List<Account> accounts = accountDataJpaDAO.getAll();
        assertEquals("getAll", accounts.size(), 3);
        Account account = accounts.get(0);
        assertEquals("ID", account.getId(), 1);
        assertEquals("LOGIN", account.getLogin(), "Vasya");
        assertEquals("Password", account.getPassword(), "123");
        assertEquals("FirstName", account.getFirstName(), "VASYa");
        assertEquals("MiddleName", account.getMiddleName(), "LITTLE");
        assertEquals("LastName", account.getLastName(), "PONY");
    }

    @Transactional
    @Test
    public void getFriends() throws Exception {
        Account account = accountDataJpaDAO.getById(2);
        Set<Account> friends1 = account.getAccounts1();
        Set<Account> friends2 = account.getAccounts2();
        assertEquals("sizeFr", friends1.size() + friends2.size(), 2);
    }

    @Test
    @Transactional

    public void persist() throws Exception {
        Account account = new Account("Loj", "aw");
        Account account1 = getAccount(account);
        assertEquals("persist.eqAc", account.getPassword(), account1.getPassword());
        Account fromSql = accountDataJpaDAO.getById(account1.getId());
        assertEquals("persist.eqAcSQL", account.getPassword(), fromSql.getPassword());
    }

    @Test
    @Transactional

    public void persistOneToMany() throws Exception {
        Account account = new Account("Loj2", "aw");
        List<Phone> phones = new ArrayList<>();
        phones.add(new Phone("123", ""));
        account.setTelephones(phones);
        Account account1 = getAccount(account);
        Account account2 = accountDataJpaDAO.getById(account1.getId());
        assertEquals("persist.Phone", account2.getTelephones().iterator().next().getTelephone(), "123");
    }

    private Account getAccount(Account account) {
        return accountDataJpaDAO.persist(account);
    }

    @Transactional
    @Test
    public void update() throws Exception {
        Account account = accountDataJpaDAO.getById(1);
        account.setPassword("TILITILI");
        upAc(account);
        Account account2 = accountDataJpaDAO.getById(1);
        assertEquals("update", account2.getPassword(), account.getPassword());
    }

    private void upAc(Account account) {
        accountDataJpaDAO.update(account);
    }

    @Transactional
    @Test
    public void delete() throws Exception {
        Account account = accountDataJpaDAO.getById(1);
        delAc(account);
        List<Account> accounts = accountDataJpaDAO.getAll();
        assertEquals("delete", accounts.size(), 2);
    }

    private void delAc(Account account) {
        accountDataJpaDAO.delete(account);
    }
}