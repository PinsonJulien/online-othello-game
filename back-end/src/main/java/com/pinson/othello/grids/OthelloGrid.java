package com.pinson.othello.grids;

import com.pinson.othello.commons.entities.grids.Grid;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.IMatrixArrayList;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.tiles.IOthelloTile;

public class OthelloGrid extends Grid<IOthelloTile> implements IOthelloGrid {
    public OthelloGrid() throws NonPositiveValueException {
        super(8, 8);

        this.setupTiles();
    }

    public OthelloGrid(int rows, int columns) throws NonPositiveValueException {
        super(rows, columns);

        this.setupTiles();
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
