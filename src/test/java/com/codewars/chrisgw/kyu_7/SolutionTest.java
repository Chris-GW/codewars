package com.codewars.chrisgw.kyu_7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class SolutionTest {


    @Test
    public void testNormalCondition() {
        int actual = Solution.solveSuperMarketQueue(new int[]{2, 2, 3, 3, 4, 4}, 2);
        assertEquals(9, actual);
    }

    @Test
    public void testEmptyArray() {
        int actual = Solution.solveSuperMarketQueue(new int[]{}, 1);
        assertEquals(0, actual);
    }

    @Test
    public void testSingleTillManyCustomers() {
        int actual = Solution.solveSuperMarketQueue(new int[]{1, 2, 3, 4, 5}, 1);
        assertEquals(15, actual);
    }

}