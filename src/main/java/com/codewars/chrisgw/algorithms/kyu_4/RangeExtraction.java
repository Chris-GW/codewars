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

    public static String rangeExtraction(int[] values) {
        if (values == null || values.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        int rangeStartIndex = 0;
        int rangeEndIndex = 0;
        while (++rangeEndIndex < values.length) {
            int rangeWidth = rangeEndIndex - rangeStartIndex;
            boolean isValueInRange = values[rangeStartIndex] + rangeWidth == values[rangeEndIndex];
            if (!isValueInRange) {
                appendRangeExtraction(values, rangeStartIndex, rangeEndIndex, sb);
                rangeStartIndex += rangeWidth;
            }
        }
        appendRangeExtraction(values, rangeStartIndex, values.length, sb);
        sb.deleteCharAt(sb.length() - 1); // delete last ','
        return sb.toString();
    }

    private static void appendRangeExtraction(int[] values, int rangeStartIndex, int rangeEndIndex, StringBuilder sb) {
        int rangeWidth = rangeEndIndex - rangeStartIndex;
        if (rangeWidth < 3) {
            for (int i = rangeStartIndex; i < rangeEndIndex; i++) {
                sb.append(values[i]).append(',');
            }
        } else {
            int rangeStartValue = values[rangeStartIndex];
            int rangeEndValue = values[rangeEndIndex - 1];
            sb.append(rangeStartValue).append('-').append(rangeEndValue).append(',');
        }
    }

}
