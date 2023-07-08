package com.pinson.othello.gamePlayers.responses;

import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;

public record OthelloGamePlayerLightResponse (
    Long id,
    Integer score,
    OthelloGamePlayerColor color
) { }
