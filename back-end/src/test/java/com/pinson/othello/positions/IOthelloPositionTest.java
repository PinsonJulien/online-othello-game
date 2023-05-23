package com.pinson.othello.positions;

import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IOthelloPositionTest {

    @Test
    void isStandardNotation() {
        assertTrue(IOthelloPosition.isStandardNotation("A1"));
        assertTrue(IOthelloPosition.isStandardNotation("H8"));
        assertTrue(IOthelloPosition.isStandardNotation("D5"));
        assertTrue(IOthelloPosition.isStandardNotation("E6"));
        assertTrue(IOthelloPosition.isStandardNotation("FAZ70"));
        assertTrue(IOthelloPosition.isStandardNotation("A1000"));
        assertTrue(IOthelloPosition.isStandardNotation("ZZZZ50"));
        assertFalse(IOthelloPosition.isStandardNotation("A"));
        assertFalse(IOthelloPosition.isStandardNotation("1"));
        assertFalse(IOthelloPosition.isStandardNotation("A0"));
        assertFalse(IOthelloPosition.isStandardNotation("6A"));
        assertFalse(IOthelloPosition.isStandardNotation("6A1"));
    }

    @Test
    void toStandardNotation() throws NonPositiveValueException {
        assertEquals("A1", IOthelloPosition.toStandardNotation(IOthelloPosition.create(0, 0)));
        assertEquals("H8", IOthelloPosition.toStandardNotation(IOthelloPosition.create(7, 7)));
        assertEquals("D5", IOthelloPosition.toStandardNotation(IOthelloPosition.create(4, 3)));
        assertEquals("E6", IOthelloPosition.toStandardNotation(IOthelloPosition.create(5, 4)));
        assertEquals("FA270", IOthelloPosition.toStandardNotation(IOthelloPosition.create(269, 26*5)));
        assertEquals("A1000", IOthelloPosition.toStandardNotation(IOthelloPosition.create(999, 0)));
        assertEquals("ZZZZ50", IOthelloPosition.toStandardNotation(IOthelloPosition.create(49, (26*26*26*26)-1)));

        assertThrows(NonPositiveValueException.class, () -> IOthelloPosition.toStandardNotation(IOthelloPosition.create(-1, 0)));
        assertThrows(NonPositiveValueException.class, () -> IOthelloPosition.toStandardNotation(IOthelloPosition.create(0, -1)));
        assertThrows(NonPositiveValueException.class, () -> IOthelloPosition.toStandardNotation(IOthelloPosition.create(-1, -1)));
    }
}