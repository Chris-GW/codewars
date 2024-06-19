package com.codewars.chrisgw.algorithms.kyu_3;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BattleshipFieldValidatorTest {


    private static int[][] battleField = { //
            {1, 0, 0, 0, 0, 1, 1, 0, 0, 0}, //
            {1, 0, 1, 0, 0, 0, 0, 0, 1, 0}, //
            {1, 0, 1, 0, 1, 1, 1, 0, 1, 0}, //
            {1, 0, 0, 0, 0, 0, 0, 0, 0, 0}, //
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, //
            {0, 0, 0, 0, 1, 1, 1, 0, 0, 0}, //
            {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}, //
            {0, 0, 0, 1, 0, 0, 0, 0, 0, 0}, //
            {0, 0, 0, 0, 0, 0, 0, 1, 0, 0}, //
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0}}; //

    @Test
    public void SampleTest() {
        boolean validField = BattleshipFieldValidator.fieldValidator(battleField);
        assertTrue(validField);
    }

}
