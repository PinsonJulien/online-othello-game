package com.pinson.othello.players.dtos;

import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerLightResponse;
import com.pinson.othello.lobbies.dtos.responses.OthelloLobbyLightResponse;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.dtos.responses.OthelloPlayerLightResponse;
import com.pinson.othello.players.dtos.responses.OthelloPlayerResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloPlayerDTOMapper {

    public OthelloPlayerDTOMapper() {
        //
    }

    public OthelloPlayerResponse toResponse(
        IOthelloPlayer player,
        List<OthelloGamePlayerLightResponse> gamePlayers,
        OthelloLobbyLightResponse lobby
    ) {
        return new OthelloPlayerResponse(
            player.getId(),
            player.getUsername(),
            player.getCreatedAt(),
            player.getUpdatedAt(),
            gamePlayers,
            lobby
        );
    }

    public OthelloPlayerLightResponse toLightResponse(IOthelloPlayer player) {
        return new OthelloPlayerLightResponse(
            player.getId(),
            player.getUsername(),
            player.getCreatedAt(),
            player.getUpdatedAt()
        );
    }

    public List<OthelloPlayerLightResponse> toLightResponseList(List<? extends IOthelloPlayer> players) {
        List<OthelloPlayerLightResponse> response = new ArrayList<>();

        for (IOthelloPlayer player : players) {
            response.add(this.toLightResponse(player));
        }

        return response;
    }

}
