package com.pinson.othello.lobbies;


import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerNotFoundException;
import com.pinson.othello.players.IOthelloPlayer;

import java.time.LocalDateTime;
import java.util.List;

public interface IOthelloLobby {
    static IOthelloLobby create(Integer maxPlayers) throws NonPositiveValueException {
        return new OthelloLobby()
                .setMaxPlayers(maxPlayers);
    }

    static IOthelloLobby create(Integer maxPlayers, IOthelloPlayer player) throws NonPositiveValueException {
        try {
            return IOthelloLobby.create(maxPlayers).addPlayer(player);
        } catch (PlayerAlreadyInLobbyException | FullLobbyException exception) {
            // this should never happen.
            exception.printStackTrace();

            return new OthelloLobby();
        }
    }

    static IOthelloLobby create(Integer maxPlayers, List<IOthelloPlayer> players) throws NonPositiveValueException, PlayerAlreadyInLobbyException, FullLobbyException {
        return IOthelloLobby.create(maxPlayers).setPlayers(players);
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
    IOthelloLobby setMaxPlayers(Integer maxPlayers) throws NonPositiveValueException, NonEvenNumberException;
}
