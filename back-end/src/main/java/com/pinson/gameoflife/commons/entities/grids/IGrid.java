package com.pinson.gameoflife.commons.entities.grids;

import com.pinson.gameoflife.commons.entities.cells.ICell;

public interface IGrid<T extends ICell> {
    int getRows();
    int getColumns();
    T[][] getCells();

    T getCellAt(int row, int column);
    IGrid<T> setCellAt(int row, int column, T cell);
}
