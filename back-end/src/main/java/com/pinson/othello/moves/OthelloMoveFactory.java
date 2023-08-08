package com.pinson.othello.moves;

import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.positions.IOthelloPosition;
import org.springframework.stereotype.Component;

@Component
public class OthelloMoveFactory {
    public OthelloMoveFactory() {

    }
    
    public OthelloMove create(
        final OthelloGamePlayer gamePlayer,
        final IOthelloPosition position
    ) {
        OthelloMove move = new OthelloMove();
        move.setGamePlayer(gamePlayer);
        move.setPosition(position);
        return move;
    }
}
