package com.pinson.othello.games;

import com.pinson.othello.commons.entities.games.IGame;
import com.pinson.othello.commons.entities.games.exceptions.GameOverException;
import com.pinson.othello.commons.entities.grids.exceptions.GridSizeException;
import com.pinson.othello.commons.exceptions.InvalidMoveException;
import com.pinson.othello.commons.exceptions.InvalidNumberOfPlayersException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.gamePlayers.IOthelloGamePlayer;
import com.pinson.othello.gamePlayers.OthelloGamePlayer;
import com.pinson.othello.games.exceptions.CannotPassTurnException;
import com.pinson.othello.games.exceptions.UnknownGamePlayerException;
import com.pinson.othello.grids.IOthelloGrid;
import com.pinson.othello.moves.IOthelloMove;
import com.pinson.othello.moves.OthelloMove;
import com.pinson.othello.tiles.IOthelloTile;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface IOthelloGame extends IGame<IOthelloTile, IOthelloGrid, IOthelloDisk> {

    static IOthelloGame create(List<OthelloGamePlayer> gamePlayers, int gridWidth, int gridHeight) throws GridSizeException, InvalidNumberOfPlayersException {
        return new OthelloGame(gamePlayers, gridWidth, gridHeight);
    }

    IOthelloGamePlayer getCurrentTurnPlayer();

    List<IOthelloMove> getValidMoves();
    List<IOthelloMove> getValidMoves(IOthelloGamePlayer player);

    IOthelloMove getRandomValidMove();

    boolean isMoveValid(IOthelloMove move);

    IOthelloGame playMove(IOthelloMove move) throws InvalidMoveException, GameOverException, CannotPassTurnException, UnknownGamePlayerException;
    IOthelloGame skipMove() throws InvalidMoveException, GameOverException, CannotPassTurnException, UnknownGamePlayerException;

    IOthelloGame setDiskAt(int row, int column, IOthelloDisk disk) throws MatrixIndexOutOfBoundsException;
    IOthelloDisk getDiskAt(int row, int column) throws MatrixIndexOutOfBoundsException;

    List<IOthelloDisk> getAllDisks();

    IOthelloGrid getGrid();

    boolean isGameOver();

    List<OthelloGamePlayer> getWinners();
    List<OthelloGamePlayer> getLosers();

    // Getters and Setters

    Long getId();
    IOthelloGame setId(Long id);

    OthelloGameStatus getStatus();
    IOthelloGame setStatus(OthelloGameStatus status);

    List<OthelloGamePlayer> getGamePlayers();
    IOthelloGame setGamePlayers(List<OthelloGamePlayer> gamePlayers);

    List<OthelloMove> getMoves();
    IOthelloGame setMoves(List<OthelloMove> moves);

    LocalDateTime getCreatedAt();
    IOthelloGame setCreatedAt(LocalDateTime createdAt);

    LocalDateTime getUpdatedAt();
    IOthelloGame setUpdatedAt(LocalDateTime updatedAt);

}
