package com.pinson.othello.commons.entities.pieces;

import com.pinson.othello.commons.entities.tiles.ITile;

public interface IPiece {

    static IPiece create() {
        return new Piece();
    }

    static IPiece create(ITile cell) {
        return new Piece(cell);
    }

    ITile getCell();
    IPiece setCell(ITile cell);
}
