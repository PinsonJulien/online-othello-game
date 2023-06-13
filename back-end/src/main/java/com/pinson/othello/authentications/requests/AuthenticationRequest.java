package com.pinson.othello.authentications.requests;

public class AuthenticationRequest {
    private String username;
    private String password;

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    public String getUsername() {
        return this.username;
    }

    public AuthenticationRequest setUsername(String username) {
        this.username = username;

        return this;
    }

    public String getPassword() {
        return this.password;
    }

    public AuthenticationRequest setPassword(String password) {
        this.password = password;

        return this;
    }
}
