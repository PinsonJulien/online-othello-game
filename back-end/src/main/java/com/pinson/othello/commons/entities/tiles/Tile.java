package com.pinson.othello.commons.entities.tiles;

import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;

public class Tile<T extends IPiece<ITile<T>>> implements ITile<T> {
    private T piece;
    private final IMatrixPosition<Integer> position;

    public Tile(IMatrixPosition<Integer> position) {
        this.piece = null;
        this.position = position;
    }

    @Override
    public IMatrixPosition<Integer> getPosition() {
        return this.position;
    }

    @Override
    public T getPiece() {
        return this.piece;
    }

    @Override
    public ITile<T> setPiece(T piece) {
        this.piece = piece;
        if (this.piece.getTile() != this)
            this.piece.setTile(this);

        return this;
    }
}

