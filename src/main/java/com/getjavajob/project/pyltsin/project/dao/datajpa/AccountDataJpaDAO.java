package com.getjavajob.project.pyltsin.project.dao.datajpa;

import com.getjavajob.project.pyltsin.project.common.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Pyltsin on 25.08.2017.
 */
@Repository("accountDataDAO")
@Transactional(readOnly = true)
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class AccountDataJpaDAO implements GenericDAO<Account> {

    private final CrudAccountRepository crudAccountRepository;

    @Autowired
    public AccountDataJpaDAO(CrudAccountRepository crudAccountRepository) {
        this.crudAccountRepository = crudAccountRepository;
    }

    @Override
    public Account getById(int key) {
        return crudAccountRepository.findOne(key);
    }

    @Override
    public List<Account> getAll() {
        return crudAccountRepository.findAll();
    }

    @Transactional
    @Override
    public Account persist(Account object) {
        return crudAccountRepository.save(object);
    }

    @Transactional

    @Override
    public void update(Account object) {
        crudAccountRepository.save(object);
    }

    @Transactional

    @Override
    public void delete(Account object) {
        crudAccountRepository.delete(object);
    }

    @Transactional

    @Override
    public void addBlob(Account object, InputStream file) throws IOException {
        Account obj = getById(object.getId());
        if (obj != null) {
            byte[] cache = new byte[file.available()];
            int i = file.read(cache);
            if (i != -1) {
                System.out.println("ERROR");
            }
            obj.setPicture(cache);
            update(obj);
        }
    }

    @Transactional

    @Override
    public byte[] getBlob(int id) {
        Account obj = getById(id);
        if (obj != null) {
            return obj.getPicture();
        }
        return null;
    }

    @Override
    public List<Account> finderEntity(String textFind, Integer len) {
        return finderEntity(textFind, 0, len);
    }

    @Override
    public List<Account> finderEntity(String textFind, Integer by, Integer len) {
        return crudAccountRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(
                textFind, textFind, new PageRequest(by / len, len));
    }

    @Override
    public Account getByName(String name) {
        return crudAccountRepository.getAccountByLogin(name);
    }
}
