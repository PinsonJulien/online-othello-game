package com.pinson.othello.players;

import com.pinson.othello.commons.entities.players.IPlayer;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.lobbies.IOthelloLobby;

import java.time.LocalDateTime;
import java.util.List;

public interface IOthelloPlayer extends IPlayer {
    static IOthelloPlayer create() {
        return new OthelloPlayer();
    }

    Long getId();
    IOthelloPlayer setId(Long id);

    String getUsername();
    IOthelloPlayer setUsername(String username);

    String getPassword();
    IOthelloPlayer setPassword(String password);

    LocalDateTime getCreatedAt();
    IOthelloPlayer setCreatedAt(LocalDateTime createdAt);

    LocalDateTime getUpdatedAt();
    IOthelloPlayer setUpdatedAt(LocalDateTime updatedAt);

    IOthelloLobby getLobby();
    IOthelloPlayer setLobby(IOthelloLobby lobby);

    List<IOthelloGamePlayer> getGamePlayers();
    IOthelloPlayer setGamePlayers(List<IOthelloGamePlayer> gamePlayers);

}
