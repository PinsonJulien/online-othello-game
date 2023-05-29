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
    public void testAddPlayerToRandomLobby() throws FullLobbyException, PlayerAlreadyInLobbyException, NonPositiveValueException, NonEvenNumberException {
        IOthelloPlayer player = IOthelloPlayer.create();
        OthelloLobby lobby = new OthelloLobby();
        lobby.setMaxPlayers(2);
        List<OthelloLobby> lobbies = new ArrayList<>();
        lobbies.add(lobby);

        when(lobbyRepository.findAll()).thenReturn(lobbies);
        when(lobbyRepository.save(any(OthelloLobby.class))).thenReturn(lobby);

        OthelloLobby result = lobbyService.addPlayerToRandomLobby(player, 2);

        assertNotNull(result);
        assertEquals(lobby, result);
        assertEquals(1, lobby.getPlayers().size());
        assertTrue(lobby.hasPlayer(player));
    }

    @Test
    public void testAddPlayerToNewLobby() throws FullLobbyException, PlayerAlreadyInLobbyException {
        IOthelloPlayer player = IOthelloPlayer.create().setId(1L);
        List<OthelloLobby> lobbies = new ArrayList<>();

        when(lobbyRepository.findAll()).thenReturn(lobbies);
        when(lobbyRepository.save(any(OthelloLobby.class))).thenReturn(new OthelloLobby());

        OthelloLobby result = lobbyService.addPlayerToRandomLobby(player, 2);

        assertNotNull(result);
        assertEquals(1, result.getPlayers().size());
        assertTrue(result.hasPlayer(player));
    }

    @Test
    public void testAddPlayerToFullLobby() throws FullLobbyException, PlayerAlreadyInLobbyException {
        /*OthelloPlayer player = new OthelloPlayer();
        OthelloLobby lobby = new OthelloLobby();
        lobby.setMaxPlayers(1);
        lobby.addPlayer(player);
        List<OthelloLobby> lobbies = new ArrayList<>();
        lobbies.add(lobby);

        when(lobbyRepository.findAll()).thenReturn(lobbies);

        assertThrows(FullLobbyException.class, () -> lobbyService.addPlayerToRandomLobby(player));*/
    }


}