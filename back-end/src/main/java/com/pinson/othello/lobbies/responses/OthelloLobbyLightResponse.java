package com.pinson.othello.lobbies.responses;

import java.time.LocalDateTime;
import java.util.List;

public record OthelloLobbyLightResponse (
    Long id,
    Integer maxPlayers,
    LocalDateTime createdAt
) {
}
