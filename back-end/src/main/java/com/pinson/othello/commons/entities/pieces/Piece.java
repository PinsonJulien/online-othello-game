package com.pinson.othello.commons.entities.pieces;

import com.pinson.othello.commons.entities.tiles.ITile;

public class Piece<T extends ITile<Piece<T>>> implements IPiece<T> {
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
    public IPiece<T> setTile(T cell) {
        this.tile = cell;
        if (this.tile.getPiece() != this)
            this.tile.setPiece(this);

        return this;
    }
}
