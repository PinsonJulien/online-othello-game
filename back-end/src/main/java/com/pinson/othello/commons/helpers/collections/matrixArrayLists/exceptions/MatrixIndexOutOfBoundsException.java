package com.pinson.othello.commons.helpers.collections.matrixArrayLists.exceptions;

public class MatrixIndexOutOfBoundsException extends Exception {

    public MatrixIndexOutOfBoundsException() {
        super("Matrix index out of bounds.");
    }

    public MatrixIndexOutOfBoundsException(String message) {
        super(message);
    }
}
