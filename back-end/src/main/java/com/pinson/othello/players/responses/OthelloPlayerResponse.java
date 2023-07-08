package com.pinson.othello.players.responses;

import com.pinson.othello.gamePlayers.responses.OthelloGamePlayerLightResponse;
import com.pinson.othello.lobbies.responses.OthelloLobbyLightResponse;

import java.time.LocalDateTime;
import java.util.List;

public record OthelloPlayerResponse(
    Long id,
    String username,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<OthelloGamePlayerLightResponse> gamePlayers,
    OthelloLobbyLightResponse lobby
) {

}