package com.sebisoftworks.a200123requestchat;

import java.util.Date;

class Message {
    private String from;
    private Date date;
    private String message;

    Message(String from, Date date, String message) {
        this.from = from;
        this.date = date;
        this.message = message;
    }

    String getFrom() {
        return from;
    }

    Date getDate() {
        return date;
    }

    String getMessage() {
        return message;
    }
}
