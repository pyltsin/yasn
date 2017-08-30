package com.getjavajob.project.pyltsin.project.common.to;

/**
 * Created by Pyltsin on 06.01.2017. Algo8
 */

public class SendTo {
    private final String message;

    private final String from;

    private final String to;

    private final String localDateTime;

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
