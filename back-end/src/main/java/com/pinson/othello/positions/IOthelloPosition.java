package com.pinson.othello.positions;

import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.positions.exceptions.InvalidStandardNotationException;

import java.util.regex.Pattern;

public interface IOthelloPosition extends IMatrixPosition<Integer> {

    static IOthelloPosition create(Integer row, Integer column) throws NonPositiveValueException {
        return new OthelloPosition(row, column);
    }

    static IOthelloPosition create(String standardNotation) throws InvalidStandardNotationException {
        return new OthelloPosition(standardNotation);
    }

    static IOthelloPosition create(IMatrixPosition<Integer> position) throws NonPositiveValueException {
        return new OthelloPosition(position.getY(), position.getX());
    }

    static boolean isStandardNotation(String standardNotation) {
        Pattern pattern = Pattern.compile("^([A-Z]+)([1-9]\\d*)$");
        return pattern.matcher(standardNotation).matches();
    }

    static boolean isAlgebraicNotation(String algebraicNotation) {
        return IOthelloPosition.isStandardNotation(algebraicNotation);
    }

    static String toStandardNotation(IOthelloPosition position) throws NonPositiveValueException {
        int column = position.getColumn();
        int row = position.getRow();

        if (column < 0)
            throw new NonPositiveValueException("The column of the position cannot be converted to standard notation because it is negative.");
        if (row < 0)
            throw new NonPositiveValueException("The row of the position cannot be converted to standard notation because it is negative.");

        StringBuilder notation = new StringBuilder();

        if (column == 0)
            notation.append('A');
        else {
            int columnLength = (int) Math.floor(Math.log(column) / Math.log(26)) + 1;
            char[] letters = new char[columnLength];
            for (int i = columnLength - 1; i >= 0; i--) {
                letters[i] = (char) (column % 26 + 'A');
                column /= 26;
            }

            notation.append(letters);
        }

        notation.append(row + 1);

        return notation.toString();
    }

    static String toAlgebraicNotation(IOthelloPosition position) throws NonPositiveValueException {
        return IOthelloPosition.toStandardNotation(position);
    }

    Integer getRow();
    IOthelloPosition setRow(Integer row);
    Integer getColumn();
    IOthelloPosition setColumn(Integer column);

    String getStandardNotation();
    String getAlgebraicNotation();

    IOthelloPosition setPosition(Integer row, Integer column) throws NonPositiveValueException;
    IOthelloPosition setPosition(String standardNotation) throws InvalidStandardNotationException;
}
