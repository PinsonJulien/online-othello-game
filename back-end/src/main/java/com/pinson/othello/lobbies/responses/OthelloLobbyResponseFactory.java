package com.pinson.othello.lobbies.responses;

import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.games.responses.OthelloGameResponseFactory;
import com.pinson.othello.lobbies.IOthelloLobby;
import com.pinson.othello.lobbies.OthelloLobby;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.responses.OthelloPlayerResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloLobbyResponseFactory {

    private final OthelloGameResponseFactory othelloGameResponseFactory;
    private final OthelloPlayerResponseFactory othelloPlayerResponseFactory;

    @Autowired
    public OthelloLobbyResponseFactory(
        OthelloGameResponseFactory othelloGameResponseFactory,
        OthelloPlayerResponseFactory othelloPlayerResponseFactory
    ) {
        this.othelloGameResponseFactory = othelloGameResponseFactory;
        this.othelloPlayerResponseFactory = othelloPlayerResponseFactory;
    }

    public OthelloLobbyResponse create(OthelloLobby lobby) {
        OthelloLobbyResponse response = new OthelloLobbyResponse(
            lobby.getId(),
            lobby.getMaxPlayers(),
            lobby.getCreatedAt(),
            null,
            null
        );

        return response;
    }

    public OthelloLobbyResponse create(OthelloLobby lobby, OthelloGame game) {
        return new OthelloLobbyResponse(
            lobby.getId(),
            lobby.getMaxPlayers(),
            lobby.getCreatedAt(),
            this.othelloPlayerResponseFactory.createLightList(lobby.getPlayers()),
            this.othelloGameResponseFactory.createLight(game)
        );
    }

    public List<OthelloLobbyResponse> createList(List<OthelloLobby> lobbies) {
        List<OthelloLobbyResponse> response = new ArrayList<>();

        for (OthelloLobby lobby : lobbies) {
            response.add(this.create(lobby));
        }

        return response;
    }

    public OthelloLobbyLightResponse createLight(OthelloLobby lobby) {
        return new OthelloLobbyLightResponse(
            lobby.getId(),
            lobby.getMaxPlayers(),
            lobby.getCreatedAt()
        );
    }

    public List<OthelloLobbyLightResponse> createLightList(List<OthelloLobby> lobbies) {
        List<OthelloLobbyLightResponse> response = new ArrayList<>();

        for (OthelloLobby lobby : lobbies) {
            response.add(this.createLight(lobby));
        }

        return response;
    }

}
