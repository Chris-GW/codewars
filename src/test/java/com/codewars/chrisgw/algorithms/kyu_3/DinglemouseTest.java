package com.codewars.chrisgw.algorithms.kyu_3;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class DinglemouseTest {

    @Test
    public void testUp() {
        final int[][] queues = { //
                new int[0], // G
                new int[0], // 1
                new int[] { 5, 5, 5 }, // 2
                new int[0], // 3
                new int[0], // 4
                new int[0], // 5
                new int[0], // 6
        };
        final int[] result = Dinglemouse.theLift(queues, 5);
        assertArrayEquals(new int[] { 0, 2, 5, 0 }, result);
    }

    @Test
    public void testDown() {
        final int[][] queues = { //
                new int[0], // G
                new int[0], // 1
                new int[] { 1, 1 }, // 2
                new int[0], // 3
                new int[0], // 4
                new int[0], // 5
                new int[0], // 6
        };
        final int[] result = Dinglemouse.theLift(queues, 5);
        assertArrayEquals(new int[] { 0, 2, 1, 0 }, result);
    }

    @Test
    public void testUpAndUp() {
        final int[][] queues = { //
                new int[0], // G
                new int[] { 3 }, // 1
                new int[] { 4 }, // 2
                new int[0], // 3
                new int[] { 5 }, // 4
                new int[0], // 5
                new int[0], // 6
        };
        final int[] result = Dinglemouse.theLift(queues, 5);
        assertArrayEquals(new int[] { 0, 1, 2, 3, 4, 5, 0 }, result);
    }

    @Test
    public void testDownAndDown() {
        final int[][] queues = { //
                new int[0], // G
                new int[] { 0 }, // 1
                new int[0], // 2
                new int[0], // 3
                new int[] { 2 }, // 4
                new int[] { 3 }, // 5
                new int[0], // 6
        };
        final int[] result = Dinglemouse.theLift(queues, 5);
        assertArrayEquals(new int[] { 0, 5, 4, 3, 2, 1, 0 }, result);
    }


    @Test
    public void testTrickyQueues() {
        final int[][] queues = { //
                new int[] {}, // G
                new int[] { 0, 0, 0, 6 }, // 1
                new int[] {}, // 2
                new int[] {}, // 3
                new int[] {}, // 4
                new int[] { 6, 6, 0, 0, 0, 6 }, // 5
                new int[] {} // 6
        };
        final int[] result = Dinglemouse.theLift(queues, 5);
        assertEquals(result.length, 9);
    }

    @Test
    public void testLiftFullUp() {
        final int[][] queues = { //
                new int[] { 3, 3, 3, 3, 3, 3 }, // G
                new int[] {}, // 1
                new int[] {}, // 2
                new int[] {}, // 3
                new int[] {}, // 4
                new int[] {}, // 5
                new int[] {}, // 6
        };
        final int[] result = Dinglemouse.theLift(queues, 5);
        assertEquals(result.length, 5);
    }

    @Test
    public void testEmpty() {
        final int[][] queues = { //
                new int[] {}, // G
                new int[] {}, // 1
                new int[] {}, // 2
                new int[] {}, // 3
                new int[] {}, // 4
                new int[] {}, // 5
                new int[] {}, // 6
        };
        final int[] result = Dinglemouse.theLift(queues, 5);
        assertEquals(result.length, 1);
    }

}
