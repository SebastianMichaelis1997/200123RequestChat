package com.sebisoftworks.a200123requestchat;

import java.util.Date;

public class Message {
    private String from;
    private Date date;
    private String message;

    public Message(String from, Date date, String message) {
        this.from = from;
        this.date = date;
        this.message = message;
    }

    public Message(String from, long date, String message) {
        this.from = from;
        this.date = new Date(date);
        this.message = message;
    }

    public Message(String from, String date, String message) {
        this.from = from;
        this.date = new Date(new Long((date)));
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public Date getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
