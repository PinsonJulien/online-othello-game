package com.pinson.othello.players.responses;

import com.pinson.othello.gamePlayers.responses.OthelloGamePlayerResponseFactory;
import com.pinson.othello.lobbies.responses.OthelloLobbyResponseFactory;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloPlayerResponseFactory {

    private final OthelloGamePlayerResponseFactory gamePlayerResponseFactory;
    private final OthelloLobbyResponseFactory lobbyResponseFactory;

    @Autowired
    public OthelloPlayerResponseFactory(
        OthelloGamePlayerResponseFactory gamePlayerResponseFactory,
        OthelloLobbyResponseFactory lobbyResponseFactory
    ) {
        this.gamePlayerResponseFactory = gamePlayerResponseFactory;
        this.lobbyResponseFactory = lobbyResponseFactory;
    }

    public OthelloPlayerResponse create(IOthelloPlayer player) {
        return new OthelloPlayerResponse(
            player.getId(),
            player.getUsername(),
            player.getCreatedAt(),
            player.getUpdatedAt(),
            this.gamePlayerResponseFactory.createLightList(player.getGamePlayers()),
            this.lobbyResponseFactory.createLight(player.getLobby())
        );
    }

    public List<OthelloPlayerResponse> createList(List<IOthelloPlayer> players) {
        List<OthelloPlayerResponse> response = new ArrayList<>();

        for (IOthelloPlayer player : players) {
            response.add(this.create(player));
        }

        return response;
    }

    public OthelloPlayerLightResponse createLight(IOthelloPlayer player) {
        return new OthelloPlayerLightResponse(
            player.getId(),
            player.getUsername(),
            player.getCreatedAt(),
            player.getUpdatedAt()
        );
    }

    public List<OthelloPlayerLightResponse> createLightList(List<OthelloPlayer> players) {
        List<OthelloPlayerLightResponse> response = new ArrayList<>();

        for (OthelloPlayer player : players) {
            response.add(this.createLight(player));
        }

        return response;
    }

}
