package com.pinson.othello.games.dtos.requests;

public record OthelloGamePlayMoveRequest(
    Integer row,
    Integer column
) {

}
