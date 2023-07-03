package com.pinson.othello.players.responses;

import com.pinson.othello.gamePlayers.responses.OthelloGamePlayerResponseFactory;
import com.pinson.othello.lobbies.responses.OthelloLobbyResponseFactory;
import com.pinson.othello.players.IOthelloPlayer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloPlayerResponseFactory {

    private final OthelloGamePlayerResponseFactory gamePlayerResponseFactory;
    private final OthelloLobbyResponseFactory lobbyResponseFactory;

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
            this.gamePlayerResponseFactory.createList(player.getGamePlayers()),
            this.lobbyResponseFactory.createList(player.getLobby())
        );
    }

    public List<OthelloPlayerResponse> createList(List<IOthelloPlayer> players) {
        List<OthelloPlayerResponse> response = new ArrayList<>();

        for (IOthelloPlayer player : players) {
            response.add(this.create(player));
        }

        return response;
    }

    public OthelloPlayerResponse createLight(IOthelloPlayer player) {
        return new OthelloPlayerLightResponse(
            player.getId(),
            player.getUsername(),
            player.getCreatedAt(),
            player.getUpdatedAt(),
            this.gamePlayerResponseFactory.createList(player.getGamePlayers()),
        );
    }




}
