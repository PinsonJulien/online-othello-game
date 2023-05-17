package com.pinson.othello.commons.entities.grids;

import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.exceptions.NotFoundException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.IMatrixArrayList;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

import java.util.ArrayList;

public interface IGrid<T extends ITile<?, ?>> {

    int getRows();

    int getColumns();

    IMatrixArrayList<T> getTiles();

    T getTileAt(int row, int column) throws MatrixIndexOutOfBoundsException;

    IGrid<T> setTileAt(int row, int column, T tile) throws MatrixIndexOutOfBoundsException;

    IMatrixPosition<Integer> findTile(T tile) throws NotFoundException;

    ArrayList<T> getNorthNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getEastNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getEastNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getSouthNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getWestNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getWestNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getNorthEastNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthEastNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getSouthEastNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthEastNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getSouthWestNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthWestNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getSouthWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getSouthWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<T> getNorthWestNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthWestNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<T> getNorthWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    ArrayList<T> getNorthWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    ArrayList<ArrayList<T>> getAdjacentNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    ArrayList<ArrayList<T>> getAdjacentNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    ArrayList<ArrayList<T>> getAdjacentNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;

    ArrayList<ArrayList<T>> getAdjacentNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
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
