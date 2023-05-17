package com.pinson.othello.tiles;

import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.entities.tiles.ITile;
import com.pinson.othello.disks.IOthelloDisk;

public interface IOthelloTile extends ITile<IOthelloDisk, IOthelloTile> {

    static IOthelloTile create(IMatrixPosition<Integer> position) {
        return new OthelloTile(position);
    }
}
