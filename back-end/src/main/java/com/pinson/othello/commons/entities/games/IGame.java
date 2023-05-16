package com.pinson.othello.commons.entities.games;

import com.pinson.othello.commons.entities.grids.IGrid;
import com.pinson.othello.commons.entities.pieces.IPiece;
import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.entities.tiles.ITile;

public interface IGame<T extends ITile,  G extends IGrid<T>, P extends IPiece> {

    G getGrid();

    void playMove(int x, int y, P piece);
    void playMove(IMatrixPosition<Integer> position, P piece);

}
