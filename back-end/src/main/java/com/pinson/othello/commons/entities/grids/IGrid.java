package com.pinson.othello.commons.entities.grids;

import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.exceptions.NotFoundException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.IMatrixArrayList;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

import java.util.List;

public interface IGrid<T extends ITile<?, ?>> {

    int getRows();

    int getColumns();

    IMatrixArrayList<T> getTiles();

    T getTileAt(int row, int column) throws MatrixIndexOutOfBoundsException;

    IGrid<T> setTileAt(int row, int column, T tile) throws MatrixIndexOutOfBoundsException;

    IMatrixPosition<Integer> findTile(T tile) throws NotFoundException;

    List<T> getNorthNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getNorthNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    List<T> getNorthNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    List<T> getNorthNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getNorthNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    List<T> getNorthNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    List<T> getEastNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    List<T> getEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    List<T> getEastNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    List<T> getEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    List<T> getSouthNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getSouthNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    List<T> getSouthNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    List<T> getSouthNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getSouthNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    List<T> getSouthNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    List<T> getWestNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    List<T> getWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    List<T> getWestNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    List<T> getWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    List<T> getNorthEastNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getNorthEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    List<T> getNorthEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    List<T> getNorthEastNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getNorthEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    List<T> getNorthEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    List<T> getSouthEastNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getSouthEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    List<T> getSouthEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    List<T> getSouthEastNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getSouthEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    List<T> getSouthEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    List<T> getSouthWestNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getSouthWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    List<T> getSouthWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    List<T> getSouthWestNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getSouthWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    List<T> getSouthWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    List<T> getNorthWestNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getNorthWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    List<T> getNorthWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;
    List<T> getNorthWestNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<T> getNorthWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    List<T> getNorthWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;

    List<List<T>> getAdjacentNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<List<T>> getAdjacentNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException;
    List<List<T>> getAdjacentNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException;

    List<List<T>> getAdjacentNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException;
    List<List<T>> getAdjacentNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException;
    List<List<T>> getAdjacentNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException;
}
