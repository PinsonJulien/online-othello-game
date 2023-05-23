package com.pinson.othello.positions;

import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.positions.exceptions.InvalidStandardNotationException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OthelloPositionTest {

    @Test
    void getStandardNotation() throws NonPositiveValueException {
        IOthelloPosition position = IOthelloPosition.create(0, 0);
        assertEquals("A1", position.getStandardNotation());
        position = IOthelloPosition.create(7, 7);
        assertEquals("H8", position.getStandardNotation());
        position = IOthelloPosition.create(4, 3);
        assertEquals("D5", position.getStandardNotation());
        position = IOthelloPosition.create(5, 4);
        assertEquals("E6", position.getStandardNotation());
        position = IOthelloPosition.create(269, 26*5);
        assertEquals("FA270", position.getStandardNotation());
        position = IOthelloPosition.create(999, 0);
        assertEquals("A1000", position.getStandardNotation());
        position = IOthelloPosition.create(49, (26*26*26*26)-1);
        assertEquals("ZZZZ50", position.getStandardNotation());
    }

    @Test
    void getAlgebraicNotation() throws NonPositiveValueException {
        IOthelloPosition position = IOthelloPosition.create(0, 0);
        assertEquals("A1", position.getStandardNotation());
        position = IOthelloPosition.create(7, 7);
        assertEquals("H8", position.getStandardNotation());
        position = IOthelloPosition.create(4, 3);
        assertEquals("D5", position.getStandardNotation());
        position = IOthelloPosition.create(5, 4);
        assertEquals("E6", position.getStandardNotation());
        position = IOthelloPosition.create(269, 26*5);
        assertEquals("FA270", position.getStandardNotation());
        position = IOthelloPosition.create(999, 0);
        assertEquals("A1000", position.getStandardNotation());
        position = IOthelloPosition.create(49, (26*26*26*26)-1);
        assertEquals("ZZZZ50", position.getStandardNotation());
    }

    @Test
    void setPosition() throws NonPositiveValueException, InvalidStandardNotationException {
        IOthelloPosition position = IOthelloPosition.create(0, 0);
        position.setPosition("A1");
        assertEquals(0, position.getRow());
        assertEquals(0, position.getColumn());
        assertEquals("A1", position.getStandardNotation());
        position.setPosition("H8");
        assertEquals(7, position.getRow());
        assertEquals(7, position.getColumn());
        assertEquals("H8", position.getStandardNotation());
        position.setPosition("D5");
        assertEquals(4, position.getRow());
        assertEquals(3, position.getColumn());
        assertEquals("D5", position.getStandardNotation());
        position.setPosition("E6");
        assertEquals(5, position.getRow());
        assertEquals(4, position.getColumn());
        assertEquals("E6", position.getStandardNotation());
        position.setPosition("FA270");
        assertEquals(269, position.getRow());
        assertEquals(26*5, position.getColumn());
        assertEquals("FA270", position.getStandardNotation());
        position.setPosition("A1000");
        assertEquals(999, position.getRow());
        assertEquals(0, position.getColumn());
        assertEquals("A1000", position.getStandardNotation());
        position.setPosition("ZZZZ50");
        assertEquals(49, position.getRow());
        assertEquals((26*26*26*26)-1, position.getColumn());
        assertEquals("ZZZZ50", position.getStandardNotation());

        assertThrows(InvalidStandardNotationException.class, () -> position.setPosition("A0"));
        assertThrows(InvalidStandardNotationException.class, () -> position.setPosition("A-1"));
        assertThrows(InvalidStandardNotationException.class, () -> position.setPosition("10A10"));
        assertThrows(InvalidStandardNotationException.class, () -> position.setPosition("A10A"));
        assertThrows(InvalidStandardNotationException.class, () -> position.setPosition("10"));
    }

    @Test
    void equals() throws NonPositiveValueException {
        IOthelloPosition position1 = IOthelloPosition.create(0, 0);
        IOthelloPosition position2 = IOthelloPosition.create(0, 0);
        IOthelloPosition position3 = IOthelloPosition.create(0, 1);
        IOthelloPosition position4 = IOthelloPosition.create(1, 0);
        IOthelloPosition position5 = IOthelloPosition.create(1, 1);

        assertEquals(position1, position2);
        assertNotEquals(position1, position3);
        assertNotEquals(position1, position4);
        assertNotEquals(position1, position5);
        assertNotEquals(position2, position3);
        assertNotEquals(position2, position4);
        assertNotEquals(position2, position5);
        assertNotEquals(position3, position4);
        assertNotEquals(position3, position5);
        assertNotEquals(position4, position5);
    }

    @Test
    void getRow() {
    }

    @Test
    void setRow() {
    }

    @Test
    void getColumn() {
    }

    @Test
    void setColumn() {
    }


}