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
import static org.mockito.Mockito.when;

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
}