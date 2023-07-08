package com.pinson.othello.gamePlayers.responses;

import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.games.responses.OthelloGameResponseFactory;
import com.pinson.othello.players.responses.OthelloPlayerResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloGamePlayerResponseFactory {

    private final OthelloGameResponseFactory othelloGameResponseFactory;
    private final OthelloPlayerResponseFactory othelloPlayerResponseFactory;

    @Autowired
    public OthelloGamePlayerResponseFactory(
        OthelloGameResponseFactory othelloGameResponseFactory,
        OthelloPlayerResponseFactory othelloPlayerResponseFactory
    ) {
        this.othelloGameResponseFactory = othelloGameResponseFactory;
        this.othelloPlayerResponseFactory = othelloPlayerResponseFactory;
    }

    public OthelloGamePlayerResponse create(OthelloGamePlayer gamePlayer) {
        return new OthelloGamePlayerResponse(
            gamePlayer.getId(),
            gamePlayer.getScore(),
            gamePlayer.getPlayerColor(),
            this.othelloGameResponseFactory.createLight(gamePlayer.getGame()),
            this.othelloPlayerResponseFactory.createLight(gamePlayer.getPlayer())
        );
    }

    public List<OthelloGamePlayerResponse> createList(List<OthelloGamePlayer> gamePlayers) {
        List<OthelloGamePlayerResponse> response = new ArrayList<>();

        for (OthelloGamePlayer gamePlayer : gamePlayers) {
            response.add(this.create(gamePlayer));
        }

        return response;
    }

    public OthelloGamePlayerLightResponse createLight(OthelloGamePlayer gamePlayer) {
        return new OthelloGamePlayerLightResponse(
            gamePlayer.getId(),
            gamePlayer.getScore(),
            gamePlayer.getPlayerColor()
        );
    }

    public List<OthelloGamePlayerLightResponse> createLightList(List<OthelloGamePlayer> gamePlayers) {
        List<OthelloGamePlayerLightResponse> response = new ArrayList<>();

        for (OthelloGamePlayer gamePlayer: gamePlayers) {
            response.add(this.createLight(gamePlayer));
        }

        return response;
    }
}
