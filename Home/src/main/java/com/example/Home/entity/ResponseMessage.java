package com.example.Home.entity;

public class ResponseMessage {

    private String message;
    private User user;

    public ResponseMessage(String message, User user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getcustomer() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
