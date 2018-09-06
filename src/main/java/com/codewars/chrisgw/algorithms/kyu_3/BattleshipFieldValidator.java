package com.codewars.chrisgw.algorithms.kyu_3;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;
import java.util.stream.Stream.Builder;


/**
 * <p>Write a method that takes a field for well-known board game "Battleship" as an argument and returns true if it has a valid disposition of ships, false otherwise. Argument is guaranteed to be 10*10 two-dimension array. Elements in the array are numbers, 0 if the cell is free and 1 if occupied by ship.</p>
 * <p><b>Battleship</b> (also Battleships or Sea Battle) is a guessing game for two players.
 * Each player has a 10x10 grid containing several "ships" and objective is to destroy enemy's forces by targetting individual cells on his field. The ship occupies one or more cells in the grid. Size and number of ships may differ from version to version. In this kata we will use Soviet/Russian version of the game.</p>
 * <img src="http://i.imgur.com/IWxeRBV.png"><br>
 * Before the game begins, players set up the board and place the ships accordingly to the following rules:<br>
 * <ul>
 * <li>There must be single battleship (size of 4 cells), 2 cruisers (size 3), 3 destroyers (size 2) and 4 submarines (size 1). Any additional ships are not allowed, as well as missing ships.</li>
 * <li>Each ship must be a straight line, except for submarines, which are just single cell.<br>
 * <img src="http://i.imgur.com/FleBpT9.png"></li>
 * <li>The ship cannot overlap or be in contact with any other ship, neither by edge nor by corner.<br>
 * <img src="http://i.imgur.com/MuLvnug.png"></li>
 * </ul>
 * This is all you need to solve this kata. If you're interested in more information about the game, visit <a href="http://en.wikipedia.org/wiki/Battleship_(game)">this link</a>.
 */
public class BattleshipFieldValidator {

    private BattleshipField[][] field;


    public BattleshipFieldValidator(int[][] field) {
        this.field = new BattleshipField[field.length][field.length];
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[row].length; column++) {
                boolean containsShip = field[row][column] == 1;
                this.field[row][column] = new BattleshipField(row, column, containsShip);
            }
        }
    }


    public static boolean fieldValidator(int[][] field) {
        return new BattleshipFieldValidator(field).isValid();
    }

    public boolean isValid() {
        return false;
    }


    public BattleshipField getField(int row, int column) {
        if (0 <= row && row < field.length && 0 <= column && column < field[row].length) {
            return field[row][column];
        } else {
            return null;
        }
    }


    class BattleshipField {

        int row;
        int column;
        boolean containsShip;
        boolean hasNearbyShip;


        public BattleshipField(int row, int column, boolean containsShip) {
            this.row = row;
            this.column = column;
            this.containsShip = containsShip;
            this.hasNearbyShip = false;
        }

        public Stream<BattleshipField> adjecentFields() {
            Builder<BattleshipField> adjecentFieldsBuilder = Stream.builder();
            for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
                for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                    if (rowOffset == 0 && columnOffset == 0) {
                        continue;
                    }
                    adjecentFieldsBuilder.add(getField(row + rowOffset, column + columnOffset));
                }
            }
            return adjecentFieldsBuilder.build().filter(Objects::nonNull);
        }

        public Set<BattleshipField> shipFields() {
            Set<BattleshipField> battleshipFields = new HashSet<>(4);
            BattleshipField nextField = this;
            while (nextField != null) {
                battleshipFields.add(nextField);
                nextField = nextField.adjecentFields()
                        .filter(battleshipField -> battleshipField.containsShip)
                        .filter(battleshipField -> !battleshipFields.contains(battleshipField))
                        .findFirst()
                        .orElse(null);
            }
            return battleshipFields;
        }


        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof BattleshipField))
                return false;

            BattleshipField that = (BattleshipField) o;

            if (row != that.row)
                return false;
            if (column != that.column)
                return false;
            if (containsShip != that.containsShip)
                return false;
            return hasNearbyShip == that.hasNearbyShip;
        }

        @Override
        public int hashCode() {
            int result = row;
            result = 31 * result + column;
            result = 31 * result + (containsShip ? 1 : 0);
            result = 31 * result + (hasNearbyShip ? 1 : 0);
            return result;
        }

    }


    enum BattleshipType {

        BATTLESHIP(4), CRUISER(3), DESTROYER(2), SUBMARINE(1);

        int shipLength;

        BattleshipType(int shipLength) {
            this.shipLength = shipLength;
        }

        public static BattleshipType forLength(int shipLength) {
            for (BattleshipType battleshipType : values()) {
                if (battleshipType.shipLength == shipLength) {
                    return battleshipType;
                }
            }
            return null;
        }
    }


}
