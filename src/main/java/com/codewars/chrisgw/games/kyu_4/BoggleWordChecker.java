package com.codewars.chrisgw.games.kyu_4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Write a function that determines whether a string is a valid guess in a Boggle board, as per the rules of Boggle. A Boggle board is a 2D array of individual characters, e.g.:
 * Valid guesses are strings which can be formed by connecting adjacent cells (horizontally, vertically, or diagonally) without re-using any previously used cells.
 * <p>
 * For example, in the above board "BINGO", "LINGO", and "ILNBIA" would all be valid guesses, while "BUNGIE", "BINS", and "SINUS" would not.
 * <p>
 * Your function should take two arguments (a 2D array and a string) and return true or false depending on whether the string is found in the array as per Boggle rules.
 * <p>
 * Test cases will provide various array and string sizes (squared arrays up to 150x150 and strings up to 150 uppercase letters). You do not have to check whether the string is a real word or not, only if it's a valid guess.
 */
public class BoggleWordChecker {

    private final String word;
    private BoggleWordField[][] board;
    private Boolean isValid = null;


    public BoggleWordChecker(final char[][] board, final String word) {
        this.board = new BoggleWordField[board.length][];
        for (int row = 0; row < board.length; row++) {
            this.board[row] = new BoggleWordField[board[row].length];
            for (int column = 0; column < board[row].length; column++) {
                char letter = board[row][column];
                this.board[row][column] = new BoggleWordField(row, column, letter);
            }
        }
        this.word = word;
    }

    private BoggleWordField getBoggleField(int row, int column) {
        boolean isValidRowIndex = 0 <= row && row < board.length;
        if (isValidRowIndex && 0 <= column && column < board.length) {
            return board[row][column];
        } else {
            return null;
        }
    }


    private Stream<BoggleWordField> allFields() {
        return Arrays.stream(board).flatMap(Arrays::stream);
    }


    public boolean check() {
        if (isValid == null) {
            isValid = word != null && checkBoggleWord(0, null);
        }
        return isValid;
    }

    private boolean checkBoggleWord(int wordIndex, BoggleWordField currentField) {
        if (wordIndex >= word.length()) {
            return true;
        }
        char nextLetter = word.charAt(wordIndex);
        for (BoggleWordField nextField : findUnusedAndAdjecentFieldsWithLetter(nextLetter, currentField)) {
            nextField.used = true;
            if (checkBoggleWord(wordIndex + 1, nextField)) {
                return true;
            }
            nextField.used = false;
        }
        return false;
    }

    private List<BoggleWordField> findUnusedAndAdjecentFieldsWithLetter(char nextLetter, BoggleWordField currentField) {
        if (currentField == null) {
            return allFields().filter(field -> field.letter == nextLetter).collect(Collectors.toList());
        }
        List<BoggleWordField> possibleNextFields = new ArrayList<>(9);
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                if (rowOffset == 0 && columnOffset == 0) {
                    continue;
                }
                int row = currentField.row + rowOffset;
                int column = currentField.column + columnOffset;
                BoggleWordField field = getBoggleField(row, column);
                if (field != null && field.letter == nextLetter && !field.used) {
                    possibleNextFields.add(field);
                }
            }
        }
        return possibleNextFields;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BoggleWordChecker [").append(board.length).append("] for word(");
        sb.append(word.length()).append("): ").append(word).append("\n");
        for (int row = 0; row < board.length; row++) {
            sb.append('[');
            for (int column = 0; column < board.length; column++) {
                BoggleWordField boggleWordField = getBoggleField(row, column);
                if (boggleWordField.used) {
                    sb.append(Character.toLowerCase(boggleWordField.letter));
                } else {
                    sb.append(Character.toUpperCase(boggleWordField.letter));
                }
                sb.append(", ");
            }
            sb.replace(sb.length() - 2, sb.length(), "]\n");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }


    private static class BoggleWordField {

        int row;
        int column;
        char letter;
        boolean used;

        public BoggleWordField(int row, int column, char letter) {
            this.row = row;
            this.column = column;
            this.letter = letter;
            this.used = false;
        }

        public boolean isAdjacentField(BoggleWordField otherField) {
            int columnDifference = otherField.column - this.column;
            int rowDifference = otherField.row - this.row;
            boolean isAdjacentColumn = -1 <= columnDifference && columnDifference <= 1;
            return isAdjacentColumn && -1 <= rowDifference && rowDifference <= 1;
        }

    }

}
