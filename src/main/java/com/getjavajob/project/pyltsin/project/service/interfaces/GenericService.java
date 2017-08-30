package com.getjavajob.project.pyltsin.project.service.interfaces;


import com.getjavajob.project.pyltsin.project.service.exception.VerificationException;

import java.io.InputStream;
import java.util.List;

/**
 * Created by Pyltsin on 15.01.2017. Algo8
 */
public interface GenericService<T> {
    List<T> getAll();

    T create(T t) throws VerificationException;

    void delete(T t);

    void edit(T T);

    T get(int id);

    T getByName(String name);

    void saveImage(T t, InputStream file);

    byte[] getPicture(int id);
}

