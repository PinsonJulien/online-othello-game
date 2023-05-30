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
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = { ApplicationTestConfiguration.class })
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OthelloLobbyServiceTest {
    @Autowired
    private OthelloLobbyService lobbyService;

    @Autowired
    private OthelloLobbyRepository lobbyRepository;

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
        }

        lobbies.get(0).setPlayers(players.subList(0, 2)); // full
        lobbies.get(1).addPlayer(players.get(2)); // 1 / 2 players
        lobbies.get(2).addPlayer(players.get(3)); // 1 / 2 players
        lobbies.get(3).setPlayers(players.subList(3, 7)); // full
        lobbies.get(4).setPlayers(players.subList(7, 10)); // 3 / 4 players
        lobbies.get(5).setPlayers(players.subList(10, 14)); // 4/6 players
        lobbies.get(6).addPlayer(players.get(14)); // 1/6

        lobbyRepository.saveAll(lobbies);
        // Necessary to save players.
        //playerRepository.saveAll(players.stream().map(p -> (OthelloPlayer) p).toList());
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
    void deleteLobbyById__LobbyNotFoundException() {
        assertThrowsExactly(LobbyNotFoundException.class, () -> lobbyService.deleteLobbyById(100L));
        assertThrowsExactly(LobbyNotFoundException.class, () -> lobbyService.deleteLobbyById(101L));
    }

    @Test
    void addPlayerToRandomLobby() throws NonPositiveValueException, NonEvenNumberException, FullLobbyException, PlayerAlreadyInLobbyException {
        System.out.println("-------------------");
        for(OthelloLobby lobby: lobbyRepository.findAll()) {
            System.out.println(lobby.getId() + " " + lobby.getMaxPlayers() + " " + lobby.getPlayers().size() + " " + lobby.isFull());
        }

        // Try to add a player to the first lobby, this will make it full.
        OthelloLobby result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(15), 2);
        assertEquals(2, result.getPlayers().size());
        assertEquals(lobbies.get(1).getId(), result.getId());
        assertEquals(players.get(15).getUsername(), result.getPlayers().get(1).getUsername());
        assertTrue(result.isFull());

        System.out.println("-------------------");
        for(OthelloLobby lobby: lobbyRepository.findAll()) {
            System.out.println(lobby.getId() + " " + lobby.getMaxPlayers() + " " + lobby.getPlayers().size() + " " + lobby.isFull());
        }

        // Try to add a player to another max 2 lobby, this will make it full.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(16), 2);

        System.out.println("-------------------");
        for(OthelloLobby lobby: lobbyRepository.findAll()) {
            System.out.println(lobby.getId() + " " + lobby.getMaxPlayers() + " " + lobby.getPlayers().size() + " " + lobby.isFull());
        }

        assertEquals(2, result.getPlayers().size());
        assertEquals(lobbies.get(2).getId(), result.getId());
        assertEquals(players.get(16).getUsername(), result.getPlayers().get(1).getUsername());
        assertTrue(result.isFull());

        System.out.println("-------------------");
        for (OthelloLobby lobby : lobbyRepository.findLobbiesByMaxPlayersAndNotFull(2)) {
            System.out.println(lobby.getId() + " " + lobby.getPlayers().size() + " " + lobby.isFull());
        }
        System.out.println("-------------------");

        // Try to add a player to another max 2 lobby, this will create a new one.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(17), 2);
        assertEquals(1, result.getPlayers().size());
        for (OthelloLobby lobby : lobbies) {
            assertNotEquals(lobby.getId(), result.getId());
        }
        assertEquals(players.get(17).getUsername(), result.getPlayers().get(0).getUsername());
        assertFalse(result.isFull());

        // Try to add a player to another max 4 lobby, this will make it almost full.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(17), 4);
        assertEquals(lobbies.get(1), result);
        assertEquals(3, result.getPlayers().size());
        assertTrue(result.hasPlayer(players.get(17)));
        assertFalse(result.isFull());

        // Try to add a player to another max 4 lobby, this will make it full.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(18), 4);
        assertEquals(lobbies.get(1), result);
        assertEquals(4, result.getPlayers().size());
        assertTrue(result.hasPlayer(players.get(18)));
        assertTrue(result.isFull());

        // Try to add a player to another max 4 lobby, this will create a new one.
        result = lobbyService.addPlayerToRandomLobby((OthelloPlayer) players.get(19), 4);
        assertFalse(lobbies.contains(result));
        assertEquals(1, result.getPlayers().size());
        assertTrue(result.hasPlayer(players.get(19)));
        assertFalse(result.isFull());
    }



}



