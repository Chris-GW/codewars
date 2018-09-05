package com.codewars.chrisgw.algorithms.kyu_6;

import org.junit.Test;

import static com.codewars.chrisgw.algorithms.kyu_6.FindTheMissingLetter.findMissingLetter;
import static org.junit.Assert.assertEquals;


public class FindTheMissingLetterTest {

    @Test
    public void exampleTests() {
        assertEquals('e', findMissingLetter(new char[] { 'a', 'b', 'c', 'd', 'f' }));
        assertEquals('P', findMissingLetter(new char[] { 'O', 'Q', 'R', 'S' }));
    }

}
