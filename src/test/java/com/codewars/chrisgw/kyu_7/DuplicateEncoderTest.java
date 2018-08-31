package com.codewars.chrisgw.kyu_7;

import org.junit.Test;

import static org.junit.Assert.*;


public class DuplicateEncoderTest {


    @Test
    public void test() {
        assertEquals(")()())()(()()(", DuplicateEncoder.encode("Prespecialized"));
        assertEquals("))))())))", DuplicateEncoder.encode("   ()(   "));
    }

    @Test
    public void test2() {
        assertEquals("(((", DuplicateEncoder.encode("din"));
    }

    @Test
    public void test3() {
        assertEquals("()()()", DuplicateEncoder.encode("recede"));
    }

    @Test
    public void test4() {
        assertEquals(")())())", DuplicateEncoder.encode("Success"));
    }

}