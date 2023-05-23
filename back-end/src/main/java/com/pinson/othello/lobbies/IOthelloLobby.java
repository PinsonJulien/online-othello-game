package com.pinson.othello.lobbies;


import com.pinson.othello.commons.exceptions.NonPositiveValueException;
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

    static IOthelloLobby create(Integer maxPlayers, IOthelloPlayer player) throws FullLobbyException, NonPositiveValueException {
        try {
            return new OthelloLobby()
                    .setMaxPlayers(maxPlayers)
                    .addPlayer(player);
        } catch (PlayerAlreadyInLobbyException exception) {
            return new OthelloLobby();
        }
    }

    IOthelloLobby addPlayer(IOthelloPlayer player) throws PlayerAlreadyInLobbyException, FullLobbyException;
    IOthelloLobby removePlayer(IOthelloPlayer player) throws PlayerNotFoundException;

    boolean hasPlayer(IOthelloPlayer player);

    boolean isFull();

    // getters / setters

    Long getId();
    List<IOthelloPlayer> getPlayers();
    IOthelloLobby setPlayers(List<IOthelloPlayer> players) throws PlayerAlreadyInLobbyException, FullLobbyException;
    LocalDateTime getCreatedAt();
    IOthelloLobby setCreatedAt(LocalDateTime createdAt);
    Integer getMaxPlayers();
    IOthelloLobby setMaxPlayers(Integer maxPlayers) throws NonPositiveValueException;
}
