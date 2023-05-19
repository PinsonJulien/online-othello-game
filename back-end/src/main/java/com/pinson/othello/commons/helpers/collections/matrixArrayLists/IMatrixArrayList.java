package com.pinson.othello.commons.helpers.collections.matrixArrayLists;

import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.commons.exceptions.NotFoundException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @param <T> Any Object
 */
public interface IMatrixArrayList<T> extends Iterable<ArrayList<T>> {

    /**
     * Instantiate a MatrixArrayList without a specific size.
     *
     * @return IMatrixArrayList<T> The pointer of the matrix.
     */
    static <T> IMatrixArrayList<T> create() {
        return new MatrixArrayList<>();
    }

    /**
     * Instantiate a MatrixArrayList with a default size, values are null by default.
     *
     * @param rows int
     * @param columns int
     * @return IMatrixArrayList<T> The pointer of the matrix.
     * @throws NonPositiveValueException When the given rows or columns are not a positive number.
     */
    static <T> IMatrixArrayList<T> create(int rows, int columns) throws NonPositiveValueException {
        return new MatrixArrayList<T>(rows, columns);
    }

    static <T> IMatrixArrayList<T> create(int rows, int columns, List<T> elements) throws NonPositiveValueException {
        return new MatrixArrayList<T>(rows, columns, elements);
    }

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



    IMatrixArrayList<T> resize(int rows, int columns) throws NonPositiveValueException;
    IMatrixArrayList<T> insertRows(int amount) throws NonPositiveValueException;
    IMatrixArrayList<T> insertRows(int amount, int index) throws NonPositiveValueException, MatrixIndexOutOfBoundsException;

    IMatrixArrayList<T> insertColumns(int amount) throws NonPositiveValueException;
    IMatrixArrayList<T> insertColumns(int amount, int index) throws NonPositiveValueException, MatrixIndexOutOfBoundsException;
    IMatrixArrayList<T> removeRows(int amount) throws NonPositiveValueException;
    IMatrixArrayList<T> removeRows(int amount, int index) throws NonPositiveValueException, MatrixIndexOutOfBoundsException;
    IMatrixArrayList<T> removeColumns(int amount) throws NonPositiveValueException;
    IMatrixArrayList<T> removeColumns(int amount, int index) throws NonPositiveValueException, MatrixIndexOutOfBoundsException;

    List<T> toList();
}
