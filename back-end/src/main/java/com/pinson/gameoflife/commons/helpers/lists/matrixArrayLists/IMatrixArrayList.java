package com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists;

import com.pinson.gameoflife.commons.exceptions.NotFoundException;
import com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.gameoflife.commons.helpers.positions.MatrixPositions.IMatrixPosition;

public interface IMatrixArrayList<T> {

    // getters
    int getRows();
    int getColumns();

    T get(int row, int column) throws MatrixIndexOutOfBoundsException;
    T get(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;

    IMatrixArrayList<T> set(int row, int column, T value) throws MatrixIndexOutOfBoundsException;
    IMatrixArrayList<T> set(IMatrixPosition<Integer> position, T value) throws MatrixIndexOutOfBoundsException;

    IMatrixArrayList<T> remove(int row, int column) throws MatrixIndexOutOfBoundsException;
    IMatrixArrayList<T> remove(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;

    IMatrixPosition<Integer> find(T value) throws NotFoundException;

    // Todo: Add missing methods in MatrixArrayList.
    // IMatrixArrayList<T> padColumns();
    // IMatrixArrayList<T> resize(int rows, int columns);
}
