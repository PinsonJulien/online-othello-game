package com.pinson.othello.commons.entities.games;

import com.pinson.othello.commons.entities.grids.IGrid;
import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

public abstract class Game<T extends ITile<?, ?>,  G extends IGrid<T>, P extends IPiece<?, ?>> implements IGame<T, G, P> {

    private G grid;

    public Game(G grid) {
        this.setGrid(grid);
    }

    @Override
    public G getGrid() {
        return grid;
    }

    private void setGrid(G grid) {
        this.grid = grid;
    }

    @Override
    abstract public void playMove(int x, int y, P piece) throws MatrixIndexOutOfBoundsException;
    @Override
    abstract public void playMove(IMatrixPosition<Integer> position, P piece) throws MatrixIndexOutOfBoundsException;
}
