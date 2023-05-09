package com.pinson.othello.commons.entities.positions.MatrixPositions;

public class MatrixPosition<T extends Number> implements IMatrixPosition<T> {
    private T x;
    private T y;

    /**
     * Instantiates a new MatrixPosition with x and y.
     *
     * @param x the X position of type T.
     * @param y the Y position of type T.
     */
    public MatrixPosition(T x, T y) {
        this.set(x, y);
    }

    /**
     * Get the X position.
     *
     * @return the X position of type T.
     */
    public T getX() {
        return this.x;
    }

    /**
     * Set the X position.
     *
     * @param x the X position of type T.
     * @return the IMatrixPosition<T> object.
     */
    public IMatrixPosition<T> setX(T x) {
        this.x = x;

        return this;
    }

    /**
     * Get the Y position.
     *
     * @return the Y position of type T.
     */
    public T getY() {
        return y;
    }

    /**
     * Set the Y position.
     *
     * @param y the Y position of type T.
     * @return the IMatrixPosition<T> object.
     */
    public IMatrixPosition<T> setY(T y) {
        this.y = y;

        return this;
    }

    /**
     * Set the position.
     *
     * @param x the X position of type T.
     * @param y the Y position of type T.
     * @return the IMatrixPosition<T> object.
     */
    public IMatrixPosition<T> set(T x, T y) {
        return this.setX(x).setY(y);
    }
}
