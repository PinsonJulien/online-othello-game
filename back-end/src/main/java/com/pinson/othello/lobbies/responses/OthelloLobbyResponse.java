package com.pinson.othello.lobbies.responses;

import com.pinson.othello.games.OthelloGame;

import java.time.LocalDateTime;
import java.util.List;

public class OthelloLobbyResponse {

    private Long id;
    private Integer maxPlayers;

    private LocalDateTime createdAt;
    private OthelloGame game = null;

    protected OthelloLobbyResponse() {

    }

    protected OthelloLobbyResponse(Long id, Integer maxPlayers, LocalDateTime createdAt, OthelloGame game) {
        this.setId(id);
        this.setMaxPlayers(maxPlayers);
        this.setCreatedAt(createdAt);
        this.setGame(game);
    }

    public Long getId() {
        return this.id;
    }

    protected OthelloLobbyResponse setId(Long id) {
        this.id = id;
        return this;
    }

    public Integer getMaxPlayers() {
        return this.maxPlayers;
    }

    protected OthelloLobbyResponse setMaxPlayers(Integer maxPlayers) {
        this.maxPlayers = maxPlayers;
        return this;
    }

    public LocalDateTime getCreatedAt() {
        return this.createdAt;
    }

    protected OthelloLobbyResponse setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public OthelloGame getGame() {
        return this.game;
    }

    protected OthelloLobbyResponse setGame(OthelloGame game) {
        this.game = game;
        return this;
    }
}
