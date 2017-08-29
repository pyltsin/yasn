package com.getjavajob.project.pyltsin.project.ui;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.to.AccountTO;
import com.getjavajob.project.pyltsin.project.service.AccountService;
import com.getjavajob.project.pyltsin.project.ui.help.HelpAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pyltsin on 25.02.2017. Algo8
 */
@RestController
public class AccountRestController {

    private static Logger logger = LoggerFactory.getLogger(AccountRestController.class);
    private AccountService as;

    public AccountRestController(AccountService as) {
        this.as = as;
    }

    public AccountService getAs() {
        return as;
    }

    @Autowired
    public void setAs(AccountService as) {
        this.as = as;
    }

    @ResponseBody
    @RequestMapping(value = "/friends")
    public Set<AccountTO> showFriends() {
        Account enterAc = getAccount();
        Set<Account> friends = as.getFriends(enterAc);
        Set<AccountTO> out = new HashSet<>();
        friends.stream().forEach(account -> out.add(new AccountTO(account)));
        return out;
    }

    private Account getAccount() {
        int enterId = HelpAuth.getIdAccount(as);
        return as.get(enterId);
    }

    @ResponseBody
    @RequestMapping(value = "/friendsRest/{type}", method = RequestMethod.GET)
    public void changeFriends(@RequestParam("login") String login,
                              @PathVariable(value = "type") String type) {
        Account accountEnter = getAccount();

        if (login == null || login.equals("")) {
            return;
        }

        if (type.equals("addFriends")) {
            as.addFriend(accountEnter, login);
        } else if (type.equals("deleteFriends")) {
            as.deleteFriend(accountEnter, login);
        }
    }


    @ResponseBody
    @RequestMapping(value = "/isFriendsRest", method = RequestMethod.GET)
    public int isFriends(@RequestParam(value = "login", required = false) String login) {
        Account accountEnter = getAccount();
        if (login == null || login.equals("")) {
            return 0;
        }
        if (login.equals(accountEnter.getLogin())) {
            return 0;
        }
        if (as.isFriends(accountEnter, login)) {
            return 1;
        } else {
            return -1;
        }
    }

    @ResponseBody
    @RequestMapping(value = "/accountRest", method = RequestMethod.GET)
    public AccountTO accountGet(@RequestParam(value = "login", required = false) String login) {
        Account account = null;

        if (login != null && !login.equals("")) {
            account = as.getByName(login);
        }

        if (account == null) {
            account = getAccount();
        }
        return new AccountTO(account);
    }

    @ResponseBody
    @RequestMapping(value = "/accountInfoRest", method = RequestMethod.GET)
    public String accountInfoGet(@RequestParam(value = "login", required = false) String login) {
        return "{\"message\": \"Hello World\"}";
    }

}
