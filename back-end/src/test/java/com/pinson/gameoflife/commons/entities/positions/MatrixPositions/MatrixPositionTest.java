package com.pinson.gameoflife.commons.entities.positions.MatrixPositions;

import com.pinson.gameoflife.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.gameoflife.commons.entities.positions.MatrixPositions.MatrixPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MatrixPositionTest {

    @Test
    void getX() {
        IMatrixPosition<Integer> position = new MatrixPosition<>(1, 2);
        assertEquals(1, position.getX());

        position.setX(3);
        assertEquals(3, position.getX());

        position.setX(-3);
        assertEquals(-3, position.getX());
    }

    @Test
    void setX() {
        IMatrixPosition<Integer> position = new MatrixPosition<>(1, 2);

        IMatrixPosition<Integer> position2 = position.setX(3);
        assertEquals(3, position2.getX());
        assertEquals(position, position2);
    }

    @Test
    void getY() {
        IMatrixPosition<Integer> position = new MatrixPosition<>(1, 2);

        assertEquals(2, position.getY());

        position.setY(3);
        assertEquals(3, position.getY());

        position.setY(-3);
        assertEquals(-3, position.getY());
    }

    @Test
    void setY() {
        IMatrixPosition<Integer> position = new MatrixPosition<>(1, 2);

        IMatrixPosition<Integer> position2 = position.setY(3);

        assertEquals(3, position2.getY());

        assertEquals(position, position2);
    }

    @Test
    void set() {
        IMatrixPosition<Integer> position = new MatrixPosition<>(1, 2);

        IMatrixPosition<Integer> position2 = position.set(3, 4);

        assertEquals(3, position2.getX());
        assertEquals(4, position2.getY());

        assertEquals(position, position2);
    }
}