package com.pinson.othello.tokens;

import com.pinson.othello.players.OthelloPlayer;
import jakarta.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String token;

    @Enumerated(EnumType.STRING)
    private TokenType tokenType = TokenType.BEARER;

    private boolean revoked;

    private boolean expired;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "othello_player_id")
    private OthelloPlayer player;

    public Token() {
        //
    }

    public Long getId() {
        return this.id;
    }

    public Token setId(Long id) {
        this.id = id;
        return this;
    }

    public String getToken() {
        return this.token;
    }

    public Token setToken(String token) {
        this.token = token;
        return this;
    }

    public TokenType getTokenType() {
        return this.tokenType;
    }

    public Token setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
        return this;
    }

    public boolean isRevoked() {
        return this.revoked;
    }

    public Token setRevoked(boolean revoked) {
        this.revoked = revoked;
        return this;
    }

    public boolean isExpired() {
        return this.expired;
    }

    public Token setExpired(boolean expired) {
        this.expired = expired;
        return this;
    }

    public OthelloPlayer getPlayer() {
        return this.player;
    }

    public Token setPlayer(OthelloPlayer player) {
        this.player = player;
        return this;
    }

}