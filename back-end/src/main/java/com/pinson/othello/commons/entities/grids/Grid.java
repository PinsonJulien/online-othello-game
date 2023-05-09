package com.pinson.othello.commons.entities.grids;

import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.commons.exceptions.NotFoundException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.IMatrixArrayList;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

public class Grid<T extends ITile> implements IGrid<T> {

    private final IMatrixArrayList<T> cells;

    public Grid(int rows, int columns) throws NonPositiveValueException {
        this.cells = IMatrixArrayList.create(rows, columns);
    }

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

    @Override
    public IMatrixPosition<Integer> findCell(T cell) throws NotFoundException {
        return this.cells.find(cell);
    }

}
