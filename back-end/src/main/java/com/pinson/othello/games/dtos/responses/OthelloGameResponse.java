package com.pinson.othello.games.dtos.responses;

import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerLightResponse;
import com.pinson.othello.games.OthelloGameStatus;
import com.pinson.othello.grids.dtos.responses.OthelloGridResponse;

import java.time.LocalDateTime;
import java.util.List;

public record OthelloGameResponse (
    Long id,
    OthelloGameStatus status,
    List<OthelloGamePlayerLightResponse> players,
    List<OthelloGamePlayerLightResponse> winners,
    List<OthelloGamePlayerLightResponse> losers,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    OthelloGridResponse grid,
    OthelloGamePlayerLightResponse currentPlayer

) {}
