package com.pinson.gameoflife.commons.entities.tiles;

import com.pinson.gameoflife.commons.entities.pieces.IPiece;
import com.pinson.gameoflife.commons.entities.positions.MatrixPositions.IMatrixPosition;

public interface ITile {
    static ITile create(IMatrixPosition<Integer> position) {
        return new Tile(position);
    }

    static ITile create(IMatrixPosition<Integer> position, IPiece piece) {
        return new Tile(position).setPiece(piece);
    }

    IPiece getPiece();
    ITile setPiece(IPiece piece);

    IMatrixPosition<Integer> getPosition();
}
