package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.IGame;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.gamePlayers.GamePlayer;
import com.pinson.othello.grids.IOthelloGrid;
import com.pinson.othello.moves.OthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.tiles.IOthelloTile;

import java.time.LocalDateTime;
import java.util.Set;

public interface IOthelloGame extends IGame<IOthelloTile, IOthelloGrid, IOthelloDisk> {
    void skipMove();

    IOthelloPlayer getCurrentTurnPlayer();

    // Getters and Setters

    Long getId();
    IOthelloGame setId(Long id);

    OthelloGameStatus getStatus();
    IOthelloGame setStatus(OthelloGameStatus status);

    IOthelloPlayer getWinner();
    IOthelloGame setWinner(IOthelloPlayer winner);

    Set<GamePlayer> getGamePlayers();
    IOthelloGame setGamePlayers(Set<GamePlayer> gamePlayers);

    Set<OthelloMove> getMoves();
    IOthelloGame setMoves(Set<OthelloMove> moves);

    LocalDateTime getCreatedAt();
    IOthelloGame setCreatedAt(LocalDateTime createdAt);

    LocalDateTime getUpdatedAt();
    IOthelloGame setUpdatedAt(LocalDateTime updatedAt);

}
