package com.pinson.othello.grids;

import com.pinson.othello.commons.entities.grids.IGrid;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.tiles.IOthelloTile;

public interface IOthelloGrid extends IGrid<IOthelloTile> {

    static IOthelloGrid create() {
        try {
            return new OthelloGrid();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    static IOthelloGrid create(int rows, int columns) {
        try {
            return new OthelloGrid(rows, columns);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    IOthelloGrid setDiskAt(int row, int column, IOthelloDisk disk) throws MatrixIndexOutOfBoundsException;
}
