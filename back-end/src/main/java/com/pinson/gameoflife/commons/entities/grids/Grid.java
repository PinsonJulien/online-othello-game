package com.pinson.gameoflife.commons.entities.grids;

import com.pinson.gameoflife.commons.entities.cells.ICell;
import com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists.IMatrixArrayList;
import com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

import java.util.ArrayList;

public class Grid<T extends ICell> implements IGrid<T> {

    private IMatrixArrayList<T> cells;

    @Override
    public int getRows() {
        return this.cells.getRows();
    }

    @Override
    public int getColumns() {
        return this.cells.getColumns();
    }

    @Override
    public IMatrixArrayList<T> getCells() {
        return this.cells;
    }

    @Override
    public T getCellAt(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.cells.get(row, column);
    }

    @Override
    public IGrid<T> setCellAt(int row, int column, T cell) throws MatrixIndexOutOfBoundsException {
        this.cells.set(row, column, cell);

        return this;
    }

    // todo: getCircularNeighbours
    // todo: getSquaredNeighbours using getRectangularNeighbours
    // todo: getPerpendicularNeighbours (or better name for typical cross) (+)
    // todo: getDiagonalNeighbours (X)
    // todo: getRectangularNeighbours
    // todo: getTriangularNeighbours
    // todo: getHexagonalNeighbours

}
