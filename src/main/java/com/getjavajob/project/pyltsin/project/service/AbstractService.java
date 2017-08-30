package com.getjavajob.project.pyltsin.project.service;

import com.getjavajob.project.pyltsin.project.common.interfaces.Identified;
import com.getjavajob.project.pyltsin.project.dao.datajpa.GenericDAO;
import com.getjavajob.project.pyltsin.project.service.exception.VerificationException;
import com.getjavajob.project.pyltsin.project.service.interfaces.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Pyltsin on 15.01.2017. Algo8
 */
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional(readOnly = true)
public abstract class AbstractService<T extends Identified> implements GenericService<T> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractService.class);
    private final GenericDAO<T> dao;


    AbstractService(GenericDAO<T> dao) {
        this.dao = dao;
    }


    @Override
    public List<T> getAll() {
        return dao.getAll();
    }

    @Transactional
    @Override
    public T create(T t) throws VerificationException {
        validate(t);
        return dao.persist(t);
    }

    protected abstract void validate(T t) throws VerificationException;

    @Transactional
    @Override
    public void delete(T t) {
        dao.delete(t);
    }

    @Transactional
    @Override
    public void edit(T t) {
        dao.update(t);
    }

    @Override
    public T get(int id) {
        T out;
        out = dao.getById(id);
        if (out == null) {
            throw new NullPointerException();
        }

        return out;
    }

    @Override
    public T getByName(String name) {
        T out = null;
        try {
            out = dao.getByName(name);
            if (out == null) {
                throw new NullPointerException();
            }
        } catch (NullPointerException e) {
            logger.error("get" + e);
        }
        return out;
    }

    @Transactional
    @Override
    public void saveImage(T t, InputStream file) {
        try {
            dao.addBlob(t, file);
        } catch (IOException e) {
            logger.error("saveImage" + e);
        }
    }

    @Override
    public byte[] getPicture(int id) {
        return dao.getBlob(id);
    }

    @Transactional
    List<T> find(String textFind, Integer start, Integer len) {
        return dao.finderEntity(textFind, start, len);
    }

    public byte[] getPictureByName(String name) {
        try {
            return getPicture(getByName(name).getId());
        } catch (Exception e) {
            logger.error("getPicture" + e);
        }
        return null;
    }
}
