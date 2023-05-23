package com.pinson.othello.commons.entities.pieces;

import com.pinson.othello.commons.entities.tiles.ITile;

public abstract class Piece<T extends ITile<P, T>, P extends IPiece<T, P>> implements IPiece<T, P> {
    private T tile;

    public Piece() {
        this.tile = null;
    }

    public Piece(T cell) {
        this.tile = cell;
    }

    @Override
    public T getTile() {
        return this.tile;
    }

    @Override
    public P setTile(T tile) {
        this.tile = tile;
        if (this.tile.getPiece() != this)
            this.tile.setPiece(this.self());

        return this.self();
    }

    @SuppressWarnings("unchecked")
    private P self() {
        return (P) this;
    }
}
