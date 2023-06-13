package com.pinson.othello.authentications.requests;

import com.pinson.othello.authentications.requests.RegisterRequest;
import org.springframework.stereotype.Component;

@Component
public class RegisterRequestFactory {

    public RegisterRequest create(String username, String password) {
        return new RegisterRequest(username, password);
    }
}
