package com.pinson.othello.gamePlayers.responses;

import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.games.responses.OthelloGameLightResponse;
import com.pinson.othello.players.responses.OthelloPlayerLightResponse;

public record OthelloGamePlayerResponse (
    Long id,
    Integer score,
    OthelloGamePlayerColor color,
    OthelloGameLightResponse game,
    OthelloPlayerLightResponse player
) { }
