package com.pinson.othello.lobbies.dtos.responses;

import java.time.LocalDateTime;

public record OthelloLobbyLightResponse (
        Long id,
        Integer maxPlayers,
        LocalDateTime createdAt
) {
}
