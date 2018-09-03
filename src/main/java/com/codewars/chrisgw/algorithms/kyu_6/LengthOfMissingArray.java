package com.codewars.chrisgw.algorithms.kyu_6;

import java.util.Arrays;
import java.util.PrimitiveIterator.OfInt;


/**
 * You get an array of arrays.<br>
 * If you sort the arrays by their length, you will see, that their length-values are consecutive.<br>
 * But one array is missing!<br>
 * <br><br>
 * You have to write a method, that return the length of the missing array.<br>
 * ```
 * Example:
 * [[1, 2], [4, 5, 1, 1], [1], [5, 6, 7, 8, 9]] --> 3
 * ```
 * <br>
 * <p>
 * If the array of arrays is null/nil or empty, the method should return 0.<br>
 * <p>
 * When an array in the array is null or empty, the method should return 0 too!<br>
 * There will always be a missing element and its length will be always between the given arrays.
 * <br><br>
 * Have fun coding it and please don't forget to vote and rank this kata! :-)<br>
 * <br>
 * I have created other katas. Have a look if you like coding and challenges.
 */
public class LengthOfMissingArray {

    public static int getLengthOfMissingArray(Object[][] arrayOfArrays) {
        if (arrayOfArrays == null) {
            return 0;
        }
        OfInt arrayLengthItarator = Arrays.stream(arrayOfArrays)
                .mapToInt(array -> array != null ? array.length : 0)
                .distinct()
                .sorted()
                .iterator();
        int firstArrayLength = 0;
        if (arrayLengthItarator.hasNext()) {
            firstArrayLength = arrayLengthItarator.nextInt();
        }
        if (firstArrayLength == 0) {
            return 0; // why?
        }
        for (int arrayLength = firstArrayLength + 1; arrayLengthItarator.hasNext(); arrayLength++) {
            if (arrayLength != arrayLengthItarator.nextInt()) {
                return arrayLength;
            }
        }
        return 0;
    }

}
