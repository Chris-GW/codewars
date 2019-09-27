package com.codewars.chrisgw.reference.kyu_5;

import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.codewars.chrisgw.reference.kyu_5.BestTravel.chooseBestSum;
import static org.junit.Assert.*;


public class BestTravelTest {


    @Test
    @Ignore
    public void BasicTests1() {
        System.out.println("****** Basic Tests small numbers******");
        List<Integer> ts = new ArrayList<>(Arrays.asList(50, 55, 56, 57, 58));
        Integer n = chooseBestSum(163, 3, ts);
        assertNotNull(n);
        assertEquals(163, n.intValue());
        ts = Collections.singletonList(50);
        Integer m = chooseBestSum(163, 3, ts);
        assertNull(m);
        ts = Arrays.asList(91, 74, 73, 85, 73, 81, 87);
        n = chooseBestSum(230, 3, ts);
        assertNotNull(n);
        assertEquals(228, n.intValue());
    }

}
