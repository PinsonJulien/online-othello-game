package com.pinson.othello.games.dtos.responses;

import com.pinson.othello.games.OthelloGameStatus;

import java.time.LocalDateTime;

public record OthelloGameLightResponse (
    Long id,
    OthelloGameStatus status,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) { }
