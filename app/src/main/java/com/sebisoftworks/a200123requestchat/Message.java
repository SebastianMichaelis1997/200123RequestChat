package com.sebisoftworks.a200123requestchat;

public class Message {
    private String from;
    private String date;
    private String message;

    public Message(String from, String date, String message) {
        this.from = from;
        this.date = date;
        this.message = message;
    }

    public String getFrom() {
        return from;
    }

    public String getDate() {
        return date;
    }

    public String getMessage() {
        return message;
    }
}
