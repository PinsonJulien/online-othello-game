package com.pinson.othello.lobbies.responses;

import com.pinson.othello.games.responses.OthelloGameLightResponse;
import com.pinson.othello.players.responses.OthelloPlayerLightResponse;

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
