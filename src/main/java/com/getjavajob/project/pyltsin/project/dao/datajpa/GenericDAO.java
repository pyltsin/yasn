package com.getjavajob.project.pyltsin.project.dao.datajpa;

import com.getjavajob.project.pyltsin.project.common.interfaces.Identified;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by Pyltsin on 05.12.2016. Algo8
 */
public interface GenericDAO<T extends Identified> {

    T getById(int key);

    List<T> getAll();

    T persist(T object);

    void update(T object);

    void delete(T object);

    void addBlob(T object, InputStream file) throws IOException;

    byte[] getBlob(int id);

    List<T> finderEntity(String textFind, Integer by);

    List<T> finderEntity(String textFind, Integer by, Integer from);

    T getByName(String name);
}
