package com.pinson.othello.auths.dtos.responses;

import com.pinson.othello.auths.dtos.AuthDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthResponseFactory {

    private final AuthDTOMapper authDTOMapper;

    @Autowired
    public AuthResponseFactory(
        AuthDTOMapper authDTOMapper
    ) {
        this.authDTOMapper = authDTOMapper;
    }

    public AuthenticationResponse create(String accessToken) {
        return this.authDTOMapper.createAuthenticationResponse(accessToken);
    }

}
