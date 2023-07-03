package com.pinson.othello.lobbies.responses;

import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.games.responses.OthelloGameResponse;
import com.pinson.othello.players.responses.OthelloPlayerResponse;

import java.time.LocalDateTime;
import java.util.List;


public record OthelloLobbyResponse(
    Long id,
    Integer maxPlayers,
    LocalDateTime createdAt,
    List<OthelloPlayerResponse> players,
    OthelloGameResponse game
) {

}
