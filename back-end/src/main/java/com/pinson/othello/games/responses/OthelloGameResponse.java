package com.pinson.othello.games.responses;

import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.gamePlayers.responses.OthelloGamePlayerLightResponse;
import com.pinson.othello.games.OthelloGameStatus;
import com.pinson.othello.grids.responses.GridSizeResponse;

import java.time.LocalDateTime;
import java.util.List;

public record OthelloGameResponse (
    Long id,
    GridSizeResponse gridSize,
    OthelloGameStatus status,
    List<OthelloGamePlayerLightResponse> players,
    List<OthelloGamePlayerLightResponse> winners,
    List<OthelloGamePlayerLightResponse> losers,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) {}
