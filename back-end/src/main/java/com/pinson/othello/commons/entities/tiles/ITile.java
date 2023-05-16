package com.pinson.othello.commons.entities.tiles;

import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;

public interface ITile {

    static ITile create(int x, int y) {
        return new Tile(IMatrixPosition.create(x, y));
    }

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
