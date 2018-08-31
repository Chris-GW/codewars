package com.codewars.chrisgw.kyu_7;

import java.util.Map;
import java.util.stream.Collectors;


public class DuplicateEncoder {

    static String encode(String word) {
        word = word.toLowerCase();

        Map<Integer, Long> groupByCharacterOccurence = word.chars()
                .boxed()
                .collect(Collectors.groupingBy(o -> o, Collectors.counting()));

        StringBuilder sb = new StringBuilder(word.length());
        for (int i = 0; i < word.length(); i++) {
            int currentChar = word.charAt(i);
            if (groupByCharacterOccurence.get(currentChar) > 1) {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }
        return sb.toString();
    }

}
