package com.codewars.chrisgw.reference.kyu_5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;


public class DirectionsReductionTest {

    @Test
    public void testSimpleDirReduc1() {
        String[] expected = {"WEST"};
        String[] actual = DirectionsReduction.dirReduc(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH", "WEST"});
        assertArrayEquals(expected, actual, "\"NORTH\", \"SOUTH\", \"SOUTH\", \"EAST\", \"WEST\", \"NORTH\", \"WEST\"");
    }

    @Test
    public void testSimpleDirReduc2() {
        String[] expected = {};
        String[] actual = DirectionsReduction.dirReduc(new String[]{"NORTH", "SOUTH", "SOUTH", "EAST", "WEST", "NORTH"});
        assertArrayEquals(expected, actual, "\"NORTH\",\"SOUTH\",\"SOUTH\",\"EAST\",\"WEST\",\"NORTH\"");
    }

}
