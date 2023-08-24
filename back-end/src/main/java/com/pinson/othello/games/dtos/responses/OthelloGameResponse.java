package com.pinson.othello.games.dtos.responses;

import com.pinson.othello.gamePlayers.dtos.responses.OthelloGamePlayerResponse;
import com.pinson.othello.games.OthelloGameStatus;
import com.pinson.othello.grids.dtos.responses.OthelloGridResponse;

import java.time.LocalDateTime;
import java.util.List;

public record OthelloGameResponse (
    Long id,
    OthelloGameStatus status,
    List<OthelloGamePlayerResponse> players,
    List<OthelloGamePlayerResponse> winners,
    List<OthelloGamePlayerResponse> losers,
    LocalDateTime createdAt,
    LocalDateTime updatedAt,
    OthelloGridResponse grid,
    OthelloGamePlayerResponse currentPlayer

) {}
