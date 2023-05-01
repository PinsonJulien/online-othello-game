package com.pinson.gameoflife.commons.helpers.collections.matrixArrayLists;

import com.pinson.gameoflife.commons.exceptions.NonPositiveValueException;
import com.pinson.gameoflife.commons.exceptions.NotFoundException;
import com.pinson.gameoflife.commons.helpers.collections.matrixArrayLists.exceptions.MatrixIndexOutOfBoundsException;
import com.pinson.gameoflife.commons.helpers.positions.MatrixPositions.IMatrixPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixArrayListTest {


    @Test
    public void constructor() {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create();

        assertEquals(0, matrix.getRows());
        assertEquals(0, matrix.getColumns());
    }

    @Test
    public void constructor_RowsColumns() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertEquals(2, matrix.getRows());
        assertEquals(3, matrix.getColumns());
    }

    @Test
    public void constructor_RowsColumns_NonPositiveValueException() {
        assertThrowsExactly(NonPositiveValueException.class, () -> IMatrixArrayList.create(-1, 3));
        assertThrowsExactly(NonPositiveValueException.class, () -> IMatrixArrayList.create(2, -3));
        assertThrowsExactly(NonPositiveValueException.class, () -> IMatrixArrayList.create(-1, -3));
    }

    @Test
    void getRows() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertEquals(2, matrix.getRows());
    }

    @Test
    void getColumns() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertEquals(3, matrix.getColumns());
    }

    @Test
    void get_RowColumn() throws MatrixIndexOutOfBoundsException, NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        matrix.set(0, 0, 1);

        assertEquals(1, matrix.get(0, 0));

        matrix.set(1, 2, 2);

        assertEquals(2, matrix.get(1, 2));

        matrix.set(1, 2, 3);
        assertEquals(3, matrix.get(1, 2));
    }

    @Test
    void get_RowColumn_MatrixIndexOutOfBoundsException() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.get(-1, 0));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.get(0, -1));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.get(3, 0));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.get(0, 4));
    }

    @Test
    void get_IMatrixPosition() throws MatrixIndexOutOfBoundsException, NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        matrix.set(0, 0, 1);
        IMatrixPosition.create(0, 0);
        assertEquals(1, matrix.get(IMatrixPosition.create(0, 0)));

        matrix.set(1, 2, 2);

        assertEquals(2, matrix.get(IMatrixPosition.create(2, 1)));
    }

    @Test
    void get_IMatrixPosition_MatrixIndexOutOfBoundsException() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.get(IMatrixPosition.create(-1, 0)));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.get(IMatrixPosition.create(0, -1)));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.get(IMatrixPosition.create(4, 0)));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.get(IMatrixPosition.create(0, 3)));
    }

    @Test
    void set_RowColumnValue() throws MatrixIndexOutOfBoundsException, NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        matrix.set(0, 0, 1);

        assertEquals(1, matrix.get(0, 0));

        matrix.set(1, 2, 2);

        assertEquals(2, matrix.get(1, 2));
    }

    @Test
    void set_RowColumnValue_MatrixIndexOutOfBoundsException() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.set(-1, 0, 1));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.set(0, -1, 1));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.set(3, 0, 1));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.set(0, 4, 1));
    }

    @Test
    void set_IMatrixPositionValue() throws MatrixIndexOutOfBoundsException, NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        matrix.set(IMatrixPosition.create(0, 0), 1);

        assertEquals(1, matrix.get(0, 0));

        matrix.set(IMatrixPosition.create(2, 1), 2);

        assertEquals(2, matrix.get(1, 2));
    }

    @Test
    void set_IMatrixPositionValue_MatrixIndexOutOfBoundsException() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.set(IMatrixPosition.create(-1, 0), 1));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.set(IMatrixPosition.create(0, -1), 1));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.set(IMatrixPosition.create(4, 0), 1));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.set(IMatrixPosition.create(0, 3), 1));
    }

    @Test
    void remove_RowColumn() throws MatrixIndexOutOfBoundsException, NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        matrix.set(0, 0, 1);

        assertEquals(1, matrix.get(0, 0));

        matrix.remove(0, 0);

        assertNull(matrix.get(0, 0));
    }

    @Test
    void remove_RowColumn_MatrixIndexOutOfBoundsException() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.remove(-1, 0));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.remove(0, -1));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.remove(2, 0));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.remove(0, 3));
    }

    @Test
    void remove_IMatrixPosition() throws MatrixIndexOutOfBoundsException, NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        matrix.set(0, 0, 1);

        assertEquals(1, matrix.get(0, 0));

        matrix.remove(IMatrixPosition.create(0, 0));

        assertNull(matrix.get(0, 0));
    }

    @Test
    void remove_IMatrixPosition_MatrixIndexOutOfBoundsException() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.remove(IMatrixPosition.create(-1, 0)));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.remove(IMatrixPosition.create(0, -1)));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.remove(IMatrixPosition.create(4, 0)));
        assertThrowsExactly(MatrixIndexOutOfBoundsException.class, () -> matrix.remove(IMatrixPosition.create(0, 3)));
    }

    @Test
    void find() throws NonPositiveValueException, MatrixIndexOutOfBoundsException, NotFoundException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(0, 2, 3);
        matrix.set(1, 0, 4);

        assertEquals(0, matrix.find(1).getX());
        assertEquals(0, matrix.find(1).getY());
        assertEquals(1, matrix.find(2).getX());
        assertEquals(0, matrix.find(2).getY());
        assertEquals(2, matrix.find(3).getX());
        assertEquals(0, matrix.find(3).getY());
        assertEquals(0, matrix.find(4).getX());
        assertEquals(1, matrix.find(4).getY());
    }

    @Test
    void find_NotFoundException() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(0, 2, 3);
        matrix.set(1, 0, 4);

        assertThrowsExactly(NotFoundException.class, () -> matrix.find(5));
    }

    @Test
    void resize() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        matrix.resize(3, 4);

        assertEquals(3, matrix.getRows());
        assertEquals(4, matrix.getColumns());

        matrix.resize(1, 1);

        assertEquals(1, matrix.getRows());
        assertEquals(1, matrix.getColumns());
    }

    @Test
    void resize_NonPositiveValueException() throws NonPositiveValueException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        assertThrowsExactly(NonPositiveValueException.class, () -> matrix.resize(-1, 1));
        assertThrowsExactly(NonPositiveValueException.class, () -> matrix.resize(1, -1));
        assertThrowsExactly(NonPositiveValueException.class, () -> matrix.resize(-1, -1));
    }

    @Test
    void insertRows() throws NonPositiveValueException, MatrixIndexOutOfBoundsException {
        IMatrixArrayList<Integer> matrix = IMatrixArrayList.create(2, 3);

        // set some values
        matrix.set(0, 0, 1);
        matrix.set(0, 1, 2);
        matrix.set(0, 2, 3);
        matrix.set(1, 0, 4);
        matrix.set(1, 1, 5);
        matrix.set(1, 2, 6);


        matrix.insertRows(1);
        assertEquals(3, matrix.getRows());
        assertEquals(3, matrix.getColumns());

        // check if values are still there
        assertEquals(1, matrix.get(0, 0));
        assertEquals(2, matrix.get(0, 1));
        assertEquals(3, matrix.get(0, 2));
        assertEquals(4, matrix.get(2, 0));
        assertEquals(5, matrix.get(2, 1));
        assertEquals(6, matrix.get(2, 2));

        // check if new row is empty
        assertNull(matrix.get(1, 0));
        assertNull(matrix.get(1, 1));
        assertNull(matrix.get(1, 2));

        matrix = IMatrixArrayList.create();

        matrix.insertRows(1);
        assertEquals(0, matrix.getRows());
        assertEquals(0, matrix.getColumns());

        matrix.insertRows(1);
        assertEquals(1, matrix.getRows());
        assertEquals(0, matrix.getColumns());
    }


}