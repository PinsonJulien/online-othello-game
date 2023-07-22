package com.pinson.othello.players;

import com.pinson.othello.commons.entities.players.IPlayer;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.lobbies.IOthelloLobby;
import com.pinson.othello.lobbies.OthelloLobby;

import java.time.LocalDateTime;
import java.util.List;

public interface IOthelloPlayer extends IPlayer {
    static IOthelloPlayer create() {
        return new OthelloPlayer();
    }

    IOthelloPlayer copy();

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

    List<OthelloLobby> getLobbies();

    List<OthelloGamePlayer> getGamePlayers();
    IOthelloPlayer setGamePlayers(List<IOthelloGamePlayer> gamePlayers);

}
