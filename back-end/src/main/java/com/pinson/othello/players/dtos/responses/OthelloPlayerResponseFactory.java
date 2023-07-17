package com.pinson.othello.players.dtos.responses;

import com.pinson.othello.gamePlayers.dtos.OthelloGamePlayerDTOMapper;
import com.pinson.othello.lobbies.dtos.OthelloLobbyDTOMapper;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.dtos.OthelloPlayerDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloPlayerResponseFactory {

    private final OthelloPlayerDTOMapper othelloPlayerDTOMapper;
    private final OthelloGamePlayerDTOMapper othelloGamePlayerDTOMapper;
    private final OthelloLobbyDTOMapper othelloLobbyDTOMapper;


    @Autowired
    public OthelloPlayerResponseFactory(
        OthelloPlayerDTOMapper othelloPlayerDTOMapper,
        OthelloGamePlayerDTOMapper othelloGamePlayerDTOMapper,
        OthelloLobbyDTOMapper othelloLobbyDTOMapper
    ) {
        this.othelloPlayerDTOMapper = othelloPlayerDTOMapper;
        this.othelloGamePlayerDTOMapper = othelloGamePlayerDTOMapper;
        this.othelloLobbyDTOMapper = othelloLobbyDTOMapper;
    }

    public OthelloPlayerResponse create(IOthelloPlayer player) {
        return this.othelloPlayerDTOMapper.toResponse(
            player,
            this.othelloGamePlayerDTOMapper.toLightResponseList(player.getGamePlayers()),
            this.othelloLobbyDTOMapper.toLightResponseList(player.getLobbies())
        );
    }

    public List<OthelloPlayerResponse> createList(List<? extends IOthelloPlayer> players) {
        List<OthelloPlayerResponse> response = new ArrayList<>();

        for (IOthelloPlayer player : players) {
            response.add(this.create(player));
        }

        return response;
    }

    public OthelloPlayerLightResponse createLight(IOthelloPlayer player) {
        return this.othelloPlayerDTOMapper.toLightResponse(player);
    }

    public List<OthelloPlayerLightResponse> createLightList(List<? extends IOthelloPlayer> players) {
        return this.othelloPlayerDTOMapper.toLightResponseList(players);
    }

}
