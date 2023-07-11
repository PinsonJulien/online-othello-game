package com.pinson.othello.lobbies.dtos.responses;

import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.dtos.OthelloGameDTOMapper;
import com.pinson.othello.games.dtos.responses.OthelloGameLightResponse;
import com.pinson.othello.lobbies.IOthelloLobby;
import com.pinson.othello.lobbies.dtos.OthelloLobbyDTOMapper;
import com.pinson.othello.players.dtos.OthelloPlayerDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloLobbyResponseFactory {

    private final OthelloLobbyDTOMapper othelloLobbyDTOMapper;
    private final OthelloPlayerDTOMapper othelloPlayerDTOMapper;
    private final OthelloGameDTOMapper othelloGameDTOMapper;

    @Autowired
    public OthelloLobbyResponseFactory(
        OthelloLobbyDTOMapper othelloLobbyDTOMapper,
        OthelloPlayerDTOMapper othelloPlayerDTOMapper,
        OthelloGameDTOMapper othelloGameDTOMapper
    ) {
        this.othelloLobbyDTOMapper = othelloLobbyDTOMapper;
        this.othelloPlayerDTOMapper = othelloPlayerDTOMapper;
        this.othelloGameDTOMapper = othelloGameDTOMapper;
    }

    public OthelloLobbyResponse create(IOthelloLobby lobby) {
        return this.create(lobby, null);
    }

    public OthelloLobbyResponse create(IOthelloLobby lobby, IOthelloGame game) {
        OthelloGameLightResponse gameResponse = (game == null)
            ? null
            : this.othelloGameDTOMapper.toLightResponse(game);

        return this.othelloLobbyDTOMapper.toResponse(
            lobby,
            this.othelloPlayerDTOMapper.toLightResponseList(lobby.getPlayers()),
            gameResponse
        );
    }

    public List<OthelloLobbyResponse> createList(List<? extends IOthelloLobby> lobbies) {
        List<OthelloLobbyResponse> response = new ArrayList<>();

        for (IOthelloLobby lobby : lobbies) {
            response.add(this.create(lobby));
        }

        return response;
    }

    public OthelloLobbyLightResponse createLight(IOthelloLobby lobby) {
        return this.othelloLobbyDTOMapper.toLightResponse(lobby);
    }

    public List<OthelloLobbyLightResponse> createLightList(List<? extends IOthelloLobby> lobbies) {
        List<OthelloLobbyLightResponse> response = new ArrayList<>();

        for (IOthelloLobby lobby : lobbies) {
            response.add(this.createLight(lobby));
        }

        return response;
    }

}
