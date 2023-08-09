package com.pinson.othello.gamePlayers.dtos;

import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerLightResponse;
import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerResponse;
import com.pinson.othello.games.dtos.responses.OthelloGameLightResponse;
import com.pinson.othello.players.dtos.responses.OthelloPlayerLightResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloGamePlayerDTOMapper {
    public OthelloGamePlayerDTOMapper() {
        //
    }

    public OthelloGamePlayerResponse toResponse(
        IOthelloGamePlayer gamePlayer,
        OthelloGameLightResponse game,
        OthelloPlayerLightResponse player
    ) {
        return new OthelloGamePlayerResponse(
            gamePlayer.getId(),
            gamePlayer.getScore(),
            gamePlayer.getPlayerColor(),
            game,
            player
        );
    }

    public OthelloGamePlayerLightResponse toLightResponse(IOthelloGamePlayer gamePlayer) {
        return new OthelloGamePlayerLightResponse(
            gamePlayer.getId(),
            gamePlayer.getScore(),
            gamePlayer.getPlayerColor()
        );
    }

    public List<OthelloGamePlayerLightResponse> toLightResponseList(List<? extends IOthelloGamePlayer> gamePlayers) {
        List<OthelloGamePlayerLightResponse> response = new ArrayList<>();

        for (IOthelloGamePlayer gamePlayer : gamePlayers) {
            response.add(this.toLightResponse(gamePlayer));
        }

        return response;
    }

}
