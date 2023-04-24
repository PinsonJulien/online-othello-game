package com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists;

import com.pinson.gameoflife.commons.exceptions.NotFoundException;
import com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.gameoflife.commons.helpers.positions.MatrixPositions.IMatrixPosition;
import com.pinson.gameoflife.commons.helpers.positions.MatrixPositions.MatrixPosition;

import java.util.ArrayList;

public class MatrixArrayList<T> extends ArrayList<ArrayList<T>> implements IMatrixArrayList<T> {
    private int rows;
    private int columns;

    public MatrixArrayList() {
        super();
    }

    public MatrixArrayList(int rows, int columns) {
        super(rows);
        for (int i = 0; i < rows; i++) {
            this.add(new ArrayList<T>(columns));
        }

        this.setRows(rows);
        this.setColumns(columns);
    }

    @Override
    public int getRows() {
        return this.rows;
    }

    protected IMatrixArrayList<T> setRows(int rows) {
        this.rows = rows;
        return this;
    }

    @Override
    public int getColumns() {
        return this.columns;
    }

    protected IMatrixArrayList<T> setColumns(int columns) {
        this.columns = columns;
        return this;
    }

    @Override
    public T get(int row, int column) throws MatrixIndexOutOfBoundsException {
        this.checkBounds(row, column);

        return this.get(row).get(column);
    }

    @Override
    public T get(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.get(position.getY(), position.getX());
    }

    @Override
    public IMatrixArrayList<T> set(int row, int column, T value) throws MatrixIndexOutOfBoundsException {
        this.checkBounds(row, column);
        this.get(row).set(column, value);

        return this;
    }

    @Override
    public IMatrixArrayList<T> set(IMatrixPosition<Integer> position, T value) throws MatrixIndexOutOfBoundsException {
        return this.set(position.getY(), position.getX(), value);
    }

    @Override
    public IMatrixArrayList<T> remove(int row, int column) throws MatrixIndexOutOfBoundsException {
        this.checkBounds(row, column);
        this.get(row).remove(column);

        return this;
    }

    @Override
    public IMatrixArrayList<T> remove(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.remove(position.getY(), position.getX());
    }

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

    protected void checkBounds(int row, int column) throws MatrixIndexOutOfBoundsException {
        int rowSize = this.size();

        if (row < 0 || row >= rowSize)
            throw new MatrixIndexOutOfBoundsException("The row must be within the matrix bounds.");

        int colSize = this.get(row).size();
        if (column < 0 || column >= colSize)
            throw new MatrixIndexOutOfBoundsException("The column must be within the matrix bounds.");
    }
}
