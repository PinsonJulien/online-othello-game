package com.pinson.othello.commons.helpers.collections.matrixArrayLists;

import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.commons.exceptions.NotFoundException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ArrayList of ArrayList.
 *
 * @param <T> Any Object
 */
public class MatrixArrayList<T> implements IMatrixArrayList<T>, Iterable<List<T>> {
    private final List<List<T>> matrix;

    /**
     * Instantiate a MatrixArrayList without a specific size.
     */
    public MatrixArrayList() {
        this.matrix = new ArrayList<>();
    }

    /**
     * Instantiate a MatrixArrayList with a default size, values are null by default.
     *
     * @param rows int
     * @param columns int
     * @throws NonPositiveValueException When the given rows or columns are not a positive number.
     */
    public MatrixArrayList(int rows, int columns) throws NonPositiveValueException {
        if (rows <= 0)
            throw new NonPositiveValueException("Rows must be positive");
        if (columns <= 0)
            throw new NonPositiveValueException("Columns must be positive");

        this.matrix = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<T> column = new ArrayList<>();

            for (int j = 0; j < columns; j++) {
                column.add(null);
            }

            this.matrix.add(column);
        }
    }

    /**
     * Instantiate a MatrixArrayList with a default size and a list of elements to insert.
     * If the list of elements is smaller than the matrix, the remaining elements will be null.
     *
     * @param rows int
     * @param columns int
     * @param elements List<T> of elements to insert.
     * @throws NonPositiveValueException When the given rows or columns are not a positive number.
     */
    public MatrixArrayList(int rows, int columns, List<T> elements) throws NonPositiveValueException {
        if (rows <= 0)
            throw new NonPositiveValueException("Rows must be positive");
        if (columns <= 0)
            throw new NonPositiveValueException("Columns must be positive");

        this.matrix = new ArrayList<>();

        for (int i = 0; i < rows; i++) {
            List<T> column = new ArrayList<>();

            for (int j = 0; j < columns; j++) {
                try {
                    column.add(elements.get(i * columns + j));
                } catch (IndexOutOfBoundsException e) {
                    column.add(null);
                }
            }

            this.matrix.add(column);
        }
    }

    /**
     * @return Number of rows of the matrix, as int.
     */
    @Override
    public int getRows() {
        return this.matrix.size();
    }

    /**
     * @return Number of columns of the matrix, as int.
     */
    @Override
    public int getColumns() {
        if (this.matrix.size() == 0)
            return 0;

        return this.matrix.get(0).size();
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
            List<T> column = this.matrix.get(i);
            int colSize = column.size();

            for (int j = 0; j < colSize; ++j) {
                if (column.get(j) == value) {
                    return IMatrixPosition.create(j, i);
                }
            }
        }

        throw new NotFoundException();
    }

    /**
     * Resize the matrix to the given size.
     * If the given size is smaller than the current size, the matrix will be truncated.
     * If the given size is bigger than the current size, the matrix will be filled with null values.
     *
     * @param rows int
     * @param columns int
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given rows or columns sizes are not positive.
     */
    @Override
    public IMatrixArrayList<T> resize(int rows, int columns) throws NonPositiveValueException {
        if (rows < 0)
            throw new NonPositiveValueException("Rows must be positive");
        if (columns < 0)
            throw new NonPositiveValueException("Columns must be positive");

        if (columns > this.getColumns())
            this.insertColumns(columns - this.getColumns());
        else if (columns < this.getColumns())
            this.removeColumns(this.getColumns() - columns);


        if (rows > this.getRows())
            this.insertRows(rows - this.getRows());
        else if (rows < this.getRows())
            this.removeRows(this.getRows() - rows);

        return this;
    }

    /**
     * Insert a given amount of rows at the end of the matrix.
     * The inserted rows will be filled with columns of null values.
     *
     * @param amount int
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given amount is not positive.
     */
    @Override
    public IMatrixArrayList<T> insertRows(int amount) throws NonPositiveValueException {
        try {
            this.insertRows(amount, this.getRows());
        } catch (MatrixIndexOutOfBoundsException e) {
            // this should never happen
        }

        return this;
    }

    /**
     * Insert a given amount of columns at the end of the matrix
     * The inserted rows will be filled with columns of null values.
     *
     * @param amount int
     * @param index int
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given amount is not positive.
     * @throws MatrixIndexOutOfBoundsException When the given index is out of bounds.
     */
    @Override
    public IMatrixArrayList<T> insertRows(int amount, int index) throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        if (amount < 1) throw new NonPositiveValueException();

        int colSize = this.getColumns();
        int rowSize = this.getRows();

        // When the matrix is empty, we can't check the bounds
        // When the index is lower than the amount of rows, we can't check the bounds (insert at the end)
        if (rowSize > 0 && index < rowSize)
            this.checkRowBounds(index);

        for (int i = 0; i < amount; ++i) {
            List<T> row = new ArrayList<>();

            for (int j = 0; j < colSize; ++j) {
                row.add(null);
            }

            if (index < rowSize)
                this.matrix.add(index, row);
            else
                this.matrix.add(row);
        }

        return this;
    }

    /**
     * Insert a given amount of columns at the end of each rows of the matrix
     * The inserted columns will be filled with null values.
     *
     * @param amount int
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given amount is not positive.
     */
    @Override
    public IMatrixArrayList<T> insertColumns(int amount) throws NonPositiveValueException {
        try {
            this.insertColumns(amount, this.getColumns());
        } catch (MatrixIndexOutOfBoundsException e) {
            // this should never happen
        }

        return this;
    }

    /**
     * Insert a given amount of columns at the end of each rows of the matrix
     * The inserted columns will be filled with null values.
     *
     * @param amount int
     * @param index int
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given amount is not positive.
     * @throws MatrixIndexOutOfBoundsException When the given index is out of bounds.
     */
    @Override
    public IMatrixArrayList<T> insertColumns(int amount, int index) throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        if (amount < 1) throw new NonPositiveValueException();

        int colSize = this.getColumns();
        int rowSize = this.getRows();

        // When the matrix is empty, we can't check the bounds
        // When the index is lower than the amount of columns, we can't check the bounds (insert at the end)
        if (colSize > 0 && index < colSize)
            this.checkColumnBounds(index);

        for (int i = 0; i < rowSize; ++i) {
            List<T> row = this.matrix.get(i);

            for (int j = 0; j < amount; ++j) {
                if (index < colSize)
                    row.add(index, null);
                else
                    row.add(null);
            }
        }

        return this;
    }

    /**
     * Remove a given amount of rows at the end of the matrix.
     *
     * @param amount int
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given amount is not positive.
     */
    @Override
    public IMatrixArrayList<T> removeRows(int amount) throws NonPositiveValueException {
        try {
            this.removeRows(amount, this.getRows());
        } catch (MatrixIndexOutOfBoundsException e) {
            // this should never happen
        }

        return this;
    }

    /**
     * Remove a given amount of rows at a specified index of the matrix.
     *
     * @param amount int
     * @param index int
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given amount is not positive.
     * @throws MatrixIndexOutOfBoundsException When the given index is out of bounds.
     */
    @Override
    public IMatrixArrayList<T> removeRows(int amount, int index) throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        int rowSize = this.getRows();

        if (amount < 1) throw new NonPositiveValueException();

        // When the matrix is empty, we can't check the bounds
        // When the index is lower than the amount of rows, we can't check the bounds (remove at the end)
        if (rowSize > 0 && index < rowSize)
            this.checkRowBounds(index);

        for (int i = 0; i < amount && rowSize != 0; ++i) {
            if (index < rowSize)
                this.matrix.remove(index - i);
            else
                this.matrix.remove(rowSize - 1);
            rowSize = this.getRows();
        }

        return this;
    }

    /**
     * Remove a given amount of columns at the end of each rows of the matrix.
     *
     * @param amount int
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given amount is not positive.
     */
    @Override
    public IMatrixArrayList<T> removeColumns(int amount) throws NonPositiveValueException {
        try {
            this.removeColumns(amount, this.getColumns());
        } catch (MatrixIndexOutOfBoundsException e) {
            // this should never happen
        }

        return this;
    }

    /**
     * Remove a given amount of columns at a specified index of each rows of the matrix.
     *
     * @param amount int
     * @param index int
     * @return The pointer of the matrix.
     * @throws NonPositiveValueException When the given amount is not positive.
     * @throws MatrixIndexOutOfBoundsException When the given index is out of bounds.
     */
    @Override
    public IMatrixArrayList<T> removeColumns(int amount, int index) throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        int rowSize = this.getRows();
        int colSize = this.getColumns();

        if (amount < 1) throw new NonPositiveValueException();

        // When the matrix is empty, we can't check the bounds
        // When the index is lower than the amount of columns, we can't check the bounds (remove at the end)
        if (colSize > 0 && index < colSize)
            this.checkColumnBounds(index);

        for (int i = 0; i < rowSize; ++i) {
            List<T> row = this.matrix.get(i);
            int size = row.size();

            for (int j = 0; j < amount && size != 0; ++j) {
                if (index < size)
                    row.remove(index - j);
                else
                    row.remove(size - 1);

                size = row.size();
            }
        }

        return this;
    }

    /**
     * Returns the MatrixArrayList as a List.
     *
     * @return List<T> The MatrixArrayList as a List.
     */
    @Override
    public List<T> toList() {
        return this.matrix.stream().flatMap(List::stream).collect(Collectors.toList());
    }

    /**
     * Checks if the given row and columns are within bounds of the matrix.
     *
     * @param row int
     * @param column int
     * @throws MatrixIndexOutOfBoundsException When the row and column are not within the bounds of the matrix.
     */
    protected void checkBounds(int row, int column) throws MatrixIndexOutOfBoundsException {
        this.checkRowBounds(row);
        this.checkColumnBounds(column);
    }

    /**
     * Checks if the given row is within bounds of the matrix.
     *
     * @param row int
     * @throws MatrixIndexOutOfBoundsException When the row is not within the bounds of the matrix.
     */
    protected void checkRowBounds(int row) throws MatrixIndexOutOfBoundsException {
        int rowSize = this.matrix.size();

        if (row < 0 || row >= rowSize)
            throw new MatrixIndexOutOfBoundsException("The row must be within the matrix bounds.");
    }

    /**
     * Checks if the given column is within bounds of the matrix.
     *
     * @param column int
     * @throws MatrixIndexOutOfBoundsException When the column is not within the bounds of the matrix.
     */
    protected void checkColumnBounds(int column) throws MatrixIndexOutOfBoundsException {
        int colSize = this.matrix.get(0).size();

        if (column < 0 || column >= colSize)
            throw new MatrixIndexOutOfBoundsException("The column must be within the matrix bounds.");
    }

    /**
     * Allows the MatrixArrayList to be iterated over.
     *
     * @return Iterator<ArrayList<T>> The iterator.
     */
    public Iterator<List<T>> iterator() {
        return this.matrix.iterator();
    }

    // Todo: clone method.
}
