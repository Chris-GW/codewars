package com.codewars.chrisgw.algorithms.kyu_3;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class BattleshipFieldValidatorTest {


    private static int[][] battleField = { //
            { 1, 0, 0, 0, 0, 1, 1, 0, 0, 0 }, //
            { 1, 0, 1, 0, 0, 0, 0, 0, 1, 0 }, //
            { 1, 0, 1, 0, 1, 1, 1, 0, 1, 0 }, //
            { 1, 0, 0, 0, 0, 0, 0, 0, 0, 0 }, //
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, //
            { 0, 0, 0, 0, 1, 1, 1, 0, 0, 0 }, //
            { 0, 0, 0, 0, 0, 0, 0, 0, 1, 0 }, //
            { 0, 0, 0, 1, 0, 0, 0, 0, 0, 0 }, //
            { 0, 0, 0, 0, 0, 0, 0, 1, 0, 0 }, //
            { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 } }; //

    @Test
    public void SampleTest() {
        assertEquals(true, BattleshipFieldValidator.fieldValidator(battleField));
    }

}
