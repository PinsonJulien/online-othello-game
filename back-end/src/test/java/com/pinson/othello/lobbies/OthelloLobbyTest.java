package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.players.IOthelloPlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OthelloLobbyTest {

    @Test
    void addPlayer() throws FullLobbyException, PlayerAlreadyInLobbyException, NonPositiveValueException, NonEvenNumberException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create());
        players.add(IOthelloPlayer.create());
        players.add(IOthelloPlayer.create());
        players.add(IOthelloPlayer.create());

        IOthelloLobby lobby = IOthelloLobby.create(2);

        lobby.addPlayer(players.get(0));
        assertEquals(1, lobby.getPlayers().size());
        assertFalse(lobby.isFull());
        lobby.addPlayer(players.get(1));
        assertEquals(2, lobby.getPlayers().size());
        assertTrue(lobby.isFull());

        lobby.setMaxPlayers(4);

        assertFalse(lobby.isFull());
        lobby.addPlayer(players.get(2));
        assertEquals(3, lobby.getPlayers().size());
        assertFalse(lobby.isFull());
        lobby.addPlayer(players.get(3));
        assertEquals(4, lobby.getPlayers().size());
        assertTrue(lobby.isFull());
    }

    @Test
    void removePlayer() {
    }

    @Test
    void hasPlayer() {
    }

    @Test
    void isFull() {
    }

    @Test
    void getPlayers() {
    }

    @Test
    void setPlayers() {
    }

    @Test
    void setMaxPlayers() {
    }
}