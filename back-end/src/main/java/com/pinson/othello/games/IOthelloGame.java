package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.IGame;
import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.grids.IOthelloGrid;
import com.pinson.othello.moves.OthelloMove;
import com.pinson.othello.players.IOthelloPlayer;
import com.pinson.othello.tiles.IOthelloTile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface IOthelloGame extends IGame<IOthelloTile, IOthelloGrid, IOthelloDisk> {

    static IOthelloGame create(List<OthelloGamePlayer> gamePlayers, int gridWidth, int gridHeight) throws GridSizeException, InvalidNumberOfPlayersException {
        return new OthelloGame(gamePlayers, gridWidth, gridHeight);
    }

    void skipMove();

    IOthelloGamePlayer getCurrentTurnPlayer();

    ArrayList<IOthelloTile> getValidMoves(IOthelloPlayer player);

    IOthelloGame setDiskAt(int row, int column, IOthelloDisk disk) throws MatrixIndexOutOfBoundsException;
    IOthelloDisk getDiskAt(int row, int column) throws MatrixIndexOutOfBoundsException;

    List<IOthelloDisk> getAllDisks();

    IOthelloGrid getGrid();

    // Getters and Setters

    Long getId();
    IOthelloGame setId(Long id);

    OthelloGameStatus getStatus();
    IOthelloGame setStatus(OthelloGameStatus status);

    IOthelloPlayer getWinner();
    IOthelloGame setWinner(IOthelloPlayer winner);

    List<OthelloGamePlayer> getGamePlayers();
    IOthelloGame setGamePlayers(List<OthelloGamePlayer> gamePlayers);

    List<OthelloMove> getMoves();
    IOthelloGame setMoves(List<OthelloMove> moves);

    LocalDateTime getCreatedAt();
    IOthelloGame setCreatedAt(LocalDateTime createdAt);

    LocalDateTime getUpdatedAt();
    IOthelloGame setUpdatedAt(LocalDateTime updatedAt);

}
