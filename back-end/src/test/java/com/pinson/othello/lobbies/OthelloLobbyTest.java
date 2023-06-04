package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.lobbies.exceptions.PlayerNotFoundInLobbyException;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OthelloLobbyTest {

    @Test
    void addPlayer() throws FullLobbyException, PlayerAlreadyInLobbyException, NonPositiveValueException, NonEvenNumberException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

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
    void addPlayer__PlayerAlreadyInLobbyException() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(6, players);

        assertThrows(PlayerAlreadyInLobbyException.class, () -> lobby.addPlayer(players.get(0)));
        assertThrows(PlayerAlreadyInLobbyException.class, () -> lobby.addPlayer(players.get(1)));
        assertThrows(PlayerAlreadyInLobbyException.class, () -> lobby.addPlayer(players.get(2)));
        assertThrows(PlayerAlreadyInLobbyException.class, () -> lobby.addPlayer(players.get(3)));
    }

    @Test
    void addPlayer__FullLobbyException() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(4, players);

        assertThrows(FullLobbyException.class, () -> lobby.addPlayer(IOthelloPlayer.create().setId(5L)));
    }

    @Test
    void removePlayer() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException, PlayerNotFoundInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(4, players);
        assertEquals(4, lobby.getPlayers().size());

        lobby.removePlayer(players.get(0));
        assertEquals(3, lobby.getPlayers().size());
        assertEquals(players.get(1), lobby.getPlayers().get(0));
        assertEquals(players.get(2), lobby.getPlayers().get(1));
        assertEquals(players.get(3), lobby.getPlayers().get(2));

        lobby.removePlayer(players.get(1));
        assertEquals(2, lobby.getPlayers().size());
        assertEquals(players.get(2), lobby.getPlayers().get(0));
        assertEquals(players.get(3), lobby.getPlayers().get(1));

        lobby.removePlayer(players.get(2));
        assertEquals(1, lobby.getPlayers().size());
        assertEquals(players.get(3), lobby.getPlayers().get(0));

        lobby.removePlayer(players.get(3));
        assertEquals(0, lobby.getPlayers().size());
    }

    @Test
    void removePlayer__PlayerNotFoundException() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(4, players);

        assertThrows(PlayerNotFoundInLobbyException.class, () -> lobby.removePlayer(IOthelloPlayer.create().setId(5L)));
        assertThrows(PlayerNotFoundInLobbyException.class, () -> lobby.removePlayer(IOthelloPlayer.create()));
    }

    @Test
    void hasPlayer() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(4, players);
        assertTrue(lobby.hasPlayer(players.get(0)));
        assertTrue(lobby.hasPlayer(players.get(1)));
        assertTrue(lobby.hasPlayer(players.get(2)));
        assertTrue(lobby.hasPlayer(players.get(3)));
        assertFalse(lobby.hasPlayer(IOthelloPlayer.create().setId(5L)));
        assertFalse(lobby.hasPlayer(IOthelloPlayer.create()));
    }

    @Test
    void isFull() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(4, players);
        assertTrue(lobby.isFull());

        lobby.setMaxPlayers(6);
        assertFalse(lobby.isFull());

        lobby = IOthelloLobby.create(6, players);
        assertFalse(lobby.isFull());
        lobby.addPlayer(IOthelloPlayer.create().setId(5L));
        assertFalse(lobby.isFull());
        lobby.addPlayer(IOthelloPlayer.create().setId(6L));
        assertTrue(lobby.isFull());
    }

    @Test
    void getPlayers() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(4, players);
        List<OthelloPlayer> lobbyPlayers = lobby.getPlayers();
        assertEquals(4, lobbyPlayers.size());
        assertEquals(players.get(0), lobbyPlayers.get(0));
        assertEquals(players.get(1), lobbyPlayers.get(1));
        assertEquals(players.get(2), lobbyPlayers.get(2));
        assertEquals(players.get(3), lobbyPlayers.get(3));
    }

    @Test
    void setPlayers() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(4);
        lobby.setPlayers(players);
        List<OthelloPlayer> lobbyPlayers = lobby.getPlayers();
        assertEquals(4, lobbyPlayers.size());
        assertEquals(players.get(0), lobbyPlayers.get(0));
        assertEquals(players.get(1), lobbyPlayers.get(1));
        assertEquals(players.get(2), lobbyPlayers.get(2));
        assertEquals(players.get(3), lobbyPlayers.get(3));
    }

    @Test
    void setPlayers__FullLobbyException() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(2);
        assertThrowsExactly(FullLobbyException.class, () -> lobby.setPlayers(players));
    }

    @Test
    void setPlayers__PlayerAlreadyInLobbyException() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        IOthelloPlayer player = IOthelloPlayer.create().setId(1L);
        players.add(player);
        players.add(player);
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(4);
        assertThrowsExactly(PlayerAlreadyInLobbyException.class, () -> lobby.setPlayers(players));

        // test with null values
        player = IOthelloPlayer.create();
        players.set(0, player);
        players.set(1, player);
        assertThrowsExactly(PlayerAlreadyInLobbyException.class, () -> lobby.setPlayers(players));

    }

    @Test
    void setMaxPlayers() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(4, players);
        assertEquals(4, lobby.getMaxPlayers());

        lobby.setMaxPlayers(6);
        assertEquals(6, lobby.getMaxPlayers());
        assertEquals(4, lobby.getPlayers().size());
        assertEquals(players.get(0), lobby.getPlayers().get(0));
        assertEquals(players.get(1), lobby.getPlayers().get(1));
        assertEquals(players.get(2), lobby.getPlayers().get(2));
        assertEquals(players.get(3), lobby.getPlayers().get(3));

        lobby.setMaxPlayers(2);
        assertEquals(2, lobby.getMaxPlayers());
        assertEquals(2, lobby.getPlayers().size());
        assertEquals(players.get(0), lobby.getPlayers().get(0));
        assertEquals(players.get(1), lobby.getPlayers().get(1));
    }

    @Test
    void setMaxPlayers__NonPositiveValueException() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        IOthelloLobby lobby = IOthelloLobby.create(4);
        assertThrowsExactly(NonPositiveValueException.class, () -> lobby.setMaxPlayers(0));
        assertThrowsExactly(NonPositiveValueException.class, () -> lobby.setMaxPlayers(-1));
        assertThrowsExactly(NonPositiveValueException.class, () -> lobby.setMaxPlayers(-595));
    }

    @Test
    void setMaxPlayers__NonEvenNumberException() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        IOthelloLobby lobby = IOthelloLobby.create(4);
        assertThrowsExactly(NonEvenNumberException.class, () -> lobby.setMaxPlayers(1));
        assertThrowsExactly(NonEvenNumberException.class, () -> lobby.setMaxPlayers(3));
        assertThrowsExactly(NonEvenNumberException.class, () -> lobby.setMaxPlayers(589));
    }
}