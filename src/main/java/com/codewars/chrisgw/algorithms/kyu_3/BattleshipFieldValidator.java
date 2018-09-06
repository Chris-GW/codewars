package com.codewars.chrisgw.algorithms.kyu_3;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.*;
import java.util.stream.Collectors;
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
        this.field = new BattleshipField[field.length][];
        for (int row = 0; row < field.length; row++) {
            this.field[row] = new BattleshipField[field[row].length];
            for (int column = 0; column < field[row].length; column++) {
                boolean isShipField = field[row][column] == 1;
                if (isShipField) {
                    this.field[row][column] = newShipField(row, column);
                } else if (this.field[row][column] == null) {
                    this.field[row][column] = newWaterField(row, column);
                }
            }
        }
        assignAdjecentShipFields();
        System.out.println(this.toString());
    }

    private void assignAdjecentShipFields() {
        Set<BattleshipField> shipFields = fields().filter(BattleshipField::isShipField).collect(Collectors.toSet());
        for (BattleshipField shipField : shipFields) {
            if (shipField.shipFields.size() > 1) {
                continue;
            }
            Set<BattleshipField> adjecentShipFields = new HashSet<>(4);
            assignAdjecentShipFields(shipField, adjecentShipFields, true);
            if (adjecentShipFields.size() == 1) {
                adjecentShipFields.clear();
                assignAdjecentShipFields(shipField, adjecentShipFields, false);
            }
            adjecentShipFields.forEach(battleshipField -> battleshipField.shipFields = adjecentShipFields);
        }
    }

    private void assignAdjecentShipFields(BattleshipField currentShipField, Set<BattleshipField> shipFields,
            boolean horizontal) {
        if (!shipFields.add(currentShipField)) {
            return;
        }
        if (horizontal) {
            currentShipField.adjecentHorizontalFields()
                    .filter(BattleshipField::isShipField)
                    .forEach(battleshipField -> assignAdjecentShipFields(battleshipField, shipFields, horizontal));
        } else
            currentShipField.adjecentVerticalFields()
                    .filter(BattleshipField::isShipField)
                    .forEach(battleshipField -> assignAdjecentShipFields(battleshipField, shipFields, horizontal));
    }


    public BattleshipField getField(int row, int column) {
        if (0 <= row && row < field.length && 0 <= column && column < field[row].length) {
            return field[row][column];
        } else {
            return newWaterField(row, column);
        }
    }

    private Stream<BattleshipField> fields() {
        return Arrays.stream(field).flatMap(Arrays::stream);
    }


    public static boolean fieldValidator(int[][] field) {
        return new BattleshipFieldValidator(field).isValid();
    }

    public boolean isValid() {
        Map<BattleshipType, Integer> battleshipTypeToFieldCount = new HashMap<>(BattleshipType.values().length);
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[row].length; column++) {
                BattleshipField currentField = getField(row, column);
                if (currentField.isShipField()) {
                    battleshipTypeToFieldCount.merge(currentField.getBattleshipType(), 1, Integer::sum);
                    boolean hasNoAdjecentOtherShipField = currentField.adjecentFields()
                            .filter(BattleshipField::isShipField)
                            .allMatch(currentField.shipFields::contains);
                    if (!hasNoAdjecentOtherShipField) {
                        System.out.printf("hasNoAdjecentOtherShipField %d-%d%n", currentField.row, currentField.column);
                        return false;
                    }
                }
            }
        }
        return hasExpectedShipTypes(battleshipTypeToFieldCount);
    }

    private boolean hasExpectedShipTypes(Map<BattleshipType, Integer> battleshipTypeToFieldCount) {
        for (BattleshipType battleshipType : BattleshipType.values()) {
            int expectedTypeFieldCount = battleshipType.quantity * battleshipType.shipLength;
            int actialTypeFieldCount = battleshipTypeToFieldCount.getOrDefault(battleshipType, 0);
            if (expectedTypeFieldCount != actialTypeFieldCount) {
                System.out.printf("%s expect %d but was %d%n", //
                        battleshipType, expectedTypeFieldCount, actialTypeFieldCount);
                return false;
            }
        }
        return true;
    }


    private BattleshipField newWaterField(int row, int column) {
        return new BattleshipField(row, column);
    }

    private BattleshipField newShipField(int row, int column) {
        BattleshipField battleshipField = new BattleshipField(row, column);
        battleshipField.shipFields.add(battleshipField);
        return battleshipField;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int row = 0; row < field.length; row++) {
            for (int column = 0; column < field[row].length; column++) {
                sb.append(getField(row, column));
            }
            sb.append("\n");
        }
        return sb.toString();
    }


    class BattleshipField {

        int row;
        int column;
        Set<BattleshipField> shipFields = new HashSet<>(4);


        public BattleshipField(int row, int column) {
            this.row = row;
            this.column = column;
        }


        public boolean isShipField() {
            return shipFields.size() > 0;
        }

        public BattleshipType getBattleshipType() {
            return BattleshipType.forLength(shipFields.size());
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

        public Stream<BattleshipField> adjecentHorizontalFields() {
            return adjecentFields().filter(battleshipField -> battleshipField.row == this.row);
        }

        public Stream<BattleshipField> adjecentVerticalFields() {
            return adjecentFields().filter(battleshipField -> battleshipField.column == this.column);
        }


        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;

            if (!(o instanceof BattleshipField))
                return false;

            BattleshipField that = (BattleshipField) o;
            return new EqualsBuilder().append(row, that.row).append(column, that.column).isEquals();
        }

        @Override
        public int hashCode() {
            return new HashCodeBuilder(17, 37).append(row).append(column).toHashCode();
        }

        @Override
        public String toString() {
            BattleshipType battleshipType = getBattleshipType();
            if (battleshipType == null) {
                return "+";
            }
            switch (battleshipType) {
            case BATTLESHIP:
                return "B";
            case CRUISER:
                return "C";
            case DESTROYER:
                return "D";
            case SUBMARINE:
                return "S";
            default:
                return "+";
            }
        }

    }


    enum BattleshipType {

        BATTLESHIP(4, 1), CRUISER(3, 2), DESTROYER(2, 3), SUBMARINE(1, 4);

        int shipLength;
        int quantity;

        BattleshipType(int shipLength, int quantity) {
            this.shipLength = shipLength;
            this.quantity = quantity;
        }

        public static BattleshipType forLength(int shipLength) {
            for (BattleshipType shipType : values()) {
                if (shipType.shipLength > 0 && shipType.shipLength == shipLength) {
                    return shipType;
                }
            }
            return null;
        }

    }

}
