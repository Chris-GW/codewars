package com.codewars.chrisgw.algorithms.kyu_6;

import java.util.Arrays;


/**
 * #Find the missing letter
 * <p>
 * Write a method that takes an array of consecutive (increasing) letters as input and that returns the missing letter in the array.
 * <p>
 * You will always get an valid array. And it will be always exactly one letter be missing. The length of the array will always be at least 2.<br>
 * The array will always contain letters in only one case.
 * <p>
 * Example:
 * ```
 * ['a','b','c','d','f'] -> 'e'
 * ['O','Q','R','S'] -> 'P'
 * ```
 * <p>
 * (Use the English alphabet with 26 letters!)
 * <p>
 * Have fun coding it and please don't forget to vote and rank this kata! :-)
 * <p>
 * I have also created other katas. Take a look if you enjoyed this kata!
 */
public class FindTheMissingLetter {

    public static char findMissingLetter(char[] array) {
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException(
                    "Expect non null with at least 2 chars, but got: " + Arrays.toString(array));
        }
        for (int i = 1; i < array.length; i++) {
            int currentChar = array[i];
            int expectedChar = array[i - 1] + 1;
            if (currentChar != expectedChar) {
                return (char) expectedChar;
            }
        }
        int lastChar = array[array.length - 1];
        return (char) (lastChar + 1);
    }

}
