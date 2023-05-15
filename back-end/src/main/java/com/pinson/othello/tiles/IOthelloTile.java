package com.pinson.othello.tiles;

import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.entities.tiles.ITile;

public interface IOthelloTile extends ITile {

    static IOthelloTile create(IMatrixPosition<Integer> position) {
        return new OthelloTile(position);
    }
}
