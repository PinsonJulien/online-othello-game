package com.pinson.gameoflife.commons.entities.grids;

import com.pinson.gameoflife.commons.entities.cells.ICell;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;

public class Grid<T extends ICell> implements IGrid<T> {
    private T[][] cells;
    private int columns;
    private int rows;


    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.cells = new T[rows][columns];
    }

    @Override
    public int getColumns() {
        return columns;
    }

    protected void setColumns(int columns) {
        this.columns = columns;
    }

    @Override
    public int getRows() {
        return rows;
    }

    protected void setRows(int rows) {
        this.rows = rows;
    }

    @Override
    public T[][] getCells() {
        // Todo : Return a copy of the tiles
        return this.cells;
    }

    @Override
    public T getCellAt(int row, int column) {
        return this.cells[row][column];
    }

    @Override
    public IGrid<T> setCellAt(int row, int column, T cell) {
        this.cells[row][column] = cell;
        return this;
    }

}
