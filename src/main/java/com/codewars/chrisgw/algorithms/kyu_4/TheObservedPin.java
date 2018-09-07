package com.codewars.chrisgw.algorithms.kyu_4;

import java.util.*;
import java.util.stream.IntStream;


/**
 * Alright, detective, one of our colleagues successfully observed our target person, Robby the robber. We followed him to a secret warehouse, where we assume to find all the stolen stuff. The door to this warehouse is secured by an electronic combination lock. Unfortunately our spy isn't sure about the PIN he saw, when Robby entered it.
 * <p>
 * The keypad has the following layout:
 * ```
 * ┌───┬───┬───┐
 * │ 1 │ 2 │ 3 │
 * ├───┼───┼───┤
 * │ 4 │ 5 │ 6 │
 * ├───┼───┼───┤
 * │ 7 │ 8 │ 9 │
 * └───┼───┼───┘
 * │ 0 │
 * └───┘
 * ```
 * He noted the PIN `1357`, but he also said, it is possible that each of the digits he saw could actually be another adjacent digit (horizontally or vertically, but not diagonally). E.g. instead of the `1` it could also be the `2` or `4`. And instead of the `5` it could also be the `2`, `4`, `6` or `8`.
 * <p>
 * He also mentioned, he knows this kind of locks. You can enter an unlimited amount of wrong PINs, they never finally lock the system or sound the alarm. That's why we can try out all possible (*) variations.
 * <p>
 * \* possible in sense of: the observed PIN itself and all variations considering the adjacent digits
 * <p>
 * Can you help us to find all those variations? It would be nice to have a function, that returns an array (or a list in Java and C#)  of all variations for an observed PIN with a length of 1 to 8 digits. We could name the function `getPINsDethFirstSearch` (`get_pins` in python, `GetPINs` in C#). But please note that all PINs, the observed one and also the results, must be strings, because of potentially leading '0's. We already prepared some test cases for you.
 * <p>
 * Detective, we count on you!
 */
public class TheObservedPin {

    private static int[][] keypad = new int[][] { //
            new int[] { 1, 2, 3 }, //
            new int[] { 4, 5, 6 }, //
            new int[] { 7, 8, 9 }, //
            new int[] { -1, 0, -1 }, //
    };

    public static List<String> getPINs(String observed) {
        return getPINsDethFirstSearch(new StringBuilder(observed), 0);
    }

    private static List<String> getPINsDethFirstSearch(StringBuilder observedPin, int numberIndex) {
        if (numberIndex >= observedPin.length()) {
            return Collections.singletonList(observedPin.toString());
        }
        List<String> possiblePINs = new LinkedList<>();
        int observedNumber = observedPin.charAt(numberIndex) - '0';
        possibleAdjecentNumbers(observedNumber).forEach(possibleNumber -> {
            observedPin.setCharAt(numberIndex, Character.forDigit(possibleNumber, 10));
            possiblePINs.addAll(getPINsDethFirstSearch(observedPin, numberIndex + 1));
            observedPin.setCharAt(numberIndex, Character.forDigit(observedNumber, 10));
        });
        return possiblePINs;
    }

    private static Set<Integer> possibleAdjecentNumbers(int number) {
        for (int row = 0; row < keypad.length; row++) {
            for (int column = 0; column < keypad[row].length; column++) {
                if (keypad[row][column] == number) {
                    return possibleAdjecentNumbers(row, column);
                }
            }
        }
        return Collections.emptySet();
    }

    private static Set<Integer> possibleAdjecentNumbers(int row, int column) {
        Set<Integer> possibleAdjecentNumbers = new HashSet<>(4);
        IntStream.rangeClosed(-1, 1)
                .map(rowOffset -> row + rowOffset)
                .filter(rowIndex -> isValidKeypadPosition(rowIndex, column))
                .map(rowIndex -> keypad[rowIndex][column])
                .forEach(possibleAdjecentNumbers::add);
        IntStream.rangeClosed(-1, 1)
                .map(columnOffset -> column + columnOffset)
                .filter(columnIndex -> isValidKeypadPosition(row, columnIndex))
                .map(columnIndex -> keypad[row][columnIndex])
                .forEach(possibleAdjecentNumbers::add);
        return possibleAdjecentNumbers;
    }

    private static boolean isValidKeypadPosition(int rowIndex, int columnIndex) {
        boolean isValidKeypadRow = 0 <= rowIndex && rowIndex < keypad.length;
        return isValidKeypadRow && 0 <= columnIndex && columnIndex < keypad[rowIndex].length
                && keypad[rowIndex][columnIndex] >= 0;
    }

}
