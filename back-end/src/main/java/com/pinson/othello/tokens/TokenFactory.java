package com.pinson.othello.tokens;

import com.pinson.othello.players.OthelloPlayer;
import org.springframework.stereotype.Component;

@Component
public class TokenFactory {

    public Token createPlayerBearerToken(OthelloPlayer othelloPlayer, String token) {
        return new Token()
            .setPlayer(othelloPlayer)
            .setToken(token)
            .setTokenType(TokenType.BEARER)
            .setExpired(false)
            .setRevoked(false);
    }

}