/*@ExtendWith(MockitoExtension.class)
class OthelloLobbyServiceTest {

    @Mock
    private OthelloLobbyRepository lobbyRepository;

    @InjectMocks
    private OthelloLobbyService lobbyService;

    @Test
    void getAllLobbies() {
        List<OthelloLobby> lobbies = new ArrayList<>();
        lobbies.add(new OthelloLobby());
        lobbies.add(new OthelloLobby());

        when(lobbyRepository.findAll()).thenReturn(lobbies);

        List<OthelloLobby> result = lobbyService.getAllLobbies();

        assertEquals(lobbies, result);
    }

    @Test
    void getLobbyById() {
        OthelloLobby lobby = new OthelloLobby();
        lobby.setId(1L);

        when(lobbyRepository.findById(1L)).thenReturn(java.util.Optional.of(lobby));

        OthelloLobby result = lobbyService.getLobbyById(1L);

        assertEquals(lobby, result);
    }

    @Test
    void getLobbyById__LobbyNotFoundException() {
        when(lobbyRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        assertThrowsExactly(LobbyNotFoundException.class, () -> lobbyService.getLobbyById(1L));
    }

    @Test
    public void testDeleteLobbyById() {
        OthelloLobby lobby = new OthelloLobby();
        lobby.setId(1L);

        doNothing().when(lobbyRepository).deleteById(1L);

        lobbyService.deleteLobbyById(1L);

        verify(lobbyRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testDeleteLobbyById__LobbyNotFoundException() {
        doThrow(new LobbyNotFoundException(1L)).when(lobbyRepository).deleteById(1L);

        assertThrowsExactly(LobbyNotFoundException.class, () -> lobbyService.deleteLobbyById(1L));
    }

    @Test
    public void addPlayerToRandomLobby() throws FullLobbyException, PlayerAlreadyInLobbyException, NonPositiveValueException, NonEvenNumberException {
        List<OthelloPlayer> existingPlayers = new ArrayList<>();

        int nbPlayers = 3;
        for (int i = 0; i < nbPlayers; i++) {
            existingPlayers.add((OthelloPlayer) IOthelloPlayer.create().setUsername("player" + i).setPassword("password" + i));
        }

        // two new players
        int nbNewPlayers = 5;
        List<OthelloPlayer> newPlayers = new ArrayList<>();
        for (int i = 0; i < nbNewPlayers; i++) {
            newPlayers.add((OthelloPlayer) IOthelloPlayer.create().setUsername("player" + i + nbPlayers).setPassword("password" + i + nbPlayers));
        }

        // two existing lobbies with 1/2 and 2/4 players
        List<OthelloLobby> lobbies = new ArrayList<>();
        lobbies.add((OthelloLobby) IOthelloLobby.create(2, existingPlayers.get(0))); // 1/2
        lobbies.add((OthelloLobby) IOthelloLobby.create(4, existingPlayers.get(1)).addPlayer(existingPlayers.get(2))); // 2/4

        when(lobbyRepository.findLobbiesByMaxPlayersAndNotFull(2)).thenReturn(lobbies.subList(0, 1));
        when(lobbyRepository.findLobbiesByMaxPlayersAndNotFull(4)).thenReturn(lobbies.subList(1, 2));

        // Try to add a player to the first lobby, this will make it full.
        when(lobbyRepository.save(any(OthelloLobby.class))).thenReturn(lobbies.get(0));
        OthelloLobby result = lobbyService.addPlayerToRandomLobby(newPlayers.get(0), 2);
        assertEquals(2, result.getPlayers().size());
        assertEquals(lobbies.get(0), result);
        assertTrue(result.hasPlayer(newPlayers.get(0)));
        assertTrue(result.isFull());

        // Try to add a player to another max 2 lobby, this will create a new one.
        when(lobbyRepository.findLobbiesByMaxPlayersAndNotFull(2)).thenReturn(new ArrayList<>());
        OthelloLobby newLobby = (OthelloLobby) IOthelloLobby.create(2, newPlayers.get(1));
        when(lobbyRepository.save(any(OthelloLobby.class))).thenReturn(newLobby);

        result = lobbyService.addPlayerToRandomLobby(newPlayers.get(1), 2);
        assertEquals(1, result.getPlayers().size());
        assertFalse(lobbies.contains(result));
        assertTrue(result.hasPlayer(newPlayers.get(1)));
        assertFalse(result.isFull());

        // Try to add a player to another max 4 lobby, this will make it almost full.
        when(lobbyRepository.save(any(OthelloLobby.class))).thenReturn(lobbies.get(1));
        result = lobbyService.addPlayerToRandomLobby(newPlayers.get(2), 4);
        assertEquals(lobbies.get(1), result);
        assertEquals(3, result.getPlayers().size());
        assertTrue(result.hasPlayer(newPlayers.get(2)));
        assertFalse(result.isFull());

        // Try to add a player to another max 4 lobby, this will make it full.
        result = lobbyService.addPlayerToRandomLobby(newPlayers.get(3), 4);
        assertEquals(lobbies.get(1), result);
        assertEquals(4, result.getPlayers().size());
        assertTrue(result.hasPlayer(newPlayers.get(3)));
        assertTrue(result.isFull());

        // Try to add a player to another max 4 lobby, this will create a new one.
        when(lobbyRepository.findLobbiesByMaxPlayersAndNotFull(4)).thenReturn(new ArrayList<>());
        newLobby = (OthelloLobby) IOthelloLobby.create(4, newPlayers.get(4));
        when(lobbyRepository.save(any(OthelloLobby.class))).thenReturn(newLobby);
        result = lobbyService.addPlayerToRandomLobby(newPlayers.get(4), 4);
        assertFalse(lobbies.contains(result));
        assertEquals(1, result.getPlayers().size());
        assertTrue(result.hasPlayer(newPlayers.get(4)));
        assertFalse(result.isFull());
    }

}*/