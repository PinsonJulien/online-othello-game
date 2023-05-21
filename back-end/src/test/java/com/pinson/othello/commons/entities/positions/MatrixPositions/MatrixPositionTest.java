package com.pinson.othello.commons.entities.positions.MatrixPositions;

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

    @Test
    void equals() {
        IMatrixPosition<Integer> position = new MatrixPosition<>(1, 2);
        IMatrixPosition<Integer> position2 = new MatrixPosition<>(1, 2);
        IMatrixPosition<Integer> position3 = new MatrixPosition<>(3, 4);
        IMatrixPosition<Float> position4 = new MatrixPosition<>(1.0f, 2.0f);
        IMatrixPosition<Long> position5 = new MatrixPosition<>(1L, 2L);

        assertEquals(position, position); // Same object
        assertEquals(position, position2);
        assertNotEquals(position, null);
        assertNotEquals(position, position3);
        assertNotEquals(position, position4);
        assertNotEquals(position, position5);

        assertEquals(position2, position2); // Same object
        assertNotEquals(position2, null);
        assertNotEquals(position2, position3);
        assertNotEquals(position2, position4);
        assertNotEquals(position2, position5);

        assertEquals(position3, position3); // Same object
        assertNotEquals(position3, null);
        assertNotEquals(position3, position4);
        assertNotEquals(position3, position5);

        assertEquals(position4, position4); // Same object
        assertNotEquals(position4, null);
        assertNotEquals(position4, position5);

        assertEquals(position5, position5); // Same object
        assertNotEquals(position5, null);
    }
}