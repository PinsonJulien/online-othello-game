package com.pinson.othello.games.dtos;

import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerLightResponse;
import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.dtos.responses.OthelloGameLightResponse;
import com.pinson.othello.games.dtos.responses.OthelloGameResponse;
import com.pinson.othello.grids.responses.GridSizeResponse;
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
        List<OthelloGamePlayerLightResponse> players,
        List<OthelloGamePlayerLightResponse> winners,
        List<OthelloGamePlayerLightResponse> losers
    ) {
        return new OthelloGameResponse(
            game.getId(),
            this.createGridSize(game),
            game.getStatus(),
            players,
            winners,
            losers,
            game.getCreatedAt(),
            game.getUpdatedAt()
        );
    }

    public OthelloGameLightResponse toLightResponse(IOthelloGame game) {
        return new OthelloGameLightResponse(
            game.getId(),
            this.createGridSize(game),
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

    protected GridSizeResponse createGridSize(IOthelloGame game) {
        return new GridSizeResponse(
                game.getGridWidth(),
                game.getGridHeight()
        );
    }

}
