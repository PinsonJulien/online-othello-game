package com.pinson.othello.lobbies;


import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerNotFoundInLobbyException;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;

import java.time.LocalDateTime;
import java.util.List;

public interface IOthelloLobby {

    static IOthelloLobby create() {
        return new OthelloLobby();
    }

    static IOthelloLobby create(Integer maxPlayers) throws NonPositiveValueException, NonEvenNumberException {
        return new OthelloLobby()
                .setMaxPlayers(maxPlayers);
    }

    static IOthelloLobby create(Integer maxPlayers, IOthelloPlayer player) throws NonPositiveValueException, NonEvenNumberException {
        try {
            return IOthelloLobby.create(maxPlayers).addPlayer(player);
        } catch (PlayerAlreadyInLobbyException | FullLobbyException  exception) {
            // this should never happen.
            exception.printStackTrace();

            return new OthelloLobby();
        }
    }

    static IOthelloLobby create(Integer maxPlayers, List<IOthelloPlayer> players) throws NonPositiveValueException, NonEvenNumberException, PlayerAlreadyInLobbyException, FullLobbyException {
        return IOthelloLobby.create(maxPlayers).setPlayers(players);
    }

    IOthelloLobby addPlayer(IOthelloPlayer player) throws PlayerAlreadyInLobbyException, FullLobbyException;
    IOthelloLobby removePlayer(IOthelloPlayer player) throws PlayerNotFoundInLobbyException;

    boolean hasPlayer(IOthelloPlayer player);

    boolean isFull();

    // getters / setters

    Long getId();
    IOthelloLobby setId(Long id);
    List<OthelloPlayer> getPlayers();
    IOthelloLobby setPlayers(List<IOthelloPlayer> players) throws PlayerAlreadyInLobbyException, FullLobbyException;
    OthelloGame getGame();
    IOthelloLobby setGame(OthelloGame game);
    LocalDateTime getCreatedAt();
    IOthelloLobby setCreatedAt(LocalDateTime createdAt);
    Integer getMaxPlayers();
    IOthelloLobby setMaxPlayers(Integer maxPlayers) throws NonPositiveValueException, NonEvenNumberException;
}
