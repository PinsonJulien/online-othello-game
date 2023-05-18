package com.pinson.othello.grids;

import com.pinson.othello.commons.entities.grids.Grid;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.tiles.IOthelloTile;

import java.util.List;

public class OthelloGrid extends Grid<IOthelloTile> implements IOthelloGrid {
    public OthelloGrid() throws NonPositiveValueException {
        super(8, 8);
    }

    public OthelloGrid(int rows, int columns) throws NonPositiveValueException {
        super(rows, columns);
    }

    @Override
    public IOthelloGrid setDiskAt(int row, int column, IOthelloDisk disk) throws MatrixIndexOutOfBoundsException {
        this.getTileAt(row, column).setPiece(disk);

        return this;
    }
}
