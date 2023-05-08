package com.pinson.gameoflife.commons.entities.tiles;

import com.pinson.gameoflife.commons.entities.pieces.IPiece;
import com.pinson.gameoflife.commons.entities.positions.MatrixPositions.IMatrixPosition;

public class Tile implements ITile {
    private IPiece piece;
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
    public IPiece getPiece() {
        return this.piece;
    }

    @Override
    public ITile setPiece(IPiece piece) {
        this.piece = piece;
        if (this.piece.getCell() != this)
            this.piece.setCell(this);

        return this;
    }
}

