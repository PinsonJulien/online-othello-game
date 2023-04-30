package com.pinson.gameoflife.commons.helpers.collections.matrixArrayLists;

import com.pinson.gameoflife.commons.exceptions.NonPositiveValueException;
import com.pinson.gameoflife.commons.exceptions.NotFoundException;
import com.pinson.gameoflife.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.gameoflife.commons.helpers.positions.MatrixPositions.IMatrixPosition;

import java.util.ArrayList;

/**
 * ArrayList of ArrayList.
 *
 * @param <T> Any Object
 */
public class MatrixArrayList<T> implements IMatrixArrayList<T> {
    private final ArrayList<ArrayList<T>> matrix = new ArrayList<ArrayList<T>>();

    private int rows;
    private int columns;

    public MatrixArrayList() {

        try {
            this.setRows(0);
            this.setColumns(0);
        } catch(NonPositiveValueException e) {
            // This should never happen.
        }
    }

    /**
     * Instantiate a MatrixArrayList with a default size, values are null by default.
     *
     * @param rows int
     * @param columns int
     * @throws NonPositiveValueException When the given rows or columns are not a positive number.
     */
    public MatrixArrayList(int rows, int columns) throws NonPositiveValueException {
        this.setRows(rows);
        this.setColumns(columns);

        for (int i = 0; i < rows; i++) {
            ArrayList<T> column = new ArrayList<T>();

            for (int j = 0; j < columns; j++) {
                column.add(null);
            }

            this.matrix.add(column);
        }
    }

    /**
     * @return Number of rows of the matrix, as int.
     */
    @Override
    public int getRows() {
        return this.rows;
    }

    /**
     * Set the value of the matrix rows.
     *
     * @param rows the number of rows of the matrix, as int.
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given value is not positive.
     */
    protected IMatrixArrayList<T> setRows(int rows) throws NonPositiveValueException {
        if (rows < 1) throw new NonPositiveValueException();

        this.rows = rows;

        return this;
    }

    /**
     * @return Number of columns of the matrix, as int.
     */
    @Override
    public int getColumns() {
        return this.columns;
    }

    /**
     * Set the value of the matrix columns.
     *
     * @param columns the number of columns of the matrix, as int.
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given value is not positive.
     */
    protected IMatrixArrayList<T> setColumns(int columns) throws NonPositiveValueException {
        if (columns < 1) throw new NonPositiveValueException();

        this.columns = columns;

        return this;
    }

    /**
     * Get an object stored in the matrix at specified indexes.
     *
     * @param row int
     * @param column int
     * @return The T typed object stored in the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    @Override
    public T get(int row, int column) throws MatrixIndexOutOfBoundsException {
        this.checkBounds(row, column);

        return this.matrix.get(row).get(column);
    }

    /**
     * Get an object stored in the matrix at specified MatrixPosition.
     *
     * @param position IMatrixPosition<Integer>
     * @return The T typed object stored in the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    @Override
    public T get(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.get(position.getY(), position.getX());
    }

    /**
     * Set an object at specified indexes of the matrix.
     *
     * @param row int
     * @param column int
     * @param value Object to store, of type T.
     * @return The pointer of the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    @Override
    public IMatrixArrayList<T> set(int row, int column, T value) throws MatrixIndexOutOfBoundsException {
        this.checkBounds(row, column);
        this.matrix.get(row).set(column, value);

        return this;
    }

    /**
     * Set an object at specified MatrixPosition of the matrix.
     *
     * @param position IMatrixPosition<Integer>
     * @param value Object to store, of type T.
     * @return The pointer of the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    @Override
    public IMatrixArrayList<T> set(IMatrixPosition<Integer> position, T value) throws MatrixIndexOutOfBoundsException {
        return this.set(position.getY(), position.getX(), value);
    }

    /**
     * Remove an object at specified indexes of the matrix.
     *
     * @param row int
     * @param column int
     * @return The pointer of the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    @Override
    public IMatrixArrayList<T> remove(int row, int column) throws MatrixIndexOutOfBoundsException {
        this.checkBounds(row, column);
        this.matrix.get(row).remove(column);

        return this;
    }

    /**
     * Remove an object at specified MatrixPosition of the matrix.
     *
     * @param position IMatrixPosition<Integer>
     * @return The pointer of the matrix.
     * @throws MatrixIndexOutOfBoundsException When the given indexes does not exist within the bounds of the matrix.
     */
    @Override
    public IMatrixArrayList<T> remove(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.remove(position.getY(), position.getX());
    }

    /**
     * Finds the MatrixPosition of a given T typed value.
     *
     * @param value Object of type T.
     * @return The MatrixPosition of the found value.
     * @throws NotFoundException When the given object doesn't exist within the matrix.
     */
    @Override
    public IMatrixPosition<Integer> find(T value) throws NotFoundException {
        int rowSize = this.matrix.size();
        for (int i = 0; i < rowSize; ++i) {
            ArrayList<T> column = this.matrix.get(i);
            int colSize = column.size();

            for (int j = 0; j < colSize; ++j) {
                if (column.get(j) == value) {
                    IMatrixPosition<Integer> position =  IMatrixPosition.create(j, i);
                    return position;
                }
            }
        }

        throw new NotFoundException();
    }

    /**
     * Checks if the given row and columns are within bounds of the matrix.
     *
     * @param row int
     * @param column int
     * @throws MatrixIndexOutOfBoundsException When the row and column are not within the bounds of the matrix.
     */
    protected void checkBounds(int row, int column) throws MatrixIndexOutOfBoundsException {
        int rowSize = this.matrix.size();

        if (row < 0 || row >= rowSize)
            throw new MatrixIndexOutOfBoundsException("The row must be within the matrix bounds.");

        int colSize = this.matrix.get(row).size();
        if (column < 0 || column >= colSize)
            throw new MatrixIndexOutOfBoundsException("The column must be within the matrix bounds.");
    }

    // Todo: clone method.

}
