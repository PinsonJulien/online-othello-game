package com.pinson.othello.gamePlayers;

import com.pinson.othello.games.OthelloGame;
import com.pinson.othello.players.OthelloPlayer;

public interface IOthelloGamePlayer {

    Long getId();
    IOthelloGamePlayer setId(Long id);

    OthelloGame getGame();
    IOthelloGamePlayer setGame(OthelloGame game);

    OthelloPlayer getPlayer();
    IOthelloGamePlayer setPlayer(OthelloPlayer player);

    OthelloGamePlayerColor getPlayerColor();
    IOthelloGamePlayer setPlayerColor(OthelloGamePlayerColor playerColor);

}
