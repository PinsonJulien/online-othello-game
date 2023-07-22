package com.pinson.othello.gamePlayers.dtos.responses;

import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.dtos.OthelloGamePlayerDTOMapper;
import com.pinson.othello.games.dtos.OthelloGameDTOMapper;
import com.pinson.othello.players.dtos.OthelloPlayerDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloGamePlayerResponseFactory {

    private final OthelloGamePlayerDTOMapper othelloGamePlayerDTOMapper;
    private final OthelloGameDTOMapper othelloGameDTOMapper;
    private final OthelloPlayerDTOMapper othelloPlayerDTOMapper;

    @Autowired
    public OthelloGamePlayerResponseFactory(
        OthelloGamePlayerDTOMapper othelloGamePlayerDTOMapper,
        OthelloGameDTOMapper othelloGameDTOMapper,
        OthelloPlayerDTOMapper othelloPlayerDTOMapper
    ) {
        this.othelloGamePlayerDTOMapper = othelloGamePlayerDTOMapper;
        this.othelloGameDTOMapper = othelloGameDTOMapper;
        this.othelloPlayerDTOMapper = othelloPlayerDTOMapper;
    }

    public OthelloGamePlayerResponse create(IOthelloGamePlayer gamePlayer) {
        return this.othelloGamePlayerDTOMapper.toResponse(
            gamePlayer,
            this.othelloGameDTOMapper.toLightResponse(gamePlayer.getGame()),
            this.othelloPlayerDTOMapper.toLightResponse(gamePlayer.getPlayer())
        );
    }

    public List<OthelloGamePlayerResponse> createList(List<? extends IOthelloGamePlayer> gamePlayers) {
        List<OthelloGamePlayerResponse> response = new ArrayList<>();

        for (IOthelloGamePlayer gamePlayer : gamePlayers) {
            response.add(this.create(gamePlayer));
        }

        return response;
    }

    public OthelloGamePlayerLightResponse createLight(IOthelloGamePlayer gamePlayer) {
        return this.othelloGamePlayerDTOMapper.toLightResponse(gamePlayer);
    }

    public List<OthelloGamePlayerLightResponse> createLightList(List<? extends IOthelloGamePlayer> gamePlayers) {
        return this.othelloGamePlayerDTOMapper.toLightResponseList(gamePlayers);
    }
}
