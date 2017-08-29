package com.getjavajob.project.pyltsin.project.service.verificator;


import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.service.exception.VerificationException;

/**
 * Created by Pyltsin on 21.12.2016. Algo8
 */
public interface AbstractChecker {
    void check(Account account) throws VerificationException;
}
