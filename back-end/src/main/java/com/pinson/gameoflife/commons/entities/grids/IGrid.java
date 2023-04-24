package com.pinson.gameoflife.commons.entities.grids;

import com.pinson.gameoflife.commons.entities.cells.ICell;

import java.util.ArrayList;

public interface IGrid<T extends ICell> {
    int getRows();
    int getColumns();
    ArrayList<ArrayList<T>> getCells();

    T getCellAt(int row, int column);
    IGrid<T> setCellAt(int row, int column, T cell);
}
