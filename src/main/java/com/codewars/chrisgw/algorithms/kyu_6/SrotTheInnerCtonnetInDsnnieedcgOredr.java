package com.codewars.chrisgw.algorithms.kyu_6;

/**
 * #Srot the inner ctnnoet in dsnnieedcg oredr
 * <p>
 * You have to sort the inner content of every word of a string in descending order.<br>
 * The inner content is the content of a word without first and the last char.
 * <p>
 * Some examples:
 * ```
 * "sort the inner content in descending order" -> "srot the inner ctonnet in dsnnieedcg oredr"
 * "wait for me" -> "wiat for me"
 * "this kata is easy" -> "tihs ktaa is esay"
 * ```
 * The string will never be null and will never be empty.<br>
 * It will contain only lowercase-letters and whitespaces.<br><br>
 * In C++ the string is always 0-terminated.
 * <br><br><br>
 * Have fun coding it and please don't forget to vote and rank this kata! :-)
 * <p>
 * I have also created other katas. Take a look if you enjoyed this kata!
 */
public class SrotTheInnerCtonnetInDsnnieedcgOredr {


    public static String sortTheInnerContent(String words) {
        StringBuilder sb = new StringBuilder(words);
        for (int i = 0; i < sb.length(); i++) {
            int wordEndIndex = sb.indexOf(" ", i);
            if (wordEndIndex < 0) { // last word has no following " "
                wordEndIndex = sb.length();
            }
            int innerWordStartIndex = i + 1;
            int innerWordEndIndex = wordEndIndex - 1;
            reverseSortSubChars(sb, innerWordStartIndex, innerWordEndIndex);
            i = wordEndIndex;
        }
        return sb.toString();
    }

    public static void reverseSortSubChars(StringBuilder sb, int innerWordStartIndex, int innerWordEndIndex) {
        // simple insertion sort
        for (int i = innerWordStartIndex; i < innerWordEndIndex - 1; i++) {
            for (int j = i + 1; j < innerWordEndIndex; j++) {
                if (sb.charAt(i) < sb.charAt(j)) {
                    char temp = sb.charAt(i);
                    sb.setCharAt(i, sb.charAt(j));
                    sb.setCharAt(j, temp);
                }
            }
        }
    }

}
