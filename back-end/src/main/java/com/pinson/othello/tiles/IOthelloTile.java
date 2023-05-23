package com.pinson.othello.tiles;

import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.disks.IOthelloDisk;
import com.pinson.othello.positions.IOthelloPosition;

public interface IOthelloTile extends ITile<IOthelloDisk, IOthelloTile> {

    static IOthelloTile create(int row, int column) throws NonPositiveValueException {
        return new OthelloTile(IOthelloPosition.create(row, column));
    }

    static IOthelloTile create(IOthelloPosition position) {
        return new OthelloTile(position);
    }

    @Override
    IOthelloPosition getPosition();
}
