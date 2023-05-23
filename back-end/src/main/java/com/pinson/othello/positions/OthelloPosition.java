package com.pinson.othello.positions;

import com.pinson.othello.commons.entities.positions.MatrixPositions.IMatrixPosition;
import com.pinson.othello.commons.entities.positions.MatrixPositions.MatrixPosition;
import com.pinson.othello.commons.exceptions.NonPositiveValueException;
import com.pinson.othello.positions.exceptions.InvalidStandardNotationException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OthelloPosition extends MatrixPosition<Integer> implements IOthelloPosition {

    private String standardNotation;

    protected OthelloPosition() {
        super(0, 0);
        this.standardNotation = "A0";
    }

    public OthelloPosition(Integer row, Integer column) throws NonPositiveValueException {
        super(column, row);
        this.standardNotation = IOthelloPosition.toStandardNotation(this);
    }

    /**
     * Instantiates a new OthelloPosition using Standard / Algebraic Notation.
     * Example : A1 : x = 0, y = 0
     * Example : B2 : x = 1, y = 1
     * Example: AA1 : x = 26, y = 0
     *
     * @param standardNotation the standard notation, also known as algebraic notation.
     */
    public OthelloPosition(String standardNotation) throws InvalidStandardNotationException {
        this();
        this.setPosition(standardNotation);
    }

    @Override
    public Integer getRow() {
        return this.getY();
    }

    @Override
    public IOthelloPosition setRow(Integer row) {
        this.setY(row);
        return this;
    }

    @Override
    public Integer getColumn() {
        return this.getX();
    }

    @Override
    public IOthelloPosition setColumn(Integer column) {
        this.setX(column);

        return this;
    }

    @Override
    public String getStandardNotation() {
        return this.standardNotation;
    }

    @Override
    public String getAlgebraicNotation() {
        return this.getStandardNotation();
    }

    @Override
    public IOthelloPosition setPosition(Integer row, Integer column) throws NonPositiveValueException {
        this.setX(column);
        this.setY(row);
        this.standardNotation = IOthelloPosition.toStandardNotation(this);
        return this;
    }

    @Override
    public IOthelloPosition setPosition(String standardNotation) throws InvalidStandardNotationException {
        Pattern pattern = Pattern.compile("^([A-Z]+)([1-9]\\d*)$");
        Matcher matcher = pattern.matcher(standardNotation);

        if (!matcher.matches())
            throw new InvalidStandardNotationException();

        // Convert the string of letters to an integer
        String letters = matcher.group(1);
        int column = 0;
        int lettersLength = letters.length();
        for (int i = 0; i < lettersLength; i++) {
            column += (letters.charAt(i) - 'A') * Math.pow(26, lettersLength - i - 1);
        }

        // Convert the string of numbers to an integer
        String numbers = matcher.group(2);
        int row = Integer.parseInt(numbers) - 1;

        this.setX(column);
        this.setY(row);
        this.standardNotation = standardNotation;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IOthelloPosition position))
            return false;

        if (obj == this)
            return true;

        return (
            super.equals(obj)
            &&
            this.standardNotation.equals(position.getStandardNotation())
        );
    }

    @Override
    public String toString() {
        return (
            "OthelloPosition{" +
            "x=" + this.getX() + ", " +
            "y=" + this.getY() + ", " +
            "standardNotation='" + this.standardNotation + "'"+
            "}"
        );
    }

    @Override
    public int hashCode() {
        return (
            this.getX().hashCode() +
            this.getY().hashCode() +
            this.standardNotation.hashCode()
        );
    }
}
