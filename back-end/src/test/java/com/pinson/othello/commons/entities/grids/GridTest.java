package com.pinson.othello.commons.entities.grids;

import com.pinson.othello.commons.entities.pieces.Piece;
import com.pinson.othello.commons.entities.tiles.Tile;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {

    @Test
    void getRows() {
    }

    @Test
    void getColumns() {
    }

    @Test
    void getTiles() {
    }

    @Test
    void getTileAt() {
    }

    @Test
    void setTileAt() {
    }

    @Test
    void findTile() {
    }

    @Test
    void getNorthNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 3);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getNorthNeighbours(3, 0);
        assertEquals(grid.getTileAt(2, 0), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 0), neighbours.get(1));
        assertEquals(grid.getTileAt(0, 0), neighbours.get(2));
    }

    @Test
    void getNorthNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 3);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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

        List<ConcreteTile> neighbours = grid.getNorthNeighbours(3, 0, 2);
        assertEquals(grid.getTileAt(2, 0), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 0), neighbours.get(1));

        neighbours = grid.getNorthNeighbours(3, 1, 10);
        assertEquals(grid.getTileAt(2, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 1), neighbours.get(1));
        assertEquals(grid.getTileAt(0, 1), neighbours.get(2));
    }

    @Test
    void getEastNeighbour_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(3, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getEastNeighbours(1, 1);
        assertEquals(grid.getTileAt(1, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 3), neighbours.get(1));
    }

    @Test
    void getEastNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(3, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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

        List<ConcreteTile> neighbours = grid.getEastNeighbours(1, 0, 2);
        assertEquals(grid.getTileAt(1, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 2), neighbours.get(1));

        neighbours = grid.getEastNeighbours(1, 0, 10);
        assertEquals(grid.getTileAt(1, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 2), neighbours.get(1));
        assertEquals(grid.getTileAt(1, 3), neighbours.get(2));
    }

    @Test
    void getSouthNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 3);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getSouthNeighbours(1, 1);
        assertEquals(grid.getTileAt(2, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(3, 1), neighbours.get(1));
    }

    @Test
    void getSouthNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 3);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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

        List<ConcreteTile> neighbours = grid.getSouthNeighbours(0, 1, 2);
        assertEquals(grid.getTileAt(1, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(2, 1), neighbours.get(1));

        neighbours = grid.getSouthNeighbours(0, 1, 10);
        assertEquals(grid.getTileAt(1, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(2, 1), neighbours.get(1));
        assertEquals(grid.getTileAt(3, 1), neighbours.get(2));
    }

    @Test
    void getWestNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(3, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getWestNeighbours(1, 3);
        assertEquals(grid.getTileAt(1, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 1), neighbours.get(1));
        assertEquals(grid.getTileAt(1, 0), neighbours.get(2));
    }

    @Test
    void getWestNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(3, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getWestNeighbours(1, 3, 2);
        assertEquals(grid.getTileAt(1, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 1), neighbours.get(1));

        neighbours = grid.getWestNeighbours(1, 3, 10);
        assertEquals(grid.getTileAt(1, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 1), neighbours.get(1));
        assertEquals(grid.getTileAt(1, 0), neighbours.get(2));
    }

    @Test
    void getNorthEastNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getNorthEastNeighbours(3, 0);
        assertEquals(grid.getTileAt(2, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 2), neighbours.get(1));
        assertEquals(grid.getTileAt(0, 3), neighbours.get(2));
    }

    @Test
    void getNorthEastNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getNorthEastNeighbours(3, 0, 2);
        assertEquals(grid.getTileAt(2, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 2), neighbours.get(1));

        neighbours = grid.getNorthEastNeighbours(3, 0, 10);
        assertEquals(grid.getTileAt(2, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 2), neighbours.get(1));
        assertEquals(grid.getTileAt(0, 3), neighbours.get(2));
    }

    @Test
    void getSouthEastNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getSouthEastNeighbours(0, 0);
        assertEquals(grid.getTileAt(1, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(2, 2), neighbours.get(1));
        assertEquals(grid.getTileAt(3, 3), neighbours.get(2));
    }

    @Test
    void getSouthEastNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getSouthEastNeighbours(0, 0, 2);
        assertEquals(grid.getTileAt(1, 1), neighbours.get(0));
        assertEquals(grid.getTileAt(2, 2), neighbours.get(1));

        neighbours = grid.getSouthEastNeighbours(0, 1, 10);
        assertEquals(grid.getTileAt(1, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(2, 3), neighbours.get(1));
    }

    @Test
    void getSouthWestNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getSouthWestNeighbours(0, 3);
        assertEquals(grid.getTileAt(1, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(2, 1), neighbours.get(1));
        assertEquals(grid.getTileAt(3, 0), neighbours.get(2));
    }

    @Test
    void getSouthWestNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getSouthWestNeighbours(0, 3, 2);
        assertEquals(grid.getTileAt(1, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(2, 1), neighbours.get(1));

        neighbours = grid.getSouthWestNeighbours(0, 3, 10);
        assertEquals(grid.getTileAt(1, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(2, 1), neighbours.get(1));
    }

    @Test
    void getNorthWestNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getNorthWestNeighbours(3, 3);
        assertEquals(grid.getTileAt(2, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 1), neighbours.get(1));
        assertEquals(grid.getTileAt(0, 0), neighbours.get(2));
    }

    @Test
    void getNorthWestNeighbours_RowColumnDistance() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(4, 4);

        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
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
        List<ConcreteTile> neighbours = grid.getNorthWestNeighbours(3, 3, 2);
        assertEquals(grid.getTileAt(2, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 1), neighbours.get(1));

        neighbours = grid.getNorthWestNeighbours(3, 3, 10);
        assertEquals(grid.getTileAt(2, 2), neighbours.get(0));
        assertEquals(grid.getTileAt(1, 1), neighbours.get(1));
        assertEquals(grid.getTileAt(0, 0), neighbours.get(2));
    }

    @Test
    void getAdjacentNeighbours_RowColumn() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IGrid<ConcreteTile> grid = new ConcreteGrid(5, 6);
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getColumns(); j++) {
                grid.setTileAt(i, j, new ConcreteTile(i, j));
            }
        }

        List<List<ConcreteTile>> neighbours = grid.getAdjacentNeighbours(1, 4);
        assertEquals(1, neighbours.get(0).size()); // north
        assertEquals(1, neighbours.get(1).size()); // north east
        assertEquals(1, neighbours.get(2).size()); // east
        assertEquals(1, neighbours.get(3).size()); // south east
        assertEquals(3, neighbours.get(4).size()); // south
        assertEquals(3, neighbours.get(5).size()); // south west
        assertEquals(4, neighbours.get(6).size()); // west
        assertEquals(1, neighbours.get(7).size()); // north west

        neighbours = grid.getAdjacentNeighbours(2, 2);
        assertEquals(2, neighbours.get(0).size()); // north
        assertEquals(2, neighbours.get(1).size()); // north east
        assertEquals(3, neighbours.get(2).size()); // east
        assertEquals(2, neighbours.get(3).size()); // south east
        assertEquals(2, neighbours.get(4).size()); // south
        assertEquals(2, neighbours.get(5).size()); // south west
        assertEquals(2, neighbours.get(6).size()); // west
        assertEquals(2, neighbours.get(7).size()); // north west
    }

    // Concrete classes for test purposes.
    private class ConcreteGrid extends Grid<ConcreteTile> {
        public ConcreteGrid(int rows, int columns) throws NonPositiveValueException {
            super(rows, columns);
        }
    }

    private class ConcreteTile extends Tile<ConcretePiece, ConcreteTile> {
        public ConcreteTile(int row, int column) throws NonPositiveValueException {
            super(row, column);
        }
    }

    private class ConcretePiece extends Piece<ConcreteTile, ConcretePiece> {
        public ConcretePiece() throws NonPositiveValueException {
            super();
        }
    }
}