package com.pinson.othello.authentications.responses;

import org.springframework.stereotype.Component;

@Component
public class AuthenticationResponseFactory {

    public AuthenticationResponse create(String accessToken, String refreshToken) {
        return new AuthenticationResponse(accessToken, refreshToken);
    }

}
