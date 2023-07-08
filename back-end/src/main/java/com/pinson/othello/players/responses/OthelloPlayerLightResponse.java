package com.pinson.othello.players.responses;

import java.time.LocalDateTime;

public record OthelloPlayerLightResponse (
    Long id,
    String username,
    LocalDateTime createdAt,
    LocalDateTime updatedAt
) { }
