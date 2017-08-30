package com.getjavajob.project.pyltsin.project.common.to;


import com.getjavajob.project.pyltsin.project.common.Account;

/**
 * Created by Pyltsin on 28.11.2016. Algo8
 */
public class FriendTO {

    private final String login;
    private final int id;

    private final String firstName;
    private final String middleName;
    private final String lastName;
    private final String date;
    private final String email;


    public FriendTO(Account account) {
        login = account.getLogin();
        id = account.getId();
        firstName = account.getFirstName();
        middleName = account.getMiddleName();
        lastName = account.getLastName();
        date = account.getBirthday();
        email = account.getEmail();
    }

    public String getLogin() {
        return login;
    }

    public int getId() {
        return id;
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

}
