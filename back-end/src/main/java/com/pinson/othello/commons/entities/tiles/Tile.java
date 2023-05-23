package com.pinson.othello.commons.entities.tiles;

import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;

public abstract class Tile<P extends IPiece<T, P>, T extends ITile<P, T>> implements ITile<P, T> {
    private P piece;
    private final IMatrixPosition<Integer> position;

    public Tile(IMatrixPosition<Integer> position) {
        this.piece = null;
        this.position = position;
    }

    public Tile(int x, int y) {
        this(IMatrixPosition.create(x, y));
    }

    @Override
    public IMatrixPosition<Integer> getPosition() {
        return this.position;
    }

    @Override
    public P getPiece() {
        return this.piece;
    }

    @Override
    public T setPiece(P piece) {
        this.piece = piece;
        if (this.piece.getTile() != this)
            this.piece.setTile(this.self());

        return this.self();
    }

    @SuppressWarnings("unchecked")
    private T self() {
        return (T) this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ITile<?, ?> tile)) {
            return false;
        }

        if (this == tile)
            return true;

        return (
            this.getPosition().equals(tile.getPosition())
            &&
            this.getPiece() == tile.getPiece()
        );
    }
}

