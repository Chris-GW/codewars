package com.codewars.chrisgw.games.kyu_4;

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
        return board[row][column];
    }


    private Stream<BoggleWordField> allFields() {
        return Arrays.stream(board).flatMap(Arrays::stream);
    }

    private Stream<BoggleWordField> findUnusedFieldsWithLetter(char letter) {
        return allFields() //
                .filter(field -> field.letter == letter) //
                .filter(field -> !field.used);
    }

    private List<BoggleWordField> findUnusedAndAdjecentFieldsWithLetter(char letter, BoggleWordField currentField) {
        if (currentField != null) {
            return findUnusedFieldsWithLetter(letter) //
                    .filter(currentField::isAdjacentField) //
                    .collect(Collectors.toList());
        } else {
            return findUnusedFieldsWithLetter(letter) //
                    .collect(Collectors.toList());
        }
    }


    public boolean check() {
        return word != null && checkBoggleWord(0, null);
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
