package com.pinson.othello.players;

import com.pinson.othello.commons.entities.players.IPlayer;

public interface IOthelloPlayer extends IPlayer {
    static IOthelloPlayer create() {
        return new OthelloPlayer();
    }

}
