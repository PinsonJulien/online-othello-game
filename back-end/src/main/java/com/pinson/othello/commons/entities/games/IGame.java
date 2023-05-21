package com.pinson.othello.commons.entities.games;

import com.pinson.othello.commons.entities.grids.IGrid;
import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

import java.util.List;

public interface IGame<T extends ITile<P, T>,  G extends IGrid<T>, P extends IPiece<T, P>> {

    int getGridWidth();
    int getGridHeight();

    P getPieceAt(int row, int column) throws MatrixIndexOutOfBoundsException;
    IGame<T, G, P> setPieceAt(int row, int column, P piece) throws MatrixIndexOutOfBoundsException;
    List<P> getAllPieces();

    T getTileAt(int row, int column) throws MatrixIndexOutOfBoundsException;

    List<T> getAllTiles();
    List<T> getAllEmptyTiles();
    boolean isTileEmpty(int row, int column) throws MatrixIndexOutOfBoundsException;

}
