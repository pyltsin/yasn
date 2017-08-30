package com.getjavajob.project.pyltsin.project.service;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.to.AccountTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Pyltsin on 02.03.2017. Algo8
 */
@Service("findService")
public class FindService {

    private final AccountService accountService;

    @Autowired
    public FindService(AccountService accountService) {
        this.accountService = accountService;
    }


    public List<AccountTO> findItem(String textFind, Integer start, Integer len) {
        List<Account> accountList = accountService.find(textFind, start, len);
        List<AccountTO> listOut = new ArrayList<>();
        if (accountList != null) {
            accountList.stream().sorted(Comparator.comparingInt(Account::getId)).forEach(account -> listOut.add(new AccountTO(account)));
        }

        return listOut;
    }
}
