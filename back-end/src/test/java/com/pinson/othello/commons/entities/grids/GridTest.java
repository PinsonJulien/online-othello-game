package com.pinson.othello.commons.entities.grids;

import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void getRows() {
    }

    @Test
    void getColumns() {
    }

    @Test
    void getCells() {
    }

    @Test
    void getCellAt() {
    }

    @Test
    void setCellAt() {
    }

    @Test
    void findCell() {
    }

    @Test
    void getNorthNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 3);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getNorthNeighbours(0, 0).size());
        assertEquals(0, grid.getNorthNeighbours(0, 1).size());
        assertEquals(0, grid.getNorthNeighbours(0, 2).size());

        assertEquals(1, grid.getNorthNeighbours(1, 0).size());
        assertEquals(1, grid.getNorthNeighbours(1, 1).size());
        assertEquals(1, grid.getNorthNeighbours(1, 2).size());

        assertEquals(2, grid.getNorthNeighbours(2, 0).size());
        assertEquals(2, grid.getNorthNeighbours(2, 1).size());
        assertEquals(2, grid.getNorthNeighbours(2, 2).size());

        assertEquals(3, grid.getNorthNeighbours(3, 0).size());
        assertEquals(3, grid.getNorthNeighbours(3, 1).size());
        assertEquals(3, grid.getNorthNeighbours(3, 2).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getNorthNeighbours(3, 0);
        assertEquals(grid.getCellAt(2, 0), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 0), neighbours.get(1));
        assertEquals(grid.getCellAt(0, 0), neighbours.get(2));
    }

    @Test
    void getNorthNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 3);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getNorthNeighbours(0, 0, 2).size());
        assertEquals(0, grid.getNorthNeighbours(0, 1, 2).size());
        assertEquals(0, grid.getNorthNeighbours(0, 2, 2).size());

        assertEquals(1, grid.getNorthNeighbours(1, 0, 2).size());
        assertEquals(1, grid.getNorthNeighbours(1, 1, 2).size());
        assertEquals(1, grid.getNorthNeighbours(1, 2, 2).size());

        assertEquals(2, grid.getNorthNeighbours(2, 0, 2).size());
        assertEquals(2, grid.getNorthNeighbours(2, 1, 2).size());
        assertEquals(2, grid.getNorthNeighbours(2, 2, 2).size());

        assertEquals(2, grid.getNorthNeighbours(3, 0, 2).size());
        assertEquals(2, grid.getNorthNeighbours(3, 1, 2).size());
        assertEquals(2, grid.getNorthNeighbours(3, 2, 2).size());

        ArrayList<ITile> neighbours = grid.getNorthNeighbours(3, 0, 2);
        assertEquals(grid.getCellAt(2, 0), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 0), neighbours.get(1));

        neighbours = grid.getNorthNeighbours(3, 1, 10);
        assertEquals(grid.getCellAt(2, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 1), neighbours.get(1));
        assertEquals(grid.getCellAt(0, 1), neighbours.get(2));
    }

    @Test
    void getEastNeighbour_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(3, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(3, grid.getEastNeighbours(0, 0).size());
        assertEquals(2, grid.getEastNeighbours(0, 1).size());
        assertEquals(1, grid.getEastNeighbours(0, 2).size());
        assertEquals(0, grid.getEastNeighbours(0, 3).size());

        assertEquals(3, grid.getEastNeighbours(1, 0).size());
        assertEquals(2, grid.getEastNeighbours(1, 1).size());
        assertEquals(1, grid.getEastNeighbours(1, 2).size());
        assertEquals(0, grid.getEastNeighbours(1, 3).size());

        assertEquals(3, grid.getEastNeighbours(2, 0).size());
        assertEquals(2, grid.getEastNeighbours(2, 1).size());
        assertEquals(1, grid.getEastNeighbours(2, 2).size());
        assertEquals(0, grid.getEastNeighbours(2, 3).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getEastNeighbours(1, 1);
        assertEquals(grid.getCellAt(1, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 3), neighbours.get(1));
    }

    @Test
    void getEastNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(3, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(2, grid.getEastNeighbours(0, 0, 2).size());
        assertEquals(2, grid.getEastNeighbours(0, 1, 2).size());
        assertEquals(1, grid.getEastNeighbours(0, 2, 2).size());
        assertEquals(0, grid.getEastNeighbours(0, 3, 2).size());

        assertEquals(2, grid.getEastNeighbours(1, 0, 2).size());
        assertEquals(2, grid.getEastNeighbours(1, 1, 2).size());
        assertEquals(1, grid.getEastNeighbours(1, 2, 2).size());
        assertEquals(0, grid.getEastNeighbours(1, 3, 2).size());

        assertEquals(2, grid.getEastNeighbours(2, 0, 2).size());
        assertEquals(2, grid.getEastNeighbours(2, 1, 2).size());
        assertEquals(1, grid.getEastNeighbours(2, 2, 2).size());
        assertEquals(0, grid.getEastNeighbours(2, 3, 2).size());

        ArrayList<ITile> neighbours = grid.getEastNeighbours(1, 0, 2);
        assertEquals(grid.getCellAt(1, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 2), neighbours.get(1));

        neighbours = grid.getEastNeighbours(1, 0, 10);
        assertEquals(grid.getCellAt(1, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 2), neighbours.get(1));
        assertEquals(grid.getCellAt(1, 3), neighbours.get(2));
    }

    @Test
    void getSouthNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 3);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(3, grid.getSouthNeighbours(0, 0).size());
        assertEquals(3, grid.getSouthNeighbours(0, 1).size());
        assertEquals(3, grid.getSouthNeighbours(0, 2).size());

        assertEquals(2, grid.getSouthNeighbours(1, 0).size());
        assertEquals(2, grid.getSouthNeighbours(1, 1).size());
        assertEquals(2, grid.getSouthNeighbours(1, 2).size());

        assertEquals(1, grid.getSouthNeighbours(2, 0).size());
        assertEquals(1, grid.getSouthNeighbours(2, 1).size());
        assertEquals(1, grid.getSouthNeighbours(2, 2).size());

        assertEquals(0, grid.getSouthNeighbours(3, 0).size());
        assertEquals(0, grid.getSouthNeighbours(3, 1).size());
        assertEquals(0, grid.getSouthNeighbours(3, 2).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getSouthNeighbours(1, 1);
        assertEquals(grid.getCellAt(2, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(3, 1), neighbours.get(1));
    }

    @Test
    void getSouthNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 3);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(2, grid.getSouthNeighbours(0, 0, 2).size());
        assertEquals(2, grid.getSouthNeighbours(0, 1, 2).size());
        assertEquals(2, grid.getSouthNeighbours(0, 2, 2).size());

        assertEquals(2, grid.getSouthNeighbours(1, 0, 2).size());
        assertEquals(2, grid.getSouthNeighbours(1, 1, 2).size());
        assertEquals(2, grid.getSouthNeighbours(1, 2, 2).size());

        assertEquals(1, grid.getSouthNeighbours(2, 0, 2).size());
        assertEquals(1, grid.getSouthNeighbours(2, 1, 2).size());
        assertEquals(1, grid.getSouthNeighbours(2, 2, 2).size());

        assertEquals(0, grid.getSouthNeighbours(3, 0, 2).size());
        assertEquals(0, grid.getSouthNeighbours(3, 1, 2).size());
        assertEquals(0, grid.getSouthNeighbours(3, 2, 2).size());

        ArrayList<ITile> neighbours = grid.getSouthNeighbours(0, 1, 2);
        assertEquals(grid.getCellAt(1, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(2, 1), neighbours.get(1));

        neighbours = grid.getSouthNeighbours(0, 1, 10);
        assertEquals(grid.getCellAt(1, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(2, 1), neighbours.get(1));
        assertEquals(grid.getCellAt(3, 1), neighbours.get(2));
    }

    @Test
    void getWestNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(3, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getWestNeighbours(0, 0).size());
        assertEquals(1, grid.getWestNeighbours(0, 1).size());
        assertEquals(2, grid.getWestNeighbours(0, 2).size());

        assertEquals(3, grid.getWestNeighbours(0, 3).size());

        assertEquals(0, grid.getWestNeighbours(1, 0).size());
        assertEquals(1, grid.getWestNeighbours(1, 1).size());
        assertEquals(2, grid.getWestNeighbours(1, 2).size());
        assertEquals(3, grid.getWestNeighbours(1, 3).size());

        assertEquals(0, grid.getWestNeighbours(2, 0).size());
        assertEquals(1, grid.getWestNeighbours(2, 1).size());
        assertEquals(2, grid.getWestNeighbours(2, 2).size());
        assertEquals(3, grid.getWestNeighbours(2, 3).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getWestNeighbours(1, 3);
        assertEquals(grid.getCellAt(1, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 1), neighbours.get(1));
        assertEquals(grid.getCellAt(1, 0), neighbours.get(2));
    }

    @Test
    void getWestNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(3, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getWestNeighbours(0, 0, 2).size());
        assertEquals(1, grid.getWestNeighbours(0, 1, 2).size());
        assertEquals(2, grid.getWestNeighbours(0, 2, 2).size());
        assertEquals(2, grid.getWestNeighbours(0, 3, 2).size());

        assertEquals(0, grid.getWestNeighbours(1, 0, 2).size());
        assertEquals(1, grid.getWestNeighbours(1, 1, 2).size());
        assertEquals(2, grid.getWestNeighbours(1, 2, 2).size());
        assertEquals(2, grid.getWestNeighbours(1, 3, 2).size());

        assertEquals(0, grid.getWestNeighbours(2, 0, 2).size());
        assertEquals(1, grid.getWestNeighbours(2, 1, 2).size());
        assertEquals(2, grid.getWestNeighbours(2, 2, 2).size());
        assertEquals(2, grid.getWestNeighbours(2, 3, 2).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getWestNeighbours(1, 3, 2);
        assertEquals(grid.getCellAt(1, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 1), neighbours.get(1));

        neighbours = grid.getWestNeighbours(1, 3, 10);
        assertEquals(grid.getCellAt(1, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 1), neighbours.get(1));
        assertEquals(grid.getCellAt(1, 0), neighbours.get(2));
    }

    @Test
    void getNorthEastNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getNorthEastNeighbours(0, 0).size());
        assertEquals(0, grid.getNorthEastNeighbours(0, 1).size());
        assertEquals(0, grid.getNorthEastNeighbours(0, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(0, 3).size());

        assertEquals(1, grid.getNorthEastNeighbours(1, 0).size());
        assertEquals(1, grid.getNorthEastNeighbours(1, 1).size());
        assertEquals(1, grid.getNorthEastNeighbours(1, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(1, 3).size());

        assertEquals(2, grid.getNorthEastNeighbours(2, 0).size());
        assertEquals(2, grid.getNorthEastNeighbours(2, 1).size());
        assertEquals(1, grid.getNorthEastNeighbours(2, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(2, 3).size());

        assertEquals(3, grid.getNorthEastNeighbours(3, 0).size());
        assertEquals(2, grid.getNorthEastNeighbours(3, 1).size());
        assertEquals(1, grid.getNorthEastNeighbours(3, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(3, 3).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getNorthEastNeighbours(3, 0);
        assertEquals(grid.getCellAt(2, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 2), neighbours.get(1));
        assertEquals(grid.getCellAt(0, 3), neighbours.get(2));
    }

    @Test
    void getNorthEastNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getNorthEastNeighbours(0, 0, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(0, 1, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(0, 2, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(0, 3, 2).size());

        assertEquals(1, grid.getNorthEastNeighbours(1, 0, 2).size());
        assertEquals(1, grid.getNorthEastNeighbours(1, 1, 2).size());
        assertEquals(1, grid.getNorthEastNeighbours(1, 2, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(1, 3, 2).size());

        assertEquals(2, grid.getNorthEastNeighbours(2, 0, 2).size());
        assertEquals(2, grid.getNorthEastNeighbours(2, 1, 2).size());
        assertEquals(1, grid.getNorthEastNeighbours(2, 2, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(2, 3, 2).size());

        assertEquals(2, grid.getNorthEastNeighbours(3, 0, 2).size());
        assertEquals(2, grid.getNorthEastNeighbours(3, 1, 2).size());
        assertEquals(1, grid.getNorthEastNeighbours(3, 2, 2).size());
        assertEquals(0, grid.getNorthEastNeighbours(3, 3, 2).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getNorthEastNeighbours(3, 0, 2);
        assertEquals(grid.getCellAt(2, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 2), neighbours.get(1));

        neighbours = grid.getNorthEastNeighbours(3, 0, 10);
        assertEquals(grid.getCellAt(2, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 2), neighbours.get(1));
        assertEquals(grid.getCellAt(0, 3), neighbours.get(2));
    }

    @Test
    void getSouthEastNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(3, grid.getSouthEastNeighbours(0, 0).size());
        assertEquals(2, grid.getSouthEastNeighbours(0, 1).size());
        assertEquals(1, grid.getSouthEastNeighbours(0, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(0, 3).size());

        assertEquals(2, grid.getSouthEastNeighbours(1, 0).size());
        assertEquals(2, grid.getSouthEastNeighbours(1, 1).size());
        assertEquals(1, grid.getSouthEastNeighbours(1, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(1, 3).size());

        assertEquals(1, grid.getSouthEastNeighbours(2, 0).size());
        assertEquals(1, grid.getSouthEastNeighbours(2, 1).size());
        assertEquals(1, grid.getSouthEastNeighbours(2, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(2, 3).size());

        assertEquals(0, grid.getSouthEastNeighbours(3, 0).size());
        assertEquals(0, grid.getSouthEastNeighbours(3, 1).size());
        assertEquals(0, grid.getSouthEastNeighbours(3, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(3, 3).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getSouthEastNeighbours(0, 0);
        assertEquals(grid.getCellAt(1, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(2, 2), neighbours.get(1));
        assertEquals(grid.getCellAt(3, 3), neighbours.get(2));
    }

    @Test
    void getSouthEastNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(2, grid.getSouthEastNeighbours(0, 0, 2).size());
        assertEquals(2, grid.getSouthEastNeighbours(0, 1, 2).size());
        assertEquals(1, grid.getSouthEastNeighbours(0, 2, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(0, 3, 2).size());

        assertEquals(2, grid.getSouthEastNeighbours(1, 0, 2).size());
        assertEquals(2, grid.getSouthEastNeighbours(1, 1, 2).size());
        assertEquals(1, grid.getSouthEastNeighbours(1, 2, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(1, 3, 2).size());

        assertEquals(1, grid.getSouthEastNeighbours(2, 0, 2).size());
        assertEquals(1, grid.getSouthEastNeighbours(2, 1, 2).size());
        assertEquals(1, grid.getSouthEastNeighbours(2, 2, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(2, 3, 2).size());

        assertEquals(0, grid.getSouthEastNeighbours(3, 0, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(3, 1, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(3, 2, 2).size());
        assertEquals(0, grid.getSouthEastNeighbours(3, 3, 2).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getSouthEastNeighbours(0, 0, 2);
        assertEquals(grid.getCellAt(1, 1), neighbours.get(0));
        assertEquals(grid.getCellAt(2, 2), neighbours.get(1));

        neighbours = grid.getSouthEastNeighbours(0, 1, 10);
        assertEquals(grid.getCellAt(1, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(2, 3), neighbours.get(1));
    }

    @Test
    void getSouthWestNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getSouthWestNeighbours(0, 0).size());
        assertEquals(1, grid.getSouthWestNeighbours(0, 1).size());
        assertEquals(2, grid.getSouthWestNeighbours(0, 2).size());
        assertEquals(3, grid.getSouthWestNeighbours(0, 3).size());

        assertEquals(0, grid.getSouthWestNeighbours(1, 0).size());
        assertEquals(1, grid.getSouthWestNeighbours(1, 1).size());
        assertEquals(2, grid.getSouthWestNeighbours(1, 2).size());
        assertEquals(2, grid.getSouthWestNeighbours(1, 3).size());

        assertEquals(0, grid.getSouthWestNeighbours(2, 0).size());
        assertEquals(1, grid.getSouthWestNeighbours(2, 1).size());
        assertEquals(1, grid.getSouthWestNeighbours(2, 2).size());
        assertEquals(1, grid.getSouthWestNeighbours(2, 3).size());

        assertEquals(0, grid.getSouthWestNeighbours(3, 0).size());
        assertEquals(0, grid.getSouthWestNeighbours(3, 1).size());
        assertEquals(0, grid.getSouthWestNeighbours(3, 2).size());
        assertEquals(0, grid.getSouthWestNeighbours(3, 3).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getSouthWestNeighbours(0, 3);
        assertEquals(grid.getCellAt(1, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(2, 1), neighbours.get(1));
        assertEquals(grid.getCellAt(3, 0), neighbours.get(2));
    }

    @Test
    void getSouthWestNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getSouthWestNeighbours(0, 0, 2).size());
        assertEquals(1, grid.getSouthWestNeighbours(0, 1, 2).size());
        assertEquals(2, grid.getSouthWestNeighbours(0, 2, 2).size());
        assertEquals(2, grid.getSouthWestNeighbours(0, 3, 2).size());

        assertEquals(0, grid.getSouthWestNeighbours(1, 0, 2).size());
        assertEquals(1, grid.getSouthWestNeighbours(1, 1, 2).size());
        assertEquals(2, grid.getSouthWestNeighbours(1, 2, 2).size());
        assertEquals(2, grid.getSouthWestNeighbours(1, 3, 2).size());

        assertEquals(0, grid.getSouthWestNeighbours(2, 0, 2).size());
        assertEquals(1, grid.getSouthWestNeighbours(2, 1, 2).size());
        assertEquals(1, grid.getSouthWestNeighbours(2, 2, 2).size());
        assertEquals(1, grid.getSouthWestNeighbours(2, 3, 2).size());

        assertEquals(0, grid.getSouthWestNeighbours(3, 0, 2).size());
        assertEquals(0, grid.getSouthWestNeighbours(3, 1, 2).size());
        assertEquals(0, grid.getSouthWestNeighbours(3, 2, 2).size());
        assertEquals(0, grid.getSouthWestNeighbours(3, 3, 2).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getSouthWestNeighbours(0, 3, 2);
        assertEquals(grid.getCellAt(1, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(2, 1), neighbours.get(1));

        neighbours = grid.getSouthWestNeighbours(0, 3, 10);
        assertEquals(grid.getCellAt(1, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(2, 1), neighbours.get(1));
    }

    @Test
    void getNorthWestNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getNorthWestNeighbours(0, 0).size());
        assertEquals(0, grid.getNorthWestNeighbours(0, 1).size());
        assertEquals(0, grid.getNorthWestNeighbours(0, 2).size());
        assertEquals(0, grid.getNorthWestNeighbours(0, 3).size());

        assertEquals(0, grid.getNorthWestNeighbours(1, 0).size());
        assertEquals(1, grid.getNorthWestNeighbours(1, 1).size());
        assertEquals(1, grid.getNorthWestNeighbours(1, 2).size());
        assertEquals(1, grid.getNorthWestNeighbours(1, 3).size());

        assertEquals(0, grid.getNorthWestNeighbours(2, 0).size());
        assertEquals(1, grid.getNorthWestNeighbours(2, 1).size());
        assertEquals(2, grid.getNorthWestNeighbours(2, 2).size());
        assertEquals(2, grid.getNorthWestNeighbours(2, 3).size());

        assertEquals(0, grid.getNorthWestNeighbours(3, 0).size());
        assertEquals(1, grid.getNorthWestNeighbours(3, 1).size());
        assertEquals(2, grid.getNorthWestNeighbours(3, 2).size());
        assertEquals(3, grid.getNorthWestNeighbours(3, 3).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getNorthWestNeighbours(3, 3);
        assertEquals(grid.getCellAt(2, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 1), neighbours.get(1));
        assertEquals(grid.getCellAt(0, 0), neighbours.get(2));
    }

    @Test
    void getNorthWestNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ITile> grid = new Grid<ITile>(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setCellAt(i, j, ITile.create(i, j));
            }
        }

        assertEquals(0, grid.getNorthWestNeighbours(0, 0, 2).size());
        assertEquals(0, grid.getNorthWestNeighbours(0, 1, 2).size());
        assertEquals(0, grid.getNorthWestNeighbours(0, 2, 2).size());
        assertEquals(0, grid.getNorthWestNeighbours(0, 3, 2).size());

        assertEquals(0, grid.getNorthWestNeighbours(1, 0, 2).size());
        assertEquals(1, grid.getNorthWestNeighbours(1, 1, 2).size());
        assertEquals(1, grid.getNorthWestNeighbours(1, 2, 2).size());
        assertEquals(1, grid.getNorthWestNeighbours(1, 3, 2).size());

        assertEquals(0, grid.getNorthWestNeighbours(2, 0, 2).size());
        assertEquals(1, grid.getNorthWestNeighbours(2, 1, 2).size());
        assertEquals(2, grid.getNorthWestNeighbours(2, 2, 2).size());
        assertEquals(2, grid.getNorthWestNeighbours(2, 3, 2).size());

        assertEquals(0, grid.getNorthWestNeighbours(3, 0, 2).size());
        assertEquals(1, grid.getNorthWestNeighbours(3, 1, 2).size());
        assertEquals(2, grid.getNorthWestNeighbours(3, 2, 2).size());
        assertEquals(2, grid.getNorthWestNeighbours(3, 3, 2).size());

        // checks if the neighbours are the right ones
        ArrayList<ITile> neighbours = grid.getNorthWestNeighbours(3, 3, 2);
        assertEquals(grid.getCellAt(2, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 1), neighbours.get(1));

        neighbours = grid.getNorthWestNeighbours(3, 3, 10);
        assertEquals(grid.getCellAt(2, 2), neighbours.get(0));
        assertEquals(grid.getCellAt(1, 1), neighbours.get(1));
        assertEquals(grid.getCellAt(0, 0), neighbours.get(2));
    }
}