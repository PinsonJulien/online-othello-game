package com.pinson.othello.players.responses;

import java.util.List;

public record OthelloPlayerLightResponse (
    Long id,
    String username,
    String createdAt,
    String updatedAt,
    List<Long> gamePlayers,
    Long lobby
) { }
