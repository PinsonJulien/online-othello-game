package com.pinson.othello.commons.entities.pieces;

import com.pinson.othello.commons.entities.tiles.ITile;

public interface IPiece<T extends ITile<P, T>, P extends IPiece<T, P>> {

    T getTile();
    P setTile(T tile);
}
