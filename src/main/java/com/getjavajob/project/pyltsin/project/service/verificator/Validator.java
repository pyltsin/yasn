package com.getjavajob.project.pyltsin.project.service.verificator;


import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.service.exception.VerificationException;

/**
 * Created by Pyltsin on 21.12.2016. Algo8
 */
public class Validator {

    private AbstractChecker[] checkers;

    public Validator(AbstractChecker[] checkers) {
        this.checkers = checkers;
    }

    public Validator() {
    }

    public void setCheckers(AbstractChecker[] checkers) {
        this.checkers = checkers;
    }

    public void validate(Account account) throws VerificationException {
        boolean answer;
        if (checkers == null) {
            throw new IllegalArgumentException();
        }
        for (AbstractChecker checker : checkers) {
            checker.check(account);
        }
    }
}
