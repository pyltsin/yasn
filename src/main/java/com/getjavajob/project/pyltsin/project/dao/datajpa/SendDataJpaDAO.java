package com.getjavajob.project.pyltsin.project.dao.datajpa;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.Send;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Pyltsin on 25.08.2017.
 */
@Repository("sendDataDAO")
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)

public class SendDataJpaDAO implements GenericDAO<Send> {

    private final CrudSendRepository crudSendRepository;
    @PersistenceContext
    private EntityManager em;

    @Autowired
    public SendDataJpaDAO(CrudSendRepository crudSendRepository) {
        this.crudSendRepository = crudSendRepository;
    }

    @Override
    public Send getById(int key) {
        return crudSendRepository.findOne(key);
    }

    @Override
    public List<Send> getAll() {
        return crudSendRepository.findAll();
    }

    @Transactional
    @Override
    public Send persist(Send object) {
        return crudSendRepository.save(object);
    }

    @Transactional
    @Override
    public void update(Send object) {
        crudSendRepository.save(object);
    }

    @Transactional
    @Override
    public void delete(Send object) {
        crudSendRepository.delete(object);
    }

    @Transactional
    @Override
    public void addBlob(Send object, InputStream file) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Transactional
    @Override
    public byte[] getBlob(int id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Send> finderEntity(String textFind, Integer len) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Send> finderEntity(String textFind, Integer by, Integer len) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Send getByName(String name) {
        throw new UnsupportedOperationException();
    }

    public List<Send> getSends(Account accountFrom, Account accountTo, int len) {
        CriteriaBuilder builder = em.getCriteriaBuilder();
        CriteriaQuery<Send> criteriaQuery = builder.createQuery(Send.class);
        Root<Send> from = criteriaQuery.from(Send.class);
        criteriaQuery.select(from);
        criteriaQuery.where(builder.or(
                (builder.and(builder.equal(from.get("from"), accountFrom),
                        builder.equal(from.get("to"), accountTo))),
                builder.and(builder.equal(from.get("from"), accountTo),
                        builder.equal(from.get("to"), accountFrom))));

        criteriaQuery.orderBy(builder.desc(from.get("localDateTime")));
        TypedQuery<Send> tTypedQuery = em.createQuery(criteriaQuery).setMaxResults(len);
        return tTypedQuery.getResultList();
    }

}
