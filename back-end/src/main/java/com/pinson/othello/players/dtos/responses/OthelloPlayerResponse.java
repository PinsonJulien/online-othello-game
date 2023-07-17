package com.pinson.othello.players.dtos.responses;

import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerLightResponse;
import com.pinson.othello.lobbies.dtos.responses.OthelloLobbyLightResponse;

import java.time.LocalDateTime;
import java.util.List;

public record OthelloPlayerResponse(
    Long id,
    String username,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    List<OthelloGamePlayerLightResponse> gamePlayers,
    List<OthelloLobbyLightResponse> lobbies
) {

}