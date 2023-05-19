package com.pinson.othello.grids;

import com.pinson.othello.commons.entities.grids.Grid;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.IMatrixArrayList;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.tiles.IOthelloTile;

import java.util.List;

public class OthelloGrid extends Grid<IOthelloTile> implements IOthelloGrid {
    public OthelloGrid() throws NonPositiveValueException {
        super(8, 8);

        this.setupTiles();
    }

    public OthelloGrid(int rows, int columns) throws NonPositiveValueException {
        super(rows, columns);

        this.setupTiles();
    }

    @Override
    public IOthelloGrid setDiskAt(int row, int column, IOthelloDisk disk) throws MatrixIndexOutOfBoundsException {
        this.getTileAt(row, column).setPiece(disk);

        return this;
    }

    @Override
    public IOthelloDisk getDiskAt(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getTileAt(row, column).getPiece();
    }

    protected IOthelloGrid setupTiles() {
        int rows = this.getRows();
        int columns = this.getColumns();
        IMatrixArrayList<IOthelloTile> tiles = this.getTiles();

        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                try {
                    tiles.set(row, column, IOthelloTile.create(row, column));
                } catch (MatrixIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
            }
        }

        return this;
    }

}
