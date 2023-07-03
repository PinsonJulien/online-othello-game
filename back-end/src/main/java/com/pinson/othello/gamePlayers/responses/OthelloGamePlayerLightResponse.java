package com.pinson.othello.gamePlayers.responses;

import com.pinson.othello.games.responses.OthelloGameLightResponse;
import com.pinson.othello.players.responses.OthelloPlayerLightResponse;

public record OthelloGamePlayerLightResponse (
    Long id,
    String createdAt,
    String updatedAt,

    OthelloPlayerLightResponse player,
    OthelloGameLightResponse game
) { }
