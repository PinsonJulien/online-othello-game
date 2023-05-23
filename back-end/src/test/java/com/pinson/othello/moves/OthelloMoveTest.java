package com.pinson.othello.moves;

import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.positions.IOthelloPosition;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OthelloMoveTest {

    @Test
    void getPosition() throws NonPositiveValueException {
        IOthelloMove move = IOthelloMove.create();
        assertEquals(IOthelloPosition.create(0, 0), move.getPosition());

        move.setColumn(7).setRow(7);
        assertEquals(IOthelloPosition.create(7, 7), move.getPosition());

        move.setColumn(3).setRow(4);
        assertEquals(IOthelloPosition.create(4, 3), move.getPosition());

        move.setColumn(0).setRow(0);
        assertEquals(IOthelloPosition.create(0, 0), move.getPosition());
    }

    @Test
    void setPosition() throws NonPositiveValueException {
        IOthelloMove move = IOthelloMove.create();
        assertEquals(IOthelloPosition.create(0, 0), move.getPosition());

        move.setPosition(IOthelloPosition.create(7, 7));
        assertEquals(IOthelloPosition.create(7, 7), move.getPosition());

        move.setPosition(null);
        assertEquals(IOthelloPosition.create(0, 0), move.getPosition());

        move.setPosition(IOthelloPosition.create(4, 3));
        assertEquals(IOthelloPosition.create(4, 3), move.getPosition());

        move.setPosition(IOthelloPosition.create(0, 0));
        assertEquals(IOthelloPosition.create(0, 0), move.getPosition());
    }

    @Test
    void testEquals() {

    }
}