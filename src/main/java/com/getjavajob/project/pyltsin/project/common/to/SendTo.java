package com.getjavajob.project.pyltsin.project.common.to;

/**
 * Created by Pyltsin on 06.01.2017. Algo8
 */

public class SendTo {
    private String message;

    private String from;

    private String to;

    private String localDateTime;

    public SendTo(String message, String from, String to, String localDateTime) {
        this.message = message;
        this.from = from;
        this.to = to;
        this.localDateTime = localDateTime;
    }

    public String getMessage() {

        return message;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public String getLocalDateTime() {
        return localDateTime;
    }
}
