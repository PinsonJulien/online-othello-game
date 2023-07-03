package com.pinson.othello.gamePlayers.responses;

import com.pinson.othello.gamePlayers.OthelloGamePlayerColor;
import com.pinson.othello.games.responses.OthelloGameResponse;
import com.pinson.othello.players.responses.OthelloPlayerResponse;

public record OthelloGamePlayerResponse (
    Long id,
    Integer score,
    OthelloGamePlayerColor color,
    OthelloGameResponse game,
    OthelloPlayerResponse player
) { }
