package com.codewars.chrisgw.games.kyu_4;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class SkyScrapersTest {

    private static final int clues[][] = {{ //
            2, 2, 1, 3, //
            2, 2, 3, 1, //
            1, 2, 2, 3, //
            3, 2, 1, 3  //
    }, { //
            0, 0, 1, 2, //
            0, 2, 0, 0, //
            0, 3, 0, 0, //
            0, 1, 0, 0 //
    }};

    private static final int outcomes[][][] = {{ //
            {1, 3, 4, 2}, //
            {4, 2, 1, 3}, //
            {3, 4, 2, 1}, //
            {2, 1, 3, 4} //
    }, { //
            {2, 1, 4, 3}, //
            {3, 4, 1, 2}, //
            {4, 2, 3, 1}, //
            {1, 3, 2, 4} //
    }};

    @Test
    public void testSolvePuzzle1() {
        assertArrayEquals(SkyScrapers.solvePuzzle(clues[0]), outcomes[0]);
    }

    @Test
    @Disabled
    public void testSolvePuzzle2() {
        assertArrayEquals(SkyScrapers.solvePuzzle(clues[1]), outcomes[1]);
    }

}
