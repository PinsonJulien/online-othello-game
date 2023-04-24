package com.pinson.gameoflife.commons.helpers.positions.MatrixPositions;

import com.pinson.gameoflife.commons.helpers.positions.Position;

public class MatrixPosition<T extends Number> extends Position<T> implements IMatrixPosition<T> {
    private T y;

    public MatrixPosition(T x, T y) {
        super(x);
        this.y = y;
    }

    public T getY() {
        return y;
    }

    public IMatrixPosition<T> setY(T y) {
        this.y = y;
        return this;
    }
}
