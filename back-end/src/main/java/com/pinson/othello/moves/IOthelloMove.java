package com.pinson.othello.moves;

import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.positions.IOthelloPosition;

import java.time.LocalDateTime;

public interface IOthelloMove {

    static IOthelloMove create() {
        return new OthelloMove();
    }

    IOthelloPosition getPosition();
    IOthelloMove setPosition(Integer row, Integer column);
    IOthelloMove setPosition(IOthelloPosition position);

    // getters and setters

    Long getId();
    Integer getRow();
    IOthelloMove setRow(Integer row);

    Integer getColumn();
    IOthelloMove setColumn(Integer column);

    Boolean isPassed();
    IOthelloMove setPassed(Boolean passed);

    IOthelloGame getGame();
    IOthelloMove setGame(IOthelloGame game);

    IOthelloGamePlayer getGamePlayer();
    IOthelloMove setGamePlayer(IOthelloGamePlayer gamePlayer);

    LocalDateTime getCreatedAt();
    IOthelloMove setCreatedAt(LocalDateTime createdAt);
}
