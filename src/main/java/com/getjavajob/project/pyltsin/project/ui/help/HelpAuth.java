package com.getjavajob.project.pyltsin.project.ui.help;

import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.Phone;
import com.getjavajob.project.pyltsin.project.service.AccountService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class HelpAuth implements Serializable {
    public static final String PATH = "/";
    private static ThreadLocal<Integer> id;

    public static int getIdAccount(AccountService accountService) {
        if (id != null && id.get() != null) {
            return id.get();
        }
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Account accountOut = accountService.getByName(user.getUsername());
        id = new ThreadLocal<>();
        id.set(accountOut.getId());
        return accountOut.getId();
    }

    public static Account getCurrentAccount(AccountService accountService) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return accountService.getByName(user.getUsername());
    }

    public static Account getAccount(HttpServletRequest request) throws ParseException {
        String login = request.getParameter("login");
        String password = request.getParameter("password");
        String hashPassword = getHashPassword(password);
        String firstName = request.getParameter("firstName");
        String middleName = request.getParameter("middleName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String phone1 = request.getParameter("phone1");
        String phone2 = request.getParameter("phone2");
        String date = request.getParameter("birthday");
        Account account = new Account(login, hashPassword);
        account.setFirstName(firstName);
        account.setMiddleName(middleName);
        account.setLastName(lastName);
        account.setEmail(email);
        account.getTelephones().add(new Phone(phone1, ""));
        account.getTelephones().add(new Phone(phone2, ""));
        if (date == null) {
            account.setDate(new SimpleDateFormat("yyyy-mm-DD").parse("1900-00-00"));
        } else {
            try {
                account.setDate(new SimpleDateFormat("yyyy-mm-DD").parse(date));
            } catch (ParseException e) {
                account.setDate(new SimpleDateFormat("yyyy-mm-DD").parse("1900-00-00"));
            }
        }


        return account;
    }

    private static String getHashPassword(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}