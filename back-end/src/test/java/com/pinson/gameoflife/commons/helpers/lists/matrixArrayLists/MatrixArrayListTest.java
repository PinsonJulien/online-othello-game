package com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists;

import com.pinson.gameoflife.commons.exceptions.NonPositiveValueException;
import com.pinson.gameoflife.commons.helpers.lists.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixArrayListTest {


    @Test
    public void constructor() {
        IMatrixArrayList<Integer> matrix = new MatrixArrayList<>();

        assertEquals(0, matrix.getRows());
        assertEquals(0, matrix.getColumns());
    }

    @Test
    public void constructor_RowsColumns() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IMatrixArrayList<Integer> matrix = new MatrixArrayList<>(2, 3);

        assertEquals(2, matrix.getRows());
        assertEquals(3, matrix.getColumns());
    }

    @Test
    public void constructor_RowsColumns_NonPositiveValueException() {
        assertThrowsExactly(NonPositiveValueException.class, () -> new MatrixArrayList<>(-1, 3));
        assertThrowsExactly(NonPositiveValueException.class, () -> new MatrixArrayList<>(2, -3));
        assertThrowsExactly(NonPositiveValueException.class, () -> new MatrixArrayList<>(-1, -3));
    }

    @Test
    void getRows() {
    }

    @Test
    void setRows() {
    }

    @Test
    void getColumns() {
    }

    @Test
    void setColumns() {
    }

    @Test
    void get() {
    }

    @Test
    void testGet() {
    }

    @Test
    void set() {
    }

    @Test
    void testSet() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void find() {
    }

    @Test
    void testRemove1() {
    }

    @Test
    void checkBounds() {
    }
}