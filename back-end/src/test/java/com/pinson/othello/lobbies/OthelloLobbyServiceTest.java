package com.pinson.othello.lobbies;

import com.pinson.othello.ApplicationTestConfiguration;
import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.LobbyNotFoundException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import com.pinson.othello.players.OthelloPlayerRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = { ApplicationTestConfiguration.class })
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OthelloLobbyServiceTest {
    @Autowired
    private OthelloLobbyService lobbyService;

    @Autowired
    private OthelloLobbyRepository lobbyRepository;

    @Autowired
    private OthelloPlayerRepository playerRepository;

    private List<OthelloLobby> lobbies = new ArrayList<>();
    private List<IOthelloPlayer> players = new ArrayList<>();

    @BeforeEach
    public void setUp() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        lobbies.add((OthelloLobby) IOthelloLobby.create(2));
        lobbies.add((OthelloLobby) IOthelloLobby.create(2));
        lobbies.add((OthelloLobby) IOthelloLobby.create(2));
        lobbies.add((OthelloLobby) IOthelloLobby.create(4));
        lobbies.add((OthelloLobby) IOthelloLobby.create(4));
        lobbies.add((OthelloLobby) IOthelloLobby.create(6));
        lobbies.add((OthelloLobby) IOthelloLobby.create(6));

        // Create players
        int nbPlayers = 30;
        for (int i = 0; i < nbPlayers; i++) {
            players.add(IOthelloPlayer.create().setUsername("player" + i).setPassword("password" + i));
            this.playerRepository.save((OthelloPlayer) players.get(i));
        }

        this.players = this.playerRepository.findAll().stream().map(p -> (IOthelloPlayer) p).toList();

        lobbies.get(0).setPlayers(players.subList(0, 2)); // full
        lobbies.get(1).addPlayer(players.get(2)); // 1 / 2 players
        lobbies.get(2).addPlayer(players.get(3)); // 1 / 2 players
        lobbies.get(3).setPlayers(players.subList(3, 7)); // full
        lobbies.get(4).setPlayers(players.subList(7, 9)); // 2 / 4 players
        lobbies.get(5).setPlayers(players.subList(9, 13)); // 4/6 players
        lobbies.get(6).addPlayer(players.get(13)); // 1/6

        lobbies = lobbyRepository.saveAll(lobbies);
    }

    @Test
    void getAllLobbies() {
        List<OthelloLobby> result = lobbyService.getAllLobbies();
        assertEquals(this.lobbies.size(), result.size());
        assertEquals(this.lobbies, result);
    }

    @Test
    void getLobbyById() {
        OthelloLobby result = lobbyService.getLobbyById(lobbies.get(0).getId());
        assertEquals(lobbies.get(0), result);

        result = lobbyService.getLobbyById(lobbies.get(1).getId());
        assertEquals(lobbies.get(1), result);

        result = lobbyService.getLobbyById(lobbies.get(2).getId());
        assertEquals(lobbies.get(2), result);
    }

    @Test
    void getLobbyById__LobbyNotFoundException() {
        assertThrowsExactly(LobbyNotFoundException.class, () -> lobbyService.getLobbyById(100L));
        assertThrowsExactly(LobbyNotFoundException.class, () -> lobbyService.getLobbyById(101L));
    }
    
    @Test
    void deleteLobbyById() {
        lobbyService.deleteLobbyById(lobbies.get(0).getId());
        assertEquals(lobbies.size() - 1, lobbyService.getAllLobbies().size());
        lobbyService.deleteLobbyById(lobbies.get(1).getId());
        assertEquals(lobbies.size() - 2, lobbyService.getAllLobbies().size());
    }

    @Test
    void addPlayerToRandomLobby() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        // Try to add a player to the first lobby, this will make it full.
        OthelloLobby result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(14), 2);
        assertEquals(2, result.getPlayers().size());
        assertEquals(players.get(14).getUsername(), result.getPlayers().get(1).getUsername());
        assertTrue(result.isFull());

        // Try to add a player to another max 2 lobby, this will make it full.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(15), 2);

        assertEquals(2, result.getPlayers().size());
        assertEquals(players.get(15).getUsername(), result.getPlayers().get(1).getUsername());
        assertTrue(result.isFull());

        // Try to add a player to another max 2 lobby, this will create a new one.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(16), 2);
        assertEquals(1, result.getPlayers().size());
        for (OthelloLobby lobby : lobbies) {
            assertNotEquals(lobby.getId(), result.getId());
        }
        assertEquals(players.get(16).getUsername(), result.getPlayers().get(0).getUsername());
        assertFalse(result.isFull());

        // Try to add a player to another max 4 lobby, this will make it almost full.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(17), 4);
        assertEquals(3, result.getPlayers().size());
        assertEquals(players.get(17).getUsername(), result.getPlayers().get(2).getUsername());
        assertFalse(result.isFull());

        // Try to add a player to another max 4 lobby, this will make it full.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(18), 4);
        assertEquals(4, result.getPlayers().size());
        assertEquals(players.get(18).getUsername(), result.getPlayers().get(3).getUsername());
        assertTrue(result.isFull());

        // Try to add a player to another max 4 lobby, this will create a new one.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(19), 4);
        assertEquals(1, result.getPlayers().size());
        for (OthelloLobby lobby : lobbies) {
            assertNotEquals(lobby.getId(), result.getId());
        }
        assertEquals(players.get(19).getUsername(), result.getPlayers().get(0).getUsername());
        assertFalse(result.isFull());

        // TODO : Maybe create tests for the lobbies of 6, but may not be necessary.

        // Try to add a player to a max 42 lobby, it doesn't exist so it will be created.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(20), 42);
        assertEquals(1, result.getPlayers().size());
        for (OthelloLobby lobby : lobbies) {
            assertNotEquals(lobby.getId(), result.getId());
        }
        assertEquals(players.get(20).getUsername(), result.getPlayers().get(0).getUsername());
        assertFalse(result.isFull());
    }

    @Test
    public void removePlayerFromLobby() {
        OthelloLobby lobby = this.lobbies.get(0);
        OthelloPlayer player = (OthelloPlayer) this.players.get(29);
        OthelloLobby result = this.lobbyService.removePlayerFromLobby(lobby.getId(), player);
        assertEquals(lobby.getPlayers().size(), result.getPlayers().size());

        lobby = this.lobbies.get(0);
        player = (OthelloPlayer) this.players.get(0);
        result = this.lobbyService.removePlayerFromLobby(lobby.getId(), player);
        assertEquals(lobby.getId(), result.getId());
        assertEquals(1, result.getPlayers().size());
        for (OthelloPlayer p : result.getPlayers()) {
            assertNotEquals(p.getId(), this.players.get(0).getId());
        }

        lobby = this.lobbies.get(0);
        player = (OthelloPlayer) this.players.get(1);
        result = this.lobbyService.removePlayerFromLobby(lobby.getId(), player);
        assertEquals(lobby.getId(), result.getId());
        assertEquals(0, result.getPlayers().size());

        lobby = this.lobbies.get(5);
        player = (OthelloPlayer) this.players.get(11);
        result = this.lobbyService.removePlayerFromLobby(lobby.getId(), player);
        assertEquals(lobby.getId(), result.getId());
        assertEquals(3, result.getPlayers().size());
        for (OthelloPlayer p : result.getPlayers()) {
            assertNotEquals(p.getId(), this.players.get(11).getId());
        }
    }

    @Test
    public void removePlayerFromLobby__LobbyNotFoundException() throws NonPositiveValueException, NonEvenNumberException {
        this.lobbyRepository.deleteById(100L);
        OthelloPlayer player = (OthelloPlayer) this.players.get(0);
        assertThrows(LobbyNotFoundException.class, () -> this.lobbyService.removePlayerFromLobby(100L, player));

        this.lobbyRepository.deleteById(151L);
        assertThrows(LobbyNotFoundException.class, () -> this.lobbyService.removePlayerFromLobby(151L, player));
    }

}