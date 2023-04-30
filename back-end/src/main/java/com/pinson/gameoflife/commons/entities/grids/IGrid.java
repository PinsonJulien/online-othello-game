package com.pinson.gameoflife.commons.entities.grids;

import com.pinson.gameoflife.commons.entities.cells.ICell;
import com.pinson.gameoflife.commons.helpers.collections.matrixArrayLists.IMatrixArrayList;
import com.pinson.gameoflife.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

public interface IGrid<T extends ICell> {

    int getRows();

    int getColumns();

    IMatrixArrayList<T> getCells();

    T getCellAt(int row, int column) throws MatrixIndexOutOfBoundsException;

    IGrid<T> setCellAt(int row, int column, T cell) throws MatrixIndexOutOfBoundsException;
    
}
