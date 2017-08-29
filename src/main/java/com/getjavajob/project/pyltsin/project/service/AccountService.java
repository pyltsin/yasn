package com.getjavajob.project.pyltsin.project.service;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.dao.datajpa.GenericDAO;
import com.getjavajob.project.pyltsin.project.service.exception.VerificationException;
import com.getjavajob.project.pyltsin.project.service.verificator.AbstractChecker;
import com.getjavajob.project.pyltsin.project.service.verificator.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * Created by Pyltsin on 18.12.2016. Algo8
 */
@Transactional(readOnly = true)
@Service("accountService")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)

public class AccountService extends AbstractService<Account> implements UserDetailsService {

    private static Logger logger = LoggerFactory.getLogger(AccountService.class);

    private GenericDAO<Account> accountDAO;
    private Validator validator;

    @Autowired
    public AccountService(@Qualifier("accountDataDAO") GenericDAO<Account> dao) {
        super(dao);
        setValidator();
        accountDAO = dao;
    }


    @Override
    protected void validate(Account account) throws VerificationException {
        validator.validate(account);
    }

    private void setValidator() {
        validator = new Validator();
//        AbstractChecker[] checkers = {new CheckerEmail()};
        AbstractChecker[] checkers = {};
        validator.setCheckers(checkers);
    }

    @Transactional
    public void addFriend(Account account1, int id) {
        Account account2 = get(id);
        addFriends(account1, account2);
    }

    @Transactional
    public void addFriend(Account account1, String name) {
        Account account2 = getByName(name);
        addFriends(account1, account2);
    }

    //check have database this login
    public boolean check(Account account) {
        Account accountOut = accountDAO.getByName(account.getLogin());
        return accountOut == null;
    }

    private void addFriends(Account account1, Account account2) {
        account1.getAccounts1().add(account2);
        accountDAO.update(account1);
    }

    @Transactional
    public void deleteFriend(Account account1, int id) {
        Account account2 = get(id);
        deleteFriends(account1, account2);
    }

    @Transactional
    public void deleteFriend(Account account1, String name) {
        Account account2 = getByName(name);
        deleteFriends(account1, account2);
    }

    private void deleteFriends(Account account1, Account account2) {
        if (account1.getAccounts1().contains(account2)) {
            account1.getAccounts1().remove(account2);
        }
        if (account1.getAccounts2().contains(account2)) {
            account1.getAccounts2().remove(account2);
        }
        accountDAO.update(account1);
    }

    public Set<Account> getFriends(Account account) {
        Account account1 = accountDAO.getById(account.getId());
        Set<Account> out = new HashSet<>();
        out.addAll(account1.getAccounts1());
        out.addAll(account1.getAccounts2());
        return out;
    }

    @Transactional
    public boolean isFriends(Account account1, Account account2) {
        return account1.getAccounts1().contains(account2) ||
                account1.getAccounts2().contains(account2);
    }

    @Transactional
    public boolean isFriends(Account account1, String login) {
        return isFriends(account1, getByName(login));
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void editWithPicture(Account account, InputStream file) throws IOException {

        edit(account);
        if (file != null && file.available() != 0) {
            saveImage(account, file);
        }
    }

    @Transactional
    public void createWithPicture(Account account, InputStream file) {
        Account outAc = null;
        try {
            outAc = create(account);
        } catch (VerificationException e) {
            logger.error("createWithPicture" + e);
        }
        if (file != null) {
            saveImage(outAc, file);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = getByName(username);
        if (account != null) {
            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
            return new User(account.getLogin(), account.getPassword(), authorities);
        }
        throw new UsernameNotFoundException(
                "User '" + username + "' not found.");
    }
}
