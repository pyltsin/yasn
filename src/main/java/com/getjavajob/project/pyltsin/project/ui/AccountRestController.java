package com.getjavajob.project.pyltsin.project.ui;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.Phone;
import com.getjavajob.project.pyltsin.project.common.to.AccountTO;
import com.getjavajob.project.pyltsin.project.common.to.FriendTO;
import com.getjavajob.project.pyltsin.project.service.AccountService;
import com.getjavajob.project.pyltsin.project.ui.help.HelpAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Pyltsin on 25.02.2017. Algo8
 */
@Controller
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
    public Set<FriendTO> showFriends() {
        Account enterAc = getAccount();
        Set<Account> friends = as.getFriends(enterAc);
        Set<FriendTO> out = new HashSet<>();
        friends.stream().forEach(account -> out.add(new FriendTO(account)));
        return out;
    }

    private Account getAccount() {
        int enterId = HelpAuth.getIdAccount(as);
        return as.get(enterId);
    }

    @ResponseBody
    @RequestMapping(value = "/friendsRest/{type}", method = RequestMethod.GET)
    public int changeFriends(@RequestParam("login") String login,
                             @PathVariable(value = "type") String type) {
        Account accountEnter = getAccount();

        if (login == null || login.equals("")) {
            return 0;
        }

        if (type.equals("addFriends")) {
            as.addFriend(accountEnter, login);
            return 1;
        } else if (type.equals("deleteFriends")) {
            as.deleteFriend(accountEnter, login);
            return -1;
        }
        return 0;
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
    @Transactional
    @RequestMapping(value = {"/ChangeServlet"}, method = RequestMethod.POST)
    public String changeAccount(@ModelAttribute(name = "account") Account accountForEdit,
                                @RequestParam(value = "foto", required = true) MultipartFile mFile,
                                @RequestParam(value = "date", required = true) String date, final MultipartHttpServletRequest request) {

        Account accountEnter = getAccount();

        getAccount(accountForEdit, accountEnter, date);
        InputStream fileFromSpring = null;
        if (mFile != null) {
            try {
                fileFromSpring = mFile.getInputStream();
            } catch (IOException e) {
                logger.error("changeAccount" + e);
            }


        }

        try {
            as.editWithPicture(accountEnter, fileFromSpring);
        } catch (IOException e) {
            logger.error("changeAccount" + e);
        }

        return "{\"message\": \"Hello World\"}";
    }

    @ResponseBody
    @RequestMapping(value = "/accountInfoRest", method = RequestMethod.GET)
    public String accountInfoGet(@RequestParam(value = "login", required = false) String login) {
        return "{\"message\": \"Hello World\"}";
    }

    private void getAccount(Account accountForEdit, Account account, String date) {
        String firstName = accountForEdit.getFirstName();
        String middleName = accountForEdit.getMiddleName();
        String lastName = accountForEdit.getLastName();
        String email = accountForEdit.getEmail();

        account.setFirstName(firstName);
        account.setMiddleName(middleName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.clearTelephones();

        for (Phone phone : accountForEdit.getTelephones()) {
            if (phone.getType() != null && phone.getTelephone() != null) {
                account.getTelephones().add(phone);
            }
        }

        try {
            account.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        } catch (ParseException e) {
            logger.error("getAccount" + e);
        }
    }

    @ResponseBody
    @RequestMapping(value = {"/importXML"})
    public String importXML(HttpServletRequest req, @RequestParam("fileXML") MultipartFile mFil) throws ServletException, IOException {

        Account accountEnter = getAccount();

        Account account = null;
        try {
            JAXBContext context = JAXBContext.newInstance(Phone.class, Account.class);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            account = (Account) unmarshaller.unmarshal(mFil.getInputStream());


        } catch (JAXBException e) {
            logger.error(e.toString());
        }

        if (account != null) {
            accountEnter = as.get(accountEnter.getId());

            getAccountXML(account, accountEnter);
        }

        try {
            as.editWithPicture(accountEnter, null);
        } catch (IOException e) {
            logger.error("changeAccount" + e);
        }

        return "{\"message\": \"Hello World\"}";
    }


    private void getAccountXML(Account account, Account accountEnter) {
        accountEnter.setFirstName(account.getFirstName());
        accountEnter.setMiddleName(account.getMiddleName());
        accountEnter.setLastName(account.getLastName());
        accountEnter.setDate(account.getDate());
        accountEnter.setEmail(account.getEmail());
        accountEnter.setTelephones(account.getTelephones());
    }
}
