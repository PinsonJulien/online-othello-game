package com.pinson.othello.gamePlayers.dtos.responses;

import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.games.dtos.responses.OthelloGameLightResponse;
import com.pinson.othello.players.dtos.responses.OthelloPlayerLightResponse;

public record OthelloGamePlayerResponse (
    Long id,
    Integer score,
    OthelloGamePlayerColor color,
    OthelloGameLightResponse game,
    OthelloPlayerLightResponse player
) { }
