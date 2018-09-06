package com.codewars.chrisgw.algorithms.kyu_4;

/**
 * A format for expressing an ordered list of integers is to use a comma separated list of either
 * <p>
 * * individual integers
 * * or a range of integers denoted by the starting integer separated from the end integer in the range by a dash, '-'. The range includes all integers in the interval including both endpoints.  It is not considered a range unless it spans at least 3 numbers. For example ("12, 13, 15-17")
 * <p>
 * Complete the solution  so that it takes a list of integers in increasing order and returns a correctly formatted string in the range format.
 * <p>
 * **Example:**
 * <p>
 * *Courtesy of rosettacode.org*
 */
public class RangeExtraction {

    public static String rangeExtraction(int[] arr) {
        StringBuilder sb = new StringBuilder();
        int rangeStartIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            int value = arr[i];
            int rangeWidth = i - rangeStartIndex;
            int rangeStartValue = arr[rangeStartIndex];
            boolean isValueInRange = rangeStartValue + rangeWidth == value;
            if (!isValueInRange && rangeWidth < 3) {
                for (int j = rangeStartIndex; j < i; j++) {
                    sb.append(arr[j]).append(',');
                }
                rangeStartIndex = i;
            } else if (!isValueInRange) {
                int rangeEndValue = arr[i - 1];
                sb.append(rangeStartValue).append('-').append(rangeEndValue).append(',');
                rangeStartIndex = i;
            }
        }
        int rangeWidth = arr.length - rangeStartIndex;
        if (rangeWidth < 3) {
            for (int j = rangeStartIndex; j < arr.length; j++) {
                sb.append(arr[j]).append(',');
            }
        } else {
            int rangeEndValue = arr[arr.length - 1];
            sb.append(arr[rangeStartIndex]).append('-').append(rangeEndValue).append(',');
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1); // delete last ','
        }
        return sb.toString();
    }

}
