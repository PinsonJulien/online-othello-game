package com.pinson.othello.games.dtos.responses;

import com.pinson.othello.gamePlayers.dtos.OthelloGamePlayerDTOMapper;
import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.dtos.OthelloGameDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloGameResponseFactory {

    private final OthelloGameDTOMapper othelloGameDTOMapper;
    private final OthelloGamePlayerDTOMapper othelloGamePlayerDTOMapper;

    @Autowired
    public OthelloGameResponseFactory(
        OthelloGameDTOMapper othelloGameDTOMapper,
        OthelloGamePlayerDTOMapper othelloGamePlayerDTOMapper
    ) {
        this.othelloGameDTOMapper = othelloGameDTOMapper;
        this.othelloGamePlayerDTOMapper = othelloGamePlayerDTOMapper;
    }

    public OthelloGameResponse create(IOthelloGame game) {
        return this.othelloGameDTOMapper.toResponse(
            game,
            this.othelloGamePlayerDTOMapper.toLightResponseList(game.getGamePlayers()),
            this.othelloGamePlayerDTOMapper.toLightResponseList(game.getWinners()),
            this.othelloGamePlayerDTOMapper.toLightResponseList(game.getLosers())
        );
    }

    public List<OthelloGameResponse> createList(List<? extends IOthelloGame> games) {
        List<OthelloGameResponse> response = new ArrayList<>();

        for (IOthelloGame game : games) {
            response.add(this.create(game));
        }

        return response;
    }

    public OthelloGameLightResponse createLight(IOthelloGame game) {
        return this.othelloGameDTOMapper.toLightResponse(game);
    }

    public List<OthelloGameLightResponse> createLightList(List<? extends IOthelloGame> games) {
        return this.othelloGameDTOMapper.toLightResponseList(games);
    }
}
