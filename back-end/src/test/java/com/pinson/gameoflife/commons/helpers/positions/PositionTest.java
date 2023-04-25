package com.pinson.gameoflife.commons.helpers.positions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionTest {

    @Test
    void getX() {
        IPosition<Integer> position = new Position<>(1);

        assertEquals(1, position.getX());

        position.setX(2);

        assertEquals(2, position.getX());

        position.setX(-3);
        assertEquals(-3, position.getX());
    }

    @Test
    void setX() {
        IPosition<Integer> position = new Position<>(1);

        IPosition<Integer> position2 = position.setX(2);

        assertEquals(2, position2.getX());

        assertEquals(position, position2);
    }

    @Test
    void set() {
        IPosition<Integer> position = new Position<>(1);

        IPosition<Integer> position2 = position.set(2);

        assertEquals(2, position2.getX());

        assertEquals(position, position2);
    }
}