package com.pinson.othello.lobbies;


import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerNotFoundException;
import com.pinson.othello.players.IOthelloPlayer;

import java.time.LocalDateTime;
import java.util.List;

public interface IOthelloLobby {

    static IOthelloLobby create() {
        return new OthelloLobby();
    }

    static IOthelloLobby create(IOthelloPlayer player) {
        IOthelloLobby lobby = new OthelloLobby();
        lobby.addPlayer(player);

        return lobby;
    }

    IOthelloLobby addPlayer(IOthelloPlayer player) throws PlayerAlreadyInLobbyException, FullLobbyException;
    IOthelloLobby removePlayer(IOthelloPlayer player) throws PlayerNotFoundException;

    boolean hasPlayer(IOthelloPlayer player);

    boolean isFull();

    // getters / setters

    Long getId();
    List<IOthelloPlayer> getPlayers();
    IOthelloLobby setPlayers(List<IOthelloPlayer> players);
    LocalDateTime getCreatedAt();
    IOthelloLobby setCreatedAt();
    Integer getMaxPlayers();
    IOthelloLobby setMaxPlayers();
}
