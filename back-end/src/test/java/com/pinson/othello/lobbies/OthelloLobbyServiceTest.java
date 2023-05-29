package com.pinson.othello.lobbies;

import com.pinson.othello.commons.exceptions.NonEvenNumberException;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.lobbies.exceptions.FullLobbyException;
import com.pinson.othello.lobbies.exceptions.LobbyNotFoundException;
import com.pinson.othello.lobbies.exceptions.PlayerAlreadyInLobbyException;
import com.pinson.othello.players.IOthelloPlayer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
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
        List<IOthelloPlayer> existingPlayers = new ArrayList<>();

        int nbPlayers = 3;
        for (int i = 0; i < nbPlayers; i++) {
            existingPlayers.add(IOthelloPlayer.create().setUsername("player" + i).setPassword("password" + i));
        }

        // two new players
        int nbNewPlayers = 5;
        List<IOthelloPlayer> newPlayers = new ArrayList<>();
        for (int i = 0; i < nbNewPlayers; i++) {
            newPlayers.add(IOthelloPlayer.create().setUsername("player" + i + nbPlayers).setPassword("password" + i + nbPlayers));
        }

        // two existing lobbies with 1/2 and 2/4 players
        List<OthelloLobby> lobbies = new ArrayList<>();
        lobbies.add((OthelloLobby) IOthelloLobby.create(2, existingPlayers.get(0))); // 1/2
        lobbies.add((OthelloLobby) IOthelloLobby.create(4, existingPlayers.subList(1, 3))); // 2/4

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

}