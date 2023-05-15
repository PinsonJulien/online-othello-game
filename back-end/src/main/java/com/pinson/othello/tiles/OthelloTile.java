package com.pinson.othello.tiles;

import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.entities.tiles.Tile;

public class OthelloTile extends Tile implements IOthelloTile {
    public OthelloTile(IMatrixPosition<Integer> position) {
        super(position);
    }
    
}
