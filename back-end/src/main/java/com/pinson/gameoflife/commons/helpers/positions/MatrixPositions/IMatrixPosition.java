package com.pinson.gameoflife.commons.helpers.positions.MatrixPositions;

import com.pinson.gameoflife.commons.helpers.positions.IPosition;

public interface IMatrixPosition<T extends Number> extends IPosition<T> {

    T getY();
    IMatrixPosition<T> setY(T y);

    IMatrixPosition<T> set(T x, T y);
}
