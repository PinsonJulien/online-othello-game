package com.pinson.othello.commons.entities.grids;

import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.commons.exceptions.NotFoundException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.IMatrixArrayList;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;

import java.util.ArrayList;

public abstract class Grid<T extends ITile<?, ?>> implements IGrid<T> {

    private final IMatrixArrayList<T> tiles;

    public Grid(int rows, int columns) throws NonPositiveValueException {
        this.tiles = IMatrixArrayList.create(rows, columns);
    }

    @Override
    public int getRows() {
        return this.tiles.getRows();
    }

    @Override
    public int getColumns() {
        return this.tiles.getColumns();
    }

    @Override
    public IMatrixArrayList<T> getTiles() {
        return this.tiles;
    }

    @Override
    public T getTileAt(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.tiles.get(row, column);
    }

    @Override
    public IGrid<T> setTileAt(int row, int column, T tile) throws MatrixIndexOutOfBoundsException {
        this.tiles.set(row, column, tile);

        return this;
    }

    @Override
    public IMatrixPosition<Integer> findTile(T tile) throws NotFoundException {
        return this.tiles.find(tile);
    }

    @Override
    public ArrayList<T> getNorthNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getNorthNeighbours(this.findTile(tile));
    }

    @Override
    public ArrayList<T> getNorthNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.getNorthNeighbours(position.getY(), position.getX());
    }

    @Override
    public ArrayList<T> getNorthNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getNorthNeighbours(row, column, row);
    }

    @Override
    public ArrayList<T> getNorthNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getNorthNeighbours(this.findTile(tile), distance);
    }

    @Override
    public ArrayList<T> getNorthNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException {
        return this.getNorthNeighbours(position.getY(), position.getX(), distance);
    }

    @Override
    public ArrayList<T> getNorthNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException {
        ArrayList<T> neighbours = new ArrayList<>();

        if (row <= 0 || distance <= 0)
            return neighbours;

        // get all the neighbours in the north direction of the tile, within the limit and the distance
        int len = row - distance;
        for (int i = row - 1; i >= 0 && i >= len; i--) {
            neighbours.add(this.getTileAt(i, column));
        }

        return neighbours;
    }

    @Override
    public ArrayList<T> getEastNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getEastNeighbours(this.findTile(tile));
    }

    @Override
    public ArrayList<T> getEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.getEastNeighbours(position.getY(), position.getX());
    }

    @Override
    public ArrayList<T> getEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getEastNeighbours(row, column, this.getColumns() - column - 1);
    }

    @Override
    public ArrayList<T> getEastNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getEastNeighbours(this.findTile(tile), distance);
    }

    @Override
    public ArrayList<T> getEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException {
        return this.getEastNeighbours(position.getY(), position.getX(), distance);
    }

    @Override
    public ArrayList<T> getEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException {
        ArrayList<T> neighbours = new ArrayList<>();

        if (distance <= 0) {
            return neighbours;
        }

        int len = column + distance;
        int columns = this.getColumns();

        if (len >= columns) {
            len = columns - 1;
        }

        for (int i = column + 1; i <= len; i++) {
            neighbours.add(this.tiles.get(row, i));
        }

        return neighbours;
    }

    @Override
    public ArrayList<T> getSouthNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getSouthNeighbours(this.findTile(tile));
    }

    @Override
    public ArrayList<T> getSouthNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.getSouthNeighbours(position.getY(), position.getX());
    }

    @Override
    public ArrayList<T> getSouthNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getSouthNeighbours(row, column, this.getRows() - row - 1);
    }

    @Override
    public ArrayList<T> getSouthNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getSouthNeighbours(this.findTile(tile), distance);
    }

    @Override
    public ArrayList<T> getSouthNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException {
        return this.getSouthNeighbours(position.getY(), position.getX(), distance);
    }

    @Override
    public ArrayList<T> getSouthNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException {
        ArrayList<T> neighbours = new ArrayList<>();

        if (distance <= 0)
            return neighbours;

        int len = row + distance;
        int rows = this.getRows();

        if (len >= rows) {
            len = rows - 1;
        }

        for (int i = row + 1; i <= len; i++) {
            neighbours.add(this.tiles.get(i, column));
        }

        return neighbours;
    }

    @Override
    public ArrayList<T> getWestNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getWestNeighbours(this.findTile(tile));
    }

    @Override
    public ArrayList<T> getWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.getWestNeighbours(position.getY(), position.getX());
    }

    @Override
    public ArrayList<T> getWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getWestNeighbours(row, column, column);
    }

    @Override
    public ArrayList<T> getWestNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getWestNeighbours(this.findTile(tile), distance);
    }

    @Override
    public ArrayList<T> getWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException {
        return this.getWestNeighbours(position.getY(), position.getX(), distance);
    }

    @Override
    public ArrayList<T> getWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException {
        ArrayList<T> neighbours = new ArrayList<>();

        if (column <= 0 || distance <= 0)
            return neighbours;

        int len = column - distance;

        for (int i = column - 1; i >= 0 && i >= len; i--) {
            neighbours.add(this.tiles.get(row, i));
        }

        return neighbours;
    }

    @Override
    public ArrayList<T> getNorthEastNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getNorthEastNeighbours(this.findTile(tile));
    }

    @Override
    public ArrayList<T> getNorthEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.getNorthEastNeighbours(position.getY(), position.getX());
    }

    @Override
    public ArrayList<T> getNorthEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getNorthEastNeighbours(row, column, row);
    }

    @Override
    public ArrayList<T> getNorthEastNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getNorthEastNeighbours(this.findTile(tile), distance);
    }

    @Override
    public ArrayList<T> getNorthEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException {
        return this.getNorthEastNeighbours(position.getY(), position.getX(), distance);
    }

    @Override
    public ArrayList<T> getNorthEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException {
        ArrayList<T> neighbours = new ArrayList<>();
        int rows = this.getRows();
        int columns = this.getColumns();

        if (row <= 0 || column >= columns - 1 || distance <= 0)
            return neighbours;

        int len = Math.min(row, columns - column - 1);

        if (len >= distance)
            len = distance;
        else if (len >= rows || len >= columns)
            len = Math.min(rows - 1 - row, columns - 1 - column);

        for (int i = 1; i <= len; i++) {
            neighbours.add(this.tiles.get(row - i, column + i));
        }

        return neighbours;
    }

    @Override
    public ArrayList<T> getSouthEastNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getSouthEastNeighbours(this.findTile(tile));
    }

    @Override
    public ArrayList<T> getSouthEastNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.getSouthEastNeighbours(position.getY(), position.getX());
    }

    @Override
    public ArrayList<T> getSouthEastNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getSouthEastNeighbours(row, column, this.getRows() - row - 1);
    }

    @Override
    public ArrayList<T> getSouthEastNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getSouthEastNeighbours(this.findTile(tile), distance);
    }

    @Override
    public ArrayList<T> getSouthEastNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException {
        return this.getSouthEastNeighbours(position.getY(), position.getX(), distance);
    }

    @Override
    public ArrayList<T> getSouthEastNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException {
        ArrayList<T> neighbours = new ArrayList<>();
        int rows = this.getRows();
        int columns = this.getColumns();

        if (row >= rows - 1 || column >= columns - 1 || distance <= 0)
            return neighbours;

        int len = Math.min(rows - row - 1, columns - column - 1);

        if (len >= distance)
            len = distance;
        else if (len >= rows || len >= columns)
            len = Math.min(row, column);

        for (int i = 1; i <= len; i++) {
            neighbours.add(this.tiles.get(row + i, column + i));
        }

        return neighbours;
    }

    @Override
    public ArrayList<T> getSouthWestNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getSouthWestNeighbours(this.findTile(tile));
    }

    @Override
    public ArrayList<T> getSouthWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.getSouthWestNeighbours(position.getY(), position.getX());
    }

    @Override
    public ArrayList<T> getSouthWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getSouthWestNeighbours(row, column, this.getRows() - row - 1);
    }

    @Override
    public ArrayList<T> getSouthWestNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getSouthWestNeighbours(this.findTile(tile), distance);
    }

    @Override
    public ArrayList<T> getSouthWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException {
        return this.getSouthWestNeighbours(position.getY(), position.getX(), distance);
    }

    @Override
    public ArrayList<T> getSouthWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException {
        ArrayList<T> neighbours = new ArrayList<>();
        int rows = this.getRows();
        int columns = this.getColumns();

        if (row >= rows - 1 || column <= 0 || distance <= 0)
            return neighbours;

        int len = Math.min(rows - row - 1, column);

        if (len >= distance)
            len = distance;
        else if (len >= rows || len >= columns)
            len = Math.min(row, columns - column - 1);

        for (int i = 1; i <= len; i++) {
            neighbours.add(this.tiles.get(row + i, column - i));
        }

        return neighbours;
    }

    @Override
    public ArrayList<T> getNorthWestNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getNorthWestNeighbours(this.findTile(tile));
    }

    @Override
    public ArrayList<T> getNorthWestNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.getNorthWestNeighbours(position.getY(), position.getX());
    }

    @Override
    public ArrayList<T> getNorthWestNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getNorthWestNeighbours(row, column, row);
    }

    @Override
    public ArrayList<T> getNorthWestNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getNorthWestNeighbours(this.findTile(tile), distance);
    }

    @Override
    public ArrayList<T> getNorthWestNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException {
        return this.getNorthWestNeighbours(position.getY(), position.getX(), distance);
    }

    @Override
    public ArrayList<T> getNorthWestNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException {
        ArrayList<T> neighbours = new ArrayList<>();
        int rows = this.getRows();
        int columns = this.getColumns();

        if (row <= 0 || column <= 0 || distance <= 0)
            return neighbours;

        int len = Math.min(row, column);

        if (len >= distance)
            len = distance;
        else if (len >= rows || len >= columns)
            len = Math.min(rows - row - 1, columns - column - 1);

        for (int i = 1; i <= len; i++) {
            neighbours.add(this.tiles.get(row - i, column - i));
        }

        return neighbours;
    }

    @Override
    public ArrayList<ArrayList<T>> getAdjacentNeighbours(T tile) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getAdjacentNeighbours(this.findTile(tile));
    }

    @Override
    public ArrayList<ArrayList<T>> getAdjacentNeighbours(IMatrixPosition<Integer> position) throws MatrixIndexOutOfBoundsException {
        return this.getAdjacentNeighbours(position.getY(), position.getX());
    }

    @Override
    public ArrayList<ArrayList<T>> getAdjacentNeighbours(int row, int column) throws MatrixIndexOutOfBoundsException {
        return this.getAdjacentNeighbours(row, column, Math.min(row, column));
    }

    @Override
    public ArrayList<ArrayList<T>> getAdjacentNeighbours(T tile, int distance) throws MatrixIndexOutOfBoundsException, NotFoundException {
        return this.getAdjacentNeighbours(this.findTile(tile), distance);
    }

    @Override
    public ArrayList<ArrayList<T>> getAdjacentNeighbours(IMatrixPosition<Integer> position, int distance) throws MatrixIndexOutOfBoundsException {
        return this.getAdjacentNeighbours(position.getY(), position.getX(), distance);
    }

    @Override
    public ArrayList<ArrayList<T>> getAdjacentNeighbours(int row, int column, int distance) throws MatrixIndexOutOfBoundsException {
        ArrayList<ArrayList<T>> neighbours = new ArrayList<>();

        neighbours.add(this.getNorthNeighbours(row, column, distance));
        neighbours.add(this.getNorthEastNeighbours(row, column, distance));
        neighbours.add(this.getEastNeighbours(row, column, distance));
        neighbours.add(this.getSouthEastNeighbours(row, column, distance));
        neighbours.add(this.getSouthNeighbours(row, column, distance));
        neighbours.add(this.getSouthWestNeighbours(row, column, distance));
        neighbours.add(this.getWestNeighbours(row, column, distance));
        neighbours.add(this.getNorthWestNeighbours(row, column, distance));

        return neighbours;
    }

}
