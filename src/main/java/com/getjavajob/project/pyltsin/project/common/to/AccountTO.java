package com.getjavajob.project.pyltsin.project.common.to;


import com.getjavajob.project.pyltsin.project.common.Account;
import com.getjavajob.project.pyltsin.project.common.Phone;
import com.getjavajob.project.pyltsin.project.common.interfaces.Identified;

import java.util.List;

/**
 * Created by Pyltsin on 28.11.2016. Algo8
 */
public class AccountTO implements Identified {

    private final String login;
    private final int id;

    private final String picture;
    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String date;
    private final String email;

    private final List<Phone> telephones;

    public AccountTO(Account account) {
        login = account.getLogin();
        id = account.getId();
        picture = "/images/account/" + login + ".jpg";
        firstName = account.getFirstName();
        middleName = account.getMiddleName();
        lastName = account.getLastName();
        date = account.getBirthday();
        email = account.getEmail();
        telephones = account.getTelephones();
    }

    public String getLogin() {
        return login;
    }

    public String getName() {
        return lastName + " " + firstName;
    }

    public int getId() {
        return id;
    }

    public String getPicture() {
        return picture;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getDate() {
        return date;
    }

    public String getEmail() {
        return email;
    }

    public List<Phone> getTelephones() {
        return telephones;
    }
}
