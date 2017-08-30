package com.getjavajob.project.pyltsin.project.common;


import com.getjavajob.project.pyltsin.project.common.interfaces.Identified;
import com.getjavajob.project.pyltsin.project.common.interfaces.Pictured;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Pyltsin on 28.11.2016. Algo8
 */
@XmlRootElement
@Entity
@Table(name = "ACCOUNTS")
public class Account implements Identified, Pictured {

    @XmlTransient
    private String password;
    @Column(unique = true)
    private String login;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    @XmlTransient
    private byte[] picture;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "MIDDLE_NAME")
    private String middleName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "BIRTH_DAY")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Temporal(TemporalType.DATE)
    private Date date;

    private String email;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = Phone.class)
    @JoinColumn(name = "ID_ACCOUNT")
    private List<Phone> telephones;

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "FRIENDS",
            joinColumns =
            @JoinColumn(name = "ID_ACCOUNT1", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "ID_ACCOUNT2", referencedColumnName = "ID")
    )
    private Set<Account> accounts1 = new HashSet<>();

    @XmlTransient
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "FRIENDS",
            joinColumns =
            @JoinColumn(name = "ID_ACCOUNT2", referencedColumnName = "ID"),
            inverseJoinColumns =
            @JoinColumn(name = "ID_ACCOUNT1", referencedColumnName = "ID")
    )
    private Set<Account> accounts2 = new HashSet<>();

    public Account() {

    }

    public Account(String login, String password) {
        this.login = login;
        this.password = password;
        telephones = new ArrayList<>();
    }


    public Account(Integer id) {
        this.id = id;
    }

    @XmlTransient
    public byte[] getPicture() {
        return picture;
    }

    public void setPicture(byte[] picture) {
        this.picture = picture;
    }

    @XmlTransient
    public Set<Account> getAccounts1() {
        return accounts1;
    }

    public void setAccounts1(Set<Account> accounts1) {
        this.accounts1 = accounts1;
    }

    @XmlTransient
    public Set<Account> getAccounts2() {
        return accounts2;
    }

    public void setAccounts2(Set<Account> accounts2) {
        this.accounts2 = accounts2;
    }

    public List<Phone> getTelephones() {
        return telephones;
    }

//    public void addTelephone(String tel, String type) {
//        telephones.add(new Phone(tel, type));
//    }

    public void setTelephones(List<Phone> telephones) {
        this.telephones = telephones;
    }

    public String getBirthday() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        return simpleDateFormat.format(date);

    }

    @XmlTransient
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getDate() {
        return date;
    }


    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return
                "id: " + id + '\n' +
                        ", login= " + login + '\n' +
                        ", password: " + password + '\n' +
                        ", firstName: " + firstName + '\n' +
                        ", middleName: " + middleName + '\n' +
                        ", lastName: " + lastName + '\n' +
                        ", date: " + date +
                        ", email: " + email;
    }

    public String getName() {
        return firstName + " " + lastName;
    }

//    public Phone getPhone1() {
//        return telephones.size() >= 1 ? telephones.get(0) : null;
//    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id && (password != null ? password.equals(account.password) : account.password == null) && login.equals(account.login);
    }

    @Override
    public int hashCode() {
        int result = password != null ? password.hashCode() : 0;
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }

    public void clearTelephones() {
        telephones.clear();
    }
}
