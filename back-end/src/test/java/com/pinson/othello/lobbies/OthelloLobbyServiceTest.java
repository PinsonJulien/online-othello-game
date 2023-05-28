package com.pinson.othello.lobbies;

import com.pinson.othello.lobbies.exceptions.LobbyNotFoundException;
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


}