package com.pinson.othello.commons.entities.pieces;

import com.pinson.othello.commons.entities.tiles.ITile;

public interface IPiece<T extends ITile<?>> {

    static <T extends ITile<?>> IPiece<T> create() {
        return new Piece<>();
    }

    static <T extends ITile<?>> IPiece<T> create(T cell) {
        return new Piece<>(cell);
    }

    T getTile();
    IPiece<T> setTile(T cell);
}
