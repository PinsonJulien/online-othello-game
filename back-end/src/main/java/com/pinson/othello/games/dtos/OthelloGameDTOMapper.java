package com.pinson.othello.games.dtos;

import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerResponse;
import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.dtos.responses.OthelloGameLightResponse;
import com.pinson.othello.games.dtos.responses.OthelloGameResponse;
import com.pinson.othello.grids.dtos.responses.OthelloGridResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OthelloGameDTOMapper {

    public OthelloGameDTOMapper() {
        //
    }

    public OthelloGameResponse toResponse(
        IOthelloGame game,
        List<OthelloGamePlayerResponse> players,
        List<OthelloGamePlayerResponse> winners,
        List<OthelloGamePlayerResponse> losers,
        OthelloGridResponse grid,
        OthelloGamePlayerResponse currentPlayer
    ) {
        return new OthelloGameResponse(
            game.getId(),
            game.getStatus(),
            players,
            winners,
            losers,
            game.getCreatedAt(),
            game.getUpdatedAt(),
            grid,
            currentPlayer
        );
    }

    public OthelloGameLightResponse toLightResponse(IOthelloGame game) {
        return new OthelloGameLightResponse(
            game.getId(),
            game.getStatus(),
            game.getCreatedAt(),
            game.getUpdatedAt()
        );
    }

    public List<OthelloGameLightResponse> toLightResponseList(List<? extends IOthelloGame> games) {
        List<OthelloGameLightResponse> response = new ArrayList<>();

        for (IOthelloGame game : games) {
            response.add(this.toLightResponse(game));
        }

        return response;
    }

}
