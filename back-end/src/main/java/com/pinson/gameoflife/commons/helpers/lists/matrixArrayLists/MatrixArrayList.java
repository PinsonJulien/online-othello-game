package com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists;

import com.pinson.gameoflife.commons.exceptions.NonPositiveValueException;
import com.pinson.gameoflife.commons.exceptions.NotFoundException;
import com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.gameoflife.commons.helpers.positions.MatrixPositions.IMatrixPosition;
import com.pinson.gameoflife.commons.helpers.positions.MatrixPositions.MatrixPosition;

import java.util.ArrayList;

/**
 * ArrayList of ArrayList.
 *
 * @param <T> Any Object
 */
public class MatrixArrayList<T> extends ArrayList<ArrayList<T>> implements IMatrixArrayList<T> {
    private int rows;
    private int columns;

    public MatrixArrayList() {
        super();
    }

    /**
     * Instantiate a MatrixArrayList with a default size.
     *
     * @param rows int
     * @param columns int
     * @throws NonPositiveValueException When the given rows or columns are not a positive number.
     */
    public MatrixArrayList(int rows, int columns) throws NonPositiveValueException {
        super(rows);

        for (int i = 0; i < rows; i++) {
            this.add(new ArrayList<T>(columns));
        }

        this.setRows(rows);
        this.setColumns(columns);
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

        return this.get(row).get(column);
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
        this.get(row).set(column, value);

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
        this.get(row).remove(column);

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
        int rowSize = this.size();
        for (int i = 0; i < rowSize; ++i) {
            ArrayList<T> column = this.get(i);
            int colSize = column.size();

            for (int j = 0; j < colSize; ++j) {
                if (column.get(j) == value) {
                    return new MatrixPosition<Integer>(j, i);
                }
            }
        }

        throw new NotFoundException();
    }

    /**
     * Remove an Object from the matrix.
     *
     * @param value element to be removed from this list, if present
     * @return True on success, false on failure.
     */
    @Override
    public boolean remove(Object value) {
        try {
            IMatrixPosition<Integer> position = this.find((T) value);

            this.remove(position);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Checks if the given row and columns are within bounds of the matrix.
     *
     * @param row int
     * @param column int
     * @throws MatrixIndexOutOfBoundsException When the row and column are not within the bounds of the matrix.
     */
    protected void checkBounds(int row, int column) throws MatrixIndexOutOfBoundsException {
        int rowSize = this.size();

        if (row < 0 || row >= rowSize)
            throw new MatrixIndexOutOfBoundsException("The row must be within the matrix bounds.");

        int colSize = this.get(row).size();
        if (column < 0 || column >= colSize)
            throw new MatrixIndexOutOfBoundsException("The column must be within the matrix bounds.");
    }

    // Todo: clone method.

}
