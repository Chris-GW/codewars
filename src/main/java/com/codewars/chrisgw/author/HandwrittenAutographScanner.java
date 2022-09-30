package com.codewars.chrisgw.author;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;


public class HandwrittenAutographScanner {


    public static String resolveAutograph(List<String> dictionary, String readAutograph) {
        return dictionary.stream().filter(autographMatcher(readAutograph)).sorted().findFirst().orElse(null);
    }

    public static Predicate<String> autographMatcher(final String readAutograph) {
        return name -> {
            boolean wrongLength = readAutograph.length() != name.length();
            if (wrongLength || readAutograph.charAt(0) != name.charAt(0)) {
                return false;
            }
            for (int i = 1; i < readAutograph.length(); i++) {
                LetterClass letterClass = LetterClass.valueOf(name.charAt(i));
                LetterClass readLetterClass = LetterClass.valueOf(readAutograph.charAt(i));
                if (!letterClass.equals(readLetterClass)) {
                    return false;
                }
            }
            return true;
        };
    }


    public enum LetterClass {
        UPPER(new char[] { //
                'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', //
                'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', //
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', //
                'b', 'd', 'h', 'k', 'l', 't', 'f' }), //
        SPACE(new char[] { ' ' }), //
        MIDDLE(new char[] { 'a', 'c', 'e', 'i', 'm', 'n', 'o', 'r', 's', 'u', 'v', 'w', 'x', 'z' }), //
        LOWER(new char[] { 'g', 'j', 'p', 'q', 'y', 'f' });


        private final char[] letters;

        LetterClass(char[] letters) {
            this.letters = letters;
        }

        public static LetterClass valueOf(char letter) {
            return Arrays.stream(LetterClass.values())
                    .filter(letterClass -> letterClass.contains(letter))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("No LetterClass for: '" + letter + "'"));
        }


        public boolean contains(char c) {
            for (char letter : letters) {
                if (letter == c) {
                    return true;
                }
            }
            return false;
        }


    }

}
