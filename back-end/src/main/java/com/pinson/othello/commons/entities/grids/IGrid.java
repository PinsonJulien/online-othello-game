package com.pinson.othello.commons.entities.grids;

import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.exceptions.NotFoundException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.IMatrixArrayList;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

import java.util.ArrayList;

public interface IGrid<T extends ITile> {

    int getRows();

    int getColumns();

    IMatrixArrayList<T> getCells();

    T getCellAt(int row, int column) throws MatrixIndexOutOfBoundsException;

    IGrid<T> setCellAt(int row, int column, T cell) throws MatrixIndexOutOfBoundsException;

    IMatrixPosition<Integer> findCell(T cell) throws NotFoundException;

    ArrayList<T> getNorthNeighbours(T cell) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthNeighbours(T cell, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getEastNeighbours(T cell) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getEastNeighbours(T cell, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getSouthNeighbours(T cell) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthNeighbours(T cell, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getWestNeighbours(T cell) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getWestNeighbours(T cell, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getNorthEastNeighbours(T cell) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthEastNeighbours(T cell, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getSouthEastNeighbours(T cell) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthEastNeighbours(T cell, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getSouthWestNeighbours(T cell) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthWestNeighbours(T cell, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getNorthWestNeighbours(T cell) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthWestNeighbours(T cell, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<ArrayList<T>> getAdjacentNeighbours(T cell) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<ArrayList<T>> getAdjacentNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<ArrayList<T>> getAdjacentNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;

    ArrayList<ArrayList<T>> getAdjacentNeighbours(T cell, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<ArrayList<T>> getAdjacentNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<ArrayList<T>> getAdjacentNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    // todo: getCircularNeighbours
    // todo: getSquaredNeighbours using getRectangularNeighbours
    // todo: getPerpendicularNeighbours (or better name for typical cross) (+)
    // todo: getDiagonalNeighbours (X)
    // todo: getRectangularNeighbours
    // todo: getTriangularNeighbours
    // todo: getHexagonalNeighbours

}
