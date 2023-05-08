package com.pinson.gameoflife.commons.entities.grids;

import com.pinson.gameoflife.commons.entities.tiles.ITile;
import com.pinson.gameoflife.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.gameoflife.commons.exceptions.NotFoundException;
import com.pinson.gameoflife.commons.helpers.collections.matrixArrayLists.IMatrixArrayList;
import com.pinson.gameoflife.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

public interface IGrid<T extends ITile> {

    int getRows();

    int getColumns();

    IMatrixArrayList<T> getCells();

    T getCellAt(int row, int column) throws MatrixIndexOutOfBoundsException;

    IGrid<T> setCellAt(int row, int column, T cell) throws MatrixIndexOutOfBoundsException;

    IMatrixPosition<Integer> findCell(T cell) throws NotFoundException;
}
