package com.pinson.othello.commons.entities.tiles;

import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;

public interface ITile<T extends IPiece<ITile<T>>> {

    static <T extends IPiece<ITile<T>>> ITile<T> create(int x, int y) {
        return new Tile<>(IMatrixPosition.create(x, y));
    }

    static <T extends IPiece<ITile<T>>> ITile<T> create(IMatrixPosition<Integer> position) {
        return new Tile<>(position);
    }

    static <T extends IPiece<ITile<T>>> ITile<T> create(IMatrixPosition<Integer> position, T piece) {
        ITile<T> tile = new Tile<>(position);
        return tile.setPiece(piece);
    }

    T getPiece();
    ITile<T> setPiece(T piece);

    IMatrixPosition<Integer> getPosition();
}
