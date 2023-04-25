package com.pinson.gameoflife.commons.helpers.positions;

public class Position<T extends Number> implements IPosition<T> {
    private T x;

    public Position(T x) {
        this.x = x;
    }

    public T getX() {
        return x;
    }

    public IPosition<T> setX(T x) {
        this.x = x;
        return this;
    }

    public IPosition<T> set(T x) {
        return setX(x);
    }
}
