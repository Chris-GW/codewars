package com.codewars.chrisgw.reference.kyu_4;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;


/**
 * We want to generate all the numbers of three digits that:
 * <p>
 * - the value of adding their corresponding ones(digits) is equal to 10.
 * <p>
 * - their digits are in increasing order (the numbers may have two or more equal contiguous digits)
 * <p>
 * The numbers that fulfill the two above constraints are: ```118, 127, 136, 145, 226, 235, 244, 334```
 * <p>
 * Make a function that receives two arguments:
 * <p>
 * - the sum of digits value
 * <p>
 * - the amount of desired digits for the numbers
 * <p>
 * The function should output an array with three values:
 * [1,2,3]
 * <p>
 * 1 - the total amount of all these possible numbers
 * <p>
 * 2 - the minimum number
 * <p>
 * 3 - the maximum numberwith
 * <p>
 * The example given above should be:
 */
public class HowManyNumbersIii {


    public static List<Long> findAll(final int targetSumDigits, final int numDigits) {
        List<Long> allPossibleNumbers = findAllRecursiveDepthFirstSearch(targetSumDigits, new int[numDigits], 0);
        long size = allPossibleNumbers.size();
        if (size > 0) {
            long min = allPossibleNumbers.stream().min(Long::compareTo).orElse(0L);
            long max = allPossibleNumbers.stream().max(Long::compareTo).orElse(0L);
            return Arrays.asList(size, min, max);
        } else {
            return Collections.emptyList();
        }
    }


    private static List<Long> findAllRecursiveDepthFirstSearch(final int targetSumDigits, int[] digits, int index) {
        int sum = Arrays.stream(digits).sum();
        if (index == digits.length && sum == targetSumDigits) {
            long numberStr = Long.valueOf(Arrays.stream(digits)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining("")));
            return Collections.singletonList(numberStr);
        } else if (index == digits.length || sum > targetSumDigits) {
            return Collections.emptyList();
        }

        int startNum = 1;
        if (index > 0) {
            startNum = digits[index - 1];
        }
        List<Long> results = new LinkedList<>();
        for (int num = startNum; num <= 9; num++) {
            digits[index] = num; // do set number
            results.addAll(findAllRecursiveDepthFirstSearch(targetSumDigits, digits, index + 1));
            digits[index] = 0; // undo set number
        }
        return results;
    }

}
