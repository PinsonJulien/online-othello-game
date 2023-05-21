package com.pinson.othello.commons.entities.games;

import com.pinson.othello.commons.entities.grids.IGrid;
import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

import java.util.ArrayList;
import java.util.List;

public abstract class Game<T extends ITile<P, T>,  G extends IGrid<T>, P extends IPiece<T, P>> implements IGame<T, G, P> {

    private G grid;

    public Game() {
        this.setGrid(null);
    }

    public Game(G grid) {
        this.setGrid(grid);
    }

    protected G getGrid() {
        return this.grid;
    }

    protected void setGrid(G grid) {
        this.grid = grid;
    }

    @Override
    public int getGridWidth() {
        return this.grid.getRows();
    }

    @Override
    public int getGridHeight() {
        return this.grid.getColumns();
    }

    @Override
    public P getPieceAt(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.grid.getTileAt(row, column).getPiece();
    }

    @Override
    public IGame<T, G, P> setPieceAt(int row, int column, P piece) throws MatrixIndexOutOfBoundsException {
        this.grid.getTileAt(row, column).setPiece(piece);

        return this;
    }

    @Override
    public List<P> getAllPieces() {
        List<P> pieces = new ArrayList<>();

        for (List<T> row : this.grid.getTiles()) {
            for (T tile : row) {
                // if the tile is not empty
                P piece = tile.getPiece();

                if (piece != null)
                    pieces.add(piece);
            }
        }

        return pieces;
    }
    @Override
    public T getTileAt(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.grid.getTileAt(row, column);
    }

    @Override
    public List<T> getAllTiles() {
        List<T> tiles = new ArrayList<>();

        for (List<T> row : this.grid.getTiles()) {
            tiles.addAll(row);
        }

        return tiles;
    }

    @Override
    public List<T> getAllEmptyTiles() {
        List<T> tiles = new ArrayList<>();

        for (List<T> row : this.grid.getTiles()) {
            for (T tile : row) {
                // if the tile is empty
                if (tile.getPiece() == null)
                    tiles.add(tile);
            }
        }

        return tiles;
    }

    @Override
    public boolean isTileEmpty(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.grid.getTileAt(row, column).getPiece() == null;
    }

}
