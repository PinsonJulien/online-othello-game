package com.pinson.othello.gamePlayers;

import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.players.OthelloPlayer;

public interface IOthelloGamePlayer {

    static IOthelloGamePlayer create(IOthelloPlayer player, OthelloGamePlayerColor playerColor) {
        return new OthelloGamePlayer(player, playerColor);
    }

    static IOthelloGamePlayer create(IOthelloGame game, OthelloPlayer player, OthelloGamePlayerColor playerColor) {
        return new OthelloGamePlayer(game, player, playerColor);
    }

    Long getId();
    IOthelloGamePlayer setId(Long id);

    IOthelloGame getGame();
    IOthelloGamePlayer setGame(IOthelloGame game);

    IOthelloPlayer getPlayer();
    IOthelloGamePlayer setPlayer(IOthelloPlayer player);

    OthelloGamePlayerColor getPlayerColor();
    IOthelloGamePlayer setPlayerColor(OthelloGamePlayerColor playerColor);

}
