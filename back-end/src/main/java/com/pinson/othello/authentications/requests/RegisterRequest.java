package com.pinson.othello.authentications.requests;

public class RegisterRequest {
    private String username;
    private String password;

    public RegisterRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public RegisterRequest setUsername(String username) {
        this.username = username;

        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public RegisterRequest setPassword(String password) {
        this.password = password;
        return this;
    }
}