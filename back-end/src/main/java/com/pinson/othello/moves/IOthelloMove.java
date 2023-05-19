package com.pinson.othello.moves;

import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.games.IOthelloGame;

import java.time.LocalDateTime;

public interface IOthelloMove {

    static IOthelloMove create() {
        return new OthelloMove();
    }

    Long getId();
    Integer getRow();
    IOthelloMove setRow(Integer row);

    Integer getColumn();
    IOthelloMove setColumn(Integer column);

    IOthelloGame getGame();
    IOthelloMove setGame(IOthelloGame game);

    IOthelloGamePlayer getGamePlayer();
    IOthelloMove setGamePlayer(IOthelloGamePlayer gamePlayer);

    LocalDateTime getCreatedAt();
    IOthelloMove setCreatedAt(LocalDateTime createdAt);
}
