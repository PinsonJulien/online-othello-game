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

class IOthelloLobbyTest {

    @Test
    void create__MaxPlayers() throws NonPositiveValueException, NonEvenNumberException {
        IOthelloLobby lobby = IOthelloLobby.create(2);
        assertEquals(2, lobby.getMaxPlayers());

        lobby = IOthelloLobby.create(4);
        assertEquals(4, lobby.getMaxPlayers());

        lobby = IOthelloLobby.create(650);
        assertEquals(650, lobby.getMaxPlayers());
    }

    @Test
    void create__MaxPlayers__NonPositiveNumberException() {
        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(0));
        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(-2));
        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(-432));
    }

    @Test
    void create__MaxPlayers__NonEvenNumberException() {
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(1));
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(3));
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(11));
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(533));
    }

    @Test
    void create__MaxPlayers_Player() throws NonPositiveValueException, NonEvenNumberException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(2, players.get(0));
        assertEquals(2, lobby.getMaxPlayers());
        assertFalse(lobby.isFull());
        assertEquals(1, lobby.getPlayers().size());
        assertEquals(players.get(0).getId(), lobby.getPlayers().get(0).getId());

        lobby = IOthelloLobby.create(4, players.get(1));
        assertEquals(4, lobby.getMaxPlayers());
        assertFalse(lobby.isFull());
        assertEquals(1, lobby.getPlayers().size());
        assertEquals(players.get(1).getId(), lobby.getPlayers().get(0).getId());
    }

    @Test
    void create__MaxPlayers_Player__NonPositiveValueException() {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(0, players.get(0)));
        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(-1, players.get(1)));
        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(-3, players.get(2)));
        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(-567, players.get(3)));
    }

    @Test
    void create__MaxPlayers_Player__NonEvenNumberException() {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(3, players.get(0)));
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(5, players.get(1)));
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(7, players.get(2)));
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(533, players.get(3)));
    }

    @Test
    void create__MaxPlayers_Players() throws NonPositiveValueException, FullLobbyException, PlayerAlreadyInLobbyException, NonEvenNumberException {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        IOthelloLobby lobby = IOthelloLobby.create(2, players.subList(0, 2));
        assertEquals(2, lobby.getMaxPlayers());
        assertTrue(lobby.isFull());
        assertEquals(2, lobby.getPlayers().size());
        assertEquals(players.get(0).getId(), lobby.getPlayers().get(0).getId());
        assertEquals(players.get(1).getId(), lobby.getPlayers().get(1).getId());

        lobby = IOthelloLobby.create(4, players.subList(2, 4));
        assertEquals(4, lobby.getMaxPlayers());
        assertFalse(lobby.isFull());
        assertEquals(2, lobby.getPlayers().size());
        assertEquals(players.get(2).getId(), lobby.getPlayers().get(0).getId());
        assertEquals(players.get(3).getId(), lobby.getPlayers().get(1).getId());

        lobby = IOthelloLobby.create(4, players);
        assertEquals(4, lobby.getMaxPlayers());
        assertTrue(lobby.isFull());
        assertEquals(4, lobby.getPlayers().size());
        assertEquals(players.get(0).getId(), lobby.getPlayers().get(0).getId());
        assertEquals(players.get(1).getId(), lobby.getPlayers().get(1).getId());
        assertEquals(players.get(2).getId(), lobby.getPlayers().get(2).getId());
        assertEquals(players.get(3).getId(), lobby.getPlayers().get(3).getId());
    }

    @Test
    void create__MaxPlayers_Players__NonPositiveValueException() {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(0, players.subList(0, 2)));
        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(-1, players.subList(1, 3)));
        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(-3, players.subList(2, 4)));
        assertThrowsExactly(NonPositiveValueException.class, () -> IOthelloLobby.create(-567, players));
    }

    @Test
    void create__MaxPlayers_Players__NonEvenNumberException() {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(3, players.subList(0, 2)));
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(5, players.subList(1, 3)));
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(7, players.subList(2, 4)));
        assertThrowsExactly(NonEvenNumberException.class, () -> IOthelloLobby.create(533, players));
    }

    @Test
    void create__MaxPlayers_Players__FullLobbyException() {
        List<IOthelloPlayer> players = new ArrayList<>();
        players.add(IOthelloPlayer.create().setId(1L));
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        assertThrowsExactly(FullLobbyException.class, () -> IOthelloLobby.create(2, players));
    }

    @Test
    void create__MaxPlayers_Players__PlayerAlreadyInLobbyException() {
        List<IOthelloPlayer> players = new ArrayList<>();
        IOthelloPlayer player = IOthelloPlayer.create().setId(1L);
        players.add(player);
        players.add(player);
        players.add(IOthelloPlayer.create().setId(2L));
        players.add(IOthelloPlayer.create().setId(3L));
        players.add(IOthelloPlayer.create().setId(4L));

        assertThrowsExactly(PlayerAlreadyInLobbyException.class, () -> IOthelloLobby.create(4, players));
    }
}