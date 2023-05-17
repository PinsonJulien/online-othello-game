package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.IGame;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.grids.IOthelloGrid;
import com.pinson.othello.moves.OthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.tiles.IOthelloTile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;

public interface IOthelloGame extends IGame<IOthelloTile, IOthelloGrid, IOthelloDisk> {
    void skipMove();

    IOthelloPlayer getCurrentTurnPlayer();

    ArrayList<IOthelloTile> getValidMoves(IOthelloPlayer player);

    // Getters and Setters

    Long getId();
    IOthelloGame setId(Long id);

    OthelloGameStatus getStatus();
    IOthelloGame setStatus(OthelloGameStatus status);

    IOthelloPlayer getWinner();
    IOthelloGame setWinner(IOthelloPlayer winner);

    Set<OthelloGamePlayer> getGamePlayers();
    IOthelloGame setGamePlayers(Set<OthelloGamePlayer> gamePlayers);

    Set<OthelloMove> getMoves();
    IOthelloGame setMoves(Set<OthelloMove> moves);

    LocalDateTime getCreatedAt();
    IOthelloGame setCreatedAt(LocalDateTime createdAt);

    LocalDateTime getUpdatedAt();
    IOthelloGame setUpdatedAt(LocalDateTime updatedAt);

}
