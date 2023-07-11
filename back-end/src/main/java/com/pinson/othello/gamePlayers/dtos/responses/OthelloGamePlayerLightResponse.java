package com.pinson.othello.gamePlayers.dtos.responses;

import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;

public record OthelloGamePlayerLightResponse (
    Long id,
    Integer score,
    OthelloGamePlayerColor color
) { }
