package com.pinson.othello.lobbies.dtos.responses;

import com.pinson.othello.games.dtos.responses.OthelloGameLightResponse;
import com.pinson.othello.players.dtos.responses.OthelloPlayerLightResponse;

import java.time.LocalDateTime;
import java.util.List;

public record OthelloLobbyResponse(
        Long id,
        Integer maxPlayers,
        LocalDateTime createdAt,
        List<OthelloPlayerLightResponse> players,
        OthelloGameLightResponse game
) {

}
