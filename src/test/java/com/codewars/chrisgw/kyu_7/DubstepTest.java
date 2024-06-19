package com.codewars.chrisgw.kyu_7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class DubstepTest {


    @Test
    public void Test1() {
        String actual = new Dubstep().SongDecoder("WUBWUBABCWUB");
        assertEquals("ABC", actual);
    }
    
    @Test
    public void Test2() {
        String actual = new Dubstep().SongDecoder("RWUBWUBWUBLWUB");
        assertEquals("R L", actual);
    }

}