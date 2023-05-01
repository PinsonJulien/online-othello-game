package com.pinson.gameoflife.commons.entities.positions.MatrixPositions;

public interface IMatrixPosition<T extends Number> {

    static <T extends Number> IMatrixPosition<T> create(T x, T y) {
        return new MatrixPosition<>(x, y);
    }

    T getX();
    IMatrixPosition<T> setX(T x);

    T getY();
    IMatrixPosition<T> setY(T y);

    IMatrixPosition<T> set(T x, T y);

}
