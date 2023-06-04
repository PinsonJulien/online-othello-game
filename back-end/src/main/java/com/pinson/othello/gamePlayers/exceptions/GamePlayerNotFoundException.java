package com.pinson.othello.gamePlayers.exceptions;

import com.pinson.othello.games.IOthelloGame;
import com.pinson.othello.players.IOthelloPlayer;

public class GamePlayerNotFoundException extends RuntimeException {
    public GamePlayerNotFoundException(Long id) {
        super("Could not find GamePlayer " + id + ".");
    }

    public GamePlayerNotFoundException(Long gameId, Long playerId) {
        super("Could not find GamePlayer with gameId " + gameId + " and playerId " + playerId + ".");
    }

    public GamePlayerNotFoundException(IOthelloGame game, IOthelloPlayer player) {
        this(game.getId(), player.getId());
    }
}
