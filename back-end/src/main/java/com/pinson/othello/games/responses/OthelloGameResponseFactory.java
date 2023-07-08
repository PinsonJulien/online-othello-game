package com.pinson.othello.games.responses;

import com.pinson.othello.gamePlayers.responses.OthelloGamePlayerResponseFactory;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.grids.responses.GridSizeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloGameResponseFactory {

    private final OthelloGamePlayerResponseFactory othelloGamePlayerResponseFactory;

    @Autowired
    public OthelloGameResponseFactory(
        OthelloGamePlayerResponseFactory othelloGamePlayerResponseFactory
    ) {
        this.othelloGamePlayerResponseFactory = othelloGamePlayerResponseFactory;
    }

    public OthelloGameResponse create(OthelloGame game) {
        return new OthelloGameResponse(
            game.getId(),
            this.createGridSize(game),
            game.getStatus(),
            this.othelloGamePlayerResponseFactory.createLightList(game.getGamePlayers()),
            this.othelloGamePlayerResponseFactory.createLightList(game.getWinners()),
            this.othelloGamePlayerResponseFactory.createLightList(game.getLosers()),
            game.getCreatedAt(),
            game.getUpdatedAt()
        );
    }

    public List<OthelloGameResponse> createList(List<OthelloGame> games) {
        List<OthelloGameResponse> list = new ArrayList<>();

        for (OthelloGame game : games) {
            list.add(this.create(game));
        }

        return list;
    }

    public OthelloGameLightResponse createLight(OthelloGame game) {
        return new OthelloGameLightResponse(
            game.getId(),
            this.createGridSize(game),
            game.getStatus(),
            game.getCreatedAt(),
            game.getUpdatedAt()
        );
    }

    public List<OthelloGameLightResponse> createLightList(List<OthelloGame> games) {
        List<OthelloGameLightResponse> list = new ArrayList<>();

        for (OthelloGame game : games) {
            list.add(this.createLight(game));
        }

        return list;
    }

    protected GridSizeResponse createGridSize(OthelloGame game) {
        return new GridSizeResponse(
            game.getGridWidth(),
            game.getGridHeight()
        );
    }

}
