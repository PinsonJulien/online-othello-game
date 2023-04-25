package com.pinson.gameoflife.commons.helpers.positions;

public interface IPosition<T extends Number> {
    T getX();
    IPosition<T> setX(T x);

    IPosition<T> set(T x);
}
