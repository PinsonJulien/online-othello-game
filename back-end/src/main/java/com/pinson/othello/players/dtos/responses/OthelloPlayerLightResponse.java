package com.pinson.othello.players.dtos.responses;

import java.time.LocalDateTime;

public record OthelloPlayerLightResponse (
    Long id,
    String username,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) { }
