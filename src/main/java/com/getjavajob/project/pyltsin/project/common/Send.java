package com.getjavajob.project.pyltsin.project.common;


import com.getjavajob.project.pyltsin.project.common.interfaces.Identified;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Pyltsin on 06.01.2017. Algo8
 */
@Entity
@Table(name = "SEND")
public class Send implements Identified {
    @Column(name = "MESSAGE")
    private String message;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "FROM_ACCOUNT", nullable = false)
    private Account from;

    @ManyToOne
    @JoinColumn(name = "TO_ACCOUNT", nullable = false)
    private Account to;
    @Column(name = "DATE")
    private LocalDateTime localDateTime;

    public Send(int id) {
        this.id = id;
    }

    public Send() {
    }

    public Send(Account from, Account to, String message) {
        this.from = from;
        this.to = to;
        this.message = message;
        localDateTime = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "" +
                "message=" + message +
                ", id=" + id +
                ", from=" + from.getName() +
                ", to=" + to.getName() +
                ", localDateTime=" + localDateTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    public Account getFrom() {
        return from;
    }

    public void setFrom(Account from) {
        this.from = from;
    }

    public Account getTo() {
        return to;
    }

    public void setTo(Account to) {
        this.to = to;
    }

    @Override
    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }
}
