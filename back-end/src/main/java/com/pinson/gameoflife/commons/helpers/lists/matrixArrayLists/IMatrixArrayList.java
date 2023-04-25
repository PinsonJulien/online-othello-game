package com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists;

import com.pinson.gameoflife.commons.exceptions.NotFoundException;
import com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.gameoflife.commons.helpers.positions.MatrixPositions.IMatrixPosition;

/**
 * @param <T> Any Object
 */
public interface IMatrixArrayList<T> {

    // getters

    /**
     * @return Number of rows of the matrix, as int.
     */
    int getRows();

    /**
     * @return Number of columns of the matrix, as int.
     */
    int getColumns();

    /**
     * Get an object stored in the matrix at specified indexes.
     *
     * @param row int
     * @param column int
     * @return The T typed object stored in the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    T get(int row, int column) throws MatrixIndexOutOfBoundsException;

    /**
     * Get an object stored in the matrix at specified MatrixPosition.
     *
     * @param position IMatrixPosition<Integer>
     * @return The T typed object stored in the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    T get(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;

    /**
     * Set an object at specified indexes of the matrix.
     *
     * @param row int
     * @param column int
     * @param value Object to store, of type T.
     * @return The pointer of the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    IMatrixArrayList<T> set(int row, int column, T value) throws MatrixIndexOutOfBoundsException;

    /**
     * Set an object at specified MatrixPosition of the matrix.
     *
     * @param position IMatrixPosition<Integer>
     * @param value Object to store, of type T.
     * @return The pointer of the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    IMatrixArrayList<T> set(IMatrixPosition<Integer> position, T value) throws MatrixIndexOutOfBoundsException;

    /**
     * Remove an object at specified indexes of the matrix.
     *
     * @param row int
     * @param column int
     * @return The pointer of the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    IMatrixArrayList<T> remove(int row, int column) throws MatrixIndexOutOfBoundsException;

    /**
     * Remove an object at specified MatrixPosition of the matrix.
     *
     * @param position IMatrixPosition<Integer>
     * @return The pointer of the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    IMatrixArrayList<T> remove(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;

    /**
     * Finds the MatrixPosition of a given T typed value.
     *
     * @param value Object of type T.
     * @return The MatrixPosition of the found value.
     * @throws NotFoundException When the given object doesn't exist within the matrix.
     */
    IMatrixPosition<Integer> find(T value) throws NotFoundException;

    // Todo: Add missing methods in MatrixArrayList.
    // IMatrixArrayList<T> padColumns();
    // IMatrixArrayList<T> resize(int rows, int columns);
}
