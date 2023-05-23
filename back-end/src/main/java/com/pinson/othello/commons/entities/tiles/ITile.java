package com.pinson.othello.commons.entities.tiles;

import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;

public interface ITile<P extends IPiece<T, P>, T extends ITile<P, T>> {

    P getPiece();
    T setPiece(P piece);

    IMatrixPosition<Integer> getPosition();
}
