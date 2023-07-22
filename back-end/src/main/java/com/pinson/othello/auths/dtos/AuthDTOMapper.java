package com.pinson.othello.auths.dtos;

import com.pinson.othello.auths.dtos.responses.AuthenticationResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthDTOMapper {

    public AuthDTOMapper() {
        //
    }

    public AuthenticationResponse createAuthenticationResponse(String accessToken) {
        return new AuthenticationResponse(accessToken);
    }

}
