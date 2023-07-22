package com.pinson.othello.games.dtos.responses;

import com.pinson.othello.games.OthelloGameStatus;
import com.pinson.othello.grids.responses.GridSizeResponse;

import java.time.LocalDateTime;

public record OthelloGameLightResponse (
    Long id,
    GridSizeResponse gridSize,
    OthelloGameStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) { }
