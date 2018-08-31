package com.codewars.chrisgw.kyu_7;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Dubstep {

    public static String SongDecoder(String song) {
        StringBuffer sb = new StringBuffer();
        Pattern remixWordPattern = Pattern.compile("(WUB)+");
        Matcher remixWordMatcher = remixWordPattern.matcher(song);
        while (remixWordMatcher.find()) {
            remixWordMatcher.appendReplacement(sb, " ");
        }
        remixWordMatcher.appendTail(sb);
        return sb.toString().trim();
    }

}