package com.codewars.chrisgw.algorithms.kyu_3;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class MakeASpiralTest {


    @Test
    public void test1() {
        assertArrayEquals(MakeASpiral.spiralize(1), new int[][] { { 1 } });
    }

    @Test
    public void test2() {
        assertArrayEquals(MakeASpiral.spiralize(2), //
                new int[][] { //
                        { 1, 1 }, //
                        { 0, 1 } //
                }); //
    }

    @Test
    public void test3() {
        assertArrayEquals(MakeASpiral.spiralize(3), //
                new int[][] { //
                        { 1, 1, 1 }, //
                        { 0, 0, 1 }, //
                        { 1, 1, 1 } //
                }); //
    }

    @Test
    public void test5() {
        assertArrayEquals(MakeASpiral.spiralize(5), //
                new int[][] { //
                        { 1, 1, 1, 1, 1 }, //
                        { 0, 0, 0, 0, 1 }, //
                        { 1, 1, 1, 0, 1 }, //
                        { 1, 0, 0, 0, 1 }, //
                        { 1, 1, 1, 1, 1 } //
                }); //
    }

    @Test
    public void test8() {
        assertArrayEquals(MakeASpiral.spiralize(8), //
                new int[][] { //
                        { 1, 1, 1, 1, 1, 1, 1, 1 }, //
                        { 0, 0, 0, 0, 0, 0, 0, 1 }, //
                        { 1, 1, 1, 1, 1, 1, 0, 1 }, //
                        { 1, 0, 0, 0, 0, 1, 0, 1 }, //
                        { 1, 0, 1, 1, 0, 1, 0, 1 }, //
                        { 1, 0, 1, 1, 1, 1, 0, 1 }, //
                        { 1, 0, 0, 0, 0, 0, 0, 1 }, //
                        { 1, 1, 1, 1, 1, 1, 1, 1 }, //
                }); //
    }

}
