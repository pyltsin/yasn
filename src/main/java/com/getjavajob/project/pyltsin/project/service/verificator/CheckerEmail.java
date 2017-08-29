package com.getjavajob.project.pyltsin.project.service.verificator;


import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.service.exception.VerificationException;

/**
 * Created by Pyltsin on 21.12.2016. Algo8
 */
public class CheckerEmail implements AbstractChecker {
    public void check(Account account) throws VerificationException {
        if (!account.getEmail().matches("[0-9a-zA-Z._%-]+@[0-9a-zA-Z._%-]+")) {
            throw new VerificationException("email not parse");
        }
    }
}
