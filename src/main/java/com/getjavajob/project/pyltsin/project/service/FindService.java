package com.getjavajob.project.pyltsin.project.service;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.to.ItemFind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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


    public List<ItemFind> findItem(String textFind, Integer start, Integer len) {
        String text = "%" + textFind + "%";
        List<Account> accountList = accountService.find(text, start, len);
        List<ItemFind> listOut = new ArrayList<>();
        if (accountList != null) {
            for (Account account : accountList) {
                listOut.add(new ItemFind(account.getName(), account.getLogin()));
            }
        }

        return listOut;
    }
}
