package com.pinson.gameoflife.commons.entities.grids;

import com.pinson.gameoflife.commons.entities.cells.ICell;

import java.util.ArrayList;

public class Grid<T extends ICell> implements IGrid<T> {
    /*private ArrayList<ArrayList<T>> cells;

    private int columns;
    private int rows;


    public Grid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;

        this.cells = new ArrayList<>();
        for (int i = 0; i < rows; ++i) {
            ArrayList<T> row = new ArrayList<>();
            for (int j = 0; j < columns; ++j) {
                row.add(null);
            }

            this.cells.add(row);
        }
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
    public ArrayList<ArrayList<T>> getCells() {
        // Todo : Return a copy of the tiles

        return this.cells;
    }

    @Override
    public T getCellAt(int row, int column) {
        if (row < 0 || row >= this.rows)
            throw new GridIndexOutOfBoundsException("Row must be within grid bounds.");
        if (column < 0 || column >= this.columns)
            throw new GridIndexOutOfBoundsException("Column must be within grid bounds.");

        return this.tiles.get(row).get(column);
    }

    @Override
    public IGrid<T> setCellAt(int row, int column, T cell) {
        this.cells[row][column] = cell;
        return this;
    }

    */

}
